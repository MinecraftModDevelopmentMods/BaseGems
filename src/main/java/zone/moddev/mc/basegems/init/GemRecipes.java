package zone.moddev.mc.basegems.init;

import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.recipe.ShieldRepairRecipe;
import com.mcmoddev.lib.recipe.ShieldUpgradeRecipe;
import com.mcmoddev.lib.util.Config.Options;
import com.mcmoddev.lib.util.Oredicts;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import zone.moddev.mc.basegems.BaseGems;

/** Forge 1.12 registrations for the gem crafting patterns used on 1.11.2. */
final class GemRecipes {
    private final IForgeRegistry<IRecipe> registry;
    private final MMDMaterial material;

    GemRecipes(IForgeRegistry<IRecipe> registry, MMDMaterial material) {
        this.registry = registry;
        this.material = material;
    }

    void register() {
        String gem = ore(Oredicts.INGOT);
        String nugget = ore(Oredicts.NUGGET);
        String block = ore(Oredicts.BLOCK);
        String rod = ore(Oredicts.ROD);
        String stick = Oredicts.STICK_WOOD;

        shaped(Names.BLOCK, 1, "xxx|xxx|xxx", 'x', gem);
        shapeless("unpack_block", Names.INGOT, 9, block);
        shaped(Names.INGOT, 1, "xxx|xxx|xxx", 'x', nugget);
        shapeless("unpack_gem", Names.NUGGET, 9, gem);
        shaped(Names.POWDER, 1, "xxx|xxx|xxx", 'x', stack(Names.SMALLPOWDER, 1));
        shapeless("unpack_powder", Names.SMALLPOWDER, 9, stack(Names.POWDER, 1));
        shaped(Names.PLATE, Options.plateQuantity(), "xxx", 'x', gem);

        shaped(Names.SLAB, 6, "xxx", 'x', block);
        shaped(Names.LEVER, 1, "s|x", 's', stick, 'x', gem);
        shaped(Names.STAIRS, 4, "x  |xx |xxx", 'x', block);
        shaped(Names.WALL, 6, "xxx|xxx", 'x', block);
        shaped(Names.BARS, 16, "xxx|xxx", 'x', gem);
        shaped("bars_from_rods", Names.BARS, 4, "xxx", 'x', rod);
        shaped(Names.PRESSURE_PLATE, 1, "xx", 'x', gem);
        shaped(Names.DOOR, 3, "xx|xx|xx", 'x', gem);
        shaped(Names.TRAPDOOR, 1, "xx|xx", 'x', gem);
        shaped(Names.BUTTON, 1, "x|x", 'x', nugget);
        shaped(Names.ROD, Options.rodQuantity(), "x|x", 'x', gem);
        shaped(Names.GEAR, Options.gearQuantity(), " x |xrx| x ", 'x', gem, 'r', rod);

        if (!Options.disableAllHammerRecipes()) {
            shaped(Names.CRACKHAMMER, 1, "x|s|s", 'x', block, 's', stick);
        }
        shaped(Names.AXE, 1, "xx|xs| s", 'x', gem, 's', stick);
        shaped(Names.HOE, 1, "xx| s| s", 'x', gem, 's', stick);
        shaped(Names.PICKAXE, 1, "xxx| s | s ", 'x', gem, 's', stick);
        shaped(Names.SHOVEL, 1, "x|s|s", 'x', gem, 's', stick);
        shaped(Names.SWORD, 1, "x|x|s", 'x', gem, 's', stick);
        shaped(Names.SHEARS, 1, " x|x ", 'x', ore(Oredicts.GEM));
        shaped(Names.ARROW, 4, "x|r|f", 'x', nugget, 'r', rod, 'f', Oredicts.FEATHER);
        shaped(Names.BOW, 1, " rs|r s| rs", 'r', rod, 's', Oredicts.STRING);
        shaped(Names.BOLT, 4, "r|r|f", 'r', rod, 'f', Oredicts.FEATHER);
        shaped(Names.CROSSBOW, 1, "srr| gr|r s", 'r', rod, 'g', ore(Oredicts.GEAR), 's', Oredicts.STRING);
        shaped(Names.FISHING_ROD, 1, "  r| rs|r s", 'r', rod, 's', Oredicts.STRING);
        shaped(Names.SHIELD, 1, "xpx|xxx| x ", 'x', gem, 'p', Oredicts.PLANK_WOOD);
        if (material.hasItem(Names.SHIELD) && material.hasBlock(Names.PLATE)) {
            if (Options.enablePlateRepairs()) add("repair_shield", new ShieldRepairRecipe(material));
            if (Options.enableShieldUpgrades()) add("upgrade_shield", new ShieldUpgradeRecipe(material));
        }
    }

    private String ore(String prefix) {
        return prefix + material.getCapitalizedName();
    }

    private ItemStack stack(Names form, int count) {
        if (material.hasItem(form)) return material.getItemStack(form, count);
        return material.hasBlock(form) ? material.getBlockItemStack(form, count) : ItemStack.EMPTY;
    }

    private void shaped(Names form, int count, String rows, Object... keys) {
        shaped(form.toString(), form, count, rows, keys);
    }

    private void shaped(String name, Names form, int count, String rows, Object... keys) {
        ItemStack result = stack(form, count);
        if (result.isEmpty()) return;
        for (int index = 1; index < keys.length; index += 2) {
            if (!hasIngredient(keys[index])) return;
        }

        Object[] pattern = new Object[keys.length + 1];
        pattern[0] = rows.split("\\|");
        System.arraycopy(keys, 0, pattern, 1, keys.length);
        add(name, new ShapedOreRecipe(null, result, pattern));
    }

    private void shapeless(String name, Names form, int count, Object input) {
        ItemStack result = stack(form, count);
        if (!result.isEmpty() && hasIngredient(input)) {
            add(name, new ShapelessOreRecipe(null, result, input));
        }
    }

    private static boolean hasIngredient(Object ingredient) {
        if (ingredient instanceof ItemStack) return !((ItemStack) ingredient).isEmpty();
        return !OreDictionary.getOres((String) ingredient, false).isEmpty();
    }

    private void add(String name, IRecipe recipe) {
        registry.register(recipe.setRegistryName(new ResourceLocation(BaseGems.MODID, material.getName() + "_" + name)));
    }
}
