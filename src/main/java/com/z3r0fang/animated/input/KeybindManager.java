package com.z3r0fang.animated.input;

public class Keybind {
    private final int keyCode;
    private final String name;

    public Keybind(int keyCode, String name) {
        this.keyCode = keyCode;
        this.name = name;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public String getName() {
        return name;
    }
}
