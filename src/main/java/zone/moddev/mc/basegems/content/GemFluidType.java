package zone.moddev.mc.basegems.content;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import zone.moddev.mc.basegems.BaseGems;

/** A molten gem's fluid type, which also describes how the client draws it. */
public final class GemFluidType extends FluidType {
    private static final ResourceLocation STILL =
            ResourceLocation.fromNamespaceAndPath(BaseGems.MOD_ID, "block/molten_metal_still");
    private static final ResourceLocation FLOWING =
            ResourceLocation.fromNamespaceAndPath(BaseGems.MOD_ID, "block/molten_metal_flow");

    private final int colour;

    public GemFluidType(Properties properties, int colour) {
        super(properties);
        this.colour = 0xFF000000 | colour;
    }

    public int colour() {
        return colour;
    }

    /** Registered through {@code RegisterClientExtensionsEvent} by the client setup. */
    public IClientFluidTypeExtensions clientExtensions() {
        return new IClientFluidTypeExtensions() {
            @Override public ResourceLocation getStillTexture() { return STILL; }
            @Override public ResourceLocation getFlowingTexture() { return FLOWING; }
            @Override public int getTintColor() { return colour; }
        };
    }
}
