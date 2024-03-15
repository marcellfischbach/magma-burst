package com.cobaltsky.engine.graphics.opengl.gl4.shading;

public class ShaderLinkException extends Exception {

    private String log;

    public ShaderLinkException(String log) {
        super("Unable to link shader program");
        this.log = log;
    }

    public String getLog() {
        return log;
    }
}
