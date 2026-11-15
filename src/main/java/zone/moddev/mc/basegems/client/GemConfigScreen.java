package zone.moddev.mc.basegems.client;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import zone.moddev.mc.basegems.config.GemConfig;
import zone.moddev.mc.basegems.data.Gem;

/** The gem switches and content mode, shared by the Mods list and OreSpawn's world settings. */
final class GemConfigScreen extends Screen {
    private static final int ROW = 23;

    private final Screen parent;
    private final Map<String, Boolean> enabled = new LinkedHashMap<>();
    private String mode;
    private int page;

    GemConfigScreen(Screen parent) {
        super(Component.translatable("config.basegems.title"));
        this.parent = parent;
        mode = GemConfig.configuredMode();
        for (Gem gem : Gem.ALL) enabled.put(gem.name(), GemConfig.configuredGem(gem.name()));
    }

    @Override
    protected void init() {
        int left = width / 2 - 120;
        int rows = Math.max(1, (height - 104) / ROW);
        int perPage = rows * 2;
        int lastPage = (Gem.ALL.size() - 1) / perPage;
        page = Math.min(page, lastPage);

        addRenderableWidget(Button.builder(Component.translatable("config.basegems.content_mode")
                        .append(": ").append(Component.translatable(mode)), button -> {
                    mode = GemConfig.LOW_FANTASY.equals(mode) ? GemConfig.HIGH_FANTASY : GemConfig.LOW_FANTASY;
                    rebuildWidgets();
                }).bounds(left, 29, 240, 20)
                .tooltip(net.minecraft.client.gui.components.Tooltip.create(
                        Component.translatable("config.basegems.content_mode.tooltip")))
                .build());
        for (int index = 0; index < perPage && page * perPage + index < Gem.ALL.size(); index++) {
            Gem gem = Gem.ALL.get(page * perPage + index);
            Component label = Component.translatable("config.basegems." + gem.configKey()).append(": ")
                    .append(enabled.get(gem.name()) ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF);
            addRenderableWidget(Button.builder(label, button -> {
                enabled.put(gem.name(), !enabled.get(gem.name()));
                rebuildWidgets();
            }).bounds(left + (index % 2) * 122, 54 + (index / 2) * ROW, 118, 20).build());
        }
        if (page > 0) {
            addRenderableWidget(Button.builder(Component.literal("<"), button -> {
                page--;
                rebuildWidgets();
            }).bounds(left, height - 49, 118, 20).build());
        }
        if (page < lastPage) {
            addRenderableWidget(Button.builder(Component.literal(">"), button -> {
                page++;
                rebuildWidgets();
            }).bounds(left + 122, height - 49, 118, 20).build());
        }
        addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> {
            GemConfig.save(mode, enabled);
            onClose();
        }).bounds(left, height - 26, 118, 20).build());
        addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, button -> onClose())
                .bounds(left + 122, height - 26, 118, 20).build());
    }

    @Override
    public void onClose() {
        if (minecraft != null) minecraft.setScreen(parent);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(font, title, width / 2, 8, 0xFFFFFF);
        graphics.drawCenteredString(font, Component.translatable("config.basegems.restart"), width / 2, 19, 0xC0C0C0);
    }
}
