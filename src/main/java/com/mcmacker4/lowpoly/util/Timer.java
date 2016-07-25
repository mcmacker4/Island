package com.mcmacker4.lowpoly.util;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class Timer {

    private static double lastUpdate;
    private static double delta;

    private static int fpsCount;
    private static int FPS;

    private static double counter;

    public static void update() {
        double now = glfwGetTime();
        delta = now - lastUpdate;
        lastUpdate = now;
        counter += delta;
        fpsCount++;
        if(counter > 1) {
            counter = 0;
            FPS = fpsCount;
            fpsCount = 0;
        }
    }

    public static double getDelta() {
        return delta;
    }

    public static int getFPS() {
        return FPS;
    }

}
