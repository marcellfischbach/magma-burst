package com.eruption.engine.core.graphics;

import com.eruption.engine.core.graphics.shading.IShader;
import com.eruption.engine.core.math.Color4f;

public interface IGraphics {

    void setViewport (int x, int y, int width, int height);
    void clear(boolean clearColor, Color4f color, boolean clearDepth, double depth, boolean clearStencil, int stencil);

    void setShader (IShader shader);

    void render (Mesh mesh);

    IVertexBuffer createVertexBuffer (int size);
    IIndexBuffer createIndexBuffer (int size);

    IGraphicsMesh createGraphicsMesh ();


}
