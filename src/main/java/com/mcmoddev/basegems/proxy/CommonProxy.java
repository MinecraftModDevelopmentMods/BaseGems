package com.mcmoddev.basegems.proxy;

import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.basegems.init.*;
import com.mcmoddev.basegems.util.BGeConfig;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Base Gems Common Proxy
 *
 * @author Jasmine Iwanek
 *
 */
public class CommonProxy {

	public void preInit(final FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(com.mcmoddev.basegems.proxy.CommonProxy.class);
	}

	public void init(final FMLInitializationEvent event) {
		ItemGroups.setupIcons(MaterialNames.BLACKDIAMOND);
	}

	public void postInit(final FMLPostInitializationEvent event) {
		BGeConfig.postInit();
	}
}
