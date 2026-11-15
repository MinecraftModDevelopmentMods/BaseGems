package zone.moddev.mc.basegems.data;

import java.util.List;
import java.util.Objects;

import net.minecraft.resources.ResourceLocation;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.lib.MMDLib;
import zone.moddev.mc.lib.common.factories.MaterialFactory;
import zone.moddev.mc.lib.common.runtime.MaterialStats;
import zone.moddev.mc.lib.common.types.MMDMaterial;

/**
 * One of the 33 gems. Names and config keys are fixed here, because the config is
 * built before MMDLib loads; statistics come from the gem's MMDLib material in
 * {@code mmdlib/material/}.
 */
public record Gem(String name, String configKey) {
    public static final List<Gem> ALL = List.of(
            new Gem("agate", "EnableAgate"),
            new Gem("alexandrite", "EnableAlexandrite"),
            new Gem("amber", "EnableAmber"),
            new Gem("amethyst", "EnableAmethyst"),
            new Gem("ametrine", "EnableAmetrine"),
            new Gem("aquamarine", "EnableAquamarine"),
            new Gem("beryl", "EnableBeryl"),
            new Gem("blackdiamond", "EnableBlackDiamond"),
            new Gem("bluetopaz", "EnableBlueTopaz"),
            new Gem("carnelian", "EnableCarnelian"),
            new Gem("citrine", "EnableCitrine"),
            new Gem("garnet", "EnableGarnet"),
            new Gem("goldenberyl", "EnableGoldenBeryl"),
            new Gem("heliodor", "EnableHeliodor"),
            new Gem("indicolite", "EnableIndicolite"),
            new Gem("iolite", "EnableIolite"),
            new Gem("jade", "EnableJade"),
            new Gem("jasper", "EnableJasper"),
            new Gem("lepidolite", "EnableLepidolite"),
            new Gem("malachite", "EnableMalachite"),
            new Gem("moldavite", "EnableMoldavite"),
            new Gem("moonstone", "EnableMoonstone"),
            new Gem("morganite", "EnableMorganite"),
            new Gem("onyx", "EnableOnyx"),
            new Gem("opal", "EnableOpal"),
            new Gem("peridot", "EnablePeridot"),
            new Gem("ruby", "EnableRuby"),
            new Gem("sapphire", "EnableSapphire"),
            new Gem("spinel", "EnableSpinel"),
            new Gem("tanzanite", "EnableTanzanite"),
            new Gem("topaz", "EnableTopaz"),
            new Gem("turquoise", "EnableTurquoise"),
            new Gem("violetsapphire", "EnableVioletSapphire"));

    public Gem {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(configKey, "configKey");
    }

    public ResourceLocation id() {
        return ResourceLocation.fromNamespaceAndPath(BaseGems.MOD_ID, name);
    }

    /** The gem's MMDLib material; available once MMDLib has loaded definitions. */
    public MMDMaterial material() {
        MaterialFactory.Result result = MMDLib.loadedMaterials().get(id());
        if (result == null) {
            throw new IllegalStateException("MMDLib has not loaded the material " + id());
        }
        return result.material();
    }

    public double hardness() { return material().properties().hardness(); }
    public double strength() { return material().properties().strength(); }
    public double magic() { return material().properties().magic(); }
    public int colour() { return material().properties().color(); }
    public int toolLevel() { return (int) (hardness() / 3); }
    public float baseAttackDamage() { return new MaterialStats(material()).baseAttackDamage(); }
    public double equipmentTradeValue() { return hardness() + strength() + magic() + toolLevel(); }
}
