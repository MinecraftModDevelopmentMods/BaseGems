package zone.moddev.mc.basegems.config;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import zone.moddev.mc.basegems.data.Gem;

/** Registry names always exist. These options control new gameplay and recipes. */
public final class GemConfig {
    public static final ForgeConfigSpec SPEC;
    private static final ForgeConfigSpec.ConfigValue<String> MODE;
    private static final Map<String, ForgeConfigSpec.BooleanValue> ENABLED = new LinkedHashMap<>();
    private static volatile ModConfig loaded;
    private static volatile String activeMode = "high_fantasy";
    private static volatile Map<String, Boolean> activeGems = java.util.Collections.emptyMap();
    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        MODE = builder.comment("Choose high_fantasy or low_fantasy and restart Minecraft.")
                .worldRestart().define("general.contentMode", "high_fantasy",
                        value -> "high_fantasy".equals(value) || "low_fantasy".equals(value));
        for (Gem gem : Gem.ALL) {
            ENABLED.put(gem.name(), builder.comment("Enable new deposits, recipes and trades for " + gem.name() + ".")
                    .worldRestart().define("gems." + gem.configKey(), true));
        }
        SPEC = builder.build();
    }

    private GemConfig() {}

    public static void onConfigLoading(ModConfig.Loading event) {
        if (event.getConfig().getSpec() != SPEC) return;
        activeMode = MODE.get();
        Map<String, Boolean> options = new LinkedHashMap<>();
        ENABLED.forEach((gem, value) -> options.put(gem, value.get()));
        activeGems = java.util.Collections.unmodifiableMap(options);
        loaded = event.getConfig();
    }

    public static boolean enabled(String gem) {
        return activeGems.getOrDefault(gem, true);
    }

    public static String configuredMode() { return loaded == null ? activeMode : MODE.get(); }
    public static boolean configuredGem(String gem) { return loaded == null || ENABLED.get(gem).get(); }

    public static void save(String mode, Map<String, Boolean> gems) {
        ModConfig config = loaded;
        if (config == null) return;
        config.getConfigData().set(MODE.getPath(), mode);
        gems.forEach((gem, value) -> config.getConfigData().set(ENABLED.get(gem).getPath(), value));
        config.save();
    }

    public static boolean allows(String path) {
        int separator = path.indexOf('_');
        if (separator < 0) return enabled(path);
        if (!enabled(path.substring(0, separator))) return false;
        if (!"low_fantasy".equals(activeMode)) return true;
        switch (path.substring(separator + 1)) {
            case "ore":
            case "ingot":
            case "nugget":
            case "powder":
            case "smallpowder":
            case "plate":
            case "block":
                return true;
            default:
                return false;
        }
    }
}
