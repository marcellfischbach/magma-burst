package com.eruption.engine.core.compat;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "Duplicates"})
public interface CompatList {

    static <T> List<T> of() {
        return new ArrayList<>();
    }

    static <T> List<T> of(T v0) {
        List<T> list = new ArrayList<>(1);
        list.add(v0);
        return list;
    }

    static <T> List<T> of(T v0, T v1) {
        List<T> list = new ArrayList<>(2);
        list.add(v0);
        list.add(v1);
        return list;
    }

    static <T> List<T> of(T v0, T v1, T v2) {
        List<T> list = new ArrayList<>(3);
        list.add(v0);
        list.add(v1);
        list.add(v2);
        return list;
    }

    static <T> List<T> of(T v0, T v1, T v2, T v3) {
        List<T> list = new ArrayList<>(4);
        list.add(v0);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        return list;
    }


    static <T> List<T> of(T v0, T v1, T v2, T v3, T v4) {
        List<T> list = new ArrayList<>(5);
        list.add(v0);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        return list;
    }

    static <T> List<T> of(T v0, T v1, T v2, T v3, T v4, T v5) {
        List<T> list = new ArrayList<>(6);
        list.add(v0);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        return list;
    }


    static <T> List<T> of(T v0, T v1, T v2, T v3, T v4, T v5, T v6) {
        List<T> list = new ArrayList<>(7);
        list.add(v0);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        list.add(v6);
        return list;
    }


    static <T> List<T> of(T v0, T v1, T v2, T v3, T v4, T v5, T v6, T v7) {
        List<T> list = new ArrayList<>(8);
        list.add(v0);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        list.add(v6);
        list.add(v7);
        return list;
    }


    static <T> List<T> of(T v0, T v1, T v2, T v3, T v4, T v5, T v6, T v7, T v8) {
        List<T> list = new ArrayList<>(9);
        list.add(v0);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        list.add(v6);
        list.add(v7);
        list.add(v8);
        return list;
    }


    static <T> List<T> of(T v0, T v1, T v2, T v3, T v4, T v5, T v6, T v7, T v8, T v9) {
        List<T> list = new ArrayList<>(10);
        list.add(v0);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        list.add(v6);
        list.add(v7);
        list.add(v8);
        list.add(v9);
        return list;
    }

    @SafeVarargs
    @SuppressWarnings("all")
    static <T> List<T> of(T... vs) {
        List<T> list = new ArrayList<>(vs.length);
        for (T v : vs) {
            list.add(v);
        }
        return list;
    }

}
