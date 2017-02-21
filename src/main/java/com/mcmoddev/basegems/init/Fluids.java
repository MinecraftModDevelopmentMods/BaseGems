package com.mcmoddev.basegems.init;

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

		if (Options.enableAgate) {
			addFluid(Materials.agate, 2000, 10000, 330, 10);
			addFluidBlock(Materials.agate);
		}

		if (Options.enableAlexandrite) {
			addFluid(Materials.alexandrite, 2000, 10000, 330, 10);
			addFluidBlock(Materials.alexandrite);
		}

		if (Options.enableAmber) {
			addFluid(Materials.amber, 2000, 10000, 330, 10);
			addFluidBlock(Materials.amber);
		}

		if (Options.enableAmethyst) {
			addFluid(Materials.amethyst, 2000, 10000, 330, 10);
			addFluidBlock(Materials.amethyst);
		}

		if (Options.enableAmetrine) {
			addFluid(Materials.ametrine, 2000, 10000, 330, 10);
			addFluidBlock(Materials.ametrine);
		}

		if (Options.enableAquamarine) {
			addFluid(Materials.aquamarine, 2000, 10000, 330, 10);
			addFluidBlock(Materials.aquamarine);
		}

		if (Options.enableBeryl) {
			addFluid(Materials.beryl, 2000, 10000, 330, 10);
			addFluidBlock(Materials.beryl);
		}

		if (Options.enableBlackDiamond) {
			addFluid(Materials.blackdiamond, 2000, 10000, 330, 10);
			addFluidBlock(Materials.blackdiamond);
		}

		if (Options.enableBlueTopaz) {
			addFluid(Materials.bluetopaz, 2000, 10000, 330, 10);
			addFluidBlock(Materials.bluetopaz);
		}

		if (Options.enableCarnelian) {
			addFluid(Materials.carnelian, 2000, 10000, 330, 10);
			addFluidBlock(Materials.carnelian);
		}

		if (Options.enableCitrine) {
			addFluid(Materials.citrine, 2000, 10000, 330, 10);
			addFluidBlock(Materials.citrine);
		}

		if (Options.enableGarnet) {
			addFluid(Materials.garnet, 2000, 10000, 330, 10);
			addFluidBlock(Materials.garnet);
		}

		if (Options.enableGoldenBeryl) {
			addFluid(Materials.goldenberyl, 2000, 10000, 330, 10);
			addFluidBlock(Materials.goldenberyl);
		}

		if (Options.enableHeliodor) {
			addFluid(Materials.heliodor, 2000, 10000, 330, 10);
			addFluidBlock(Materials.heliodor);
		}

		if (Options.enableIndicolite) {
			addFluid(Materials.indicolite, 2000, 10000, 330, 10);
			addFluidBlock(Materials.indicolite);
		}

		if (Options.enableIolite) {
			addFluid(Materials.iolite, 2000, 10000, 330, 10);
			addFluidBlock(Materials.iolite);
		}

		if (Options.enableJade) {
			addFluid(Materials.jade, 2000, 10000, 330, 10);
			addFluidBlock(Materials.jade);
		}

		if (Options.enableJasper) {
			addFluid(Materials.jasper, 2000, 10000, 330, 10);
			addFluidBlock(Materials.jasper);
		}

		if (Options.enableLepidolite) {
			addFluid(Materials.lepidolite, 2000, 10000, 330, 10);
			addFluidBlock(Materials.lepidolite);
		}

		if (Options.enableMalachite) {
			addFluid(Materials.malachite, 2000, 10000, 330, 10);
			addFluidBlock(Materials.malachite);
		}

		if (Options.enableMoldavite) {
			addFluid(Materials.moldavite, 2000, 10000, 330, 10);
			addFluidBlock(Materials.moldavite);
		}

		if (Options.enableMoonstone) {
			addFluid(Materials.moonstone, 2000, 10000, 330, 10);
			addFluidBlock(Materials.moonstone);
		}

		if (Options.enableMorganite) {
			addFluid(Materials.morganite, 2000, 10000, 330, 10);
			addFluidBlock(Materials.morganite);
		}

		if (Options.enableOnyx) {
			addFluid(Materials.onyx, 2000, 10000, 330, 10);
			addFluidBlock(Materials.onyx);
		}

		if (Options.enableOpal) {
			addFluid(Materials.opal, 2000, 10000, 330, 10);
			addFluidBlock(Materials.opal);
		}

		if (Options.enablePeridot) {
			addFluid(Materials.peridot, 2000, 10000, 330, 10);
			addFluidBlock(Materials.peridot);
		}

		if (Options.enableRuby) {
			addFluid(Materials.ruby, 2000, 10000, 330, 10);
			addFluidBlock(Materials.ruby);
		}

		if (Options.enableSapphire) {
			addFluid(Materials.sapphire, 2000, 10000, 330, 10);
			addFluidBlock(Materials.sapphire);
		}

		if (Options.enableSpinel) {
			addFluid(Materials.spinel, 2000, 10000, 330, 10);
			addFluidBlock(Materials.spinel);
		}

		if (Options.enableTanzanite) {
			addFluid(Materials.tanzanite, 2000, 10000, 330, 10);
			addFluidBlock(Materials.tanzanite);
		}

		if (Options.enableTopaz) {
			addFluid(Materials.topaz, 2000, 10000, 330, 10);
			addFluidBlock(Materials.topaz);
		}

		if (Options.enableTurquoise) {
			addFluid(Materials.turquoise, 2000, 10000, 330, 10);
			addFluidBlock(Materials.turquoise);
		}

		if (Options.enableVioletSapphire) {
			addFluid(Materials.violetsapphire, 2000, 10000, 330, 10);
			addFluidBlock(Materials.violetsapphire);
		}

		initDone = true;
	}
}
