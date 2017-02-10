package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;

import com.mcmoddev.lib.material.MetalMaterial;

/**
 * This class initializes all of the materials in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Materials extends com.mcmoddev.lib.init.Materials {

	public static MetalMaterial agate;
	public static MetalMaterial alexandrite;
	public static MetalMaterial amber;
	public static MetalMaterial amethyst;
	public static MetalMaterial ametrine;
	public static MetalMaterial aquamarine;
	public static MetalMaterial beryl;
	public static MetalMaterial blackdiamond;
	public static MetalMaterial bluetopaz;
	public static MetalMaterial carnelian;
	public static MetalMaterial citrine;
	public static MetalMaterial garnet;
	public static MetalMaterial goldenberyl;
	public static MetalMaterial heliodor;
	public static MetalMaterial indicolite;
	public static MetalMaterial iolite;
	public static MetalMaterial jade;
	public static MetalMaterial jasper;
	public static MetalMaterial lepidolite;
	public static MetalMaterial malachite;
	public static MetalMaterial moldavite;
	public static MetalMaterial moonstone;
	public static MetalMaterial morganite;
	public static MetalMaterial onyx;
	public static MetalMaterial opal;
	public static MetalMaterial peridot;
	public static MetalMaterial ruby;
	public static MetalMaterial sapphire;
	public static MetalMaterial spinel;
	public static MetalMaterial tanzanite;
	public static MetalMaterial topaz;
	public static MetalMaterial turquoise;
	public static MetalMaterial violetsapphire;

	private static boolean initDone = false;

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		// Mod Materials
		if (Options.enableAgate) {
			agate = createMaterial("agate", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableAlexandrite) {
			alexandrite = createMaterial("alexandrite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableAmber) {
			amber = createMaterial("amber", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableAmethyst) {
			amethyst = createMaterial("amethyst", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableAmetrine) {
			ametrine = createMaterial("ametrine", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableAquamarine) {
			aquamarine = createMaterial("aquamarine", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableBeryl) {
			beryl = createMaterial("beryl", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableBlackDiamond) {
			blackdiamond = createMaterial("blackdiamond", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableBlueTopaz) {
			bluetopaz = createMaterial("bluetopaz", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableCarnelian) {
			carnelian = createMaterial("carnelian", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableCitrine) {
			citrine = createMaterial("citrine", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableGarnet) {
			garnet = createMaterial("garnet", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableGoldenBeryl) {
			goldenberyl = createMaterial("goldenberyl", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableHeliodor) {
			heliodor = createMaterial("heliodor", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableIndicolite) {
			indicolite = createMaterial("indicolite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableIolite) {
			iolite = createMaterial("iolite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableJade) {
			jade = createMaterial("jade", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableJasper) {
			jasper = createMaterial("jasper", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableLepidolite) {
			lepidolite = createMaterial("lepidolite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableMalachite) {
			malachite = createMaterial("malachite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableMoldavite) {
			moldavite = createMaterial("moldavite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableMoonstone) {
			moonstone = createMaterial("moonstone", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableMorganite) {
			morganite = createMaterial("morganite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableOnyx) {
			onyx = createMaterial("onyx", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableOpal) {
			opal = createMaterial("opal", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enablePeridot) {
			peridot = createMaterial("peridot", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableRuby) {
			ruby = createMaterial("ruby", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableSapphire) {
			sapphire = createMaterial("sapphire", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableSpinel) {
			spinel = createMaterial("spinel", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableTanzanite) {
			tanzanite = createMaterial("tanzanite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableTopaz) {
			topaz = createMaterial("topaz", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableTurquoise) {
			turquoise = createMaterial("turquoise", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.enableVioletSapphire) {
			violetsapphire = createMaterial("violetsapphire", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}

		initDone = true;
	}
}
