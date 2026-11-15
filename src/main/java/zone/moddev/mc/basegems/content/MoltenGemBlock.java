package zone.moddev.mc.basegems.content;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

/** Molten gem burns like lava, as it did when its block used lava's material. */
public final class MoltenGemBlock extends LiquidBlock {
    public MoltenGemBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);
        // Respects fire immunity and Fire Resistance, as lava does.
        if (!level.isClientSide()) entity.lavaHurt();
    }
}
