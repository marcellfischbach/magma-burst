package com.cobaltsky.engine.graphics.opengl.gl4.shading;

import com.cobaltsky.engine.core.graphics.shading.EShaderAttribute;
import com.cobaltsky.engine.core.graphics.shading.IShader;
import com.cobaltsky.engine.core.graphics.shading.IShaderAttribute;
import com.cobaltsky.engine.graphics.opengl.gl4.NamedGL4;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL40.*;

public class ProgramGL4 extends NamedGL4 implements IShader {

    private final List<ShaderAttributeGL4> attributes = new ArrayList<>();

    public ProgramGL4() {
        this.name = glCreateProgram();
    }

    public void attach(ShaderGL4 shader) {
        glAttachShader(this.name, shader.getName());
    }

    public void detach(ShaderGL4 shader) {
        glDetachShader(this.name, shader.getName());
    }

    public void link() throws ShaderLinkException {
        glLinkProgram(this.name);
        int linkStatus = glGetProgrami(this.name, GL_LINK_STATUS);
        if (linkStatus != GL_TRUE) {
            String log = glGetProgramInfoLog(this.name);
            throw new ShaderLinkException(log);
        }

        this.attributes.clear();
        bindStandardAttributes();
    }

    private void bindStandardAttributes() {
        glUseProgram(this.name);
        for (EShaderAttribute attributeName : EShaderAttribute.values()) {
            String locationName = locationName(attributeName);
            String shaderLocationName = locationName(locationName);
            int loc = glGetUniformLocation(this.name, shaderLocationName);
            ShaderAttributeGL4 attribute = null;
            if (loc != -1) {
                attribute = new ShaderAttributeGL4(loc, locationName);
            }
            this.attributes.add(attribute);
        }
    }


    private String locationName(EShaderAttribute attribute) {
        StringBuilder location = new StringBuilder();
        String attributeName = attribute.name();
        boolean upper = true;
        for (int i = 0, in = attributeName.length(); i < in; i++) {
            char ch = attributeName.charAt(i);
            if (ch == '_') {
                upper = true;
                continue;
            }

            ch = upper ? Character.toUpperCase(ch)
                    : Character.toLowerCase(ch);
            location.append(ch);
            upper = false;
        }

        return location.toString();
    }

    private String locationName(String locationName) {
        if (locationName == null || locationName.isEmpty()) {
            return "";
        }

        String locName = Character.toUpperCase(locationName.charAt(0)) + locationName.substring(1);
        return "cs_" + locName;
    }

    @Override
    public int registerAttribute(String attributeName) {
        int idx = indexOf(attributeName);
        if (idx != -1) {
            return idx;
        }

        String locationName = locationName(attributeName);
        int loc = glGetUniformLocation(this.name, locationName);
        ShaderAttributeGL4 attribute = null;
        if (loc != -1) {
            attribute = new ShaderAttributeGL4(loc, attributeName);
        }
        this.attributes.add(attribute);

        return this.attributes.size() - 1;
    }

    @Override
    public int indexOf(String attributeName) {
        for (int i = 0, in = this.attributes.size(); i < in; i++) {
            ShaderAttributeGL4 attributeGL4 = this.attributes.get(i);
            if (attributeGL4 != null && attributeGL4.getName().equals(attributeName)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOf(EShaderAttribute attribute) {
        return attribute.ordinal();
    }

    @Override
    public IShaderAttribute getAttribute(int idx) {
        return idx >= 0 && idx < this.attributes.size()
                ? this.attributes.get(idx)
                : null;
    }

    @Override
    public IShaderAttribute getAttribute(String attributeName) {
        return getAttribute(indexOf(attributeName));
    }

    @Override
    public IShaderAttribute getAttribute(EShaderAttribute attribute) {
        return this.attributes.get(attribute.ordinal());
    }
}
