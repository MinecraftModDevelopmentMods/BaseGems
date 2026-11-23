package zone.moddev.mc.basegems.migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.mojang.datafixers.Dynamic;

import cpw.mods.modlauncher.api.INameMappingService;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.content.CompatibilityDoubleSlabBlock;
import zone.moddev.mc.basegems.content.ModContent;
import zone.moddev.mc.basegems.content.PlateBlock;

import net.minecraft.block.Block;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.fixes.BlockStateFlatteningMap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Restores old modded block IDs before Minecraft's 1.13 world conversion can
 * replace them with air. Also converts old item names, universal buckets and
 * durability data before Minecraft reads the items.
 */
public final class LegacyWorldDataHook {
    private static final ResourceLocation BLOCK_REGISTRY = new ResourceLocation("minecraft", "blocks");
    private static final Map<Integer, Set<Long>> LEGACY_GEMS_CHUNKS = new ConcurrentHashMap<>();
    private static final Map<Integer, Dynamic<?>> INSTALLED_STATES = new LinkedHashMap<>();
    private static final Map<Integer, Dynamic<?>> REPLACED_STATES = new LinkedHashMap<>();
    private static final Block[] LEGACY_DOORS = new Block[4096];
    private static final String PRESERVE_CHUNK_MARKER = "BaseGemsLegacyPreserveChunk";
    private static volatile boolean legacyWorldActive;

    private LegacyWorldDataHook() {}

    /** Save the world's registry once so unvisited chunks can be upgraded on later starts. */
    public static synchronized void prepareLegacyWorld(File levelDat) {
        legacyWorldActive = false;
        LEGACY_GEMS_CHUNKS.clear();
        Arrays.fill(LEGACY_DOORS, null);
        restorePreviousStates();
        File directory = levelDat.getParentFile();
        File sidecar = new File(directory, "basegems-legacy-registry.dat");
        try {
            CompoundNBT root = read(levelDat);
            CompoundNBT snapshot = read(sidecar);
            CompoundNBT registry = snapshot == null ? legacyRegistry(root) : snapshot.getCompound("Blocks");
            if (registry == null && snapshot == null) {
                registry = legacyRegistry(read(new File(directory, "level.dat_old")));
            }
            if (registry == null) return;
            int states = installLegacyBlockStates(registry);
            if (snapshot != null && states == 0) {
                throw new IllegalStateException("Legacy Base Gems snapshot has no valid block IDs: " + sidecar);
            }
            if (states == 0) return;
            if (snapshot == null) {
                captureLegacyChunks(directory);
                snapshot = new CompoundNBT();
                snapshot.put("Blocks", registry);
                CompoundNBT chunks = new CompoundNBT();
                LEGACY_GEMS_CHUNKS.forEach((dimension, positions) ->
                        chunks.putLongArray(dimension.toString(), new ArrayList<>(positions)));
                snapshot.put("Chunks", chunks);
                writeWithBackup(sidecar, snapshot);
            } else {
                CompoundNBT chunks = snapshot.getCompound("Chunks");
                for (String dimension : chunks.keySet()) {
                    Set<Long> positions = new HashSet<>();
                    for (long position : chunks.getLongArray(dimension)) positions.add(position);
                    LEGACY_GEMS_CHUNKS.put(Integer.parseInt(dimension), Collections.unmodifiableSet(positions));
                }
            }
            if (root != null) migrateLoosePlayerData(levelDat, root);
            legacyWorldActive = true;
            BaseGems.LOGGER.info("Prepared {} legacy Base Gems states and protected {} existing chunks in '{}'",
                    states, LEGACY_GEMS_CHUNKS.values().stream().mapToInt(Set::size).sum(), directory);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not prepare legacy Base Gems world " + directory, exception);
        }
    }

    /** The optimiser can run before SaveHandler.loadWorldInfo, on a separate worker. */
    public static void prepareOptimizer(SaveHandler handler) {
        if (handler instanceof SaveHandler) {
            SaveHandler save = (SaveHandler) handler;
            prepareLegacyWorld(new File(save.getWorldDirectory(), "level.dat"));
        }
    }

    private static CompoundNBT read(File file) throws IOException {
        if (!file.isFile()) return null;
        try (FileInputStream input = new FileInputStream(file)) {
            return CompressedStreamTools.readCompressed(input);
        }
    }

    private static CompoundNBT legacyRegistry(CompoundNBT root) {
        if (root == null) return null;
        CompoundNBT registry = root.getCompound("FML").getCompound("Registries")
                .getCompound(BLOCK_REGISTRY.toString());
        return registry.contains("ids", 9) ? registry : null;
    }

    /** Called by the coremod immediately after a legacy chunk NBT is read. */
    public static synchronized void prepareLegacyChunk(CompoundNBT root) {
        if (!legacyWorldActive || root == null || root.getInt("DataVersion") > 1343 || !root.contains("Level", 10)) return;
        migrateLegacyItems(root);
        CompoundNBT level = root.getCompound("Level");
        LegacyDoorStates.capture(level, LEGACY_DOORS);
        // Chests or entities may contain the only Base Gems items in this chunk.
        // Mark it as finished terrain too, or 1.13 can regenerate it and lose those items.
        level.putBoolean("TerrainPopulated", true);
        level.putBoolean("LightPopulated", true);
        level.putBoolean(PRESERVE_CHUNK_MARKER, true);
    }

    /** Called after vanilla datafixing, before a legacy chunk is returned. */
    public static CompoundNBT finalizeLegacyChunk(CompoundNBT root) {
        if (root != null && root.contains("Level", 10)) {
            CompoundNBT level = root.getCompound("Level");
            if (level.getBoolean(PRESERVE_CHUNK_MARKER)) {
                level.putString("Status", "full");
                LegacyDoorStates.apply(level);
                level.remove(PRESERVE_CHUNK_MARKER);
            }
        }
        return root;
    }

    public static boolean shouldBlockWorldgenWrite(WorldGenRegion region, net.minecraft.util.math.BlockPos position) {
        if (!legacyWorldActive) return false;
        Set<Long> chunks = LEGACY_GEMS_CHUNKS.get(region.getDimension().getType().getId());
        return chunks != null && chunks.contains(chunkKey(position.getX() >> 4, position.getZ() >> 4));
    }

    private static void restorePreviousStates() {
        if (INSTALLED_STATES.isEmpty()) return;
        Dynamic<?>[] table = expandFlatteningTable(0);
        INSTALLED_STATES.forEach((id, state) -> {
            if (table[id] == state) table[id] = REPLACED_STATES.get(id);
        });
        INSTALLED_STATES.clear();
        REPLACED_STATES.clear();
    }

    private static int installLegacyBlockStates(CompoundNBT snapshot) {
        Map<ResourceLocation, Integer> ids = new LinkedHashMap<ResourceLocation, Integer>();
        ListNBT savedIds = snapshot.getList("ids", 10);
        int highestState = 0;
        for (int index = 0; index < savedIds.size(); index++) {
            CompoundNBT entry = savedIds.getCompound(index);
            String normalized = normalizeLegacyRegistryName(entry.getString("K"));
            int separator = normalized.indexOf(':');
            if (separator < 0) continue;
            String namespace = normalized.substring(0, separator);
            if (!BaseGems.MOD_ID.equals(namespace)) continue;
            ResourceLocation id = new ResourceLocation(normalized);
            int numeric = entry.getInt("V");
            if (numeric <= 0 || numeric >= 4096) throw new IllegalStateException("Invalid legacy Base Gems ID " + numeric);
            ids.put(id, Integer.valueOf(numeric));
            highestState = Math.max(highestState, (numeric << 4) | 15);
        }
        if (ids.isEmpty()) return 0;
        Dynamic<?>[] table = expandFlatteningTable(highestState + 1);
        int mapped = 0;
        for (Map.Entry<ResourceLocation, Integer> entry : ids.entrySet()) {
            Block block = resolveCurrentBlock(entry.getKey());
            if (block instanceof DoorBlock) LEGACY_DOORS[entry.getValue()] = block;
            for (int meta = 0; meta < 16; meta++) {
                BlockState state = legacyState(block, entry.getKey().getPath(), meta);
                int id = (entry.getValue() << 4) | meta;
                Dynamic<?> fixed = BlockStateFlatteningMap.makeDynamic(NBTUtil.writeBlockState(state).toString());
                REPLACED_STATES.put(id, table[id]);
                INSTALLED_STATES.put(id, fixed);
                table[id] = fixed;
                mapped++;
            }
        }
        return mapped;
    }

    private static Block resolveCurrentBlock(ResourceLocation oldId) {
        String path = oldId.getPath();
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BaseGems.MOD_ID, path));
        if (block == null) throw new IllegalStateException("No Base Gems replacement for legacy block " + oldId);
        return block;
    }

    static BlockState legacyState(Block block, String path, int meta) {
        BlockState state = block.getDefaultState();
        if (block instanceof PlateBlock) return state.with(PlateBlock.FACING, Direction.byIndex(meta));
        if (block instanceof CompatibilityDoubleSlabBlock) return state.with(SlabBlock.TYPE, SlabType.DOUBLE);
        if (block instanceof SlabBlock) return state.with(SlabBlock.TYPE,
                (meta & 8) == 0 ? SlabType.BOTTOM : SlabType.TOP);
        if (block instanceof StairsBlock) {
            Direction facing = Direction.byIndex(5 - (meta & 3));
            return state.with(StairsBlock.FACING, facing)
                    .with(StairsBlock.HALF, (meta & 4) == 0 ? Half.BOTTOM : Half.TOP)
                    .with(StairsBlock.SHAPE, StairsShape.STRAIGHT)
                    .with(StairsBlock.WATERLOGGED, Boolean.FALSE);
        }
        if (block instanceof DoorBlock) {
            if ((meta & 8) != 0) {
                return state.with(DoorBlock.HALF, DoubleBlockHalf.UPPER)
                        .with(DoorBlock.HINGE, (meta & 1) != 0 ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT)
                        .with(DoorBlock.POWERED, Boolean.valueOf((meta & 2) != 0));
            }
            return state.with(DoorBlock.HALF, DoubleBlockHalf.LOWER)
                    .with(DoorBlock.FACING, Direction.byHorizontalIndex(meta & 3).rotateYCCW())
                    .with(DoorBlock.OPEN, Boolean.valueOf((meta & 4) != 0));
        }
        if (block instanceof TrapDoorBlock) {
            Direction[] facing = { Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST };
            return state.with(HorizontalBlock.HORIZONTAL_FACING, facing[meta & 3])
                    .with(TrapDoorBlock.OPEN, Boolean.valueOf((meta & 4) != 0))
                    .with(TrapDoorBlock.HALF, (meta & 8) == 0 ? Half.BOTTOM : Half.TOP)
                    .with(TrapDoorBlock.POWERED, Boolean.FALSE)
                    .with(TrapDoorBlock.WATERLOGGED, Boolean.FALSE);
        }
        if (block instanceof AbstractButtonBlock) return attachedState(state, meta, AbstractButtonBlock.POWERED);
        if (block instanceof LeverBlock) return legacyLeverState(state, meta);
        if (block instanceof PressurePlateBlock) return state.with(PressurePlateBlock.POWERED,
                Boolean.valueOf(meta > 0));
        if (block instanceof FlowingFluidBlock) return state.with(FlowingFluidBlock.LEVEL,
                Integer.valueOf(Math.min(15, meta)));
        return state;
    }

    private static BlockState attachedState(BlockState state, int meta,
            net.minecraft.state.BooleanProperty powered) {
        Direction oldFacing;
        switch (meta & 7) {
            case 0: oldFacing = Direction.DOWN; break;
            case 1: oldFacing = Direction.EAST; break;
            case 2: oldFacing = Direction.WEST; break;
            case 3: oldFacing = Direction.SOUTH; break;
            case 4: oldFacing = Direction.NORTH; break;
            default: oldFacing = Direction.UP;
        }
        AttachFace face = oldFacing == Direction.DOWN ? AttachFace.CEILING
                : oldFacing == Direction.UP ? AttachFace.FLOOR : AttachFace.WALL;
        Direction horizontal = oldFacing.getAxis().isHorizontal() ? oldFacing : Direction.NORTH;
        return state.with(HorizontalFaceBlock.FACE, face)
                .with(HorizontalBlock.HORIZONTAL_FACING, horizontal)
                .with(powered, Boolean.valueOf((meta & 8) != 0));
    }

    private static BlockState legacyLeverState(BlockState state, int meta) {
        int orientation = meta & 7;
        AttachFace face;
        Direction horizontal;
        if (orientation == 0 || orientation == 7) {
            face = AttachFace.CEILING;
            horizontal = orientation == 0 ? Direction.EAST : Direction.NORTH;
        } else if (orientation == 5 || orientation == 6) {
            face = AttachFace.FLOOR;
            horizontal = orientation == 6 ? Direction.EAST : Direction.NORTH;
        } else {
            face = AttachFace.WALL;
            horizontal = new Direction[] { Direction.NORTH, Direction.EAST, Direction.WEST,
                    Direction.SOUTH, Direction.NORTH }[orientation];
        }
        return state.with(HorizontalFaceBlock.FACE, face)
                .with(HorizontalBlock.HORIZONTAL_FACING, horizontal)
                .with(LeverBlock.POWERED, Boolean.valueOf((meta & 8) != 0));
    }

    public static String normalizeLegacyRegistryName(String name) {
        if (name == null) return "";
        String value = name.trim().toLowerCase(Locale.ROOT);
        return value;
    }

    private static void migrateLoosePlayerData(File levelDat, CompoundNBT root) throws IOException {
        boolean changed = migrateLegacyItems(root) > 0;
        if (changed && levelDat.isFile()) writeWithBackup(levelDat, root);
        File playerData = new File(levelDat.getParentFile(), "playerdata");
        File[] files = playerData.listFiles((directory, name) -> name.endsWith(".dat"));
        if (files == null) return;
        Arrays.sort(files);
        for (File file : files) {
            try (FileInputStream input = new FileInputStream(file)) {
                CompoundNBT player = CompressedStreamTools.readCompressed(input);
                if (migrateLegacyItems(player) > 0) writeWithBackup(file, player);
            }
        }
    }

    private static int migrateLegacyItems(INBT value) {
        int changed = 0;
        if (value instanceof CompoundNBT) {
            CompoundNBT compound = (CompoundNBT) value;
            String id = compound.getString("id");
            if ("forge:bucketfilled".equalsIgnoreCase(id) && compound.contains("tag", 10)) {
                CompoundNBT tag = compound.getCompound("tag");
                String oldFluid = tag.getString("FluidName").toLowerCase(Locale.ROOT);
                String target = oldFluid.startsWith("basegems:") || oldFluid.startsWith("basegems.")
                        ? oldFluid.substring("basegems:".length()) : oldFluid;
                if (ModContent.fluid(target) != null) {
                    compound.putString("id", BaseGems.MOD_ID + ":" + target + "_bucket");
                    tag.remove("FluidName");
                    tag.remove("Amount");
                    if (tag.isEmpty()) compound.remove("tag");
                    changed++;
                }
            } else if (compound.contains("Count", 99)) {
                ResourceLocation oldId = safeId(id);
                if (oldId != null && BaseGems.MOD_ID.equals(oldId.getNamespace())) {
                    String path = oldId.getPath();
                    if (path.endsWith("_door_item")) path = path.substring(0, path.length() - 5);
                    ResourceLocation target = ModContent.id(path);
                    if (!target.toString().equals(id)) {
                        compound.putString("id", target.toString());
                        changed++;
                    }
                    if (compound.getInt("Damage") > 0 && ModContent.item(path) != null && ModContent.item(path).isDamageable()) {
                        CompoundNBT tag = compound.contains("tag", 10)
                                ? compound.getCompound("tag") : new CompoundNBT();
                        if (!tag.contains("Damage", 99)) {
                            tag.putInt("Damage", compound.getInt("Damage"));
                            compound.put("tag", tag);
                            changed++;
                        }
                    }
                }
            }
            for (String key : new ArrayList<String>(compound.keySet())) {
                INBT child = compound.get(key);
                if (child != null) changed += migrateLegacyItems(child);
            }
        } else if (value instanceof ListNBT) {
            ListNBT list = (ListNBT) value;
            for (int index = 0; index < list.size(); index++) changed += migrateLegacyItems(list.get(index));
        }
        return changed;
    }

    private static ResourceLocation safeId(String value) {
        try { return value == null || value.isEmpty() ? null : new ResourceLocation(value.toLowerCase(Locale.ROOT)); }
        catch (RuntimeException ignored) { return null; }
    }

    private static void writeWithBackup(File file, CompoundNBT data) throws IOException {
        File backup = new File(file.getParentFile(), file.getName() + ".basegems-legacy-backup");
        if (file.exists() && !backup.exists()) Files.copy(file.toPath(), backup.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        File temporary = File.createTempFile(file.getName(), ".basegems.tmp", file.getParentFile());
        try (FileOutputStream output = new FileOutputStream(temporary)) {
            CompressedStreamTools.writeCompressed(data, output);
        }
        try {
            Files.move(temporary.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (java.nio.file.AtomicMoveNotSupportedException ignored) {
            Files.move(temporary.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static void captureLegacyChunks(File directory) {
        indexLegacyChunks(new File(directory, "region"), 0);
        File[] dimensions = directory.listFiles(file -> file.isDirectory() && file.getName().matches("DIM-?\\d+"));
        if (dimensions == null) return;
        for (File dimension : dimensions) {
            indexLegacyChunks(new File(dimension, "region"), Integer.parseInt(dimension.getName().substring(3)));
        }
    }

    private static void indexLegacyChunks(File regionDirectory, int dimension) {
        File[] regions = regionDirectory.listFiles((directory, name) -> name.matches("r\\.-?\\d+\\.-?\\d+\\.mc[ar]"));
        if (regions == null) return;
        Set<Long> chunks = new HashSet<>();
        byte[] locations = new byte[4096];
        for (File region : regions) {
            String[] parts = region.getName().split("\\.");
            if (parts.length != 4) continue;
            try (InputStream input = Files.newInputStream(region.toPath())) {
                int read = 0;
                while (read < locations.length) {
                    int count = input.read(locations, read, locations.length - read);
                    if (count < 0) break;
                    read += count;
                }
                int regionX = Integer.parseInt(parts[1]);
                int regionZ = Integer.parseInt(parts[2]);
                for (int index = 0; index < read / 4; index++) {
                    int offset = index * 4;
                    if ((locations[offset] | locations[offset + 1] | locations[offset + 2] | locations[offset + 3]) != 0) {
                        chunks.add(Long.valueOf(chunkKey(
                                regionX * 32 + (index & 31), regionZ * 32 + (index >> 5))));
                    }
                }
            } catch (IOException | NumberFormatException exception) {
                BaseGems.LOGGER.warn("Could not inspect legacy chunk locations in '{}'", region, exception);
            }
        }
        LEGACY_GEMS_CHUNKS.put(dimension, Collections.unmodifiableSet(chunks));
    }

    private static long chunkKey(int x, int z) {
        return ((long) x & 0xffffffffL) << 32 | ((long) z & 0xffffffffL);
    }

    @SuppressWarnings("unchecked")
    private static Dynamic<?>[] expandFlatteningTable(int requiredLength) {
        try {
            String fieldName = ObfuscationReflectionHelper.remapName(INameMappingService.Domain.FIELD, "field_199200_b");
            Field valuesField = BlockStateFlatteningMap.class.getDeclaredField(fieldName);
            valuesField.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(valuesField, valuesField.getModifiers() & ~Modifier.FINAL);
            Dynamic<?>[] current = (Dynamic<?>[]) valuesField.get(null);
            if (current.length >= requiredLength) return current;
            Dynamic<?>[] expanded = Arrays.copyOf(current, requiredLength);
            valuesField.set(null, expanded);
            return expanded;
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Could not expand Minecraft's legacy block-state flattening table", exception);
        }
    }

}
