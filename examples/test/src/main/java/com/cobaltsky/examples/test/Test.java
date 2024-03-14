package com.cobaltsky.examples.test;

import com.cobaltsky.engine.core.Engine;

public class Test {

    private static void setupScene (Engine engine) {

    }

    public static void main(String[] args) {

        Engine engine = new Engine();
        engine.initialize ();
        setupScene(engine);
        engine.run();
    }
}
