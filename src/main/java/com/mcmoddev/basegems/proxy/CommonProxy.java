package com.mcmoddev.basegems.proxy;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.init.*;
import com.mcmoddev.basegems.util.Config;
import com.mcmoddev.basegems.util.EventHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Base Gems Common Proxy
 *
 * @author Jasmine Iwanek
 *
 */
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {

		Config.init();

		Fluids.init();
		Materials.init();
		ItemGroups.init();
		Blocks.init();
		Items.init();
		VillagerTrades.init();

		FMLInterModComms.sendFunctionMessage("orespawn", "api", "mmd.orespawn.BaseGemsOreSpawn");

		/*
		if ((Loader.isModLoaded("EnderIO")) && Options.ENABLE_ENDER_IO) {
			EnderIO.init();
		}
		if ((Loader.isModLoaded("IC2")) && Options.ENABLE_IC2) {
			//IC2.init();
		}
		if ((Loader.isModLoaded("tconstruct")) && Options.ENABLE_TINKERS_CONSTRUCT) {
			TinkersConstruct.init();
		}
		if ((Loader.isModLoaded("Mekanism")) && Options.ENABLE_MEKANISM) {
			Mekanism.init();
		}
		if ((Loader.isModLoaded("thaumcraft")) && Options.ENABLE_THAUMCRAFT) {
			//Thaumcraft.init();
		}
		if ((Loader.isModLoaded("veinminer")) && Options.ENABLE_VEINMINER) {
			VeinMiner.init();
		}
		*/
	}

	public void onRemap(FMLMissingMappingsEvent event) {
		for (final MissingMapping mapping : event.get()) {
			if (mapping.resourceLocation.getResourceDomain().equals(BaseGems.MODID)) {
				if (mapping.type.equals(GameRegistry.Type.BLOCK)) {
				} else if (mapping.type.equals(GameRegistry.Type.ITEM)) {
				}
			}
		}
	}

	public void init(FMLInitializationEvent event) {
		Recipes.init();

		Achievements.init();

		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	public void postInit(FMLPostInitializationEvent event) {
		Config.postInit();
	}
}
