package com.mcmoddev.basegems.init;

import com.mcmoddev.lib.material.MetalMaterial;
import com.mcmoddev.basegems.util.Config.Options;

/**
 * This class initializes all fluids in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Fluids extends com.mcmoddev.lib.init.Fluids {

	private static boolean initDone = false;

	private Fluids() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		MetalMaterial material;

		// fluids and fluid blocks
		if (Options.ENABLE_AGATE) {
			material = Materials.agate;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_ALEXANDRITE) {
			material = Materials.alexandrite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_AMBER) {
			material = Materials.amber;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_AMETHYST) {
			material = Materials.amethyst;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_AMETRINE) {
			material = Materials.ametrine;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_AQUAMARINE) {
			material = Materials.aquamarine;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_BERYL) {
			material = Materials.beryl;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_BLACKDIAMOND) {
			material = Materials.blackdiamond;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_BLUETOPAZ) {
			material = Materials.bluetopaz;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_CARNELIAN) {
			material = Materials.carnelian;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_CITRINE) {
			material = Materials.citrine;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_GARNET) {
			material = Materials.garnet;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_GOLDENBERYL) {
			material = Materials.goldenberyl;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_HELIODOR) {
			material = Materials.heliodor;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_INDICOLITE) {
			material = Materials.indicolite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_IOLITE) {
			material = Materials.iolite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_JADE) {
			material = Materials.jade;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_JASPER) {
			material = Materials.jasper;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_LEPIDOLITE) {
			material = Materials.lepidolite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_MALACHITE) {
			material = Materials.malachite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_MOLDAVITE) {
			material = Materials.moldavite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_MOONSTONE) {
			material = Materials.moonstone;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_MORGANITE) {
			material = Materials.morganite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_ONYX) {
			material = Materials.onyx;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_OPAL) {
			material = Materials.opal;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_PERIDOT) {
			material = Materials.peridot;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_RUBY) {
			material = Materials.ruby;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_SAPPHIRE) {
			material = Materials.sapphire;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_SPINEL) {
			material = Materials.spinel;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_TANZANITE) {
			material = Materials.tanzanite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_TOPAZ) {
			material = Materials.topaz;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_TURQUOISE) {
			material = Materials.turquoise;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.ENABLE_VIOLETSAPPHIRE) {
			material = Materials.violetsapphire;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}

		initDone = true;
	}
}
