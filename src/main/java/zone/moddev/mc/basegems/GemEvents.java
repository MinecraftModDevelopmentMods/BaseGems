package zone.moddev.mc.basegems;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import zone.moddev.mc.basegems.trade.GemTrades;

/** Game-bus handlers for Base Gems. Crackhammer crushing is MMDLib's. */
final class GemEvents {
    /** Restores unlocked recipes whose book entries were dropped while a gem or mode was off. */
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        List<RecipeHolder<?>> recipes = new ArrayList<>();
        for (RecipeHolder<?> recipe : player.server.getRecipeManager().getRecipes()) {
            ResourceLocation id = recipe.id();
            if (!BaseGems.MOD_ID.equals(id.getNamespace()) || recipe.value().isSpecial()) continue;
            AdvancementHolder unlock = player.server.getAdvancements()
                    .get(ResourceLocation.fromNamespaceAndPath(BaseGems.MOD_ID, "recipes/" + id.getPath()));
            if (unlock != null && player.getAdvancements().getOrStartProgress(unlock).isDone()) recipes.add(recipe);
        }
        player.awardRecipes(recipes);
    }

    @SubscribeEvent
    public void onVillagerTrades(VillagerTradesEvent event) {
        GemTrades.add(event);
    }
}
