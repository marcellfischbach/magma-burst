package com.cobaltsky.engine.window.glfw;

public class WindowConfig {

    public int width;

    public int height;

    public int x;

    public int y;

    public String title;

    public boolean fullscreen;

    public String renderAPI;

    public OpenGL opengl;


    public static class OpenGL {

        public int versionMajor;

        public int versionMinor;
    }
}
