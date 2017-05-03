package com.mcmoddev.basegems.init;

import com.mcmoddev.basemetals.data.MaterialNames;
import com.mcmoddev.basemetals.init.Materials;
import com.mcmoddev.basemetals.util.Config.Options;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.init.MMDCreativeTab;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * This class initializes all item groups in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class ItemGroups extends com.mcmoddev.lib.init.ItemGroups {

	private static boolean initDone = false;
	private static final int blocksTabId  = addTab("blocks", true );;
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

		// blocksTab = addTab("blocks", true, Materials.vanilla_iron);
		// itemsTab = addTab("items", true, Materials.vanilla_iron);
		// toolsTab = addTab("tools", true, Materials.vanilla_iron);

		initDone = true;
	}
	
	public static void setupIcons() {
		Item blocksTabIconItem = Item.getItemFromBlock(Materials.getMaterialByName("blackdiamond")!=null?Materials.getMaterialByName("blackdiamond").getBlock(Names.BLOCK):(Block) Materials.getMaterialByName(MaterialNames.IRON).getBlock(Names.BLOCK));
		Item itemsTabIconItem = (Options.enableGear&&Materials.getMaterialByName("blackdiamond")!=null)?Materials.getMaterialByName("blackdiamond").getItem(Names.GEAR):net.minecraft.init.Items.STICK;
		Item toolsTabIconItem = (Options.enableBasicTools&&Materials.getMaterialByName("blackdiamond")!=null)?Materials.getMaterialByName("blackdiamond").getItem(Names.SWORD):net.minecraft.init.Items.DIAMOND_SWORD;
		
		blocksTab.setTabIconItem( blocksTabIconItem );
		itemsTab.setTabIconItem(itemsTabIconItem);
		toolsTab.setTabIconItem(toolsTabIconItem);
	}

}
