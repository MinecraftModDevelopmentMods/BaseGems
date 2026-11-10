package zone.moddev.mc.basegems.proxy;

import zone.moddev.mc.basegems.init.ItemGroups;
import zone.moddev.mc.basegems.init.VillagerTrades;
import zone.moddev.mc.basegems.util.Config;
import zone.moddev.mc.basegems.worldgen.GemWorldgen;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) { }

    public void init(FMLInitializationEvent event) {
        ItemGroups.setupIcons();
        VillagerTrades.init();
        GemWorldgen.enqueue();
    }

    public void postInit(FMLPostInitializationEvent event) {
        Config.postInit();
    }
}
