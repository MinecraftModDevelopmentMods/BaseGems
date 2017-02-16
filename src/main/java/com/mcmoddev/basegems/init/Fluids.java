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
		if (Options.enableAgate) {
			material = Materials.agate;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableAlexandrite) {
			material = Materials.alexandrite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableAmber) {
			material = Materials.amber;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableAmethyst) {
			material = Materials.amethyst;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableAmetrine) {
			material = Materials.ametrine;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableAquamarine) {
			material = Materials.aquamarine;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableBeryl) {
			material = Materials.beryl;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableBlackDiamond) {
			material = Materials.blackdiamond;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableBlueTopaz) {
			material = Materials.bluetopaz;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableCarnelian) {
			material = Materials.carnelian;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableCitrine) {
			material = Materials.citrine;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableGarnet) {
			material = Materials.garnet;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableGoldenBeryl) {
			material = Materials.goldenberyl;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableHeliodor) {
			material = Materials.heliodor;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableIndicolite) {
			material = Materials.indicolite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableIolite) {
			material = Materials.iolite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableJade) {
			material = Materials.jade;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableJasper) {
			material = Materials.jasper;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableLepidolite) {
			material = Materials.lepidolite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableMalachite) {
			material = Materials.malachite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableMoldavite) {
			material = Materials.moldavite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableMoonstone) {
			material = Materials.moonstone;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableMorganite) {
			material = Materials.morganite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableOnyx) {
			material = Materials.onyx;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableOpal) {
			material = Materials.opal;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enablePeridot) {
			material = Materials.peridot;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableRuby) {
			material = Materials.ruby;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableSapphire) {
			material = Materials.sapphire;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableSpinel) {
			material = Materials.spinel;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableTanzanite) {
			material = Materials.tanzanite;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableTopaz) {
			material = Materials.topaz;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableTurquoise) {
			material = Materials.turquoise;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
		if (Options.enableVioletSapphire) {
			material = Materials.violetsapphire;

			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}

		initDone = true;
	}
}
