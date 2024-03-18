package com.eruption.engine.graphics.opengl.gl4.shading;

public class ShaderCompilationException extends Exception {

    private final String shaderSource;

    private final String shaderLog;

    public ShaderCompilationException(String shaderSource, String shaderLog) {
        super("Unable to compile shader");
        this.shaderSource = shaderSource;
        this.shaderLog = shaderLog;
    }

    public String getShaderSource() {
        return shaderSource;
    }

    public String getShaderLog() {
        return shaderLog;
    }
}
