package com.z3r0fang.animated.module;

public class Setting {
    private final String name;
    private final SettingType type;
    private Object value;

    public Setting(String name, SettingType type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public SettingType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean getBoolean() {
        return Boolean.TRUE.equals(value);
    }

    public int getInteger() {
        return value instanceof Number number ? number.intValue() : 0;
    }

    public double getDouble() {
        return value instanceof Number number ? number.doubleValue() : 0.0D;
    }

    public enum SettingType {
        BOOLEAN,
        INTEGER,
        DOUBLE,
        STRING
    }
}
