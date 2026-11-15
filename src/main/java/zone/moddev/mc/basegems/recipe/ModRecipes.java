package zone.moddev.mc.basegems.recipe;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.config.GemConfig;

/** Base Gems recipe serializers and conditions. Crushing uses MMDLib's {@code mmdlib:crushing}. */
public final class ModRecipes {
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, BaseGems.MOD_ID);
    private static final DeferredRegister<MapCodec<? extends ICondition>> CONDITIONS =
            DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, BaseGems.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<Recipe<?>>> CONTENT_CRAFTING =
            SERIALIZERS.register("content_crafting", ContentCraftingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<GemSmeltingRecipe>> SMELTING =
            SERIALIZERS.register("smelting", GemSmeltingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PlateRepairRecipe>> PLATE_REPAIR =
            SERIALIZERS.register("plate_repair", PlateRepairRecipe.Serializer::new);
    public static final DeferredHolder<MapCodec<? extends ICondition>, MapCodec<ContentModeCondition>> CONTENT_MODE =
            CONDITIONS.register("content_mode", () -> ContentModeCondition.CODEC);

    private ModRecipes() {}

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
        CONDITIONS.register(bus);
    }

    /** Whether the content mode and gem switches let players make this item. */
    static boolean allows(Item item) {
        var id = BuiltInRegistries.ITEM.getKey(item);
        return !BaseGems.MOD_ID.equals(id.getNamespace()) || GemConfig.allows(id.getPath());
    }
}
