package zone.moddev.mc.basegems.init;

import zone.moddev.mc.basegems.BaseGems;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.util.Oredicts;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * This class initializes all items in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Items extends com.mcmoddev.lib.init.Items {

	private static final Names[] MATERIAL_FORMS = {
			Names.BLEND, Names.INGOT, Names.NUGGET, Names.POWDER, Names.SMALLBLEND,
			Names.SMALLPOWDER, Names.ROD, Names.GEAR
	};
	private static final Names[] TOOL_FORMS = {
			Names.ARROW, Names.AXE, Names.BOLT, Names.BOW, Names.CRACKHAMMER, Names.CROSSBOW,
			Names.FISHING_ROD, Names.HOE, Names.PICKAXE, Names.SHEARS, Names.SHIELD, Names.SHOVEL,
			Names.SWORD
	};

	protected Items() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
			for (Names form : MATERIAL_FORMS) {
				create(form, material, ItemGroups.itemsTab);
			}
			for (Names form : TOOL_FORMS) {
				create(form, material, ItemGroups.toolsTab);
			}
			create(Names.DOOR, material, ItemGroups.blocksTab);
			create(Names.SLAB, material, ItemGroups.blocksTab);
			Item gem = material.getItem(Names.INGOT);
			if (gem != null) {
				// Shared ore logic looks up GEM. Both names refer to the historical ingot item.
				material.addNewItem(Names.GEM, gem);
				Oredicts.registerOre(Oredicts.GEM + material.getCapitalizedName(), gem);
			}
		}
	}

	static void registerCompoundNameAliases(MMDMaterial material) {
		// Match Silent's Gems while keeping the Base Metals keys used by shared recipes.
		final String suffix;
		switch (material.getName()) {
			case "blackdiamond": suffix = "BlackDiamond"; break;
			case "bluetopaz": suffix = "BlueTopaz"; break;
			case "goldenberyl": suffix = "GoldenBeryl"; break;
			case "violetsapphire": suffix = "VioletSapphire"; break;
			default: return;
		}

		String legacySuffix = material.getCapitalizedName();
		// Shared material items include the ore and storage block's inventory forms.
		for (ItemStack stack : material.getItems()) {
			for (int oreId : OreDictionary.getOreIDs(stack)) {
				String name = OreDictionary.getOreName(oreId);
				if (name.endsWith(legacySuffix)) {
					String prefix = name.substring(0, name.length() - legacySuffix.length());
					OreDictionary.registerOre(prefix + suffix, stack);
				}
			}
		}
	}
}
