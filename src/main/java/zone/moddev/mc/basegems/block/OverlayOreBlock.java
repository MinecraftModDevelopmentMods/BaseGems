package zone.moddev.mc.basegems.block;

import com.mcmoddev.lib.block.BlockMMDOre;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.material.MMDMaterial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zone.moddev.mc.basegems.data.GemRarity;

/** A gem ore with mining XP and alpha testing for overlay models. */
public final class OverlayOreBlock extends BlockMMDOre {
    private final GemRarity rarity;

    public OverlayOreBlock(MMDMaterial material) {
        super(material);
        this.rarity = GemRarity.of(material.getName());
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        if (!getMMDMaterial().hasItem(Names.GEM)) {
            return 0;
        }
        // Forge calls this for a successful harvest without Silk Touch.
        return rarity.minXp + RANDOM.nextInt(rarity.maxXp - rarity.minXp + 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
