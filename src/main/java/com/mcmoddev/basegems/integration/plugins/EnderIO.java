package com.mcmoddev.basegems.integration.plugins;

import com.mcmoddev.basegems.integration.BaseGemsPlugin;
import com.mcmoddev.lib.integration.IIntegration;

/**
 *
 * @author Jasmine Iwanek
 *
 */
@BaseGemsPlugin(EnderIO.PLUGIN_MODID)
public class EnderIO extends com.mcmoddev.lib.integration.plugins.EnderIO implements IIntegration {

	private static boolean initDone = false;

	/**
	 *
	 */
	@Override
	public void init() {
		if (initDone || !com.mcmoddev.basemetals.util.Config.Options.ENABLE_ENDER_IO) {
			return;
		}

		initDone = true;
	}
}
