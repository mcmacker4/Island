package com.mcmacker4.lowpoly.model;

import com.mcmacker4.lowpoly.material.Material;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class ModelLoader {

    public static void createModel(String rootPath, String name) {
        OBJLoader.loadObjModel(rootPath, name);
    }

    public static Model createModel(float[] vertices, float[] texCoords, float[] normals, Material material) {
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        storeDataInAttribute(0, 3, vertices);
        storeDataInAttribute(1, 3, normals);
        storeDataInAttribute(2, 2, texCoords);
        glBindVertexArray(0);
        return new Model(vao, vertices.length / 3, material);
    }

    private static void storeDataInAttribute(int index, int size, float[] data) {
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, toFloatBuffer(data), GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(index);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    private static FloatBuffer toFloatBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

}
