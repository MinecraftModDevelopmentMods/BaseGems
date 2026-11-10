package zone.moddev.mc.basegems.client;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zone.moddev.mc.basegems.BaseGems;
import zone.moddev.mc.basegems.util.Config;

/** One parent-aware screen for Forge's mod list and OreSpawn's child-mod list. */
@SideOnly(Side.CLIENT)
public final class BaseGemsConfigScreen extends GuiConfig {
    public BaseGemsConfigScreen(final GuiScreen parent) {
        super(parent, elements(),
              BaseGems.MODID, false, true, I18n.format("config.basegems.title"),
              I18n.format("config.basegems.description"));
    }

    private static List<IConfigElement> elements() {
        final List<IConfigElement> elements = new ArrayList<>(
                new ConfigElement(Config.getConfiguration().getCategory(Config.GENERAL_CAT)).getChildElements());
        elements.addAll(new ConfigElement(Config.getConfiguration().getCategory(Config.MATERIALS_CAT)).getChildElements());
        return elements;
    }
}
