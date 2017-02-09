package com.mcmoddev.basegems.integration.plugins;

import com.mcmoddev.basegems.init.Materials;
import com.mcmoddev.basegems.integration.BaseGemsPlugin;
import com.mcmoddev.basegems.util.Config.Options;
import com.mcmoddev.lib.integration.IIntegration;
import com.mcmoddev.lib.material.MetalMaterial;

import net.minecraft.item.ItemStack;

@BaseGemsPlugin(Mekanism.PLUGIN_MODID)
public class Mekanism extends com.mcmoddev.lib.integration.plugins.Mekanism implements IIntegration {

	private static boolean initDone = false;

	@Override
	public void init() {
		if (initDone || !com.mcmoddev.basemetals.util.Config.Options.enableMekanism) {
			return;
		}

		MetalMaterial material;

		if (Options.ENABLE_AGATE) {
			material = Materials.agate;
			addCrusherRecipe(new ItemStack(material.clump), new ItemStack(material.powder_dirty));
			addCrusherRecipe(new ItemStack(material.ingot), new ItemStack(material.powder));

			addEnrichmentChamberRecipe(new ItemStack(material.ore), new ItemStack(material.powder, 2));
			addEnrichmentChamberRecipe(new ItemStack(material.powder_dirty), new ItemStack(material.powder));

			addPurificationChamberRecipe(new ItemStack(material.ore), new ItemStack(material.clump, 3));
			addPurificationChamberRecipe(new ItemStack(material.shard), new ItemStack(material.clump));

			addChemicalInjectionChamberRecipe(new ItemStack(material.ore), new ItemStack(material.shard, 4));
			addChemicalInjectionChamberRecipe(new ItemStack(material.crystal), new ItemStack(material.shard));
		}

		initDone = true;
	}
}
