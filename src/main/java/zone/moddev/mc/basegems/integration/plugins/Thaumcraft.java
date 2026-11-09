package zone.moddev.mc.basegems.integration.plugins;

import zone.moddev.mc.basegems.BaseGems;
import com.mcmoddev.lib.integration.MMDPlugin;
import com.mcmoddev.lib.integration.IIntegration;

@MMDPlugin(addonId = BaseGems.MODID, pluginId = Thaumcraft.PLUGIN_MODID)
public class Thaumcraft extends com.mcmoddev.lib.integration.plugins.ThaumcraftBase implements IIntegration {

	private static boolean initDone = false;

	@Override
	public void init() {
		if (initDone || !com.mcmoddev.lib.util.ConfigBase.Options.isModEnabled(PLUGIN_MODID)) {
			return;
		}

		initDone = true;
	}
}
