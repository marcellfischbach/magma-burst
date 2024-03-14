package com.cobaltsky.engine.core.math;

public class Matrix3 {

    public float m00 = 1.0f;
    public float m01 = 0.0f;
    public float m02 = 0.0f;

    public float m10 = 0.0f;
    public float m11 = 1.0f;
    public float m12 = 0.0f;

    public float m20 = 0.0f;
    public float m21 = 0.0f;
    public float m22 = 1.0f;

    public void set(float m00, float m01, float m02,
                    float m10, float m11, float m12,
                    float m20, float m21, float m22) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;

        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;

        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;

    }
}