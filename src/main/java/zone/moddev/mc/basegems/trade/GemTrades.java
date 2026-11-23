package zone.moddev.mc.basegems.trade;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.config.GemConfig;
import zone.moddev.mc.basegems.content.ModContent;
import zone.moddev.mc.basegems.data.Gem;
import zone.moddev.mc.basegems.data.GemRarity;

/** The existing gem offers, moved to Village & Pillage's three smith professions. */
@Mod.EventBusSubscriber(modid = BaseGems.MOD_ID)
public final class GemTrades {
    private static final String[] TOOLS = {"crackhammer", "axe", "hoe", "shovel", "pickaxe"};
    private GemTrades() {}

    @SubscribeEvent
    public static void register(VillagerTradesEvent event) {
        VillagerProfession smith = event.getType();
        if (smith != VillagerProfession.ARMORER && smith != VillagerProfession.TOOLSMITH
                && smith != VillagerProfession.WEAPONSMITH) return;
        Map<GemRarity, List<Gem>> tiers = new EnumMap<>(GemRarity.class);
        for (Gem gem : Gem.ALL) tiers.computeIfAbsent(GemRarity.of(gem.name()), key -> new ArrayList<>()).add(gem);
        tiers.forEach((rarity, gems) -> {
            for (boolean buying : new boolean[]{true, false}) {
                event.getTrades().get(rarity.tradeLevel).add((merchant, random) -> {
                    List<Gem> available = new ArrayList<>();
                    for (Gem gem : gems) if (GemConfig.enabled(gem.name())) available.add(gem);
                    if (available.isEmpty()) return null;
                    String name = available.get(random.nextInt(available.size())).name();
                    ItemStack bundle = new ItemStack(ModContent.item(name + "_ingot"), rarity.gemsPerTrade);
                    int xp = rarity.tradeLevel == 1 ? 1 : rarity.tradeLevel * 5;
                    return buying ? new MerchantOffer(bundle, new ItemStack(Items.EMERALD), 6, xp, 0.05F)
                            : new MerchantOffer(new ItemStack(Items.EMERALD, rarity.emeraldsToBuy), bundle, 6, xp, 0.05F);
                });
            }
        });
        for (Gem gem : Gem.ALL) {
            float value = gem.equipmentTradeValue();
            int level = Math.max(1, Math.min(4, (int) (value * 0.1F)));
            int price = Math.max(1, (int) (value * 0.2F));
            if (smith == VillagerProfession.TOOLSMITH) {
                for (String tool : TOOLS) addEquipment(event, level, price, gem.name() + "_" + tool);
            } else if (smith == VillagerProfession.WEAPONSMITH) {
                addEquipment(event, level, price + (int) (gem.baseAttackDamage() / 2) - 1, gem.name() + "_sword");
            }
        }
    }

    private static void addEquipment(VillagerTradesEvent event, int level, int price, String path) {
        event.getTrades().get(level).add((merchant, random) -> {
            if (!GemConfig.allows(path)) return null;
            int markup = price > 1 ? random.nextInt(Math.max(2, price / 4) + 1) : 0;
            return new MerchantOffer(new ItemStack(Items.EMERALD, Math.max(1, price + markup)),
                    new ItemStack(ModContent.item(path)), 6, level * 5, 0.05F);
        });
    }
}
