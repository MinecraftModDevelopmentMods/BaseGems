package zone.moddev.mc.basegems.proxy;

import com.mcmoddev.lib.client.registrations.RegistrationHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.client.BaseGemsConfigScreen;
import zone.moddev.mc.basegems.init.Blocks;
import zone.moddev.mc.basegems.init.Fluids;
import zone.moddev.mc.basegems.init.Items;
import zone.moddev.mc.orespawn.api.client.WorldSettingsExtensionRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        WorldSettingsExtensionRegistry.registerConfigScreen(BaseGems.MODID, BaseGemsConfigScreen::new);
    }

    @SubscribeEvent
    public void modelRegistry(ModelRegistryEvent event) {
        Items.getItemRegistry().forEach((name, item) -> {
            if (owned(item.getRegistryName())) RegistrationHelper.registerItemRender(name);
        });
        Blocks.getBlockRegistry().forEach((name, block) -> {
            if (owned(block.getRegistryName())) RegistrationHelper.registerBlockRender(name);
        });
        Fluids.getFluidBlockRegistry().forEach((name, block) -> {
            if (owned(block.getRegistryName())) RegistrationHelper.registerFluidRender(name);
        });
    }

    private static boolean owned(ResourceLocation name) {
        return name != null && BaseGems.MODID.equals(name.getNamespace());
    }
}
