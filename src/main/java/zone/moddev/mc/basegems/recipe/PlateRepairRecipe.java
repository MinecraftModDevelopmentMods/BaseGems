package zone.moddev.mc.basegems.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

/** A matching plate fully repairs one damaged shield without changing its other data. */
public final class PlateRepairRecipe extends CustomRecipe {
    private final Item target;
    private final Ingredient plate;

    public PlateRepairRecipe(Item target, Ingredient plate) {
        super(CraftingBookCategory.EQUIPMENT);
        this.target = target;
        this.plate = plate;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (!ModRecipes.allows(target)) return false;
        ItemStack foundTarget = ItemStack.EMPTY;
        boolean foundPlate = false;
        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) continue;
            if (stack.is(target) && stack.isDamaged() && foundTarget.isEmpty()) {
                foundTarget = stack;
            } else if (plate.test(stack) && !foundPlate) {
                foundPlate = true;
            } else {
                return false;
            }
        }
        return !foundTarget.isEmpty() && foundPlate;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        if (!matches(input, null)) return ItemStack.EMPTY;
        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.is(target) && stack.isDamaged()) {
                ItemStack repaired = stack.copyWithCount(1);
                repaired.setDamageValue(0);
                return repaired;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override public boolean canCraftInDimensions(int width, int height) { return width * height >= 2; }
    @Override public ItemStack getResultItem(HolderLookup.Provider registries) { return target.getDefaultInstance(); }
    @Override public boolean isSpecial() { return !ModRecipes.allows(target); }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY, Ingredient.of(target), plate);
    }

    @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.PLATE_REPAIR.get(); }

    static final class Serializer implements RecipeSerializer<PlateRepairRecipe> {
        private static final MapCodec<PlateRepairRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        BuiltInRegistries.ITEM.byNameCodec().fieldOf("target").forGetter(recipe -> recipe.target),
                        Ingredient.CODEC_NONEMPTY.fieldOf("plate").forGetter(recipe -> recipe.plate)
                ).apply(instance, PlateRepairRecipe::new));
        private static final StreamCodec<RegistryFriendlyByteBuf, PlateRepairRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        ByteBufCodecs.registry(Registries.ITEM), recipe -> recipe.target,
                        Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.plate,
                        PlateRepairRecipe::new);

        @Override public MapCodec<PlateRepairRecipe> codec() { return CODEC; }
        @Override public StreamCodec<RegistryFriendlyByteBuf, PlateRepairRecipe> streamCodec() { return STREAM_CODEC; }
    }
}
