package com.mcmacker4.lowpoly;

import com.mcmacker4.lowpoly.entity.Entity;
import com.mcmacker4.lowpoly.entity.Light;
import com.mcmacker4.lowpoly.shader.StaticShader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class MasterRenderer {

    private StaticShader shader;

    public MasterRenderer(StaticShader shader) {
        this.shader =  shader;
    }

    public void drawEntity(Entity e, Camera camera, Light light, int skyboxTexID) {
        shader.start();
        shader.loadModelMatrix(e.getModelMatrix());
        shader.loadViewMatrix(camera.getViewMatrix());
        shader.setLight(light);
        shader.setEyePosition(camera.getPosition());
        shader.setMaterial(e.getModel().getMaterial());
        shader.setSkyboxSamplerID(1);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, e.getModel().getMaterial().getDiffuseTextureID());
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_CUBE_MAP, skyboxTexID);
        glBindVertexArray(e.getModel().getVao());
        glDrawArrays(GL_TRIANGLES, 0, e.getModel().getVertexCount());
        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_2D, 0);
        shader.stop();
    }

}
