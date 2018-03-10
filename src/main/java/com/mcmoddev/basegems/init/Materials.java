package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.material.MMDMaterial.MaterialType;

/**
 * This class initializes all of the materials in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Materials extends com.mcmoddev.lib.init.Materials {

	/** Agate */
	public static MMDMaterial agate;
	/** Alexandrite */
	public static MMDMaterial alexandrite;
	/** Amber */
	public static MMDMaterial amber;
	/** Amethyst */
	public static MMDMaterial amethyst;
	/** Ametrine */
	public static MMDMaterial ametrine;
	/** Aquamarine */
	public static MMDMaterial aquamarine;
	/** Beryl */
	public static MMDMaterial beryl;
	/** Black Diamond */
	public static MMDMaterial blackdiamond;
	/** Blue Topaz */
	public static MMDMaterial bluetopaz;
	/** Carnelian */
	public static MMDMaterial carnelian;
	/** Citrine */
	public static MMDMaterial citrine;
	/** Garnet */
	public static MMDMaterial garnet;
	/** Golden Beryl */
	public static MMDMaterial goldenberyl;
	/** Heliodor */
	public static MMDMaterial heliodor;
	/** Indicolite */
	public static MMDMaterial indicolite;
	/** Iolite */
	public static MMDMaterial iolite;
	/** Jade */
	public static MMDMaterial jade;
	/** Jasper */
	public static MMDMaterial jasper;
	/** Lepidolite */
	public static MMDMaterial lepidolite;
	/** Malachite */
	public static MMDMaterial malachite;
	/** Moldavite */
	public static MMDMaterial moldavite;
	/** Moonstone */
	public static MMDMaterial moonstone;
	/**	Morganite */
	public static MMDMaterial morganite;
	/** Onyx */
	public static MMDMaterial onyx;
	/** Opal */
	public static MMDMaterial opal;
	/** Peridot */
	public static MMDMaterial peridot;
	/** Ruby */
	public static MMDMaterial ruby;
	/**	Sapphire */
	public static MMDMaterial sapphire;
	/**	Spinel */
	public static MMDMaterial spinel;
	/**	Tanzanite */
	public static MMDMaterial tanzanite;
	/**	Topaz */
	public static MMDMaterial topaz;
	/** Turquoise */
	public static MMDMaterial turquoise;
	/** Violet Sapphire */
	public static MMDMaterial violetsapphire;

	// vanilla imports
	/** Diamond */
	public static MMDMaterial vanilla_diamond;
	/** Emerald */
	public static MMDMaterial vanilla_emerald;
	/** Quartz */
	public static MMDMaterial vanilla_quartz;

	private static boolean initDone = false;

	protected Materials() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		// Vanilla Materials
		vanilla_diamond = createMaterial("diamond", MaterialType.GEM, 10, 15, 4, 0xFF000000);

		if (Options.enableEmerald) {
			vanilla_emerald = createMaterial("emerald", MaterialType.GEM, 10, 15, 4, 0xFF000000);
		}

		if (Options.enableQuartz) {
			vanilla_quartz = createMaterial("quartz", MaterialType.GEM, 5, 4, 2, 0xFF000000);
		}

		// Mod Materials
		if (Options.enableAgate) {
			agate = createMaterial("agate", MaterialType.GEM, 6.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableAlexandrite) {
			alexandrite = createMaterial("alexandrite", MaterialType.GEM, 8.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableAmber) {
			amber = createMaterial("amber", MaterialType.GEM, 2, 8, 4.5, 0xFF000000);
		}

		if (Options.enableAmethyst) {
			amethyst = createMaterial("amethyst", MaterialType.GEM, 7, 8, 4.5, 0xFF000000);
		}

		if (Options.enableAmetrine) {
			ametrine = createMaterial("ametrine", MaterialType.GEM, 7, 8, 4.5, 0xFF000000);
		}

		if (Options.enableAquamarine) {
			aquamarine = createMaterial("aquamarine", MaterialType.GEM, 7.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableBeryl) {
			beryl = createMaterial("beryl", MaterialType.GEM, 7.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableBlackDiamond) {
			blackdiamond = createMaterial("blackdiamond", MaterialType.GEM, 10, 15, 4.5, 0xFF000000);
		}

		if (Options.enableBlueTopaz) {
			bluetopaz = createMaterial("bluetopaz", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}

		if (Options.enableCarnelian) {
			carnelian = createMaterial("carnelian", MaterialType.GEM, 6.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableCitrine) {
			citrine = createMaterial("citrine", MaterialType.GEM, 7, 8, 4.5, 0xFF000000);
		}

		if (Options.enableGarnet) {
			garnet = createMaterial("garnet", MaterialType.GEM, 6.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableGoldenBeryl) {
			goldenberyl = createMaterial("goldenberyl", MaterialType.GEM, 7.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableHeliodor) {
			heliodor = createMaterial("heliodor", MaterialType.GEM, 7.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableIndicolite) {
			indicolite = createMaterial("indicolite", MaterialType.GEM, 7, 8, 4.5, 0xFF000000);
		}

		if (Options.enableIolite) {
			iolite = createMaterial("iolite", MaterialType.GEM, 7, 8, 4.5, 0xFF000000);
		}

		if (Options.enableJade) {
			jade = createMaterial("jade", MaterialType.GEM, 6.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableJasper) {
			jasper = createMaterial("jasper", MaterialType.GEM, 7, 8, 4.5, 0xFF000000);
		}

		if (Options.enableLepidolite) {
			lepidolite = createMaterial("lepidolite", MaterialType.GEM, 3, 8, 4.5, 0xFF000000);
		}

		if (Options.enableMalachite) {
			malachite = createMaterial("malachite", MaterialType.GEM, 4, 8, 4.5, 0xFF000000);
		}

		if (Options.enableMoldavite) {
			moldavite = createMaterial("moldavite", MaterialType.GEM, 5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableMoonstone) {
			moonstone = createMaterial("moonstone", MaterialType.GEM, 6, 8, 4.5, 0xFF000000);
		}

		if (Options.enableMorganite) {
			morganite = createMaterial("morganite", MaterialType.GEM, 7.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableOnyx) {
			onyx = createMaterial("onyx", MaterialType.GEM, 7, 8, 4.5, 0xFF000000);
		}

		if (Options.enableOpal) {
			opal = createMaterial("opal", MaterialType.GEM, 6, 8, 4.5, 0xFF000000);
		}

		if (Options.enablePeridot) {
			peridot = createMaterial("peridot", MaterialType.GEM, 6.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableRuby) {
			ruby = createMaterial("ruby", MaterialType.GEM, 9, 8, 4.5, 0xFF000000);
		}

		if (Options.enableSapphire) {
			sapphire = createMaterial("sapphire", MaterialType.GEM, 9, 8, 4.5, 0xFF000000);
		}

		if (Options.enableSpinel) {
			spinel = createMaterial("spinel", MaterialType.GEM, 7.5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableTanzanite) {
			tanzanite = createMaterial("tanzanite", MaterialType.GEM, 6, 8, 4.5, 0xFF000000);
		}

		if (Options.enableTopaz) {
			topaz = createMaterial("topaz", MaterialType.GEM, 8, 8, 4.5, 0xFF000000);
		}

		if (Options.enableTurquoise) {
			turquoise = createMaterial("turquoise", MaterialType.GEM, 5, 8, 4.5, 0xFF000000);
		}

		if (Options.enableVioletSapphire) {
			violetsapphire = createMaterial("violetsapphire", MaterialType.GEM, 9, 8, 4.5, 0xFF000000);
		}

		initDone = true;
	}
}
