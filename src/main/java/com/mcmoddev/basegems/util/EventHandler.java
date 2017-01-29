package com.mcmoddev.basegems.util;

import com.mcmoddev.basemetals.material.IMetalObject;
import com.mcmoddev.basemetals.material.MetalMaterial;
import com.mcmoddev.basemetals.util.Config.Options;
import com.mcmoddev.lib.items.ItemMetalBlend;
import com.mcmoddev.lib.items.ItemMetalIngot;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;

public class EventHandler {

	@SubscribeEvent
	void event(ItemCraftedEvent event) {
		final Item item = event.crafting.getItem();
		if (item instanceof IMetalObject) {
			final MetalMaterial material = ((IMetalObject) item).getMaterial();
			if (item instanceof ItemMetalBlend) {
				if (Options.ENABLE_ACHIEVEMENTS) {
//					event.player.addStat(Achievements.metallurgy, 1);
				}
			}
		}
	}

	@SubscribeEvent
	void event(ItemSmeltedEvent event) {
		final Item item = event.smelting.getItem();
		if (item instanceof IMetalObject) {
			final MetalMaterial material = ((IMetalObject) item).getMaterial();
			if (item instanceof ItemMetalIngot) {
				if (Options.ENABLE_ACHIEVEMENTS) {
//					event.player.addStat(Achievements.this_is_new, 1);
				}
			}
		}
	}
}
