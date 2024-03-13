package com.cobaltsky.engine.core;


import com.cobaltsky.engine.core.math.Vector3f;

import static com.cobaltsky.engine.core.math.VecMath.*;

public class Engine {

    public static void main(String[] args) {
        Vector3f res = add(new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(0.0f, 1.0f, 1.0f), new Vector3f());
        System.out.println("Res: " + res);
    }
}
