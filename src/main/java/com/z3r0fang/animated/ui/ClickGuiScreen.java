package com.z3r0fang.animated.ui;

import com.z3r0fang.animated.client.AudioManager;
import com.z3r0fang.animated.module.Module;
import com.z3r0fang.animated.module.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiPanel {
    private final ModuleCategory category;
    private final List<Module> modules = new ArrayList<>();
    private int x;
    private int y;
    private int width = 160;
    private int height = 200;
    private boolean dragging;
    private int dragOffsetX;
    private int dragOffsetY;

    public ClickGuiPanel(ModuleCategory category, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
    }

    public void setModules(List<Module> modules) {
        this.modules.clear();
        this.modules.addAll(modules);
    }

    public void render(DrawContext context, int mouseX, int mouseY) {
        context.fill(x, y, x + width, y + 18, 0xFF171B22);
        context.fill(x, y + 18, x + width, y + height, 0xFF101419);
        context.drawHorizontalLine(x, x + width, y + 18, 0xFF2F3C49);

        MinecraftClient client = MinecraftClient.getInstance();
        context.drawTextWithShadow(client.textRenderer, Text.literal(category.getDisplayName()), x + 8, y + 5, 0xFFEAF3FF);

        int offsetY = y + 28;
        for (Module module : modules) {
            int rowColor = module.isEnabled() ? 0xFF2B7A78 : 0xFF1D232B;
            context.fill(x + 6, offsetY, x + width - 6, offsetY + 14, rowColor);
            context.drawTextWithShadow(client.textRenderer, module.getDisplayName(), x + 12, offsetY + 3, 0xFFE6F7FF);
            offsetY += 18;
        }
    }

    public boolean clicked(double mouseX, double mouseY, int button) {
        if (button == 0 && mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 18) {
            dragging = true;
            dragOffsetX = (int) mouseX - x;
            dragOffsetY = (int) mouseY - y;
            return true;
        }

        if (button == 0) {
            int offsetY = y + 28;
            for (Module module : modules) {
                if (mouseX >= x + 6 && mouseX <= x + width - 6 && mouseY >= offsetY && mouseY <= offsetY + 14) {
                    module.toggle();
                    AudioManager.playClick();
                    return true;
                }
                offsetY += 18;
            }
        }

        return false;
    }

    public void drag(double mouseX, double mouseY) {
        if (dragging) {
            x = (int) mouseX - dragOffsetX;
            y = (int) mouseY - dragOffsetY;
        }
    }

    public void stopDragging() {
        dragging = false;
    }
}
