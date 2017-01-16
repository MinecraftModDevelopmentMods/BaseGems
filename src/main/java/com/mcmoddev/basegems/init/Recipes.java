package com.mcmoddev.basegems.init;

import net.minecraft.item.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.*;

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
		if (initDone) {
			return;
		}

		Materials.init();
		Blocks.init();
		Items.init();

		initModSpecificRecipes();

		initDone = true;
	}

	private static void initModSpecificRecipes() {
		// alloy blends
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Materials.agate.blend, 2), "dustAgate", "dustAgate"));

		// small alloy blends
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Materials.agate.smallblend, 2), "smalldustAgate", "smalldustAgate"));
	}
}
