package com.z3r0fang.animated;

import com.z3r0fang.animated.client.AudioManager;
import com.z3r0fang.animated.client.BridgeHud;
import com.z3r0fang.animated.client.ClickGuiManager;
import com.z3r0fang.animated.module.Module;
import com.z3r0fang.animated.module.ModuleManager;
import com.z3r0fang.animated.ui.ClickGuiScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class AnimatedClient implements ClientModInitializer {
    private static final BridgeHud BRIDGE_HUD = new BridgeHud();
    private static final ClickGuiManager CLICK_GUI = new ClickGuiManager();

    @Override
    public void onInitializeClient() {
        ModuleManager.registerDefaults();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ModuleManager.tick();
            if (client.player != null && client.player.age % 20 == 0) {
                BRIDGE_HUD.refreshStats();
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null || client.player == null || client.options.hudHidden) {
                return;
            }

            BRIDGE_HUD.render(drawContext, client, tickCounter.getTickDelta(true));
        });

        AnimatedMod.LOGGER.info("Animated client initialized.");
    }

    public static BridgeHud getBridgeHud() {
        return BRIDGE_HUD;
    }

    public static ClickGuiManager getClickGui() {
        return CLICK_GUI;
    }

    public static void openClickGui() {
        MinecraftClient client = MinecraftClient.getInstance();
        Screen current = client.currentScreen;

        if (current instanceof ClickGuiScreen) {
            client.setScreen(null);
            return;
        }

        client.setScreen(new ClickGuiScreen());
        AudioManager.playClick();
    }

    public static Module getModule(String name) {
        return ModuleManager.get(name);
    }
}
