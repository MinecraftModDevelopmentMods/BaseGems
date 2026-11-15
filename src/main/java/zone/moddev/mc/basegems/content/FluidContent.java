package zone.moddev.mc.basegems.content;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;

/** A molten gem: its fluid type, source and flowing fluids, liquid block and bucket. */
public record FluidContent(
        DeferredHolder<FluidType, GemFluidType> type,
        DeferredHolder<Fluid, BaseFlowingFluid.Source> source,
        DeferredHolder<Fluid, BaseFlowingFluid.Flowing> flowing,
        DeferredHolder<Block, ? extends LiquidBlock> block,
        DeferredHolder<Item, ? extends Item> bucket) {
}
