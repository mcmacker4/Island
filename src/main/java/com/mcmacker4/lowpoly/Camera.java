package com.mcmacker4.lowpoly;

import com.mcmacker4.lowpoly.input.Keyboard;
import com.mcmacker4.lowpoly.input.Mouse;
import com.mcmacker4.lowpoly.util.Timer;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class Camera {

    private Vector3f position;
    private Vector3f rotation = new Vector3f();

    private final float speed = 0.02f;
    private final float sensitivity = 0.003f;

    public Camera(Vector3f position) {
        this.position = position;
    }

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void update() {
        float delta = (float) (speed * Timer.getDelta());
        rotation.add((float) -Mouse.getDY() * sensitivity, (float) -Mouse.getDX() * sensitivity, 0);
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
            position.add(frontVector().mul(speed));
        }
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
            position.add(leftVector().mul(speed));
        }
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
            position.add(backVector().mul(speed));
        }
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
            position.add(rightVector().mul(speed));
        }
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            position.add(new Vector3f(0, -1, 0).mul(speed));
        }
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            position.add(new Vector3f(0, 1, 0).mul(speed));
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

    private Vector3f frontVector() {
        Matrix3f mat = new Matrix3f().rotateY(rotation.y);
        return new Vector3f(0, 0, -1).mul(mat).normalize();
    }

    private Vector3f leftVector() {
        Matrix3f mat = new Matrix3f().rotateY((float) (Math.PI / 2));
        return frontVector().mul(mat).normalize();
    }

    private Vector3f rightVector() {
        Matrix3f mat = new Matrix3f().rotateY((float) -(Math.PI / 2));
        return frontVector().mul(mat).normalize();
    }

    private Vector3f backVector() {
        Vector3f front = frontVector();
        return new Vector3f(-front.x, front.y, -front.z);
    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f()
                .rotateX(-rotation.x)
                .rotateY(-rotation.y)
                .rotateX(-rotation.z)
                .translate(-position.x, -position.y, -position.z);
    }

}
