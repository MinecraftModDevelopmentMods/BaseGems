package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;
import com.mcmoddev.basemetals.init.ItemGroups;
import com.mcmoddev.lib.util.TabContainer;

/**
 * This class initializes all blocks in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Blocks extends com.mcmoddev.lib.init.Blocks {

	private static boolean initDone = false;
	private static TabContainer myTabs = new TabContainer( ItemGroups.blocksTab, ItemGroups.itemsTab, ItemGroups.toolsTab );

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
			createBlocksFull(Materials.agate, myTabs);
		}

		if (Options.enableAlexandrite) {
			createBlocksFull(Materials.alexandrite, myTabs);
		}

		if (Options.enableAmber) {
			createBlocksFull(Materials.amber, myTabs);
		}

		if (Options.enableAmethyst) {
			createBlocksFull(Materials.amethyst, myTabs);
		}

		if (Options.enableAmetrine) {
			createBlocksFull(Materials.ametrine, myTabs);
		}

		if (Options.enableAquamarine) {
			createBlocksFull(Materials.aquamarine, myTabs);
		}

		if (Options.enableBeryl) {
			createBlocksFull(Materials.beryl, myTabs);
		}

		if (Options.enableBlackDiamond) {
			createBlocksFull(Materials.blackdiamond, myTabs);
		}

		if (Options.enableBlueTopaz) {
			createBlocksFull(Materials.bluetopaz, myTabs);
		}

		if (Options.enableCarnelian) {
			createBlocksFull(Materials.carnelian, myTabs);
		}

		if (Options.enableCitrine) {
			createBlocksFull(Materials.citrine, myTabs);
		}

		if (Options.enableGarnet) {
			createBlocksFull(Materials.garnet, myTabs);
		}

		if (Options.enableGoldenBeryl) {
			createBlocksFull(Materials.goldenberyl, myTabs);
		}

		if (Options.enableHeliodor) {
			createBlocksFull(Materials.heliodor, myTabs);
		}

		if (Options.enableIndicolite) {
			createBlocksFull(Materials.indicolite, myTabs);
		}

		if (Options.enableIolite) {
			createBlocksFull(Materials.iolite, myTabs);
		}

		if (Options.enableJade) {
			createBlocksFull(Materials.jade, myTabs);
		}

		if (Options.enableJasper) {
			createBlocksFull(Materials.jasper, myTabs);
		}

		if (Options.enableLepidolite) {
			createBlocksFull(Materials.lepidolite, myTabs);
		}

		if (Options.enableMalachite) {
			createBlocksFull(Materials.malachite, myTabs);
		}

		if (Options.enableMoldavite) {
			createBlocksFull(Materials.moldavite, myTabs);
		}

		if (Options.enableMoonstone) {
			createBlocksFull(Materials.moonstone, myTabs);
		}

		if (Options.enableMorganite) {
			createBlocksFull(Materials.morganite, myTabs);
		}

		if (Options.enableOnyx) {
			createBlocksFull(Materials.onyx, myTabs);
		}

		if (Options.enableOpal) {
			createBlocksFull(Materials.opal, myTabs);
		}

		if (Options.enablePeridot) {
			createBlocksFull(Materials.peridot, myTabs);
		}

		if (Options.enableRuby) {
			createBlocksFull(Materials.ruby, myTabs);
		}

		if (Options.enableSapphire) {
			createBlocksFull(Materials.sapphire, myTabs);
		}

		if (Options.enableSpinel) {
			createBlocksFull(Materials.spinel, myTabs);
		}

		if (Options.enableTanzanite) {
			createBlocksFull(Materials.tanzanite, myTabs);
		}

		if (Options.enableTopaz) {
			createBlocksFull(Materials.topaz, myTabs);
		}

		if (Options.enableTurquoise) {
			createBlocksFull(Materials.turquoise, myTabs);
		}

		if (Options.enableVioletSapphire) {
			createBlocksFull(Materials.violetsapphire, myTabs);
		}

		initDone = true;
	}
}
