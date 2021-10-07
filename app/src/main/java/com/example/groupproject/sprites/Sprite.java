package com.example.groupproject.sprites;

import android.graphics.Canvas;

/**
 * This class is used to define any interactive object that is seen on
 * the screen when playing the game, whether it be a ball that the
 * player themselves can move, or an object that has some special
 * effect if the ball touches it.
 *
 * @author Harmon Transfield, 1317381
 */
abstract public class Sprite {
    float startX, startY; // determines where the sprite where first be drawn on the screen
    int size; // controls how large the sprite will be

    /**
     * Draws a new sprite on the game screen.
     *
     * @param canvas Where the sprite will be drawn
     */
    abstract void onDraw(Canvas canvas);

    /**
     * Checks if the sprite is touching any other sprite
     */
    abstract boolean collisionCheck();

    /**
     * Sets position after layout has loaded.
     * @param width the width of the current device
     * @param height the height of the current device
     */
    public abstract void setPosition(int width, int height);
}
