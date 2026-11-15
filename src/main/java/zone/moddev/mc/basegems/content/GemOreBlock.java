package zone.moddev.mc.basegems.content;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.data.GemRarity;
import zone.moddev.mc.lib.api.BlockContext;
import zone.moddev.mc.lib.api.BlockImplementation;
import zone.moddev.mc.lib.api.MaterialExtensions;
import zone.moddev.mc.lib.common.types.MMDBlock;
import zone.moddev.mc.lib.common.types.MMDMaterial;

/**
 * Gem ore, made by the {@code basegems:ore} block form. Rarer gems give more experience;
 * the gem itself drops through the loot table.
 */
public final class GemOreBlock extends DropExperienceBlock {
    public GemOreBlock(GemRarity rarity, Properties properties) {
        super(UniformInt.of(rarity.minXp, rarity.maxXp), properties);
    }

    /** Must run before MMDLib decodes the gem materials that select {@code basegems:ore}. */
    public static void registerImplementation() {
        MaterialExtensions.GLOBAL.registerBlockImplementation(
                ResourceLocation.fromNamespaceAndPath(BaseGems.MOD_ID, "ore"), new Implementation());
    }

    private static final class Implementation implements BlockImplementation<GemOreBlock> {
        @Override
        public Class<GemOreBlock> blockClass() {
            return GemOreBlock.class;
        }

        @Override
        public GemOreBlock create(MMDMaterial material, MMDBlock definition) {
            throw new IllegalStateException("Created by the runtime registrar with a BlockContext: " + definition.name());
        }

        @Override
        public GemOreBlock create(BlockContext context) {
            String gem = context.material().name().orElseThrow().getPath();
            return new GemOreBlock(GemRarity.of(gem), context.properties());
        }
    }
}
