package com.mcmacker4.lowpoly;

import com.mcmacker4.lowpoly.input.Keyboard;
import com.mcmacker4.lowpoly.input.Mouse;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class Display {

    private static long window;

    public static final int
            WIDTH = 1280,
            HEIGHT = 720;

    public static void create() {

        glfwInit();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

        window = glfwCreateWindow(WIDTH, HEIGHT, "LOW POLY", NULL, NULL);
        if(window == GL_FALSE)
            throw new IllegalStateException("Could not create GLFW window");

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2);

        glfwSetKeyCallback(window, new Keyboard());
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        glfwSwapInterval(0);

        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        //glEnable(GL_CULL_FACE);
        glClearColor(0.05f, 0.05f, 0.05f, 1.0f);

    }

    public static boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public static void swapBuffers() {
        glfwSwapBuffers(window);
    }

    private static DoubleBuffer mxpos, mypos;
    static {
        mxpos = MemoryUtil.memAllocDouble(1);
        mypos = MemoryUtil.memAllocDouble(1);
    }

    public static void pollEvents() {
        glfwPollEvents();
        mxpos.clear(); mypos.clear();
        glfwGetCursorPos(window, mxpos, mypos);
        Mouse.update(mxpos.get(), mypos.get());
    }

    public static void destroy() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

}
