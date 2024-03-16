package com.cobaltsky.engine.graphics.opengl.gl4.loader;

import com.cobaltsky.engine.core.resource.AssetManager;
import com.cobaltsky.engine.core.resource.IAssetLoader;
import com.cobaltsky.engine.core.resource.Locator;
import com.cobaltsky.engine.graphics.opengl.gl4.shading.EShaderType;
import com.cobaltsky.engine.graphics.opengl.gl4.shading.ShaderCompilationException;
import com.cobaltsky.engine.graphics.opengl.gl4.shading.ShaderGL4;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderStageLoaderGL4 implements IAssetLoader<ShaderGL4> {

    private static final Logger LOGGER = LogManager.getLogger(ShaderStageLoaderGL4.class);

    @Override
    public boolean canLoad(Class<?> cls, Locator locator) {
        String ext = locator.getExt();
        return cls.isAssignableFrom(ShaderGL4.class)
                && ("vert".equals(ext)
                || "ctrl".equals(ext)
                || "eval".equals(ext)
                || "geom".equals(ext)
                || "frag".equals(ext)
                || "comp".equals(ext)
        );
    }

    @Override
    public ShaderGL4 load(Class<?> cls, Locator locator) {
        try {
            ShaderSourceGL4 shaderSourceGL4 = AssetManager.instance().get(ShaderSourceGL4.class, locator);
            EShaderType type = byFileExtension(locator.getExt());
            ShaderGL4 shader = new ShaderGL4(type);
            shader.compile(shaderSourceGL4.getSource());
            return shader;
        }
        catch (ShaderCompilationException compilationException) {
            LOGGER.error("Unable to compile shader:" + locator.getEncoded() + "\n"
                                 + compilationException.getShaderSource() + "\n"
                                 + compilationException.getShaderLog());
        }
        catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }

    private static EShaderType byFileExtension(String ext) {
        if ("vert".equals(ext)) {
            return EShaderType.VERTEX;
        } else if ("eval".equals(ext)) {
            return EShaderType.TESS_EVAL;
        } else if ("ctrl".equals(ext)) {
            return EShaderType.TESS_CTRL;
        } else if ("geom".equals(ext)) {
            return EShaderType.GEOMETRY;
        } else if ("frag".equals(ext)) {
            return EShaderType.FRAGMENT;
        } else if ("comp".equals(ext)) {
            return EShaderType.COMPUTE;
        }
        return null;
    }
}
