package com.mcmacker4.lowpoly.model;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class ModelData {

    private float[] vertices;
    private float[] texCoords;
    private float[] normals;

    public ModelData(float[] vertices, float[] texCoords, float[] normals) {
        this.vertices = vertices;
        this.texCoords = texCoords;
        this.normals = normals;
    }

    public float[] getNormals() {
        return normals;
    }

    public float[] getTexCoords() {
        return texCoords;
    }

    public float[] getVertices() {
        return vertices;
    }

}
