package zone.moddev.mc.basegems.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.config.GemConfig;

/** Furnace recipes also honour the gem switches. */
public final class GemSmeltingRecipe extends FurnaceRecipe {
    public static final IRecipeSerializer<GemSmeltingRecipe> SERIALIZER = new Serializer();
    private static final int COOKING_TIME = 200;

    private GemSmeltingRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, float experience) {
        super(id, "", ingredient, result, experience, COOKING_TIME);
    }

    private boolean allowed() {
        return GemConfig.allows(getRecipeOutput().getItem().getRegistryName().getPath());
    }

    @Override public boolean matches(IInventory inventory, World world) {
        return allowed() && super.matches(inventory, world);
    }

    @Override public ItemStack getCraftingResult(IInventory inventory) {
        return allowed() ? super.getCraftingResult(inventory) : ItemStack.EMPTY;
    }

    @Override public boolean isDynamic() { return !allowed(); }
    @Override public IRecipeSerializer<?> getSerializer() { return SERIALIZER; }

    private static final class Serializer implements IRecipeSerializer<GemSmeltingRecipe> {
        @Override public GemSmeltingRecipe read(ResourceLocation id, JsonObject json) {
            return new GemSmeltingRecipe(id, Ingredient.fromJson(json.get("ingredient")),
                    CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), true),
                    JsonUtils.getFloat(json, "experience", 0.0F));
        }

        @Override public GemSmeltingRecipe read(ResourceLocation id, PacketBuffer buffer) {
            return new GemSmeltingRecipe(id, Ingredient.fromBuffer(buffer), buffer.readItemStack(), buffer.readFloat());
        }

        @Override public void write(PacketBuffer buffer, GemSmeltingRecipe recipe) {
            recipe.getIngredients().get(0).writeToBuffer(buffer);
            buffer.writeItemStack(recipe.getRecipeOutput());
            buffer.writeFloat(recipe.getExperience());
        }

        @Override public ResourceLocation getName() {
            return new ResourceLocation(BaseGems.MOD_ID, "smelting");
        }
    }
}
