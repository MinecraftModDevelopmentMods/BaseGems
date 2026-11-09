package zone.moddev.mc.basegems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zone.moddev.mc.basegems.proxy.CommonProxy;
import zone.moddev.mc.basegems.util.ContentPolicy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
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
		dependencies = "required-after:forge@[13.20.1.2588,);required-after:basemetals@[2.5.1.111021,2.6.0);required-after:orespawn@[4.1.6.111021,5.0.0);before:buildingbricks",
		acceptedMinecraftVersions = "[1.11.2]",
		guiFactory = "zone.moddev.mc.basegems.client.BaseGemsGuiFactory")
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
	public static final String VERSION = "2.5.0.111021";

	private static final String PROXY_BASE = "zone.moddev.mc." + MODID + ".proxy.";

	@SidedProxy(clientSide = PROXY_BASE + "ClientProxy", serverSide = PROXY_BASE + "ServerProxy")
	public static CommonProxy proxy;

	public static final Logger logger = LogManager.getFormatterLogger(BaseGems.MODID);

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

	@EventHandler
	public static void loadComplete(FMLLoadCompleteEvent event) {
		ContentPolicy.filterRecipes();
	}

	@EventHandler
	public static void onRemap(FMLMissingMappingsEvent event) {
		proxy.onRemap(event);
	}
}
