package com.mcmoddev.basegems.util;

import java.util.Arrays;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.data.MaterialNames;
import com.mcmoddev.lib.client.renderer.FluidStateMapper;
import com.mcmoddev.lib.data.SharedStrings;
import com.mcmoddev.lib.init.Fluids;
import com.mcmoddev.lib.init.Materials;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid=BaseGems.MODID)
public class EventHandlers {

	protected EventHandlers() {
		throw new IllegalAccessError(SharedStrings.NOT_INSTANTIABLE);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void modelRegistryBits(ModelRegistryEvent ev) {
		Arrays.asList(MaterialNames.AMBER, MaterialNames.ALEXANDRITE, MaterialNames.AGATE, MaterialNames.AMETRINE,
				MaterialNames.AMETHYST, MaterialNames.AQUAMARINE, MaterialNames.BERYL, MaterialNames.BLACKDIAMOND, MaterialNames.BLUETOPAZ,
				MaterialNames.CARNELIAN, MaterialNames.CITRINE, MaterialNames.GARNET, MaterialNames.GOLDENBERYL, MaterialNames.HELIODOR,
				MaterialNames.INDICOLITE, MaterialNames.IOLITE, MaterialNames.JADE, MaterialNames.JASPER, MaterialNames.LEPIDOLITE,
				MaterialNames.MALACHITE, MaterialNames.MOLDAVITE, MaterialNames.MOONSTONE, MaterialNames.MORGANITE, MaterialNames.ONYX,
				MaterialNames.OPAL, MaterialNames.PERIDOT, MaterialNames.RUBY, MaterialNames.SAPPHIRE, MaterialNames.SPINEL,
				MaterialNames.TANZANITE, MaterialNames.TOPAZ, MaterialNames.TURQUOISE, MaterialNames.VIOLETSAPPHIRE).stream()
		.forEach(name -> {
			Materials.getMaterialByName(name).getItems().stream()
			.filter(item -> BaseGems.MODID.equalsIgnoreCase(item.getItem().getRegistryName().getNamespace()))
			.filter(item -> !item.getItem().getRegistryName().getPath().equalsIgnoreCase(name))
			.forEach(item -> ModelLoader.setCustomModelResourceLocation(item.getItem(), 0,
					new ModelResourceLocation(item.getItem().getRegistryName(), "inventory")));

			final Block block = Fluids.getFluidBlockByName(name);
			if(block != null) {
				final Item item = Item.getItemFromBlock(block);
				final ResourceLocation resourceLocation = block.getRegistryName();
				final FluidStateMapper mapper = new FluidStateMapper(
						resourceLocation.getNamespace() + ":" + name);

				if(BaseGems.MODID.equalsIgnoreCase(item.getRegistryName().getNamespace())) {
					if (item != null) {
						ModelBakery.registerItemVariants(item);
						ModelLoader.setCustomMeshDefinition(item, mapper);
					}
					ModelLoader.setCustomStateMapper(block, mapper);
				}
			}
		});
	}
}
