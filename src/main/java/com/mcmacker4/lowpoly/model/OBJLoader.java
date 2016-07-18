package com.mcmacker4.lowpoly.model;

import com.mcmacker4.lowpoly.material.Material;
import com.mcmacker4.lowpoly.textures.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by McMacker4 on 22/05/2016.
 */
public class OBJLoader {

    private static final Logger logger = Logger.getLogger(OBJLoader.class.getName());

    private static ArrayList<Vector3f> vertices = new ArrayList<>();
    private static ArrayList<Vector3f> normals = new ArrayList<>();
    private static ArrayList<Vector2f> texCoords = new ArrayList<>();
    private static ArrayList<Vector3f> ordVertices = new ArrayList<>();
    private static ArrayList<Vector3f> ordNormals = new ArrayList<>();
    private static ArrayList<Vector2f> ordTexCoords = new ArrayList<>();

    private static String currentModelName;
    private static String modelMaterialName;

    private static String mtlName;
    private static Vector3f mtlAmbient;
    private static Vector3f mtlDiffuse;
    private static Vector3f mtlSpecular;
    private static int mtlDiffuseTextureID;

    private static Map<String, Model> models = new HashMap<>();
    private static Map<String, Material> materials  = new HashMap<>();

    private static int lastV = 0;
    private static int lastVt = 0;
    private static int lastVn = 0;

    private static String currentRootPath;

    public static void loadObjModel(String rootPath, String name) {
        currentRootPath = rootPath;
        resetModel();
        try {
            Files.lines(new File(rootPath + "/" + name + ".mtl").toPath()).forEach(OBJLoader::parseMtlLine);
            if(mtlAmbient != null) {
                materials.put(mtlName, new Material(mtlName, mtlDiffuseTextureID, mtlAmbient, mtlDiffuse, mtlSpecular, 1, 0));
            }
            Files.lines(new File(rootPath + "/" + name + ".obj").toPath()).forEach(OBJLoader::parseObjLine);
            models.put(currentModelName, ModelLoader.createModel(toArray3(lastV, ordVertices), toArray2(lastVt, ordTexCoords), toArray3(lastVn, ordNormals), materials.get(modelMaterialName)));
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private static void parseObjLine(String line) {
        if(line.startsWith("v ")) {
            String[] parts = line.split(" ");
            vertices.add(new Vector3f(
                    Float.parseFloat(parts[1]),
                    Float.parseFloat(parts[2]),
                    Float.parseFloat(parts[3])
            ));
        } else if(line.startsWith("vt ")) {
            String[] parts = line.split(" ");
            texCoords.add(new Vector2f(
                    Float.parseFloat(parts[1]),
                    1 - Float.parseFloat(parts[2])
            ));
        } else if(line.startsWith("vn ")) {
            String[] parts = line.split(" ");
            normals.add(new Vector3f(
                    Float.parseFloat(parts[1]),
                    Float.parseFloat(parts[2]),
                    Float.parseFloat(parts[3])
            ));
        } else if(line.startsWith("f ")) {
            String[] parts = line.split(" ");
            parseVertex(parts[1]);
            parseVertex(parts[2]);
            parseVertex(parts[3]);
        } else if(line.startsWith("usemtl ")) {
            modelMaterialName = line.split(" ")[1];
        } else if(line.startsWith("o ")) {
            if(vertices.size() > 0) {
                models.put(currentModelName,
                        ModelLoader.createModel(toArray3(lastV, ordVertices), toArray2(lastVt, ordTexCoords), toArray3(lastVn, ordNormals), materials.get(modelMaterialName)));
                lastV = ordVertices.size();
                lastVt = ordTexCoords.size();
                lastVn = ordNormals.size();
            }
            currentModelName = line.split(" ")[1];
        }
    }

    private static void parseMtlLine(String line) {
        if(line.startsWith("newmtl ")) {
            if(mtlAmbient != null) {
                materials.put(mtlName, new Material(mtlName, mtlDiffuseTextureID, mtlAmbient, mtlDiffuse, mtlSpecular, 1, 0));
            }
            mtlName = line.split(" ")[1];
        } else if(line.startsWith("Ka ")) {
            String[] parts = line.split(" ");
            mtlAmbient = new Vector3f(
                    Float.parseFloat(parts[1]),
                    Float.parseFloat(parts[2]),
                    Float.parseFloat(parts[3])
            );
        } else if(line.startsWith("Kd ")) {
            String[] parts = line.split(" ");
            mtlDiffuse = new Vector3f(
                    Float.parseFloat(parts[1]),
                    Float.parseFloat(parts[2]),
                    Float.parseFloat(parts[3])
            );
        } else if(line.startsWith("Ks ")) {
            String[] parts = line.split(" ");
            mtlSpecular = new Vector3f(
                    Float.parseFloat(parts[1]),
                    Float.parseFloat(parts[2]),
                    Float.parseFloat(parts[3])
            );
        } else if(line.startsWith("map_Kd ")) {
            mtlDiffuseTextureID = Texture.loadTexture(currentRootPath, line.split(" ")[1].substring(1));
            System.out.println("Texture: " + line.split(" ")[1].substring(1) + " with id " + mtlDiffuseTextureID);
        }
    }

    private static void parseVertex(String vertex) {
        String[] parts = vertex.split("/");
        ordVertices.add(vertices.get(Integer.parseInt(parts[0]) - 1));
        ordTexCoords.add(texCoords.get(Integer.parseInt(parts[1]) - 1));
        ordNormals.add(normals.get(Integer.parseInt(parts[2]) - 1));
    }

    private static float[] toArray3(int start, ArrayList<Vector3f> list) {
        float[] array = new float[list.size() * 3];
        int pointer = 0;
        for(int i = start; i < list.size(); i++) {
            array[pointer++] = list.get(i).x;
            array[pointer++] = list.get(i).y;
            array[pointer++] = list.get(i).z;
        }
        return array;
    }

    private static float[] toArray2(int start, ArrayList<Vector2f> list) {
        float[] array = new float[list.size() * 2];
        int pointer = 0;
        for(int i = start; i < list.size(); i++) {
            array[pointer++] = list.get(i).x;
            array[pointer++] = list.get(i).y;
        }
        return array;
    }

    private static void resetModel() {
        vertices.clear();
        normals.clear();
        ordVertices.clear();
        ordNormals.clear();
    }

    public static Model get(String name) {
        return models.get(name);
    }

    public static Set<String> getModelNames() {
        return models.keySet();
    }

    public static Collection<Model> getAllModels() {
        return models.values();
    }

}
