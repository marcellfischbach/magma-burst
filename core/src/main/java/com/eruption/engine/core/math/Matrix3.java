package com.eruption.engine.core.math;

import java.nio.FloatBuffer;

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

    public void toBuffer (float[] b) {
        b[0] = m00;
        b[1] = m01;
        b[2] = m02;
        b[3] = m10;
        b[4] = m11;
        b[5] = m12;
        b[6] = m20;
        b[7] = m21;
        b[8] = m22;
    }

    public void toBuffer (FloatBuffer buffer) {
        buffer.put(m00);
        buffer.put(m01);
        buffer.put(m02);
        buffer.put(m10);
        buffer.put(m11);
        buffer.put(m12);
        buffer.put(m20);
        buffer.put(m21);
        buffer.put(m22);
    }
}
