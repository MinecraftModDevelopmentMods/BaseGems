package zone.moddev.mc.basegems.init;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.mcmoddev.lib.data.MaterialStats;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.material.MMDMaterial;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.data.GemRarity;
import zone.moddev.mc.basemetals.util.VillagerTradeHelper;

public final class VillagerTrades extends com.mcmoddev.lib.init.VillagerTrades {
    private static final int[] SMITH_CAREERS = {ARMOR_SMITH_ID, WEAPON_SMITH_ID, TOOL_SMITH_ID};
    private static final Names[] TOOLS = {Names.CRACKHAMMER, Names.AXE, Names.HOE, Names.SHOVEL, Names.PICKAXE};
    private static final int GEM_TRADE_USES = 6;
    private static boolean initDone;

    private VillagerTrades() { }

    public static void init() {
        if (initDone) {
            return;
        }

        Map<GemRarity, List<Item>> gemsByRarity = new EnumMap<>(GemRarity.class);
        for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
            registerEquipment(material);
            if (material.hasItem(Names.GEM)) {
                GemRarity rarity = GemRarity.of(material.getName());
                gemsByRarity.computeIfAbsent(rarity, key -> new ArrayList<>()).add(material.getItem(Names.GEM));
            }
        }

        // One gem and its two prices per tier, so the rest of the smith's offers stay usable.
        gemsByRarity.forEach((rarity, gems) -> {
            gems.sort(Comparator.comparing(gem -> gem.getRegistryName().getResourcePath()));
            for (int career : SMITH_CAREERS) {
                VillagerTradeHelper.insertTrades(SMITH_RL, career, rarity.tradeLevel, (merchant, offers, random) -> {
                    Item gem = gems.get(random.nextInt(gems.size()));
                    ItemStack bundle = new ItemStack(gem, rarity.gemsPerTrade);
                    offers.add(new MerchantRecipe(bundle, ItemStack.EMPTY, new ItemStack(Items.EMERALD), 0, GEM_TRADE_USES));
                    offers.add(new MerchantRecipe(new ItemStack(Items.EMERALD, rarity.emeraldsToBuy),
                            ItemStack.EMPTY, bundle.copy(), 0, GEM_TRADE_USES));
                });
            }
        });
        initDone = true;
    }

    private static void registerEquipment(MMDMaterial material) {
        float value = material.getStat(MaterialStats.HARDNESS) + material.getStat(MaterialStats.STRENGTH)
                + material.getStat(MaterialStats.MAGICAFFINITY) + material.getToolHarvestLevel();
        int price = emeraldPurchaseValue(value);
        int level = tradeLevel(value);
        for (Names form : TOOLS) {
            registerEquipment(material, form, TOOL_SMITH_ID, level, price);
        }
        registerEquipment(material, Names.SWORD, WEAPON_SMITH_ID, level,
                price + (int) (material.getBaseAttackDamage() / 2) - 1);
    }

    private static void registerEquipment(MMDMaterial material, Names form, int career, int level, int price) {
        if (material.hasItem(form)) {
            VillagerTradeHelper.insertTrades(SMITH_RL, career, level,
                    makePurchasePalette(price, material.getItemStack(form)));
        }
    }
}
