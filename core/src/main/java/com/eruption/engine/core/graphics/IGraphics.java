package com.eruption.engine.core.graphics;

import com.eruption.engine.core.graphics.shading.IShader;
import com.eruption.engine.core.math.Color4f;
import com.eruption.engine.core.math.Matrix4;

public interface IGraphics {

    void setViewport (int x, int y, int width, int height);
    void clear(boolean clearColor, Color4f color, boolean clearDepth, double depth, boolean clearStencil, int stencil);

    void setShader (IShader shader);
    void setModelMatrix (Matrix4 matrix);
    void setViewMatrix (Matrix4 matrix);
    void setViewMatrix (Matrix4 matrix, Matrix4 matrixInv);
    void setProjectionMatrix (Matrix4 matrix);
    void setProjectionMatrix (Matrix4 matrix, Matrix4 matrixInv);

    void render (Mesh mesh);

    IVertexBuffer createVertexBuffer (int size);
    IIndexBuffer createIndexBuffer (int size);

    IGraphicsMesh createGraphicsMesh ();


}
