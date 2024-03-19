package com.eruption.engine.core.math;

public class Matrix4 {

    public float m00 = 1.0f;
    public float m01 = 0.0f;
    public float m02 = 0.0f;
    public float m03 = 0.0f;

    public float m10 = 0.0f;
    public float m11 = 1.0f;
    public float m12 = 0.0f;
    public float m13 = 0.0f;

    public float m20 = 0.0f;
    public float m21 = 0.0f;
    public float m22 = 1.0f;
    public float m23 = 0.0f;

    public float m30 = 0.0f;
    public float m31 = 0.0f;
    public float m32 = 0.0f;
    public float m33 = 1.0f;


    public void set(Matrix4 m) {
        this.m00 = m.m00;
        this.m01 = m.m01;
        this.m02 = m.m02;
        this.m03 = m.m03;

        this.m10 = m.m10;
        this.m11 = m.m11;
        this.m12 = m.m12;
        this.m13 = m.m13;

        this.m20 = m.m20;
        this.m21 = m.m21;
        this.m22 = m.m22;
        this.m23 = m.m23;

        this.m30 = m.m30;
        this.m31 = m.m31;
        this.m32 = m.m32;
        this.m33 = m.m33;
    }


    public void set(float m00, float m01, float m02, float m03,
                    float m10, float m11, float m12, float m13,
                    float m20, float m21, float m22, float m23,
                    float m30, float m31, float m32, float m33) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;

        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;

        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;

        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }


    public Matrix4 setIdentity()
    {
        m01 = m02 = m03 = m10 = m12 = m13 = m20 = m21 = m23 = m30 = m31 = m32 = 0.0f;
        m00 = m11 = m22 = m33 = 1.0f;
        return this;
    }


    public Matrix4 setXAxis(float x, float y, float z, float w)
    {
        m00 = x;
        m01 = y;
        m02 = z;
        m03 = w;
        return this;
    }

    public Matrix4 setXAxis(Vector3f v, float w)
    {
        m00 = v.x;
        m01 = v.y;
        m02 = v.z;
        m03 = w;
        return this;
    }

    

    public Matrix4 setXAxis(Vector4f v)
    {
        m00 = v.x;
        m01 = v.y;
        m02 = v.z;
        m03 = v.w;
        return this;
    }


    public Matrix4 setYAxis(float x, float y, float z, float w)
    {
        m10 = x;
        m11 = y;
        m12 = z;
        m13 = w;
        return this;
    }

    public Matrix4 setYAxis(Vector3f v, float w)
    {
        m10 = v.x;
        m11 = v.y;
        m12 = v.z;
        m13 = w;
        return this;
    }


    public Matrix4 setYAxis(Vector4f v)
    {
        m10 = v.x;
        m11 = v.y;
        m12 = v.z;
        m13 = v.w;
        return this;
    }


    public Matrix4 setZAxis(float x, float y, float z, float w)
    {
        m20 = x;
        m21 = y;
        m22 = z;
        m23 = w;
        return this;
    }

    public Matrix4 setZAxis(Vector3f v, float w)
    {
        m20 = v.x;
        m21 = v.y;
        m22 = v.z;
        m23 = w;
        return this;
    }

    public Matrix4 setZAxis(Vector4f v)
    {
        m20 = v.x;
        m21 = v.y;
        m22 = v.z;
        m23 = v.w;
        return this;
    }


    public Matrix4 setTranslation(float x, float y, float z, float w)
    {
        m30 = x;
        m31 = y;
        m32 = z;
        m33 = w;
        return this;
    }

    public Matrix4 setTranslation(Vector3f tr, float w)
    {
        m30 = tr.x;
        m31 = tr.y;
        m32 = tr.z;
        m33 = w;
        return this;
    }

    public Matrix4 setTranslation(Vector4f tr)
    {
        m30 = tr.x;
        m31 = tr.y;
        m32 = tr.z;
        m33 = tr.w;
        return this;
    }



    public Matrix4 clearRotation()
    {
        m00 = m11 = m22 = 1.0f;
        m01 = m02 = m10 = m12 = m20 = m21 = 0.0f;
        return this;
    }


    public Matrix4 setRotationX(float angle)
    {
        float c = (float)Math.cos(angle);
        float s = (float)Math.sin(angle);
        m11 = c;
        m12 = s;
        m21 = -s;
        m22 = c;
        return this;
    }

    public Matrix4 setRotationY(float angle)
    {
        float c = (float)Math.cos(angle);
        float s = (float)Math.sin(angle);
        m00 = c;
        m02 = -s;
        m20 = s;
        m22 = c;
        return this;
    }

    public Matrix4 setRotationZ(float angle)
    {
        float c = (float)Math.cos(angle);
        float s = (float)Math.sin(angle);
        m00 = c;
        m01 = s;
        m10 = -s;
        m11 = c;
        return this;
    }

    public Matrix4 setScale(float x, float y, float z)
    {
        m00 = x;
        m11 = y;
        m22 = z;
        return this;
    }

    public Matrix4 setScale( Vector3f s)
    {
        m00 = s.x;
        m11 = s.y;
        m22 = s.z;
        return this;
    }


    public Matrix4 setRotation( Vector3f axis, float angle)
    {
        float c = (float)Math.cos(angle);
        float s = (float)Math.sin(angle);

        float x = axis.x;
        float y = axis.y;
        float z = axis.z;

        float ic = 1.0f - c;
        m00 = x * x * ic + c;
        m10 = x * y * ic - z * s;
        m20 = x * z * ic + y * s;
        m01 = y * x * ic + z * s;
        m11 = y * y * ic + c;
        m21 = y * z * ic - x * s;
        m02 = z * x * ic - y * s;
        m12 = z * y * ic + x * s;
        m22 = z * z * ic + c;

        return this;
    }

    public void toBuffer(float[] b) {
        b[0] = m00;
        b[1] = m01;
        b[2] = m02;
        b[3] = m03;
        b[4] = m10;
        b[5] = m11;
        b[6] = m12;
        b[7] = m13;
        b[8] = m20;
        b[9] = m21;
        b[10] = m22;
        b[11] = m23;
        b[12] = m30;
        b[13] = m31;
        b[14] = m32;
        b[15] = m33;
    }

  

    public Matrix4 setPerspectiveProjection(float l, float r, float b, float t, float n, float f) {
        float z2 = 2.0f * n;
        float dx = r - l;
        float dy = t - b;
        float dz = f - n;
        float sx = r + l;
        float sy = t + b;
        float sz = n + f;


        m00 = z2 / dx;
        m10 = 0.0f;
        m20 = -sx / dx;
        m30 = 0.0f;

        m01 = 0.0f;
        m11 = z2 / dy;
        m21 = -sy / dy;
        m31 = 0.0f;

        m02 = 0.0f;
        m12 = 0.0f;
        m22 = sz / dz;
        m32 = -2.0f * n * f / dz;

        m03 = 0.0f;
        m13 = 0.0f;
        m23 = 1.0f;
        m33 = 0.0f;
        return this;
    }


    public Matrix4 setPerspectiveProjectionInv(float l, float r, float b, float t, float n, float f) {
        float z2 = 2.0f * n;
        float dx = r - l;
        float dy = t - b;
        float dz = f - n;
        float sx = r + l;
        float sy = t + b;
        float sz = n + f;
        float nf2 = z2 * f;


        m00 = dx / z2;
        m10 = 0.0f;
        m20 = 0.0f;
        m30 = sx / z2;
        m01 = 0.0f;
        m11 = dy / z2;
        m21 = 0.0f;
        m31 = sy / z2;
        m02 = 0.0f;
        m12 = 0.0f;
        m22 = 0.0f;
        m32 = 1.0f;
        m03 = 0.0f;
        m13 = 0.0f;
        m23 = -dz / nf2;
        m33 = sz / nf2;


        return this;
    }

    public Matrix4 setOrthographicProjection(float l, float r, float b, float t, float n, float f) {
        float dx = r - l;
        float dy = t - b;
        float dz = f - n;
        float sx = r + l;
        float sy = t + b;
        float sz = f + n;

        m00 = 2.0f / dx;
        m10 = 0.0f;
        m20 = 0.0f;
        m30 = -sx / dx;
        m01 = 0.0f;
        m11 = 2.0f / dy;
        m21 = 0.0f;
        m31 = -sy / dy;
        m02 = 0.0f;
        m12 = 0.0f;
        m22 = 2.0f / dz;
        m32 = -sz / dz;
        m03 = 0.0f;
        m13 = 0.0f;
        m23 = 0.0f;
        m33 = 1.0f;
        return this;
    }

    public Matrix4 setOrthographicProjectionInv(float l, float r, float b, float t, float n, float f) {
        float dx = r - l;
        float dy = t - b;
        float dz = f - n;
        float sx = r + l;
        float sy = t + b;
        float sz = n + f;

        m00 = dx / 2.0f;
        m10 = 0.0f;
        m20 = 0.0f;
        m30 = sx / 2.0f;
        m01 = 0.0f;
        m11 = dy / 2.0f;
        m21 = 0.0f;
        m31 = sy / 2.0f;
        m02 = 0.0f;
        m12 = 0.0f;
        m22 = dz / 2.0f;
        m32 = sz / 2.0f;
        m03 = 0.0f;
        m13 = 0.0f;
        m23 = 0.0f;
        m33 = 1.0f;

        return this;
    }

    public void setLookAt(Vector3f eye, Vector3f spot, Vector3f up)
    {

        Vector3f zAxis = VecMath.sub(spot, eye, new Vector3f()).normalize();
        Vector3f xAxis = VecMath.cross(up, zAxis, new Vector3f()).normalize();
        Vector3f yAxis = VecMath.cross(zAxis, xAxis, new Vector3f());
//    Vector3f::Sub(spot, eye, zAxis).Normalize();
//    Vector3f::Cross(up, zAxis, xAxis).Normalize();
//    Vector3f::Cross(zAxis, xAxis, yAxis);
        setXAxis(xAxis.x, yAxis.x, zAxis.x, 0.0f);
        setYAxis(xAxis.y, yAxis.y, zAxis.y, 0.0f);
        setZAxis(xAxis.z, yAxis.z, zAxis.z, 0.0f);
        setTranslation(-VecMath.dot(xAxis, eye), -VecMath.dot(yAxis, eye), -VecMath.dot(zAxis, eye), 1.0f);
    }

    public void setLookAtInv(Vector3f eye, Vector3f spot, Vector3f up)
    {
//    Vector3f xAxis, yAxis, zAxis;
//    Vector3f::Sub(eye, spot, zAxis).Normalize();
//    Vector3f::Cross(up, zAxis, xAxis).Normalize();
//    Vector3f::Cross(zAxis, xAxis, yAxis);
//
//    Vector3f::Sub(spot, eye, zAxis).Normalize();
//    Vector3f::Cross(up, zAxis, xAxis).Normalize();
//    Vector3f::Cross(zAxis, xAxis, yAxis);

        Vector3f zAxis = VecMath.sub(spot, eye, new Vector3f()).normalize();
        Vector3f xAxis = VecMath.cross(up, zAxis, new Vector3f()).normalize();
        Vector3f yAxis = VecMath.cross(zAxis, xAxis, new Vector3f());


        setXAxis(xAxis, 0.0f);
        setYAxis(yAxis, 0.0f);
        setZAxis(zAxis, 0.0f);
        setTranslation(eye, 1.0f);
    }
}
