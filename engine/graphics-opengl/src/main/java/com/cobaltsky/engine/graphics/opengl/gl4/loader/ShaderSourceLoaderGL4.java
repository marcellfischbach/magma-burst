package com.cobaltsky.engine.graphics.opengl.gl4.loader;

import com.cobaltsky.engine.core.resource.IAssetLoader;
import com.cobaltsky.engine.core.resource.Locator;
import com.cobaltsky.engine.core.resource.VFS;

public class ShaderSourceLoaderGL4 implements IAssetLoader<ShaderSourceGL4> {

    @Override
    public boolean canLoad(Class<?> cls, Locator locator) {
        String ext = locator.getExt();
        return cls.isAssignableFrom(ShaderSourceGL4.class)
                && ("vert".equals(ext)
                || "ctrl".equals(ext)
                || "eval".equals(ext)
                || "geom".equals(ext)
                || "frag".equals(ext)
                || "comp".equals(ext)
                || "glsl".equals(ext));
    }

    @Override
    public ShaderSourceGL4 load(Class<?> cls, Locator locator) {
        String textContent = VFS.instance().getTextContent(locator);
        return new ShaderSourceGL4(textContent);
    }
}
