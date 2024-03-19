#version 330

layout(location = 0) in vec4 mb_Vertices;

uniform mat4 mb_ModelMatrix;
uniform mat4 mb_ViewMatrix;
uniform mat4 mb_ProjectionMatrix;


void main ()
{
    gl_Position = mb_ProjectionMatrix * mb_ViewMatrix * mb_ModelMatrix * mb_Vertices;
}