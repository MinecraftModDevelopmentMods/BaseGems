package zone.moddev.mc.basegems.content;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

/** A dedicated filled bucket; Forge 25 has no universal bucket replacement. */
public final class GemBucketItem extends ItemBucket {
    public GemBucketItem(Fluid fluid, Item.Properties properties) {
        super(fluid, properties);
    }
}
