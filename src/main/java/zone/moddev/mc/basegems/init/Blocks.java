package zone.moddev.mc.basegems.init;

import zone.moddev.mc.basegems.BaseGems;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.util.Oredicts;
import net.minecraft.block.Block;
import zone.moddev.mc.basegems.block.OverlayOreBlock;

/**
 * This class initializes all blocks in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Blocks extends com.mcmoddev.lib.init.Blocks {

	private static final Names[] FORMS = {
			Names.BLOCK, Names.PLATE, Names.BARS, Names.DOOR, Names.TRAPDOOR, Names.BUTTON,
			Names.SLAB, Names.DOUBLE_SLAB, Names.LEVER, Names.PRESSURE_PLATE, Names.STAIRS, Names.WALL
	};

	protected Blocks() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
			for (Names form : FORMS) {
				create(form, material);
			}
			if (material.hasOre() && !material.hasBlock(Names.ORE) && isNameEnabled(Names.ORE)) {
				Block ore = addBlock(new OverlayOreBlock(material), Names.ORE, material, ItemGroups.blocksTab);
				material.addNewBlock(Names.ORE, ore);
				Oredicts.registerOre("ore" + material.getCapitalizedName(), ore);
			}
		}
	}
}
