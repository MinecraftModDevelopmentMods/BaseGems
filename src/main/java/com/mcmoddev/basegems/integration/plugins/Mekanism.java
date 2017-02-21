package com.mcmoddev.basegems.integration.plugins;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.init.Materials;
import com.mcmoddev.basegems.util.Config.Options;
import com.mcmoddev.lib.integration.MMDPlugin;
import com.mcmoddev.lib.integration.IIntegration;

@MMDPlugin(addonId = BaseGems.MODID, pluginId = Mekanism.PLUGIN_MODID)
public class Mekanism extends com.mcmoddev.lib.integration.plugins.Mekanism implements IIntegration {

	private static boolean initDone = false;

	@Override
	public void init() {
		if (initDone || !com.mcmoddev.basemetals.util.Config.Options.enableMekanism) {
			return;
		}

		if (Options.enableAgate) {
			addOreMultiplicationRecipes(Materials.agate);
		}

		if (Options.enableAlexandrite) {
			addOreMultiplicationRecipes(Materials.alexandrite);
		}

		if (Options.enableAmber) {
			addOreMultiplicationRecipes(Materials.amber);
		}

		if (Options.enableAmethyst) {
			addOreMultiplicationRecipes(Materials.amethyst);
		}

		if (Options.enableAmetrine) {
			addOreMultiplicationRecipes(Materials.ametrine);
		}

		if (Options.enableAquamarine) {
			addOreMultiplicationRecipes(Materials.aquamarine);
		}

		if (Options.enableBeryl) {
			addOreMultiplicationRecipes(Materials.beryl);
		}

		if (Options.enableBlackDiamond) {
			addOreMultiplicationRecipes(Materials.blackdiamond);
		}

		if (Options.enableBlueTopaz) {
			addOreMultiplicationRecipes(Materials.bluetopaz);
		}

		if (Options.enableCarnelian) {
			addOreMultiplicationRecipes(Materials.carnelian);
		}

		if (Options.enableCitrine) {
			addOreMultiplicationRecipes(Materials.citrine);
		}

		if (Options.enableGarnet) {
			addOreMultiplicationRecipes(Materials.garnet);
		}

		if (Options.enableGoldenBeryl) {
			addOreMultiplicationRecipes(Materials.goldenberyl);
		}

		if (Options.enableHeliodor) {
			addOreMultiplicationRecipes(Materials.heliodor);
		}

		if (Options.enableIndicolite) {
			addOreMultiplicationRecipes(Materials.indicolite);
		}

		if (Options.enableIolite) {
			addOreMultiplicationRecipes(Materials.iolite);
		}

		if (Options.enableJade) {
			addOreMultiplicationRecipes(Materials.jade);
		}

		if (Options.enableJasper) {
			addOreMultiplicationRecipes(Materials.jasper);
		}

		if (Options.enableLepidolite) {
			addOreMultiplicationRecipes(Materials.lepidolite);
		}

		if (Options.enableMalachite) {
			addOreMultiplicationRecipes(Materials.malachite);
		}

		if (Options.enableMoldavite) {
			addOreMultiplicationRecipes(Materials.moldavite);
		}

		if (Options.enableMoonstone) {
			addOreMultiplicationRecipes(Materials.moonstone);
		}

		if (Options.enableMorganite) {
			addOreMultiplicationRecipes(Materials.morganite);
		}

		if (Options.enableOnyx) {
			addOreMultiplicationRecipes(Materials.onyx);
		}

		if (Options.enableOpal) {
			addOreMultiplicationRecipes(Materials.opal);
		}

		if (Options.enablePeridot) {
			addOreMultiplicationRecipes(Materials.peridot);
		}

		if (Options.enableRuby) {
			addOreMultiplicationRecipes(Materials.ruby);
		}

		if (Options.enableSapphire) {
			addOreMultiplicationRecipes(Materials.sapphire);
		}

		if (Options.enableSpinel) {
			addOreMultiplicationRecipes(Materials.spinel);
		}

		if (Options.enableTanzanite) {
			addOreMultiplicationRecipes(Materials.tanzanite);
		}
		
		if (Options.enableTopaz) {
			addOreMultiplicationRecipes(Materials.topaz);
		}

		if (Options.enableTurquoise) {
			addOreMultiplicationRecipes(Materials.turquoise);
		}

		if (Options.enableVioletSapphire) {
			addOreMultiplicationRecipes(Materials.violetsapphire);
		}

		initDone = true;
	}
}
