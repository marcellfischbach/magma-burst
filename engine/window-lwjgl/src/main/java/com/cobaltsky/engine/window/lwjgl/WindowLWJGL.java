package com.cobaltsky.engine.window.lwjgl;

import com.cobaltsky.engine.core.resource.VFS;
import com.cobaltsky.engine.core.window.IWindow;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.lwjgl.glfw.GLFW.*;

import java.io.InputStream;

public class WindowLWJGL implements IWindow {

    private long wnd;

    private int width;

    private int height;

    private String title;

    private boolean fullscreen;

    public void loadConfig() throws Exception {
        InputStream is = VFS.instance().open("/config/window.json");
        WindowConfig config = new ObjectMapper().readValue(is, WindowConfig.class);

        this.width = config.width;
        this.height = config.height;
        this.title = config.title;
        this.fullscreen = config.fullscreen;;
    }

    public void initialize () {
        glfwInit();
        wnd = glfwCreateWindow(this.width, this.height, this.title, 0, 0);
    }

    public void show () {
        glfwShowWindow(this.wnd);
    }

    @Override
    public void pollEvents() {
        glfwPollEvents();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        if (this.wnd != 0) {
            glfwSetWindowTitle(this.wnd, this.title);
        }
    }

    @Override
    public boolean isFullscreen() {
        return fullscreen;
    }

    @Override
    public boolean isVisible() {
        return wnd != 0 && glfwGetWindowAttrib(wnd, GLFW_VISIBLE) != 0;
    }

    @Override
    public boolean shouldClose() {
        return this.wnd != 0 &&  glfwWindowShouldClose(this.wnd);
    }
}
