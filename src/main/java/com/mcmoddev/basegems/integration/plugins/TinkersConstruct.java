package com.mcmoddev.basegems.integration.plugins;

import com.mcmoddev.basegems.util.Config.Options;

import com.mcmoddev.basemetals.integration.BaseMetalsPlugin;
import com.mcmoddev.basemetals.integration.IIntegration;

/**
 *
 * @author Jasmine Iwanek
 *
 */
@BaseMetalsPlugin(TinkersConstruct.PLUGIN_MODID)
public class TinkersConstruct extends com.mcmoddev.basemetals.integration.plugins.TinkersConstruct implements IIntegration {

	private static boolean initDone = false;

	@Override
	public void init() {
		if (initDone || !com.mcmoddev.basemetals.util.Config.Options.ENABLE_TINKERS_CONSTRUCT) {
			return;
		}

		if (Options.ENABLE_AGATE) {
			//registerMaterial("agate", false, true);
			//registerFluid("agate");
		}
		if (Options.ENABLE_ALEXANDRITE) {
		}
		if (Options.ENABLE_AMBER) {
		}
		if (Options.ENABLE_AMETHYST) {
		}
		if (Options.ENABLE_AMETRINE) {
		}
		if (Options.ENABLE_AQUAMARINE) {
		}
		if (Options.ENABLE_BERYL) {
		}
		if (Options.ENABLE_BLACKDIAMOND) {
		}
		if (Options.ENABLE_BLUETOPAZ) {
		}
		if (Options.ENABLE_CARNELIAN) {
		}
		if (Options.ENABLE_CITRINE) {
		}
		if (Options.ENABLE_GARNET) {
		}
		if (Options.ENABLE_GOLDENBERYL) {
		}
		if (Options.ENABLE_HELIODOR) {
		}
		if (Options.ENABLE_INDICOLITE) {
		}
		if (Options.ENABLE_IOLITE) {
		}
		if (Options.ENABLE_JADE) {
		}
		if (Options.ENABLE_JASPER) {
		}
		if (Options.ENABLE_LEPIDOLITE) {
		}
		if (Options.ENABLE_MALACHITE) {
		}
		if (Options.ENABLE_MOLDAVITE) {
		}
		if (Options.ENABLE_MOONSTONE) {
		}
		if (Options.ENABLE_MORGANITE) {
		}
		if (Options.ENABLE_ONYX) {
		}
		if (Options.ENABLE_OPAL) {
		}
		if (Options.ENABLE_PERIDOT) {
		}
		if (Options.ENABLE_RUBY) {
		}
		if (Options.ENABLE_SAPPHIRE) {
		}
		if (Options.ENABLE_SPINEL) {
		}
		if (Options.ENABLE_TANZANITE) {
		}
		if (Options.ENABLE_TOPAZ) {
		}
		if (Options.ENABLE_TURQUOISE) {
		}
		if (Options.ENABLE_VIOLETSAPPHIRE) {
		}

		initDone = true;
	}
}
