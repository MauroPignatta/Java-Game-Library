package com.mpJavaGame.physics;

import com.mpJavaGame.math.Vector2F;

public class Gravity {

    private static final Vector2F GRAVITY = new Vector2F(0f, 0f);

    public static Vector2F getGravity() {
        return GRAVITY;
    }

    public static void setGravity(float gravity) {
        GRAVITY.setY(gravity);
    }

    public static void setGravity(float gx, float gy) {
        GRAVITY.set(gx, gy);
    }

    public static void setLowGravity() {
        setGravity(0, 500f);
    }

    public static void setMediumGravity() {
        setGravity(0, 1000f);
    }

    public static void setHighGravity() {
        setGravity(0, 2000f);
    }
}
