package com.eruption.engine.core.graphics.material;

import com.eruption.engine.core.graphics.ERenderPass;
import com.eruption.engine.core.graphics.shading.IShader;
import com.eruption.engine.core.graphics.shading.IShaderAttribute;
import com.eruption.engine.core.math.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialDef {

    private static class Attribute {
        final int idx;
        final String name;
        final EAttributeType type;
        IShaderAttribute[] shaderAttributes = new IShaderAttribute[ERenderPass.values().length];

        float float1;
        final Vector2f float2;
        final Vector3f float3;
        final Vector4f float4;
        final Color4f color;
        final Matrix3 mat3;
        final Matrix4 mat4;

        public Attribute(int idx, String name, EAttributeType type) {
            this.idx = idx;
            this.name = name;
            this.type = type;
            float2 = type == EAttributeType.VEC2 ? new Vector2f() : null;
            float3 = type == EAttributeType.VEC3 ? new Vector3f() : null;
            float4 = type == EAttributeType.VEC4 ? new Vector4f() : null;
            color = type == EAttributeType.COL4 ? new Color4f() : null;
            mat3 = type == EAttributeType.MAT3 ? new Matrix3() : null;
            mat4 = type == EAttributeType.MAT4 ? new Matrix4() : null;
        }
    }

    private IShader[] shaders = new IShader[ERenderPass.values().length];

    private final List<Attribute> attributes = new ArrayList<>();


    public void setShader(ERenderPass pass, IShader shader) {
        this.shaders[pass.ordinal()] = shader;
        this.attributes.forEach(attr -> attr.shaderAttributes[pass.ordinal()] = shader.getAttribute(attr.name));
    }

    public IShader getShader(ERenderPass pass) {
        return this.shaders[pass.ordinal()];
    }

    public int register(String attributeName, EAttributeType type) {
        int idx = indexOf(attributeName);
        if (idx != -1) {
            throw new AttributeAlreadyDefinedException(attributeName);
        }

        idx = this.attributes.size();
        Attribute attribute = new Attribute(idx, attributeName, type);
        for (ERenderPass renderPass : ERenderPass.values()) {
            IShader shader = shaders[renderPass.ordinal()];
            attribute.shaderAttributes[renderPass.ordinal()] = shader != null ? shader.getAttribute(attributeName) : null;
        }
        this.attributes.add(attribute);
        return idx;
    }

    public int indexOf(String attributeName) {

        for (int i = 0; i < attributes.size(); i++) {
            if (this.attributes.get(i).name.equals(attributeName)) {
                return i;
            }
        }
        return -1;
    }

    public record AttributeDesc(int idx, String name, EAttributeType type, IShaderAttribute[] shaderAttributes) {
    }

    public List<AttributeDesc> getAttributeDescriptions() {
        return this.attributes.stream()
                              .map(a -> new AttributeDesc(a.idx, a.name, a.type, a.shaderAttributes))
                              .collect(Collectors.toList());
    }

    public void setDefault(int idx, float value) {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.FLOAT) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.FLOAT);
        }
        attribute.float1 = value;
    }

    public void setDefault(int idx, Vector2f value) {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.VEC2) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.VEC2);
        }
        attribute.float2.set(value);
    }

    public void setDefault(int idx, Vector3f value) {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.VEC3) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.VEC3);
        }
        attribute.float3.set(value);
    }

    public void setDefault(int idx, Vector4f value) {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.VEC4) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.VEC4);
        }
        attribute.float4.set(value);
    }

    public void setDefault(int idx, Color4f value) {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.COL4) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.COL4);
        }
        attribute.color.set(value);
    }


    public void setDefault(int idx, Matrix3 value) {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.MAT3) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.MAT3);
        }
        attribute.mat3.set(value);
    }


    public void setDefault(int idx, Matrix4 value) {
        Attribute attribute = this.attributes.get(idx);
        if (attribute.type != EAttributeType.MAT4) {
            throw new InvalidAttributeTypeException(attribute.name, attribute.type, EAttributeType.MAT4);
        }
        attribute.mat4.set(value);
    }

    public void bindAttribute(int idx, ERenderPass pass) {
        Attribute attribute = this.attributes.get(idx);
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
