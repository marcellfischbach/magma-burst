package com.cobaltsky.engine.graphics.opengl.gl4.shading;

import com.cobaltsky.engine.core.graphics.shading.IShaderAttribute;
import com.cobaltsky.engine.core.math.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL40.*;

public class ShaderAttributeGL4 implements IShaderAttribute {

    private final int base;

    private int index;

    private int offset;

    private final String locationName;

    public ShaderAttributeGL4(int base, String locationName) {
        this.base = base;
        this.locationName = locationName;
        this.index = 0;
        this.offset = base;
    }

    @Override
    public String toString() {
        return  locationName + "[" + base + "]";
    }

    @Override
    public String getName() {
        return locationName;
    }

    @Override
    public boolean isValid() {
        return this.base != -1;
    }

    @Override
    public void setArrayIndex(int idx) {
        this.index = idx;
        this.offset = this.base + idx;
    }

    @Override
    public void bind(float x) {
        glUniform1f(this.offset, x);
    }

    @Override
    public void bind(float x, float y) {
        glUniform2f(this.offset, x, y);
    }

    @Override
    public void bind(float x, float y, float z) {
        glUniform3f(this.offset, x, y, z);
    }

    @Override
    public void bind(float x, float y, float z, float w) {
        glUniform4f(this.offset, x, y, z, w);
    }

    @Override
    public void bind(Vector2f v) {
        glUniform2f(this.offset, v.x, v.y);
    }

    @Override
    public void bind(Vector3f v) {
        glUniform3f(this.offset, v.x, v.y, v.z);
    }

    @Override
    public void bind(Vector4f v) {
        glUniform4f(this.offset, v.x, v.y, v.z, v.w);
    }

    @Override
    public void bind(int x) {
        glUniform1i(this.offset, x);
    }

    @Override
    public void bind(int x, int y) {
        glUniform2i(this.offset, x, y);
    }

    @Override
    public void bind(int x, int y, int z) {
        glUniform3i(this.offset, x, y, z);
    }

    @Override
    public void bind(int x, int y, int z, int w) {
        glUniform4i(this.offset, x, y, z, w);
    }

    @Override
    public void bind(Vector2i v) {
        glUniform2i(this.offset, v.x, v.y);
    }

    @Override
    public void bind(Vector3i v) {
        glUniform3i(this.offset, v.x, v.y, v.z);
    }

    @Override
    public void bind(Vector4i v) {
        glUniform4i(this.offset, v.x, v.y, v.z, v.w);
    }

    @Override
    public void bind(Color4f c) {
        glUniform4f(this.offset, c.r, c.g, c.b, c.a);
    }

    private static float[] BUFFER3 = new float[9];
    private static float[] BUFFER4 = new float[16];

    private static FloatBuffer NIO_BUFFER = ByteBuffer.allocateDirect(256 * 16 * Float.BYTES).asFloatBuffer();

    public static void main(String[] args) {
        System.out.println();
    }

    @Override
    public void bind(Matrix3 mat) {
        mat.toBuffer(BUFFER3);
        glUniformMatrix3fv(this.offset, false, BUFFER3);
    }

    @Override
    public void bind(Matrix3[] mat) {
        NIO_BUFFER.clear();
        for (Matrix3 matrix3 : mat) {
            matrix3.toBuffer(BUFFER3);
            NIO_BUFFER.put(BUFFER3);
        }
        NIO_BUFFER.limit(NIO_BUFFER.position());
        glUniformMatrix3fv(this.offset, false, NIO_BUFFER);
    }


    @Override
    public void bind(Matrix4 mat) {
        mat.toBuffer(BUFFER4);
        glUniformMatrix4fv(this.offset, false, BUFFER4);
    }

    @Override
    public void bind(Matrix4[] mat) {
        NIO_BUFFER.clear();
        for (Matrix4 matrix4 : mat) {
            matrix4.toBuffer(BUFFER4);
            NIO_BUFFER.put(BUFFER4);
        }
        NIO_BUFFER.limit(NIO_BUFFER.position());
        glUniformMatrix4fv(this.offset, false, NIO_BUFFER);
    }
}
