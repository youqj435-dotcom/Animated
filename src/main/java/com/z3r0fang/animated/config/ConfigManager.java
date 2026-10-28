package com.z3r0fang.animated.module;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ModuleSettings {
    private final Map<String, Object> values = new LinkedHashMap<>();

    public void set(String key, Object value) {
        values.put(key, value);
    }

    public Object get(String key) {
        return values.get(key);
    }

    public boolean getBoolean(String key) {
        Object value = values.get(key);
        return Boolean.TRUE.equals(value);
    }

    public int getInteger(String key) {
        Object value = values.get(key);
        return value instanceof Number number ? number.intValue() : 0;
    }

    public double getDouble(String key) {
        Object value = values.get(key);
        return value instanceof Number number ? number.doubleValue() : 0.0D;
    }
}
