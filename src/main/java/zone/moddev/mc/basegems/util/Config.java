package zone.moddev.mc.basegems.util;

import java.io.File;
import java.util.Locale;

import com.mcmoddev.lib.registry.CrusherRecipeRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zone.moddev.mc.basegems.BaseGems;

/**
 * @author Jasmine Iwanek
 *
 */
public class Config {

	private static Configuration configuration;
	private static final String CONFIG_FILE = "config/BaseGems.cfg";
	private static final String HIGH_FANTASY = "high_fantasy";
	private static final String LOW_FANTASY = "low_fantasy";
	public static final String GENERAL_CAT = "General";
	public static final String MATERIALS_CAT = "Gems";
	private static boolean lowFantasy;

	@SubscribeEvent
	public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.getModID().equals(BaseGems.MODID) && configuration.hasChanged()) {
			configuration.save();
		}
	}

	public static void init() {
		if (configuration == null) {
			configuration = new Configuration(new File(CONFIG_FILE));
			MinecraftForge.EVENT_BUS.register(new Config());
			Property mode = configuration.get(GENERAL_CAT, "contentMode", HIGH_FANTASY,
					"Fantasy level for Base Gems crafting. Requires a game restart.");
			mode.setValidValues(new String[] { HIGH_FANTASY, LOW_FANTASY });
			mode.setLanguageKey("config.basegems.content_mode");
			mode.setRequiresMcRestart(true);
			lowFantasy = LOW_FANTASY.equals(mode.getString().trim().toLowerCase(Locale.ROOT));
			mode.set(lowFantasy ? LOW_FANTASY : HIGH_FANTASY);
		}

		// GEMS
		Options.enableAgate = gemEnabled("EnableAgate", "Enable Agate Items and Materials");
		Options.enableAlexandrite = gemEnabled("EnableAlexandrite", "Enable Alexandrite Items and Materials");
		Options.enableAmber = gemEnabled("EnableAmber", "Enable Amber Items and Materials");
		Options.enableAmethyst = gemEnabled("EnableAmethyst", "Enable Amethyst Items and Materials");
		Options.enableAmetrine = gemEnabled("EnableAmetrine", "Enable Ametrine Items and Materials");
		Options.enableAquamarine = gemEnabled("EnableAquamarine", "Enable Aquamarine Items and Materials");
		Options.enableBeryl = gemEnabled("EnableBeryl", "Enable Beryl Items and Materials");
		Options.enableBlackDiamond = gemEnabled("EnableBlackDiamond", "Enable Black Diamond Items and Materials");
		Options.enableBlueTopaz = gemEnabled("EnableBlueTopaz", "Enable Blue Topaz Items and Materials");
		Options.enableCarnelian = gemEnabled("EnableCarnelian", "Enable Carnelian Items and Materials");
		Options.enableCitrine = gemEnabled("EnableCitrine", "Enable Citrine Items and Materials");
		Options.enableGarnet = gemEnabled("EnableGarnet", "Enable Garnet Items and Materials");
		Options.enableGoldenBeryl = gemEnabled("EnableGoldenBeryl", "Enable Golden Beryl Items and Materials");
		Options.enableHeliodor = gemEnabled("EnableHeliodor", "Enable Heliodor Items and Materials");
		Options.enableIndicolite = gemEnabled("EnableIndicolite", "Enable Indicolite Items and Materials");
		Options.enableIolite = gemEnabled("EnableIolite", "Enable Iolite Items and Materials");
		Options.enableJade = gemEnabled("EnableJade", "Enable Jade Items and Materials");
		Options.enableJasper = gemEnabled("EnableJasper", "Enable Jasper Items and Materials");
		Options.enableLepidolite = gemEnabled("EnableLepidolite", "Enable Lepidolite Items and Materials");
		Options.enableMalachite = gemEnabled("EnableMalachite", "Enable Malachite Items and Materials");
		Options.enableMoldavite = gemEnabled("EnableMoldovite", "Enable Moldavite Items and Materials");
		Options.enableMoonstone = gemEnabled("EnableMoonstone", "Enable Moonstone Items and Materials");
		Options.enableMorganite = gemEnabled("EnableMorganite", "Enable Morganite Items and Materials");
		Options.enableOnyx = gemEnabled("EnableOnyx", "Enable Onyx Items and Materials");
		Options.enableOpal = gemEnabled("EnableOpal", "Enable Opal Items and Materials");
		Options.enablePeridot = gemEnabled("EnablePeridot", "Enable Peridot Items and Materials");
		Options.enableRuby = gemEnabled("EnableRuby", "Enable Ruby Items and Materials");
		Options.enableSapphire = gemEnabled("EnableSapphire", "Enable Sapphire Items and Materials");
		Options.enableSpinel = gemEnabled("EnableSpinel", "Enable Spinel Items and Materials");
		Options.enableTanzanite = gemEnabled("EnableTanzanite", "Enable Tanzanite Items and Materials");
		Options.enableTopaz = gemEnabled("EnableTopaz", "Enable Topaz Items and Materials");
		Options.enableTurquoise = gemEnabled("EnableTurquoise", "Enable Turquoise Items and Materials");
		Options.enableVioletSapphire = gemEnabled("EnableVioletSapphire", "Enable Violet Sapphire Items and Materials");

		if (configuration.hasChanged()) {
			configuration.save();
		}

	}

	private static boolean gemEnabled(final String key, final String comment) {
		return configuration.getBoolean(key, MATERIALS_CAT, true, comment, "config.basegems." + key);
	}

	public static Configuration getConfiguration() {
		return configuration;
	}

	public static boolean lowFantasy() {
		return lowFantasy;
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

		private Options() {
			throw new IllegalAccessError("Not a instantiable class");
		}
	}

	public static void postInit() {
		CrusherRecipeRegistry.getInstance().clearCache();
	}
}
