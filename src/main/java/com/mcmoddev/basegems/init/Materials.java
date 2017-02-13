package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;

import com.mcmoddev.lib.material.MetalMaterial;
import com.mcmoddev.lib.material.MetalMaterial.MaterialType;

/**
 * This class initializes all of the materials in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Materials extends com.mcmoddev.lib.init.Materials {

	/**	 */
	public static MetalMaterial agate;
	/**	 */
	public static MetalMaterial alexandrite;
	/**	 */
	public static MetalMaterial amber;
	/**	 */
	public static MetalMaterial amethyst;
	/**	 */
	public static MetalMaterial ametrine;
	/**	 */
	public static MetalMaterial aquamarine;
	/**	 */
	public static MetalMaterial beryl;
	/**	 */
	public static MetalMaterial blackdiamond;
	/**	 */
	public static MetalMaterial bluetopaz;
	/**	 */
	public static MetalMaterial carnelian;
	/**	 */
	public static MetalMaterial citrine;
	/**	 */
	public static MetalMaterial garnet;
	/**	 */
	public static MetalMaterial goldenberyl;
	/**	 */
	public static MetalMaterial heliodor;
	/**	 */
	public static MetalMaterial indicolite;
	/**	 */
	public static MetalMaterial iolite;
	/**	 */
	public static MetalMaterial jade;
	/**	 */
	public static MetalMaterial jasper;
	/**	 */
	public static MetalMaterial lepidolite;
	/**	 */
	public static MetalMaterial malachite;
	/**	 */
	public static MetalMaterial moldavite;
	/**	 */
	public static MetalMaterial moonstone;
	/**	 */
	public static MetalMaterial morganite;
	/**	 */
	public static MetalMaterial onyx;
	/**	 */
	public static MetalMaterial opal;
	/**	 */
	public static MetalMaterial peridot;
	/**	 */
	public static MetalMaterial ruby;
	/**	 */
	public static MetalMaterial sapphire;
	/**	 */
	public static MetalMaterial spinel;
	/**	 */
	public static MetalMaterial tanzanite;
	/**	 */
	public static MetalMaterial topaz;
	/**	 */
	public static MetalMaterial turquoise;
	/**	 */
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
			agate = createMaterial("agate", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableAlexandrite) {
			alexandrite = createMaterial("alexandrite", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableAmber) {
			amber = createMaterial("amber", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableAmethyst) {
			amethyst = createMaterial("amethyst", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableAmetrine) {
			ametrine = createMaterial("ametrine", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableAquamarine) {
			aquamarine = createMaterial("aquamarine", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableBeryl) {
			beryl = createMaterial("beryl", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableBlackDiamond) {
			blackdiamond = createMaterial("blackdiamond", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableBlueTopaz) {
			bluetopaz = createMaterial("bluetopaz", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableCarnelian) {
			carnelian = createMaterial("carnelian", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableCitrine) {
			citrine = createMaterial("citrine", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableGarnet) {
			garnet = createMaterial("garnet", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableGoldenBeryl) {
			goldenberyl = createMaterial("goldenberyl", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableHeliodor) {
			heliodor = createMaterial("heliodor", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableIndicolite) {
			indicolite = createMaterial("indicolite", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableIolite) {
			iolite = createMaterial("iolite", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableJade) {
			jade = createMaterial("jade", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableJasper) {
			jasper = createMaterial("jasper", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableLepidolite) {
			lepidolite = createMaterial("lepidolite", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableMalachite) {
			malachite = createMaterial("malachite", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableMoldavite) {
			moldavite = createMaterial("moldavite", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableMoonstone) {
			moonstone = createMaterial("moonstone", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableMorganite) {
			morganite = createMaterial("morganite", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableOnyx) {
			onyx = createMaterial("onyx", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableOpal) {
			opal = createMaterial("opal", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enablePeridot) {
			peridot = createMaterial("peridot", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableRuby) {
			ruby = createMaterial("ruby", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableSapphire) {
			sapphire = createMaterial("sapphire", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableSpinel) {
			spinel = createMaterial("spinel", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableTanzanite) {
			tanzanite = createMaterial("tanzanite", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableTopaz) {
			topaz = createMaterial("topaz", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableTurquoise) {
			turquoise = createMaterial("turquoise", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}
		if (Options.enableVioletSapphire) {
			violetsapphire = createMaterial("violetsapphire", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}

		initDone = true;
	}
}
