package com.mcmacker4.lowpoly.material;

import org.joml.Vector3f;

/**
 * Created by McMacker4 on 23/05/2016.
 */
public class Material {

    private String name;
    private Vector3f ambient;
    private Vector3f diffuse;
    private Vector3f specular;
    private float shineDamper;
    private float shininess;
    private int diffuseTextureID;

    public Material() {
        this.name = "unknown";
        this.diffuseTextureID = 0;
        this.ambient = new Vector3f();
        this.diffuse = new Vector3f();
        this.specular = new Vector3f();
        this.shineDamper = 1;
        this.shininess = 0;
    }

    public Material(String name, int textureID, Vector3f ambient, Vector3f diffuse, Vector3f specular, float shineDamper, float shininess) {
        this.name = name;
        this.diffuseTextureID = textureID;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shineDamper = shineDamper;
        this.shininess = shininess;
    }

    public String getName() {
        return name;
    }

    public int getDiffuseTextureID() {
        return diffuseTextureID;
    }

    public Vector3f getAmbient() {
        return ambient;
    }

    public Vector3f getDiffuse() {
        return diffuse;
    }

    public float getShininess() {
        return shininess;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public Vector3f getSpecular() {
        return specular;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmbient(Vector3f ambient) {
        this.ambient = ambient;
    }

    public void setDiffuse(Vector3f diffuse) {
        this.diffuse = diffuse;
    }

    public void setSpecular(Vector3f specular) {
        this.specular = specular;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public void setShininess(float shininess) {
        this.shininess = shininess;
    }

    public void setDiffuseTextureID(int diffuseTextureID) {
        this.diffuseTextureID = diffuseTextureID;
    }

}
