package com.mcmoddev.basegems.init;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.lib.data.SharedStrings;
import com.mcmoddev.lib.events.MMDLibRegisterMaterials;
import com.mcmoddev.lib.material.MMDMaterialType.MaterialType;
import com.mcmoddev.lib.util.Config.Options;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This class initializes all of the materials in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
@Mod.EventBusSubscriber(modid=BaseGems.MODID)
public class Materials extends com.mcmoddev.lib.init.Materials {
	private Materials() {
		throw new IllegalAccessError(SharedStrings.NOT_INSTANTIABLE);
	}

	/**
	 *
	 */
	@SubscribeEvent
	public static void init(final MMDLibRegisterMaterials event) {
		final List<String> materials = Arrays.asList(MaterialNames.AMBER, MaterialNames.ALEXANDRITE, MaterialNames.AGATE, MaterialNames.AMETRINE,
				MaterialNames.AMETHYST, MaterialNames.AQUAMARINE, MaterialNames.BERYL, MaterialNames.BLACKDIAMOND, MaterialNames.BLUETOPAZ,
				MaterialNames.CARNELIAN, MaterialNames.CITRINE, MaterialNames.GARNET, MaterialNames.GOLDENBERYL, MaterialNames.HELIODOR,
				MaterialNames.INDICOLITE, MaterialNames.IOLITE, MaterialNames.JADE, MaterialNames.JASPER, MaterialNames.LEPIDOLITE,
				MaterialNames.MALACHITE, MaterialNames.MOLDAVITE, MaterialNames.MOONSTONE, MaterialNames.MORGANITE, MaterialNames.ONYX,
				MaterialNames.OPAL, MaterialNames.PERIDOT, MaterialNames.RUBY, MaterialNames.SAPPHIRE, MaterialNames.SPINEL,
				MaterialNames.TANZANITE, MaterialNames.TOPAZ, MaterialNames.TURQUOISE, MaterialNames.VIOLETSAPPHIRE);

		materials.stream().filter(Options::isMaterialEnabled).forEach(name -> createMaterial(name, MaterialType.GEM,
				getHardness(name), getStrength(name), getMagic(name), getColor(name)));
	}

	private static int getColor(@Nonnull final String name) {
		switch (name) {
			case MaterialNames.AMBER:
				return 0xFFF9C923;
			case MaterialNames.ALEXANDRITE:
				return 0xFF747385;
			case MaterialNames.AGATE:
				return 0xFF179FD1;
			case MaterialNames.AMETRINE:
				return 0xFFC8A394;
			case MaterialNames.AMETHYST:
				return 0xFFA242E2;
			case MaterialNames.AQUAMARINE:
				return 0xFF78BACA;
			case MaterialNames.BERYL:
				return 0xFF4EB86A;
			case MaterialNames.BLACKDIAMOND:
				return 0xFF000000;
			case MaterialNames.BLUETOPAZ:
				return 0xFF01A4E9;
			case MaterialNames.CARNELIAN:
				return 0xFFF03905;
			case MaterialNames.CITRINE:
				return 0xFFFAD563;
			case MaterialNames.GARNET:
				return 0xFF9D0938;
			case MaterialNames.GOLDENBERYL:
				return 0xFFFDEE4C;
			case MaterialNames.HELIODOR:
				return 0xFFF9FD5D;
			case MaterialNames.INDICOLITE:
				return 0xFF7EFBF5;
			case MaterialNames.IOLITE:
				return 0xFF605DBD;
			case MaterialNames.JADE:
				return 0xFF008127;
			case MaterialNames.JASPER:
				return 0xFF9E0403;
			case MaterialNames.LEPIDOLITE:
				return 0xFFB980A0;
			case MaterialNames.MALACHITE:
				return 0xFF25674C;
			case MaterialNames.MOLDAVITE:
				return 0xFF68873B;
			case MaterialNames.MOONSTONE:
				return 0xFF9FAED7;
			case MaterialNames.MORGANITE:
				return 0xFFF4D8CA;
			case MaterialNames.ONYX:
				return 0xFF000000;
			case MaterialNames.OPAL:
				return 0xFFC3CCE9;
			case MaterialNames.PERIDOT:
				return 0xFFB9E64F;
			case MaterialNames.RUBY:
				return 0xFF7E0308;
			case MaterialNames.SAPPHIRE:
				return 0xFF1036D5;
			case MaterialNames.SPINEL:
				return 0xFFFE2B80;
			case MaterialNames.TANZANITE:
				return 0xFF4A4AE0;
			case MaterialNames.TOPAZ:
				return 0xFFF9E838;
			case MaterialNames.TURQUOISE:
				return 0xFF3FB2C4;
			case MaterialNames.VIOLETSAPPHIRE:
				return 0xFF8247B7;
			default:
				return 0xFF000000;
		}
	}

	private static double getMagic(@Nonnull final String name) {
		switch (name) {
			case MaterialNames.AMBER:
			case MaterialNames.ALEXANDRITE:
			case MaterialNames.AGATE:
			case MaterialNames.AMETRINE:
			case MaterialNames.AMETHYST:
			case MaterialNames.AQUAMARINE:
			case MaterialNames.BERYL:
			case MaterialNames.BLACKDIAMOND:
			case MaterialNames.BLUETOPAZ:
			case MaterialNames.CARNELIAN:
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
				return 4.5d;
			default:
				return 1.0d;
		}
	}

	private static double getStrength(@Nonnull final String name) {
		switch (name) {
			case MaterialNames.AMBER:
			case MaterialNames.ALEXANDRITE:
			case MaterialNames.AGATE:
			case MaterialNames.AMETRINE:	
			case MaterialNames.AMETHYST:
			case MaterialNames.AQUAMARINE:
			case MaterialNames.BERYL:
			case MaterialNames.BLUETOPAZ:
			case MaterialNames.CARNELIAN:
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
				return 8.0d;
			case MaterialNames.BLACKDIAMOND:
				return 15.0d;
			default:
				return 1.0d;
		}
	}

	private static double getHardness(@Nonnull final String name) {
		switch (name) {
			case MaterialNames.AMBER:
				return 2.0d;
			case MaterialNames.ALEXANDRITE:
				return 8.5d;
			case MaterialNames.PERIDOT:
			case MaterialNames.JADE:
			case MaterialNames.CARNELIAN:
			case MaterialNames.GARNET:
			case MaterialNames.AGATE:
				return 6.5d;
			case MaterialNames.AMETRINE:
			case MaterialNames.AMETHYST:
			case MaterialNames.CITRINE:
			case MaterialNames.INDICOLITE:
			case MaterialNames.IOLITE:
			case MaterialNames.JASPER:
			case MaterialNames.ONYX:
				return 7.0d;
			case MaterialNames.AQUAMARINE:
			case MaterialNames.BERYL:
			case MaterialNames.GOLDENBERYL:
			case MaterialNames.HELIODOR:
			case MaterialNames.MORGANITE:
			case MaterialNames.SPINEL:
				return 7.5d;
			case MaterialNames.BLACKDIAMOND:
				return 10.0d;
			case MaterialNames.BLUETOPAZ:
				return 8.0d;
			case MaterialNames.LEPIDOLITE:
				return 3.0d;
			case MaterialNames.MALACHITE:
				return 4.0d;
			case MaterialNames.MOLDAVITE:
				return 5.0d;
			case MaterialNames.MOONSTONE:
			case MaterialNames.OPAL:
			case MaterialNames.TANZANITE:
				return 6.0d;
			case MaterialNames.RUBY:
			case MaterialNames.SAPPHIRE:
			case MaterialNames.VIOLETSAPPHIRE:
				return 9.0d;
			case MaterialNames.TOPAZ:
			case MaterialNames.TURQUOISE:
				return 5.0d;
			default:
				return 1.0d;
		}
	}
}
