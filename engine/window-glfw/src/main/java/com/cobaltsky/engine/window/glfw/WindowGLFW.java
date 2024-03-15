package com.cobaltsky.engine.window.glfw;

import com.cobaltsky.engine.core.resource.VFS;
import com.cobaltsky.engine.core.window.IKeyboard;
import com.cobaltsky.engine.core.window.IWindow;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.lwjgl.glfw.GLFW.*;

import java.io.InputStream;

public class WindowGLFW implements IWindow {

    private long wnd;

    private int width;

    private int height;

    private int x;

    private int y;

    private String title;

    private boolean fullscreen;

    private KeyboardGLFW keyboard = new KeyboardGLFW();
    private MouseGLFW mouse = new MouseGLFW(this);

    public void initialize () throws Exception {
        glfwInit();

        InputStream is = VFS.instance().open("/config/window.json");
        WindowConfig config = new ObjectMapper().readValue(is, WindowConfig.class);

        this.width = config.width;
        this.height = config.height;
        this.x = config.x;
        this.y = config.y;
        this.title = config.title;
        this.fullscreen = config.fullscreen;

        if ("opengl".equalsIgnoreCase(config.renderAPI) && config.opengl != null) {

            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, config.opengl.versionMajor);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, config.opengl.versionMinor);
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

            glfwWindowHint(GLFW_DOUBLEBUFFER, 1);

            glfwWindowHint(GLFW_RED_BITS, 8);
            glfwWindowHint(GLFW_GREEN_BITS, 8);
            glfwWindowHint(GLFW_BLUE_BITS, 8);
            glfwWindowHint(GLFW_ALPHA_BITS, 8);

        }


        this.wnd = glfwCreateWindow(this.width, this.height, this.title, 0, 0);

        glfwSetKeyCallback(this.wnd, this.keyboard::onKeyboard);
        glfwSetMouseButtonCallback(this.wnd, this.mouse::onMouseButton);
        glfwSetCursorPosCallback(this.wnd, this.mouse::onMouseMotion);

        glfwSetInputMode(this.wnd, GLFW_CURSOR, GLFW_CURSOR_NORMAL);

        glfwSetWindowPos(this.wnd, this.x, this.y);

        glfwMakeContextCurrent(this.wnd);
    }

    public void show () {
        glfwShowWindow(this.wnd);
    }

    public long getWnd() {
        return wnd;
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
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        if (this.wnd != 0) {
            glfwSetWindowPos(this.wnd, this.x, this.y);
        }
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
    public void pollEvents() {
        this.keyboard.swap();
        this.mouse.prepareFrame();

        glfwPollEvents();
    }

    @Override
    public void finishFrame() {
        glfwSwapBuffers(this.wnd);
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

    @Override
    public IKeyboard getKeyboard() {
        return this.keyboard;
    }

    @Override
    public MouseGLFW getMouse() {
        return mouse;
    }
}
