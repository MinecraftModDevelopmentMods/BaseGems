package zone.moddev.mc.basegems.client;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import zone.moddev.mc.basegems.config.GemConfig;
import zone.moddev.mc.basegems.data.Gem;

/** Shared screen for the Forge Mods list and OreSpawn. */
public final class GemConfigScreen extends Screen {
    private final Screen parent;
    private final Map<String, Boolean> enabled = new LinkedHashMap<>();
    private String mode;
    private int page;

    public GemConfigScreen(Screen parent) {
        super(new net.minecraft.util.text.TranslationTextComponent("config.basegems.title"));
        this.parent = parent;
        mode = GemConfig.configuredMode();
        for (Gem gem : Gem.ALL) enabled.put(gem.name(), GemConfig.configuredGem(gem.name()));
    }

    @Override protected void init() {
        int left = width / 2 - 120;
        int rows = Math.max(1, (height - 104) / 23);
        int perPage = rows * 2;
        int lastPage = (Gem.ALL.size() - 1) / perPage;
        page = Math.min(page, lastPage);
        addButton(new Button(0, left, 29, 240, I18n.format("config.basegems.content_mode")
                + ": " + I18n.format(mode), () -> {
            mode = "low_fantasy".equals(mode) ? "high_fantasy" : "low_fantasy";
            minecraft.displayGuiScreen(this);
        }));
        for (int index = 0; index < perPage && page * perPage + index < Gem.ALL.size(); index++) {
            Gem gem = Gem.ALL.get(page * perPage + index);
            addButton(new Button(index + 10, left + (index % 2) * 122, 54 + (index / 2) * 23, 118,
                    I18n.format("config.basegems." + gem.configKey()) + ": " +
                            I18n.format(enabled.get(gem.name()) ? "options.on" : "options.off"), () -> {
                enabled.put(gem.name(), !enabled.get(gem.name()));
                minecraft.displayGuiScreen(this);
            }));
        }
        if (page > 0) addButton(new Button(1, left, height - 49, 118, "<", () -> {
            page--;
            minecraft.displayGuiScreen(this);
        }));
        if (page < lastPage) addButton(new Button(2, left + 122, height - 49, 118, ">", () -> {
            page++;
            minecraft.displayGuiScreen(this);
        }));
        addButton(new Button(3, left, height - 26, 118, I18n.format("gui.done"), () -> {
            GemConfig.save(mode, enabled);
            onClose();
        }));
        addButton(new Button(4, left + 122, height - 26, 118, I18n.format("gui.cancel"), this::onClose));
    }

    @Override public void onClose() { minecraft.displayGuiScreen(parent); }

    @Override public void render(int mouseX, int mouseY, float ticks) {
        renderBackground();
        drawCenteredString(font, I18n.format("config.basegems.title"), width / 2, 8, 0xffffff);
        drawCenteredString(font, I18n.format("config.basegems.restart"), width / 2, 19, 0xc0c0c0);
        super.render(mouseX, mouseY, ticks);
    }

    private static final class Button extends net.minecraft.client.gui.widget.button.Button {
        Button(int id, int x, int y, int width, String text, Runnable click) {
            super(x, y, width, 20, text, button -> click.run());
        }
    }
}
