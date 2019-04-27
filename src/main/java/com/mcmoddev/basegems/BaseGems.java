package com.mcmoddev.basegems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcmoddev.basegems.proxy.CommonProxy;
import com.mcmoddev.basegems.util.BGeConfig;
import com.mcmoddev.lib.data.SharedStrings;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
		dependencies = "required-after:forge@[14.21.1.2387,);required-after:mmdlib;before:buildingbricks",
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
	 * Version number, in Major.Minor.Build format. The minor number is
	 * increased whenever a change is made that has the potential to break
	 * compatibility with other mods that depend on this one.
	 */
	protected static final String VERSION = "2.5.0-rc1";

	protected static final String UPDATEJSON = SharedStrings.UPDATE_JSON_URL + "BaseGems/master/update.json";

	private static final String PROXY_BASE = SharedStrings.MMD_PROXY_GROUP + MODID + SharedStrings.DOT_PROXY_DOT;

	@SidedProxy(clientSide = PROXY_BASE + SharedStrings.CLIENTPROXY, serverSide = PROXY_BASE
		+ SharedStrings.SERVERPROXY)
	public static CommonProxy proxy;

	public static final Logger logger = LogManager.getLogger(BaseGems.MODID);

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		if (!(Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
			logger.warn(SharedStrings.INVALID_FINGERPRINT);
		}
	}

  	@EventHandler
	public static void constructing(final FMLConstructionEvent event) {
		BGeConfig.init();
	}

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
