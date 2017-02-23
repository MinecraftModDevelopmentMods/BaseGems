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

	/** Agate */
	public static MetalMaterial agate;
	/** Alexandrite */
	public static MetalMaterial alexandrite;
	/** Amber */
	public static MetalMaterial amber;
	/** Amethyst */
	public static MetalMaterial amethyst;
	/** Ametrine */
	public static MetalMaterial ametrine;
	/** Aquamarine */
	public static MetalMaterial aquamarine;
	/** Beryl */
	public static MetalMaterial beryl;
	/** Black Diamond */
	public static MetalMaterial blackdiamond;
	/** Blue Topaz */
	public static MetalMaterial bluetopaz;
	/** Carnelian */
	public static MetalMaterial carnelian;
	/** Citrine */
	public static MetalMaterial citrine;
	/** Garnet */
	public static MetalMaterial garnet;
	/** Golden Beryl */
	public static MetalMaterial goldenberyl;
	/** Heliodor */
	public static MetalMaterial heliodor;
	/** Indicolite */
	public static MetalMaterial indicolite;
	/** Iolite */
	public static MetalMaterial iolite;
	/** Jade */
	public static MetalMaterial jade;
	/** Jasper */
	public static MetalMaterial jasper;
	/** Lepidolite */
	public static MetalMaterial lepidolite;
	/** Malachite */
	public static MetalMaterial malachite;
	/** Moldavite */
	public static MetalMaterial moldavite;
	/** Moonstone */
	public static MetalMaterial moonstone;
	/**	Morganite */
	public static MetalMaterial morganite;
	/** Onyx */
	public static MetalMaterial onyx;
	/** Opal */
	public static MetalMaterial opal;
	/** Peridot */
	public static MetalMaterial peridot;
	/** Ruby */
	public static MetalMaterial ruby;
	/**	Sapphire */
	public static MetalMaterial sapphire;
	/**	Spinel */
	public static MetalMaterial spinel;
	/**	Tanzanite */
	public static MetalMaterial tanzanite;
	/**	Topaz */
	public static MetalMaterial topaz;
	/** Turquoise */
	public static MetalMaterial turquoise;
	/** Violet Sapphire */
	public static MetalMaterial violetsapphire;

	// vanilla imports
	/** Diamond */
	public static MetalMaterial vanilla_diamond;
	/** Emerald */
	public static MetalMaterial vanilla_emerald;
	/** Quartz */
	public static MetalMaterial vanilla_quartz;

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
