package zone.moddev.mc.basegems.integration.plugins;

import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.init.Materials;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.integration.MMDPlugin;
import com.mcmoddev.lib.integration.IIntegration;

@MMDPlugin(addonId = BaseGems.MODID, pluginId = Mekanism.PLUGIN_MODID)
public class Mekanism extends com.mcmoddev.lib.integration.plugins.MekanismBase implements IIntegration {

	private static boolean initDone = false;

	@Override
	public void init() {
		if (initDone || !com.mcmoddev.lib.util.ConfigBase.Options.isModEnabled(PLUGIN_MODID)) {
			return;
		}

		for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
			addOreMultiplicationRecipes(material);
		}

		initDone = true;
	}
}
