package com.mcmoddev.basegems.integration.plugins;

import com.mcmoddev.basemetals.integration.BaseMetalsPlugin;
import com.mcmoddev.basemetals.integration.IIntegration;

@BaseMetalsPlugin(Thaumcraft.PLUGIN_MODID)
public class Thaumcraft extends com.mcmoddev.basemetals.integration.plugins.Thaumcraft implements IIntegration {

	private static boolean initDone = false;

	@Override
	public void init() {
		if (initDone || !com.mcmoddev.basemetals.util.Config.Options.ENABLE_THAUMCRAFT) {
			return;
		}

		initDone = true;
	}
}
