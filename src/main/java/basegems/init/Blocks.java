package basegems.init;

import basegems.BaseGems;
import cyano.basemetals.blocks.*;
import cyano.basemetals.material.MetalMaterial;
import cyano.basemetals.registry.IOreDictionaryEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * This class initializes all blocks in Base Gems and provides some utility 
 * methods for looking up blocks. 
 * @author DrCyano
 *
 */
public abstract class Blocks {

	public static Block template_bars;
	public static Block template_block;
	public static Block template_plate;
	public static BlockDoor template_door;
	public static Block template_ore;
	public static Block template_trapdoor;

	private static boolean initDone = false;

//	private static Map<Block, String> blockRegistry = new HashMap<>();
	private static final Map<String, Block> allBlocks = new HashMap<>();
//	private static Map<MetalMaterial, List<Block>> blocksByMetal = new HashMap<>();

	/**
	 * Gets an block by its name. The name is the name as it is registered in 
	 * the GameRegistry, not its unlocalized name (the unlocalized name is the 
	 * registered name plus the prefix "fantasymetals.")
	 * @param name The name of the block in question
	 * @return The block matching that name, or null if there isn't one
	 */
	public static Block getBlockByName(String name) {
		return allBlocks.get(name);
	}

	/**
	 * 
	 */
	public static void init() {
		if(initDone) return;

		basegems.init.Materials.init();
		basegems.init.ItemGroups.init();

		template_block = createBlock(Materials.template);
		template_plate = createPlate(Materials.template);
		template_ore = createOre(Materials.template);
		template_bars = createBars(Materials.template);
		template_door = createDoor(Materials.template);
		template_trapdoor = createTrapDoor(Materials.template);

		// final block settings
		for(Block b : allBlocks.values()) {
			if(b instanceof IOreDictionaryEntry) { OreDictionary.registerOre(((IOreDictionaryEntry)b).getOreDictionaryName(), b); }
			if(!(b instanceof BlockMetalDoor)) b.setCreativeTab(ItemGroups.tab_blocks);
		}

		initDone = true;
	}

	private static Block addBlock(Block block, String name) {
		ResourceLocation location = new ResourceLocation(BaseGems.MODID, name);
		block.setRegistryName(location);
		block.setUnlocalizedName(location.toString());
		GameRegistry.register(block);

		if (!(block instanceof BlockMetalDoor)) {
			ItemBlock itemBlock = new ItemBlock(block);
			itemBlock.setRegistryName(location);
			itemBlock.setUnlocalizedName(location.toString());
			GameRegistry.register(itemBlock);
		}

		allBlocks.put(name, block);
		return block;
	}

	private static Block createPlate(MetalMaterial metal) {
		return addBlock(new BlockMetalPlate(metal), metal.getName()+"_plate");
	}

	private static Block createBars(MetalMaterial metal) {
		return addBlock(new BlockMetalBars(metal), metal.getName()+"_bars");
	}

	private static Block createBlock(MetalMaterial metal) {
		return createBlock(metal, false);
	}

	private static Block createBlock(MetalMaterial metal, boolean glow) {
		return addBlock(new BlockMetalBlock(metal, glow), metal.getName()+"_block");
	}

	private static Block createOre(MetalMaterial metal) {
		return addBlock(new BlockMetalOre(metal), metal.getName()+"_ore");
	}

	private static BlockDoor createDoor(MetalMaterial metal) {
		return (BlockDoor)addBlock(new basegems.blocks.BlockMetalDoor(metal), metal.getName()+"_door");
	}

	private static Block createTrapDoor(MetalMaterial metal) {
		return addBlock(new BlockMetalTrapDoor(metal), metal.getName()+"_trapdoor");
	}

	/**
	 * 
	 * @param event
	 */
	@SideOnly(Side.CLIENT)
	public static void registerItemRenders(FMLInitializationEvent event) {
		for(String name : allBlocks.keySet()) {
			if(allBlocks.get(name) instanceof BlockDoor) continue; // do not add door blocks
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(net.minecraft.item.Item.getItemFromBlock(allBlocks.get(name)), 0, 
				new ModelResourceLocation(new ResourceLocation(BaseGems.MODID, name), "inventory"));
		}
	}
}
