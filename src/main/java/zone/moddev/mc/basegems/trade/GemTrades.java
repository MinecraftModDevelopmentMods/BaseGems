package zone.moddev.mc.basegems.trade;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.ForgeRegistries;
import zone.moddev.mc.basegems.config.GemConfig;
import zone.moddev.mc.basegems.content.ModContent;
import zone.moddev.mc.basegems.data.Gem;
import zone.moddev.mc.basegems.data.GemRarity;

public final class GemTrades {
    private static final String[] TOOLS = {"crackhammer", "axe", "hoe", "shovel", "pickaxe"};

    private GemTrades() {}

    public static void register() {
        VillagerProfession smith = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("minecraft", "smith"));
        if (smith == null) return;
        Map<GemRarity, List<Gem>> tiers = new EnumMap<>(GemRarity.class);
        for (Gem gem : Gem.ALL) tiers.computeIfAbsent(GemRarity.of(gem.name()), key -> new ArrayList<>()).add(gem);
        for (int careerId : new int[]{1, 2, 3}) {
            VillagerCareer career = smith.getCareer(careerId);
            tiers.forEach((rarity, gems) -> career.addTrade(rarity.tradeLevel, (merchant, offers, random) -> {
                List<Gem> available = new ArrayList<>();
                for (Gem gem : gems) if (GemConfig.enabled(gem.name())) available.add(gem);
                if (available.isEmpty()) return;
                String name = available.get(random.nextInt(available.size())).name();
                ItemStack bundle = new ItemStack(ModContent.item(name + "_ingot"), rarity.gemsPerTrade);
                offers.add(new MerchantRecipe(bundle, ItemStack.EMPTY, new ItemStack(Items.EMERALD), 0, 6));
                offers.add(new MerchantRecipe(new ItemStack(Items.EMERALD, rarity.emeraldsToBuy),
                        ItemStack.EMPTY, bundle.copy(), 0, 6));
            }));
        }
        VillagerCareer weapons = smith.getCareer(2);
        VillagerCareer tools = smith.getCareer(3);
        for (Gem gem : Gem.ALL) {
            float value = gem.equipmentTradeValue();
            int level = Math.max(1, Math.min(4, (int) (value * 0.1F)));
            int price = Math.max(1, (int) (value * 0.2F));
            for (String tool : TOOLS) addEquipment(tools, level, price, gem.name() + "_" + tool);
            addEquipment(weapons, level, price + (int) (gem.baseAttackDamage() / 2) - 1, gem.name() + "_sword");
        }
    }

    private static void addEquipment(VillagerCareer career, int level, int price, String path) {
        career.addTrade(level, (merchant, offers, random) -> {
            if (!GemConfig.allows(path)) return;
            int markup = price > 1 ? random.nextInt(Math.max(2, price / 4) + 1) : 0;
            offers.add(new MerchantRecipe(new ItemStack(Items.EMERALD, Math.max(1, price + markup)),
                    new ItemStack(ModContent.item(path))));
        });
    }
}
