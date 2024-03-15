package com.cobaltsky.engine.window.glfw;

import com.cobaltsky.engine.core.window.EMouseButton;
import com.cobaltsky.engine.core.window.EMouseMode;
import com.cobaltsky.engine.core.window.IMouse;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;

public class MouseGLFW implements IMouse {

    private int x;
    private int y;

    private int deltaX;
    private int deltaY;

    private int lastX;
    private int lastY;

    private EMouseMode mode = EMouseMode.NORMAL;

    private boolean[] buttons = new boolean[EMouseButton.values().length];
    private boolean[] prevButtons = new boolean[EMouseButton.values().length];

    private final WindowGLFW window;

    public MouseGLFW(WindowGLFW window) {
        this.window = window;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    @Override
    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    @Override
    public boolean isButtonDown(EMouseButton button) {
        return buttons[button.ordinal()];
    }

    @Override
    public boolean isButtonUp(EMouseButton button) {
        return !buttons[button.ordinal()];
    }

    @Override
    public boolean isButtonPressed(EMouseButton button) {
        int ordinal = button.ordinal();
        return this.buttons[ordinal] && !this.prevButtons[ordinal];    }

    @Override
    public boolean isButtonReleased(EMouseButton button) {
        int ordinal = button.ordinal();
        return !this.buttons[ordinal] && this.prevButtons[ordinal];
    }

    @Override
    public void setMode(EMouseMode mode) {
        this.mode = mode;
        switch (this.mode) {
            case NORMAL:
                GLFW.glfwSetInputMode(this.window.getWnd(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
                break;
            case CENTERED:
                GLFW.glfwSetInputMode(this.window.getWnd(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_CAPTURED);
                break;
        }
    }

    @Override
    public EMouseMode getMode() {
        return this.mode;
    }

    public void prepareFrame() {
        this.deltaX = 0;
        this.deltaY = 0;
        if (this.mode == EMouseMode.CENTERED) {

            int centerX = this.window.getWidth() / 2;
            int centerY = this.window.getHeight() / 2;
            this.lastX = centerX;
            this.lastY = centerY;
            this.x = centerX;
            this.y = centerY;
            glfwSetCursorPos(this.window.getWnd(), centerX, centerY);

        }
        else {
            this.lastX = this.x;
            this.lastY = this.y;

        }

        System.arraycopy(this.buttons, 0, this.prevButtons, 0, this.buttons.length);
    }


    public void onMouseButton(long wnd, int key, int action, int mods) {
        int idx = MAP [key];
        if (idx == -1) {
            return;
        }

        this.buttons[idx] = action != 0;
    }

    public void onMouseMotion (long wnd, double xPos, double yPos) {
        this.x = (int)xPos;
        this.y = (int)yPos;

        this.deltaX = this.x - this.lastX;
        this.deltaY = this.y - this.lastY;

        if (this.mode == EMouseMode.CENTERED) {
            this.x = this.lastX;
            this.y = this.lastY;
        }
    }

    private static final int[] MAP = new int[]{
            EMouseButton.MB_LEFT.ordinal(),
            EMouseButton.MB_RIGHT.ordinal(),
            EMouseButton.MB_MIDDLE.ordinal(),
            -1,
            -1,
            -1,
            -1,
            -1,
    };

}
