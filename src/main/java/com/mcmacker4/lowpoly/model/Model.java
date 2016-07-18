package com.mcmacker4.lowpoly.model;

import com.mcmacker4.lowpoly.material.Material;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class Model {

    private int vao;
    private int vertexCount;
    private Material material;

    public Model(int vao, int vertexCount, Material material) {
        this.vao = vao;
        this.vertexCount = vertexCount;
        this.material = material;
    }

    public int getVao() {
        return vao;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public Material getMaterial() {
        return material;
    }

}
