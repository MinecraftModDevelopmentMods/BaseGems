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

	protected Blocks() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Materials.init();
		ItemGroups.init();

		if (Options.enableAgate) {
			createBlocksFull(Materials.agate);
		}

		if (Options.enableAlexandrite) {
			createBlocksFull(Materials.alexandrite);
		}

		if (Options.enableAmber) {
			createBlocksFull(Materials.amber);
		}

		if (Options.enableAmethyst) {
			createBlocksFull(Materials.amethyst);
		}

		if (Options.enableAmetrine) {
			createBlocksFull(Materials.ametrine);
		}

		if (Options.enableAquamarine) {
			createBlocksFull(Materials.aquamarine);
		}

		if (Options.enableBeryl) {
			createBlocksFull(Materials.beryl);
		}

		if (Options.enableBlackDiamond) {
			createBlocksFull(Materials.blackdiamond);
		}

		if (Options.enableBlueTopaz) {
			createBlocksFull(Materials.bluetopaz);
		}

		if (Options.enableCarnelian) {
			createBlocksFull(Materials.carnelian);
		}

		if (Options.enableCitrine) {
			createBlocksFull(Materials.citrine);
		}

		if (Options.enableGarnet) {
			createBlocksFull(Materials.garnet);
		}

		if (Options.enableGoldenBeryl) {
			createBlocksFull(Materials.goldenberyl);
		}

		if (Options.enableHeliodor) {
			createBlocksFull(Materials.heliodor);
		}

		if (Options.enableIndicolite) {
			createBlocksFull(Materials.indicolite);
		}

		if (Options.enableIolite) {
			createBlocksFull(Materials.iolite);
		}

		if (Options.enableJade) {
			createBlocksFull(Materials.jade);
		}

		if (Options.enableJasper) {
			createBlocksFull(Materials.jasper);
		}

		if (Options.enableLepidolite) {
			createBlocksFull(Materials.lepidolite);
		}

		if (Options.enableMalachite) {
			createBlocksFull(Materials.malachite);
		}

		if (Options.enableMoldavite) {
			createBlocksFull(Materials.moldavite);
		}

		if (Options.enableMoonstone) {
			createBlocksFull(Materials.moonstone);
		}

		if (Options.enableMorganite) {
			createBlocksFull(Materials.morganite);
		}

		if (Options.enableOnyx) {
			createBlocksFull(Materials.onyx);
		}

		if (Options.enableOpal) {
			createBlocksFull(Materials.opal);
		}

		if (Options.enablePeridot) {
			createBlocksFull(Materials.peridot);
		}

		if (Options.enableRuby) {
			createBlocksFull(Materials.ruby);
		}

		if (Options.enableSapphire) {
			createBlocksFull(Materials.sapphire);
		}

		if (Options.enableSpinel) {
			createBlocksFull(Materials.spinel);
		}

		if (Options.enableTanzanite) {
			createBlocksFull(Materials.tanzanite);
		}

		if (Options.enableTopaz) {
			createBlocksFull(Materials.topaz);
		}

		if (Options.enableTurquoise) {
			createBlocksFull(Materials.turquoise);
		}

		if (Options.enableVioletSapphire) {
			createBlocksFull(Materials.violetsapphire);
		}

		initDone = true;
	}
}
