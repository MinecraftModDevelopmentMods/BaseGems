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
	public static MetalMaterial agate;
	public static MetalMaterial alexandrite;
	public static MetalMaterial amber;
	public static MetalMaterial amethyst;
	public static MetalMaterial ametrine;
	public static MetalMaterial aquamarine;
	public static MetalMaterial beryl;
	public static MetalMaterial blackdiamond;
	public static MetalMaterial bluetopaz;
	public static MetalMaterial carnelian;
	public static MetalMaterial citrine;
	public static MetalMaterial garnet;
	public static MetalMaterial goldenberyl;
	public static MetalMaterial heliodor;
	public static MetalMaterial indicolite;
	public static MetalMaterial iolite;
	public static MetalMaterial jasper;
	public static MetalMaterial lepidolite;
	public static MetalMaterial malachite;
	public static MetalMaterial moldavite;
	public static MetalMaterial moonstone;
	public static MetalMaterial morganite;
	public static MetalMaterial onyx;
	public static MetalMaterial opal;
	public static MetalMaterial peridot;
	public static MetalMaterial ruby;
	public static MetalMaterial sapphire;
	public static MetalMaterial spinel;
	public static MetalMaterial tanzanite;
	public static MetalMaterial topaz;
	public static MetalMaterial turquoise;
	public static MetalMaterial violetsapphire;

	private static boolean initDone = false;

	/**
	 * 
	 */
	public static void init() {
		if(initDone) return;

		// Mod Metals
		template = addMaterial("template", 8, 8, 4.5);
		agate = addMaterial("agate", 8, 8, 4.5);
		alexandrite = addMaterial("alexandrite", 8, 8, 4.5);
		amber = addMaterial("amber", 8, 8, 4.5);
		amethyst = addMaterial("amethyst", 8, 8, 4.5);
		ametrine = addMaterial("ametrine", 8, 8, 4.5);
		aquamarine = addMaterial("aquamarine", 8, 8, 4.5);
		beryl = addMaterial("beryl", 8, 8, 4.5);
		blackdiamond = addMaterial("blackdiamond", 8, 8, 4.5);
		bluetopaz = addMaterial("bluetopaz", 8, 8, 4.5);
		carnelian = addMaterial("carnelian", 8, 8, 4.5);
		citrine = addMaterial("citrine", 8, 8, 4.5);
		garnet = addMaterial("garnet", 8, 8, 4.5);
		goldenberyl = addMaterial("goldenberyl", 8, 8, 4.5);
		heliodor = addMaterial("heliodor", 8, 8, 4.5);
		indicolite = addMaterial("indicolite", 8, 8, 4.5);
		iolite = addMaterial("iolite", 8, 8, 4.5);
		jasper = addMaterial("jasper", 8, 8, 4.5);
		lepidolite = addMaterial("lepidolite", 8, 8, 4.5);
		malachite = addMaterial("malachite", 8, 8, 4.5);
		moldavite = addMaterial("moldavite", 8, 8, 4.5);
		moonstone = addMaterial("moonstone", 8, 8, 4.5);
		morganite = addMaterial("morganite", 8, 8, 4.5);
		onyx = addMaterial("onyx", 8, 8, 4.5);
		opal = addMaterial("opal", 8, 8, 4.5);
		peridot = addMaterial("peridot", 8, 8, 4.5);
		ruby = addMaterial("ruby", 8, 8, 4.5);
		sapphire = addMaterial("sapphire", 8, 8, 4.5);
		spinel = addMaterial("spinel", 8, 8, 4.5);
		tanzanite = addMaterial("tanzanite", 8, 8, 4.5);
		topaz = addMaterial("topaz", 8, 8, 4.5);
		turquoise = addMaterial("turquoise", 8, 8, 4.5);
		violetsapphire = addMaterial("violetsapphire", 8, 8, 4.5);

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
