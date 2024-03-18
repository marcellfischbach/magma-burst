#version 330

layout(location = 0) in vec4 mb_Vertices;


void main ()
{
    gl_Position = mb_Vertices;
}