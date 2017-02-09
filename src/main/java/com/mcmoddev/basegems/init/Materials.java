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
		if (Options.ENABLE_AGATE) {
			agate = createMaterial("agate", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_ALEXANDRITE) {
			alexandrite = createMaterial("alexandrite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_AMBER) {
			amber = createMaterial("amber", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_AMETHYST) {
			amethyst = createMaterial("amethyst", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_AMETRINE) {
			ametrine = createMaterial("ametrine", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_AQUAMARINE) {
			aquamarine = createMaterial("aquamarine", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_BERYL) {
			beryl = createMaterial("beryl", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_BLACKDIAMOND) {
			blackdiamond = createMaterial("blackdiamond", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_BLUETOPAZ) {
			bluetopaz = createMaterial("bluetopaz", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_CARNELIAN) {
			carnelian = createMaterial("carnelian", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_CITRINE) {
			citrine = createMaterial("citrine", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_GARNET) {
			garnet = createMaterial("garnet", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_GOLDENBERYL) {
			goldenberyl = createMaterial("goldenberyl", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_HELIODOR) {
			heliodor = createMaterial("heliodor", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_INDICOLITE) {
			indicolite = createMaterial("indicolite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_IOLITE) {
			iolite = createMaterial("iolite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_JADE) {
			jade = createMaterial("jade", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_JASPER) {
			jasper = createMaterial("jasper", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_LEPIDOLITE) {
			lepidolite = createMaterial("lepidolite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_MALACHITE) {
			malachite = createMaterial("malachite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_MOLDAVITE) {
			moldavite = createMaterial("moldavite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_MOONSTONE) {
			moonstone = createMaterial("moonstone", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_MORGANITE) {
			morganite = createMaterial("morganite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_ONYX) {
			onyx = createMaterial("onyx", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_OPAL) {
			opal = createMaterial("opal", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_PERIDOT) {
			peridot = createMaterial("peridot", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_RUBY) {
			ruby = createMaterial("ruby", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_SAPPHIRE) {
			sapphire = createMaterial("sapphire", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_SPINEL) {
			spinel = createMaterial("spinel", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_TANZANITE) {
			tanzanite = createMaterial("tanzanite", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_TOPAZ) {
			topaz = createMaterial("topaz", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_TURQUOISE) {
			turquoise = createMaterial("turquoise", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}
		if (Options.ENABLE_VIOLETSAPPHIRE) {
			violetsapphire = createMaterial("violetsapphire", 8, 8, 4.5, 0xFF000000);
			agate.materialType = MetalMaterial.MaterialType.GEM;
		}

		initDone = true;
	}
}
