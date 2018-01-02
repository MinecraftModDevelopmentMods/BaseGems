package com.mcmoddev.basegems.util;

import java.io.*;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.lib.registry.CrusherRecipeRegistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Jasmine Iwanek
 *
 */
public class Config extends com.mcmoddev.lib.util.ConfigBase {

	private static Configuration configuration;
	private static final String CONFIG_FILE = "config/BaseGems.cfg";
	private static final String MATERIALS_CAT = "Gems";

	@SubscribeEvent
	public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.getModID().equals(BaseGems.MODID)) {
			init();
		}
	}

	public static void init() {
		if (configuration == null) {
			configuration = new Configuration(new File(CONFIG_FILE));
			MinecraftForge.EVENT_BUS.register(com.mcmoddev.basegems.util.Config.class);
		}

		
		Options.materialEnabled(MaterialNames.AMBER, configuration.getBoolean("EnableAmber", MATERIALS_CAT, true, "Enable Amber Items and Materials"));
		Options.materialEnabled(MaterialNames.ALEXANDRITE, configuration.getBoolean("EnableAlexandrite", MATERIALS_CAT, true, "Enable Alexandrite Items and Materials"));
		Options.materialEnabled(MaterialNames.AGATE, configuration.getBoolean("EnableAgate", MATERIALS_CAT, true, "Enable Agate Items and Materials"));
		Options.materialEnabled(MaterialNames.AMETRINE, configuration.getBoolean("EnableAmetrine", MATERIALS_CAT, true, "Enable Ametrine Items and Materials"));
		Options.materialEnabled(MaterialNames.AMETHYST, configuration.getBoolean("EnableAmethyst", MATERIALS_CAT, true, "Enable Amethyst Items and Materials"));
		Options.materialEnabled(MaterialNames.AQUAMARINE, configuration.getBoolean("EnableAquamarine", MATERIALS_CAT, true, "Enable Aquamarine Items and Materials"));
		Options.materialEnabled(MaterialNames.BERYL, configuration.getBoolean("EnableBeryl", MATERIALS_CAT, true, "Enable Beryl Items and Materials"));
		Options.materialEnabled(MaterialNames.BLACKDIAMOND, configuration.getBoolean("EnableBlackdiamond", MATERIALS_CAT, true, "Enable Blackdiamond Items and Materials"));
		Options.materialEnabled(MaterialNames.BLUETOPAZ, configuration.getBoolean("EnableBluetopaz", MATERIALS_CAT, true, "Enable Bluetopaz Items and Materials"));
		Options.materialEnabled(MaterialNames.CARNELION, configuration.getBoolean("EnableCarnelion", MATERIALS_CAT, true, "Enable Carnelion Items and Materials"));
		Options.materialEnabled(MaterialNames.CITRINE, configuration.getBoolean("EnableCitrine", MATERIALS_CAT, true, "Enable Citrine Items and Materials"));
		Options.materialEnabled(MaterialNames.GARNET, configuration.getBoolean("EnableGarnet", MATERIALS_CAT, true, "Enable Garnet Items and Materials"));
		Options.materialEnabled(MaterialNames.GOLDENBERYL, configuration.getBoolean("EnableGoldenberyl", MATERIALS_CAT, true, "Enable Goldenberyl Items and Materials"));
		Options.materialEnabled(MaterialNames.HELIODOR, configuration.getBoolean("EnableHeliodor", MATERIALS_CAT, true, "Enable Heliodor Items and Materials"));
		Options.materialEnabled(MaterialNames.INDICOLITE, configuration.getBoolean("EnableIndicolite", MATERIALS_CAT, true, "Enable Indicolite Items and Materials"));
		Options.materialEnabled(MaterialNames.IOLITE, configuration.getBoolean("EnableIolite", MATERIALS_CAT, true, "Enable Iolite Items and Materials"));
		Options.materialEnabled(MaterialNames.JADE, configuration.getBoolean("EnableJade", MATERIALS_CAT, true, "Enable Jade Items and Materials"));
		Options.materialEnabled(MaterialNames.JASPER, configuration.getBoolean("EnableJasper", MATERIALS_CAT, true, "Enable Jasper Items and Materials"));
		Options.materialEnabled(MaterialNames.LEPIDOLITE, configuration.getBoolean("EnableLepidolite", MATERIALS_CAT, true, "Enable Lepidolite Items and Materials"));
		Options.materialEnabled(MaterialNames.MALACHITE, configuration.getBoolean("EnableMalachite", MATERIALS_CAT, true, "Enable Malachite Items and Materials"));
		Options.materialEnabled(MaterialNames.MOLDAVITE, configuration.getBoolean("EnableMoldavite", MATERIALS_CAT, true, "Enable Moldavite Items and Materials"));
		Options.materialEnabled(MaterialNames.MOONSTONE, configuration.getBoolean("EnableMoonstone", MATERIALS_CAT, true, "Enable Moonstone Items and Materials"));
		Options.materialEnabled(MaterialNames.MORGANITE, configuration.getBoolean("EnableMorganite", MATERIALS_CAT, true, "Enable Morganite Items and Materials"));
		Options.materialEnabled(MaterialNames.ONYX, configuration.getBoolean("EnableOnyx", MATERIALS_CAT, true, "Enable Onyx Items and Materials"));
		Options.materialEnabled(MaterialNames.OPAL, configuration.getBoolean("EnableOpal", MATERIALS_CAT, true, "Enable Opal Items and Materials"));
		Options.materialEnabled(MaterialNames.PERIDOT, configuration.getBoolean("EnablePeridot", MATERIALS_CAT, true, "Enable Peridot Items and Materials"));
		Options.materialEnabled(MaterialNames.RUBY, configuration.getBoolean("EnableRuby", MATERIALS_CAT, true, "Enable Ruby Items and Materials"));
		Options.materialEnabled(MaterialNames.SAPPHIRE, configuration.getBoolean("EnableSapphire", MATERIALS_CAT, true, "Enable Sapphire Items and Materials"));
		Options.materialEnabled(MaterialNames.SPINEL, configuration.getBoolean("EnableSpinel", MATERIALS_CAT, true, "Enable Spinel Items and Materials"));
		Options.materialEnabled(MaterialNames.TANZANITE, configuration.getBoolean("EnableTanzanite", MATERIALS_CAT, true, "Enable Tanzanite Items and Materials"));
		Options.materialEnabled(MaterialNames.TOPAZ, configuration.getBoolean("EnableTopaz", MATERIALS_CAT, true, "Enable Topaz Items and Materials"));
		Options.materialEnabled(MaterialNames.TURQUOISE, configuration.getBoolean("EnableTurquoise", MATERIALS_CAT, true, "Enable Turquoise Items and Materials"));
		Options.materialEnabled(MaterialNames.VIOLETSAPPHIRE, configuration.getBoolean("EnableVioletsapphire", MATERIALS_CAT, true, "Enable Violetsapphire Items and Materials"));

		if (configuration.hasChanged()) {
			configuration.save();
		}
	}

	public static void postInit() {
		CrusherRecipeRegistry.getInstance().clearCache();
	}
}
