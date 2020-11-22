package com.mpJavaGame.physics;

public class Physics {

    public static float elasticCollision(float mass, float vel, float mass2, float vel2) {
        float sumMass = mass + mass2;
        float newVel = ((mass - mass2) / sumMass) * vel;
        newVel += ((2 * mass2) / sumMass) * vel2;

        return newVel;
    }

}
