package com.example.groupproject.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import com.example.groupproject.R;

/**
 * This class is used to display a Sand obstacle in the game.
 * Collision with the Sand is defined by 50% of the Ball
 * overlapping with the Sand. When a collision with the
 * Sand is detected, the ball will slow.
 *
 * #### Image source:
 * https://opengameart.org/content/lpc-sandrock-alt-colors
 */
public class Sand extends Decor {

    /* CONSTANT CLASS MEMBER VARIABLES */
    private final int SAND_SIZE = 80; // needs to scale to screen size?
    private Context context;
    private final int xOffset = 120;
    private final int yOffset = 120;

    /* CLASS MEMBER VARIABLES */
    private Water waterCurrent; // the current instance of water
    private Target targetCurrent; // the current instance of target
    private boolean hasBallHit = false; // checks if the ball has hit it in that round

    //region CONSTRUCTOR
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Constructor, instantiates a new Sand object
     */
    public Sand(Context c) {
        super();
        this.size = SAND_SIZE;
        context =  c;
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region GETTERS
    /*--------------------------------------------------------------------------------------------*/

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
        this.trueX = this.x = generateX(width); // generate random x
        this.trueY = this.y = generateY(height); // generate random y

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
        int sandColor = Color.YELLOW;

        paint.setColor(sandColor); // set the colour
        canvas.drawCircle(this.x, this.y, SAND_SIZE, paint); // draw the sand
        // draw the image
        Bitmap sandImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.sand);
        canvas.drawBitmap(sandImage, this.x - xOffset
                , this.y - yOffset, paint);
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion
}