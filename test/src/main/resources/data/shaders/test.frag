#version 330
layout (location = 0) out vec4 cs_FragColor;

uniform vec4 ec_Diffuse;

in vec4 vs_Color;


void main ()
{
    cs_FragColor = vs_Color;
}
