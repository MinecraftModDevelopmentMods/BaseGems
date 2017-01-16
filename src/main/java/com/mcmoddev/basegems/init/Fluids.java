package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;

import net.minecraftforge.fluids.*;

/**
 * This class initializes all fluids in Base Gems.
 *
 * @author DrCyano
 *
 */
public class Fluids extends cyano.basemetals.init.Fluids {

	public static Fluid fluidAgate = null;
	public static BlockFluidBase fluidBlockAgate = null;

	private static boolean initDone = false;

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		String materialName;

		if (Options.ENABLE_AGATE) {
			materialName = "agate";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_ALEXANDRITE) {
			materialName = "alexandrite";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_AMBER) {
			materialName = "amber";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_AMETHYST) {
			materialName = "amethyst";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_AMETRINE) {
			materialName = "ametrine";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_AQUAMARINE) {
			materialName = "aquamarine";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_BERYL) {
			materialName = "beryl";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_BLACKDIAMOND) {
			materialName = "blackdiamond";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_BLUETOPAZ) {
			materialName = "bluetopaz";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_CARNELIAN) {
			materialName = "carnelian";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_CITRINE) {
			materialName = "citrine";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_GARNET) {
			materialName = "garnet";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_GOLDENBERYL) {
			materialName = "goldenberyl";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_HELIODOR) {
			materialName = "heliodor";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_INDICOLITE) {
			materialName = "indicolite";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_IOLITE) {
			materialName = "iolite";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_JADE) {
			materialName = "jade";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_JASPER) {
			materialName = "jasper";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_LEPIDOLITE) {
			materialName = "lepidolite";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_MALACHITE) {
			materialName = "malachite";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_MOLDAVITE) {
			materialName = "moldovite";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_MOONSTONE) {
			materialName = "moonstone";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_MORGANITE) {
			materialName = "morganite";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_ONYX) {
			materialName = "onyx";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_OPAL) {
			materialName = "opal";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_PERIDOT) {
			materialName = "peridot";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_RUBY) {
			materialName = "ruby";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_SAPPHIRE) {
			materialName = "sapphire";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_SPINEL) {
			materialName = "spinel";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_TANZANITE) {
			materialName = "tanzanite";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_TOPAZ) {
			materialName = "topaz";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_TURQUOISE) {
			materialName = "turquoise";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}
		if (Options.ENABLE_VIOLETSAPPHIRE) {
			materialName = "violetsapphire";

			fluidAgate = addFluid(materialName, 2000, 10000, 330, 10);
			fluidBlockAgate = addFluidBlock(materialName);
		}

		initDone = true;
	}
}
