package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;

import com.mcmoddev.lib.material.MetalMaterial;

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

		MetalMaterial material;

		if (Options.enableAgate) {
			material = Materials.agate;
			createItemsFull(material);
		}

		if (Options.enableAlexandrite) {
			material = Materials.alexandrite;
			createItemsFull(material);
		}

		if (Options.enableAmber) {
			material = Materials.amber;
			createItemsFull(material);
		}

		if (Options.enableAmethyst) {
			material = Materials.amethyst;
			createItemsFull(material);
		}

		if (Options.enableAmetrine) {
			material = Materials.ametrine;
			createItemsFull(material);
		}

		if (Options.enableAquamarine) {
			material = Materials.aquamarine;
			createItemsFull(material);
		}

		if (Options.enableBeryl) {
			material = Materials.beryl;
			createItemsFull(material);
		}

		if (Options.enableBlackDiamond) {
			material = Materials.blackdiamond;
			createItemsFull(material);
		}

		if (Options.enableBlueTopaz) {
			material = Materials.bluetopaz;
			createItemsFull(material);
		}

		if (Options.enableCarnelian) {
			material = Materials.carnelian;
			createItemsFull(material);
		}

		if (Options.enableCitrine) {
			material = Materials.citrine;
			createItemsFull(material);
		}

		if (Options.enableGarnet) {
			material = Materials.garnet;
			createItemsFull(material);
		}

		if (Options.enableGoldenBeryl) {
			material = Materials.goldenberyl;
			createItemsFull(material);
		}

		if (Options.enableHeliodor) {
			material = Materials.heliodor;
			createItemsFull(material);
		}

		if (Options.enableIndicolite) {
			material = Materials.indicolite;
			createItemsFull(material);
		}

		if (Options.enableIolite) {
			material = Materials.iolite;
			createItemsFull(material);
		}

		if (Options.enableJade) {
			material = Materials.jade;
			createItemsFull(material);
		}

		if (Options.enableJasper) {
			material = Materials.jasper;
			createItemsFull(material);
		}

		if (Options.enableLepidolite) {
			material = Materials.lepidolite;
			createItemsFull(material);
		}

		if (Options.enableMalachite) {
			material = Materials.malachite;
			createItemsFull(material);
		}

		if (Options.enableMoldavite) {
			material = Materials.moldavite;
			createItemsFull(material);
		}

		if (Options.enableMoonstone) {
			material = Materials.moonstone;
			createItemsFull(material);
		}

		if (Options.enableMorganite) {
			material = Materials.morganite;
			createItemsFull(material);
		}

		if (Options.enableOnyx) {
			material = Materials.onyx;
			createItemsFull(material);
		}

		if (Options.enableOpal) {
			material = Materials.opal;
			createItemsFull(material);
		}

		if (Options.enablePeridot) {
			material = Materials.peridot;
			createItemsFull(material);
		}

		if (Options.enableRuby) {
			material = Materials.ruby;
			createItemsFull(material);
		}

		if (Options.enableSapphire) {
			material = Materials.sapphire;
			createItemsFull(material);
		}

		if (Options.enableSpinel) {
			material = Materials.spinel;
			createItemsFull(material);
		}

		if (Options.enableTanzanite) {
			material = Materials.tanzanite;
			createItemsFull(material);
		}

		if (Options.enableTopaz) {
			material = Materials.topaz;
			createItemsFull(material);
		}

		if (Options.enableTurquoise) {
			material = Materials.turquoise;
			createItemsFull(material);
		}

		if (Options.enableVioletSapphire) {
			material = Materials.violetsapphire;
			createItemsFull(material);
		}

//		addToMetList() // May not be needed, check

		initDone = true;
	}
}
