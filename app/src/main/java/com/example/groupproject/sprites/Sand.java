package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * This class is used to display a Sand obstacle in the game.
 * Collision with the Sand is defined by 50% of the Ball
 * overlapping with the Sand. When a collision with the
 * Sand is detected, the ball will slow.
 */
public class Sand extends Decor {

    /* CONSTANT CLASS MEMBER VARIABLES */
    private final int sandSize = 120; // needs to scale to screen size?
    private final double minHeight = 0.2; // uppermost % of screen to spawn in (20%)
    private final double maxHeight = 0.7; // lowest % of screen to spawn in (70%)

    /* CLASS MEMBER VARIABLES */
    private int sandColor = Color.YELLOW; // the colour
    private float sandStartX = -200; // initial position (off screen)
    private float sandStartY = -200; // initial position (off screen)
    private float sandTrueX; // relative x position after screen load
    private float sandTrueY; // relative y position after screen load
    private float deviceWidth; // the width of the device
    private float deviceHeight; // the height of the device
    private Water waterCurrent; // the current instance of water
    private Target targetCurrent; // the current instance of target

    //region CONSTRUCTOR
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Constructor, instantiates a new Sand object
     */
    public Sand() {
        super();
        this.size = sandSize;
        this.startX = sandStartX; // randomly generate this
        this.startY = sandStartY; // randomly generate this
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
        sandTrueX = sandStartX = generateX(width); // generate random x
        sandTrueY = sandStartY = generateY(height); // generate random y
        // check for overlapping with target
        Boolean overlapCheckTarget = checkDrawOverlapTarget(targetCurrent);
        if (overlapCheckTarget) {
            sandColor = Color.BLACK; // Show that collision occurred *TEST STUFF*
            setPosition(width, height);
        }
        // check for overlapping with water
        Boolean overlapCheckWater = checkDrawOverlapWater(waterCurrent);
        // if overlapping, recalculate position
        if (overlapCheckWater) {
            sandColor = Color.DKGRAY; // Show that collision occurred *TEST STUFF*
            setPosition(width, height);
        }
    }
    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region GETTERS
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Get the current instance of water.
     */
    public void getTheWater(Water water) {
        waterCurrent = water;
    }
    /**
     * Get the current instance of target.
     */
    public void getTheTarget(Target target) {
        targetCurrent = target;
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
        int min = sandSize; // most left it should be
        int max = width - sandSize; // most right it should be
        return random.nextInt(max-min) + min;
    }
    /**
     * Generate a random Y position within 20% - 70% of the screen height
     *
     * @param height how long the playable game screen is.
     */
    @Override
    protected int generateY(int height) {
        int min = (int) (height * minHeight) + sandSize; // highest point it should be drawn
        int max = (int) (height * maxHeight) + sandSize; // lowest point it should be drawn
        return random.nextInt(max-min) + min;
    }

    /**
     * Check if the sand position will overlap with the water.
     * If the sand overlaps water. it should generate a new position.
     * @param water the water to compare positions to.
     * @return True if a collision is detected.
     */
    public boolean checkDrawOverlapWater(Water water) {
        float xDifference = water.getWaterTrueX() - sandTrueX; // Get the X difference
        float yDifference = water.getWaterTrueY() - sandTrueY; // Get the Y difference
        // Calculate the difference squared
        float distanceSquared = xDifference  * xDifference  + yDifference * yDifference;
        // Collision check
        return distanceSquared < (size + size) * (sandSize + sandSize);
    }

    /**
     * Use positions to check for a overlapping collision with target.
     * If a collision is detected, sand should generate a new position.
     * @return True if a collision is detected.
     */
    public boolean checkDrawOverlapTarget(Target target) {
        float xDifference = target.getTargetTrueX() - sandTrueX; // Get the X difference
        float yDifference = target.getTargetTrueY() - sandTrueY; // Get the Y difference
        // Calculate the difference squared
        float distanceSquared = xDifference  * xDifference  + yDifference * yDifference;
        // Collision check
        return distanceSquared < (size + size) * (sandSize + sandSize);
    }

    /**
     * Draw a Sand circle with a specified colour at a randomly,
     * generated x and y.
     *
     * @param canvas the object to draw the Sand object on.
     */
    @Override
    public void drawSprite(Canvas canvas) {
        paint.setColor(sandColor); // set the colour
        canvas.drawCircle(sandStartX, sandStartY, sandSize, paint); // draw the target
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