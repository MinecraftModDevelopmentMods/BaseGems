package mmd.basegems.init;

import mmd.basegems.util.Config.Options;
import net.minecraft.block.*;

/**
 * This class initializes all blocks in Base Gems.
 *
 * @author DrCyano
 *
 */
public class Blocks extends cyano.basemetals.init.Blocks {

	public static Block agate_bars;
	public static Block agate_block;
	public static Block agate_plate;
	public static BlockDoor agate_door;
	public static Block agate_ore;
	public static Block agate_trapdoor;

	private static boolean initDone = false;

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Materials.init();
		ItemGroups.init();

		String materialName;

		if (Options.ENABLE_AGATE) {

			materialName = "agate";

			agate_block = createBlock(materialName);
			agate_plate = createPlate(materialName);
			agate_ore = createOre(materialName);
			agate_bars = createBars(materialName);
			agate_door = createDoor(materialName);
			agate_trapdoor = createTrapDoor(materialName);
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

		initDone = true;
	}
}
