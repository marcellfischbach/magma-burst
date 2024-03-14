package com.cobaltsky.engine.window.glfw;

import com.cobaltsky.engine.core.Engine;
import com.cobaltsky.engine.core.module.IModule;
import com.cobaltsky.engine.core.resource.ObjectRegistry;
import com.cobaltsky.engine.core.window.IWindow;

import java.util.Set;

public class ModuleWindowGLFW implements IModule {

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
        WindowGLFW window = new WindowGLFW();
        ObjectRegistry.instance().register(IWindow.class, window);
    }

    @Override
    public void initialize(Engine engine) throws Exception {
        WindowGLFW window = ObjectRegistry.instance().get(IWindow.class, WindowGLFW.class);
        window.initialize();
        window.show ();
    }
}
