package com.mcmoddev.basegems.proxy;

import java.util.HashSet;

import com.mcmoddev.basegems.BaseGems;
import com.mcmoddev.basegems.init.*;
import com.mcmoddev.basegems.util.Config;
import com.mcmoddev.lib.integration.IntegrationManager;
import com.mcmoddev.lib.oregen.FallbackGenerator;
import com.mcmoddev.lib.util.ConfigBase.Options;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MissingModsException;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;

/**
 * Base Gems Common Proxy
 *
 * @author Jasmine Iwanek
 *
 */
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {

		Config.init();

		if ((Options.requireMMDOreSpawn()) && (!Loader.isModLoaded("orespawn"))) {
			if(Options.fallbackOrespawn()) {
				GameRegistry.registerWorldGenerator(new FallbackGenerator(), 0);
			} else {
				final HashSet<ArtifactVersion> orespawnMod = new HashSet<>();
				orespawnMod.add(new DefaultArtifactVersion("3.2.0"));
				throw new MissingModsException(orespawnMod, "orespawn", "MMD Ore Spawn Mod (fallback generator disabled, MMD OreSpawn enabled)");
			}
		}

		Materials.init();
		Fluids.init();
		ItemGroups.init();
		Blocks.init();
		Items.init();
		VillagerTrades.init();

		IntegrationManager.INSTANCE.preInit(event);
		IntegrationManager.INSTANCE.runCallbacks("preInit");
		MinecraftForge.EVENT_BUS.register(com.mcmoddev.basegems.proxy.CommonProxy.class);
	}

	@SubscribeEvent
	public void onRemapBlock(RegistryEvent.MissingMappings<Block> event) {
		for (final RegistryEvent.MissingMappings.Mapping<Block> mapping : event.getAllMappings()) {
			if (mapping.key.getResourceDomain().equals(BaseGems.MODID)) {
				// we have nothing in BaseGems that needs this right now
			}
		}
	}

	@SubscribeEvent
	public void onRemapItem(RegistryEvent.MissingMappings<Item> event) {
		for (final RegistryEvent.MissingMappings.Mapping<Item> mapping : event.getAllMappings()) {
			if (mapping.key.getResourceDomain().equals(BaseGems.MODID)) {
				// we have nothing in BaseGems that needs this right now
			}
		}
	}

	public void init(FMLInitializationEvent event) {
		Recipes.init();

		ItemGroups.setupIcons();
		
	}

	public void postInit(FMLPostInitializationEvent event) {
		Config.postInit();
	}
}
