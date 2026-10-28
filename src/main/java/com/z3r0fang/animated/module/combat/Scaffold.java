package com.z3r0fang.animated.input;

import com.z3r0fang.animated.AnimatedClient;
import com.z3r0fang.animated.module.Module;
import com.z3r0fang.animated.module.ModuleManager;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.ArrayList;
import java.util.List;

public final class KeybindManager {
    private static final List<KeyBinding> BINDINGS = new ArrayList<>();

    private KeybindManager() {
    }

    public static void registerBindings() {
        KeyBinding gui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.animated.gui",
                InputUtil.Type.KEYSYM,
                InputUtil.KEY_RIGHT_SHIFT.getCode(),
                "category.animated"
        ));

        KeyBinding toggleAura = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.animated.toggle_killaura",
                InputUtil.Type.KEYSYM,
                InputUtil.KEY_K.getCode(),
                "category.animated"
        ));

        BINDINGS.add(gui);
        BINDINGS.add(toggleAura);
    }

    public static void handleKeybinds() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return;
        }

        if (client.options == null) {
            return;
        }

        for (KeyBinding binding : BINDINGS) {
            if (binding.wasPressed()) {
                if (binding.getTranslationKey().equals("key.animated.gui")) {
                    AnimatedClient.openClickGui();
                }
                if (binding.getTranslationKey().equals("key.animated.toggle_killaura")) {
                    Module module = ModuleManager.get("KillAura");
                    if (module != null) {
                        module.toggle();
                    }
                }
            }
        }
    }
}
