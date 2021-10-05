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

    private final int targetSize = 60; // needs to scale to screen size
    private final int targetColor = Color.RED;
    private final double maxHeight = 0.2; // lowest % of screen to spawn in (20%)

    private float targetStartX = -200; // initial position (off screen)
    private float targetStartY = -200; // initial position (off screen)

    /**
     * Constructor, instantiates a new Target object
     */
    public Target() {
        super();
        this.size = targetSize;
        this.startX = targetStartX;
        this.startY = targetStartY;
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
     * Generate a random X position within the playable area of the screen width.
     *
     * @param width how wide the playable game screen is.
     */
    @Override
    int generateX(int width) {

        int min = targetSize; // most left it should be
        int max = width - targetSize; // most right it should be
        int result = random.nextInt(max-min) + min;
        return result;
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
        targetStartX = generateX(width); // generate random x
        targetStartY= generateY(height); // generate random y
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
