package com.mcmacker4.lowpoly.entity;

import org.joml.Vector3f;

/**
 * Created by McMacker4 on 23/05/2016.
 */
public class Light {

    private Vector3f position;
    private Vector3f color;

    public Light(Vector3f position, Vector3f color) {
        this.position = position;
        this.color = color;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getColor() {
        return color;
    }

}
