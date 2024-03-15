package com.cobaltsky.examples.test;

import com.cobaltsky.engine.core.Engine;
import com.cobaltsky.engine.graphics.opengl.gl4.shading.*;

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


    private static void setupScene (Engine engine) {
        try {
            ShaderGL4 vert = new ShaderGL4(EShaderType.VERTEX);
            vert.compile(vs);

            ShaderGL4 frag = new ShaderGL4(EShaderType.FRAGMENT);
            frag.compile(fs);

            ProgramGL4 prog = new ProgramGL4();
            prog.attach(vert);
            prog.attach(frag);
            prog.link ();
        }
        catch (ShaderCompilationException ce) {
            System.out.println(ce.getShaderSource());
            System.out.println(ce.getShaderLog());
        }
        catch (ShaderLinkException le) {
            System.out.println(le.getLog());
        }

    }

    public static void main(String[] args) {

        Engine engine = new Engine();
        engine.initialize ();
        setupScene(engine);
        engine.run();
    }
}
