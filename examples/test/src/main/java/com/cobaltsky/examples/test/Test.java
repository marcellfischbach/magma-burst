package com.cobaltsky.examples.test;

import com.cobaltsky.engine.core.Engine;
import com.cobaltsky.engine.core.graphics.shading.IShader;
import com.cobaltsky.engine.core.resource.AssetManager;
import com.cobaltsky.engine.core.resource.Locator;
import com.cobaltsky.engine.graphics.opengl.gl4.shading.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class Test {

    public static final String vs = "#version 330\n" +
            "\n" +
            "uniform mat4 cs_ModelMatrix;\n" +
            "uniform mat4 cs_ViewMatrix;\n" +
            "uniform mat4 cs_ProjectionMatrix;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "  gl_Position = cs_ProjectionMatrix * cs_ViewMatrix * cs_ModelMatrix * vec4(0, 0, 0, 1);\n" +
            "}\n";

    public static final String fs = "#version 330\n" +
            "\n" +
            "layout(location = 0) out vec4 cs_FragColor;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "  cs_FragColor = vec4(0, 0, 0, 1);\n" +
            "}\n";


    private static IShader shader;

    private static void setupScene (Engine engine) {
        shader = AssetManager.instance().load(IShader.class, new Locator("/shaders/test.shader"));

    }


    private static void renderFrame () {

    }

    public static void main(String[] args) {

        Engine engine = new Engine();
        engine.initialize ();
        setupScene(engine);
        engine.run(new Engine.FrameCallback() {
            @Override
            public void postRender() {
                renderFrame();
            }
        });
    }
}
