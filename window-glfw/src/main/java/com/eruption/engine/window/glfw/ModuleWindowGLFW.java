package com.eruption.engine.window.glfw;

import com.eruption.engine.core.Engine;
import com.eruption.engine.core.compat.CompatSet;
import com.eruption.engine.core.module.IModule;
import com.eruption.engine.core.resource.ObjectRegistry;
import com.eruption.engine.core.window.IWindow;

import java.util.Set;

public class ModuleWindowGLFW implements IModule {

    @Override
    public String getName() {
        return "Window LWJGL";
    }

    @Override
    public Set<Class<?>> definingClasses() {
        return CompatSet.of(IWindow.class);
    }

    @Override
    public Set<Class<?>> dependingClasses() {
        return CompatSet.of();
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
