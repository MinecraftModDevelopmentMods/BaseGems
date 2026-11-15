package zone.moddev.mc.basegems;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.common.NeoForge;
import zone.moddev.mc.basegems.config.GemConfig;
import zone.moddev.mc.basegems.content.ModContent;
import zone.moddev.mc.basegems.recipe.ModRecipes;
import zone.moddev.mc.basegems.worldgen.GemWorldgen;

@Mod(BaseGems.MOD_ID)
public final class BaseGems {
    public static final String MOD_ID = "basegems";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BaseGems(IEventBus modBus, ModContainer container) {
        GemConfig.register(container);
        modBus.addListener(GemConfig::onConfigLoading);
        ModContent.register(modBus);
        ModRecipes.register(modBus);
        modBus.addListener(InterModEnqueueEvent.class, event -> GemWorldgen.enqueue());
        NeoForge.EVENT_BUS.register(new GemEvents());
    }
}
