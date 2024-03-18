package com.eruption.engine.core.math;

public class Vector3i {

    public int x;
    public int y;
    public int z;

    public Vector3i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3i(Vector3i other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }



    public void set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Vector3i v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }


    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f", this.x, this.y, this.z);
    }
}
