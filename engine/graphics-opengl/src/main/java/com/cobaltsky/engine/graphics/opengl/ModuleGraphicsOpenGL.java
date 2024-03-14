package com.cobaltsky.engine.graphics.opengl;

import com.cobaltsky.engine.core.Engine;
import com.cobaltsky.engine.core.graphics.IGraphics;
import com.cobaltsky.engine.core.module.IModule;
import com.cobaltsky.engine.core.resource.ObjectRegistry;
import com.cobaltsky.engine.core.window.IWindow;
import com.cobaltsky.engine.graphics.opengl.gl4.GraphicsGL4;

import java.util.Set;

public class ModuleGraphicsOpenGL implements IModule {

    @Override
    public Set<Class<?>> definingClasses() {
        return Set.of(IGraphics.class);
    }

    @Override
    public Set<Class<?>> dependingClasses() {
        return Set.of(IWindow.class);
    }

    @Override
    public String getName() {
        return "Graphics OpenGL";
    }

    @Override
    public void register(Engine engine) {
        GraphicsGL4 graphics = new GraphicsGL4();
        ObjectRegistry.instance().register(IGraphics.class, graphics);
    }

    @Override
    public void initialize(Engine engine) throws Exception {
        GraphicsGL4 graphics = ObjectRegistry.instance().get(IGraphics.class, GraphicsGL4.class);
        graphics.initialize ();
    }
}
