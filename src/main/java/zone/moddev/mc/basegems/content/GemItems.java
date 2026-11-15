package zone.moddev.mc.basegems.content;

import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import zone.moddev.mc.basegems.data.Gem;
import zone.moddev.mc.basegems.entity.ModEntities;

final class GemItems {
    private GemItems() {}

    static void add(Gem gem, ItemGroup group, Map<String, Supplier<? extends Item>> items) {
        String id = gem.name() + "_";
        for (String form : new String[]{"ingot", "nugget", "powder", "smallpowder", "rod", "gear"}) {
            items.put(id + form, () -> new Item(properties(group)));
        }
        items.put(id + "pickaxe", () -> new MaterialItems.Pickaxe(gem, properties(group)));
        items.put(id + "axe", () -> new MaterialItems.Axe(gem, properties(group)));
        items.put(id + "hoe", () -> new MaterialItems.Hoe(gem, properties(group)));
        items.put(id + "shovel", () -> new MaterialItems.Shovel(gem, properties(group)));
        items.put(id + "sword", () -> new MaterialItems.Sword(gem, properties(group)));
        items.put(id + "shears", () -> new MaterialItems.Shears(gem, properties(group)));
        items.put(id + "bow", () -> new MaterialItems.Bow(gem, properties(group)));
        items.put(id + "crossbow", () -> new MaterialItems.Crossbow(gem, properties(group)));
        items.put(id + "fishing_rod", () -> new MaterialItems.FishingRod(gem, properties(group)));
        items.put(id + "shield", () -> new MaterialItems.Shield(gem, properties(group)));
        items.put(id + "crackhammer", () -> new CrackhammerItem(gem, properties(group)));
        items.put(id + "arrow", () -> new GemAmmoItem(gem, GemAmmoItem.Kind.ARROW,
                ModEntities.CUSTOM_ARROW, properties(group)));
        items.put(id + "bolt", () -> new GemAmmoItem(gem, GemAmmoItem.Kind.BOLT,
                ModEntities.CUSTOM_BOLT, properties(group)));
    }

    private static Item.Properties properties(ItemGroup group) { return new Item.Properties().group(group); }
}
