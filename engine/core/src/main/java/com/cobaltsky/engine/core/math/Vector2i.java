package com.cobaltsky.engine.core.math;

public class Vector2i {

    public int x;
    public int y;

    public Vector2i() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(Vector2i other) {
        this.x = other.x;
        this.y = other.y;
    }
}
