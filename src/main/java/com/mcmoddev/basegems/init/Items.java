package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;

/**
 * This class initializes all items in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Items extends com.mcmoddev.lib.init.Items {

	private static boolean initDone = false;

	protected Items() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Blocks.init();

		if (Options.enableAgate) {
			createItemsFull(Materials.agate);
		}

		if (Options.enableAlexandrite) {
			createItemsFull(Materials.alexandrite);
		}

		if (Options.enableAmber) {
			createItemsFull(Materials.amber);
		}

		if (Options.enableAmethyst) {
			createItemsFull(Materials.amethyst);
		}

		if (Options.enableAmetrine) {
			createItemsFull(Materials.ametrine);
		}

		if (Options.enableAquamarine) {
			createItemsFull(Materials.aquamarine);
		}

		if (Options.enableBeryl) {
			createItemsFull(Materials.beryl);
		}

		if (Options.enableBlackDiamond) {
			createItemsFull(Materials.blackdiamond);
		}

		if (Options.enableBlueTopaz) {
			createItemsFull(Materials.bluetopaz);
		}

		if (Options.enableCarnelian) {
			createItemsFull(Materials.carnelian);
		}

		if (Options.enableCitrine) {
			createItemsFull(Materials.citrine);
		}

		if (Options.enableGarnet) {
			createItemsFull(Materials.garnet);
		}

		if (Options.enableGoldenBeryl) {
			createItemsFull(Materials.goldenberyl);
		}

		if (Options.enableHeliodor) {
			createItemsFull(Materials.heliodor);
		}

		if (Options.enableIndicolite) {
			createItemsFull(Materials.indicolite);
		}

		if (Options.enableIolite) {
			createItemsFull(Materials.iolite);
		}

		if (Options.enableJade) {
			createItemsFull(Materials.jade);
		}

		if (Options.enableJasper) {
			createItemsFull(Materials.jasper);
		}

		if (Options.enableLepidolite) {
			createItemsFull(Materials.lepidolite);
		}

		if (Options.enableMalachite) {
			createItemsFull(Materials.malachite);
		}

		if (Options.enableMoldavite) {
			createItemsFull(Materials.moldavite);
		}

		if (Options.enableMoonstone) {
			createItemsFull(Materials.moonstone);
		}

		if (Options.enableMorganite) {
			createItemsFull(Materials.morganite);
		}

		if (Options.enableOnyx) {
			createItemsFull(Materials.onyx);
		}

		if (Options.enableOpal) {
			createItemsFull(Materials.opal);
		}

		if (Options.enablePeridot) {
			createItemsFull(Materials.peridot);
		}

		if (Options.enableRuby) {
			createItemsFull(Materials.ruby);
		}

		if (Options.enableSapphire) {
			createItemsFull(Materials.sapphire);
		}

		if (Options.enableSpinel) {
			createItemsFull(Materials.spinel);
		}

		if (Options.enableTanzanite) {
			createItemsFull(Materials.tanzanite);
		}

		if (Options.enableTopaz) {
			createItemsFull(Materials.topaz);
		}

		if (Options.enableTurquoise) {
			createItemsFull(Materials.turquoise);
		}

		if (Options.enableVioletSapphire) {
			createItemsFull(Materials.violetsapphire);
		}

		// addToMetList() // May not be needed, check

		initDone = true;
	}
}
