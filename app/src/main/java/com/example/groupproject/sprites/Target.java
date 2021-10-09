package com.example.groupproject.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import com.example.groupproject.R;

/**
 * This class is used to display a Target in the game for the user to
 * aim the Ball at. Collision with the target is defined by ___% of the
 * Ball overlapping with the Target. When a collision with the Target
 * is achieved, the round ends.
 *
 * @author Wednesday Wilson
 */
public class Target extends Decor {

    /* CONSTANT CLASS MEMBER VARIABLES */
    private final int TARGET_SIZE = 60; // needs to scale to screen size
    private Context context;
    private final Bitmap flagImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.flag);
    private final int xOffset = 30;
    private final int yOffset = 160;

    //region CONSTRUCTOR
    /*--------------------------------------------------------------------------------------------*/
    /**
     * Constructor, instantiates a new Target object
     */
    public Target(Context c) {
        super();
        this.size = TARGET_SIZE;
        context = c;
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
        this.trueX = this.x = generateX(width); // generate random x
        this.trueY = this.y = generateY(height); // generate random y
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
        int min = TARGET_SIZE; // most left it should be
        int max = width - TARGET_SIZE; // most right it should be
        return random.nextInt(max-min) + min;
    }

    /**
     * Generate a random Y position at the top of the screen.
     * Within TargetSize - 20% of the screen height.
     *
     * @param height how long the playable game screen is.
     */
    @Override
    protected int generateY(int height) {
        int min = TARGET_SIZE; // highest point it should be drawn
        int max = (int) (height * minHeight); // lowest point it should be drawn
        return random.nextInt(max-min) + min;
    }

    /**
     * Draw a Target (circle) with a specified colour at a randomly,
     * generated x and y.
     *
     * @param canvas the object to draw the Target object on.
     */
    @Override
    public void drawSprite(Canvas canvas) {
        // colour
        int targetColor = Color.BLACK;
        paint.setColor(targetColor); // set the colour
        canvas.drawCircle(this.x, this.y, TARGET_SIZE, paint); // draw the target
        // draw the image
        canvas.drawBitmap(flagImage, this.x - xOffset
                , this.y - yOffset, paint);
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion
}
