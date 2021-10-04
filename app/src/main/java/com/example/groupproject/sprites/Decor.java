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
    public void onDraw(Canvas canvas) {

    }
    /**
     * Checks if the Ball has collided with a Decor.
     */
    @Override
    boolean collisionCheck() {
        return false;
    }
    /**
     * Decor is randomly positioned at the start of a round, so
     * this method checks for overlapping before drawing the object
     * to the game.
     */
    void checkDrawOverlap(){

    }
    /**
     * Handles the collision event with the ball.
     */
    void onCollision(){

    }
    /**
     * Generates a random x position.
     */
    int generateX(){
        return 0;
    }
    /**
     * Generates a random y position.
     */
    int generateY(){
        return 0;
    }
}
