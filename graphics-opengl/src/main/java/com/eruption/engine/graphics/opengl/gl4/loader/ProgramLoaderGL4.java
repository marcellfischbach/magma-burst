package com.eruption.engine.graphics.opengl.gl4.loader;

import com.eruption.engine.core.graphics.shading.IShader;
import com.eruption.engine.core.resource.AssetManager;
import com.eruption.engine.core.resource.IAssetLoader;
import com.eruption.engine.core.resource.Locator;
import com.eruption.engine.core.resource.VFS;
import com.eruption.engine.graphics.opengl.gl4.shading.ProgramGL4;
import com.eruption.engine.graphics.opengl.gl4.shading.ShaderGL4;
import com.eruption.engine.graphics.opengl.gl4.shading.ShaderLinkException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;

public class ProgramLoaderGL4 implements IAssetLoader<IShader> {

    private static final Logger LOGGER = LogManager.getLogger(ProgramLoaderGL4.class);

    private static final ObjectMapper OM = new ObjectMapper();

    public static final class Desc {
        public List<String> shaders;
        public List<String> attributes;
    }


    @Override
    public boolean canLoad(Class<?> cls, Locator locator) {
        return cls.isAssignableFrom(ProgramGL4.class)
                && locator.getExt().equalsIgnoreCase("shader");
    }

    @Override
    public IShader load(Class<?> cls, Locator locator) {
        try (InputStream is = VFS.instance().open(locator)) {
            Desc desc = OM.readValue(is, Desc.class);


            ProgramGL4 program = new ProgramGL4();
            for (String shaderDesc : desc.shaders) {
                ShaderGL4 shader = AssetManager.instance().load(ShaderGL4.class, locator.append(shaderDesc));
                program.attach(shader);
            }
            program.link();

            for (String attribute : desc.attributes) {
                program.registerAttribute(attribute);
            }
            return program;
        }
        catch (ShaderLinkException linkException) {
            LOGGER.error("Unable to link: " + locator + "\n" + linkException.getLog());
        }
        catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }
}
