package zone.moddev.mc.basegems.content;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.BucketItem;

/** A dedicated filled bucket; retains the old fluid and item IDs. */
public final class GemBucketItem extends BucketItem {
    public GemBucketItem(Fluid fluid, Item.Properties properties) {
        super(fluid, properties);
    }
}
