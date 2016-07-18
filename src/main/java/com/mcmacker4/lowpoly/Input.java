package com.mcmacker4.lowpoly;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class Input extends GLFWKeyCallback {

    private static HashMap<Integer, java.lang.Boolean> keys = new HashMap<>();

    public static Input INSTANCE;

    public Input() {
        INSTANCE = this;
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if(action == GLFW_RELEASE) {
            if(key == GLFW_KEY_ESCAPE) glfwSetWindowShouldClose(window, true);
            keys.put(key, false);
        } else {
            keys.put(key, true);
        }
    }

    public static boolean isKeyDown(int key) {
        return keys.get(key) == null ? false : keys.get(key);
    }

}
