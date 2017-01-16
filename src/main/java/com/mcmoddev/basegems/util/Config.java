package com.mcmoddev.basegems.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

import org.apache.logging.log4j.Level;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.data.AdditionalLootTables;
import com.mcmoddev.basegems.data.DataConstants;

import cyano.basemetals.registry.CrusherRecipeRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.versioning.*;

/**
 * @author p455w0rd
 *
 */
public class Config {

	public static Configuration CONFIG;
	private static final String CONFIG_FILE = "config/BaseGems.cfg";
//	private static final String GENERAL_CAT = "General";
//	private static final String INTEGRATION_CAT = "Mod Integration";
	private static final String MATERIALS_CAT = "Gems";
	private static final String ALT_CFG_PATH = "config/additional-loot-tables"; // + BaseGems.MODID;
	private static final String ORESPAWN_CFG_PATH = "config/orespawn";
//	private static List<String> USER_CRUSHER_RECIPES = new ArrayList<String>();

	@SubscribeEvent
	public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.getModID().equals(BaseGems.MODID)) {
			init();
		}
	}

	public static void init() {
		if (CONFIG == null) {
			CONFIG = new Configuration(new File(CONFIG_FILE));
			MinecraftForge.EVENT_BUS.register(new Config());
		}

		//GENERAL
		// enablePotionRecipes = config.getBoolean("enable_potions", "options",
		// enablePotionRecipes,
		// "If true, then some metals can be used to brew potions.");

		//GENERAL
		//Options.DISABLE_ALL_HAMMERS = CONFIG.getBoolean("disable_crack_hammer", GENERAL_CAT, false, "If true, then the crack hammer cannot be crafted.");
		//Options.ENFORCE_HARDNESS = CONFIG.getBoolean("enforce_hardness", GENERAL_CAT, true, "If true, then the crack hammer cannot crush ingots into powders if that \n" + "crackhammer is not hard enough to crush the ingot's ore.");
		//Options.STRONG_HAMMERS = CONFIG.getBoolean("strong_hammers", GENERAL_CAT, true, "If true, then the crack hammer can crush ingots/ores that a pickaxe of the same \n" + "material can harvest. If false, then your crack hammer must be made of a harder \n" + "material than the ore you are crushing.");
		//Options.AUTODETECT_RECIPES = CONFIG.getBoolean("automatic_recipes", GENERAL_CAT, true, "If true, then Base Metals will scan the Ore Dictionary to automatically add a \n" + "Crack Hammer recipe for every material that has an ore, dust, and ingot.");
		//Options.REQUIRE_ORESPAWN = CONFIG.getBoolean("using_orespawn", GENERAL_CAT, true, "If false, then Base Metals will not require DrCyano's Ore Spawn mod. \n" + "Set to false if using another mod to manually handle ore generation.");
		//Options.ENABLE_ACHIEVEMENTS = CONFIG.getBoolean("achievements", GENERAL_CAT, true, "If false, then Base Metals Achievements will be disabled (This is currently required if you disable any metals");

		// INTEGRATION
		//Options.ENABLE_ENDER_IO = CONFIG.getBoolean("ender_io_integration", INTEGRATION_CAT, true, "If false, then Base Metals will not try and integrate with Ender IO");
		//Options.ENABLE_IC2 = CONFIG.getBoolean("ic2_integration", INTEGRATION_CAT, true, "If false, then Base Metals will not try and integrate with IC2");
		//Options.ENABLE_MEKANISM = CONFIG.getBoolean("mekanism_integration", INTEGRATION_CAT, true, "If false, then Base Metals will not try and integrate with Mekanism");
		//Options.ENABLE_THAUMCRAFT = CONFIG.getBoolean("thaumcraft_integration", INTEGRATION_CAT, true, "If false, then Base Metals will not try and integrate with Thaumcraft");
		//Options.ENABLE_TINKERS_CONSTRUCT = CONFIG.getBoolean("tinkers_construct_integration", INTEGRATION_CAT, true, "If false, then Base Metals will not try and integrate with Tinkers Construct");
		//Options.ENABLE_VEINMINER = CONFIG.getBoolean("veinminer_integration", INTEGRATION_CAT, true, "If false, then Base Metals will not try and integrate with VeinMiner");

		// METALS
		Options.ENABLE_AGATE = CONFIG.getBoolean("EnableAgate", MATERIALS_CAT, true, "Enable Agate Items and Materials");

		if (CONFIG.hasChanged()) {
			CONFIG.save();
		}

		if (cyano.basemetals.util.Config.Options.REQUIRE_ORESPAWN) {
			if (!Loader.isModLoaded("orespawn")) {
				final HashSet<ArtifactVersion> orespawnMod = new HashSet<>();
				orespawnMod.add(new DefaultArtifactVersion("1.1.0"));
				throw new MissingModsException(orespawnMod, "orespawn", "MMD Ore Spawn Mod");
			}
			final Path oreSpawnFile = Paths.get(ORESPAWN_CFG_PATH, BaseGems.MODID + ".json");
			if (!(Files.exists(oreSpawnFile))) {
				try {
					Files.createDirectories(oreSpawnFile.getParent());
					Files.write(oreSpawnFile, Arrays.asList(DataConstants.DEFAULT_ORESPAWN_JSON.split("\n")), Charset.forName("UTF-8"));
				}
				catch (final IOException e) {
					FMLLog.severe(Loader.instance().activeModContainer().getModId() + ": Error: Failed to write file " + oreSpawnFile);
				}
			}
		}

//		final Path myLootFolder = Paths.get(ALT_CFG_PATH);
		final Path myLootFolder = Paths.get(ALT_CFG_PATH, BaseGems.MODID);
		if (Files.notExists(myLootFolder)) {
			try {
				Files.createDirectories(myLootFolder.resolve("chests"));
				Files.write(myLootFolder.resolve("chests").resolve("abandoned_mineshaft.json"), Collections.singletonList(AdditionalLootTables.abandoned_mineshaft));
				Files.write(myLootFolder.resolve("chests").resolve("desert_pyramid.json"), Collections.singletonList(AdditionalLootTables.desert_pyramid));
				Files.write(myLootFolder.resolve("chests").resolve("end_city_treasure.json"), Collections.singletonList(AdditionalLootTables.end_city_treasure));
				Files.write(myLootFolder.resolve("chests").resolve("jungle_temple.json"), Collections.singletonList(AdditionalLootTables.jungle_temple));
				Files.write(myLootFolder.resolve("chests").resolve("nether_bridge.json"), Collections.singletonList(AdditionalLootTables.nether_bridge));
				Files.write(myLootFolder.resolve("chests").resolve("simple_dungeon.json"), Collections.singletonList(AdditionalLootTables.simple_dungeon));
				Files.write(myLootFolder.resolve("chests").resolve("spawn_bonus_chest.json"), Collections.singletonList(AdditionalLootTables.spawn_bonus_chest));
				Files.write(myLootFolder.resolve("chests").resolve("stronghold_corridor.json"), Collections.singletonList(AdditionalLootTables.stronghold_corridor));
				Files.write(myLootFolder.resolve("chests").resolve("stronghold_crossing.json"), Collections.singletonList(AdditionalLootTables.stronghold_crossing));
				Files.write(myLootFolder.resolve("chests").resolve("village_blacksmith.json"), Collections.singletonList(AdditionalLootTables.village_blacksmith));
			} catch (final IOException ex) {
				FMLLog.log(Level.ERROR, ex, "%s: Failed to extract additional loot tables", Loader.instance().activeModContainer().getModId());
			}
		}
	}

	public static class Options {

		//GENERAL
//		public static boolean DISABLE_ALL_HAMMERS = false;
//		public static boolean ENFORCE_HARDNESS = true;
//		public static boolean STRONG_HAMMERS = true;
//		public static boolean AUTODETECT_RECIPES = true;
//		public static boolean REQUIRE_ORESPAWN = true;
//		public static boolean ENABLE_ACHIEVEMENTS = true;

		// INTEGRATION
//		public static boolean ENABLE_ENDER_IO = true;
//		public static boolean ENABLE_IC2 = true;
//		public static boolean ENABLE_MEKANISM = true;
//		public static boolean ENABLE_THAUMCRAFT = true;
//		public static boolean ENABLE_TINKERS_CONSTRUCT = true;
//		public static boolean ENABLE_VEINMINER = true;

		// MATERIALS
		public static boolean ENABLE_AGATE = true;
		public static boolean ENABLE_ALEXANDRITE = true;
		public static boolean ENABLE_AMBER = true;
		public static boolean ENABLE_AMETHYST = true;
		public static boolean ENABLE_AMETRINE = true;
		public static boolean ENABLE_AQUAMARINE = true;
		public static boolean ENABLE_BERYL = true;
		public static boolean ENABLE_BLACKDIAMOND = true;
		public static boolean ENABLE_BLUETOPAZ = true;
		public static boolean ENABLE_CARNELIAN = true;
		public static boolean ENABLE_CITRINE = true;
		public static boolean ENABLE_GARNET = true;
		public static boolean ENABLE_GOLDENBERYL = true;
		public static boolean ENABLE_HELIODOR = true;
		public static boolean ENABLE_INDICOLITE = true;
		public static boolean ENABLE_IOLITE = true;
		public static boolean ENABLE_JADE = true;
		public static boolean ENABLE_JASPER = true;
		public static boolean ENABLE_LEPIDOLITE = true;
		public static boolean ENABLE_MALACHITE = true;
		public static boolean ENABLE_MOLDAVITE = true;
		public static boolean ENABLE_MOONSTONE = true;
		public static boolean ENABLE_MORGANITE = true;
		public static boolean ENABLE_ONYX = true;
		public static boolean ENABLE_OPAL = true;
		public static boolean ENABLE_PERIDOT = true;
		public static boolean ENABLE_RUBY = true;
		public static boolean ENABLE_SAPPHIRE = true;
		public static boolean ENABLE_SPINEL = true;
		public static boolean ENABLE_TANZANITE = true;
		public static boolean ENABLE_TOPAZ = true;
		public static boolean ENABLE_TURQUOISE = true;
		public static boolean ENABLE_VIOLETSAPPHIRE = true;
	}

	public static void postInit() {
		CrusherRecipeRegistry.getInstance().clearCache();
	}

	/**
	 * Parses a String in the format (stack-size)*(modid):(item/block
	 * name)#(metadata value). The stacksize and metadata value parameters are
	 * optional.
	 *
	 * @param str
	 *            A String describing an itemstack (e.g. "4*minecraft:dye#15" or
	 *            "minecraft:bow")
	 * @param allowWildcard
	 *            If true, then item strings that do not specify a metadata
	 *            value will use the OreDictionary wildcard value. If false,
	 *            then the default meta value is 0 instead.
	 * @return An ItemStack representing the item, or null if the item is not
	 *         found
	 */
	/*
	public static ItemStack parseStringAsItemStack(String str, boolean allowWildcard) {
		str = str.trim();
		int count = 1;
		int meta;
		if (allowWildcard) {
			meta = OreDictionary.WILDCARD_VALUE;
		}
		else {
			meta = 0;
		}
		int nameStart = 0;
		int nameEnd = str.length();
		if (str.contains("*")) {
			count = Integer.parseInt(str.substring(0, str.indexOf('*')).trim());
			nameStart = str.indexOf('*') + 1;
		}
		if (str.contains("#")) {
			meta = Integer.parseInt(str.substring(str.indexOf('#') + 1, str.length()).trim());
			nameEnd = str.indexOf('#');
		}
		final String id = str.substring(nameStart, nameEnd).trim();
		if (Block.getBlockFromName(id) != null) {
			// is a block
			return new ItemStack(Block.getBlockFromName(id), count, meta);
		}
		else if (Item.getByNameOrId(id) != null) {
			// is an item
			return new ItemStack(Item.getByNameOrId(id), count, meta);
		}
		else {
			// item not found
			FMLLog.severe("Failed to find item or block for ID '" + id + "'");
			return null;
		}
	}
	*/
}
