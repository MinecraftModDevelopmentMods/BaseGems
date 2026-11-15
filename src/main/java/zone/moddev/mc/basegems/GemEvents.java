package zone.moddev.mc.basegems;

import java.util.Collections;

import net.minecraft.advancements.Advancement;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import zone.moddev.mc.basegems.content.CrackhammerItem;
import zone.moddev.mc.basegems.recipe.CrushingRecipe;

final class GemEvents {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getPlayer() instanceof EntityPlayerMP)) return;
        EntityPlayerMP player = (EntityPlayerMP) event.getPlayer();
        for (IRecipe recipe : player.getServer().getRecipeManager().getRecipes()) {
            ResourceLocation id = recipe.getId();
            if (!BaseGems.MOD_ID.equals(id.getNamespace()) || recipe.isDynamic()
                    || player.getRecipeBook().isUnlocked(recipe)) continue;
            Advancement advancement = player.getServer().getAdvancementManager().getAdvancement(
                    new ResourceLocation(BaseGems.MOD_ID, "recipes/" + id.getPath()));
            if (advancement != null && player.getAdvancements().getProgress(advancement).isDone()) {
                player.unlockRecipes(Collections.singletonList(recipe));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onBreak(BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        World world = player.world;
        ItemStack held = player.getHeldItemMainhand();
        IBlockState state = event.getState();
        BlockPos pos = event.getPos();
        if (world.isRemote || player.isCreative() || !(held.getItem() instanceof CrackhammerItem)
                || !state.canHarvestBlock(world, pos, player)) return;

        IRecipe recipe = CrushingRecipe.find(world, new ItemStack(state.getBlock()));
        if (recipe == null) return;
        event.setCanceled(true);
        state.getBlock().onBlockHarvested(world, pos, state, player);
        if (!world.removeBlock(pos)) return;
        state.getBlock().onPlayerDestroy(world, pos, state);
        EntityItem drop = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5,
                pos.getZ() + 0.5, recipe.getRecipeOutput().copy());
        drop.setDefaultPickupDelay();
        world.spawnEntity(drop);
        player.addStat(net.minecraft.stats.StatList.BLOCK_MINED.get(state.getBlock()));
        player.addExhaustion(0.005F);
        held.damageItem(1, player);
    }
}
