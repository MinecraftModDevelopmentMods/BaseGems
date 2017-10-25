package com.mcmoddev.basegems.init;

import java.util.Arrays;
import java.util.List;

import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.lib.material.MMDMaterial.MaterialType;

/**
 * This class initializes all of the materials in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Materials extends com.mcmoddev.lib.init.Materials {

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

		List<String> materials = Arrays.asList(MaterialNames.AMBER, MaterialNames.ALEXANDRITE, MaterialNames.AGATE, MaterialNames.AMETRINE,
				MaterialNames.AMETHYST, MaterialNames.AQUAMARINE, MaterialNames.BERYL, MaterialNames.BLACKDIAMOND, MaterialNames.BLUETOPAZ,
				MaterialNames.CARNELION, MaterialNames.CITRINE, MaterialNames.GARNET, MaterialNames.GOLDENBERYL, MaterialNames.HELIODOR,
				MaterialNames.INDICOLITE, MaterialNames.IOLITE, MaterialNames.JADE, MaterialNames.JASPER, MaterialNames.LEPIDOLITE,
				MaterialNames.MALACHITE, MaterialNames.MOLDAVITE, MaterialNames.MOONSTONE, MaterialNames.MORGANITE, MaterialNames.ONYX,
				MaterialNames.OPAL, MaterialNames.PERIDOT, MaterialNames.RUBY, MaterialNames.SAPPHIRE, MaterialNames.SPINEL,
				MaterialNames.TANZANITE, MaterialNames.TOPAZ, MaterialNames.TURQUOISE, MaterialNames.VIOLETSAPPHIRE);
		
		materials.forEach( name -> createMaterial( name, MaterialType.GEM, 
				getHardness(name), getStrength(name), getMagic(name), getColor(name)));

		initDone = true;
	}
	
	private static double getStrength(String name) {
		switch(name) {
		case MaterialNames.AMBER:
		case MaterialNames.ALEXANDRITE:
		case MaterialNames.AGATE:
		case MaterialNames.AMETRINE:
		case MaterialNames.AMETHYST:
		case MaterialNames.AQUAMARINE:
		case MaterialNames.BERYL:
		case MaterialNames.BLACKDIAMOND:
		case MaterialNames.BLUETOPAZ:
		case MaterialNames.CARNELION:
		case MaterialNames.CITRINE:
		case MaterialNames.GARNET:
		case MaterialNames.GOLDENBERYL:
		case MaterialNames.HELIODOR:
		case MaterialNames.INDICOLITE:
		case MaterialNames.IOLITE:
		case MaterialNames.JADE:
		case MaterialNames.JASPER:
		case MaterialNames.LEPIDOLITE:
		case MaterialNames.MALACHITE:
		case MaterialNames.MOLDAVITE:
		case MaterialNames.MOONSTONE:
		case MaterialNames.MORGANITE:
		case MaterialNames.ONYX:
		case MaterialNames.OPAL:
		case MaterialNames.PERIDOT:
		case MaterialNames.RUBY:
		case MaterialNames.SAPPHIRE:
		case MaterialNames.SPINEL:
		case MaterialNames.TANZANITE:
		case MaterialNames.TOPAZ:
		case MaterialNames.TURQUOISE:
		case MaterialNames.VIOLETSAPPHIRE:
			return 8.0;
		default:
			return 0;
		}
	}

	private static double getMagic(String name) {
		switch(name) {
		case MaterialNames.AMBER:
		case MaterialNames.ALEXANDRITE:
		case MaterialNames.AGATE:
		case MaterialNames.AMETRINE:
		case MaterialNames.AMETHYST:
		case MaterialNames.AQUAMARINE:
		case MaterialNames.BERYL:
		case MaterialNames.BLACKDIAMOND:
		case MaterialNames.BLUETOPAZ:
		case MaterialNames.CARNELION:
		case MaterialNames.CITRINE:
		case MaterialNames.GARNET:
		case MaterialNames.GOLDENBERYL:
		case MaterialNames.HELIODOR:
		case MaterialNames.INDICOLITE:
		case MaterialNames.IOLITE:
		case MaterialNames.JADE:
		case MaterialNames.JASPER:
		case MaterialNames.LEPIDOLITE:
		case MaterialNames.MALACHITE:
		case MaterialNames.MOLDAVITE:
		case MaterialNames.MOONSTONE:
		case MaterialNames.MORGANITE:
		case MaterialNames.ONYX:
		case MaterialNames.OPAL:
		case MaterialNames.PERIDOT:
		case MaterialNames.RUBY:
		case MaterialNames.SAPPHIRE:
		case MaterialNames.SPINEL:
		case MaterialNames.TANZANITE:
		case MaterialNames.TOPAZ:
		case MaterialNames.TURQUOISE:
		case MaterialNames.VIOLETSAPPHIRE:
			return 4.5;
		default:
			return 0;
		}
	}

	private static double getHardness(String name) {
		switch(name) {
		case MaterialNames.AMBER:
		case MaterialNames.ALEXANDRITE:
		case MaterialNames.AGATE:
		case MaterialNames.AMETRINE:
		case MaterialNames.AMETHYST:
		case MaterialNames.AQUAMARINE:
		case MaterialNames.BERYL:
		case MaterialNames.BLACKDIAMOND:
		case MaterialNames.BLUETOPAZ:
		case MaterialNames.CARNELION:
		case MaterialNames.CITRINE:
		case MaterialNames.GARNET:
		case MaterialNames.GOLDENBERYL:
		case MaterialNames.HELIODOR:
		case MaterialNames.INDICOLITE:
		case MaterialNames.IOLITE:
		case MaterialNames.JADE:
		case MaterialNames.JASPER:
		case MaterialNames.LEPIDOLITE:
		case MaterialNames.MALACHITE:
		case MaterialNames.MOLDAVITE:
		case MaterialNames.MOONSTONE:
		case MaterialNames.MORGANITE:
		case MaterialNames.ONYX:
		case MaterialNames.OPAL:
		case MaterialNames.PERIDOT:
		case MaterialNames.RUBY:
		case MaterialNames.SAPPHIRE:
		case MaterialNames.SPINEL:
		case MaterialNames.TANZANITE:
		case MaterialNames.TOPAZ:
		case MaterialNames.TURQUOISE:
		case MaterialNames.VIOLETSAPPHIRE:
			return 8;
		default:
			return 0;
		}
	}

	private static int getColor(String name) {
		switch(name) {
		case MaterialNames.AMBER:
		case MaterialNames.ALEXANDRITE:
		case MaterialNames.AGATE:
		case MaterialNames.AMETRINE:
		case MaterialNames.AMETHYST:
		case MaterialNames.AQUAMARINE:
		case MaterialNames.BERYL:
		case MaterialNames.BLACKDIAMOND:
		case MaterialNames.BLUETOPAZ:
		case MaterialNames.CARNELION:
		case MaterialNames.CITRINE:
		case MaterialNames.GARNET:
		case MaterialNames.GOLDENBERYL:
		case MaterialNames.HELIODOR:
		case MaterialNames.INDICOLITE:
		case MaterialNames.IOLITE:
		case MaterialNames.JADE:
		case MaterialNames.JASPER:
		case MaterialNames.LEPIDOLITE:
		case MaterialNames.MALACHITE:
		case MaterialNames.MOLDAVITE:
		case MaterialNames.MOONSTONE:
		case MaterialNames.MORGANITE:
		case MaterialNames.ONYX:
		case MaterialNames.OPAL:
		case MaterialNames.PERIDOT:
		case MaterialNames.RUBY:
		case MaterialNames.SAPPHIRE:
		case MaterialNames.SPINEL:
		case MaterialNames.TANZANITE:
		case MaterialNames.TOPAZ:
		case MaterialNames.TURQUOISE:
		case MaterialNames.VIOLETSAPPHIRE:
			return 0xFF000000;
		default:
			return 0;
		}
	}
}
