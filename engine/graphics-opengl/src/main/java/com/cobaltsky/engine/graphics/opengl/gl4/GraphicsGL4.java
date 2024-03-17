package com.cobaltsky.engine.graphics.opengl.gl4;

import com.cobaltsky.engine.core.graphics.IGraphics;
import com.cobaltsky.engine.core.graphics.IGraphicsMesh;
import com.cobaltsky.engine.core.math.Color4f;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL45.*;

public class GraphicsGL4 implements IGraphics {


    private int clearColor = 0;
    private double clearDepth = 1.0f;
    private int clearStencil = 0;


    public void initialize () {
        GL.createCapabilities();
    }

    @Override
    public void clear(boolean clearColor,
                      Color4f color,
                      boolean clearDepth,
                      double depth,
                      boolean clearStencil,
                      int stencil) {

        int mask = 0;
        if (clearColor) {
            int argb = color.argb();
            if (argb != this.clearColor) {
                glClearColor(color.r, color.g, color.b, color.a);
                this.clearColor = argb;
            }
            mask |= GL_COLOR_BUFFER_BIT;
        }
        if (clearDepth) {
            if (depth != this.clearDepth) {
                glClearDepth(depth);
                this.clearDepth = depth;
            }
            mask |= GL_DEPTH_BUFFER_BIT;
        }
        if (clearStencil) {
            if (stencil != this.clearStencil) {
                glClearStencil(stencil);
                this.clearStencil = stencil;
            }
            mask |= GL_STENCIL_BUFFER_BIT;
        }

        glClear(mask);
    }


    @Override
    public VertexBufferGL4 createVertexBuffer(int size) {
        VertexBufferGL4 vb = new VertexBufferGL4();
        vb.bind ();
        vb.create(size);
        return vb;
    }

    @Override
    public IndexBufferGL4 createIndexBuffer(int size) {
        IndexBufferGL4 vb = new IndexBufferGL4();
        vb.bind ();
        vb.create(size);
        return vb;
    }

    @Override
    public IGraphicsMesh createGraphicsMesh() {
        return new GraphicsMeshGL4();
    }
}
