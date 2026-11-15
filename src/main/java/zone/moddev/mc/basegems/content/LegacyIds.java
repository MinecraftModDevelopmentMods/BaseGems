package zone.moddev.mc.basegems.content;

import net.minecraft.resources.ResourceLocation;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.data.Gem;

/** Registry aliases for IDs that older Base Gems versions saved. */
final class LegacyIds {
    private LegacyIds() {}

    static void register() {
        for (Gem gem : Gem.ALL) {
            // Base Gems named its double slabs double_X_slab; MMDLib names them X_double_slab.
            ModContent.BLOCKS.addAlias(id("double_" + gem.name() + "_slab"), id(gem.name() + "_double_slab"));
        }
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(BaseGems.MOD_ID, path);
    }
}
