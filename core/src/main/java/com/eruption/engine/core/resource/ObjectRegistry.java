package com.eruption.engine.core.resource;

import java.util.HashMap;
import java.util.Map;

public class ObjectRegistry {

    private final Map<Class<?>, Object> byClass = new HashMap<>();
    private final Map<String, Object> byName = new HashMap<>();

    public void register(Class<?> cls, Object obj) {
        byClass.put(cls, obj);
    }

    public void register(String name, Object obj) {
        byName.put(name, obj);
    }

    public <T> T get(Class<T> cls) {
        Object o = byClass.get(cls);
        return cls.isInstance(o)
                ? cls.cast(o)
                : null;
    }

    public <T> T get(Class<?> cls, Class<T> implementationClass) {
        Object o = byClass.get(cls);
        return implementationClass.isInstance(o)
                ? implementationClass.cast(o)
                : null;
    }


    public Object get(String name) {
        return byName.get(name);
    }

    public <T> T get(Class<T> cls, String name) {
        Object o = byName.get(name);
        return cls.isInstance(o)
                ? cls.cast(o)
                : null;
    }


    private ObjectRegistry() {
    }

    private static ObjectRegistry instance = null;

    public static ObjectRegistry instance() {
        if (instance == null) {
            instance = new ObjectRegistry();
        }
        return instance;
    }
}
