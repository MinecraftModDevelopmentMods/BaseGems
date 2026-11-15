package zone.moddev.mc.basegems.client;

import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.model.DynamicFluidContainerModel;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.content.FluidContent;
import zone.moddev.mc.basegems.content.ModContent;
import zone.moddev.mc.orespawn.api.client.WorldSettingsExtensionRegistry;

/**
 * Client entry point. MMDLib's client runtime draws the projectiles and gives bows,
 * crossbows, fishing rods and shields their item properties.
 */
@Mod(value = BaseGems.MOD_ID, dist = Dist.CLIENT)
public final class ClientSetup {
    public ClientSetup(IEventBus modBus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class,
                (modContainer, parent) -> new GemConfigScreen(parent));
        modBus.addListener(ClientSetup::setup);
        modBus.addListener(ClientSetup::registerClientExtensions);
        modBus.addListener(ClientSetup::registerItemColours);
    }

    private static void setup(FMLClientSetupEvent event) {
        // OreSpawn's world settings link to the same gem switches.
        event.enqueueWork(() -> WorldSettingsExtensionRegistry.registerConfigScreen(BaseGems.MOD_ID,
                GemConfigScreen::new));
    }

    private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        for (FluidContent fluid : ModContent.fluids().values()) {
            event.registerFluidType(fluid.type().get().clientExtensions(), fluid.type().get());
        }
    }

    /** Buckets show their molten gem's colour. */
    private static void registerItemColours(RegisterColorHandlersEvent.Item event) {
        event.register(new DynamicFluidContainerModel.Colors(), ModContent.fluids().values().stream()
                .map(fluid -> (Item) fluid.bucket().get()).toArray(Item[]::new));
    }
}
