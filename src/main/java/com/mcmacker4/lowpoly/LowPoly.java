package com.mcmacker4.lowpoly;

import com.mcmacker4.lowpoly.entity.Entity;
import com.mcmacker4.lowpoly.entity.Light;
import com.mcmacker4.lowpoly.misc.Skybox;
import com.mcmacker4.lowpoly.model.Model;
import com.mcmacker4.lowpoly.model.OBJLoader;
import com.mcmacker4.lowpoly.shader.StaticShader;
import com.mcmacker4.lowpoly.util.Timer;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class LowPoly {

    private static final Logger logger = Logger.getLogger(LowPoly.class.getName());

    MasterRenderer masterRenderer;

    Collection<Model> models;
    Collection<Entity> entities;
    StaticShader shader;
    Matrix4f projectionMatrix;
    Camera camera;
    Light light;
    Skybox skybox;

    private void start() {
        Display.create();
        init();
        while(!Display.shouldClose()) {
            Display.pollEvents();
            Timer.update();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            update();
            render();
            Display.swapBuffers();
        }
        Display.destroy();
    }

    private void init() {
        camera = new Camera(new Vector3f(0, 15, 0), new Vector3f(0f, 0f, 0f));
        light = new Light(new Vector3f(20, 60, 20), new Vector3f(0.6f, 0.6f, 0.6f));
        OBJLoader.loadObjModel("res/models/island", "Small_Tropical_Island");
        models = OBJLoader.getAllModels();
        entities = new ArrayList<>();
        for(Model m : models) {
            Entity entity = new Entity(m, new Vector3f());
            entity.setScale(0.1f);
            entities.add(entity);
        }
        projectionMatrix = new Matrix4f().setPerspective((float) Math.toRadians(80f), (float) Display.WIDTH / Display.HEIGHT, 0.1f, 300f);
        skybox = new Skybox(projectionMatrix);
        try {
            shader = new StaticShader();
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        masterRenderer = new MasterRenderer(shader);
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    private void update() {
        camera.update();
        //light.getPosition().set(0, 10, 0);
    }

    private void render() {
        skybox.render(new Matrix4f(new Matrix3f(camera.getViewMatrix())));
        for(Entity e : entities) {
            masterRenderer.drawEntity(e, camera, light, skybox.getTextureID());
        }
    }

    public static void main(String[] args) {
        new LowPoly().start();
    }

}
