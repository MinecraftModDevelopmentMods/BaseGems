package zone.moddev.mc.basegems.init;

import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.init.MMDCreativeTab;
import com.mcmoddev.lib.material.MMDMaterial;
import net.minecraft.init.Items;

public class ItemGroups extends com.mcmoddev.lib.init.ItemGroups {
	public static final MMDCreativeTab blocksTab = addTab("blocks", true);
	public static final MMDCreativeTab itemsTab = addTab("items", true);
	public static final MMDCreativeTab toolsTab = addTab("tools", true);

	private ItemGroups() {
		throw new IllegalAccessError("Not an instantiable class");
	}

	public static void setupIcons() {
		MMDMaterial diamond = Materials.blackdiamond;
		if (diamond != null && diamond.hasBlock(Names.BLOCK)) {
			blocksTab.setTabIconItem(diamond.getBlock(Names.BLOCK));
		} else {
			blocksTab.setTabIconItem(net.minecraft.init.Blocks.DIAMOND_BLOCK);
		}
		itemsTab.setTabIconItem(diamond != null && diamond.hasItem(Names.GEAR)
				? diamond.getItem(Names.GEAR) : Items.DIAMOND);
		toolsTab.setTabIconItem(diamond != null && diamond.hasItem(Names.SWORD)
				? diamond.getItem(Names.SWORD) : Items.DIAMOND_SWORD);
	}
}
