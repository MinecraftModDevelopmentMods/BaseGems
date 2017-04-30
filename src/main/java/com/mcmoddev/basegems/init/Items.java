package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;
import com.mcmoddev.basemetals.init.ItemGroups;
import com.mcmoddev.lib.util.TabContainer;

/**
 * This class initializes all items in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Items extends com.mcmoddev.lib.init.Items {

	private static boolean initDone = false;
	private static TabContainer myTabs = new TabContainer( ItemGroups.blocksTab, ItemGroups.itemsTab, ItemGroups.toolsTab );

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
			createItemsFull(Materials.agate, myTabs);
		}

		if (Options.enableAlexandrite) {
			createItemsFull(Materials.alexandrite, myTabs);
		}

		if (Options.enableAmber) {
			createItemsFull(Materials.amber, myTabs);
		}

		if (Options.enableAmethyst) {
			createItemsFull(Materials.amethyst, myTabs);
		}

		if (Options.enableAmetrine) {
			createItemsFull(Materials.ametrine, myTabs);
		}

		if (Options.enableAquamarine) {
			createItemsFull(Materials.aquamarine, myTabs);
		}

		if (Options.enableBeryl) {
			createItemsFull(Materials.beryl, myTabs);
		}

		if (Options.enableBlackDiamond) {
			createItemsFull(Materials.blackdiamond, myTabs);
		}

		if (Options.enableBlueTopaz) {
			createItemsFull(Materials.bluetopaz, myTabs);
		}

		if (Options.enableCarnelian) {
			createItemsFull(Materials.carnelian, myTabs);
		}

		if (Options.enableCitrine) {
			createItemsFull(Materials.citrine, myTabs);
		}

		if (Options.enableGarnet) {
			createItemsFull(Materials.garnet, myTabs);
		}

		if (Options.enableGoldenBeryl) {
			createItemsFull(Materials.goldenberyl, myTabs);
		}

		if (Options.enableHeliodor) {
			createItemsFull(Materials.heliodor, myTabs);
		}

		if (Options.enableIndicolite) {
			createItemsFull(Materials.indicolite, myTabs);
		}

		if (Options.enableIolite) {
			createItemsFull(Materials.iolite, myTabs);
		}

		if (Options.enableJade) {
			createItemsFull(Materials.jade, myTabs);
		}

		if (Options.enableJasper) {
			createItemsFull(Materials.jasper, myTabs);
		}

		if (Options.enableLepidolite) {
			createItemsFull(Materials.lepidolite, myTabs);
		}

		if (Options.enableMalachite) {
			createItemsFull(Materials.malachite, myTabs);
		}

		if (Options.enableMoldavite) {
			createItemsFull(Materials.moldavite, myTabs);
		}

		if (Options.enableMoonstone) {
			createItemsFull(Materials.moonstone, myTabs);
		}

		if (Options.enableMorganite) {
			createItemsFull(Materials.morganite, myTabs);
		}

		if (Options.enableOnyx) {
			createItemsFull(Materials.onyx, myTabs);
		}

		if (Options.enableOpal) {
			createItemsFull(Materials.opal, myTabs);
		}

		if (Options.enablePeridot) {
			createItemsFull(Materials.peridot, myTabs);
		}

		if (Options.enableRuby) {
			createItemsFull(Materials.ruby, myTabs);
		}

		if (Options.enableSapphire) {
			createItemsFull(Materials.sapphire, myTabs);
		}

		if (Options.enableSpinel) {
			createItemsFull(Materials.spinel, myTabs);
		}

		if (Options.enableTanzanite) {
			createItemsFull(Materials.tanzanite, myTabs);
		}

		if (Options.enableTopaz) {
			createItemsFull(Materials.topaz, myTabs);
		}

		if (Options.enableTurquoise) {
			createItemsFull(Materials.turquoise, myTabs);
		}

		if (Options.enableVioletSapphire) {
			createItemsFull(Materials.violetsapphire, myTabs);
		}

		// addToMetList() // May not be needed, check

		initDone = true;
	}
}
