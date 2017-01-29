package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;

import com.mcmoddev.basemetals.material.MetalMaterial;

/**
 * This class initializes all items in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Items extends com.mcmoddev.lib.init.Items {

	private static boolean initDone = false;

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Blocks.init();

		MetalMaterial material;

		if (Options.ENABLE_AGATE) {
			material = Materials.agate;
			createItemsFull(material);
		}

		if (Options.ENABLE_ALEXANDRITE) {
			material = Materials.alexandrite;
			createItemsFull(material);
		}

		if (Options.ENABLE_AMBER) {
			material = Materials.amber;
			createItemsFull(material);
		}

		if (Options.ENABLE_AMETHYST) {
			material = Materials.amethyst;
			createItemsFull(material);
		}

		if (Options.ENABLE_AMETRINE) {
			material = Materials.ametrine;
			createItemsFull(material);
		}

		if (Options.ENABLE_AQUAMARINE) {
			material = Materials.aquamarine;
			createItemsFull(material);
		}

		if (Options.ENABLE_BERYL) {
			material = Materials.beryl;
			createItemsFull(material);
		}

		if (Options.ENABLE_BLACKDIAMOND) {
			material = Materials.blackdiamond;
			createItemsFull(material);
		}

		if (Options.ENABLE_BLUETOPAZ) {
			material = Materials.bluetopaz;
			createItemsFull(material);
		}

		if (Options.ENABLE_CARNELIAN) {
			material = Materials.carnelian;
			createItemsFull(material);
		}

		if (Options.ENABLE_CITRINE) {
			material = Materials.citrine;
			createItemsFull(material);
		}

		if (Options.ENABLE_GARNET) {
			material = Materials.garnet;
			createItemsFull(material);
		}

		if (Options.ENABLE_GOLDENBERYL) {
			material = Materials.goldenberyl;
			createItemsFull(material);
		}

		if (Options.ENABLE_HELIODOR) {
			material = Materials.heliodor;
			createItemsFull(material);
		}

		if (Options.ENABLE_INDICOLITE) {
			material = Materials.indicolite;
			createItemsFull(material);
		}

		if (Options.ENABLE_IOLITE) {
			material = Materials.iolite;
			createItemsFull(material);
		}

		if (Options.ENABLE_JADE) {
			material = Materials.jade;
			createItemsFull(material);
		}

		if (Options.ENABLE_JASPER) {
			material = Materials.jasper;
			createItemsFull(material);
		}

		if (Options.ENABLE_LEPIDOLITE) {
			material = Materials.lepidolite;
			createItemsFull(material);
		}

		if (Options.ENABLE_MALACHITE) {
			material = Materials.malachite;
			createItemsFull(material);
		}

		if (Options.ENABLE_MOLDAVITE) {
			material = Materials.moldavite;
			createItemsFull(material);
		}

		if (Options.ENABLE_MOONSTONE) {
			material = Materials.moonstone;
			createItemsFull(material);
		}

		if (Options.ENABLE_MORGANITE) {
			material = Materials.morganite;
			createItemsFull(material);
		}

		if (Options.ENABLE_ONYX) {
			material = Materials.onyx;
			createItemsFull(material);
		}

		if (Options.ENABLE_OPAL) {
			material = Materials.opal;
			createItemsFull(material);
		}

		if (Options.ENABLE_PERIDOT) {
			material = Materials.peridot;
			createItemsFull(material);
		}

		if (Options.ENABLE_RUBY) {
			material = Materials.ruby;
			createItemsFull(material);
		}

		if (Options.ENABLE_SAPPHIRE) {
			material = Materials.sapphire;
			createItemsFull(material);
		}

		if (Options.ENABLE_SPINEL) {
			material = Materials.spinel;
			createItemsFull(material);
		}

		if (Options.ENABLE_TANZANITE) {
			material = Materials.tanzanite;
			createItemsFull(material);
		}

		if (Options.ENABLE_TOPAZ) {
			material = Materials.topaz;
			createItemsFull(material);
		}

		if (Options.ENABLE_TURQUOISE) {
			material = Materials.turquoise;
			createItemsFull(material);
		}

		if (Options.ENABLE_VIOLETSAPPHIRE) {
			material = Materials.violetsapphire;
			createItemsFull(material);
		}

//		addToMetList() // May not be needed, check

		initDone = true;
	}
}
