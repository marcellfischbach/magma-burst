package com.eruption.engine.core.math;

public class Vector3f {

    public float x;
    public float y;
    public float z;

    public Vector3f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector3f other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }


    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Vector3f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vector3f normalize () {
        float l = (float)Math.sqrt(x*x + y*y + z*z);
        this.x /= l;
        this.y /= l;
        this.z /= l;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f", this.x, this.y, this.z);
    }
}
