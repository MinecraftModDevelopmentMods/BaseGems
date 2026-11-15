package zone.moddev.mc.basegems.content;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.lib.api.ItemContext;
import zone.moddev.mc.lib.api.ItemImplementation;
import zone.moddev.mc.lib.api.MaterialExtensions;
import zone.moddev.mc.lib.common.types.MMDItem;
import zone.moddev.mc.lib.common.types.MMDMaterial;

/** A bucket of molten gem, made by the {@code basegems:bucket} item form. */
public final class GemBucketItem extends BucketItem {
    public GemBucketItem(Fluid fluid, Properties properties) {
        super(fluid, properties);
    }

    /** Must run before MMDLib decodes the gem materials that select {@code basegems:bucket}. */
    public static void registerImplementation() {
        MaterialExtensions.GLOBAL.registerItemImplementation(
                ResourceLocation.fromNamespaceAndPath(BaseGems.MOD_ID, "bucket"), new Implementation());
    }

    /** Lets fluid tanks and pipes fill from and empty into the buckets. */
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        ModContent.fluids().values().forEach(fluid -> event.registerItem(Capabilities.FluidHandler.ITEM,
                (stack, context) -> new FluidBucketWrapper(stack), fluid.bucket().get()));
    }

    private static final class Implementation implements ItemImplementation<GemBucketItem> {
        @Override
        public Class<GemBucketItem> itemClass() {
            return GemBucketItem.class;
        }

        @Override
        public GemBucketItem create(MMDMaterial material, MMDItem definition) {
            throw new IllegalStateException("Created by the runtime registrar with an ItemContext: " + definition.name());
        }

        @Override
        public GemBucketItem create(ItemContext context) {
            String gem = context.material().name().orElseThrow().getPath();
            FluidContent fluid = ModContent.fluids().get(gem);
            if (fluid == null) throw new IllegalStateException("No molten fluid for " + gem);
            return new GemBucketItem(fluid.source().get(), context.properties());
        }
    }
}
