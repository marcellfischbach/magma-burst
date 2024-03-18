package com.eruption.engine.core.graphics.material;

import com.eruption.engine.core.graphics.ERenderPass;
import com.eruption.engine.core.graphics.IGraphics;
import com.eruption.engine.core.graphics.shading.IShader;
import com.eruption.engine.core.graphics.shading.IShaderAttribute;
import com.eruption.engine.core.math.*;

import java.util.ArrayList;
import java.util.List;

public class Material {

    private final MaterialDef materialDef;

    private static class Attribute {
        final int idx;
        final String name;
        final EAttributeType type;
        boolean override = false;
        final IShaderAttribute[] shaderAttributes;

        float float1;
        final Vector2f float2;
        final Vector3f float3;
        final Vector4f float4;
        final Color4f color;
        final Matrix3 mat3;
        final Matrix4 mat4;

        public Attribute(int idx, String name, EAttributeType type, IShaderAttribute[] shaderAttributes) {
            this.idx = idx;
            this.name = name;
            this.type = type;
            this.shaderAttributes = shaderAttributes;
            float2 = type == EAttributeType.VEC2 ? new Vector2f() : null;
            float3 = type == EAttributeType.VEC3 ? new Vector3f() : null;
            float4 = type == EAttributeType.VEC4 ? new Vector4f() : null;
            color = type == EAttributeType.COL4 ? new Color4f() : null;
            mat3 = type == EAttributeType.MAT3 ? new Matrix3() : null;
            mat4 = type == EAttributeType.MAT4 ? new Matrix4() : null;
        }
    }

    private final List<Attribute> attributes = new ArrayList<>();

    public Material(MaterialDef materialDef) {
        this.materialDef = materialDef;
        this.materialDef.getAttributeDescriptions().forEach(def -> attributes.add(new Attribute(def.idx(),
                                                                                                def.name(),
                                                                                                def.type(),
                                                                                                def.shaderAttributes()))
        );
    }

    public boolean bind (IGraphics graphics, ERenderPass pass) {
        IShader shader = this.materialDef.getShader(pass);
        if (shader == null) {
            return false;
        }

        graphics.setShader(shader);
        for (Attribute attribute : this.attributes) {
            bindAttribute(attribute.idx, pass);
        }
        return true;
    }

    public int indexOf (String attributeName) {
        return this.materialDef.indexOf(attributeName);
    }

    public void clear (int idx) {
        Attribute attribute = this.attributes.get(idx);
        attribute.override = false;
    }

    public void set(int idx, float value) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.FLOAT) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.FLOAT);
        }
        attribute.override = true;
        attribute.float1 = value;
    }

    public float getFloat (int idx) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.FLOAT) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.FLOAT);
        }
        return attribute.float1;
    }

    public void set(int idx, Vector2f value) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.VEC2) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.VEC2);
        }
        attribute.override = true;
        attribute.float2.set(value);
    }

    public Vector2f getFloat2 (int idx) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.VEC2) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.VEC2);
        }
        return attribute.float2;
    }


    public void set(int idx, Vector3f value) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.VEC3) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.VEC3);
        }
        attribute.override = true;
        attribute.float3.set(value);
    }


    public Vector3f getFloat3 (int idx) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.VEC3) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.VEC3);
        }
        return attribute.float3;
    }


    public void set(int idx, Vector4f value) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.VEC4) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.VEC4);
        }
        attribute.override = true;
        attribute.float4.set(value);
    }

    public Vector4f getFloat4 (int idx) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.VEC4) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.VEC4);
        }
        return attribute.float4;
    }


    public void set(int idx, Color4f value) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.COL4) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.COL4);
        }
        attribute.override = true;
        attribute.color.set(value);
    }


    public Color4f getColor4 (int idx) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.COL4) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.COL4);
        }
        attribute.override = true;
        return attribute.color;
    }



    public void set(int idx, Matrix3 value) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.MAT3) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.MAT3);
        }
        attribute.override = true;
        attribute.mat3.set(value);
    }


    public Matrix3 getMatrix3 (int idx) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.MAT3) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.MAT3);
        }
        return attribute.mat3;
    }


    public void set(int idx, Matrix4 value) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.MAT4) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.MAT4);
        }
        attribute.override = true;
        attribute.mat4.set(value);
    }


    public Matrix4 getMatrix4 (int idx) throws InvalidAttributeTypeException {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.MAT4) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.MAT4);
        }
        return attribute.mat4;
    }



    public void bindAttribute(int idx, ERenderPass pass) {
        Attribute attribute = this.attributes.get(idx);
        if (!attribute.override) {
            this.materialDef.bindAttribute(idx, pass);
            return;
        }


        IShaderAttribute attr = attribute.shaderAttributes[pass.ordinal()];
        if (attr == null) {
            return;
        }

        switch (attribute.type) {
            case FLOAT -> attr.bind(attribute.float1);
            case VEC2 -> attr.bind(attribute.float2.x, attribute.float2.y);
            case VEC3 -> attr.bind(attribute.float3.x, attribute.float3.y, attribute.float3.z);
            case VEC4 -> attr.bind(attribute.float4.x,
                                   attribute.float4.y,
                                   attribute.float4.z,
                                   attribute.float4.w);
            case COL4 -> attr.bind(attribute.color.r,
                                   attribute.color.g,
                                   attribute.color.b,
                                   attribute.color.a);
            case MAT3 -> attr.bind(attribute.mat3);
            case MAT4 -> attr.bind(attribute.mat4);
        }
    }
}
