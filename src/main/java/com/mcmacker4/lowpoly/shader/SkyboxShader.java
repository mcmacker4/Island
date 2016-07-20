package com.mcmacker4.lowpoly.shader;

import org.joml.Matrix4f;

import java.io.IOException;

/**
 * Created by McMacker4 on 20/07/2016.
 */
public class SkyboxShader extends ShaderProgram {

    private static int LOCATION_PROJECTION_MATRIX;
    private static int LOCATION_VIEW_MATRIX;
    private static int LOCATION_SAMPLER;

    public SkyboxShader() throws IOException {
        super("skybox");
    }

    @Override
    protected void getAllUniformLocations() {
        LOCATION_PROJECTION_MATRIX = getUniformLocation("projectionMatrix");
        LOCATION_VIEW_MATRIX = getUniformLocation("viewMatrix");
        LOCATION_SAMPLER = getUniformLocation("skybox");
        System.out.println("Proj: " + LOCATION_PROJECTION_MATRIX + " View: " + LOCATION_VIEW_MATRIX + " Sampler: " + LOCATION_SAMPLER);
    }

    public void loadProjectionMatrix(Matrix4f projection) {
        loadMatrix4f(LOCATION_PROJECTION_MATRIX, projection);
    }

    public void loadViewMatrix(Matrix4f view) {
        loadMatrix4f(LOCATION_VIEW_MATRIX, view);
    }

    public void setSamplerValue(int value) {
        loadInt(LOCATION_SAMPLER, value);
    }

}
