package zone.moddev.mc.basegems.integration.plugins;

import com.mcmoddev.lib.data.Names;
import net.minecraftforge.fluids.Fluid;
import slimeknights.tconstruct.library.TinkerRegistry;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.init.Materials;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.integration.MMDPlugin;
import com.mcmoddev.lib.integration.IIntegration;

/**
 *
 * @author Jasmine Iwanek
 *
 */
@MMDPlugin(addonId = BaseGems.MODID, pluginId = TinkersConstruct.PLUGIN_MODID)
public class TinkersConstruct extends com.mcmoddev.lib.integration.plugins.TinkersConstructBase implements IIntegration {

	private static boolean initDone = false;

	@Override
	public void init() {
		if (initDone || !com.mcmoddev.lib.util.ConfigBase.Options.isModEnabled(PLUGIN_MODID)) {
			return;
		}

		for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
			Fluid fluid = material.getFluid();
            if (fluid == null) continue;

            melt(material, fluid, Names.ORE, 288);
            melt(material, fluid, Names.BLOCK, 1296);
            melt(material, fluid, Names.INGOT, 144);
            melt(material, fluid, Names.NUGGET, 16);
            melt(material, fluid, Names.POWDER, 144);
            melt(material, fluid, Names.SMALLPOWDER, 16);
            melt(material, fluid, Names.SLAB, 648);
            melt(material, fluid, Names.WALL, 1296);
            melt(material, fluid, Names.SHEARS, 288);
            melt(material, fluid, Names.PRESSURE_PLATE, 288);
            melt(material, fluid, Names.BARS, 54);
            melt(material, fluid, Names.ROD, 72);
            melt(material, fluid, Names.DOOR, 288);
            melt(material, fluid, Names.TRAPDOOR, 576);
            melt(material, fluid, Names.BUTTON, 32);
		}

		initDone = true;
	}

    private static void melt(MMDMaterial material, Fluid fluid, Names form, int amount) {
        if (material.hasItem(form)) {
            TinkerRegistry.registerMelting(material.getItem(form), fluid, amount);
        } else if (material.hasBlock(form)) {
            TinkerRegistry.registerMelting(material.getBlock(form), fluid, amount);
        }
    }
}
