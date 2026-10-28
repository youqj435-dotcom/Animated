package com.z3r0fang.animated;

import com.z3r0fang.animated.module.ModuleManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnimatedMod implements ModInitializer {
    public static final String MOD_ID = "animated";
    public static final Logger LOGGER = LoggerFactory.getLogger("Animated");

    @Override
    public void onInitialize() {
        ModuleManager.initialize();
        LOGGER.info("Animated utility client initialized.");
    }
}
