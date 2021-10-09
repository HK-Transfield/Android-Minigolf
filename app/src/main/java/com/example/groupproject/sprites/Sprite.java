package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * This class is used to define any interactive object that is seen on
 * the screen when playing the game, whether it be a ball that the
 * player themselves can move, or an object that has some special
 * effect if the ball touches it.
 *
 * @author Harmon Transfield, 1317381
 */
abstract public class Sprite {
    protected float x;
    protected float y; // determines where the sprite where first be drawn on the screen
    protected Color color;
    protected int size; // controls how large the sprite will be
    protected final Paint paint = new Paint();
    /**
     * Draws a new sprite on the game screen.
     *
     * @param canvas Where the sprite will be drawn
     */
    abstract public void drawSprite(Canvas canvas);

    /**
     * Sets position after layout has loaded.
     * @param width the width of the current device
     * @param height the height of the current device
     */
    public abstract void setPosition(int width, int height);
}
