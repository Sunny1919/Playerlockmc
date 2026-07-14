package com.example;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class AimGuiScreen extends Screen {

    protected AimGuiScreen() {
        super(Text.literal("Aim Settings"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Range Slider: 1 bis 50
        this.addDrawableChild(new SliderWidget(centerX - 100, centerY - 40, 200, 20,
                Text.literal("Range: " + AimSettings.RANGE),
                AimSettings.RANGE / 50.0) {
            @Override
            protected void updateMessage() {
                this.setMessage(Text.literal("Range: " + String.format("%.1f", AimSettings.RANGE)));
            }

            @Override
            protected void applyValue() {
                AimSettings.RANGE = 1 + this.value * 49; // Wert zwischen 1 und 50
            }
        });

        // Smoothness Slider: 0.05 bis 1.0
        this.addDrawableChild(new SliderWidget(centerX - 100, centerY, 200, 20,
                Text.literal("Smooth: " + AimSettings.SMOOTH),
                (AimSettings.SMOOTH - 0.05) / 0.95) {
            @Override
            protected void updateMessage() {
                this.setMessage(Text.literal("Smooth: " + String.format("%.2f", AimSettings.SMOOTH)));
            }

            @Override
            protected void applyValue() {
                AimSettings.SMOOTH = 0.05 + this.value * 0.95;
            }
        });

        // Close Button
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Close"),
                button -> this.close()
        ).position(centerX - 100, centerY + 50).size(200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                this.title,
                this.width / 2,
                20,
                0xFFFFFF
        );
    }
}
