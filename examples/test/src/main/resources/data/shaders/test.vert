#version 330

layout(location = 0) in vec4 cs_Vertices;

uniform mat4 cs_ModelMatrix;
uniform mat4 cs_ViewMatrix;
uniform mat4 cs_ProjectionMatrix;



void main ()
{
    gl_Position = cs_ProjectionMatrix * cs_ViewMatrix * cs_ModelMatrix * cs_Vertices;
}