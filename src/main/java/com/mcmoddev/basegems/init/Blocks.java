package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;

import cyano.basemetals.material.MetalMaterial;

/**
 * This class initializes all blocks in Base Gems.
 *
 * @author DrCyano
 *
 */
public class Blocks extends cyano.basemetals.init.Blocks {

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

		MetalMaterial material;

		if (Options.ENABLE_AGATE) {

			material = Materials.agate;
			createBlocksFull(material);
		}
		if (Options.ENABLE_ALEXANDRITE) {
			material = Materials.alexandrite;
			createBlocksFull(material);
		}
		if (Options.ENABLE_AMBER) {
			material = Materials.amber;
			createBlocksFull(material);
		}
		if (Options.ENABLE_AMETHYST) {
			material = Materials.amethyst;
			createBlocksFull(material);
		}
		if (Options.ENABLE_AMETRINE) {
			material = Materials.ametrine;
			createBlocksFull(material);
		}
		if (Options.ENABLE_AQUAMARINE) {
			material = Materials.aquamarine;
			createBlocksFull(material);
		}
		if (Options.ENABLE_BERYL) {
			material = Materials.beryl;
			createBlocksFull(material);
		}
		if (Options.ENABLE_BLACKDIAMOND) {
			material = Materials.blackdiamond;
			createBlocksFull(material);
		}
		if (Options.ENABLE_BLUETOPAZ) {
			material = Materials.bluetopaz;
			createBlocksFull(material);
		}
		if (Options.ENABLE_CARNELIAN) {
			material = Materials.carnelian;
			createBlocksFull(material);
		}
		if (Options.ENABLE_CITRINE) {
			material = Materials.citrine;
			createBlocksFull(material);
		}
		if (Options.ENABLE_GARNET) {
			material = Materials.garnet;
			createBlocksFull(material);
		}
		if (Options.ENABLE_GOLDENBERYL) {
			material = Materials.goldenberyl;
			createBlocksFull(material);
		}
		if (Options.ENABLE_HELIODOR) {
			material = Materials.heliodor;
			createBlocksFull(material);
		}
		if (Options.ENABLE_INDICOLITE) {
			material = Materials.indicolite;
			createBlocksFull(material);
		}
		if (Options.ENABLE_IOLITE) {
			material = Materials.iolite;
			createBlocksFull(material);
		}
		if (Options.ENABLE_JADE) {
			material = Materials.jade;
			createBlocksFull(material);
		}
		if (Options.ENABLE_JASPER) {
			material = Materials.jasper;
			createBlocksFull(material);
		}
		if (Options.ENABLE_LEPIDOLITE) {
			material = Materials.lepidolite;
			createBlocksFull(material);
		}
		if (Options.ENABLE_MALACHITE) {
			material = Materials.malachite;
			createBlocksFull(material);
		}
		if (Options.ENABLE_MOLDAVITE) {
			material = Materials.moldavite;
			createBlocksFull(material);
		}
		if (Options.ENABLE_MOONSTONE) {
			material = Materials.moonstone;
			createBlocksFull(material);
		}
		if (Options.ENABLE_MORGANITE) {
			material = Materials.morganite;
			createBlocksFull(material);
		}
		if (Options.ENABLE_ONYX) {
			material = Materials.onyx;
			createBlocksFull(material);
		}
		if (Options.ENABLE_OPAL) {
			material = Materials.opal;
			createBlocksFull(material);
		}
		if (Options.ENABLE_PERIDOT) {
			material = Materials.peridot;
			createBlocksFull(material);
		}
		if (Options.ENABLE_RUBY) {
			material = Materials.ruby;
			createBlocksFull(material);
		}
		if (Options.ENABLE_SAPPHIRE) {
			material = Materials.sapphire;
			createBlocksFull(material);
		}
		if (Options.ENABLE_SPINEL) {
			material = Materials.spinel;
			createBlocksFull(material);
		}
		if (Options.ENABLE_TANZANITE) {
			material = Materials.tanzanite;
			createBlocksFull(material);
		}
		if (Options.ENABLE_TOPAZ) {
			material = Materials.topaz;
			createBlocksFull(material);
		}
		if (Options.ENABLE_TURQUOISE) {
			material = Materials.turquoise;
			createBlocksFull(material);
		}
		if (Options.ENABLE_VIOLETSAPPHIRE) {
			material = Materials.violetsapphire;
			createBlocksFull(material);
		}

		initDone = true;
	}
}
