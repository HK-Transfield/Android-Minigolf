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

    private final int waterSize = 120; // needs to scale to screen size
    private final int waterColor = Color.BLUE;
    private final double minHeight = 0.2; // uppermost % of screen to spawn in (20%)
    private final double maxHeight = 0.7; // lowest % of screen to spawn in (70%)

    private float waterStartX = -200; // initial position (off screen)
    private float waterStartY = -200; // initial position (off screen)

    /**
     * Constructor, instantiates a new Water object
     */
    public Water() {
        super();
        this.size = waterSize;
        this.startX = waterStartX;
        this.startY = waterStartY;
    }

    /**
     * Draw a Water circle with a specified colour at a randomly,
     * generated x and y.
     *
     * @param canvas the object to draw the Water object on.
     */
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(waterColor); // set the colour
        canvas.drawCircle(waterStartX, waterStartY, waterSize, paint); // draw the target
    }

    /**
     * Generate a random X position within the playable area of the screen width.
     *
     * @param width how wide the playable game screen is.
     */
    @Override
    int generateX(int width) {
        int min = waterSize; // most left it should be
        int max = width - waterSize; // most right it should be
        int result = random.nextInt(max-min) + min;
        return result;
    }

    /**
     * Generate a random Y position within 20% - 70% of the screen height
     *
     * @param height how long the playable game screen is.
     */
    @Override
    int generateY(int height) {
        int min = (int) (height * minHeight) + waterSize; // highest point it should be drawn
        int max = (int) (height * maxHeight) + waterSize; // lowest point it should be drawn
        int result = random.nextInt(max-min) + min;
        return result;
    }

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
        waterStartX = generateX(width); // generate random x
        waterStartY = generateY(height); // generate random y
    }

    @Override
    void checkDrawOverlap() {
        super.checkDrawOverlap();
    }

    @Override
    void onCollision() {
        super.onCollision();
    }


}