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

    private final int sandSize = 100; // needs to scale to screen size
    private final int sandColor = Color.YELLOW;
    private final double minHeight = 0.2; // uppermost % of screen to spawn in (20%)
    private final double maxHeight = 0.7; // lowest % of screen to spawn in (70%)
    private float sandStartX = -200; // initial position (off screen)
    private float sandStartY = -200; // initial position (off screen)

    /**
     * Constructor, instantiates a new Sand object
     */
    public Sand() {
        super();
        this.size = sandSize;
        this.startX = sandStartX; // randomly generate this
        this.startY = sandStartY; // randomly generate this
    }

    /**
     * Draw a Sand circle with a specified colour at a randomly,
     * generated x and y.
     *
     * @param canvas the object to draw the Sand object on.
     */
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(sandColor); // set the colour
        canvas.drawCircle(sandStartX, sandStartY, sandSize, paint); // draw the target
    }

    /**
     * Generate a random X position within the playable area of the screen width.
     *
     * @param width how wide the playable game screen is.
     */
    @Override
    int generateX(int width) {

        int min = sandSize; // most left it should be
        int max = width - sandSize; // most right it should be
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

        int min = (int) (height * minHeight) + sandSize; // highest point it should be drawn
        int max = (int) (height * maxHeight) + sandSize; // lowest point it should be drawn
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
        sandStartX = generateX(width); // generate random x
        sandStartY = generateY(height); // generate random y
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