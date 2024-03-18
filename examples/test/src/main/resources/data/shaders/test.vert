#version 330

layout(location = 0) in vec4 cs_Vertices;

uniform mat4 ec_ModelMatrix;
uniform mat4 ec_ViewMatrix;
uniform mat4 ec_ProjectionMatrix;



void main ()
{
    gl_Position = ec_ProjectionMatrix * ec_ViewMatrix * ec_ModelMatrix * ec_Vertices;
}