package zone.moddev.mc.basegems.content;



import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.data.Gem;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public final class MaterialItems {
    private MaterialItems() {}

    public static final class Pickaxe extends ItemPickaxe implements MaterialBacked {
        protected final Gem material;
        public Pickaxe(Gem material, Properties properties) {
            super(new MaterialTier(material), 1, -2.8F, properties);
            this.material = material;
        }
        @Override public Gem gem() { return material; }
    }

    public static final class Axe extends ItemAxe implements MaterialBacked {
        protected final Gem material;
        public Axe(Gem material, Properties properties) {
            super(new MaterialTier(material), 4.0F + material.baseAttackDamage(),
                    material.axeAttackSpeed(), properties);
            this.material = material;
        }
        @Override public Gem gem() { return material; }
    }

    public static final class Shovel extends ItemSpade implements MaterialBacked {
        protected final Gem material;
        public Shovel(Gem material, Properties properties) {
            super(new MaterialTier(material), 1.5F, -3.0F, properties);
            this.material = material;
        }
        @Override public Gem gem() { return material; }
    }

    public static final class Hoe extends ItemHoe implements MaterialBacked {
        protected final Gem material;
        public Hoe(Gem material, Properties properties) {
            super(new MaterialTier(material), material.baseAttackDamage() - 3.0F, properties);
            this.material = material;
        }
        @Override public Gem gem() { return material; }
    }

    public static class Sword extends ItemSword implements MaterialBacked {
        protected final Gem material;
        public Sword(Gem material, Properties properties) {
            super(new MaterialTier(material), 3, -2.4F, properties);
            this.material = material;
        }
        @Override public Gem gem() { return material; }
    }

    public static final class Shears extends ItemShears implements MaterialBacked {
        protected final Gem material;
        public Shears(Gem material, Properties properties) {
            super(properties.defaultMaxDamage(material.toolDurability()));
            this.material = material;
        }
        @Override public Gem gem() { return material; }
        @Override public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new MaterialTier(material).getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
        }
    }

    public static class Shield extends ItemShield implements MaterialBacked {
        protected final Gem material;
        public Shield(Gem material, Properties properties) {
            super(properties.defaultMaxDamage(material.shieldDurability()));
            this.material = material;
        }
        @Override public Gem gem() { return material; }
        @Override public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new MaterialTier(material).getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
        }
    }

    public static class Bow extends ItemBow implements MaterialBacked {
        protected final Gem material;

        public Bow(Gem material, Properties properties) {
            super(properties.defaultMaxDamage(material.toolDurability()));
            this.material = material;
        }

        @Override public Gem gem() { return material; }

        @Override
        public EntityArrow customizeArrow(EntityArrow arrow) {
            // Forge calls this before applying Power, so enchantment bonuses stay unchanged.
            arrow.setDamage(arrow.getDamage() + material.baseAttackDamage() - 1.0D);

            return arrow;
        }

        @Override protected boolean isArrow(ItemStack stack) {
            if (stack.getItem() instanceof GemAmmoItem) {
                return ((GemAmmoItem) stack.getItem()).kind() == GemAmmoItem.Kind.ARROW;
            }
            return stack.getItem() instanceof ItemArrow;
        }
        @Override public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new MaterialTier(material).getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
        }
    }

    public static final class Crossbow extends Bow {
        public Crossbow(Gem material, Properties properties) {
            super(material, properties);
        }

        @Override protected boolean isArrow(ItemStack stack) {
            return stack.getItem() instanceof GemAmmoItem
                    && ((GemAmmoItem) stack.getItem()).kind() == GemAmmoItem.Kind.BOLT;
        }
        @Override
        public void onPlayerStoppedUsing(ItemStack bow, World world, EntityLivingBase user, int timeLeft) {
            if (!(user instanceof EntityPlayer)) return;
            EntityPlayer player = (EntityPlayer) user;
            boolean hasInfiniteAmmo = player.abilities.isCreativeMode
                    || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bow) > 0;
            ItemStack ammunition = findAmmo(player);
            int charge = getUseDuration(bow) - timeLeft;
            charge = ForgeEventFactory.onArrowLoose(bow, world, player, charge,
                    !ammunition.isEmpty() || hasInfiniteAmmo);
            if (charge < 0 || ammunition.isEmpty() && !hasInfiniteAmmo) return;

            if (ammunition.isEmpty()) ammunition = new ItemStack(ModContent.item(material.name() + "_bolt"));
            float power = getArrowVelocity(charge);
            if (power < 0.1F) return;

            GemAmmoItem bolt = (GemAmmoItem) ammunition.getItem();
            boolean infiniteShot = player.abilities.isCreativeMode
                    || bolt.isInfinite(ammunition, bow, player);
            if (!world.isRemote) {
                EntityArrow projectile = customizeArrow(bolt.createArrow(world, ammunition, player));
                projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F,
                        power * 3.0F, 1.0F);
                if (power == 1.0F) projectile.setIsCritical(true);
                int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bow);
                if (powerLevel > 0) {
                    projectile.setDamage(projectile.getDamage() + powerLevel * 0.5D + 0.5D);
                }
                int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bow);
                if (punchLevel > 0) projectile.setKnockbackStrength(punchLevel);
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bow) > 0) {
                    projectile.setFire(100);
                }
                bow.damageItem(1, player);
                if (infiniteShot) projectile.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                world.spawnEntity(projectile);
            }
            world.playSound(null, player.posX, player.posY, player.posZ,
                    SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
                    1.0F / (random.nextFloat() * 0.4F + 1.2F) + power * 0.5F);
            if (!infiniteShot && !player.abilities.isCreativeMode) {
                ammunition.shrink(1);
                if (ammunition.isEmpty()) player.inventory.deleteStack(ammunition);
            }
            player.addStat(StatList.ITEM_USED.get(this));
        }
    }

    public static final class FishingRod extends ItemFishingRod implements MaterialBacked {
        protected final Gem material;
        public FishingRod(Gem material, Properties properties) {
            super(properties.defaultMaxDamage(material.toolDurability()));
            this.material = material;
        }
        @Override public Gem gem() { return material; }
        @Override public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new MaterialTier(material).getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
        }
    }

}
