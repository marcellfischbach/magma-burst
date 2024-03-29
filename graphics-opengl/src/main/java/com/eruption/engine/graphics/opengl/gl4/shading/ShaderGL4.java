package com.eruption.engine.graphics.opengl.gl4.shading;

import com.eruption.engine.graphics.opengl.gl4.NamedGL4;

import static org.lwjgl.opengl.GL40.*;

public class ShaderGL4 extends NamedGL4 {

    private EShaderType type;

    public ShaderGL4(EShaderType type) {
        this.type = type;
        this.name = glCreateShader(type.gl);
    }

    @Override
    protected void release() {
        if (this.name != 0) {
            glDeleteShader(this.name);
            this.name = 0;
        }
    }

    public void compile(String source) throws ShaderCompilationException {
        glShaderSource(this.name, source);
        glCompileShader(this.name);
        int compileStatus = glGetShaderi(this.name, GL_COMPILE_STATUS);

        if (compileStatus != GL_TRUE) {
            String log = glGetShaderInfoLog(this.name);
            throw new ShaderCompilationException(source, log);
        }
    }
}
