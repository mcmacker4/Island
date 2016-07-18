package com.mcmacker4.lowpoly;

import com.mcmacker4.lowpoly.util.Timer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class Camera {

    private Vector3f position;
    private Vector3f rotation = new Vector3f();

    private final float speed = 5f;

    public Camera(Vector3f position) {
        this.position = position;
    }

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void update() {
        float delta = (float) (speed * Timer.getDelta());
        if(Input.isKeyDown(GLFW.GLFW_KEY_W)) {
            position.add(0, 0, -delta);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_S)) {
            position.add(0, 0, delta);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_A)) {
            position.add(-delta, 0, 0);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_D)) {
            position.add(delta, 0, 0);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            position.add(0, delta, 0);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            position.add(0, -delta, 0);
        }
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f()
                .rotateX(-rotation.x)
                .rotateY(-rotation.y)
                .rotateX(-rotation.z)
                .translate(-position.x, -position.y, -position.z);
    }

}
