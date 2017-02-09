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

	private static Configuration configuration;
	private static final String CONFIG_FILE = "config/BaseGems.cfg";
	private static final String MATERIALS_CAT = "Gems";
	private static final String ALT_CFG_PATH = "config/additional-loot-tables";
	private static final String ORESPAWN_CFG_PATH = "config/orespawn";

	@SubscribeEvent
	public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.getModID().equals(BaseGems.MODID)) {
			init();
		}
	}

	public static void init() {
		if (configuration == null) {
			configuration = new Configuration(new File(CONFIG_FILE));
			MinecraftForge.EVENT_BUS.register(new Config());
		}

		// METALS
		Options.ENABLE_AGATE = configuration.getBoolean("EnableAgate", MATERIALS_CAT, true, "Enable Agate Items and Materials");

		if (configuration.hasChanged()) {
			configuration.save();
		}

		if (com.mcmoddev.basemetals.util.Config.Options.requireMMDOreSpawn) {
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

		final Path myLootFolder = Paths.get(ALT_CFG_PATH, BaseGems.MODID);
		if (Files.notExists(myLootFolder)) {
			try {
				final String chests = "chests";
				Files.createDirectories(myLootFolder.resolve(chests));
				Files.write(myLootFolder.resolve(chests).resolve("abandoned_mineshaft.json"), Collections.singletonList(AdditionalLootTables.ABANDONED_MINESHAFT));
				Files.write(myLootFolder.resolve(chests).resolve("desert_pyramid.json"), Collections.singletonList(AdditionalLootTables.DESERT_PYRAMID));
				Files.write(myLootFolder.resolve(chests).resolve("end_city_treasure.json"), Collections.singletonList(AdditionalLootTables.END_CITY_TREASURE));
				Files.write(myLootFolder.resolve(chests).resolve("jungle_temple.json"), Collections.singletonList(AdditionalLootTables.JUNGLE_TEMPLE));
				Files.write(myLootFolder.resolve(chests).resolve("nether_bridge.json"), Collections.singletonList(AdditionalLootTables.NETHER_BRIDGE));
				Files.write(myLootFolder.resolve(chests).resolve("simple_dungeon.json"), Collections.singletonList(AdditionalLootTables.SIMPLE_DUNGEON));
				Files.write(myLootFolder.resolve(chests).resolve("spawn_bonus_chest.json"), Collections.singletonList(AdditionalLootTables.SPAWN_BONUS_CHEST));
				Files.write(myLootFolder.resolve(chests).resolve("stronghold_corridor.json"), Collections.singletonList(AdditionalLootTables.STRONGHOLD_CORRIDOR));
				Files.write(myLootFolder.resolve(chests).resolve("stronghold_crossing.json"), Collections.singletonList(AdditionalLootTables.STRONGHOLD_CROSSING));
				Files.write(myLootFolder.resolve(chests).resolve("village_blacksmith.json"), Collections.singletonList(AdditionalLootTables.VILLAGE_BLACKSMITH));
			} catch (final IOException ex) {
				FMLLog.log(Level.ERROR, ex, "%s: Failed to extract additional loot tables", Loader.instance().activeModContainer().getModId());
			}
		}
	}

	public static class Options {

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

		private Options() {
			throw new IllegalAccessError("Not a instantiable class");
		}
	}

	public static void postInit() {
		CrusherRecipeRegistry.getInstance().clearCache();
	}
}
