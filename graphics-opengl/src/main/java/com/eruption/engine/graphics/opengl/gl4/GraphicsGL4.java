package com.eruption.engine.graphics.opengl.gl4;

import com.eruption.engine.core.graphics.IGraphics;
import com.eruption.engine.core.graphics.IGraphicsMesh;
import com.eruption.engine.core.graphics.Mesh;
import com.eruption.engine.core.graphics.shading.EShaderAttribute;
import com.eruption.engine.core.graphics.shading.IShader;
import com.eruption.engine.core.graphics.shading.IShaderAttribute;
import com.eruption.engine.core.math.Color4f;
import com.eruption.engine.core.math.Matrix4;
import com.eruption.engine.core.math.VecMath;
import com.eruption.engine.graphics.opengl.gl4.shading.ProgramGL4;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL45.*;

public class GraphicsGL4 implements IGraphics {

    private ProgramGL4 shader;

    private int clearColor = 0;
    private double clearDepth = 1.0f;
    private int clearStencil = 0;


    private final Matrix4 modelMatrix = new Matrix4();
    private final Matrix4 viewMatrix = new Matrix4();
    private final Matrix4 projectionMatrix = new Matrix4();
    private final Matrix4 modelViewMatrix = new Matrix4();
    private final Matrix4 viewProjectionMatrix = new Matrix4();
    private final Matrix4 modelViewProjectionMatrix = new Matrix4();
    private final Matrix4 modelMatrixInv = new Matrix4();
    private final Matrix4 viewMatrixInv = new Matrix4();
    private final Matrix4 projectionMatrixInv = new Matrix4();
    private final Matrix4 modelViewMatrixInv = new Matrix4();
    private final Matrix4 viewProjectionMatrixInv = new Matrix4();
    private final Matrix4 modelViewProjectionMatrixInv = new Matrix4();

    private boolean modelViewMatrixDirty = false;
    private boolean viewProjectionMatrixDirty = false;
    private boolean modelViewProjectionMatrixDirty = false;

    private boolean modelMatrixInvDirty = false;
    private boolean viewMatrixInvDirty = false;
    private boolean projectionMatrixInvDirty = false;
    private boolean modelViewMatrixInvDirty = false;
    private boolean viewProjectionMatrixInvDirty = false;
    private boolean modelViewProjectionMatrixInvDirty = false;


    public void initialize () {
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);

        glColorMask(true, true, true, true);
        glDepthMask(true);
    }

    @Override
    public void setViewport(int x, int y, int width, int height) {
        glViewport(x, y, width, height);

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
    public void setShader(IShader shader) {
        if (this.shader == shader) {
            return;
        }

        this.shader = (ProgramGL4)shader;
        glUseProgram(this.shader != null ? this.shader.name : 0);
        ErrorGL4.check();
    }


    @Override
    public void setModelMatrix(Matrix4 matrix) {
        this.modelMatrix.set(matrix);

        this.modelMatrixInvDirty = true;
        this.modelViewMatrixDirty = true;
        this.modelViewMatrixInvDirty = true;
        this.modelViewProjectionMatrixDirty = true;
        this.modelViewProjectionMatrixInvDirty = true;

    }

    @Override
    public void setViewMatrix(Matrix4 matrix) {
        this.viewMatrix.set(matrix);

        this.viewMatrixInvDirty = true;
        this.modelViewMatrixDirty = true;
        this.modelViewMatrixInvDirty = true;
        this.viewProjectionMatrixDirty = true;
        this.viewProjectionMatrixInvDirty = true;
        this.modelViewProjectionMatrixDirty = true;
        this.modelViewProjectionMatrixInvDirty = true;
    }

    @Override
    public void setViewMatrix(Matrix4 matrix, Matrix4 matrixInv) {
        this.viewMatrix.set(matrix);
        this.viewMatrixInv.set(matrixInv);

        this.viewMatrixInvDirty = false;
        this.modelViewMatrixDirty = true;
        this.modelViewMatrixInvDirty = true;
        this.viewProjectionMatrixDirty = true;
        this.viewProjectionMatrixInvDirty = true;
        this.modelViewProjectionMatrixDirty = true;
        this.modelViewProjectionMatrixInvDirty = true;
    }

    @Override
    public void setProjectionMatrix(Matrix4 matrix) {
        this.projectionMatrix.set(matrix);

        this.projectionMatrixInvDirty = true;
        this.viewProjectionMatrixDirty = true;
        this.viewProjectionMatrixInvDirty = true;
        this.modelViewProjectionMatrixDirty = true;
        this.modelViewProjectionMatrixInvDirty = true;
    }

    @Override
    public void setProjectionMatrix(Matrix4 matrix, Matrix4 matrixInv) {
        this.projectionMatrix.set(matrix);
        this.projectionMatrixInv.set(matrixInv);

        this.projectionMatrixInvDirty = false;
        this.viewProjectionMatrixDirty = true;
        this.viewProjectionMatrixInvDirty = true;
        this.modelViewProjectionMatrixDirty = true;
        this.modelViewProjectionMatrixInvDirty = true;
    }

    @Override
    public void render(Mesh mesh) {
        ErrorGL4.check();
        bindAttributes();
        ErrorGL4.check();
        mesh.render(this);
        ErrorGL4.check();
    }

    private void bindAttributes () {
        if (this.shader == null) {
            return;
        }
        IShaderAttribute attrib;

        if ((attrib = shader.getAttribute(EShaderAttribute.MODEL_MATRIX)) != null) {
            attrib.bind(this.modelMatrix);
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.VIEW_MATRIX)) != null) {
            attrib.bind(this.viewMatrix);
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.PROJECTION_MATRIX)) != null) {
            attrib.bind(this.projectionMatrix);
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.MODEL_VIEW_MATRIX)) != null) {
            attrib.bind(modelViewMatrix());
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.VIEW_PROJECTION_MATRIX)) != null) {
            attrib.bind(viewProjectionMatrix());
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.MODEL_VIEW_PROJECTION_MATRIX)) != null) {
            attrib.bind(modelViewProjectionMatrix());
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.MODEL_MATRIX_INV)) != null) {
            attrib.bind(modelMatrixInv());
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.VIEW_MATRIX_INV)) != null) {
            attrib.bind(viewMatrixInv());
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.PROJECTION_MATRIX_INV)) != null) {
            attrib.bind(projectionMatrixInv());
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.MODEL_VIEW_MATRIX_INV)) != null) {
            attrib.bind(modelViewMatrixInv());
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.VIEW_PROJECTION_MATRIX_INV)) != null) {
            attrib.bind(viewProjectionMatrixInv());
        }
        if ((attrib = shader.getAttribute(EShaderAttribute.MODEL_VIEW_PROJECTION_MATRIX_INV)) != null) {
            attrib.bind(modelViewProjectionMatrixInv());
        }
    }


    private Matrix4 modelViewMatrix () {
        if (this.modelViewMatrixDirty) {
            VecMath.mul(this.viewMatrix, this.modelMatrix, this.modelViewMatrix);
            this.modelViewMatrixDirty = false;
        }
        return this.modelViewMatrix;
    }

    private Matrix4 viewProjectionMatrix () {
        if (this.viewProjectionMatrixDirty) {
            VecMath.mul(this.projectionMatrix, this.viewMatrix, this.viewProjectionMatrix);
            this.viewProjectionMatrixDirty = false;
        }
        return this.viewProjectionMatrix;
    }
    private Matrix4 modelViewProjectionMatrix () {
        if (this.modelViewProjectionMatrixDirty) {
            VecMath.mul(viewProjectionMatrix(), this.modelMatrix, this.modelViewProjectionMatrix);
            this.modelViewProjectionMatrixDirty = false;
        }
        return this.modelViewProjectionMatrix;
    }


    private Matrix4 modelMatrixInv() {
        if (this.modelMatrixInvDirty) {
            VecMath.invert(this.modelMatrix, this.modelMatrixInv);
            this.modelMatrixInvDirty = false;
        }
        return this.modelMatrixInv;
    }

    private Matrix4 viewMatrixInv() {
        if (this.viewMatrixInvDirty) {
            VecMath.invert(this.viewMatrix, this.viewMatrixInv);
            this.viewMatrixInvDirty = false;
        }
        return this.viewMatrixInv;
    }

    private Matrix4 projectionMatrixInv() {
        if (this.projectionMatrixInvDirty) {
            VecMath.invert(this.projectionMatrix, this.projectionMatrixInv);
            this.projectionMatrixInvDirty = false;
        }
        return this.projectionMatrixInv;
    }

    private Matrix4 modelViewMatrixInv() {
        if (this.modelViewMatrixInvDirty) {
            VecMath.invert(modelViewMatrix(), this.modelViewMatrixInv);
            this.modelViewMatrixInvDirty = false;
        }
        return this.modelViewMatrixInv;
    }

    private Matrix4 viewProjectionMatrixInv() {
        if (this.viewProjectionMatrixInvDirty) {
            VecMath.invert(viewProjectionMatrix(), this.viewProjectionMatrixInv);
            this.viewProjectionMatrixInvDirty = false;
        }
        return this.viewProjectionMatrixInv;
    }

    private Matrix4 modelViewProjectionMatrixInv() {
        if (this.modelViewProjectionMatrixInvDirty) {
            VecMath.invert(modelViewProjectionMatrix(), this.modelViewProjectionMatrixInv);
            this.modelViewProjectionMatrixInvDirty = false;
        }
        return this.modelViewProjectionMatrixInv;
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
