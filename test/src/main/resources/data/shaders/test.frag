#version 330
layout (location = 0) out vec4 mb_FragColor;

uniform vec4 mb_Diffuse;


void main ()
{
    mb_FragColor = mb_Diffuse;
}
