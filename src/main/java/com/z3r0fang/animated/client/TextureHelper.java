package com.z3r0fang.animated.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;

public final class AudioManager {
    private AudioManager() {
    }

    public static void playHover() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            client.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.15F, 1.2F);
        }
    }

    public static void playClick() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            client.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.20F, 0.9F);
        }
    }
}
