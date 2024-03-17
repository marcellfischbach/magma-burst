package com.cobaltsky.engine.core.graphics.shading;

import com.cobaltsky.engine.core.math.*;

public interface IShaderAttribute {

    String getName ();

    boolean isValid();

    void setArrayIndex (int idx);

    void bind(float x);
    void bind(float x, float y);
    void bind(float x, float y, float z);
    void bind(float x, float y, float z, float w);
    void bind(Vector2f v);
    void bind(Vector3f v);
    void bind(Vector4f v);

    void bind(int x);
    void bind(int x, int y);
    void bind(int x, int y, int z);
    void bind(int x, int y, int z, int w);
    void bind(Vector2i v);
    void bind(Vector3i v);
    void bind(Vector4i v);

    void bind(Color4f c);

    void bind(Matrix3 mat);
    void bind(Matrix3[] mat);
    void bindMatrix3(float[] buffer);

    void bind(Matrix4 mat);
    void bind(Matrix4[] mat);
    void bindMatrix4(float[] buffer);

}
