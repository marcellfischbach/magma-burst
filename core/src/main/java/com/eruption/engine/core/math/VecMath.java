package com.eruption.engine.core.math;

@SuppressWarnings("unused")
public abstract class VecMath {


    public static Vector2f add(Vector2f v0, Vector2f v1, Vector2f r) {
        r.x = v0.x + v1.x;
        r.y = v0.y + v1.y;
        return r;
    }

    public static Vector3f add(Vector3f v0, Vector3f v1, Vector3f r) {
        r.x = v0.x + v1.x;
        r.y = v0.y + v1.y;
        r.z = v0.z + v1.z;
        return r;
    }


    public static Vector4f add(Vector4f v0, Vector4f v1, Vector4f r) {
        r.x = v0.x + v1.x;
        r.y = v0.y + v1.y;
        r.z = v0.z + v1.z;
        r.w = v0.w + v1.w;
        return r;
    }


    public static Vector2f sub(Vector2f v0, Vector2f v1, Vector2f r) {
        r.x = v0.x - v1.x;
        r.y = v0.y - v1.y;
        return r;
    }

    public static Vector3f sub(Vector3f v0, Vector3f v1, Vector3f r) {
        r.x = v0.x - v1.x;
        r.y = v0.y - v1.y;
        r.z = v0.z - v1.z;
        return r;
    }


    public static Vector4f sub(Vector4f v0, Vector4f v1, Vector4f r) {
        r.x = v0.x - v1.x;
        r.y = v0.y - v1.y;
        r.z = v0.z - v1.z;
        r.w = v0.w - v1.w;
        return r;
    }

    public static Vector2f mul(Vector2f v, float f, Vector2f r) {
        r.x = v.x * f;
        r.y = v.y * f;
        return r;
    }

    public static Vector3f mul(Vector3f v, float f, Vector3f r) {
        r.x = v.x * f;
        r.y = v.y * f;
        r.z = v.z * f;
        return r;
    }

    public static float dot (Vector3f v0, Vector3f v1) {
        return v0.x*v1.x + v0.y*v1.y + v0.z*v1.z;
    }

    public static Vector3f cross(Vector3f v0, Vector3f v1, Vector3f r) {
        float x = v0.y * v1.z - v0.z * v1.y;
        float y = v0.z * v1.x - v0.x * v1.z;
        float z = v0.x * v1.y - v0.y * v1.x;
        r.x = x;
        r.y = y;
        r.z = z;
        return r;
    }

    public static Vector4f mul(Vector4f v, float f, Vector4f r) {
        r.x = v.x * f;
        r.y = v.y * f;
        r.z = v.z * f;
        r.w = v.w * f;
        return r;
    }


    public static Vector2f div(Vector2f v, float f, Vector2f r) {
        r.x = v.x / f;
        r.y = v.y / f;
        return r;
    }

    public static Vector3f div(Vector3f v, float f, Vector3f r) {
        r.x = v.x / f;
        r.y = v.y / f;
        r.z = v.z / f;
        return r;
    }

    public static Vector4f div(Vector4f v, float f, Vector4f r) {
        r.x = v.x / f;
        r.y = v.y / f;
        r.z = v.z / f;
        r.w = v.w / f;
        return r;
    }

    public static Matrix3 mul(Matrix3 m0, Matrix3 m1, Matrix3 r) {
        float m00 = m0.m00 * m1.m00 + m0.m10 * m1.m01 + m0.m20 * m1.m02;
        float m01 = m0.m01 * m1.m00 + m0.m11 * m1.m01 + m0.m21 * m1.m02;
        float m02 = m0.m02 * m1.m00 + m0.m12 * m1.m01 + m0.m22 * m1.m02;

        float m10 = m0.m00 * m1.m10 + m0.m10 * m1.m11 + m0.m20 * m1.m12;
        float m11 = m0.m01 * m1.m10 + m0.m11 * m1.m11 + m0.m21 * m1.m12;
        float m12 = m0.m02 * m1.m10 + m0.m12 * m1.m11 + m0.m22 * m1.m12;

        float m20 = m0.m00 * m1.m20 + m0.m10 * m1.m21 + m0.m20 * m1.m22;
        float m21 = m0.m01 * m1.m20 + m0.m11 * m1.m21 + m0.m21 * m1.m22;
        float m22 = m0.m02 * m1.m20 + m0.m12 * m1.m21 + m0.m22 * m1.m22;

        r.set(m00, m01, m02,
              m10, m11, m12,
              m20, m21, m22);

        return r;
    }

    public static Matrix4 mul(Matrix4 m0, Matrix4 m1, Matrix4 r) {
        float m00 = m0.m00 * m1.m00 + m0.m10 * m1.m01 + m0.m20 * m1.m02 + m0.m30 * m1.m03;
        float m01 = m0.m01 * m1.m00 + m0.m11 * m1.m01 + m0.m21 * m1.m02 + m0.m31 * m1.m03;
        float m02 = m0.m02 * m1.m00 + m0.m12 * m1.m01 + m0.m22 * m1.m02 + m0.m32 * m1.m03;
        float m03 = m0.m03 * m1.m00 + m0.m13 * m1.m01 + m0.m23 * m1.m02 + m0.m33 * m1.m03;

        float m10 = m0.m00 * m1.m10 + m0.m10 * m1.m11 + m0.m20 * m1.m12 + m0.m30 * m1.m13;
        float m11 = m0.m01 * m1.m10 + m0.m11 * m1.m11 + m0.m21 * m1.m12 + m0.m31 * m1.m13;
        float m12 = m0.m02 * m1.m10 + m0.m12 * m1.m11 + m0.m22 * m1.m12 + m0.m32 * m1.m13;
        float m13 = m0.m03 * m1.m10 + m0.m13 * m1.m11 + m0.m23 * m1.m12 + m0.m33 * m1.m13;

        float m20 = m0.m00 * m1.m20 + m0.m10 * m1.m21 + m0.m20 * m1.m22 + m0.m30 * m1.m23;
        float m21 = m0.m01 * m1.m20 + m0.m11 * m1.m21 + m0.m21 * m1.m22 + m0.m31 * m1.m23;
        float m22 = m0.m02 * m1.m20 + m0.m12 * m1.m21 + m0.m22 * m1.m22 + m0.m32 * m1.m23;
        float m23 = m0.m03 * m1.m20 + m0.m13 * m1.m21 + m0.m23 * m1.m22 + m0.m33 * m1.m23;

        float m30 = m0.m00 * m1.m30 + m0.m10 * m1.m31 + m0.m20 * m1.m32 + m0.m30 * m1.m33;
        float m31 = m0.m01 * m1.m30 + m0.m11 * m1.m31 + m0.m21 * m1.m32 + m0.m31 * m1.m33;
        float m32 = m0.m02 * m1.m30 + m0.m12 * m1.m31 + m0.m22 * m1.m32 + m0.m32 * m1.m33;
        float m33 = m0.m03 * m1.m30 + m0.m13 * m1.m31 + m0.m23 * m1.m32 + m0.m33 * m1.m33;

        r.set(m00, m01, m02, m03,
              m10, m11, m12, m13,
              m20, m21, m22, m23,
              m30, m31, m32, m33);

        return r;
    }

    public static Vector3f mul(Matrix3 m, Vector4f v, Vector3f r) {
        float x = m.m00 * v.x + m.m10 * v.y + m.m20 * v.z;
        float y = m.m01 * v.x + m.m11 * v.y + m.m21 * v.z;
        float z = m.m02 * v.x + m.m12 * v.y + m.m22 * v.z;

        r.x = x;
        r.y = y;
        r.z = z;

        return r;
    }


    public static Vector4f mul(Matrix4 m, Vector4f v, Vector4f r) {
        float x = m.m00 * v.x + m.m10 * v.y + m.m20 * v.z + m.m30 * v.w;
        float y = m.m01 * v.x + m.m11 * v.y + m.m21 * v.z + m.m31 * v.w;
        float z = m.m02 * v.x + m.m12 * v.y + m.m22 * v.z + m.m32 * v.w;
        float w = m.m03 * v.x + m.m13 * v.y + m.m23 * v.z + m.m33 * v.w;

        r.x = x;
        r.y = y;
        r.z = z;
        r.w = w;

        return r;
    }

    public static Matrix4 invert(Matrix4 m, Matrix4 r) {
        float v0 = m.m20 * m.m31 - m.m21 * m.m30;
        float v1 = m.m20 * m.m32 - m.m22 * m.m30;
        float v2 = m.m20 * m.m33 - m.m23 * m.m30;
        float v3 = m.m21 * m.m32 - m.m22 * m.m31;
        float v4 = m.m21 * m.m33 - m.m23 * m.m31;
        float v5 = m.m22 * m.m33 - m.m23 * m.m32;

        float t00 = +(v5 * m.m11 - v4 * m.m12 + v3 * m.m13);
        float t10 = -(v5 * m.m10 - v2 * m.m12 + v1 * m.m13);
        float t20 = +(v4 * m.m10 - v2 * m.m11 + v0 * m.m13);
        float t30 = -(v3 * m.m10 - v1 * m.m11 + v0 * m.m12);

        float invDet = 1.0f / (t00 * m.m00 + t10 * m.m01 + t20 * m.m02 + t30 * m.m03);

        float lm00 = t00 * invDet;
        float lm10 = t10 * invDet;
        float lm20 = t20 * invDet;
        float lm30 = t30 * invDet;

        float lm01 = -(v5 * m.m01 - v4 * m.m02 + v3 * m.m03) * invDet;
        float lm11 = +(v5 * m.m00 - v2 * m.m02 + v1 * m.m03) * invDet;
        float lm21 = -(v4 * m.m00 - v2 * m.m01 + v0 * m.m03) * invDet;
        float lm31 = +(v3 * m.m00 - v1 * m.m01 + v0 * m.m02) * invDet;

        v0 = m.m10 * m.m31 - m.m11 * m.m30;
        v1 = m.m10 * m.m32 - m.m12 * m.m30;
        v2 = m.m10 * m.m33 - m.m13 * m.m30;
        v3 = m.m11 * m.m32 - m.m12 * m.m31;
        v4 = m.m11 * m.m33 - m.m13 * m.m31;
        v5 = m.m12 * m.m33 - m.m13 * m.m32;

        float lm02 = +(v5 * m.m01 - v4 * m.m02 + v3 * m.m03) * invDet;
        float lm12 = -(v5 * m.m00 - v2 * m.m02 + v1 * m.m03) * invDet;
        float lm22 = +(v4 * m.m00 - v2 * m.m01 + v0 * m.m03) * invDet;
        float lm32 = -(v3 * m.m00 - v1 * m.m01 + v0 * m.m02) * invDet;

        v0 = m.m21 * m.m10 - m.m20 * m.m11;
        v1 = m.m22 * m.m10 - m.m20 * m.m12;
        v2 = m.m23 * m.m10 - m.m20 * m.m13;
        v3 = m.m22 * m.m11 - m.m21 * m.m12;
        v4 = m.m23 * m.m11 - m.m21 * m.m13;
        v5 = m.m23 * m.m12 - m.m22 * m.m13;

        float lm03 = -(v5 * m.m01 - v4 * m.m02 + v3 * m.m03) * invDet;
        float lm13 = +(v5 * m.m00 - v2 * m.m02 + v1 * m.m03) * invDet;
        float lm23 = -(v4 * m.m00 - v2 * m.m01 + v0 * m.m03) * invDet;
        float lm33 = +(v3 * m.m00 - v1 * m.m01 + v0 * m.m02) * invDet;

        r.set(
                lm00, lm01, lm02, lm03,
                lm10, lm11, lm12, lm13,
                lm20, lm21, lm22, lm23,
                lm30, lm31, lm32, lm33
        );
        return r;
    }

    private VecMath() {
    }
}
