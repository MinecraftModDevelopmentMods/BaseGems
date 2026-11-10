package zone.moddev.mc.basegems.init;

import com.mcmoddev.lib.events.MMDLibRegisterBlocks;
import com.mcmoddev.lib.events.MMDLibRegisterFluids;
import com.mcmoddev.lib.events.MMDLibRegisterItems;
import com.mcmoddev.lib.events.MMDLibRegisterMaterials;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.util.Oredicts;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.util.Config;
import zone.moddev.mc.basegems.util.ContentPolicy;

@Mod.EventBusSubscriber(modid = BaseGems.MODID)
public final class RegistryHandlers {
    private RegistryHandlers() { }

    @SubscribeEvent
    public static void createMaterials(MMDLibRegisterMaterials event) {
        Materials.init();
    }

    @SubscribeEvent
    public static void createBlocks(MMDLibRegisterBlocks event) {
        Blocks.init();
    }

    @SubscribeEvent
    public static void createItems(MMDLibRegisterItems event) {
        Items.init();
    }

    @SubscribeEvent
    public static void createFluids(MMDLibRegisterFluids event) {
        Fluids.init();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
            material.getBlocks().stream().filter(block -> BaseGems.MODID.equals(block.getRegistryName().getNamespace()))
                    .forEach(event.getRegistry()::register);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
            material.getItems().stream().map(ItemStack::getItem).distinct()
                    .filter(item -> item.getRegistryName() != null && BaseGems.MODID.equals(item.getRegistryName().getNamespace()))
                    .forEach(event.getRegistry()::register);
        }

        Oredicts.registerItemOreDictionaryEntries();
        Oredicts.registerBlockOreDictionaryEntries();
        for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
            Items.registerCompoundNameAliases(material);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        com.mcmoddev.lib.init.Recipes.register(event);
        for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
            new GemRecipes(event.getRegistry(), material).register();
        }
        if (!Config.lowFantasy()) return;

        List<IRecipe> restricted = new ArrayList<>();
        for (IRecipe recipe : event.getRegistry()) {
            boolean sharedDynamic = recipe.getRecipeOutput().isEmpty()
                    && recipe.getClass().getName().startsWith("com.mcmoddev.lib.recipe.");
            if (!ContentPolicy.allows(recipe.getRecipeOutput()) || sharedDynamic) {
                restricted.add(recipe);
            }
        }
        // Preserve recipe IDs so worlds and recipe books can switch modes safely.
        for (IRecipe recipe : restricted) {
            event.getRegistry().register(new PolicyRecipe(recipe));
        }
    }
}
