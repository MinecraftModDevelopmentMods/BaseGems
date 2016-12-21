package mmd.basegems.init;

import mmd.basegems.util.Config.Options;
import net.minecraft.item.*;

/**
 * This class initializes all items in Base Gems.
 *
 * @author DrCyano
 *
 */
public class Items extends cyano.basemetals.init.Items {

	public static Item agate_arrow;
	public static Item agate_axe;
	public static Item agate_blend;
	public static Item agate_boots;
	public static Item agate_bolt;
	public static Item agate_bow;
	public static Item agate_chestplate;
	public static Item agate_crackhammer;
	public static Item agate_crossbow;
	public static Item agate_door;
	public static Item agate_fishingrod;
	public static Item agate_helmet;
	public static Item agate_hoe;
	public static Item agate_horsearmor;
	public static Item agate_ingot;
	public static Item agate_leggings;
	public static Item agate_nugget;
	public static Item agate_pickaxe;
	public static Item agate_powder;
	public static Item agate_shears;
	public static Item agate_shovel;
	public static Item agate_smallblend;
	public static Item agate_smallpowder;
	public static Item agate_sword;
	public static Item agate_rod;
	public static Item agate_gear;

	private static boolean initDone = false;

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Blocks.init();

		String materialName;

		if (Options.ENABLE_AGATE) {

			materialName = "agate";

			agate_arrow = createArrow(materialName);
			agate_axe = createAxe(materialName);
			agate_blend = createBlend(materialName);
			agate_boots = createBoots(materialName);
			agate_bolt = createBolt(materialName);
			agate_bow = createBow(materialName);
			agate_chestplate = createChestplate(materialName);
			agate_crackhammer = createCrackhammer(materialName);
			agate_crossbow = createCrossbow(materialName);
			agate_door = createDoor(materialName);
			agate_fishingrod = createFishingRod(materialName);
			agate_helmet = createHelmet(materialName);
			agate_hoe = createHoe(materialName);
			agate_horsearmor = createHorseArmor(materialName);
			agate_ingot = createIngot(materialName);
			agate_leggings = createLeggings(materialName);
			agate_nugget = createNugget(materialName);
			agate_pickaxe = createPickaxe(materialName);
			agate_powder = createPowder(materialName);
			agate_shears = createShears(materialName);
			agate_shovel = createShovel(materialName);
			agate_smallblend = createSmallBlend(materialName);
			agate_smallpowder = createSmallPowder(materialName);
			agate_sword = createSword(materialName);
			agate_rod = createRod(materialName);
			agate_gear = createGear(materialName);
		}
		if (Options.ENABLE_ALEXANDRITE) {
			materialName = "alexandrite";
		}
		if (Options.ENABLE_AMBER) {
			materialName = "amber";
		}
		if (Options.ENABLE_AMETHYST) {
			materialName = "amethyst";
		}
		if (Options.ENABLE_AMETRINE) {
			materialName = "ametrine";
		}
		if (Options.ENABLE_AQUAMARINE) {
			materialName = "aquamarine";
		}
		if (Options.ENABLE_BERYL) {
			materialName = "beryl";
		}
		if (Options.ENABLE_BLACKDIAMOND) {
			materialName = "blackdiamond";
		}
		if (Options.ENABLE_BLUETOPAZ) {
			materialName = "bluetopaz";
		}
		if (Options.ENABLE_CARNELIAN) {
			materialName = "carnelian";
		}
		if (Options.ENABLE_CITRINE) {
			materialName = "citrine";
		}
		if (Options.ENABLE_GARNET) {
			materialName = "garnet";
		}
		if (Options.ENABLE_GOLDENBERYL) {
			materialName = "goldenberyl";
		}
		if (Options.ENABLE_HELIODOR) {
			materialName = "heliodor";
		}
		if (Options.ENABLE_INDICOLITE) {
			materialName = "indicolite";
		}
		if (Options.ENABLE_IOLITE) {
			materialName = "iolite";
		}
		if (Options.ENABLE_JADE) {
			materialName = "jade";
		}
		if (Options.ENABLE_JASPER) {
			materialName = "jasper";
		}
		if (Options.ENABLE_LEPIDOLITE) {
			materialName = "lepidolite";
		}
		if (Options.ENABLE_MALACHITE) {
			materialName = "malachite";
		}
		if (Options.ENABLE_MOLDAVITE) {
			materialName = "moldovite";
		}
		if (Options.ENABLE_MOONSTONE) {
			materialName = "moonstone";
		}
		if (Options.ENABLE_MORGANITE) {
			materialName = "morganite";
		}
		if (Options.ENABLE_ONYX) {
			materialName = "onyx";
		}
		if (Options.ENABLE_OPAL) {
			materialName = "opal";
		}
		if (Options.ENABLE_PERIDOT) {
			materialName = "peridot";
		}
		if (Options.ENABLE_RUBY) {
			materialName = "ruby";
		}
		if (Options.ENABLE_SAPPHIRE) {
			materialName = "sapphire";
		}
		if (Options.ENABLE_SPINEL) {
			materialName = "spinel";
		}
		if (Options.ENABLE_TANZANITE) {
			materialName = "tanzanite";
		}
		if (Options.ENABLE_TOPAZ) {
			materialName = "topaz";
		}
		if (Options.ENABLE_TURQUOISE) {
			materialName = "turquoise";
		}
		if (Options.ENABLE_VIOLETSAPPHIRE) {
			materialName = "violetsapphire";
		}

//		addToMetList() // May not be needed, check

		initDone = true;
	}
}
