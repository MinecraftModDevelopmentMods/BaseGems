package com.mcmoddev.basegems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcmoddev.basegems.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This is the entry point for this Mod.
 *
 * @author Jasmine Iwanek
 *
 */
@Mod(
		modid = BaseGems.MODID,
		name = BaseGems.NAME,
		version = BaseGems.VERSION,
		dependencies = "required-after:forge@[14.21.1.2443,);required-after:basemetals",
		acceptedMinecraftVersions = "[1.12,)",
		certificateFingerprint = "@FINGERPRINT@",
		updateJSON = BaseGems.UPDATEJSON)
public class BaseGems {

	@Instance
	public static BaseGems instance;

	/** ID of this Mod */
	public static final String MODID = "basegems";

	/** Display name of this Mod */
	static final String NAME = "Base Gems";

	/**
	 * Version number, in Major.Minor.Patch format. The minor number is
	 * increased whenever a change is made that has the potential to break
	 * compatibility with other mods that depend on this one.
	 */
	public static final String VERSION = "2.5.0-beta3";

	static final String UPDATEJSON = "https://raw.githubusercontent.com/MinecraftModDevelopmentMods/BaseGems/master/update.json";

	private static final String PROXY_BASE = "com.mcmoddev." + MODID + ".proxy.";

	@SidedProxy(clientSide = PROXY_BASE + "ClientProxy", serverSide = PROXY_BASE + "ServerProxy")
	public static CommonProxy proxy;

	public static final Logger logger = LogManager.getFormatterLogger(BaseGems.MODID);

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		logger.warn("Invalid fingerprint detected!");
	}

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);

		MinecraftForge.EVENT_BUS.register(com.mcmoddev.basegems.init.Items.class);
		MinecraftForge.EVENT_BUS.register(com.mcmoddev.basegems.init.Blocks.class);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	@SubscribeEvent
	public void onRemapBlock(RegistryEvent.MissingMappings<Block> event) {
		proxy.onRemapBlock(event);
	}
	
	@SubscribeEvent
	public void onRemapItem(RegistryEvent.MissingMappings<Item> event) {
		proxy.onRemapItem(event);
	}
}
