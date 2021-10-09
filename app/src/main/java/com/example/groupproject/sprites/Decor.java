package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

/**
 * This class is used to define objects in the game that produce
 * different effects when the Ball collides with them. Unlike the Ball
 * object, Decor objects randomize their position at the start of a round.
 */
abstract public class Decor extends Sprite {
    protected Random random = new Random(); // random number generator to share
    protected static double minHeight = 0.2; // uppermost % of screen to spawn in (20%)
    protected static double maxHeight = 0.7; // lowest % of screen to spawn in (70%)
    protected float initialPosition = -200; // set initial x-axis offscreen
    protected float trueX;
    protected float trueY;

    /**
     * Constructor
     */
    public Decor() {
        super();
        this.x = initialPosition;
        this.y = initialPosition;
    }

    /**
     * Checks if the Ball has collided with a Decor.
     */
    public boolean collisionCheck(float bX, float bY) {
        return  bX >= trueX - size && bX <= trueX + size &&
                bY >= trueY - size && bY <= trueY + size;
    }

    public boolean checkDrawOverlap(Decor d) {
        float diffX = d.getTrueX() - trueX;
        float diffY = d.getTrueY() - trueY;

        float distanceSquared = (diffX * diffX) + (diffY * diffY);
        return  distanceSquared < (size + size) * (this.size + this.size);
    }

    protected float getTrueX() { return this.trueX; }

    protected float getTrueY() { return this.trueY; }

    /**
     * Generates a random x position.
     * @param width The width of the device
     */
    protected abstract int generateX(int width);

    /**
     * Generates a random y position.
     * @param height The height of the device
     */
    protected abstract int generateY(int height);

}
