package com.eruption.examples.test;

import com.eruption.engine.core.Engine;
import com.eruption.engine.core.graphics.EPrimitive;
import com.eruption.engine.core.graphics.ERenderPass;
import com.eruption.engine.core.graphics.IGraphics;
import com.eruption.engine.core.graphics.Mesh;
import com.eruption.engine.core.graphics.material.EAttributeType;
import com.eruption.engine.core.graphics.material.Material;
import com.eruption.engine.core.graphics.material.MaterialDef;
import com.eruption.engine.core.graphics.shading.IShader;
import com.eruption.engine.core.math.Color4f;
import com.eruption.engine.core.math.Vector4f;
import com.eruption.engine.core.resource.AssetManager;
import com.eruption.engine.core.resource.Locator;
import org.lwjgl.opengl.GL40;

import java.util.List;

public class Test {


    private static IShader shader;

    private static Mesh mesh;

    private static MaterialDef materialDef;

    private static Material material;

    private static void setupScene(Engine engine) {
        shader = AssetManager.instance().load(IShader.class, new Locator("/shaders/test.shader"));

        mesh = new Mesh();
        mesh.setPrimitive(EPrimitive.TRIANGLES);
        mesh.setPositions(List.of(
                new Vector4f(-0.5f, -0.5f, -0.5f, 1.0f),
                new Vector4f(-0.5f, 0.5f, -0.5f, 1.0f),
                new Vector4f(0.5f, -0.5f, -0.5f, 1.0f),
                new Vector4f(0.5f, 0.5f, -0.5f, 1.0f),
                new Vector4f(-0.5f, -0.5f, 0.5f, 1.0f),
                new Vector4f(-0.5f, 0.5f, 0.5f, 1.0f),
                new Vector4f(0.5f, -0.5f, 0.5f, 1.0f),
                new Vector4f(0.5f, 0.5f, 0.5f, 1.0f)
        ));
        mesh.setIndices(List.of(0, 1, 3, 0, 3, 2));

        materialDef = new MaterialDef();
        materialDef.setShader(ERenderPass.FORWARD, shader);
        int colorIdx = materialDef.register("Diffuse", EAttributeType.COL4);
        materialDef.setDefault(colorIdx, new Color4f(0.0f, 0.5f, 0.0f, 1.0f));


        material = new Material(materialDef);
//        material.set(colorIdx, new Color4f(0.5f, 0.0f, 0.0f, 1.0f));
    }


    private static void renderFrame(IGraphics graphics) {
        if (material.bind(graphics, ERenderPass.FORWARD)) {
            graphics.render(mesh);
        }
    }

    public static void main(String[] args) {

        Engine engine = new Engine();
        engine.initialize();
        setupScene(engine);
        engine.run(new Engine.FrameCallback() {
            @Override
            public void postRender(IGraphics graphics) {
                renderFrame(graphics);
            }
        });
    }
}
