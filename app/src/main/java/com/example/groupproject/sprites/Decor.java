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

    Paint paint = new Paint(); // paint to share
    Random random = new Random(); // random number generator to share

    /**
     * Constructor
     */
    public Decor() {
        super();
    }

    /**
     * Draws the decor to the screen.
     */
    @Override
    abstract public void onDraw(Canvas canvas);

    /**
     * Checks if the Ball has collided with a Decor.
     */
    @Override
    boolean collisionCheck() {
        return false;
    }
    /**
     * Handles the collision event with the ball.
     */
    abstract void onCollision();
    /**
     * Generates a random x position.
     * @param width The width of the device
     */
    int generateX(int width){
        return 0;
    }
    /**
     * Generates a random y position.
     * @param height The height of the device
     */
    int generateY(int height){
        return 0;
    }

}
