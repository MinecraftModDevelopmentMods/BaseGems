package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.lib.init.Materials;
import com.mcmoddev.lib.util.ConfigBase.Options;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.init.MMDCreativeTab;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * This class initializes all item groups in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class ItemGroups extends com.mcmoddev.lib.init.ItemGroups {

	private static boolean initDone = false;
	private static final int blocksTabId  = addTab("blocks", true );
	private static final int itemsTabId = addTab("items", true );
	private static final int toolsTabId = addTab("tools", true );
	public static final MMDCreativeTab blocksTab = getTab(blocksTabId);
	public static final MMDCreativeTab itemsTab = getTab(itemsTabId);
	public static final MMDCreativeTab toolsTab = getTab(toolsTabId); 

	private ItemGroups() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		initDone = true;
	}
	
	public static void setupIcons() {
		Item blocksTabIconItem = Item.getItemFromBlock(Options.isMaterialEnabled(MaterialNames.BLACKDIAMOND)?Materials.getMaterialByName(MaterialNames.BLACKDIAMOND).getBlock(Names.BLOCK):Materials.getMaterialByName(com.mcmoddev.basemetals.data.MaterialNames.IRON).getBlock(Names.BLOCK));
		Item itemsTabIconItem = Options.isThingEnabled("Gear")?Materials.getMaterialByName(MaterialNames.BLACKDIAMOND).getItem(Names.GEAR):net.minecraft.init.Items.STICK;
		Item toolsTabIconItem = Options.isThingEnabled("BasicTools")?Materials.getMaterialByName(MaterialNames.BLACKDIAMOND).getItem(Names.SWORD):net.minecraft.init.Items.DIAMOND_SWORD;
		
		blocksTab.setTabIconItem( new ItemStack(blocksTabIconItem) );
		itemsTab.setTabIconItem(new ItemStack(itemsTabIconItem));
		toolsTab.setTabIconItem(new ItemStack(toolsTabIconItem));
	}

}
