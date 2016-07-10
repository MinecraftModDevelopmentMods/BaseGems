package basegems.items;

import basegems.init.Materials;
import cyano.basemetals.init.Achievements;
import cyano.basemetals.material.MetalMaterial;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Blends
 * @author Jasmine Iwanek
 *
 */
public class ItemMetalBlend extends cyano.basemetals.items.ItemMetalBlend {

	/**
	 * 
	 * @param metal
	 */
	public ItemMetalBlend(MetalMaterial metal) {
		super(metal);
	}

	@Override
	public void onCreated(final ItemStack item, final World world, final EntityPlayer crafter) {
		super.onCreated(item, world, crafter);
		// achievement
		if(metal == Materials.template) {
			crafter.addStat(Achievements.metallurgy, 1);
		}
	}
}
