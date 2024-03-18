package com.eruption.engine.core.math;

public class Vector4i {

    public int x;
    public int y;
    public int z;
    public int w;

    public Vector4i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }

    public Vector4i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 0;
    }

    public Vector4i(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4i(Vector4i other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.w = other.w;
    }
}
