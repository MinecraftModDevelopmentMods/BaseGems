package com.mcmoddev.basegems.util;

import java.io.File;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.lib.util.MaterialConfigOptions;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Jasmine Iwanek
 *
 */
public class BGeConfig extends com.mcmoddev.lib.util.Config {

	private static Configuration configuration;
	private static final String CONFIG_FILE = "config/BaseGems.cfg";

	private static final MaterialConfigOptions[] MATERIAL_CONFIG_OPTIONS = new MaterialConfigOptions[] {
			new MaterialConfigOptions(MaterialNames.AMBER, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.ALEXANDRITE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.AGATE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.AMETRINE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.AMETHYST, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.AQUAMARINE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.BERYL, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.BLACKDIAMOND, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.BLUETOPAZ, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.CARNELIAN, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.CITRINE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.GARNET, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.GOLDENBERYL, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.HELIODOR, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.INDICOLITE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.IOLITE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.JADE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.JASPER, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.LEPIDOLITE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.MALACHITE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.MOLDAVITE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.MOONSTONE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.MORGANITE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.ONYX, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.OPAL, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.PERIDOT, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.RUBY, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.SAPPHIRE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.SPINEL, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.TANZANITE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.TOPAZ, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.TURQUOISE, false, true, true, true),
			new MaterialConfigOptions(MaterialNames.VIOLETSAPPHIRE, false, true, true, true)
	};
	
	@SubscribeEvent
	public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.getModID().equals(BaseGems.MODID)) {
			init();
		}
	}

	public static void init() {
		if (configuration == null) {
			configuration = new Configuration(new File(CONFIG_FILE));
			MinecraftForge.EVENT_BUS.register(new BGeConfig());
		}

		configMaterialOptions(MATERIAL_CONFIG_OPTIONS, configuration);

		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
}
