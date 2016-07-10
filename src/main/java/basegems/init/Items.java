package basegems.init;

import basegems.BaseGems;
//import basegems.blocks.*;
import basegems.items.*;
import cyano.basemetals.blocks.*;
import cyano.basemetals.items.*;
import cyano.basemetals.material.IMetalObject;
import cyano.basemetals.material.MetalMaterial;
import cyano.basemetals.registry.IOreDictionaryEntry;
import net.minecraft.block.BlockDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

/**
 * This class initializes all items in Base Gems and provides some utility 
 * methods for looking up items. 
 * @author DrCyano
 *
 */
public abstract class Items {

	public static Item template_arrow;
	public static Item template_axe;
	public static Item template_blend;
	public static Item template_boots;
	public static Item template_bolt;
	public static Item template_bow;
	public static Item template_chestplate;
	public static Item template_crackhammer;
	public static Item template_crossbow;
	public static Item template_door;
	public static Item template_fishingrod;
	public static Item template_helmet;
	public static Item template_hoe;
	public static Item template_horsearmor;
	public static Item template_ingot;
	public static Item template_leggings;
	public static Item template_nugget;
	public static Item template_pickaxe;
	public static Item template_powder;
	public static Item template_shears;
	public static Item template_shovel;
	public static Item template_smallblend;
	public static Item template_smallpowder;
	public static Item template_sword;
	public static Item template_rod;
	public static Item template_gear;

	private static boolean initDone = false;

	private static Map<Item, String> itemRegistry = new HashMap<>();
	private static Map<String, Item> allItems = new HashMap<>();
	private static Map<MetalMaterial, List<Item>> itemsByMetal = new HashMap<>();

	private static Map<BlockDoor,Item> doorMap = new HashMap<>();

	@SuppressWarnings("rawtypes")
	private static Map<Class, Integer> classSortingValues = new HashMap<>();
	private static Map<MetalMaterial, Integer> materialSortingValues = new HashMap<>();

	/**
	 * Gets an item by its name. The name is the name as it is registered in 
	 * the GameRegistry, not its unlocalized name (the unlocalized name is the 
	 * registered name plus the prefix "modernmetals.")
	 * @param name The name of the item in question
	 * @return The item matching that name, or null if there isn't one
	 */
	public static Item getItemByName(String name) {
		return allItems.get(name);
	}

	/**
	 * This is the reverse of the getItemByName(...) method, returning the 
	 * registered name of an item instance (Modern Metals items only).
	 * @param i The item in question
	 * @return The name of the item, or null if the item is not a Modern Metals 
	 * item.
	 */
	public static String getNameOfItem(Item i) {
		return itemRegistry.get(i);
	}

	/**
	 * Gets a map of all items added, sorted by metal
	 * @return An unmodifiable map of added items catagorized by metal material
	 */
	public static Map<MetalMaterial, List<Item>> getItemsByMetal() {
		return Collections.unmodifiableMap(itemsByMetal);
	}

	/**
	 * Gets the inventory item corresponding to a given door block
	 * @param b The door block
	 * @return The item that the player should use to place that kind of door
	 */
	public static Item getDoorItemForBlock(BlockMetalDoor b) {
		return doorMap.get(b);
	}

	/**
	 * 
	 */
	public static void init() {
		if(initDone) return;
		
		basegems.init.Blocks.init();

		template_arrow = create_arrow(Materials.template);
		template_axe = create_axe(Materials.template);
		template_blend = create_blend(Materials.template);
		template_boots = create_boots(Materials.template);
		template_bolt = create_bolt(Materials.template);
		template_bow = create_bow(Materials.template);
		template_chestplate = create_chestplate(Materials.template);
		template_crackhammer = create_crackhammer(Materials.template);
		template_crossbow = create_crossbow(Materials.template);
		template_door = create_door(Materials.template, Blocks.template_door);
		template_fishingrod = create_fishingrod(Materials.template);
		template_helmet = create_helmet(Materials.template);
		template_hoe = create_hoe(Materials.template);
		template_horsearmor = create_horsearmor(Materials.template);
		template_ingot = create_ingot(Materials.template);
		template_leggings = create_leggings(Materials.template);
		template_nugget = create_nugget(Materials.template);
		template_pickaxe = create_pickaxe(Materials.template);
		template_powder = create_powder(Materials.template);
		template_shears = create_shears(Materials.template);
		template_shovel = create_shovel(Materials.template);
		template_smallblend = create_smallblend(Materials.template);
		template_smallpowder = create_smallpowder(Materials.template);
		template_sword = create_sword(Materials.template);
		template_rod = create_rod(Materials.template);
		template_gear = create_gear(Materials.template);

		for(Item i : itemRegistry.keySet()) {
			allItems.put(itemRegistry.get(i), i);
			if(i instanceof IOreDictionaryEntry) { OreDictionary.registerOre(((IOreDictionaryEntry)i).getOreDictionaryName(), i); }
		}

		int ss = 0;
		classSortingValues.put(BlockMetalOre.class, ++ss * 10000);
		classSortingValues.put(BlockMetalBlock.class, ++ss * 10000);
		classSortingValues.put(BlockMetalPlate.class, ++ss * 10000);
		classSortingValues.put(BlockMetalBars.class, ++ss * 10000);
		classSortingValues.put(BlockMetalDoor.class, ++ss * 10000);
		classSortingValues.put(BlockMetalTrapDoor.class, ++ss * 10000);
		classSortingValues.put(InteractiveFluidBlock.class, ++ss * 10000);
		classSortingValues.put(basegems.items.ItemMetalIngot.class, ++ss * 10000);
		classSortingValues.put(ItemMetalNugget.class, ++ss * 10000);
		classSortingValues.put(ItemMetalPowder.class, ++ss * 10000);
		classSortingValues.put(basegems.items.ItemMetalBlend.class, classSortingValues.get(ItemMetalPowder.class));
		classSortingValues.put(ItemMetalCrackHammer.class, ++ss * 10000);
		classSortingValues.put(ItemMetalPickaxe.class, ++ss * 10000);
		classSortingValues.put(ItemMetalShovel.class, ++ss * 10000);
		classSortingValues.put(ItemMetalAxe.class, ++ss * 10000);
		classSortingValues.put(ItemMetalHoe.class, ++ss * 10000);
		classSortingValues.put(ItemMetalSword.class, ++ss * 10000);
		classSortingValues.put(ItemMetalArmor.class, ++ss * 10000);
		classSortingValues.put(GenericMetalItem.class, ++ss * 10000);
		classSortingValues.put(ItemMetalArrow.class, ++ss * 10000);
		classSortingValues.put(ItemMetalBolt.class, ++ss * 10000);
		classSortingValues.put(ItemMetalBow.class, ++ss * 10000);
		classSortingValues.put(ItemMetalCrossbow.class, ++ss * 10000);
		classSortingValues.put(ItemMetalShears.class, ++ss * 10000);
		classSortingValues.put(ItemMetalSmallBlend.class, ++ss * 10000);
		classSortingValues.put(ItemMetalSmallPowder.class, ++ss * 10000);
		classSortingValues.put(ItemMetalDoor.class, classSortingValues.get(BlockMetalDoor.class));

		List<MetalMaterial> metlist = new ArrayList<>(Materials.getAllMetals().size());
		metlist.addAll(Materials.getAllMetals());
		metlist.sort((MetalMaterial a, MetalMaterial b)-> a.getName().compareToIgnoreCase(b.getName()));
		for(int i = 0; i < metlist.size(); i++) {
			materialSortingValues.put(metlist.get(i), i*100);
		}

		initDone = true;
	}

	private static Item registerItem(Item item, String name, MetalMaterial metal, CreativeTabs tab) {
		ResourceLocation location = new ResourceLocation(BaseGems.MODID, name);
		item.setRegistryName(location);
		item.setUnlocalizedName(location.toString());
		GameRegistry.register(item); 
		itemRegistry.put(item, name);
		if(tab != null) {
			item.setCreativeTab(tab);
		}
		if(metal != null) {
			itemsByMetal.computeIfAbsent(metal, (MetalMaterial g)->new ArrayList<>());
			itemsByMetal.get(metal).add(item);
		}
		return item;
	}

	private static Item create_ingot(MetalMaterial metal) {
		return registerItem(new basegems.items.ItemMetalIngot(metal), metal.getName()+"_"+"ingot", metal, ItemGroups.tab_items);
	}

	private static Item create_nugget(MetalMaterial metal) {
		return registerItem(new ItemMetalNugget(metal), metal.getName()+"_"+"nugget", metal, ItemGroups.tab_items);
	}

	private static Item create_powder(MetalMaterial metal) {
		return registerItem(new ItemMetalPowder(metal), metal.getName()+"_"+"powder", metal, ItemGroups.tab_items);
	}

	private static Item create_blend(MetalMaterial metal) {
		return registerItem(new basegems.items.ItemMetalBlend(metal), metal.getName()+"_"+"blend", metal, ItemGroups.tab_items);
	}

	private static Item create_rod(MetalMaterial metal) {
		return registerItem(new GenericMetalItem(metal), metal.getName()+"_"+"rod", metal, ItemGroups.tab_items);
	}

	private static Item create_gear(MetalMaterial metal) {
		Item i = registerItem(new GenericMetalItem(metal), metal.getName()+"_"+"gear", metal, ItemGroups.tab_items);
		OreDictionary.registerOre("gear"+metal.getCapitalizedName(), i);
		return i;
	}

	private static Item create_axe(MetalMaterial metal) {
		return registerItem(new ItemMetalAxe(metal), metal.getName()+"_"+"axe", metal, ItemGroups.tab_tools);
	}

	private static Item create_crackhammer(MetalMaterial metal) {
		return registerItem(new ItemMetalCrackHammer(metal), metal.getName()+"_"+"crackhammer", metal, ItemGroups.tab_tools);
	}

	private static Item create_hoe(MetalMaterial metal) {
		return registerItem(new ItemMetalHoe(metal), metal.getName()+"_"+"hoe", metal, ItemGroups.tab_tools);
	}

	private static Item create_pickaxe(MetalMaterial metal) {
		return registerItem(new ItemMetalPickaxe(metal), metal.getName()+"_"+"pickaxe", metal, ItemGroups.tab_tools);
	}

	private static Item create_shovel(MetalMaterial metal) {
		return registerItem(new ItemMetalShovel(metal), metal.getName()+"_"+"shovel", metal, ItemGroups.tab_tools);
	}

	private static Item create_sword(MetalMaterial metal) {
		return registerItem(new ItemMetalSword(metal), metal.getName()+"_"+"sword", metal, ItemGroups.tab_tools);
	}

	private static Item create_helmet(MetalMaterial metal) {
		return registerItem(ItemMetalArmor.createHelmet(metal), metal.getName()+"_"+"helmet", metal, ItemGroups.tab_tools);
	}

	private static Item create_chestplate(MetalMaterial metal) {
		return registerItem(ItemMetalArmor.createChestplate(metal), metal.getName()+"_"+"chestplate", metal, ItemGroups.tab_tools);
	}

	private static Item create_leggings(MetalMaterial metal) {
		return registerItem(ItemMetalArmor.createLeggings(metal), metal.getName()+"_"+"leggings", metal, ItemGroups.tab_tools);
	}

	private static Item create_boots(MetalMaterial metal) {
		return registerItem(ItemMetalArmor.createBoots(metal), metal.getName()+"_"+"boots", metal, ItemGroups.tab_tools);
	}

	private static Item create_horsearmor(MetalMaterial metal) {
		return registerItem(new ItemMetalHorseArmor(metal), metal.getName()+"_"+"horsearmor", metal, ItemGroups.tab_tools);
	}

	private static Item create_arrow(MetalMaterial metal) {
		return registerItem(new ItemMetalArrow(metal), metal.getName()+"_"+"arrow", metal, ItemGroups.tab_tools);
	}

	private static Item create_bolt(MetalMaterial metal) {
		return registerItem(new ItemMetalBolt(metal), metal.getName()+"_"+"bolt", metal, ItemGroups.tab_tools);
	}

	private static Item create_bow(MetalMaterial metal) {
		return registerItem(new ItemMetalBow(metal), metal.getName()+"_"+"bow", metal, ItemGroups.tab_tools);
	}

	private static Item create_crossbow(MetalMaterial metal) {
		return registerItem(new ItemMetalCrossbow(metal), metal.getName()+"_"+"crossbow", metal, ItemGroups.tab_tools);
	}

	private static Item create_shears(MetalMaterial metal) {
		return registerItem(new ItemMetalShears(metal), metal.getName()+"_"+"shears", metal, ItemGroups.tab_tools);
	}

	private static Item create_smallblend(MetalMaterial metal) {
		return registerItem(new ItemMetalSmallBlend(metal), metal.getName()+"_"+"smallblend", metal, ItemGroups.tab_items);
	}

	private static Item create_fishingrod(MetalMaterial metal) {
		return registerItem(new ItemMetalFishingRod(metal), metal.getName()+"_"+"fishingrod", metal, ItemGroups.tab_tools);
	}

	private static Item create_smallpowder(MetalMaterial metal) {
		return registerItem(new ItemMetalSmallPowder(metal), metal.getName()+"_"+"smallpowder", metal, ItemGroups.tab_items);
	}

	private static Item create_door(MetalMaterial metal, BlockDoor door) {
		ResourceLocation location = new ResourceLocation(BaseGems.MODID, metal.getName()+"_"+"door");
		Item item = new ItemMetalDoor(door, metal);
		registerItem(item, location.getResourcePath()+"_"+"item", metal, ItemGroups.tab_blocks);
		item.setUnlocalizedName(location.toString()); // Hack to set name right
		doorMap.put(door, item);
		return item;
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static int getSortingValue(ItemStack a) {
		int classVal = 990000;
		int metalVal = 9900;
		if(a.getItem() instanceof ItemBlock && ((ItemBlock)a.getItem()).getBlock() instanceof IMetalObject) {
			classVal = classSortingValues.computeIfAbsent(((ItemBlock)a.getItem()).getBlock().getClass(),
					(Class c)->990000);
			metalVal = materialSortingValues.computeIfAbsent(((IMetalObject)((ItemBlock)a.getItem()).getBlock()).getMetalMaterial(),
					(MetalMaterial m)->9900);
		} else if(a.getItem() instanceof IMetalObject) {
			classVal = classSortingValues.computeIfAbsent(a.getItem().getClass(),
					(Class c)->990000);
			metalVal = materialSortingValues.computeIfAbsent(((IMetalObject)a.getItem()).getMetalMaterial(),
					(MetalMaterial m)->9900);
		}
		return classVal + metalVal + (a.getMetadata() % 100);
	}

	/**
	 * 
	 * @param event
	 */
	@SideOnly(Side.CLIENT)
	public static void registerItemRenders(FMLInitializationEvent event) {
		for(Item i : itemRegistry.keySet()) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(i, 0, 
				new ModelResourceLocation(new ResourceLocation(BaseGems.MODID, itemRegistry.get(i)), "inventory"));
		}
	}
}
