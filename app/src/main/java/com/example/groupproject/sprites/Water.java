package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * This class is used to display a Water obstacle in the game.
 * Collision with the target is defined by 50% of the Ball
 * overlapping with the Water. When a collision with the
 * Water is detected, the player will fail the round and the
 * game over screen will appear.
 */
public class Water extends Decor {

    /* CONSTANT CLASS MEMBER VARIABLES */
    private final int WATER_SIZE = 120; // needs to scale to screen size

    /* CLASS MEMBER VARIABLES */
    private int waterColor = Color.BLUE; // colour
    private float deviceWidth; // the width of the device
    private float deviceHeight; // the height of the device
    private Target targetCurrent; // the current instance of target

    //region CONSTRUCTOR
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Constructor, instantiates a new Water object
     */
    public Water() {
        super();
        this.size = WATER_SIZE;
        this.startX = this.initX;
        this.startY = this.initY;
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
        deviceWidth = width; // save the device width
        deviceHeight = height; // save the device height

        // generate the initial position
        this.trueX = this.initX = generateX(width); // generate random x
        this.trueY = this.initY = generateY(height); // generate random y

        // check for overlapping with target
        boolean overlapCheckTarget = checkDrawOverlap(targetCurrent);

        if (overlapCheckTarget){
            //waterColor = Color.MAGENTA; // Show that collision occurred *TEST STUFF*
            this.setPosition(width, height);
        }
    }

    /**
     * Get the current instance of target
     */
    public void setCurrentTarget(Target target) {
        targetCurrent = target;
    }
    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region GETTERS
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Get the X position of Water
     */
    protected float getTrueX() { return this.trueX; }

    /**
     * Get the Y position of Water
     */
    protected float getTrueY() {
        return this.trueY;
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
    protected int generateX(int width) {
        int min = WATER_SIZE; // most left it should be
        int max = width - WATER_SIZE; // most right it should be

        return random.nextInt(max-min) + min;
    }

    /**
     * Generate a random Y position within 20% - 70% of the screen height
     *
     * @param height how long the playable game screen is.
     */
    @Override
    protected int generateY(int height) {
        int min = (int) (height * minHeight) + WATER_SIZE; // highest point it should be drawn
        int max = (int) (height * maxHeight) + WATER_SIZE; // lowest point it should be drawn

        return random.nextInt(max-min) + min;
    }

    /**
     * Draw a Water circle with a specified colour at a randomly,
     * generated x and y.
     *
     * @param canvas the object to draw the Water object on.
     */
    @Override
    public void drawSprite(Canvas canvas) {
        paint.setColor(waterColor); // set the colour
        canvas.drawCircle(this.initX, this.initY, WATER_SIZE, paint); // draw the target
    }
    /*--------------------------------------------------------------------------------------------*/
    //endregion
}