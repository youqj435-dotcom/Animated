package com.z3r0fang.animated.client;

import com.z3r0fang.animated.AnimatedMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;

public final class TextureHelper {
    private TextureHelper() {
    }

    public static Identifier loadPngWithAlpha(String resourcePath) {
        Identifier resourceId = Identifier.of(AnimatedMod.MOD_ID, resourcePath);
        MinecraftClient client = MinecraftClient.getInstance();

        try (InputStream stream = client.getResourceManager()
                .getResource(resourceId)
                .orElseThrow(() -> new IllegalArgumentException("Missing texture: " + resourceId))
                .getInputStream()) {

            NativeImage image = NativeImage.read(stream);
            NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
            client.getTextureManager().registerTexture(resourceId, texture);
            return resourceId;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load textured resource: " + resourceId, e);
        }
    }
}
