package com.z3r0fang.animated.module.combat;

import com.z3r0fang.animated.module.Module;
import com.z3r0fang.animated.module.ModuleCategory;
import com.z3r0fang.animated.module.TargetMode;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

public class KillAura extends Module {
    private TargetMode targetMode = TargetMode.BOTH;

    public KillAura() {
        super("KillAura", ModuleCategory.COMBAT, "Attacks the closest valid target.");
    }

    public TargetMode getTargetMode() {
        return targetMode;
    }

    public void setTargetMode(TargetMode targetMode) {
        this.targetMode = targetMode == null ? TargetMode.BOTH : targetMode;
    }

    public boolean shouldTarget(Entity entity) {
        if (entity == null || entity == MinecraftClient.getInstance().player) {
            return false;
        }

        boolean players = targetMode == TargetMode.PLAYERS || targetMode == TargetMode.BOTH;
        boolean mobs = targetMode == TargetMode.MOBS || targetMode == TargetMode.BOTH;

        if (entity instanceof PlayerEntity && players) {
            return true;
        }

        if (entity instanceof MobEntity && mobs) {
            return true;
        }

        return false;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
}
