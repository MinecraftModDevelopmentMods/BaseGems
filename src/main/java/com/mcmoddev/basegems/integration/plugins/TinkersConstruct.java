package com.mcmoddev.basegems.integration.plugins;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.init.Materials;
import com.mcmoddev.basegems.util.Config.Options;
import com.mcmoddev.lib.integration.MMDPlugin;
import com.mcmoddev.lib.integration.IIntegration;

/**
 *
 * @author Jasmine Iwanek
 *
 */
@MMDPlugin(addonId = BaseGems.MODID, pluginId = TinkersConstruct.PLUGIN_MODID)
public class TinkersConstruct extends com.mcmoddev.lib.integration.plugins.TinkersConstructBase implements IIntegration {

	private static boolean initDone = false;

	@Override
	public void init() {
		if (initDone || !com.mcmoddev.basemetals.util.Config.Options.enableTinkersConstruct) {
			return;
		}

		if (Options.enableAgate) {
			registerFluid(Materials.agate, 144);
		}

		if (Options.enableAlexandrite) {
			registerFluid(Materials.alexandrite, 144);
		}

		if (Options.enableAmber) {
			registerFluid(Materials.amber, 144);
		}

		if (Options.enableAmethyst) {
			registerFluid(Materials.amethyst, 144);
		}

		if (Options.enableAmetrine) {
			registerFluid(Materials.ametrine, 144);
		}

		if (Options.enableAquamarine) {
			registerFluid(Materials.aquamarine, 144);
		}

		if (Options.enableBeryl) {
			registerFluid(Materials.beryl, 144);
		}

		if (Options.enableBlackDiamond) {
			registerFluid(Materials.blackdiamond, 144);
		}

		if (Options.enableBlueTopaz) {
			registerFluid(Materials.bluetopaz, 144);
		}

		if (Options.enableCarnelian) {
			registerFluid(Materials.carnelian, 144);
		}

		if (Options.enableCitrine) {
			registerFluid(Materials.citrine, 144);
		}

		if (Options.enableGarnet) {
			registerFluid(Materials.garnet, 144);
		}

		if (Options.enableGoldenBeryl) {
			registerFluid(Materials.goldenberyl, 144);
		}

		if (Options.enableHeliodor) {
			registerFluid(Materials.heliodor, 144);
		}

		if (Options.enableIndicolite) {
			registerFluid(Materials.indicolite, 144);
		}

		if (Options.enableIolite) {
			registerFluid(Materials.iolite, 144);
		}

		if (Options.enableJade) {
			registerFluid(Materials.jade, 144);
		}

		if (Options.enableJasper) {
			registerFluid(Materials.jasper, 144);
		}

		if (Options.enableLepidolite) {
			registerFluid(Materials.lepidolite, 144);
		}

		if (Options.enableMalachite) {
			registerFluid(Materials.malachite, 144);
		}

		if (Options.enableMoldavite) {
			registerFluid(Materials.moldavite, 144);
		}

		if (Options.enableMoonstone) {
			registerFluid(Materials.moonstone, 144);
		}

		if (Options.enableMorganite) {
			registerFluid(Materials.morganite, 144);
		}

		if (Options.enableOnyx) {
			registerFluid(Materials.onyx, 144);
		}

		if (Options.enableOpal) {
			registerFluid(Materials.opal, 144);
		}

		if (Options.enablePeridot) {
			registerFluid(Materials.peridot, 144);
		}

		if (Options.enableRuby) {
			registerFluid(Materials.ruby, 144);
		}

		if (Options.enableSapphire) {
			registerFluid(Materials.sapphire, 144);
		}

		if (Options.enableSpinel) {
			registerFluid(Materials.spinel, 144);
		}

		if (Options.enableTanzanite) {
			registerFluid(Materials.tanzanite, 144);
		}
		
		if (Options.enableTopaz) {
			registerFluid(Materials.topaz, 144);
		}

		if (Options.enableTurquoise) {
			registerFluid(Materials.turquoise, 144);
		}

		if (Options.enableVioletSapphire) {
			registerFluid(Materials.violetsapphire, 144);
		}

		initDone = true;
	}
}
