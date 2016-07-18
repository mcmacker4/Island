package com.mcmacker4.lowpoly.util;

import com.mcmacker4.lowpoly.material.Material;
import org.joml.Vector3f;
import org.json.JSONObject;

/**
 * Created by McMacker4 on 23/05/2016.
 */
public class JSONUtil {

    public static Vector3f toVector3f(JSONObject object) {
        return new Vector3f(
                object.getBigDecimal("x").floatValue(),
                object.getBigDecimal("y").floatValue(),
                object.getBigDecimal("z").floatValue()
        );
    }

    public static Material toMaterial(JSONObject object) {
        return null;
    }

}
