package basegems.items;

import basegems.init.Achievements;
import basegems.init.Materials;
import cyano.basemetals.material.MetalMaterial;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Ingots
 * @author Jasmine Iwanek
 *
 */
public class ItemMetalIngot extends cyano.basemetals.items.ItemMetalIngot {

	/**
	 * 
	 * @param metal
	 */
	public ItemMetalIngot(MetalMaterial metal) {
		super(metal);
	}

	@Override
	public void onCreated(final ItemStack item, final World world, final EntityPlayer crafter) {
		super.onCreated(item, world, crafter);
		crafter.addStat(cyano.basemetals.init.Achievements.this_is_new, 1);

		if(metal == Materials.template) crafter.addStat(Achievements.template_maker, 1);
	}
}
