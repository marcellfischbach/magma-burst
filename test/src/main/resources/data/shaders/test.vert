#version 330

layout(location = 0) in vec4 ec_Vertices;
layout(location = 4) in vec4 ec_Color;

out vec4 vs_Color;

void main ()
{
    vs_Color = ec_Color;
    gl_Position = ec_Vertices;
}