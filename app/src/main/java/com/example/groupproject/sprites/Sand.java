package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

/**
 * This class is used to display a Sand obstacle in the game.
 * Collision with the Sand is defined by 50% of the Ball
 * overlapping with the Sand. When a collision with the
 * Sand is detected, the ball will slow.
 */
public class Sand extends Decor {

    /* CONSTANT CLASS MEMBER VARIABLES */
    private final int SAND_SIZE = 120; // needs to scale to screen size?

    /* CLASS MEMBER VARIABLES */
    private Water waterCurrent; // the current instance of water
    private Target targetCurrent; // the current instance of target
    private boolean hasBallHit = false; // checks if the ball has hit it in that round

    //region CONSTRUCTOR
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Constructor, instantiates a new Sand object
     */
    public Sand() {
        super();
        this.size = SAND_SIZE;
        this.x = this.initX; // randomly generate this
        this.y = this.initY; // randomly generate this
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region GETTERS
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Get the X position of Sand
     */
    protected float getTrueX() { return this.trueX; }

    /**
     * Get the Y position of Sand
     */
    protected float getTrueY() {
        return this.trueY;
    }

    /**
     * Get the current state of whether the ball has hit the Sand
     */
    public boolean getHasBallHit() { return hasBallHit; }

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
        hasBallHit = false;

        // generate the initial position
        this.trueX = this.initX = generateX(width); // generate random x
        this.trueY = this.initY = generateY(height); // generate random y

        // check for overlapping with target
        boolean overlapCheck = checkDrawOverlap(targetCurrent) || checkDrawOverlap(waterCurrent);

        if (overlapCheck)
            this.setPosition(width, height);
    }

    /**
     * Get the current instance of water.
     */
    public void setWaterCurrent(Water water) { waterCurrent = water; }

    /**
     * Get the current instance of target.
     */
    public void setTargetCurrent(Target target) { targetCurrent = target; }

    /**
     * The ball has hit the sand
     */
    public void setHasBallHit() { hasBallHit = true; }

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
    protected int generateX(int width) {
        int min = SAND_SIZE; // most left it should be
        int max = width - SAND_SIZE; // most right it should be

        return random.nextInt(max-min) + min;
    }

    /**
     * Generate a random Y position within 20% - 70% of the screen height
     *
     * @param height how long the playable game screen is.
     */
    @Override
    protected int generateY(int height) {
        int min = (int) (height * minHeight) + SAND_SIZE; // highest point it should be drawn
        int max = (int) (height * maxHeight) + SAND_SIZE; // lowest point it should be drawn

        return random.nextInt(max-min) + min;
    }

    /**
     * Draw a Sand circle with a specified colour at a randomly,
     * generated x and y.
     *
     * @param canvas the object to draw the Sand object on.
     */
    @Override
    public void drawSprite(Canvas canvas) {

        // the colour
        int sandColor = Color.YELLOW;
        paint.setColor(sandColor); // set the colour
        canvas.drawCircle(this.initX, this.initY, SAND_SIZE, paint); // draw the target
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion
}