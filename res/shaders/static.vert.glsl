#version 330 core

layout (location = 0) in vec3 vertex;
layout (location = 1) in vec3 normal;
layout (location = 2) in vec2 texCoord;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

out vec3 _fragPos;
out vec3 _normal;
out vec2 _texCoord;

void main(void) {

    vec3 worldPos = vec3(vec4(vertex, 1.0) * modelMatrix);
    gl_Position = projectionMatrix * viewMatrix * vec4(worldPos, 1.0);
    _normal = mat3(transpose(inverse(modelMatrix))) * normal;
    _fragPos = worldPos;
    _texCoord = texCoord;

}