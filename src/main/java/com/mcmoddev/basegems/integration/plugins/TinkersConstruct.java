package com.mcmoddev.basegems.integration.plugins;

import com.mcmoddev.basegems.integration.BaseGemsPlugin;
import com.mcmoddev.basegems.util.Config.Options;
import com.mcmoddev.basegems.init.Materials;


import com.mcmoddev.lib.integration.IIntegration;

/**
 *
 * @author Jasmine Iwanek
 *
 */
@BaseGemsPlugin(TinkersConstruct.PLUGIN_MODID)
public class TinkersConstruct extends com.mcmoddev.lib.integration.plugins.TinkersConstruct implements IIntegration {

	private static boolean initDone = false;

	@Override
	public void init() {
		if (initDone || !com.mcmoddev.basemetals.util.Config.Options.enableTinkersConstruct) {
			return;
		}

		if (Options.ENABLE_AGATE) {
			registerFluid(Materials.agate, 144);
		}

		if (Options.ENABLE_ALEXANDRITE) {
			registerFluid(Materials.alexandrite, 144);
		}

		if (Options.ENABLE_AMBER) {
			registerFluid(Materials.amber, 144);
		}

		if (Options.ENABLE_AMETHYST) {
			registerFluid(Materials.amethyst, 144);
		}

		if (Options.ENABLE_AMETRINE) {
			registerFluid(Materials.ametrine, 144);
		}

		if (Options.ENABLE_AQUAMARINE) {
			registerFluid(Materials.aquamarine, 144);
		}

		if (Options.ENABLE_BERYL) {
			registerFluid(Materials.beryl, 144);
		}

		if (Options.ENABLE_BLACKDIAMOND) {
			registerFluid(Materials.blackdiamond, 144);
		}

		if (Options.ENABLE_BLUETOPAZ) {
			registerFluid(Materials.bluetopaz, 144);
		}

		if (Options.ENABLE_CARNELIAN) {
			registerFluid(Materials.carnelian, 144);
		}

		if (Options.ENABLE_CITRINE) {
			registerFluid(Materials.citrine, 144);
		}

		if (Options.ENABLE_GARNET) {
			registerFluid(Materials.garnet, 144);
		}

		if (Options.ENABLE_GOLDENBERYL) {
			registerFluid(Materials.goldenberyl, 144);
		}

		if (Options.ENABLE_HELIODOR) {
			registerFluid(Materials.heliodor, 144);
		}

		if (Options.ENABLE_INDICOLITE) {
			registerFluid(Materials.indicolite, 144);
		}

		if (Options.ENABLE_IOLITE) {
			registerFluid(Materials.iolite, 144);
		}

		if (Options.ENABLE_JADE) {
			registerFluid(Materials.jade, 144);
		}

		if (Options.ENABLE_JASPER) {
			registerFluid(Materials.jasper, 144);
		}

		if (Options.ENABLE_LEPIDOLITE) {
			registerFluid(Materials.lepidolite, 144);
		}

		if (Options.ENABLE_MALACHITE) {
			registerFluid(Materials.malachite, 144);
		}

		if (Options.ENABLE_MOLDAVITE) {
			registerFluid(Materials.moldavite, 144);
		}

		if (Options.ENABLE_MOONSTONE) {
			registerFluid(Materials.moonstone, 144);
		}

		if (Options.ENABLE_MORGANITE) {
			registerFluid(Materials.morganite, 144);
		}

		if (Options.ENABLE_ONYX) {
			registerFluid(Materials.onyx, 144);
		}

		if (Options.ENABLE_OPAL) {
			registerFluid(Materials.opal, 144);
		}

		if (Options.ENABLE_PERIDOT) {
			registerFluid(Materials.peridot, 144);
		}

		if (Options.ENABLE_RUBY) {
			registerFluid(Materials.ruby, 144);
		}

		if (Options.ENABLE_SAPPHIRE) {
			registerFluid(Materials.sapphire, 144);
		}

		if (Options.ENABLE_SPINEL) {
			registerFluid(Materials.spinel, 144);
		}

		if (Options.ENABLE_TANZANITE) {
			registerFluid(Materials.tanzanite, 144);
		}
		
		if (Options.ENABLE_TOPAZ) {
			registerFluid(Materials.topaz, 144);
		}

		if (Options.ENABLE_TURQUOISE) {
			registerFluid(Materials.turquoise, 144);
		}

		if (Options.ENABLE_VIOLETSAPPHIRE) {
			registerFluid(Materials.violetsapphire, 144);
		}

		initDone = true;
	}
}
