package com.z3r0fang.animated.ui;

import com.z3r0fang.animated.client.AudioManager;
import com.z3r0fang.animated.module.ModuleCategory;
import com.z3r0fang.animated.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {
    private final List<ClickGuiPanel> panels = new ArrayList<>();

    public ClickGuiScreen() {
        super(Text.literal("Animated ClickGUI"));

        int x = 32;
        for (ModuleCategory category : ModuleCategory.values()) {
            ClickGuiPanel panel = new ClickGuiPanel(category, x, 32);
            panel.setModules(ModuleManager.getByCategory(category));
            panels.add(panel);
            x += 170;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);

        for (ClickGuiPanel panel : panels) {
            panel.render(context, mouseX, mouseY);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (ClickGuiPanel panel : panels) {
            if (panel.clicked(mouseX, mouseY, button)) {
                return true;
            }
        }

        AudioManager.playHover();
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        for (ClickGuiPanel panel : panels) {
            panel.drag(mouseX, mouseY);
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (ClickGuiPanel panel : panels) {
            panel.stopDragging();
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }
}
