package com.botmod;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class BotScreen extends Screen {
    private TextFieldWidget nameField;
    private ButtonWidget toggleButton;

    public BotScreen() { super(Text.literal("Panel Bota")); }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.nameField = new TextFieldWidget(this.textRenderer, centerX - 100, centerY - 70, 200, 20, Text.literal("Nick bota"));
        this.nameField.setMaxLength(16);
        this.nameField.setText(BotManager.getBotName());
        this.addDrawableChild(this.nameField);

        this.toggleButton = ButtonWidget.builder(
                Text.literal(BotManager.isRunning() ? "Zatrzymaj Bota" : "Wyślij / Uruchom Bota"),
                button -> {
                    BotManager.setBotName(this.nameField.getText());
                    if (BotManager.isRunning()) { BotManager.stopBot(); } else { BotManager.startBot(); }
                    button.setMessage(Text.literal(BotManager.isRunning() ? "Zatrzymaj Bota" : "Wyślij / Uruchom Bota"));
                }
        ).dimensions(centerX - 100, centerY - 40, 200, 20).build();
        this.addDrawableChild(this.toggleButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        context.drawString(this.textRenderer, "Nick Bota:", this.width / 2 - 100, this.height / 2 - 85, 0xA0A0A0, false);

        int consoleX = this.width / 2 - 150;
        int consoleY = this.height / 2 - 10;
        context.fill(consoleX, consoleY, consoleX + 300, consoleY + 120, 0xCC000000);
        context.drawBorder(consoleX, consoleY, 300, 120, 0xFF555555);
        context.drawString(this.textRenderer, "Konsola Bota:", consoleX + 5, consoleY + 5, 0x55FF55, false);

        var logs = BotManager.getLogs();
        int startIdx = Math.max(0, logs.size() - 9);
        int yOffset = consoleY + 20;
        for (int i = startIdx; i < logs.size(); i++) {
            context.drawText(this.textRenderer, logs.get(i), consoleX + 5, yOffset, 0xFFFFFF, false);
            yOffset += 10;
        }
    }

    @Override
    public boolean shouldPause() { return false; }
}
