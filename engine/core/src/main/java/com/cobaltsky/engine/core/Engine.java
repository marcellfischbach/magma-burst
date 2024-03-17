package com.cobaltsky.engine.core;


import com.cobaltsky.engine.core.graphics.IGraphics;
import com.cobaltsky.engine.core.math.Color4f;
import com.cobaltsky.engine.core.module.ModuleBootstrapper;
import com.cobaltsky.engine.core.resource.ObjectRegistry;
import com.cobaltsky.engine.core.window.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Engine {

    private static final Logger LOGGER = LogManager.getLogger(Engine.class);

    public void initialize () {
        ModuleBootstrapper.bootstrap(this);

        LOGGER.info("initialized");
    }

    public interface FrameCallback {
        default void preEvents () {}
        default void postEvents () {}
        default void preRender () {}
        default void postRender () {}
        default void preFinish () {}
        default void postFinish () {}
    }

    public void run (FrameCallback cb) {

        IWindow wnd = ObjectRegistry.instance().get(IWindow.class);
        IGraphics graphics = ObjectRegistry.instance().get(IGraphics.class);
        IKeyboard keyboard = wnd.getKeyboard();
        IMouse mouse = wnd.getMouse();

        while (!wnd.shouldClose()) {
            cb.preEvents();
            wnd.pollEvents();
            cb.postEvents();

            cb.preRender();
            renderFrame(graphics);
            cb.postRender();

            cb.preFinish();
            wnd.finishFrame();
            cb.postFinish();

            if (keyboard.isPressed(EKey.KEY_ESCAPE)) {
                break;
            }

        }
    }

    private void renderFrame(IGraphics graphics) {
        graphics.clear(true, new Color4f(0.0f, 0.0f, 0.5f, 1.0f), true, 1.0f, true, 0);
    }
}
