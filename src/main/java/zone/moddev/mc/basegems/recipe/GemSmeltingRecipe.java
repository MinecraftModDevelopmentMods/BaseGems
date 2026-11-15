package zone.moddev.mc.basegems.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

/** A furnace recipe that also follows the content mode and gem switches. */
public final class GemSmeltingRecipe extends SmeltingRecipe {
    private final ItemStack output;

    public GemSmeltingRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack result,
            float experience, int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
        this.output = result.copy();
    }

    private boolean allowed() {
        return ModRecipes.allows(output.getItem());
    }

    @Override public boolean matches(SingleRecipeInput input, Level level) {
        return allowed() && super.matches(input, level);
    }

    @Override public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        return allowed() ? super.assemble(input, registries) : ItemStack.EMPTY;
    }

    @Override public boolean isSpecial() { return !allowed(); }
    @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.SMELTING.get(); }

    static final class Serializer implements RecipeSerializer<GemSmeltingRecipe> {
        private static final MapCodec<GemSmeltingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(GemSmeltingRecipe::getGroup),
                        CookingBookCategory.CODEC.fieldOf("category").orElse(CookingBookCategory.MISC)
                                .forGetter(GemSmeltingRecipe::category),
                        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
                        Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(GemSmeltingRecipe::getExperience),
                        Codec.intRange(1, Integer.MAX_VALUE).optionalFieldOf("cookingtime", 200)
                                .forGetter(GemSmeltingRecipe::getCookingTime)
                ).apply(instance, GemSmeltingRecipe::new));
        private static final StreamCodec<RegistryFriendlyByteBuf, GemSmeltingRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        ByteBufCodecs.STRING_UTF8, GemSmeltingRecipe::getGroup,
                        NeoForgeStreamCodecs.enumCodec(CookingBookCategory.class), GemSmeltingRecipe::category,
                        Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.ingredient,
                        ItemStack.STREAM_CODEC, recipe -> recipe.output,
                        ByteBufCodecs.FLOAT, GemSmeltingRecipe::getExperience,
                        ByteBufCodecs.VAR_INT, GemSmeltingRecipe::getCookingTime,
                        GemSmeltingRecipe::new);

        @Override public MapCodec<GemSmeltingRecipe> codec() { return CODEC; }
        @Override public StreamCodec<RegistryFriendlyByteBuf, GemSmeltingRecipe> streamCodec() { return STREAM_CODEC; }
    }
}
