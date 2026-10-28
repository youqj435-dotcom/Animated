package com.z3r0fang.animated.module;

import com.z3r0fang.animated.module.combat.KillAura;
import com.z3r0fang.animated.module.combat.Velocity;
import com.z3r0fang.animated.module.legit.NoRender;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ModuleManager {
    private static final Map<String, Module> MODULES = new LinkedHashMap<>();

    private ModuleManager() {
    }

    public static void initialize() {
        MODULES.clear();
    }

    public static void registerDefaults() {
        register(new KillAura());
        register(new Velocity());
        register(new NoRender());
    }

    public static void register(Module module) {
        if (module == null) {
            return;
        }

        MODULES.put(module.getName().toLowerCase(), module);
    }

    public static Module get(String name) {
        if (name == null) {
            return null;
        }

        return MODULES.get(name.toLowerCase());
    }

    public static List<Module> getModules() {
        return new ArrayList<>(MODULES.values());
    }

    public static List<Module> getByCategory(ModuleCategory category) {
        List<Module> list = new ArrayList<>();
        for (Module module : MODULES.values()) {
            if (module.getCategory() == category) {
                list.add(module);
            }
        }
        return list;
    }

    public static void tick() {
        for (Module module : MODULES.values()) {
            if (module.isEnabled()) {
                module.tick();
            }
        }
    }
}
