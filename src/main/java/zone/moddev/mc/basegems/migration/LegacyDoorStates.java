package zone.moddev.mc.basegems.migration;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.BitArray;

/** An old door stores facing and open on its bottom half, and hinge and power on its top. */
final class LegacyDoorStates {
    private static final String MARKER = "BaseGemsLegacyDoors";
    private static final int SECTION_SIZE = 4096;

    private LegacyDoorStates() {}

    static void capture(CompoundNBT level, Block[] doorIds) {
        ListNBT list = level.getList("Sections", 10);
        RawSection[] sections = new RawSection[16];
        for (int i = 0; i < list.size(); i++) {
            CompoundNBT section = list.getCompound(i);
            int y = section.getByte("Y");
            if (y >= 0 && y < sections.length && section.contains("Blocks", 7)) {
                sections[y] = new RawSection(section);
            }
        }
        ListNBT corrections = new ListNBT();
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

                BlockState bottom = LegacyWorldDataHook.legacyState(door, door.getRegistryName().getPath(), lower.meta(i));
                BlockState top = LegacyWorldDataHook.legacyState(door, door.getRegistryName().getPath(), upper.meta(upperIndex));
                BlockState correctedBottom = bottom.with(DoorBlock.HINGE, top.get(DoorBlock.HINGE))
                        .with(DoorBlock.POWERED, top.get(DoorBlock.POWERED));
                BlockState correctedTop = top.with(DoorBlock.FACING, bottom.get(DoorBlock.FACING))
                        .with(DoorBlock.OPEN, bottom.get(DoorBlock.OPEN));
                corrections.add(correction(y, i, correctedBottom));
                corrections.add(correction(upperY, upperIndex, correctedTop));
            }
        }
        if (!corrections.isEmpty()) level.put(MARKER, corrections);
    }

    static void apply(CompoundNBT level) {
        ListNBT corrections = level.getList(MARKER, 10);
        if (corrections.isEmpty()) return;
        Map<Integer, Map<Integer, CompoundNBT>> bySection = new HashMap<>();
        for (int i = 0; i < corrections.size(); i++) {
            CompoundNBT change = corrections.getCompound(i);
            bySection.computeIfAbsent(change.getInt("Y"), key -> new HashMap<>())
                    .put(change.getInt("Index"), change.getCompound("State"));
        }
        ListNBT sections = level.getList("Sections", 10);
        for (int i = 0; i < sections.size(); i++) {
            CompoundNBT section = sections.getCompound(i);
            Map<Integer, CompoundNBT> changes = bySection.get((int) section.getByte("Y"));
            if (changes == null) continue;
            ListNBT palette = section.getList("Palette", 10);
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
            section.putLongArray("BlockStates", flattened.getBackingLongArray());
        }
        level.remove(MARKER);
    }

    private static int bits(int states) { return Math.max(4, 32 - Integer.numberOfLeadingZeros(states - 1)); }

    private static CompoundNBT correction(int y, int index, BlockState state) {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("Y", y);
        tag.putInt("Index", index);
        tag.put("State", NBTUtil.writeBlockState(state));
        return tag;
    }

    private static final class RawSection {
        private final byte[] blocks, add, data;

        RawSection(CompoundNBT tag) {
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
