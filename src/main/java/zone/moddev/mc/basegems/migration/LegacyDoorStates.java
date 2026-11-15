package zone.moddev.mc.basegems.migration;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.BitArray;

/** An old door stores facing and open on its bottom half, and hinge and power on its top. */
final class LegacyDoorStates {
    private static final String MARKER = "BaseGemsLegacyDoors";
    private static final int SECTION_SIZE = 4096;

    private LegacyDoorStates() {}

    static void capture(NBTTagCompound level, Block[] doorIds) {
        NBTTagList list = level.getList("Sections", 10);
        RawSection[] sections = new RawSection[16];
        for (int i = 0; i < list.size(); i++) {
            NBTTagCompound section = list.getCompound(i);
            int y = section.getByte("Y");
            if (y >= 0 && y < sections.length && section.contains("Blocks", 7)) {
                sections[y] = new RawSection(section);
            }
        }
        NBTTagList corrections = new NBTTagList();
        for (int y = 0; y < sections.length; y++) {
            RawSection lower = sections[y];
            if (lower == null) continue;
            for (int i = 0; i < SECTION_SIZE; i++) {
                int id = lower.id(i);
                Block door = doorIds[id];
                if (door == null || (lower.meta(i) & 8) != 0) continue;
                int upperIndex = i + 256;
                int upperY = y + upperIndex / SECTION_SIZE;
                upperIndex %= SECTION_SIZE;
                if (upperY >= sections.length) continue;
                RawSection upper = sections[upperY];
                if (upper == null || upper.id(upperIndex) != id || (upper.meta(upperIndex) & 8) == 0) continue;

                IBlockState bottom = LegacyWorldDataHook.legacyState(door, door.getRegistryName().getPath(), lower.meta(i));
                IBlockState top = LegacyWorldDataHook.legacyState(door, door.getRegistryName().getPath(), upper.meta(upperIndex));
                IBlockState correctedBottom = bottom.with(BlockDoor.HINGE, top.get(BlockDoor.HINGE))
                        .with(BlockDoor.POWERED, top.get(BlockDoor.POWERED));
                IBlockState correctedTop = top.with(BlockDoor.FACING, bottom.get(BlockDoor.FACING))
                        .with(BlockDoor.OPEN, bottom.get(BlockDoor.OPEN));
                corrections.add(correction(y, i, correctedBottom));
                corrections.add(correction(upperY, upperIndex, correctedTop));
            }
        }
        if (!corrections.isEmpty()) level.setTag(MARKER, corrections);
    }

    static void apply(NBTTagCompound level) {
        NBTTagList corrections = level.getList(MARKER, 10);
        if (corrections.isEmpty()) return;
        Map<Integer, Map<Integer, NBTTagCompound>> bySection = new HashMap<>();
        for (int i = 0; i < corrections.size(); i++) {
            NBTTagCompound change = corrections.getCompound(i);
            bySection.computeIfAbsent(change.getInt("Y"), key -> new HashMap<>())
                    .put(change.getInt("Index"), change.getCompound("State"));
        }
        NBTTagList sections = level.getList("Sections", 10);
        for (int i = 0; i < sections.size(); i++) {
            NBTTagCompound section = sections.getCompound(i);
            Map<Integer, NBTTagCompound> changes = bySection.get((int) section.getByte("Y"));
            if (changes == null) continue;
            NBTTagList palette = section.getList("Palette", 10);
            BitArray original = new BitArray(bits(palette.size()), SECTION_SIZE, section.getLongArray("BlockStates"));
            Map<Integer, Integer> indices = new HashMap<>();
            changes.forEach((index, state) -> {
                int entry = palette.indexOf(state);
                if (entry < 0) {
                    entry = palette.size();
                    palette.add(state);
                }
                indices.put(index, entry);
            });
            BitArray flattened = new BitArray(bits(palette.size()), SECTION_SIZE);
            for (int index = 0; index < SECTION_SIZE; index++) {
                flattened.setAt(index, indices.getOrDefault(index, original.getAt(index)));
            }
            section.setLongArray("BlockStates", flattened.getBackingLongArray());
        }
        level.removeTag(MARKER);
    }

    private static int bits(int states) { return Math.max(4, 32 - Integer.numberOfLeadingZeros(states - 1)); }

    private static NBTTagCompound correction(int y, int index, IBlockState state) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInt("Y", y);
        tag.setInt("Index", index);
        tag.setTag("State", NBTUtil.writeBlockState(state));
        return tag;
    }

    private static final class RawSection {
        private final byte[] blocks, add, data;

        RawSection(NBTTagCompound tag) {
            blocks = tag.getByteArray("Blocks");
            add = tag.getByteArray("Add");
            data = tag.getByteArray("Data");
        }

        int id(int index) { return (blocks[index] & 255) | (nibble(add, index) << 8); }
        int meta(int index) { return nibble(data, index); }

        private static int nibble(byte[] array, int index) {
            return array.length == 2048 ? (array[index >> 1] >> ((index & 1) * 4)) & 15 : 0;
        }
    }
}
