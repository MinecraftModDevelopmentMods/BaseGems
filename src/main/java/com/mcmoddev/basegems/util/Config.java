package com.mcmoddev.basegems.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.data.AdditionalLootTables;
import com.mcmoddev.basegems.data.DataConstants;
import com.mcmoddev.lib.registry.CrusherRecipeRegistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MissingModsException;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;

/**
 * @author Jasmine Iwanek
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
		Options.enableAgate = configuration.getBoolean("EnableAgate", MATERIALS_CAT, true,
				"Enable Agate Items and Materials");
		Options.enableAlexandrite = configuration.getBoolean("EnableAlexandrite", MATERIALS_CAT, true,
				"Enable Alexandrite Items and Materials");
		Options.enableAmber = configuration.getBoolean("EnableAmber", MATERIALS_CAT, true,
				"Enable Amber Items and Materials");
		Options.enableAmethyst = configuration.getBoolean("EnableAmethyst", MATERIALS_CAT, true,
				"Enable Amethyst Items and Materials");
		Options.enableAmetrine = configuration.getBoolean("EnableAmetrine", MATERIALS_CAT, true,
				"Enable Ametrine Items and Materials");
		Options.enableAquamarine = configuration.getBoolean("EnableAquamarine", MATERIALS_CAT, true,
				"Enable Aquamarine Items and Materials");
		Options.enableBeryl = configuration.getBoolean("EnableBeryl", MATERIALS_CAT, true,
				"Enable Beryl Items and Materials");
		Options.enableBlackDiamond = configuration.getBoolean("EnableBlackDiamond", MATERIALS_CAT, true,
				"Enable Black Diamond Items and Materials");
		Options.enableBlueTopaz = configuration.getBoolean("EnableBlueTopaz", MATERIALS_CAT, true,
				"Enable Blue Topaz Items and Materials");
		Options.enableCarnelian = configuration.getBoolean("EnableCarnelian", MATERIALS_CAT, true,
				"Enable Carnelian Items and Materials");
		Options.enableCitrine = configuration.getBoolean("EnableCitrine", MATERIALS_CAT, true,
				"Enable Citrine Items and Materials");
		Options.enableGarnet = configuration.getBoolean("EnableGarnet", MATERIALS_CAT, true,
				"Enable Garnet Items and Materials");
		Options.enableGoldenBeryl = configuration.getBoolean("EnableGoldenBeryl", MATERIALS_CAT, true,
				"Enable Golden Beryl Items and Materials");
		Options.enableHeliodor = configuration.getBoolean("EnableHeliodor", MATERIALS_CAT, true,
				"Enable Heliodor Items and Materials");
		Options.enableIndicolite = configuration.getBoolean("EnableIndicolite", MATERIALS_CAT, true,
				"Enable Indicolite Items and Materials");
		Options.enableIolite = configuration.getBoolean("EnableIolite", MATERIALS_CAT, true,
				"Enable Iolite Items and Materials");
		Options.enableJade = configuration.getBoolean("EnableJade", MATERIALS_CAT, true,
				"Enable Jade Items and Materials");
		Options.enableJasper = configuration.getBoolean("EnableJasper", MATERIALS_CAT, true,
				"Enable Jasper Items and Materials");
		Options.enableLepidolite = configuration.getBoolean("EnableLepidolite", MATERIALS_CAT, true,
				"Enable Lepidolite Items and Materials");
		Options.enableMalachite = configuration.getBoolean("EnableMalachite", MATERIALS_CAT, true,
				"Enable Malachite Items and Materials");
		Options.enableMoldavite = configuration.getBoolean("EnableMoldovite", MATERIALS_CAT, true,
				"Enable Moldavite Items and Materials");
		Options.enableMoonstone = configuration.getBoolean("EnableMoonstone", MATERIALS_CAT, true,
				"Enable Moonstone Items and Materials");
		Options.enableMorganite = configuration.getBoolean("EnableMorganite", MATERIALS_CAT, true,
				"Enable Morganite Items and Materials");
		Options.enableOnyx = configuration.getBoolean("EnableOnyx", MATERIALS_CAT, true,
				"Enable Onyx Items and Materials");
		Options.enableOpal = configuration.getBoolean("EnableOpal", MATERIALS_CAT, true,
				"Enable Opal Items and Materials");
		Options.enablePeridot = configuration.getBoolean("EnablePeridot", MATERIALS_CAT, true,
				"Enable Peridot Items and Materials");
		Options.enableRuby = configuration.getBoolean("EnableRuby", MATERIALS_CAT, true,
				"Enable Ruby Items and Materials");
		Options.enableSapphire = configuration.getBoolean("EnableSapphire", MATERIALS_CAT, true,
				"Enable Sapphire Items and Materials");
		Options.enableSpinel = configuration.getBoolean("EnableSpinel", MATERIALS_CAT, true,
				"Enable Spinel Items and Materials");
		Options.enableTanzanite = configuration.getBoolean("EnableTanzanite", MATERIALS_CAT, true,
				"Enable Tanzanite Items and Materials");
		Options.enableTopaz = configuration.getBoolean("EnableTopaz", MATERIALS_CAT, true,
				"Enable Topaz Items and Materials");
		Options.enableTurquoise = configuration.getBoolean("EnableTurquoise", MATERIALS_CAT, true,
				"Enable Turquoise Items and Materials");
		Options.enableVioletSapphire = configuration.getBoolean("EnableVioletSapphire", MATERIALS_CAT, true,
				"Enable Violet Sapphire Items and Materials");

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
			if (!(oreSpawnFile.toFile().exists())) {
				try {
					Files.createDirectories(oreSpawnFile.getParent());
					Files.write(oreSpawnFile, Arrays.asList(DataConstants.DEFAULT_ORESPAWN_JSON.split("\n")),
							Charset.forName("UTF-8"));
				} catch (final IOException ex) {
					BaseGems.logger.error("Failed to write file " + oreSpawnFile, ex);
				}
			}
		}

		final Path myLootFolder = Paths.get(ALT_CFG_PATH, BaseGems.MODID);
		if (!(myLootFolder.toFile().exists())) {
			try {
				final String chests = "chests";
				Files.createDirectories(myLootFolder.resolve(chests));
				Files.write(myLootFolder.resolve(chests).resolve("abandoned_mineshaft.json"),
						Collections.singletonList(AdditionalLootTables.ABANDONED_MINESHAFT));
				Files.write(myLootFolder.resolve(chests).resolve("desert_pyramid.json"),
						Collections.singletonList(AdditionalLootTables.DESERT_PYRAMID));
				Files.write(myLootFolder.resolve(chests).resolve("end_city_treasure.json"),
						Collections.singletonList(AdditionalLootTables.END_CITY_TREASURE));
				Files.write(myLootFolder.resolve(chests).resolve("jungle_temple.json"),
						Collections.singletonList(AdditionalLootTables.JUNGLE_TEMPLE));
				Files.write(myLootFolder.resolve(chests).resolve("nether_bridge.json"),
						Collections.singletonList(AdditionalLootTables.NETHER_BRIDGE));
				Files.write(myLootFolder.resolve(chests).resolve("simple_dungeon.json"),
						Collections.singletonList(AdditionalLootTables.SIMPLE_DUNGEON));
				Files.write(myLootFolder.resolve(chests).resolve("spawn_bonus_chest.json"),
						Collections.singletonList(AdditionalLootTables.SPAWN_BONUS_CHEST));
				Files.write(myLootFolder.resolve(chests).resolve("stronghold_corridor.json"),
						Collections.singletonList(AdditionalLootTables.STRONGHOLD_CORRIDOR));
				Files.write(myLootFolder.resolve(chests).resolve("stronghold_crossing.json"),
						Collections.singletonList(AdditionalLootTables.STRONGHOLD_CROSSING));
				Files.write(myLootFolder.resolve(chests).resolve("village_blacksmith.json"),
						Collections.singletonList(AdditionalLootTables.VILLAGE_BLACKSMITH));
			} catch (final IOException ex) {
				BaseGems.logger.error("Failed to extract additional loot tables", ex);
			}
		}
	}

	public static class Options {

		// MATERIALS
		public static boolean enableAgate = true;
		public static boolean enableAlexandrite = true;
		public static boolean enableAmber = true;
		public static boolean enableAmethyst = true;
		public static boolean enableAmetrine = true;
		public static boolean enableAquamarine = true;
		public static boolean enableBeryl = true;
		public static boolean enableBlackDiamond = true;
		public static boolean enableBlueTopaz = true;
		public static boolean enableCarnelian = true;
		public static boolean enableCitrine = true;
		public static boolean enableGarnet = true;
		public static boolean enableGoldenBeryl = true;
		public static boolean enableHeliodor = true;
		public static boolean enableIndicolite = true;
		public static boolean enableIolite = true;
		public static boolean enableJade = true;
		public static boolean enableJasper = true;
		public static boolean enableLepidolite = true;
		public static boolean enableMalachite = true;
		public static boolean enableMoldavite = true;
		public static boolean enableMoonstone = true;
		public static boolean enableMorganite = true;
		public static boolean enableOnyx = true;
		public static boolean enableOpal = true;
		public static boolean enablePeridot = true;
		public static boolean enableRuby = true;
		public static boolean enableSapphire = true;
		public static boolean enableSpinel = true;
		public static boolean enableTanzanite = true;
		public static boolean enableTopaz = true;
		public static boolean enableTurquoise = true;
		public static boolean enableVioletSapphire = true;

		// VANILLA
		public static boolean enableQuartz = true;
		public static boolean enableEmerald = true;
		public static boolean enableDiamond = true;

		private Options() {
			throw new IllegalAccessError("Not a instantiable class");
		}
	}

	public static void postInit() {
		CrusherRecipeRegistry.getInstance().clearCache();
	}
}
