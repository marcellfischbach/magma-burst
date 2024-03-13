package com.cobaltsky.engine.core.math;

public abstract class VecMath {


    public static Vector3f add(Vector3f v0, Vector3f v1, Vector3f r) {
        r.x = v0.x + v1.x;
        r.y = v0.y + v1.y;
        r.z = v0.z + v1.z;
        return r;
    }

    private VecMath() {
    }
}
