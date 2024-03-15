package com.cobaltsky.engine.graphics.opengl.gl4.shading;

import static org.lwjgl.opengl.GL45.*;

public enum EShaderType {

    VERTEX(GL_VERTEX_SHADER),
    TESS_EVAL(GL_TESS_EVALUATION_SHADER),
    TESS_CTRL(GL_TESS_CONTROL_SHADER),
    GEOMETRY(GL_GEOMETRY_SHADER),
    FRAGMENT(GL_FRAGMENT_SHADER),
    COMPUTE(GL_COMPUTE_SHADER);

    EShaderType(int gl) {
        this.gl = gl;
    }

    final int gl;
}
