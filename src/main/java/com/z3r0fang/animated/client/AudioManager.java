package com.z3r0fang.animated.module.legit;

import com.z3r0fang.animated.module.Module;
import com.z3r0fang.animated.module.ModuleCategory;

public class NoRender extends Module {
    public NoRender() {
        super("NoRender", ModuleCategory.LEGIT, "Disables obstructive visual effects such as fire, fog and particles.");
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    public boolean shouldHideFire() {
        return isEnabled();
    }

    public boolean shouldHideFog() {
        return isEnabled();
    }

    public boolean shouldHideParticles() {
        return isEnabled();
    }
}
