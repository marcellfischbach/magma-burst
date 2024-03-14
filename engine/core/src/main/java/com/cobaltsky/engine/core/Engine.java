package com.cobaltsky.engine.core;


import com.cobaltsky.engine.core.module.ModuleBootstrapper;
import com.cobaltsky.engine.core.resource.ObjectRegistry;
import com.cobaltsky.engine.core.window.IWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Engine {

    private static final Logger LOGGER = LogManager.getLogger(Engine.class);

    public void initialize () {
        ModuleBootstrapper.bootstrap(this);

        LOGGER.info("initialize");
    }

    public void run () {

        IWindow wnd = ObjectRegistry.instance().get(IWindow.class);
        while (!wnd.shouldClose()) {
            wnd.pollEvents();
        }

    }
}
