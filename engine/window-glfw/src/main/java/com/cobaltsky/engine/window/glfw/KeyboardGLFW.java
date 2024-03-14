package com.cobaltsky.engine.window.glfw;

import com.cobaltsky.engine.core.window.EKey;
import com.cobaltsky.engine.core.window.IKeyboard;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardGLFW implements IKeyboard {

    private boolean[] prevKeys = new boolean[EKey.values().length];
    private boolean[] keys = new boolean[EKey.values().length];

    @Override
    public boolean isDown(EKey key) {
        return this.keys[key.ordinal()];
    }

    @Override
    public boolean isUp(EKey key) {
        return !this.keys[key.ordinal()];
    }

    @Override
    public boolean isPressed(EKey key) {
        int ordinal = key.ordinal();
        return this.keys[ordinal] && !this.prevKeys[ordinal];
    }

    @Override
    public boolean isReleased(EKey key) {
        int ordinal = key.ordinal();
        return !this.keys[ordinal] && this.prevKeys[ordinal];
    }

    public void swap () {
        System.arraycopy(this.keys, 0, this.prevKeys, 0, this.keys.length);
    }

    public void onKeyboard (long wnd, int key, int scancode, int action, int mods){
        int idx = MAP [key];
        if (idx == -1) {
            return;
        }

        this.keys[idx] = action != 0;
    }


    private static final int[] MAP = new int[GLFW_KEY_LAST];
    static {
        Arrays.fill(MAP, -1);
        MAP[GLFW_KEY_A] = EKey.KEY_A.ordinal();
        MAP[GLFW_KEY_B] = EKey.KEY_B.ordinal();
        MAP[GLFW_KEY_C] = EKey.KEY_C.ordinal();
        MAP[GLFW_KEY_D] = EKey.KEY_D.ordinal();
        MAP[GLFW_KEY_E] = EKey.KEY_E.ordinal();
        MAP[GLFW_KEY_F] = EKey.KEY_F.ordinal();
        MAP[GLFW_KEY_G] = EKey.KEY_G.ordinal();
        MAP[GLFW_KEY_H] = EKey.KEY_H.ordinal();
        MAP[GLFW_KEY_I] = EKey.KEY_I.ordinal();
        MAP[GLFW_KEY_J] = EKey.KEY_J.ordinal();
        MAP[GLFW_KEY_K] = EKey.KEY_K.ordinal();
        MAP[GLFW_KEY_L] = EKey.KEY_L.ordinal();
        MAP[GLFW_KEY_M] = EKey.KEY_M.ordinal();
        MAP[GLFW_KEY_N] = EKey.KEY_N.ordinal();
        MAP[GLFW_KEY_O] = EKey.KEY_O.ordinal();
        MAP[GLFW_KEY_P] = EKey.KEY_P.ordinal();
        MAP[GLFW_KEY_Q] = EKey.KEY_Q.ordinal();
        MAP[GLFW_KEY_R] = EKey.KEY_R.ordinal();
        MAP[GLFW_KEY_S] = EKey.KEY_S.ordinal();
        MAP[GLFW_KEY_T] = EKey.KEY_T.ordinal();
        MAP[GLFW_KEY_U] = EKey.KEY_U.ordinal();
        MAP[GLFW_KEY_V] = EKey.KEY_V.ordinal();
        MAP[GLFW_KEY_W] = EKey.KEY_W.ordinal();
        MAP[GLFW_KEY_X] = EKey.KEY_X.ordinal();
        MAP[GLFW_KEY_Y] = EKey.KEY_Y.ordinal();
        MAP[GLFW_KEY_Z] = EKey.KEY_Z.ordinal();

        MAP[GLFW_KEY_0] = EKey.KEY_0.ordinal();
        MAP[GLFW_KEY_1] = EKey.KEY_1.ordinal();
        MAP[GLFW_KEY_2] = EKey.KEY_2.ordinal();
        MAP[GLFW_KEY_3] = EKey.KEY_3.ordinal();
        MAP[GLFW_KEY_4] = EKey.KEY_4.ordinal();
        MAP[GLFW_KEY_5] = EKey.KEY_5.ordinal();
        MAP[GLFW_KEY_6] = EKey.KEY_6.ordinal();
        MAP[GLFW_KEY_7] = EKey.KEY_7.ordinal();
        MAP[GLFW_KEY_8] = EKey.KEY_8.ordinal();
        MAP[GLFW_KEY_9] = EKey.KEY_9.ordinal();

        MAP[GLFW_KEY_ESCAPE] = EKey.KEY_ESCAPE.ordinal();
        MAP[GLFW_KEY_TAB] = EKey.KEY_TAB.ordinal();
        MAP[GLFW_KEY_LEFT_SHIFT] = EKey.KEY_LEFT_SHIFT.ordinal();
        MAP[GLFW_KEY_RIGHT_SHIFT] = EKey.KEY_RIGHT_SHIFT.ordinal();
        MAP[GLFW_KEY_LEFT_CONTROL] = EKey.KEY_LEFT_CTRL.ordinal();
        MAP[GLFW_KEY_RIGHT_CONTROL] = EKey.KEY_RIGHT_CTRL.ordinal();
        MAP[GLFW_KEY_LEFT_ALT] = EKey.KEY_LEFT_ALT.ordinal();
        MAP[GLFW_KEY_RIGHT_ALT] = EKey.KEY_RIGHT_ALT.ordinal();

        MAP[GLFW_KEY_UP] = EKey.KEY_UP.ordinal();
        MAP[GLFW_KEY_DOWN] = EKey.KEY_DOWN.ordinal();
        MAP[GLFW_KEY_LEFT] = EKey.KEY_LEFT.ordinal();
        MAP[GLFW_KEY_RIGHT] = EKey.KEY_RIGHT.ordinal();


    }
}
