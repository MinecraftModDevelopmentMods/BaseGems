package com.mcmoddev.basegems.init;

import com.mcmoddev.lib.data.SharedStrings;

/**
 *
 * @author Jasmine Iwanek
 *
 */
public class Recipes extends com.mcmoddev.lib.init.Recipes {

	private Recipes() {
		throw new IllegalAccessError(SharedStrings.NOT_INSTANTIABLE);
	}

	/**
	 *
	 */
	public static void init() {
		initModSpecificRecipes();
	}

	private static void initModSpecificRecipes() {
		// No Mod specific recipes currently
	}
}
