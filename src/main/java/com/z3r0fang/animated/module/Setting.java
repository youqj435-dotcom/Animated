package com.z3r0fang.animated.module;

import com.z3r0fang.animated.config.ConfigManager;
import com.z3r0fang.animated.input.Keybind;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private final String name;
    private final ModuleCategory category;
    private final String description;
    private boolean enabled;
    private Keybind keybind = new Keybind(-1, "NONE");
    private final List<Setting> settings = new ArrayList<>();

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
            ConfigManager.save();
        }
    }

    public void disable() {
        if (enabled) {
            enabled = false;
            onDisable();
            ConfigManager.save();
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

    public Keybind getKeybind() {
        return keybind;
    }

    public void setKeybind(Keybind keybind) {
        this.keybind = keybind == null ? new Keybind(-1, "NONE") : keybind;
    }

    public void addSetting(Setting setting) {
        if (setting != null) {
            settings.add(setting);
        }
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
