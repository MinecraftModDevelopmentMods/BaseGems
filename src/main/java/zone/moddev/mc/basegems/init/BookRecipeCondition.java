package zone.moddev.mc.basegems.init;

import com.google.gson.JsonObject;
import java.util.function.BooleanSupplier;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

/** Loads a recipe advancement only when the active config allows the recipe in the book. */
public final class BookRecipeCondition implements IConditionFactory {
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        ResourceLocation id = new ResourceLocation(JsonUtils.getString(json, "recipe"));
        return () -> {
            IRecipe recipe = CraftingManager.getRecipe(id);
            return recipe != null && !recipe.isDynamic() && !recipe.getRecipeOutput().isEmpty();
        };
    }
}
