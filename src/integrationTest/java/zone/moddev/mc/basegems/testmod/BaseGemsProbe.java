package zone.moddev.mc.basegems.testmod;

import java.nio.file.*;
import java.util.*;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.*;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.properties.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.registries.ForgeRegistries;
import zone.moddev.mc.basegems.config.GemConfig;
import zone.moddev.mc.basegems.content.ModContent;
import zone.moddev.mc.basegems.data.Gem;
import zone.moddev.mc.basegems.data.GemRarity;
import zone.moddev.mc.basegems.network.GameplaySettingsNetwork;
import zone.moddev.mc.basegems.recipe.CrushingRecipe;

/** Explicit native tests; never included in the player jar. */
@Mod("basegemsprobe")
public final class BaseGemsProbe {
    private int checks;
    public BaseGemsProbe() { MinecraftForge.EVENT_BUS.addListener(this::started); }
    private void require(boolean pass, String message) { checks++; if (!pass) throw new IllegalStateException(message); }
    private static ResourceLocation id(String path) { return new ResourceLocation("basegems", path); }
    private static ItemStack item(String path) { return new ItemStack(ModContent.item(path)); }
    private static CraftingInventory grid(int width, int height) {
        return new CraftingInventory(new Container(null, -1) {
            @Override public boolean canInteractWith(net.minecraft.entity.player.PlayerEntity player) { return false; }
        }, width, height);
    }
    private void started(FMLServerStartedEvent event) {
        MinecraftServer server = event.getServer(); JsonObject receipt = new JsonObject();
        try {
            ServerWorld world = server.getWorld(DimensionType.OVERWORLD);
            require(Gem.ALL.size() == 33, "33 gems");
            registryAndLoot(world);
            recipes(server, world);
            policies();
            if (!Boolean.getBoolean("basegemsprobe.upgrade")) {
                discovery(server, world);
                furnace(world);
                trades();
                ores(server, receipt);
            }
            BlockPos marker = new BlockPos(8, 180, 8);
            if (Files.exists(Paths.get("saved-marker.txt"))) {
                require(world.getBlockState(marker).getBlock() == ModContent.block("ruby_block"), "Saved marker survives reopening");
            } else {
                world.setBlockState(marker, ModContent.block("ruby_block").getDefaultState(), 3);
                Files.write(Paths.get("saved-marker.txt"), new byte[]{1});
            }
            receipt.addProperty("status", "passed");
        } catch (Throwable failure) {
            receipt.addProperty("status", "failed"); receipt.addProperty("failure", failure.toString()); failure.printStackTrace();
        } finally {
            receipt.addProperty("checks", checks); receipt.addProperty("fantasy", GemConfig.activeMode());
            receipt.addProperty("baseMetals", ModList.get().isLoaded("basemetals"));
            receipt.addProperty("modernMetals", ModList.get().isLoaded("modernmetals"));
            try { Files.write(Paths.get("server-probe.json"), receipt.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8)); }
            catch (Exception failure) { throw new RuntimeException(failure); }
            server.initiateShutdown(false);
        }
    }
    private void registryAndLoot(ServerWorld world) {
        ItemStack silk = new ItemStack(Items.DIAMOND_PICKAXE); silk.addEnchantment(Enchantments.SILK_TOUCH, 1);
        ItemStack fortune = new ItemStack(Items.DIAMOND_PICKAXE); fortune.addEnchantment(Enchantments.FORTUNE, 3);
        for (Gem gem : Gem.ALL) {
            String name = gem.name();
            for (String form : new String[]{"block", "plate", "bars", "door", "trapdoor", "button", "slab", "lever", "pressure_plate", "stairs", "wall"}) {
                Block block = ModContent.block(name + "_" + form); require(block != null, "Block ID " + name + "_" + form);
                BlockState state = block.getDefaultState();
                require(block.getHarvestLevel(state) == gem.requiredHarvestLevel(), "Explicit mining level " + name + "_" + form);
                if (block instanceof DoorBlock) state = state.with(DoorBlock.HALF, DoubleBlockHalf.LOWER);
                drop(world, state, new ItemStack(Items.DIAMOND_PICKAXE), name + "_" + form, 1);
                if (block instanceof DoorBlock) require(drops(world, state.with(DoorBlock.HALF, DoubleBlockHalf.UPPER), silk).isEmpty(), "No duplicate door drop");
                if (block instanceof SlabBlock) drop(world, state.with(SlabBlock.TYPE, SlabType.DOUBLE), silk, name + "_slab", 2);
            }
            drop(world, ModContent.block("double_" + name + "_slab").getDefaultState(), silk, name + "_slab", 2);
            Block ore = ModContent.block(name + "_ore"); BlockState state = ore.getDefaultState();
            drop(world, state, new ItemStack(Items.DIAMOND_PICKAXE), name + "_ingot", 1);
            drop(world, state, silk, name + "_ore", 1);
            boolean bonus = false;
            for (int trial = 0; trial < 24; trial++) {
                List<ItemStack> stacks = drops(world, state, fortune);
                require(stacks.size() == 1 && stacks.get(0).getItem() == ModContent.item(name + "_ingot"), "Fortune gem output");
                bonus |= stacks.get(0).getCount() > 1;
            }
            require(bonus, "Fortune affects " + name);
            GemRarity rarity = GemRarity.of(name);
            int xp = ore.getExpDrop(state, world, new BlockPos(0, 100, 0), 0, 0);
            require(xp >= rarity.minXp && xp <= rarity.maxXp, "Mining XP " + name);
            require(ore.getExpDrop(state, world, new BlockPos(0, 100, 0), 0, 1) == 0, "Silk Touch suppresses XP");
            require(ore.getHarvestLevel(state) == gem.requiredHarvestLevel(), "Ore mining level " + name);
            require(ModContent.fluid(name).source().get().getAttributes().getColor() == (0xff000000 | gem.colour()), "Fluid colour " + name);
            require(ModContent.fluid(name).source().get().getAttributes().getStillTexture().equals(id("block/molten_metal_still")), "Fluid sprite");
            for (String armor : new String[]{"helmet", "chestplate", "leggings", "boots", "horse_armor"}) require(!ForgeRegistries.ITEMS.containsKey(id(name + "_" + armor)), "No armour " + name);
        }
    }
    private List<ItemStack> drops(ServerWorld world, BlockState state, ItemStack tool) {
        return Block.getDrops(state, world, new BlockPos(0, 100, 0), null, FakePlayerFactory.getMinecraft(world), tool);
    }
    private void drop(ServerWorld world, BlockState state, ItemStack tool, String name, int count) {
        List<ItemStack> stacks = drops(world, state, tool);
        require(stacks.size() == 1 && stacks.get(0).getItem() == ModContent.item(name) && stacks.get(0).getCount() == count, "Loot " + state + " -> " + stacks);
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void recipes(MinecraftServer server, ServerWorld world) {
        int total = 0, normal = 0, crushing = 0;
        for (IRecipe<?> recipe : server.getRecipeManager().getRecipes()) {
            if (!recipe.getId().getNamespace().equals("basegems")) continue;
            total++;
            if (Registry.RECIPE_TYPE.getKey(recipe.getType()).getPath().equals("crushing")) { crushing++; continue; }
            if (!recipe.isDynamic()) { normal++; require(server.getAdvancementManager().getAdvancement(id("recipes/" + recipe.getId().getPath())) != null, "Discovery " + recipe.getId()); }
            if (recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe) {
                CraftingInventory input = grid(3, 3); int width = recipe instanceof ShapedRecipe ? ((ShapedRecipe) recipe).getWidth() : 3;
                int slot = 0;
                for (Ingredient ingredient : recipe.getIngredients()) {
                    if (!ingredient.hasNoMatchingItems()) input.setInventorySlotContents((slot / width) * 3 + slot % width, ingredient.getMatchingStacks()[0].copy());
                    slot++;
                }
                boolean allowed = GemConfig.allows(recipe.getRecipeOutput().getItem().getRegistryName().getPath());
                require(((IRecipe) recipe).matches(input, world) == allowed, "Crafting policy " + recipe.getId());
            }
        }
        require(total == 1353, "1353 recipe IDs, got " + total); require(crushing == 132, "132 shared crushing contributions");
        for (Gem gem : Gem.ALL) {
            String name = gem.name();
            IRecipe<?> repair = server.getRecipeManager().getRecipe(id(name + "_repair_shield")).get();
            require(repair.isDynamic(), "Manual repair stays out of book");
            ItemStack shield = item(name + "_shield"); shield.setDamage(20); shield.setDisplayName(new net.minecraft.util.text.StringTextComponent("Saved shield")); shield.addEnchantment(Enchantments.UNBREAKING, 2);
            CraftingInventory input = grid(2, 1); input.setInventorySlotContents(0, shield); input.setInventorySlotContents(1, item(name + "_plate"));
            boolean allowed = GemConfig.allows(name + "_shield"); require(((IRecipe) repair).matches(input, world) == allowed, "Repair policy " + name);
            if (allowed) {
                ItemStack result = ((IRecipe) repair).getCraftingResult(input); ItemStack expected = shield.copy(); expected.setDamage(0);
                require(ItemStack.areItemStacksEqual(result, expected), "Repair preserves components");
            }
            for (String form : new String[]{"ore", "powder"}) for (String method : new String[]{"smelting", "blasting"}) {
                IRecipe<?> recipe = server.getRecipeManager().getRecipe(id(name + "_" + form + "_" + method)).get();
                require(((IRecipe) recipe).matches(new Inventory(item(name + "_" + form)), world) == GemConfig.enabled(name), "Furnace policy " + name);
                require(recipe.getType() == (method.equals("smelting") ? IRecipeType.SMELTING : IRecipeType.BLASTING), "Native furnace type");
            }
            require(CrushingRecipe.find(world, item(name + "_ore")) != null == GemConfig.enabled(name), "Shared crushing " + name);
        }
        System.out.println("BASEGEMS_RECIPES total=" + total + " normal=" + normal + " crushing=" + crushing);
    }
    private void policies() {
        long mask = (1L << Gem.ALL.size()) - 1;
        require(GameplaySettingsNetwork.requiredChanges("high_fantasy", mask, "high_fantasy", mask).isEmpty(), "Matching policy");
        for (int index = 0; index < Gem.ALL.size(); index++) require(GameplaySettingsNetwork.requiredChanges("high_fantasy", mask, "high_fantasy", mask ^ (1L << index)).equals("gems." + Gem.ALL.get(index).configKey() + "=true"), "Gem toggle " + index);
    }
    private void discovery(MinecraftServer server, ServerWorld world) {
        ServerPlayerEntity player = new ServerPlayerEntity(server, world, new GameProfile(UUID.randomUUID(), "GemDiscovery"), new net.minecraft.server.management.PlayerInteractionManager(world));
        player.connection = new net.minecraft.network.play.ServerPlayNetHandler(server, new net.minecraft.network.NetworkManager(net.minecraft.network.PacketDirection.SERVERBOUND), player);
        player.inventory.setInventorySlotContents(0, new ItemStack(Items.EMERALD));
        net.minecraft.advancements.CriteriaTriggers.INVENTORY_CHANGED.trigger(player, player.inventory);
        IRecipe<?> recipe = server.getRecipeManager().getRecipe(id("ruby_block")).get();
        require(player.getRecipeBook().isUnlocked(recipe) == GemConfig.enabled("ruby"), "Foreign tagged ruby discovery");
        player.getAdvancements().dispose();
    }
    private void furnace(ServerWorld world) {
        BlockPos pos = new BlockPos(16, 180, 16);
        // Reopening the probe world must not reuse the previous furnace inventory.
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        world.setBlockState(pos, Blocks.FURNACE.getDefaultState(), 3);
        net.minecraft.tileentity.FurnaceTileEntity furnace = (net.minecraft.tileentity.FurnaceTileEntity) world.getTileEntity(pos);
        furnace.setInventorySlotContents(0, new ItemStack(ModContent.item("agate_powder"), 2)); furnace.setInventorySlotContents(1, new ItemStack(Items.COAL));
        for (int tick = 0; tick < 420; tick++) furnace.tick();
        require(furnace.getStackInSlot(2).getCount() == 2 && furnace.getStackInSlot(2).getItem() == ModContent.item("agate_ingot"), "Furnace accumulation");
    }
    private void trades() {
        for (net.minecraft.entity.merchant.villager.VillagerProfession profession : new net.minecraft.entity.merchant.villager.VillagerProfession[]{net.minecraft.entity.merchant.villager.VillagerProfession.ARMORER, net.minecraft.entity.merchant.villager.VillagerProfession.TOOLSMITH, net.minecraft.entity.merchant.villager.VillagerProfession.WEAPONSMITH}) {
            int offers = 0;
            for (net.minecraft.entity.merchant.villager.VillagerTrades.ITrade[] level : net.minecraft.entity.merchant.villager.VillagerTrades.VILLAGER_DEFAULT_TRADES.get(profession).values()) {
                for (net.minecraft.entity.merchant.villager.VillagerTrades.ITrade factory : level) if (factory.getClass().getName().startsWith("zone.moddev.mc.basegems.")) {
                    MerchantOffer offer = factory.getOffer(null, new Random(1)); if (offer == null) continue; offers++;
                    ItemStack result = offer.getSellingStack(); require(result.getItem() == Items.EMERALD || GemConfig.allows(result.getItem().getRegistryName().getPath()), "Trade policy");
                    require(offer.func_222214_i() == 6 && offer.getGivenExp() > 0, "Restock and XP");
                }
            }
            require(offers > 0, "Smith gem offers");
        }
    }
    private void ores(MinecraftServer server, JsonObject receipt) {
        JsonObject sample = new JsonObject();
        for (DimensionType dimension : new DimensionType[]{DimensionType.OVERWORLD, DimensionType.THE_NETHER, DimensionType.THE_END}) {
            ServerWorld world = server.getWorld(dimension); Map<String, Integer> counts = new TreeMap<>();
            for (int cx = 64; cx < 67; cx++) for (int cz = 64; cz < 67; cz++) for (net.minecraft.world.chunk.ChunkSection section : world.getChunk(cx, cz).getSections()) {
                if (section == null || section.isEmpty()) continue;
                for (int x = 0; x < 16; x++) for (int y = 0; y < 16; y++) for (int z = 0; z < 16; z++) {
                    ResourceLocation block = section.getBlockState(x, y, z).getBlock().getRegistryName();
                    if (block.getNamespace().equals("basegems") && block.getPath().endsWith("_ore")) {
                        require(dimension == DimensionType.OVERWORLD && GemConfig.enabled(block.getPath().replace("_ore", "")), "Dimension and enabled ore " + block);
                        counts.merge(block.toString(), 1, Integer::sum);
                    }
                }
            }
            if (dimension == DimensionType.OVERWORLD) require(!counts.isEmpty(), "Fresh gem deposits");
            JsonObject values = new JsonObject(); counts.forEach(values::addProperty); sample.add(DimensionType.getKey(dimension).toString(), values);
        }
        receipt.add("oreSample", sample);
    }
}
