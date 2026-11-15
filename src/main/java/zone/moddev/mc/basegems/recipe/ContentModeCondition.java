package zone.moddev.mc.basegems.recipe;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.conditions.ICondition;

/**
 * Loads a recipe only when the content mode and gem switches let players make its
 * {@code item}. Base Gems uses it for its {@code mmdlib:crushing} recipes.
 */
public record ContentModeCondition(Item item) implements ICondition {
    public static final MapCodec<ContentModeCondition> CODEC = BuiltInRegistries.ITEM.byNameCodec()
            .fieldOf("item").xmap(ContentModeCondition::new, ContentModeCondition::item);

    @Override
    public boolean test(IContext context) {
        return ModRecipes.allows(item);
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}
