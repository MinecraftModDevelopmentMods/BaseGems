package zone.moddev.mc.basegems.worldgen;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.orespawn.api.GeologyFamily;
import zone.moddev.mc.orespawn.api.OreHeightDistribution;
import zone.moddev.mc.orespawn.api.OrePattern;
import zone.moddev.mc.orespawn.api.OreSpawnApi;
import zone.moddev.mc.orespawn.api.WorldgenProvider;

/** Overworld gem defaults. OreSpawn owns placement, overrides and saved profiles. */
public final class GemWorldgen {
    private static final ResourceLocation OVERWORLD = new ResourceLocation("minecraft", "overworld");
    private static final ResourceLocation STANDARD = new ResourceLocation("orespawn", "standard");
    private static final ResourceLocation STONE = new ResourceLocation("minecraft", "stone");
    private static final ResourceLocation GRAVEL = new ResourceLocation("minecraft", "gravel");
    private static final ResourceLocation DIRT = new ResourceLocation("minecraft", "dirt");
    private static final ResourceLocation CLAY = new ResourceLocation("minecraft", "clay");
    private static final ResourceLocation SAND = new ResourceLocation("minecraft", "sand");

    // Name, setting, inclusive Y range, attempts per chunk, inclusive block budget.
    private static final Gem[] GEMS = {
        new Gem("agate", Setting.CHALCEDONY, 28, 74, 0.46, 2, 4),
        new Gem("jasper", Setting.CHALCEDONY, 26, 70, 0.38, 2, 4),
        new Gem("carnelian", Setting.CHALCEDONY, 34, 76, 0.30, 1, 3),
        new Gem("onyx", Setting.CHALCEDONY, 18, 56, 0.24, 1, 3),
        new Gem("amethyst", Setting.QUARTZ_VEIN, 16, 54, 0.40, 2, 4),
        new Gem("citrine", Setting.QUARTZ_VEIN, 18, 56, 0.26, 1, 3),
        new Gem("ametrine", Setting.QUARTZ_VEIN, 14, 43, 0.085, 1, 2),

        new Gem("amber", Setting.AMBER, 44, 75, 0.55, 1, 3),
        new Gem("moldavite", Setting.MOLDAVITE, 48, 73, 0.26, 1, 2),
        new Gem("malachite", Setting.WEATHERED, 33, 76, 0.24, 2, 4),
        new Gem("turquoise", Setting.WEATHERED, 35, 79, 0.20, 1, 3),
        new Gem("opal", Setting.WEATHERED, 29, 70, 0.18, 1, 3),

        new Gem("beryl", Setting.PEGMATITE, 19, 57, 0.28, 1, 3),
        new Gem("aquamarine", Setting.PEGMATITE, 17, 53, 0.18, 1, 3),
        new Gem("goldenberyl", Setting.PEGMATITE, 16, 48, 0.14, 1, 2),
        new Gem("heliodor", Setting.PEGMATITE, 13, 44, 0.12, 1, 2),
        new Gem("morganite", Setting.PEGMATITE, 12, 42, 0.085, 1, 2),
        new Gem("indicolite", Setting.PEGMATITE, 14, 46, 0.11, 1, 2),
        new Gem("lepidolite", Setting.PEGMATITE, 23, 62, 0.25, 2, 4),
        new Gem("moonstone", Setting.PEGMATITE, 24, 64, 0.20, 1, 3),
        new Gem("topaz", Setting.PEGMATITE, 15, 48, 0.17, 1, 3),
        new Gem("bluetopaz", Setting.PEGMATITE, 12, 41, 0.085, 1, 2),

        new Gem("garnet", Setting.METAMORPHIC, 14, 52, 0.28, 2, 4),
        new Gem("jade", Setting.METAMORPHIC, 17, 55, 0.19, 1, 3),
        new Gem("iolite", Setting.METAMORPHIC, 12, 43, 0.13, 1, 2),
        new Gem("alexandrite", Setting.METAMORPHIC, 8, 28, 0.055, 1, 2),
        new Gem("spinel", Setting.METAMORPHIC, 8, 36, 0.11, 1, 2),
        new Gem("tanzanite", Setting.METAMORPHIC, 10, 34, 0.045, 1, 2),
        new Gem("ruby", Setting.METAMORPHIC, 6, 26, 0.095, 1, 2),
        new Gem("sapphire", Setting.VOLCANIC, 7, 33, 0.11, 1, 2),
        new Gem("violetsapphire", Setting.VOLCANIC, 6, 25, 0.045, 1, 2),
        new Gem("peridot", Setting.VOLCANIC, 9, 39, 0.18, 1, 3),
        new Gem("blackdiamond", Setting.DEEP, 5, 16, 0.035, 1, 1)
    };

    private GemWorldgen() { }

    public static void enqueue() {
        WorldgenProvider.Builder provider = WorldgenProvider.builder(BaseGems.MODID, 1);
        int count = 0;
        for (Gem gem : GEMS) {
            ResourceLocation block = new ResourceLocation(BaseGems.MODID, gem.name + "_ore");
            // The legacy material toggles and Base Metals' ore switch can omit the block.
            if (!ForgeRegistries.BLOCKS.containsKey(block)) {
                continue;
            }
            ResourceLocation rule = new ResourceLocation(BaseGems.MODID, "ore/" + gem.name);
            provider.ore(WorldgenProvider.OreDefinition.builder(rule, block)
                    .material(new ResourceLocation("orespawn", gem.name))
                    .retrogen(false)
                    .dimension(OVERWORLD, gem::configure)
                    .build());
            count++;
        }
        if (count > 0 && !OreSpawnApi.enqueue(provider.build())) {
            throw new IllegalStateException("Base Gems OreSpawn provider already registered");
        }
    }

    private enum Setting {
        CHALCEDONY(OrePattern.CLUSTERS, OreHeightDistribution.TRIANGLE, 3, 1, 0.10,
                GeologyFamily.IGNEOUS_VOLCANIC, GeologyFamily.SEDIMENTARY),
        QUARTZ_VEIN(OrePattern.VEIN, OreHeightDistribution.TRIANGLE, 4, 2, 0.15,
                GeologyFamily.IGNEOUS_VOLCANIC, GeologyFamily.IGNEOUS_INTRUSIVE),
        AMBER(OrePattern.CLUSTERS, OreHeightDistribution.TRIANGLE, 3, 0, 0.00),
        MOLDAVITE(OrePattern.CLUSTERS, OreHeightDistribution.TRIANGLE, 2, 0, 0.00),
        WEATHERED(OrePattern.CLUSTERS, OreHeightDistribution.TRIANGLE, 3, 0, 0.10,
                GeologyFamily.SEDIMENTARY),
        PEGMATITE(OrePattern.VEIN, OreHeightDistribution.TRIANGLE, 4, 2, 0.25,
                GeologyFamily.IGNEOUS_INTRUSIVE),
        METAMORPHIC(OrePattern.CLUSTERS, OreHeightDistribution.BOTTOM_TRIANGLE, 2, 1, 0.35,
                GeologyFamily.METAMORPHIC),
        VOLCANIC(OrePattern.CLUSTERS, OreHeightDistribution.BOTTOM_TRIANGLE, 2, 1, 0.35,
                GeologyFamily.IGNEOUS_VOLCANIC),
        DEEP(OrePattern.CLUSTERS, OreHeightDistribution.BOTTOM_TRIANGLE, 1, 0, 0.60,
                GeologyFamily.IGNEOUS_VOLCANIC);

        final OrePattern pattern;
        final OreHeightDistribution height;
        final int horizontal;
        final int vertical;
        final double airDiscard;
        final GeologyFamily[] families;

        Setting(OrePattern pattern, OreHeightDistribution height, int horizontal, int vertical,
                double airDiscard, GeologyFamily... families) {
            this.pattern = pattern;
            this.height = height;
            this.horizontal = horizontal;
            this.vertical = vertical;
            this.airDiscard = airDiscard;
            this.families = families;
        }

        void configure(WorldgenProvider.OreDimensionDefinition.Builder placement) {
            placement.pattern(pattern).heightDistribution(height).spread(horizontal, vertical)
                    .nodeSize(2).discardChanceOnAirExposure(airDiscard);
            if (this == AMBER) {
                placement.hostBlock(DIRT).hostBlock(CLAY).hostBlock(SAND).hostBlock(GRAVEL);
            } else if (this == MOLDAVITE) {
                placement.hostBlock(GRAVEL).hostBlock(DIRT, 0.5);
            } else {
                placement.hostBlock(STONE);
                for (GeologyFamily family : families) {
                    placement.hostFamily(family);
                }
            }
        }
    }

    private static final class Gem {
        final String name;
        final Setting setting;
        final int minY;
        final int maxY;
        final double attempts;
        final int minBlocks;
        final int maxBlocks;

        Gem(String name, Setting setting, int minY, int maxY, double attempts, int minBlocks, int maxBlocks) {
            this.name = name;
            this.setting = setting;
            this.minY = minY;
            this.maxY = maxY;
            this.attempts = attempts;
            this.minBlocks = minBlocks;
            this.maxBlocks = maxBlocks;
        }

        void configure(WorldgenProvider.OreDimensionDefinition.Builder placement) {
            placement.yRange(minY, maxY).attempts(attempts).quantityRange(minBlocks, maxBlocks)
                    .placementChannel(STANDARD);
            setting.configure(placement);
        }
    }
}
