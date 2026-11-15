package zone.moddev.mc.basegems.content;

import zone.moddev.mc.basegems.data.Gem;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.item.ItemStack;

public interface MaterialBacked {
    Gem gem();

    default boolean isMaterialRepairIngredient(ItemStack repair) {
        return new ItemTags.Wrapper(new ResourceLocation(
                gem().repairIngredientTag())).contains(repair.getItem());
    }
}
