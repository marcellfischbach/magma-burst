package com.eruption.engine.core.graphics.shading;

public interface IShader {

    int registerAttribute (String attributeName);

    int indexOf(String attributeName);

    int indexOf(EShaderAttribute attribute);

    IShaderAttribute getAttribute (int idx);

    IShaderAttribute getAttribute (String attributeName);

    IShaderAttribute getAttribute (EShaderAttribute attribute);
}
