package zone.moddev.mc.basegems;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zone.moddev.mc.basegems.config.GemConfig;
import zone.moddev.mc.basegems.client.ClientSetup;
import zone.moddev.mc.basegems.entity.ModEntities;
import zone.moddev.mc.basegems.recipe.CrushingRecipe;
import zone.moddev.mc.basegems.trade.GemTrades;
import zone.moddev.mc.basegems.worldgen.GemWorldgen;

@Mod(BaseGems.MOD_ID)
public final class BaseGems {
    public static final String MOD_ID = "basegems";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public BaseGems() {
        ModEntities.initialize();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GemConfig.SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(GemConfig::onConfigLoading);
        MinecraftForge.EVENT_BUS.register(new GemEvents());
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientSetup::register);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueue);
    }

    private void setup(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(zone.moddev.mc.basegems.network.GameplaySettingsNetwork::register);
    }
    private void enqueue(InterModEnqueueEvent event) { GemWorldgen.enqueue(); }
}
