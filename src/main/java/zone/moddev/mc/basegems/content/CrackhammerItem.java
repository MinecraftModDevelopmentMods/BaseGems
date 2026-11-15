package zone.moddev.mc.basegems.content;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import zone.moddev.mc.basegems.data.Gem;
import zone.moddev.mc.basegems.recipe.CrushingRecipe;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public final class CrackhammerItem extends ItemTool implements MaterialBacked {
    private final Gem material;

    public CrackhammerItem(Gem material, Item.Properties properties) {
        super(material.crackhammerAttackDamage() - material.baseAttackDamage(), -3.5F,
                new MaterialTier(material), Collections.<Block>emptySet(),
                properties.defaultMaxDamage(material.crackhammerDurability())
                        .addToolType(ToolType.PICKAXE, material.toolLevel()));
        this.material = material;
    }

    @Override public Gem gem() { return material; }

    @Override public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return canHarvestBlock(state)
                ? material.crackhammerDestroySpeed() : 1.0F;
    }

    @Override public boolean canHarvestBlock(IBlockState state) {
        if (state.getHarvestTool() == ToolType.PICKAXE) return material.toolLevel() >= state.getHarvestLevel();
        Material blockMaterial = state.getMaterial();
        return blockMaterial == Material.ROCK || blockMaterial == Material.IRON
                || blockMaterial == Material.ANVIL;
    }


    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        EntityPlayer player = context.getPlayer();
        if (context.getFace() != EnumFacing.UP || world.isRemote || player == null) return EnumActionResult.PASS;

        AxisAlignedBB area = new AxisAlignedBB(context.getPos().up());
        List<EntityItem> entities = world.getEntitiesWithinAABB(EntityItem.class, area);
        for (EntityItem entity : entities) {
            ItemStack input = entity.getItem();
            IRecipe recipe = CrushingRecipe.find(world, input);
            if (recipe == null || !canCrushDroppedBlock(input)) continue;
            int requested = player.isSneaking() ? input.getCount() : 1;
            ItemStack hammer = context.getItem();
            int durability = hammer.isDamageable()
                    ? Math.max(0, hammer.getMaxDamage() - hammer.getDamage()) : requested;
            int operations = Math.min(requested, durability);
            if (operations <= 0) break;
            ItemStack output = recipe.getRecipeOutput().copy();
            input.shrink(operations);
            if (input.isEmpty()) entity.remove(); else entity.setItem(input);
            spawnOutputs(world, entity, output, operations);
            hammer.damageItem(operations, player);
            world.playSound(null, context.getPos(), net.minecraft.init.SoundEvents.BLOCK_GRAVEL_BREAK,
                    SoundCategory.BLOCKS, 0.5F, 0.5F + world.rand.nextFloat() * 0.3F);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    private boolean canCrushDroppedBlock(ItemStack input) {
        if (!(input.getItem() instanceof ItemBlock)) return true;
        return canHarvestBlock(((ItemBlock) input.getItem()).getBlock().getDefaultState());
    }

    private static void spawnOutputs(World world, EntityItem source, ItemStack result, int operations) {
        int remaining = result.getCount() * operations;
        while (remaining > 0) {
            ItemStack output = result.copy();
            output.setCount(Math.min(output.getMaxStackSize(), remaining));
            remaining -= output.getCount();
            EntityItem crushed = new EntityItem(world, source.posX, source.posY, source.posZ, output);
            crushed.setDefaultPickupDelay();
            world.spawnEntity(crushed);
        }
    }
}
