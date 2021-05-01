package com.mcmoddev.basegems.proxy;

import com.mcmoddev.lib.client.registrations.RegistrationHelper;
import com.mcmoddev.basegems.init.Blocks;
import com.mcmoddev.basegems.init.Fluids;
import com.mcmoddev.basegems.init.Items;
import com.mcmoddev.lib.material.MMDMaterial;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Base Gems Client Proxy
 *
 * @author Jasmine Iwanek
 *
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		MinecraftForge.EVENT_BUS.register(this);
	}

	/**
	 * Registers Fluid models for this mod.
	 *
	 * @param event The Event.
	 */
	@SubscribeEvent
	public void fluidRendering(RegistryEvent.Register<MMDMaterial> event) {
		for (final String name : Fluids.getFluidBlockRegistry().keySet()) {
			RegistrationHelper.registerFluidRender(name);
		}
	}

	@SubscribeEvent
	public void modelRegistry(ModelRegistryEvent event) {
		for (final String name : Items.getItemRegistry().keySet()) {
			RegistrationHelper.registerItemRender(name);
		}

		for (final String name : Blocks.getBlockRegistry().keySet()) {
			RegistrationHelper.registerBlockRender(name);
		}
	}
}
