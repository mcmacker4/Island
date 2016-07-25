package com.mcmacker4.lowpoly.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public abstract class ShaderProgram {

    int programID;

    public ShaderProgram(String name) throws IOException {
        String vShaderSource = readFile("res/shaders/" + name + ".vert.glsl");
        String fShaderSource = readFile("res/shaders/" + name + ".frag.glsl");
        int vShader = createShader(GL_VERTEX_SHADER, vShaderSource);
        int fShader = createShader(GL_FRAGMENT_SHADER, fShaderSource);
        programID = glCreateProgram();
        glAttachShader(programID, vShader);
        glAttachShader(programID, fShader);
        glLinkProgram(programID);
        if(glGetProgrami(programID, GL_LINK_STATUS) != GL_TRUE)
            System.err.println(glGetProgramInfoLog(programID));
        glDeleteShader(vShader);
        glDeleteShader(fShader);
        getAllUniformLocations();
        glValidateProgram(programID);
        if(glGetProgrami(programID, GL_VALIDATE_STATUS) != GL_TRUE) {
            System.err.println(glGetProgramInfoLog(programID));
        }
    }

    protected abstract void getAllUniformLocations();

    public void start() {
        glUseProgram(programID);
    }

    public void stop() {
        glUseProgram(0);
    }

    protected int getUniformLocation(String name) {
        return glGetUniformLocation(programID, name);
    }

    protected void loadFloat(int location, float value) {
        glUniform1f(location, value);
    }

    protected void loadInt(int location, int value) { glUniform1i(location, value); }

    protected void loadBoolean(int location, boolean value) {
        glUniform1f(location, value ? 1.0f : 0.0f);
    }

    protected void loadVector3f(int location, Vector3f vector) {
        glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void loadMatrix4f(int location, Matrix4f matrix) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(4*4);
        matrix.get(buffer);
        glUniformMatrix4fv(location, false, buffer);
    }

    private int createShader(int type, String source) {
        int shader = glCreateShader(type);
        glShaderSource(shader, source);
        glCompileShader(shader);
        if(glGetShaderi(shader, GL_COMPILE_STATUS) != GL_TRUE)
            System.err.println(glGetShaderInfoLog(shader));
        return shader;
    }

    private String readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        StringBuilder source = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null)
            source.append(line).append("\n");
        return source.toString();
    }

}
