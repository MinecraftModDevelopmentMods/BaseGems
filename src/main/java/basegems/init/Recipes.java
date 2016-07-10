package basegems.init;

import basegems.init.Materials;
import cyano.basemetals.material.MetalMaterial;
import cyano.basemetals.registry.CrusherRecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Jasmine Iwanek
 *
 */
public class Recipes extends cyano.basemetals.init.Recipes {

	private static boolean initDone = false;

	/**
	 * 
	 */
	public static void init() {
		if(initDone) return;

		basegems.init.Materials.init();
		basegems.init.Blocks.init();
		basegems.init.Items.init();

		initMetalRecipes();

		initDone = true;
	}

	private static void initMetalRecipes() {
		List<MetalMaterial> exceptions = Arrays.asList(Materials.vanilla_iron, Materials.vanilla_gold, Materials.vanilla_diamond, Materials.vanilla_stone, Materials.vanilla_wood, Materials.copper, Materials.silver, Materials.tin, Materials.lead, Materials.nickel, Materials.bronze, Materials.brass, Materials.steel, Materials.invar, Materials.electrum, Materials.coldiron, Materials.mithril, Materials.adamantine, Materials.starsteel, Materials.zinc, Materials.aquarium);

		for(MetalMaterial metal : Materials.getAllMetals()) {
			if(exceptions.contains(metal)) continue;
			String baseName = metal.getName()+"_";
			String oreDictName = metal.getCapitalizedName();
			Item axe = basegems.init.Items.getItemByName(baseName+"axe");
			Item blend = basegems.init.Items.getItemByName(baseName+"blend");
			Item boots = basegems.init.Items.getItemByName(baseName+"boots");
			Item chestplate = basegems.init.Items.getItemByName(baseName+"chestplate");
			Item crackhammer = basegems.init.Items.getItemByName(baseName+"crackhammer");
			Item door = basegems.init.Items.getItemByName(baseName+"door_item");
			Item helmet = basegems.init.Items.getItemByName(baseName+"helmet");
			Item hoe = basegems.init.Items.getItemByName(baseName+"hoe");
			Item ingot = basegems.init.Items.getItemByName(baseName+"ingot");
			Item leggings = basegems.init.Items.getItemByName(baseName+"leggings");
			Item nugget = basegems.init.Items.getItemByName(baseName+"nugget");
			Item pickaxe = basegems.init.Items.getItemByName(baseName+"pickaxe");
			Item powder = basegems.init.Items.getItemByName(baseName+"powder");
			Item shovel = basegems.init.Items.getItemByName(baseName+"shovel");
			Item sword = basegems.init.Items.getItemByName(baseName+"sword");
			Item rod = basegems.init.Items.getItemByName(baseName+"rod");
			Item gear = basegems.init.Items.getItemByName(baseName+"gear");
			Block bars = basegems.init.Blocks.getBlockByName(baseName+"bars");
			Block block = basegems.init.Blocks.getBlockByName(baseName+"block");
			Block plate = basegems.init.Blocks.getBlockByName(baseName+"plate");
			Block ore = basegems.init.Blocks.getBlockByName(baseName+"ore");
			Block trapdoor = basegems.init.Blocks.getBlockByName(baseName+"trapdoor");
			
			Item arrow = basegems.init.Items.getItemByName(baseName+"arrow");
			Item bow = basegems.init.Items.getItemByName(baseName+"bow");
			Item bolt = basegems.init.Items.getItemByName(baseName+"bolt");
			Item crossbow = basegems.init.Items.getItemByName(baseName+"crossbow");
			Item shears = basegems.init.Items.getItemByName(baseName+"shears");
			Item smallblend = basegems.init.Items.getItemByName(baseName+"smallblend");
			Item smallpowder = basegems.init.Items.getItemByName(baseName+"smallpowder");
			Item fishingrod = basegems.init.Items.getItemByName(baseName+"fishingrod");
			Item horsearmor = basegems.init.Items.getItemByName(baseName+"horsearmor");

			// NOTE: smelting XP is based on output item, not input item
			// ingot-related recipes 
			if(ore != null && powder != null) {
				CrusherRecipeRegistry.addNewCrusherRecipe("ore"+oreDictName, new ItemStack(powder, 2));
			}
			if(ore != null && ingot != null) {
				GameRegistry.addSmelting(ore, new ItemStack(ingot, 1), metal.getOreSmeltXP());
			}
			if(ingot != null && powder != null) {
				CrusherRecipeRegistry.addNewCrusherRecipe("ingot"+oreDictName, new ItemStack(powder, 1));
				GameRegistry.addSmelting(powder, new ItemStack(ingot, 1), metal.getOreSmeltXP());
			}
			if(ingot != null && blend != null) {
				GameRegistry.addSmelting(blend, new ItemStack(ingot, 1), metal.getOreSmeltXP());
			}
			if(ingot != null && nugget != null) {
				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), new ItemStack(ingot)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot), "xxx", "xxx", "xxx", 'x', "nugget"+oreDictName));
			}
			if(ingot != null && block != null) {
				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ingot, 9), new ItemStack(block)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block), "xxx", "xxx", "xxx", 'x', "ingot"+oreDictName));
			}
			if(ingot != null && plate != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(plate, 3), "xxx", 'x', "ingot"+oreDictName));
				GameRegistry.addSmelting(plate, new ItemStack(ingot, 1), metal.getOreSmeltXP());
			}
			if(block != null && powder != null) {
				CrusherRecipeRegistry.addNewCrusherRecipe("block"+oreDictName, new ItemStack(powder, 9));
			}
			if(ingot != null && bars != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bars, 16), "xxx", "xxx", 'x', "ingot"+oreDictName));
				OreDictionary.registerOre("bars", bars);
			}
			if(ingot != null && rod != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rod, 4), "x", "x", 'x', "ingot"+oreDictName));
				OreDictionary.registerOre("stick"+oreDictName, rod);
				OreDictionary.registerOre("rod"+oreDictName, rod);
				OreDictionary.registerOre("rod", rod);
			}
			if(nugget != null && rod != null) {
				GameRegistry.addSmelting(rod, new ItemStack(nugget, 4), 0);
			}
			if(rod != null && bars != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bars, 4), "xxx", 'x', "rod"+oreDictName));
			}
			if(rod != null && ingot != null && gear != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(gear, 4), " x ", "x/x", " x ", 'x', "ingot"+oreDictName, '/', "rod"+oreDictName));
				OreDictionary.registerOre("gear"+oreDictName, gear);
				OreDictionary.registerOre("gear", gear);
				if(metal == Materials.steel) OreDictionary.registerOre("sprocket", gear);
			}
			if(ingot != null && door != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(door, 3), "xx", "xx", "xx", 'x', "ingot"+oreDictName));
				OreDictionary.registerOre("door", door);
			}
			if(ingot != null && trapdoor != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(trapdoor), "xx", "xx", 'x', "ingot"+oreDictName));
				OreDictionary.registerOre("trapdoor", trapdoor);
			}

			if(blend != null && smallblend != null) {
				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(smallblend, 9), new ItemStack(blend)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blend), "xxx", "xxx", "xxx", 'x', new ItemStack(smallblend)));
				GameRegistry.addSmelting(smallblend, new ItemStack(nugget, 1), metal.getOreSmeltXP());
			}
			if(powder != null && smallpowder != null) {
				// TODO - small powder to powder recipe doesn't use oredict
				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(smallpowder, 9), new ItemStack(powder)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(powder), "xxx", "xxx", "xxx", 'x', new ItemStack(smallpowder)));
				GameRegistry.addSmelting(smallpowder, new ItemStack(nugget, 1), metal.getOreSmeltXP());
				CrusherRecipeRegistry.addNewCrusherRecipe("nugget"+oreDictName, new ItemStack(smallpowder, 1));
			}

			// armor and tools
			if(ingot != null && boots != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(boots), "x x", "x x", 'x', "ingot"+oreDictName));
			if(ingot != null && helmet != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(helmet), "xxx", "x x", 'x', "ingot"+oreDictName));
			if(ingot != null && chestplate != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(chestplate), "x x", "xxx", "xxx", 'x', "ingot"+oreDictName));
			if(ingot != null && leggings != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(leggings), "xxx", "x x", "x x", 'x', "ingot"+oreDictName));
			if(ingot != null && axe != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(axe), "xx", "x/", " /", 'x', "ingot"+oreDictName, '/', "stickWood"));
//			if(ingot != null && axe != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(axe), "xx", "/x", "/ ", 'x', "ingot"+oreDictName, '/', "stickWood"));
//			if(block != null && crackhammer != null && (!BaseMetals.disableAllHammers)) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(crackhammer), "x", "/", "/", 'x', "block"+oreDictName, '/', "stickWood"));
			if(block != null && crackhammer != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(crackhammer), "x", "/", "/", 'x', "block"+oreDictName, '/', "stickWood"));
			if(ingot != null && hoe != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(hoe), "xx", " /", " /", 'x', "ingot"+oreDictName, '/', "stickWood"));
//			if(ingot != null && hoe != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(hoe), "xx", "/ ", "/ ", 'x', "ingot"+oreDictName, '/', "stickWood"));
			if(ingot != null && pickaxe != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pickaxe), "xxx", " / ", " / ", 'x', "ingot"+oreDictName, '/', "stickWood"));
			if(ingot != null && shovel != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(shovel), "x", "/", "/", 'x', "ingot"+oreDictName, '/', "stickWood"));
			if(ingot != null && sword != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(sword), "x", "x", "/", 'x', "ingot"+oreDictName, '/', "stickWood"));
			if(ingot != null && shears != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(shears), " x", "x " , 'x', "ingot"+oreDictName));
//			if(rod != null && fishingrod != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fishingrod), "  x", " xy", "x y", 'x', "rod"+oreDictName, 'y', Items.STRING));
//			if(ingot != null && horsearmor != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(horsearmor), "  x", "xyx", "xxx", 'x', "ingot"+oreDictName, 'y', Blocks.WOOL));

			// Bows and Crossbows
			if(rod != null && arrow != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(arrow), "x", "y", "z", 'x', "nugget"+oreDictName, 'y', "rod"+oreDictName,'z' ,net.minecraft.init.Items.FEATHER));
			if(rod != null && bow != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bow), " xy", "x y", " xy", 'x', "rod"+oreDictName, 'y', net.minecraft.init.Items.STRING));
//			if(rod != null && gear != null && crossbow != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(crossbow), "zxx", " yx", "x z", 'x', "rod"+oreDictName, 'y', "gear"+oreDictName, 'z', Items.STRING));
//			if(rod != null && bolt != null) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bolt), "x", "x", "y", 'x', "rod"+oreDictName, 'y', Items.FEATHER));
		}

		// alloy blends
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(basegems.init.Items.template_blend, 2), "dustTemplate", "dustTemplate"));
		
		// small alloy blends
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(basegems.init.Items.template_smallblend, 2), "smalldustTemplate", "smalldustTemplate"));
	}
}
