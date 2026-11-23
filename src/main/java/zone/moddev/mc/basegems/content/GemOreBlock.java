package zone.moddev.mc.basegems.content;

import net.minecraft.block.OreBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import zone.moddev.mc.basegems.data.Gem;
import zone.moddev.mc.basegems.data.GemRarity;

final class GemOreBlock extends OreBlock {
    private final Gem gem;

    GemOreBlock(Gem gem, Properties properties) {
        super(properties);
        this.gem = gem;
    }

    @Override public ToolType getHarvestTool(BlockState state) { return ToolType.PICKAXE; }
    @Override public int getHarvestLevel(BlockState state) { return gem.requiredHarvestLevel(); }
    @Override public BlockRenderLayer getRenderLayer() { return BlockRenderLayer.CUTOUT_MIPPED; }


    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silkTouch) {
        if (silkTouch > 0) return 0;
        GemRarity rarity = GemRarity.of(gem.name());
        return rarity.minXp + java.util.concurrent.ThreadLocalRandom.current()
                .nextInt(rarity.maxXp - rarity.minXp + 1);
    }
}
