package com.mcmoddev.basegems.integration.plugins;

import com.mcmoddev.basegems.integration.BaseGemsPlugin;
import com.mcmoddev.basegems.util.Config.Options;

import com.mcmoddev.lib.integration.IIntegration;

import com.mcmoddev.basegems.init.Materials;

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
		if (initDone || !com.mcmoddev.basemetals.util.Config.Options.ENABLE_TINKERS_CONSTRUCT) {
			return;
		}

		if (Options.ENABLE_AGATE) {
			registerFluid(Materials.agate.fluid, false);
		}

		if (Options.ENABLE_ALEXANDRITE) {
			registerFluid(Materials.alexandrite.fluid, false);
		}

		if (Options.ENABLE_AMBER) {
			registerFluid(Materials.amber.fluid, false);
		}

		if (Options.ENABLE_AMETHYST) {
			registerFluid(Materials.amethyst.fluid, false);
		}

		if (Options.ENABLE_AMETRINE) {
			registerFluid(Materials.ametrine.fluid, false);
		}

		if (Options.ENABLE_AQUAMARINE) {
			registerFluid(Materials.aquamarine.fluid, false);
		}

		if (Options.ENABLE_BERYL) {
			registerFluid(Materials.beryl.fluid, false);
		}

		if (Options.ENABLE_BLACKDIAMOND) {
			registerFluid(Materials.blackdiamond.fluid, false);
		}

		if (Options.ENABLE_BLUETOPAZ) {
			registerFluid(Materials.bluetopaz.fluid, false);
		}

		if (Options.ENABLE_CARNELIAN) {
			registerFluid(Materials.carnelian.fluid, false);
		}

		if (Options.ENABLE_CITRINE) {
			registerFluid(Materials.citrine.fluid, false);
		}

		if (Options.ENABLE_GARNET) {
			registerFluid(Materials.garnet.fluid, false);
		}

		if (Options.ENABLE_GOLDENBERYL) {
			registerFluid(Materials.goldenberyl.fluid, false);
		}

		if (Options.ENABLE_HELIODOR) {
			registerFluid(Materials.heliodor.fluid, false);
		}

		if (Options.ENABLE_INDICOLITE) {
			registerFluid(Materials.indicolite.fluid, false);
		}

		if (Options.ENABLE_IOLITE) {
			registerFluid(Materials.iolite.fluid, false);
		}

		if (Options.ENABLE_JADE) {
			registerFluid(Materials.jade.fluid, false);
		}

		if (Options.ENABLE_JASPER) {
			registerFluid(Materials.jasper.fluid, false);
		}

		if (Options.ENABLE_LEPIDOLITE) {
			registerFluid(Materials.lepidolite.fluid, false);
		}

		if (Options.ENABLE_MALACHITE) {
			registerFluid(Materials.malachite.fluid, false);
		}

		if (Options.ENABLE_MOLDAVITE) {
			registerFluid(Materials.moldavite.fluid, false);
		}

		if (Options.ENABLE_MOONSTONE) {
			registerFluid(Materials.moonstone.fluid, false);
		}

		if (Options.ENABLE_MORGANITE) {
			registerFluid(Materials.morganite.fluid, false);
		}

		if (Options.ENABLE_ONYX) {
			registerFluid(Materials.onyx.fluid, false);
		}

		if (Options.ENABLE_OPAL) {
			registerFluid(Materials.opal.fluid, false);
		}

		if (Options.ENABLE_PERIDOT) {
			registerFluid(Materials.peridot.fluid, false);
		}

		if (Options.ENABLE_RUBY) {
			registerFluid(Materials.ruby.fluid, false);
		}

		if (Options.ENABLE_SAPPHIRE) {
			registerFluid(Materials.sapphire.fluid, false);
		}

		if (Options.ENABLE_SPINEL) {
			registerFluid(Materials.spinel.fluid, false);
		}

		if (Options.ENABLE_TANZANITE) {
			registerFluid(Materials.tanzanite.fluid, false);
		}
		
		if (Options.ENABLE_TOPAZ) {
			registerFluid(Materials.topaz.fluid, false);
		}

		if (Options.ENABLE_TURQUOISE) {
			registerFluid(Materials.turquoise.fluid, false);
		}

		if (Options.ENABLE_VIOLETSAPPHIRE) {
			registerFluid(Materials.violetsapphire.fluid, false);
		}

		initDone = true;
	}
}
