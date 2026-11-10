package zone.moddev.mc.basegems.client;

import java.util.Collections;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Forge 1.12's configuration factory; never loaded by a dedicated server. */
@SideOnly(Side.CLIENT)
public final class BaseGemsGuiFactory implements IModGuiFactory {
    @Override public void initialize(final Minecraft minecraft) { }
    @Override public boolean hasConfigGui() { return true; }
    @Override public GuiScreen createConfigGui(final GuiScreen parent) { return new BaseGemsConfigScreen(parent); }
    @Override public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() { return Collections.emptySet(); }
}
