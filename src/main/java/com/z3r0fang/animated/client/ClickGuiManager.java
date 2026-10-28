package com.z3r0fang.animated.client;

import com.z3r0fang.animated.module.Module;
import com.z3r0fang.animated.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class BridgeHud {
    private static final Identifier WATERMARK_TEXTURE = Identifier.of("animated", "textures/gui/watermark.png");

    private int blocksPlaced = 0;
    private double blocksPerSecond = 0.0D;
    private long lastSampleTime = System.currentTimeMillis();

    public void setBlocksPlaced(int blocksPlaced) {
        this.blocksPlaced = blocksPlaced;
    }

    public void addPlacedBlock() {
        blocksPlaced++;
        refreshStats();
    }

    public void refreshStats() {
        long now = System.currentTimeMillis();
        long delta = Math.max(1L, now - lastSampleTime);
        double seconds = delta / 1000.0D;

        if (seconds > 0.0D) {
            blocksPerSecond = Math.max(0.0D, Math.min(999.0D, blocksPlaced / seconds));
        }

        lastSampleTime = now;
    }

    public String getActiveMode() {
        Module active = null;
        for (Module module : ModuleManager.getModules()) {
            if (module.isEnabled()) {
                active = module;
                break;
            }
        }
        return active == null ? "Bridge" : active.getName();
    }

    public void render(DrawContext context, MinecraftClient client, float delta) {
        if (client.player == null) {
            return;
        }

        int x = 10;
        int y = 12;
        int width = 220;
        int height = 76;

        context.fill(x, y, x + width, y + height, 0xB914141A);
        context.fill(x, y, x + width, y + 2, 0xFF2A94F7);
        context.drawHorizontalLine(x, x + width, y + height - 1, 0x552A94F7);
        context.drawVerticalLine(x + width - 1, y, y + height, 0x552A94F7);

        context.drawTextWithShadow(client.textRenderer, "z3r0fang_", x + 12, y + 12, 0xFFE7EEF8);

        context.drawTextWithShadow(client.textRenderer, "Mode: " + getActiveMode(), x + 12, y + 28, 0xFF9BC0FF);
        context.drawTextWithShadow(client.textRenderer, "Placed: " + blocksPlaced, x + 12, y + 42, 0xFFB9F6CA);
        context.drawTextWithShadow(client.textRenderer, "Bridge: " + String.format("%.2f", blocksPerSecond) + " bl/s", x + 12, y + 56, 0xFF96DEFF);

        if (client.getWindow() != null) {
            int windowWidth = client.getWindow().getScaledWidth();
            int windowHeight = client.getWindow().getScaledHeight();

            if (windowWidth > 0 && windowHeight > 0) {
                int watermarkX = windowWidth - 140;
                int watermarkY = windowHeight - 32;

                try {
                    context.drawTexture(WATERMARK_TEXTURE, watermarkX, watermarkY, 0, 0, 120, 22, 120, 22);
                } catch (Exception ignored) {
                    // The resource may not exist yet; the HUD stays functional.
                }
            }
        }
    }
}
