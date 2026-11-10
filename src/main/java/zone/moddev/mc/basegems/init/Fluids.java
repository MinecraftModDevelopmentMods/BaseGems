package zone.moddev.mc.basegems.init;

import zone.moddev.mc.basegems.BaseGems;
import com.mcmoddev.lib.material.MMDMaterial;

/**
 * This class initializes all fluids in Base Gems.
 *
 * @author Jasmine Iwanek
 *
 */
public class Fluids extends com.mcmoddev.lib.init.Fluids {


	private Fluids() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		for (MMDMaterial material : Materials.getMaterialsByMod(BaseGems.MODID)) {
			addFluid(material, 2000, 10000, 330, 10);
			addFluidBlock(material);
		}
	}
}
