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
    private final int waterSize = 120; // needs to scale to screen size
    private final double minHeight = 0.2; // uppermost % of screen to spawn in (20%)
    private final double maxHeight = 0.7; // lowest % of screen to spawn in (70%)

    /* CLASS MEMBER VARIABLES */
    private int waterColor = Color.BLUE; // colour
    private float waterStartX = -200; // initial position (off screen)
    private float waterStartY = -200; // initial position (off screen)
    private float waterTrueX; // relative x position after screen load
    private float waterTrueY; // relative y position after screen load
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
        this.size = waterSize;
        this.startX = waterStartX;
        this.startY = waterStartY;
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
        waterTrueX = waterStartX = generateX(width); // generate random x
        waterTrueY = waterStartY = generateY(height); // generate random y
        // check for overlapping with target
        Boolean overlapCheckTarget = checkDrawOverlapTarget(targetCurrent);
        if (overlapCheckTarget){
            waterColor = Color.MAGENTA; // Show that collision occurred *TEST STUFF*
            setPosition(width, height);
        }
    }
    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region GETTERS
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Get the current instance of target
     */
    public void getTheTarget(Target target) {
        targetCurrent = target;
    }
    /**
     * Get the X position of Water
     */
    public float getWaterTrueX() {
        return waterTrueX;
    }

    /**
     * Get the Y position of Water
     */
    public float getWaterTrueY() {
        return waterTrueY;
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
        int min = waterSize; // most left it should be
        int max = width - waterSize; // most right it should be
        return random.nextInt(max-min) + min;
    }

    /**
     * Generate a random Y position within 20% - 70% of the screen height
     *
     * @param height how long the playable game screen is.
     */
    @Override
    protected int generateY(int height) {
        int min = (int) (height * minHeight) + waterSize; // highest point it should be drawn
        int max = (int) (height * maxHeight) + waterSize; // lowest point it should be drawn
        return random.nextInt(max-min) + min;
    }

    /**
     * Check if the water position will overlap with the Target.
     * If the water overlaps target, it should generate a new position.
     * @param target the target to compare positions to.
     * @return True if a collision is detected.
     */
    public boolean checkDrawOverlapTarget(Target target) {
        float xDifference = target.getTargetTrueX() - waterTrueX; // Get the X difference
        float yDifference = target.getTargetTrueY() - waterTrueY; // Get the Y difference
        // Calculate the difference squared
        float distanceSquared = xDifference  * xDifference  + yDifference * yDifference;
        // Collision check
        return distanceSquared < (size + size) * (waterSize + waterSize);
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
        canvas.drawCircle(waterStartX, waterStartY, waterSize, paint); // draw the target
    }
    /**
     * TODO
     */
    @Override
    void onCollision() {
    }
    /*--------------------------------------------------------------------------------------------*/
    //endregion
}