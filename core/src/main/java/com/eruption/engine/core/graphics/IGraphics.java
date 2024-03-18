package com.eruption.engine.core.graphics;

import com.eruption.engine.core.math.Color4f;

public interface IGraphics {

    void clear(boolean clearColor, Color4f color, boolean clearDepth, double depth, boolean clearStencil, int stencil);

    IVertexBuffer createVertexBuffer (int size);
    IIndexBuffer createIndexBuffer (int size);

    IGraphicsMesh createGraphicsMesh ();
}
