package com.z3r0fang.animated.module;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public abstract class Module {
    private final String name;
    private final ModuleCategory category;
    private final String description;
    private boolean enabled;

    protected Module(String name, ModuleCategory category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public void tick() {
    }

    public void renderHud(DrawContext context, float delta) {
    }

    public void toggle() {
        if (enabled) {
            disable();
        } else {
            enable();
        }
    }

    public void enable() {
        if (!enabled) {
            enabled = true;
            onEnable();
        }
    }

    public void disable() {
        if (enabled) {
            enabled = false;
            onDisable();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Text getDisplayName() {
        return Text.literal(name);
    }
}
