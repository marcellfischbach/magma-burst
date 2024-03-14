package com.cobaltsky.engine.core.math;

public class Color4f {

    public float r;

    public float g;

    public float b;

    public float a;

    public Color4f() {
        this.r = 0.0f;
        this.g = 0.0f;
        this.b = 0.0f;
        this.a = 0.0f;
    }

    public Color4f(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1.0f;
    }

    public Color4f(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public int argb () {
        return ((int)(this.a * 255) & 0xff << 24)
                | ((int)(this.r * 255) & 0xff << 16)
                | ((int)(this.g * 255) & 0xff << 8)
                | ((int)(this.b * 255) & 0xff);
    }
}
