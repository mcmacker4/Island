#version 330 core

in vec3 _normal;
in vec3 _fragPos;
in vec2 _texCoord;

out vec4 pixel_color;

struct Material {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    float shininess;
    float shineDamper;
};

struct Light {
    vec3 position;
    vec3 color;
};

uniform sampler2D sampler;
uniform samplerCube skybox;
uniform Material material;
uniform Light light;

uniform vec3 eyePosition;

void main(void) {

    //Diffuse
    vec3 unitNormal = normalize(_normal);
    vec3 lightDir = normalize(light.position - _fragPos);


    float diffuseBrightness = max(dot(unitNormal, lightDir), 0.0);
    vec3 diffuse = light.color * (diffuseBrightness * material.diffuse);

    //Ambient
    vec3 ambient = light.color * material.ambient;

    //Specular
    vec3 viewDir = normalize(eyePosition - _fragPos);
    vec3 lightReflectDir = reflect(-lightDir, unitNormal);
    float spec = pow(max(dot(viewDir, lightReflectDir), 0.0), material.shineDamper);
    vec3 specular = material.shininess * (spec * material.specular);

    //Reflection
    vec3 camDir = normalize(_fragPos - eyePosition);
    vec3 cameraReflectDir = reflect(camDir, unitNormal);

    //Result
    vec3 result = (ambient + diffuse + specular);
    pixel_color = vec4(result, 1.0) * texture(sampler, _texCoord) * texture(skybox, cameraReflectDir);

}