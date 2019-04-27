package com.mcmoddev.basegems.init;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.data.SharedStrings;
import com.mcmoddev.lib.events.MMDLibRegisterBlocks;
import com.mcmoddev.lib.init.Materials;
import com.mcmoddev.lib.material.MMDMaterial;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * This class initializes all blocks in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
@Mod.EventBusSubscriber(modid=BaseGems.MODID)
public class Blocks extends com.mcmoddev.lib.init.Blocks {

	private Blocks() {
		throw new IllegalAccessError(SharedStrings.NOT_INSTANTIABLE);
	}

	/**
	 *
	 */
	@SubscribeEvent
	public static void init(final MMDLibRegisterBlocks event) {
		final List<String> materials = Arrays.asList(MaterialNames.AMBER, MaterialNames.ALEXANDRITE, MaterialNames.AGATE, MaterialNames.AMETRINE,
				MaterialNames.AMETHYST, MaterialNames.AQUAMARINE, MaterialNames.BERYL, MaterialNames.BLACKDIAMOND, MaterialNames.BLUETOPAZ,
				MaterialNames.CARNELIAN, MaterialNames.CITRINE, MaterialNames.GARNET, MaterialNames.GOLDENBERYL, MaterialNames.HELIODOR,
				MaterialNames.INDICOLITE, MaterialNames.IOLITE, MaterialNames.JADE, MaterialNames.JASPER, MaterialNames.LEPIDOLITE,
				MaterialNames.MALACHITE, MaterialNames.MOLDAVITE, MaterialNames.MOONSTONE, MaterialNames.MORGANITE, MaterialNames.ONYX,
				MaterialNames.OPAL, MaterialNames.PERIDOT, MaterialNames.RUBY, MaterialNames.SAPPHIRE, MaterialNames.SPINEL,
				MaterialNames.TANZANITE, MaterialNames.TOPAZ, MaterialNames.TURQUOISE, MaterialNames.VIOLETSAPPHIRE);

		materials.stream().filter(Materials::hasMaterial)
		.filter(materialName -> !Materials.getMaterialByName(materialName).isEmpty())
		.forEach(materialName -> {
			final MMDMaterial material = Materials.getMaterialByName(materialName);
			Arrays.asList(Names.BLOCK, Names.PLATE, Names.ORE, Names.BARS, Names.DOOR, Names.TRAPDOOR,
					Names.BUTTON, Names.SLAB, Names.DOUBLE_SLAB, Names.LEVER, Names.PRESSURE_PLATE,
					Names.STAIRS, Names.WALL).stream()
			.forEach(name -> create( name, material));
		});
	}

	/**
	 * Registers Blocks for this mod.
	 *
	 * @param event The Event.
	 */
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		Materials.getMaterialsByMod(BaseGems.MODID).stream()
		.forEach(mat -> regBlocks(event.getRegistry(), mat.getBlocks()));
	}

	private static void regBlocks(final IForgeRegistry<Block> registry,
			final ImmutableList<Block> blocks) {
		blocks.stream().filter(
				block -> block.getRegistryName().getNamespace().equals(BaseGems.MODID))
		.forEach( registry::register );
	}
}
