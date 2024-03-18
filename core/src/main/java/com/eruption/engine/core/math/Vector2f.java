package com.eruption.engine.core.math;

public class Vector2f {

    public float x;
    public float y;

    public Vector2f() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f other) {
        this.x = other.x;
        this.y = other.y;
    }
}