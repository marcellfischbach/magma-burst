package com.cobaltsky.engine.window.lwjgl;

import com.cobaltsky.engine.core.Engine;
import com.cobaltsky.engine.core.module.IModule;
import com.cobaltsky.engine.core.resource.ObjectRegistry;
import com.cobaltsky.engine.core.window.IWindow;

import java.util.Set;

public class ModuleWindowLWJGL implements IModule {

    @Override
    public String getName() {
        return "Window LWJGL";
    }

    @Override
    public Set<Class<?>> definingClasses() {
        return Set.of(IWindow.class);
    }

    @Override
    public Set<Class<?>> dependingClasses() {
        return Set.of();
    }

    @Override
    public void register(Engine engine) {
        WindowLWJGL window = new WindowLWJGL();
        ObjectRegistry.instance().register(IWindow.class, window);
    }

    @Override
    public void initialize(Engine engine) throws Exception {
        WindowLWJGL window = ObjectRegistry.instance().get(IWindow.class, WindowLWJGL.class);
        window.loadConfig ();
        window.initialize();
        window.show ();
    }
}
