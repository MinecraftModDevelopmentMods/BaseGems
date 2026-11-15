package zone.moddev.mc.basegems.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;

/**
 * An ordinary shaped or shapeless recipe that follows the content mode and gem switches.
 * Its ID stays the same in either mode, so recipe books keep it.
 */
public final class ContentCraftingRecipe {
    private ContentCraftingRecipe() {}

    private static boolean allowed(Recipe<?> recipe) {
        return ModRecipes.allows(recipe.getResultItem(RegistryAccess.EMPTY).getItem());
    }

    private static Recipe<?> wrap(Recipe<?> recipe) {
        // Recipe-book placement needs the shaped recipe's width and height, not just its ingredients.
        if (recipe instanceof ShapedRecipe shaped) return new Shaped(shaped);
        if (recipe instanceof ShapelessRecipe shapeless) return new Shapeless(shapeless);
        throw new IllegalArgumentException("Not an ordinary crafting recipe: " + recipe.getClass().getName());
    }

    private static Recipe<?> unwrap(Recipe<?> recipe) {
        if (recipe instanceof Shaped shaped) return shaped.plain;
        if (recipe instanceof Shapeless shapeless) return shapeless.plain;
        throw new IllegalArgumentException("Not a content-mode crafting recipe");
    }

    private static final class Shaped extends ShapedRecipe {
        private final ShapedRecipe plain;

        private Shaped(ShapedRecipe plain) {
            super(plain.getGroup(), plain.category(), new ShapedRecipePattern(plain.getWidth(), plain.getHeight(),
                            plain.getIngredients(), java.util.Optional.empty()),
                    plain.getResultItem(RegistryAccess.EMPTY), plain.showNotification());
            this.plain = plain;
        }

        @Override public boolean matches(CraftingInput input, Level level) {
            return allowed(this) && super.matches(input, level);
        }

        @Override public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
            return allowed(this) ? super.assemble(input, registries) : ItemStack.EMPTY;
        }

        @Override public boolean isSpecial() { return !allowed(this); }
        @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.CONTENT_CRAFTING.get(); }
    }

    private static final class Shapeless extends ShapelessRecipe {
        private final ShapelessRecipe plain;

        private Shapeless(ShapelessRecipe plain) {
            super(plain.getGroup(), plain.category(), plain.getResultItem(RegistryAccess.EMPTY),
                    plain.getIngredients());
            this.plain = plain;
        }

        @Override public boolean matches(CraftingInput input, Level level) {
            return allowed(this) && super.matches(input, level);
        }

        @Override public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
            return allowed(this) ? super.assemble(input, registries) : ItemStack.EMPTY;
        }

        @Override public boolean isSpecial() { return !allowed(this); }
        @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.CONTENT_CRAFTING.get(); }
    }

    /** Reads the plain recipe nested under {@code recipe}. */
    static final class Serializer implements RecipeSerializer<Recipe<?>> {
        private static final MapCodec<Recipe<?>> CODEC = Recipe.CODEC.<Recipe<?>>flatXmap(
                recipe -> recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe
                        ? DataResult.success(wrap(recipe))
                        : DataResult.error(() -> "Content-mode crafting requires a shaped or shapeless recipe"),
                recipe -> DataResult.success(unwrap(recipe))).fieldOf("recipe");
        private static final StreamCodec<RegistryFriendlyByteBuf, Recipe<?>> STREAM_CODEC =
                Recipe.STREAM_CODEC.map(ContentCraftingRecipe::wrap, ContentCraftingRecipe::unwrap);

        @Override public MapCodec<Recipe<?>> codec() { return CODEC; }
        @Override public StreamCodec<RegistryFriendlyByteBuf, Recipe<?>> streamCodec() { return STREAM_CODEC; }
    }
}
