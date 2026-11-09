package zone.moddev.mc.basegems.proxy;

import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.init.*;
import zone.moddev.mc.basegems.util.Config;
import zone.moddev.mc.basegems.worldgen.GemWorldgen;
import com.mcmoddev.lib.integration.IntegrationManager;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Base Gems Common Proxy
 *
 * @author Jasmine Iwanek
 *
 */
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {

		Config.init();

		Materials.init();
		Fluids.init();
		Blocks.init();
		Items.init();
		VillagerTrades.init();

		IntegrationManager.INSTANCE.preInit(event);
	}

	public void onRemap(FMLMissingMappingsEvent event) {
		for (final MissingMapping mapping : event.get()) {
			if (mapping.resourceLocation.getResourceDomain().equals(BaseGems.MODID)) {
				if (mapping.type.equals(GameRegistry.Type.BLOCK)) {
					// Dummy
				} else if (mapping.type.equals(GameRegistry.Type.ITEM)) {
					// Dummy
				}
			}
		}
	}

	public void init(FMLInitializationEvent event) {
		ItemGroups.setupIcons();
		GemWorldgen.enqueue();
	}

	public void postInit(FMLPostInitializationEvent event) {
		Config.postInit();
	}
}
