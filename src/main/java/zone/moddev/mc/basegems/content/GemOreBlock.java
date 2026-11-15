package zone.moddev.mc.basegems.content;

import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import zone.moddev.mc.basegems.data.Gem;
import zone.moddev.mc.basegems.data.GemRarity;

final class GemOreBlock extends BlockOre {
    private final Gem gem;

    GemOreBlock(Gem gem, Properties properties) {
        super(properties);
        this.gem = gem;
    }

    @Override public ToolType getHarvestTool(IBlockState state) { return ToolType.PICKAXE; }
    @Override public int getHarvestLevel(IBlockState state) { return gem.requiredHarvestLevel(); }
    @Override public BlockRenderLayer getRenderLayer() { return BlockRenderLayer.CUTOUT_MIPPED; }

    @Override
    public IItemProvider getItemDropped(IBlockState state, World world, BlockPos pos, int fortune) {
        return ModContent.item(gem.name() + "_ingot");
    }

    @Override
    public int getExpDrop(IBlockState state, IWorldReader world, BlockPos pos, int fortune) {
        GemRarity rarity = GemRarity.of(gem.name());
        return rarity.minXp + java.util.concurrent.ThreadLocalRandom.current()
                .nextInt(rarity.maxXp - rarity.minXp + 1);
    }
}
