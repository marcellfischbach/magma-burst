package com.cobaltsky.engine.core.math;

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
}