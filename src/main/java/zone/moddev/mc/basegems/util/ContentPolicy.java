package zone.moddev.mc.basegems.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import zone.moddev.mc.basegems.BaseGems;

/** Keeps gem processing and storage recipes available in Low Fantasy. */
public final class ContentPolicy {
    private ContentPolicy() {}

    public static boolean allows(ItemStack output) {
        if (output == null || output.isEmpty()) return true;
        ResourceLocation id = output.getItem().getRegistryName();
        if (id == null || !BaseGems.MODID.equals(id.getNamespace())) return true;

        String path = id.getPath();
        int separator = path.indexOf('_');
        if (separator < 0) return true; // Molten gem blocks use the bare material name.
        switch (path.substring(separator + 1)) {
            case "ore":
            case "ingot":
            case "nugget":
            case "powder":
            case "smallpowder":
            case "plate":
            case "block":
                return true;
            default:
                return false;
        }
    }

}
