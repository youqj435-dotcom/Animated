package com.z3r0fang.animated.client;

import com.z3r0fang.animated.module.Module;
import com.z3r0fang.animated.module.ModuleCategory;
import com.z3r0fang.animated.module.ModuleManager;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ClickGuiManager {
    private final Map<ModuleCategory, List<Module>> categories = new EnumMap<>(ModuleCategory.class);

    public ClickGuiManager() {
        for (ModuleCategory category : ModuleCategory.values()) {
            categories.put(category, new ArrayList<>());
        }
        refreshModules();
    }

    public void refreshModules() {
        for (ModuleCategory category : ModuleCategory.values()) {
            categories.get(category).clear();
        }

        for (Module module : ModuleManager.getModules()) {
            categories.computeIfAbsent(module.getCategory(), ignored -> new ArrayList<>()).add(module);
        }
    }

    public List<Module> getModules(ModuleCategory category) {
        return categories.getOrDefault(category, List.of());
    }
}
