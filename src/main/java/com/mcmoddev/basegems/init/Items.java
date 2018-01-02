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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This class initializes all items in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Items extends com.mcmoddev.lib.init.Items {

	private static boolean initDone = false;

	protected Items() {
		throw new IllegalAccessError(SharedStrings.NOT_INSTANTIABLE);
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Blocks.init();

		List<String> materials = Arrays.asList(MaterialNames.AMBER, MaterialNames.ALEXANDRITE, MaterialNames.AGATE, MaterialNames.AMETRINE,
				MaterialNames.AMETHYST, MaterialNames.AQUAMARINE, MaterialNames.BERYL, MaterialNames.BLACKDIAMOND, MaterialNames.BLUETOPAZ,
				MaterialNames.CARNELION, MaterialNames.CITRINE, MaterialNames.GARNET, MaterialNames.GOLDENBERYL, MaterialNames.HELIODOR,
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

		initDone = true;
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (MMDMaterial mat : Materials.getMaterialsByMod(BaseGems.MODID)) {
			for (Item item : mat.getItems()) {
				if (item.getRegistryName().getResourceDomain().equals(BaseGems.MODID)) {
					event.getRegistry().register(item);
				}
			}
		}
		Oredicts.registerItemOreDictionaryEntries();
		Oredicts.registerBlockOreDictionaryEntries();
	}

	protected static Item create(@Nonnull final Names name, @Nonnull final MMDMaterial material) {
		CreativeTabs tab;

		if ((name.equals(Names.DOOR)) || (name.equals(Names.SLAB))) {
			tab = ItemGroups.myTabs.blocksTab;
		} else if ((name.equals(Names.BLEND)) || (name.equals(Names.INGOT)) || (name.equals(Names.NUGGET)) || (name.equals(Names.POWDER)) || (name.equals(Names.SMALLBLEND)) || (name.equals(Names.SMALLPOWDER)) || (name.equals(Names.ROD)) || (name.equals(Names.GEAR))) {
			tab = ItemGroups.myTabs.itemsTab;
		} else {
			tab = ItemGroups.myTabs.toolsTab;
		}

		return create(name, material, tab);
	}
}
