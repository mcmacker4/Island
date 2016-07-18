package com.mcmacker4.lowpoly.scene;

import com.mcmacker4.lowpoly.entity.Entity;
import com.mcmacker4.lowpoly.material.Material;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by McMacker4 on 23/05/2016.
 */
public class Scene {

    private ArrayList<Entity> entities = new ArrayList<>();

    public Scene(String file) {
        try {
            String text = fileToText(file);
            JSONObject json = new JSONObject(text);
            JSONArray entitiesJsonArray = json.getJSONArray("entities");
            entitiesJsonArray.forEach(o -> {
                JSONObject object = (JSONObject) o;
                JSONObject jsonMaterial = object.getJSONObject("material");
            });
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String fileToText(String file) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String line;
        while((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }
        return builder.toString();
    }

}
