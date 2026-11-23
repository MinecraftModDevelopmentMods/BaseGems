package zone.moddev.mc.basegems.recipe;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.config.GemConfig;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.ModList;

@net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = BaseGems.MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD)
public final class CrushingRecipe implements IRecipe<IInventory> {
    public static final ResourceLocation TYPE_ID = new ResourceLocation(BaseGems.MOD_ID, "crushing");
    public static final IRecipeType<CrushingRecipe> TYPE = IRecipeType.register("basegems:crushing");
    private static final ResourceLocation BASE_METALS_TYPE = new ResourceLocation("basemetals", "crushing");
    private static final ResourceLocation MODERN_METALS_TYPE = new ResourceLocation("modernmetals", "crushing");
    public static final IRecipeSerializer<IRecipe<?>> SERIALIZER = new Serializer();
    public static final IRecipeSerializer<PlateRepairRecipe> PLATE_REPAIR_SERIALIZER =
            new PlateRepairRecipe.Serializer();

    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final ItemStack result;

    public CrushingRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result.copy();
    }

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void register(net.minecraftforge.event.RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().registerAll(SERIALIZER, PLATE_REPAIR_SERIALIZER,
                ContentCraftingRecipe.SERIALIZER, GemSmeltingRecipe.SERIALIZER, GemBlastingRecipe.SERIALIZER);
    }

    @Nullable
    public static IRecipe find(World world, ItemStack input) {
        Inventory inventory = new Inventory(1);
        inventory.setInventorySlotContents(0, input);
        for (IRecipe recipe : world.getRecipeManager().getRecipes()) {
            ResourceLocation type = Registry.RECIPE_TYPE.getKey(recipe.getType());
            if ((recipe instanceof CrushingRecipe || BASE_METALS_TYPE.equals(type) || MODERN_METALS_TYPE.equals(type))
                    && recipe.matches(inventory, world)) {
                return recipe;
            }
        }
        return null;
    }

    @Override public boolean matches(IInventory inventory, World world) {
        return GemConfig.allows(result.getItem().getRegistryName().getPath())
                && inventory.getSizeInventory() > 0 && ingredient.test(inventory.getStackInSlot(0));
    }
    @Override public ItemStack getCraftingResult(IInventory inventory) { return result.copy(); }
    @Override public boolean canFit(int width, int height) { return width * height >= 1; }
    @Override public ItemStack getRecipeOutput() { return result.copy(); }
    @Override public ResourceLocation getId() { return id; }
    @Override public IRecipeSerializer<?> getSerializer() { return SERIALIZER; }
    @Override public IRecipeType<CrushingRecipe> getType() { return TYPE; }
    @Override public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(ingredient);
        return ingredients;
    }
    @Override public boolean isDynamic() { return true; }

    public static final class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<IRecipe<?>> {
        private Serializer() { setRegistryName(TYPE_ID); }
        @Override public IRecipe read(ResourceLocation id, JsonObject json) {
            ItemStack result = CraftingHelper.getItemStack(json.getAsJsonObject("result"), true);
            // Keep disabled gems in the local type, which still rejects them in matches().
            if (GemConfig.allows(result.getItem().getRegistryName().getPath())) {
                ModList mods = ModList.get();
                ResourceLocation host = mods.isLoaded("basemetals") ? BASE_METALS_TYPE
                        : mods.isLoaded("modernmetals") ? MODERN_METALS_TYPE : null;
                if (host != null) {
                    JsonObject shared = new JsonObject();
                    shared.addProperty("type", host.toString());
                    shared.add("ingredient", json.get("ingredient"));
                    shared.add("result", json.get("result"));
                    return RecipeManager.deserializeRecipe(id, shared);
                }
            }
            return new CrushingRecipe(id, Ingredient.deserialize(json.get("ingredient")), result);
        }
        @Override public CrushingRecipe read(ResourceLocation id, PacketBuffer buffer) {
            return new CrushingRecipe(id, Ingredient.read(buffer), buffer.readItemStack());
        }
        @Override public void write(PacketBuffer buffer, IRecipe value) {
            CrushingRecipe recipe = (CrushingRecipe) value;
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
        }
    }
}
