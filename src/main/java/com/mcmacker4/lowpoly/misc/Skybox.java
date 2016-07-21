package com.mcmacker4.lowpoly.misc;

import com.mcmacker4.lowpoly.shader.SkyboxShader;
import com.mcmacker4.lowpoly.textures.Texture;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by McMacker4 on 20/07/2016.
 */
public class Skybox {

    private int textureID;
    private int vao;

    private SkyboxShader shader;

    public Skybox(Matrix4f projectionMatrix) {
        //VAO
        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = MemoryUtil.memAllocFloat(cubeCoords.length);
        buffer.put(cubeCoords);
        buffer.flip();
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        //Texture
        textureID = Texture.loadSkybox("res/textures/skybox");
        //Shader
        try {
            shader = new SkyboxShader();
        } catch (IOException e) {
            e.printStackTrace();
        }

        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();

    }

    public void render(Matrix4f view) {
        glDepthMask(false);
        glDisable(GL_CULL_FACE);
        shader.start();
        shader.loadViewMatrix(view);
        shader.setSamplerValue(0);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_CUBE_MAP, textureID);
        glBindVertexArray(vao);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_CUBE_MAP, 0);
        shader.stop();
        glEnable(GL_CULL_FACE);
        glDepthMask(true);
    }

    public int getTextureID() {
        return textureID;
    }

    private static final float[] cubeCoords = new float[] {
            -100.0f,-100.0f,-100.0f, // triangle 1 : begin
            -100.0f,-100.0f, 100.0f,
            -100.0f, 100.0f, 100.0f, // triangle 1 : end
            100.0f, 100.0f,-100.0f, // triangle 2 : begin
            -100.0f,-100.0f,-100.0f,
            -100.0f, 100.0f,-100.0f, // triangle 2 : end
            100.0f,-100.0f, 100.0f,
            -100.0f,-100.0f,-100.0f,
            100.0f,-100.0f,-100.0f,
            100.0f, 100.0f,-100.0f,
            100.0f,-100.0f,-100.0f,
            -100.0f,-100.0f,-100.0f,
            -100.0f,-100.0f,-100.0f,
            -100.0f, 100.0f, 100.0f,
            -100.0f, 100.0f,-100.0f,
            100.0f,-100.0f, 100.0f,
            -100.0f,-100.0f, 100.0f,
            -100.0f,-100.0f,-100.0f,
            -100.0f, 100.0f, 100.0f,
            -100.0f,-100.0f, 100.0f,
            100.0f,-100.0f, 100.0f,
            100.0f, 100.0f, 100.0f,
            100.0f,-100.0f,-100.0f,
            100.0f, 100.0f,-100.0f,
            100.0f,-100.0f,-100.0f,
            100.0f, 100.0f, 100.0f,
            100.0f,-100.0f, 100.0f,
            100.0f, 100.0f, 100.0f,
            100.0f, 100.0f,-100.0f,
            -100.0f, 100.0f,-100.0f,
            100.0f, 100.0f, 100.0f,
            -100.0f, 100.0f,-100.0f,
            -100.0f, 100.0f, 100.0f,
            100.0f, 100.0f, 100.0f,
            -100.0f, 100.0f, 100.0f,
            100.0f,-100.0f, 100.0f
    };

    private static int vertexCount = cubeCoords.length / 3;

}
