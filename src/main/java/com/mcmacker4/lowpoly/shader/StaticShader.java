package com.mcmacker4.lowpoly.shader;

import com.mcmacker4.lowpoly.entity.Light;
import com.mcmacker4.lowpoly.material.Material;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class StaticShader extends ShaderProgram {

    private static final Logger logger = Logger.getLogger(StaticShader.class.getName());

    private static int LOCATION_PROJECTION_MATRIX;
    private static int LOCATION_MODEL_MATRIX;
    private static int LOCATION_VIEW_MATRIX;
    private static int LOCATION_LIGHT_POSITION;
    private static int LOCATION_LIGHT_COLOR;
    private static int LOCATION_MATERIAL_AMBIENT;
    private static int LOCATION_MATERIAL_DIFFUSE;
    private static int LOCATION_MATERIAL_SPECULAR;
    private static int LOCATION_MATERIAL_SHINE_DAMPER;
    private static int LOCATION_MATERIAL_SHININESS;
    private static int LOCATION_EYE_POSITION;
    private static int LOCATION_SKYBOX_SAMPLER;

    public StaticShader() throws IOException {
        super("static");
    }

    @Override
    protected void getAllUniformLocations() {
        LOCATION_PROJECTION_MATRIX = getUniformLocation("projectionMatrix");
        LOCATION_VIEW_MATRIX = getUniformLocation("viewMatrix");
        LOCATION_MODEL_MATRIX = getUniformLocation("modelMatrix");
        LOCATION_LIGHT_POSITION = getUniformLocation("light.position");
        LOCATION_LIGHT_COLOR = getUniformLocation("light.color");
        LOCATION_MATERIAL_DIFFUSE = getUniformLocation("material.diffuse");
        LOCATION_MATERIAL_AMBIENT = getUniformLocation("material.ambient");
        LOCATION_MATERIAL_SPECULAR = getUniformLocation("material.specular");
        LOCATION_MATERIAL_SHINE_DAMPER = getUniformLocation("material.shineDamper");
        LOCATION_MATERIAL_SHININESS = getUniformLocation("material.shininess");
        LOCATION_EYE_POSITION = getUniformLocation("eyePosition");
        LOCATION_SKYBOX_SAMPLER = getUniformLocation("skybox");
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        loadMatrix4f(LOCATION_PROJECTION_MATRIX, matrix);
    }

    public void loadViewMatrix(Matrix4f matrix) {
        loadMatrix4f(LOCATION_VIEW_MATRIX, matrix);
    }

    public void loadModelMatrix(Matrix4f matrix) {
        loadMatrix4f(LOCATION_MODEL_MATRIX, matrix);
    }

    public void setEyePosition(Vector3f position) {
        loadVector3f(LOCATION_EYE_POSITION, position);
    }

    public void setLight(Light light) {
        loadVector3f(LOCATION_LIGHT_POSITION, light.getPosition());
        loadVector3f(LOCATION_LIGHT_COLOR, light.getColor());
    }

    public void setMaterial(Material material) {
        loadVector3f(LOCATION_MATERIAL_AMBIENT, material.getAmbient());
        loadVector3f(LOCATION_MATERIAL_DIFFUSE, material.getDiffuse());
        loadVector3f(LOCATION_MATERIAL_SPECULAR, material.getSpecular());
        loadFloat(LOCATION_MATERIAL_SHINE_DAMPER, material.getShineDamper());
        loadFloat(LOCATION_MATERIAL_SHININESS, material.getShininess());
    }

    public void setSkyboxSamplerID(int id) {
        loadInt(LOCATION_SKYBOX_SAMPLER, id);
    }

}
