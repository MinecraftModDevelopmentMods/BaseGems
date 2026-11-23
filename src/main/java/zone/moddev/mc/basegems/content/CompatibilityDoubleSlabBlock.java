package zone.moddev.mc.basegems.content;

import net.minecraft.block.SlabBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

/** Keeps old double slabs intact when a 1.10 or 1.12 world is upgraded. */
public final class CompatibilityDoubleSlabBlock extends SlabBlock {
    private final int harvestLevel;
    private final SlabBlock single;

    public CompatibilityDoubleSlabBlock(Properties properties, int harvestLevel, SlabBlock single) {
        super(properties);
        this.harvestLevel = harvestLevel;
        this.single = single;
        setDefaultState(getDefaultState().with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override public ToolType getHarvestTool(BlockState state) { return ToolType.PICKAXE; }
    @Override public int getHarvestLevel(BlockState state) { return harvestLevel; }
}
