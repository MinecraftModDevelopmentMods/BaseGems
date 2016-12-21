package mmd.basegems.util;

import cyano.basemetals.items.ItemMetalBlend;
import cyano.basemetals.items.ItemMetalIngot;
import cyano.basemetals.material.IMetalObject;
import cyano.basemetals.material.MetalMaterial;
import cyano.basemetals.util.Config.Options;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;

public class EventHandler {

/*
	@SubscribeEvent
    public void attackEvent(LivingAttackEvent e) {
        float damage = e.getAmount();
        if (!(e.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) e.getEntityLiving();
        if (player.getActiveItemStack() == null) {
            return;
        }
        ItemStack activeItemStack = player.getActiveItemStack();
        if ((damage > 0.0F) && (activeItemStack != null) && ((activeItemStack.getItem() instanceof ItemMetalShield))) {
            int i = 1 + MathHelper.floor(damage);
            activeItemStack.damageItem(i, player);
            if (activeItemStack.stackSize <= 0) {
                EnumHand enumhand = player.getActiveHand();
                ForgeEventFactory.onPlayerDestroyItem(player, activeItemStack, enumhand);
                if (enumhand == EnumHand.MAIN_HAND) {
                    player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, (ItemStack) null);
                }
                else {
                    player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, (ItemStack) null);
                }
                activeItemStack = null;
                if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
                    player.playSound(SoundEvents.BLOCK_ANVIL_BREAK, 0.8F, 0.8F + player.world.rand.nextFloat() * 0.4F);
                }
            }
        }
    }
*/

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
//				event.player.addStat(Achievements.this_is_new, 1);
				}
			}
		}
	}
}
