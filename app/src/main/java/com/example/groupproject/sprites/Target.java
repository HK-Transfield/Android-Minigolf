package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * This class is used to display a Target in the game for the user to
 * aim the Ball at. Collision with the target is defined by ___% of the
 * Ball overlapping with the Target. When a collision with the Target
 * is achieved, the round ends.
 */
public class Target extends Decor {

    /* CONSTANT CLASS MEMBER VARIABLES */
    private final int targetSize = 60; // needs to scale to screen size?
    private final int targetColor = Color.RED; // colour
    private final double maxHeight = 0.2; // lowest % of screen to spawn in (20%)

    /* CLASS MEMBER VARIABLES */
    private float targetStartX = -200; // initial position (off screen)
    private float targetStartY = -200; // initial position (off screen)
    private float targetTrueX; // relative x position after screen load
    private float targetTrueY; // relative y position after screen load

    //region CONSTRUCTOR
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Constructor, instantiates a new Target object
     */
    public Target() {
        super();
        this.size = targetSize;
        this.startX = targetStartX;
        this.startY = targetStartY;
    }
    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region SETTERS
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Collect the screen width and height after the layout has loaded.
     * Use the width and height to generate relative positioning on the
     * screen that scales to the current device
     *
     * @param width how wide the playable game screen is.
     * @param height how long the playable game screen is.
     */
    @Override
    public void setPosition(int width, int height) {
        targetTrueX = targetStartX = generateX(width); // generate random x
        targetTrueY = targetStartY= generateY(height); // generate random y
    }
    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region GETTERS
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Get the X position of Target
     */
    public float getTargetTrueX() {
        return targetTrueX;
    }

    /**
     * Get the Y position of Target
     */
    public float getTargetTrueY() {
        return targetTrueY;
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region METHODS
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Generate a random X position within the playable area of the screen width.
     *
     * @param width how wide the playable game screen is.
     */
    @Override
    int generateX(int width) {
        int min = targetSize; // most left it should be
        int max = width - targetSize; // most right it should be
        return random.nextInt(max-min) + min;
    }

    /**
     * Generate a random Y position at the top of the screen.
     * Within TargetSize - 20% of the screen height.
     *
     * @param height how long the playable game screen is.
     */
    @Override
    int generateY(int height) {
        int min = targetSize; // highest point it should be drawn
        int max = (int) (height * maxHeight); // lowest point it should be drawn
        return random.nextInt(max-min) + min;
    }

    /**
     * Draw a Target (circle) with a specified colour at a randomly,
     * generated x and y.
     *
     * @param canvas the object to draw the Target object on.
     */
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(targetColor); // set the colour
        canvas.drawCircle(targetStartX, targetStartY, targetSize, paint); // draw the target
    }

    /**
     * TODO
     */
    @Override
    void onCollision() {
        super.onCollision();
        // TODO
    }
    /*--------------------------------------------------------------------------------------------*/
    //endregion
}
