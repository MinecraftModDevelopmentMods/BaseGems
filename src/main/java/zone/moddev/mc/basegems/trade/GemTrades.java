package zone.moddev.mc.basegems.trade;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import zone.moddev.mc.basegems.config.GemConfig;
import zone.moddev.mc.basegems.content.ModContent;
import zone.moddev.mc.basegems.data.Gem;
import zone.moddev.mc.basegems.data.GemRarity;

/** The armourer, toolsmith and weaponsmith trade gems; the smiths also sell gem equipment. */
public final class GemTrades {
    private static final String[] TOOLS = {"crackhammer", "axe", "hoe", "shovel", "pickaxe"};
    private static final int GEM_USES = 6;
    private static final int EQUIPMENT_USES = 3;
    private static final int EXPERIENCE = 5;

    private GemTrades() {}

    public static void add(VillagerTradesEvent event) {
        VillagerProfession profession = event.getType();
        if (profession != VillagerProfession.ARMORER && profession != VillagerProfession.TOOLSMITH
                && profession != VillagerProfession.WEAPONSMITH) return;

        Map<GemRarity, List<Gem>> tiers = new EnumMap<>(GemRarity.class);
        for (Gem gem : Gem.ALL) tiers.computeIfAbsent(GemRarity.of(gem.name()), key -> new ArrayList<>()).add(gem);
        tiers.forEach((rarity, gems) -> {
            event.getTrades().get(rarity.tradeLevel).add(gemTrade(rarity, gems, true));
            event.getTrades().get(rarity.tradeLevel).add(gemTrade(rarity, gems, false));
        });

        for (Gem gem : Gem.ALL) {
            double value = gem.equipmentTradeValue();
            int level = Math.max(1, Math.min(4, (int) (value * 0.1F)));
            int price = Math.max(1, (int) (value * 0.2F));
            if (profession == VillagerProfession.TOOLSMITH) {
                for (String tool : TOOLS) event.getTrades().get(level).add(equipment(gem.name() + "_" + tool, price));
            } else if (profession == VillagerProfession.WEAPONSMITH) {
                event.getTrades().get(level).add(equipment(gem.name() + "_sword",
                        price + (int) (gem.baseAttackDamage() / 2) - 1));
            }
        }
    }

    /** A bundle of one enabled gem of this rarity, bought or sold for emeralds. */
    private static ItemListing gemTrade(GemRarity rarity, List<Gem> gems, boolean villagerBuys) {
        return (trader, random) -> {
            List<Gem> available = gems.stream().filter(gem -> GemConfig.enabled(gem.name())).toList();
            if (available.isEmpty()) return null;
            Gem gem = available.get(random.nextInt(available.size()));
            ItemStack bundle = new ItemStack(ModContent.item(gem.name() + "_ingot"), rarity.gemsPerTrade);
            return villagerBuys
                    ? new MerchantOffer(new ItemCost(bundle.getItem(), bundle.getCount()),
                            new ItemStack(Items.EMERALD), GEM_USES, EXPERIENCE, 0.05F)
                    : new MerchantOffer(new ItemCost(Items.EMERALD, rarity.emeraldsToBuy),
                            bundle, GEM_USES, EXPERIENCE, 0.05F);
        };
    }

    private static ItemListing equipment(String path, int price) {
        return (trader, random) -> {
            if (!GemConfig.allows(path)) return null;
            int markup = price > 1 ? random.nextInt(Math.max(2, price / 4) + 1) : 0;
            return new MerchantOffer(new ItemCost(Items.EMERALD, Math.min(64, Math.max(1, price + markup))),
                    new ItemStack(ModContent.item(path)), EQUIPMENT_USES, EXPERIENCE, 0.2F);
        };
    }
}
