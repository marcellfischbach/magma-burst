package com.eruption.engine.core.compat;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unused", "Duplicates"})
public interface CompatSet {

    static <T> Set<T> of() {
        return new HashSet<>();
    }

    static <T> Set<T> of(T v0) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        return set;
    }

    static <T> Set<T> of(T v0, T v1) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        set.add(v1);
        return set;
    }

    static <T> Set<T> of(T v0, T v1, T v2) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        set.add(v1);
        set.add(v2);
        return set;
    }

    static <T> Set<T> of(T v0, T v1, T v2, T v3) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        set.add(v1);
        set.add(v2);
        set.add(v3);
        return set;
    }


    static <T> Set<T> of(T v0, T v1, T v2, T v3, T v4) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        set.add(v1);
        set.add(v2);
        set.add(v3);
        set.add(v4);
        return set;
    }

    static <T> Set<T> of(T v0, T v1, T v2, T v3, T v4, T v5) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        set.add(v1);
        set.add(v2);
        set.add(v3);
        set.add(v4);
        set.add(v5);
        return set;
    }


    static <T> Set<T> of(T v0, T v1, T v2, T v3, T v4, T v5, T v6) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        set.add(v1);
        set.add(v2);
        set.add(v3);
        set.add(v4);
        set.add(v5);
        set.add(v6);
        return set;
    }


    static <T> Set<T> of(T v0, T v1, T v2, T v3, T v4, T v5, T v6, T v7) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        set.add(v1);
        set.add(v2);
        set.add(v3);
        set.add(v4);
        set.add(v5);
        set.add(v6);
        set.add(v7);
        return set;
    }


    static <T> Set<T> of(T v0, T v1, T v2, T v3, T v4, T v5, T v6, T v7, T v8) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        set.add(v1);
        set.add(v2);
        set.add(v3);
        set.add(v4);
        set.add(v5);
        set.add(v6);
        set.add(v7);
        set.add(v8);
        return set;
    }


    static <T> Set<T> of(T v0, T v1, T v2, T v3, T v4, T v5, T v6, T v7, T v8, T v9) {
        Set<T> set = new HashSet<>();
        set.add(v0);
        set.add(v1);
        set.add(v2);
        set.add(v3);
        set.add(v4);
        set.add(v5);
        set.add(v6);
        set.add(v7);
        set.add(v8);
        set.add(v9);
        return set;
    }

    @SafeVarargs
    @SuppressWarnings("all")
    static <T> Set<T> of(T... vs) {
        Set<T> set = new HashSet<>();
        for (T v : vs) {
            set.add(v);
        }
        return set;
    }

}
