package zone.moddev.mc.basegems.content;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.data.Gem;
import zone.moddev.mc.lib.MMDLib;
import zone.moddev.mc.lib.api.MaterialExtensions;
import zone.moddev.mc.lib.common.factories.MaterialFactory;
import zone.moddev.mc.lib.common.runtime.RuntimeContentRegistrar;

/**
 * Base Gems content. The gems, their items and blocks are MMDLib materials under
 * {@code mmdlib/material/}, registered by MMDLib's runtime registrar. Molten gems stay
 * Base Gems fluids, so their IDs match older saves; the {@code basegems:bucket} form
 * fills them.
 */
public final class ModContent {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, BaseGems.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, BaseGems.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, BaseGems.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, BaseGems.MOD_ID);

    private static final Map<String, FluidContent> FLUID_CONTENT = new LinkedHashMap<>();
    private static RuntimeContentRegistrar registrar;

    private ModContent() {}

    public static void register(IEventBus bus) {
        // The bucket and ore forms are named by the gem materials, so they must exist
        // before MMDLib decodes them in its deferred construction work.
        GemBucketItem.registerImplementation();
        GemOreBlock.registerImplementation();
        registrar = new RuntimeContentRegistrar(BaseGems.MOD_ID, MaterialExtensions.GLOBAL, bus);
        BLOCKS.register(bus);
        ITEMS.register(bus);
        FLUIDS.register(bus);
        FLUID_TYPES.register(bus);
        bus.addListener(GemBucketItem::registerCapabilities);
        // MMDLib loads definitions after mod construction; registries open after that.
        bus.addListener(NewRegistryEvent.class, event -> populate());
    }

    private static void populate() {
        for (Gem gem : Gem.ALL) {
            MaterialFactory.Result result = MMDLib.loadedMaterials().get(gem.id());
            if (result == null) {
                throw new IllegalStateException("MMDLib did not load the Base Gems material " + gem.id());
            }
            registerFluid(gem);
            registrar.queue(result);
        }
        LegacyIds.register();
    }

    private static void registerFluid(Gem gem) {
        String name = gem.name();
        ResourceLocation id = gem.id();
        DeferredHolder<Fluid, BaseFlowingFluid.Source> source = DeferredHolder.create(Registries.FLUID, id);
        DeferredHolder<Fluid, BaseFlowingFluid.Flowing> flowing =
                DeferredHolder.create(Registries.FLUID, id.withPrefix("flowing_"));
        DeferredHolder<Block, MoltenGemBlock> block = DeferredHolder.create(Registries.BLOCK, id);
        // The basegems:bucket item form registers this ID through MMDLib.
        DeferredHolder<Item, GemBucketItem> bucket = DeferredHolder.create(Registries.ITEM, id.withSuffix("_bucket"));
        DeferredHolder<FluidType, GemFluidType> type = FLUID_TYPES.register(name, () -> new GemFluidType(
                FluidType.Properties.create().density(3000).viscosity(6000).temperature(1300)
                        .lightLevel(10).canSwim(false).canDrown(true).canExtinguish(false).canHydrate(false)
                        .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                        .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA),
                gem.colour() & 0xFFFFFF));
        // Molten gems flow slowly and never form new sources.
        BaseFlowingFluid.Properties properties = new BaseFlowingFluid.Properties(type, source, flowing)
                .bucket(bucket).block(block)
                .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(30).explosionResistance(100.0F);
        FLUIDS.register(name, () -> new BaseFlowingFluid.Source(properties));
        FLUIDS.register("flowing_" + name, () -> new BaseFlowingFluid.Flowing(properties));
        BLOCKS.register(name, () -> new MoltenGemBlock(source.get(), BlockBehaviour.Properties.of()
                .mapColor(MapColor.FIRE).replaceable().noCollission().strength(100.0F)
                .lightLevel(state -> 10).pushReaction(net.minecraft.world.level.material.PushReaction.DESTROY)
                .noLootTable().liquid()));
        // Older versions registered an item for every molten block. Keep it, hidden, so
        // stacks in old saves survive.
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        FLUID_CONTENT.put(name, new FluidContent(type, source, flowing, block, bucket));
    }

    public static RuntimeContentRegistrar registrar() {
        return registrar;
    }

    public static Map<String, FluidContent> fluids() {
        return Collections.unmodifiableMap(FLUID_CONTENT);
    }

    /** A Base Gems item, such as {@code ruby_pickaxe}, once registries are populated. */
    public static Item item(String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(BaseGems.MOD_ID, path);
        DeferredHolder<Item, ? extends Item> item = registrar.items().get(id);
        if (item == null) throw new IllegalArgumentException("Unknown Base Gems item " + id);
        return item.get();
    }
}
