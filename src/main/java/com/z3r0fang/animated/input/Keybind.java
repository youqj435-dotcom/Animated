package com.z3r0fang.animated.config;

import com.z3r0fang.animated.module.Module;
import com.z3r0fang.animated.module.ModuleManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class ConfigManager {
    private static final Path CONFIG_PATH = Path.of("config", "animated.properties");

    private ConfigManager() {
    }

    public static void save() {
        try {
            Path parent = CONFIG_PATH.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            Properties properties = new Properties();
            for (Module module : ModuleManager.getModules()) {
                properties.setProperty("module." + module.getName() + ".enabled", String.valueOf(module.isEnabled()));
            }

            Files.writeString(CONFIG_PATH, "", StandardCharsets.UTF_8);
            try (var out = Files.newOutputStream(CONFIG_PATH)) {
                properties.store(out, "Animated config");
            }
        } catch (IOException ignored) {
            // Intentionally silent, configuration is optional and should never crash the client.
        }
    }

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            return;
        }

        try {
            Properties properties = new Properties();
            try (var in = Files.newInputStream(CONFIG_PATH)) {
                properties.load(in);
            }

            for (Module module : ModuleManager.getModules()) {
                String value = properties.getProperty("module." + module.getName() + ".enabled");
                if (value != null) {
                    module.setEnabled(Boolean.parseBoolean(value));
                }
            }
        } catch (IOException ignored) {
            // No-op on failure.
        }
    }
}
