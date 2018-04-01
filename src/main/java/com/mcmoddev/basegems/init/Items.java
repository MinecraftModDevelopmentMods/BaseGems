package com.mcmoddev.basegems.init;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.data.SharedStrings;
import com.mcmoddev.lib.init.Materials;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.util.Oredicts;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This class initializes all items in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Items extends com.mcmoddev.lib.init.Items {
	protected Items() {
		throw new IllegalAccessError(SharedStrings.NOT_INSTANTIABLE);
	}

	/**
	 *
	 */
	public static void init() {
		final List<String> materials = Arrays.asList(MaterialNames.AMBER, MaterialNames.ALEXANDRITE, MaterialNames.AGATE, MaterialNames.AMETRINE,
				MaterialNames.AMETHYST, MaterialNames.AQUAMARINE, MaterialNames.BERYL, MaterialNames.BLACKDIAMOND, MaterialNames.BLUETOPAZ,
				MaterialNames.CARNELIAN, MaterialNames.CITRINE, MaterialNames.GARNET, MaterialNames.GOLDENBERYL, MaterialNames.HELIODOR,
				MaterialNames.INDICOLITE, MaterialNames.IOLITE, MaterialNames.JADE, MaterialNames.JASPER, MaterialNames.LEPIDOLITE,
				MaterialNames.MALACHITE, MaterialNames.MOLDAVITE, MaterialNames.MOONSTONE, MaterialNames.MORGANITE, MaterialNames.ONYX,
				MaterialNames.OPAL, MaterialNames.PERIDOT, MaterialNames.RUBY, MaterialNames.SAPPHIRE, MaterialNames.SPINEL,
				MaterialNames.TANZANITE, MaterialNames.TOPAZ, MaterialNames.TURQUOISE, MaterialNames.VIOLETSAPPHIRE);
		
		materials.forEach( name -> {
			final MMDMaterial material = Materials.getMaterialByName(name);

			create(Names.BLEND, material);
			create(Names.INGOT, material);
			create(Names.NUGGET, material);
			create(Names.POWDER, material);
			create(Names.SMALLBLEND, material);
			create(Names.SMALLPOWDER, material);

			create(Names.ARROW, material);
			create(Names.AXE, material);
			create(Names.BOLT, material);
			create(Names.BOOTS, material);
			create(Names.BOW, material);
			create(Names.CHESTPLATE, material);
			create(Names.CRACKHAMMER, material);
			create(Names.CROSSBOW, material);
			create(Names.DOOR, material);
			create(Names.FISHING_ROD, material);
			create(Names.HELMET, material);
			create(Names.HOE, material);
			create(Names.HORSE_ARMOR, material);
			create(Names.LEGGINGS, material);
			create(Names.PICKAXE, material);
			create(Names.SHEARS, material);
			create(Names.SHIELD, material);
			create(Names.SHOVEL, material);
			create(Names.SLAB, material);
			create(Names.SWORD, material);
			create(Names.ROD, material);
			create(Names.GEAR, material);
		});
	}

	private static boolean filterFunc(ItemStack item) {
		return item.getItem().getRegistryName().getResourceDomain().equals(BaseGems.MODID);
	}

	private static Item mapFunc(ItemStack itemStack) {
		return itemStack.getItem();
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Materials.getMaterialsByMod(BaseGems.MODID).stream()
		.forEach( mat -> {
			mat.getItems().stream()
			.filter(Items::filterFunc)
			.map(Items::mapFunc)
			.forEach(event.getRegistry()::register);
		});

		Oredicts.registerItemOreDictionaryEntries();
		Oredicts.registerBlockOreDictionaryEntries();
	}
}
