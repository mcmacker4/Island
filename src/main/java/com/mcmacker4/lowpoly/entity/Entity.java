package com.mcmacker4.lowpoly.entity;

import com.mcmacker4.lowpoly.model.Model;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class Entity {

    private Model model;

    private Vector3f position;
    private Vector3f rotation = new Vector3f();
    private float scale = 1;

    public Entity(Model model, Vector3f position) {
        this.model = model;
        this.position = position;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Model getModel() {
        return model;
    }

    public float getScale() {
        return scale;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Matrix4f getModelMatrix() {
        return new Matrix4f().translate(position)
                .rotateX(rotation.x)
                .rotateY(rotation.y)
                .rotateZ(rotation.z)
                .scale(scale);
    }

}
