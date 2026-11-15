package zone.moddev.mc.basegems.client;

import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.content.ModContent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

/** Adds the fluid textures and colours that Forge 25 doesn't render on its own. */
@OnlyIn(Dist.CLIENT)
public final class ClientMoltenGemRenderer {
    private static final ResourceLocation STILL = new ResourceLocation(BaseGems.MOD_ID, "blocks/molten_metal_still");
    private static final ResourceLocation FLOW = new ResourceLocation(BaseGems.MOD_ID, "blocks/molten_metal_flow");
    private static volatile TextureAtlasSprite[] sprites;

    private ClientMoltenGemRenderer() {}

    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(ClientMoltenGemRenderer::onTexturePre);
        MinecraftForge.EVENT_BUS.addListener(ClientMoltenGemRenderer::onTexturePost);
    }

    private static void onTexturePre(TextureStitchEvent.Pre event) {
        event.getMap().registerSprite(Minecraft.getInstance().getResourceManager(), STILL);
        event.getMap().registerSprite(Minecraft.getInstance().getResourceManager(), FLOW);
    }

    private static void onTexturePost(TextureStitchEvent.Post event) {
        sprites = new TextureAtlasSprite[] { event.getMap().getSprite(STILL), event.getMap().getSprite(FLOW) };
    }

    public static boolean useOpaqueFluidPath(IFluidState state, boolean vanillaValue) {
        return isOurs(state) || vanillaValue;
    }

    public static TextureAtlasSprite[] overrideSprites(IFluidState state, TextureAtlasSprite[] vanillaSprites) {
        TextureAtlasSprite[] current = sprites;
        return isOurs(state) && current != null ? current : vanillaSprites;
    }

    public static int overrideColor(IFluidState state, int vanillaColor) {
        return isOurs(state) ? ModContent.fluidColour(state.getFluid()) : vanillaColor;
    }

    private static boolean isOurs(IFluidState state) {
        return state != null && ModContent.isBaseGemsFluid(state.getFluid());
    }
}
