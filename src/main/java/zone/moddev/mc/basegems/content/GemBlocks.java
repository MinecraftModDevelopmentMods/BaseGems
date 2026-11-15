package zone.moddev.mc.basegems.content;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import zone.moddev.mc.basegems.data.Gem;

final class GemBlocks {
    private GemBlocks() {}

    static Map<String, Block> create(Gem gem) {
        String name = gem.name();
        int harvest = gem.requiredHarvestLevel();
        Map<String, Block> blocks = new LinkedHashMap<>();
        Block storage = new HarvestBlock(properties(gem), harvest);
        blocks.put(name + "_block", storage);
        blocks.put(name + "_ore", new GemOreBlock(gem, Block.Properties.create(Material.ROCK)
                .sound(SoundType.STONE).hardnessAndResistance(3, 5).variableOpacity()));
        blocks.put(name + "_plate", new PlateBlock(properties(gem).variableOpacity(), harvest));
        blocks.put(name + "_bars", new HarvestPane(properties(gem).variableOpacity(), harvest));
        blocks.put(name + "_door", new HarvestDoor(properties(gem).variableOpacity(), harvest));
        blocks.put(name + "_trapdoor", new HarvestTrapDoor(properties(gem).variableOpacity(), harvest));
        blocks.put(name + "_button", new HarvestButton(properties(gem).doesNotBlockMovement(), harvest));
        blocks.put(name + "_lever", new HarvestLever(properties(gem).doesNotBlockMovement(), harvest));
        blocks.put(name + "_pressure_plate", new HarvestPressurePlate(properties(gem), harvest));
        BlockSlab slab = new HarvestSlab(properties(gem), harvest);
        blocks.put(name + "_slab", slab);
        blocks.put("double_" + name + "_slab", new CompatibilityDoubleSlabBlock(properties(gem), harvest, slab));
        blocks.put(name + "_stairs", new HarvestStairs(storage.getDefaultState(), properties(gem), harvest));
        blocks.put(name + "_wall", new HarvestWall(properties(gem), harvest));
        return blocks;
    }

    private static Block.Properties properties(Gem gem) {
        return Block.Properties.create(Material.ROCK).sound(SoundType.STONE)
                .hardnessAndResistance(gem.hardness(), gem.hardness() * 2);
    }

    private static class HarvestBlock extends Block {
        private final int level;
        HarvestBlock(Properties properties, int level) { super(properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }

    private static class HarvestPane extends BlockPane {
        private final int level;
        HarvestPane(Properties properties, int level) { super(properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }
    private static class HarvestDoor extends BlockDoor {
        private final int level;
        HarvestDoor(Properties properties, int level) { super(properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }
    private static class HarvestTrapDoor extends BlockTrapDoor {
        private final int level;
        HarvestTrapDoor(Properties properties, int level) { super(properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }
    private static class HarvestButton extends BlockButtonStone {
        private final int level;
        HarvestButton(Properties properties, int level) { super(properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }
    private static class HarvestSlab extends BlockSlab {
        private final int level;
        HarvestSlab(Properties properties, int level) { super(properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }
    private static class HarvestLever extends BlockLever {
        private final int level;
        HarvestLever(Properties properties, int level) { super(properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }
    private static class HarvestPressurePlate extends BlockPressurePlate {
        private final int level;
        HarvestPressurePlate(Properties properties, int level) { super(Sensitivity.MOBS, properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }
    private static class HarvestStairs extends BlockStairs {
        private final int level;
        HarvestStairs(net.minecraft.block.state.IBlockState base, Properties properties, int level) { super(base, properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }
    private static class HarvestWall extends BlockWall {
        private final int level;
        HarvestWall(Properties properties, int level) { super(properties); this.level = level; }
        @Override public ToolType getHarvestTool(net.minecraft.block.state.IBlockState state) { return ToolType.PICKAXE; }
        @Override public int getHarvestLevel(net.minecraft.block.state.IBlockState state) { return level; }
    }
}
