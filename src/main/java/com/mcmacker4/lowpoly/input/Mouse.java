package com.mcmacker4.lowpoly.input;

import org.joml.Vector2d;

/**
 * Created by McMacker4 on 19/07/2016.
 */
public class Mouse {

    private static Vector2d position = new Vector2d();
    private static Vector2d delta = new Vector2d();

    public static void update(double mx, double my) {
        delta.set(mx - position.x, my - position.y);
        position.set(mx, my);
    }

    public static double getX() {
        return position.x;
    }

    public static double getY() {
        return position.y;
    }

    public static double getDX() {
        return delta.x;
    }

    public static double getDY() {
        return delta.y;
    }

}
