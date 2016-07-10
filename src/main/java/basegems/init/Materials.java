package basegems.init;

import cyano.basemetals.material.MetalMaterial;

/**
 * This class initializes all of the metal materials in Base Gems. It also
 * contains utility methods for looking up materials by name and finding the 
 * tool and armor material equivalents for a given metal.
 * @author DrCyano
 *
 */
public abstract class Materials extends cyano.basemetals.init.Materials {

	public static MetalMaterial template;

	private static boolean initDone = false;

	/**
	 * 
	 */
	public static void init() {
		if(initDone) return;

		// Mod Metals
		template = addMaterial("template", 8, 8, 4.5);

		initDone = true;
	}

	private static MetalMaterial addMaterial(String name, double hardness, double strength, double magic) {
		MetalMaterial m = new MetalMaterial(name, (float)hardness, (float)strength, (float)magic);
		registerMaterial(name, m);
		return m;
	}

	private static MetalMaterial addRareMaterial(String name, double hardness, double strength, double magic) {
		MetalMaterial m = new MetalMaterial(name, (float)hardness, (float)strength, (float)magic);
		registerMaterial(name, m);
		return m;
	}
}
