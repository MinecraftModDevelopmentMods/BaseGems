package com.mcmoddev.basegems.init;

import com.mcmoddev.basegems.util.Config.Options;

/**
 *
 * @author Jasmine Iwanek
 *
 */
public class Recipes extends com.mcmoddev.lib.init.Recipes {

	private static boolean initDone = false;

	private Recipes() {
		throw new IllegalAccessError("Not a instantiable class");
	}

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
		if (Options.ENABLE_AGATE) {
			//GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Materials.agate.blend, 2), "dustAgate", "dustAgate"));
			//GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Materials.agate.smallblend, 2), "smalldustAgate", "smalldustAgate"));
		}
	}
}
