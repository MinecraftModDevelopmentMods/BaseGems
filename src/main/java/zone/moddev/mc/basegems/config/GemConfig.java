package zone.moddev.mc.basegems.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import zone.moddev.mc.basegems.data.Gem;

/** Registry names always exist. These options control new gameplay and recipes. */
public final class GemConfig {
    public static final String HIGH_FANTASY = "high_fantasy";
    public static final String LOW_FANTASY = "low_fantasy";
    public static final ModConfigSpec SPEC;
    private static final ModConfigSpec.ConfigValue<String> MODE;
    private static final Map<String, ModConfigSpec.BooleanValue> ENABLED = new LinkedHashMap<>();
    private static volatile boolean loaded;
    private static volatile String activeMode = HIGH_FANTASY;
    private static volatile Map<String, Boolean> activeGems = Collections.emptyMap();

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        MODE = builder.comment("Choose high_fantasy or low_fantasy and restart Minecraft.")
                .worldRestart().define("general.contentMode", HIGH_FANTASY,
                        value -> HIGH_FANTASY.equals(value) || LOW_FANTASY.equals(value));
        for (Gem gem : Gem.ALL) {
            ENABLED.put(gem.name(), builder.comment("Enable new deposits, recipes and trades for " + gem.name() + ".")
                    .worldRestart().define("gems." + gem.configKey(), true));
        }
        SPEC = builder.build();
    }

    private GemConfig() {}

    /** Takes the settings once; later edits apply after a restart. */
    public static void onConfigLoading(ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() != SPEC) return;
        activeMode = MODE.get();
        Map<String, Boolean> options = new LinkedHashMap<>();
        ENABLED.forEach((gem, value) -> options.put(gem, value.get()));
        activeGems = Collections.unmodifiableMap(options);
        loaded = true;
    }

    public static boolean enabled(String gem) {
        return activeGems.getOrDefault(gem, true);
    }

    public static String configuredMode() { return loaded ? MODE.get() : activeMode; }
    public static boolean configuredGem(String gem) { return !loaded || ENABLED.get(gem).get(); }

    /** Saves edits from the config screen; they apply after a restart. */
    public static void save(String mode, Map<String, Boolean> gems) {
        if (!loaded) return;
        MODE.set(mode);
        gems.forEach((gem, value) -> ENABLED.get(gem).set(value));
        SPEC.save();
    }

    /** Whether an item's path, such as {@code ruby_pickaxe}, may be obtained in the active mode. */
    public static boolean allows(String path) {
        if (path.startsWith("flowing_")) path = path.substring("flowing_".length());
        int separator = path.indexOf('_');
        if (separator < 0) return enabled(path);
        if (!enabled(path.substring(0, separator))) return false;
        if (!LOW_FANTASY.equals(activeMode)) return true;
        return switch (path.substring(separator + 1)) {
            case "ore", "ingot", "nugget", "powder", "smallpowder", "plate", "block" -> true;
            default -> false;
        };
    }

    /** Registers the spec with Base Gems' container. */
    public static void register(net.neoforged.fml.ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, SPEC);
    }
}
