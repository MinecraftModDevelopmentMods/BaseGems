package zone.moddev.mc.basegems.content;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.data.Gem;

@Mod.EventBusSubscriber(modid = BaseGems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModContent {
    private static final Map<String, Block> BLOCKS = new LinkedHashMap<>();
    private static final Map<String, Item> ITEMS = new LinkedHashMap<>();
    private static final Map<String, Supplier<? extends Item>> ITEM_FACTORIES = new LinkedHashMap<>();
    private static final Map<String, FluidContent> FLUIDS = new LinkedHashMap<>();
    private static final Map<Fluid, Integer> FLUID_COLOURS = new IdentityHashMap<>();
    private static final ItemGroup GEMS = new ItemGroup("basegems") {
        @Override public ItemStack createIcon() { return new ItemStack(item("ruby_ingot")); }
    };

    private ModContent() {}

    public static Map<String, Block> blocks() { return Collections.unmodifiableMap(BLOCKS); }
    public static Map<String, Item> items() { return Collections.unmodifiableMap(ITEMS); }

    public static ResourceLocation id(String path) { return new ResourceLocation(BaseGems.MOD_ID, path); }
    public static Block block(String path) { return BLOCKS.get(path); }
    public static Item item(String path) { return ITEMS.get(path); }
    public static FluidContent fluid(String path) { return FLUIDS.get(path); }
    public static Map<String, FluidContent> fluids() { return Collections.unmodifiableMap(FLUIDS); }
    public static boolean isBaseGemsFluid(Fluid fluid) { return FLUID_COLOURS.containsKey(fluid); }
    public static int fluidColour(Fluid fluid) { return FLUID_COLOURS.getOrDefault(fluid, 0xffffff); }

    // Vanilla's fluid registries are shared, so use Forge's sequential block event.
    private static void initializeFluids() {
        for (Gem gem : Gem.ALL) {
            String name = gem.name();
            FluidContent fluid = new FluidContent(new RegistryHandle<>(name),
                    new RegistryHandle<>("flowing_" + name), new RegistryHandle<>(name),
                    new RegistryHandle<>(name + "_bucket"));
            FLUIDS.put(name, fluid);
            FlowingFluid source = new MoltenFluid.Source(name);
            FlowingFluid flowing = new MoltenFluid.Flowing(name);
            fluid.source().bind(source);
            fluid.flowing().bind(flowing);
            source.setRegistryName(id(name));
            flowing.setRegistryName(id("flowing_" + name));
            for (Fluid type : new Fluid[]{source, flowing}) {
                FLUID_COLOURS.put(type, gem.colour() & 0xffffff);
                for (IFluidState state : type.getStateContainer().getValidStates()) {
                    Fluid.STATE_REGISTRY.add(state);
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerFluids(RegistryEvent.Register<Fluid> event) {
        for (FluidContent content : FLUIDS.values()) {
            event.getRegistry().registerAll(content.source().get(), content.flowing().get());
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        initializeFluids();
        for (Gem gem : Gem.ALL) {
            String name = gem.name();
            GemBlocks.create(gem).forEach((path, value) -> {
                BLOCKS.put(path, value);
                event.getRegistry().register(value.setRegistryName(id(path)));
                if (!path.startsWith("double_")) {
                    ITEM_FACTORIES.put(path, () -> new BlockItem(value, new Item.Properties().group(GEMS)));
                }
            });
            FluidContent fluid = fluid(name);
            FlowingFluidBlock molten = new MoltenGemBlock(fluid.source().get());
            fluid.block().bind(molten);
            BLOCKS.put(name, molten);
            event.getRegistry().register(molten.setRegistryName(id(name)));
            // The pre-flattening versions registered an item for the fluid block.
            ITEM_FACTORIES.put(name, () -> new BlockItem(molten, new Item.Properties()));
            ITEM_FACTORIES.put(name + "_bucket", () -> {
                Item bucket = new GemBucketItem(fluid.source().get(),
                        new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(GEMS));
                fluid.bucket().bind(bucket);
                return bucket;
            });
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Gem gem : Gem.ALL) GemItems.add(gem, GEMS, ITEM_FACTORIES);
        ITEM_FACTORIES.forEach((path, factory) -> {
            Item item = factory.get();
            ITEMS.put(path, item);
            event.getRegistry().register(item.setRegistryName(id(path)));
        });
    }

    private static final class MoltenGemBlock extends FlowingFluidBlock {
        private MoltenGemBlock(FlowingFluid fluid) {
            super(fluid, Block.Properties.create(Material.LAVA).doesNotBlockMovement()
                    .hardnessAndResistance(100).variableOpacity());
        }
    }
}
