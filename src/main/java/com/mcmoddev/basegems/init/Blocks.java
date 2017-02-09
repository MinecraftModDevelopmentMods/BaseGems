package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;

/**
 * This class initializes all blocks in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Blocks extends com.mcmoddev.lib.init.Blocks {

	private static boolean initDone = false;

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Materials.init();
		ItemGroups.init();

		if (Options.ENABLE_AGATE) {
			createBlocksFull(Materials.agate);
		}

		if (Options.ENABLE_ALEXANDRITE) {
			createBlocksFull(Materials.alexandrite);
		}

		if (Options.ENABLE_AMBER) {
			createBlocksFull(Materials.amber);
		}

		if (Options.ENABLE_AMETHYST) {
			createBlocksFull(Materials.amethyst);
		}

		if (Options.ENABLE_AMETRINE) {
			createBlocksFull(Materials.ametrine);
		}

		if (Options.ENABLE_AQUAMARINE) {
			createBlocksFull(Materials.aquamarine);
		}

		if (Options.ENABLE_BERYL) {
			createBlocksFull(Materials.beryl);
		}

		if (Options.ENABLE_BLACKDIAMOND) {
			createBlocksFull(Materials.blackdiamond);
		}

		if (Options.ENABLE_BLUETOPAZ) {
			createBlocksFull(Materials.bluetopaz);
		}

		if (Options.ENABLE_CARNELIAN) {
			createBlocksFull(Materials.carnelian);
		}

		if (Options.ENABLE_CITRINE) {
			createBlocksFull(Materials.citrine);
		}

		if (Options.ENABLE_GARNET) {
			createBlocksFull(Materials.garnet);
		}

		if (Options.ENABLE_GOLDENBERYL) {
			createBlocksFull(Materials.goldenberyl);
		}

		if (Options.ENABLE_HELIODOR) {
			createBlocksFull(Materials.heliodor);
		}

		if (Options.ENABLE_INDICOLITE) {
			createBlocksFull(Materials.indicolite);
		}

		if (Options.ENABLE_IOLITE) {
			createBlocksFull(Materials.iolite);
		}

		if (Options.ENABLE_JADE) {
			createBlocksFull(Materials.jade);
		}

		if (Options.ENABLE_JASPER) {
			createBlocksFull(Materials.jasper);
		}

		if (Options.ENABLE_LEPIDOLITE) {
			createBlocksFull(Materials.lepidolite);
		}

		if (Options.ENABLE_MALACHITE) {
			createBlocksFull(Materials.malachite);
		}

		if (Options.ENABLE_MOLDAVITE) {
			createBlocksFull(Materials.moldavite);
		}

		if (Options.ENABLE_MOONSTONE) {
			createBlocksFull(Materials.moonstone);
		}

		if (Options.ENABLE_MORGANITE) {
			createBlocksFull(Materials.morganite);
		}

		if (Options.ENABLE_ONYX) {
			createBlocksFull(Materials.onyx);
		}

		if (Options.ENABLE_OPAL) {
			createBlocksFull(Materials.opal);
		}

		if (Options.ENABLE_PERIDOT) {
			createBlocksFull(Materials.peridot);
		}

		if (Options.ENABLE_RUBY) {
			createBlocksFull(Materials.ruby);
		}

		if (Options.ENABLE_SAPPHIRE) {
			createBlocksFull(Materials.sapphire);
		}

		if (Options.ENABLE_SPINEL) {
			createBlocksFull(Materials.spinel);
		}

		if (Options.ENABLE_TANZANITE) {
			createBlocksFull(Materials.tanzanite);
		}

		if (Options.ENABLE_TOPAZ) {
			createBlocksFull(Materials.topaz);
		}

		if (Options.ENABLE_TURQUOISE) {
			createBlocksFull(Materials.turquoise);
		}

		if (Options.ENABLE_VIOLETSAPPHIRE) {
			createBlocksFull(Materials.violetsapphire);
		}

		initDone = true;
	}
}
