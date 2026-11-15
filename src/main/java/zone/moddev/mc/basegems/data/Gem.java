package zone.moddev.mc.basegems.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** The gem names and physical properties used in the legacy releases. */
public final class Gem {
    public static final List<Gem> ALL = Collections.unmodifiableList(Arrays.asList(
            new Gem("agate", "EnableAgate", 6.5F, 8F, 4.5F, 0xFF179FD1),
            new Gem("alexandrite", "EnableAlexandrite", 8.5F, 8F, 4.5F, 0xFF747385),
            new Gem("amber", "EnableAmber", 2F, 8F, 4.5F, 0xFFF9C923),
            new Gem("amethyst", "EnableAmethyst", 7F, 8F, 4.5F, 0xFFA242E2),
            new Gem("ametrine", "EnableAmetrine", 7F, 8F, 4.5F, 0xFFC8A394),
            new Gem("aquamarine", "EnableAquamarine", 7.5F, 8F, 4.5F, 0xFF78BACA),
            new Gem("beryl", "EnableBeryl", 7.5F, 8F, 4.5F, 0xFF4EB86A),
            new Gem("blackdiamond", "EnableBlackDiamond", 10F, 15F, 4.5F, 0xFF000000),
            new Gem("bluetopaz", "EnableBlueTopaz", 8F, 8F, 4.5F, 0xFF01A4E9),
            new Gem("carnelian", "EnableCarnelian", 6.5F, 8F, 4.5F, 0xFFF03905),
            new Gem("citrine", "EnableCitrine", 7F, 8F, 4.5F, 0xFFFAD563),
            new Gem("garnet", "EnableGarnet", 6.5F, 8F, 4.5F, 0xFF9D0938),
            new Gem("goldenberyl", "EnableGoldenBeryl", 7.5F, 8F, 4.5F, 0xFFFDEE4C),
            new Gem("heliodor", "EnableHeliodor", 7.5F, 8F, 4.5F, 0xFFF9FD5D),
            new Gem("indicolite", "EnableIndicolite", 7F, 8F, 4.5F, 0xFF7EFBF5),
            new Gem("iolite", "EnableIolite", 7F, 8F, 4.5F, 0xFF605DBD),
            new Gem("jade", "EnableJade", 6.5F, 8F, 4.5F, 0xFF008127),
            new Gem("jasper", "EnableJasper", 7F, 8F, 4.5F, 0xFF9E0403),
            new Gem("lepidolite", "EnableLepidolite", 3F, 8F, 4.5F, 0xFFB980A0),
            new Gem("malachite", "EnableMalachite", 4F, 8F, 4.5F, 0xFF25674C),
            new Gem("moldavite", "EnableMoldavite", 5F, 8F, 4.5F, 0xFF68873B),
            new Gem("moonstone", "EnableMoonstone", 6F, 8F, 4.5F, 0xFF9FAED7),
            new Gem("morganite", "EnableMorganite", 7.5F, 8F, 4.5F, 0xFFF4D8CA),
            new Gem("onyx", "EnableOnyx", 7F, 8F, 4.5F, 0xFF000000),
            new Gem("opal", "EnableOpal", 6F, 8F, 4.5F, 0xFFC3CCE9),
            new Gem("peridot", "EnablePeridot", 6.5F, 8F, 4.5F, 0xFFB9E64F),
            new Gem("ruby", "EnableRuby", 9F, 8F, 4.5F, 0xFF7E0308),
            new Gem("sapphire", "EnableSapphire", 9F, 8F, 4.5F, 0xFF1036D5),
            new Gem("spinel", "EnableSpinel", 7.5F, 8F, 4.5F, 0xFFFE2B80),
            new Gem("tanzanite", "EnableTanzanite", 6F, 8F, 4.5F, 0xFF4A4AE0),
            new Gem("topaz", "EnableTopaz", 8F, 8F, 4.5F, 0xFFF9E838),
            new Gem("turquoise", "EnableTurquoise", 5F, 8F, 4.5F, 0xFF3FB2C4),
            new Gem("violetsapphire", "EnableVioletSapphire", 9F, 8F, 4.5F, 0xFF8247B7)
    ));

    private final String name;
    private final String configKey;
    private final float hardness;
    private final float strength;
    private final float magic;
    private final int colour;

    private Gem(String name, String configKey, float hardness, float strength, float magic, int colour) {
        this.name = name;
        this.configKey = configKey;
        this.hardness = hardness;
        this.strength = strength;
        this.magic = magic;
        this.colour = colour;
    }

    public String name() { return name; }
    public String configKey() { return configKey; }
    public int colour() { return colour; }
    public float hardness() { return hardness; }
    public int toolLevel() { return (int) (hardness / 3); }
    public int requiredHarvestLevel() { return Math.max(-1, Math.min(3, (int) (0.9F * hardness / 3))); }
    public int toolDurability() { return (int) (32 * strength); }
    public float toolEfficiency() { return hardness; }
    public float baseAttackDamage() { return Math.round(2.5F * hardness) / 10.0F; }
    public float equipmentTradeValue() { return hardness + strength + magic + toolLevel(); }
    public int enchantability() { return (int) (2.5F * magic); }
    public float axeAttackSpeed() { return -3.1F; }
    public int shieldDurability() { return toolDurability() * 2; }
    public int crackhammerDurability() { return toolDurability(); }
    public float crackhammerAttackDamage() { return baseAttackDamage() + 4; }
    public float crackhammerDestroySpeed() { return toolEfficiency(); }
    public String repairIngredientTag() { return "forge:gems/" + name; }
}
