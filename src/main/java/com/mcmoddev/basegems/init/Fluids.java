package com.mcmoddev.basegems.init;

import java.util.Arrays;
import java.util.List;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.lib.data.SharedStrings;
import com.mcmoddev.lib.events.MMDLibRegisterFluids;
import com.mcmoddev.lib.init.Materials;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This class initializes all fluids in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
@Mod.EventBusSubscriber(modid=BaseGems.MODID)
public class Fluids extends com.mcmoddev.lib.init.Fluids {
	private Fluids() {
		throw new IllegalAccessError(SharedStrings.NOT_INSTANTIABLE);
	}

	/**
	 *
	 */
	@SubscribeEvent
	public static void registerEvent(MMDLibRegisterFluids event) {
		final List<String> materials = Arrays.asList(MaterialNames.AMBER, MaterialNames.ALEXANDRITE, MaterialNames.AGATE, MaterialNames.AMETRINE,
				MaterialNames.AMETHYST, MaterialNames.AQUAMARINE, MaterialNames.BERYL, MaterialNames.BLACKDIAMOND, MaterialNames.BLUETOPAZ,
				MaterialNames.CARNELIAN, MaterialNames.CITRINE, MaterialNames.GARNET, MaterialNames.GOLDENBERYL, MaterialNames.HELIODOR,
				MaterialNames.INDICOLITE, MaterialNames.IOLITE, MaterialNames.JADE, MaterialNames.JASPER, MaterialNames.LEPIDOLITE,
				MaterialNames.MALACHITE, MaterialNames.MOLDAVITE, MaterialNames.MOONSTONE, MaterialNames.MORGANITE, MaterialNames.ONYX,
				MaterialNames.OPAL, MaterialNames.PERIDOT, MaterialNames.RUBY, MaterialNames.SAPPHIRE, MaterialNames.SPINEL,
				MaterialNames.TANZANITE, MaterialNames.TOPAZ, MaterialNames.TURQUOISE, MaterialNames.VIOLETSAPPHIRE);

		materials.stream()
				.filter(Materials::hasMaterial).forEach(materialName -> {
					addFluid( materialName, 2000, 10000, 330, 10);
					addFluidBlock(materialName);
				});
	}
}
