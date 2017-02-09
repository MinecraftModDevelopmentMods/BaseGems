package com.mcmoddev.basegems;

import com.mcmoddev.basegems.proxy.CommonProxy;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;

/**
 * This is the entry point for this mod.
 *
 * @author Jasmine Iwanek
 *
 */
@Mod(modid = BaseGems.MODID, name = BaseGems.NAME, version = BaseGems.VERSION, dependencies = "required-after:Forge@[12.17.0.1976,);required-after:basemetals;before:buildingbricks", acceptedMinecraftVersions = "[1.10.2,)", updateJSON = BaseGems.UPDATEJSON)
public class BaseGems {

	@Instance
	public static BaseGems instance;

	/** ID of this Mod */
	public static final String MODID = "basegems";

	/** Display name of this Mod */
	public static final String NAME = "Base Gems";

	/**
	 * Version number, in Major.Minor.Build format. The minor number is
	 * increased whenever a change is made that has the potential to break
	 * compatibility with other mods that depend on this one.
	 */
	public static final String VERSION = "0.12.0-beta1";

	public static final String UPDATEJSON = "https://raw.githubusercontent.com/MinecraftModDevelopment/BaseGems/master/update.json";

	@SidedProxy(clientSide = "basegems.proxy.ClientProxy", serverSide = "basegems.proxy.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	@EventHandler
	public void onRemap(FMLMissingMappingsEvent event) {
		proxy.onRemap(event);
	}
}
