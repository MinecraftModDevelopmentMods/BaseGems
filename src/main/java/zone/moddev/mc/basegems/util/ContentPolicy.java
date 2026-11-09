package zone.moddev.mc.basegems.util;

import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import zone.moddev.mc.basegems.BaseGems;

/** Keeps gem processing and storage recipes available in Low Fantasy. */
public final class ContentPolicy {
    private ContentPolicy() {}

    private static boolean allows(ItemStack output) {
        if (output == null || output.getItem() == null) return true;
        ResourceLocation id = output.getItem().getRegistryName();
        if (id == null || !BaseGems.MODID.equals(id.getResourceDomain())) return true;

        String path = id.getResourcePath();
        int separator = path.indexOf('_');
        if (separator < 0) return true; // Molten gem blocks use the bare material name.
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

    /** Apply after Base Metals and add-ons have registered their crafting recipes. */
    public static void filterRecipes() {
        if (!Config.lowFantasy()) return;
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        int before = recipes.size();
        recipes.removeIf(recipe -> !allows(recipe.getRecipeOutput()));
        BaseGems.logger.info("Low Fantasy removed %s Base Gems crafting recipes", before - recipes.size());
    }
}
