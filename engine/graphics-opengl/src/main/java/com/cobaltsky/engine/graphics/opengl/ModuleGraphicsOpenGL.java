package com.cobaltsky.engine.graphics.opengl;

import com.cobaltsky.engine.core.Engine;
import com.cobaltsky.engine.core.compat.CompatSet;
import com.cobaltsky.engine.core.graphics.IGraphics;
import com.cobaltsky.engine.core.module.IModule;
import com.cobaltsky.engine.core.resource.AssetManager;
import com.cobaltsky.engine.core.resource.ObjectRegistry;
import com.cobaltsky.engine.core.window.IWindow;
import com.cobaltsky.engine.graphics.opengl.gl4.GraphicsGL4;
import com.cobaltsky.engine.graphics.opengl.gl4.loader.ProgramLoaderGL4;
import com.cobaltsky.engine.graphics.opengl.gl4.loader.ShaderSourceLoaderGL4;
import com.cobaltsky.engine.graphics.opengl.gl4.loader.ShaderStageLoaderGL4;

import java.util.Set;

public class ModuleGraphicsOpenGL implements IModule {

    @Override
    public Set<Class<?>> definingClasses() {
        return CompatSet.of(IGraphics.class);
    }

    @Override
    public Set<Class<?>> dependingClasses() {
        return CompatSet.of(IWindow.class);
    }

    @Override
    public String getName() {
        return "Graphics OpenGL";
    }

    @Override
    public void register(Engine engine) {
        GraphicsGL4 graphics = new GraphicsGL4();
        ObjectRegistry.instance().register(IGraphics.class, graphics);

        AssetManager.instance()
                .register(new ShaderSourceLoaderGL4())
                .register(new ShaderStageLoaderGL4())
                .register(new ProgramLoaderGL4());
    }

    @Override
    public void initialize(Engine engine) {
        GraphicsGL4 graphics = ObjectRegistry.instance().get(IGraphics.class, GraphicsGL4.class);
        graphics.initialize ();
    }
}
