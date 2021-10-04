package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * This class is used to display a Sand obstacle in the game.
 * Collision with the Sand is defined by 50% of the Ball
 * overlapping with the Sand. When a collision with the
 * Sand is detected, the ball will slow?.
 */
public class Sand extends Decor {

    private int targetSize = 200; // needs to scale to screen size
    private float targetX = generateX();
    private float targetY = generateY();
    private int targetColor = Color.YELLOW;

    /**
     * Constructor
     */
    public Sand() {
        super();
        this.size = targetSize;
        this.startX = targetX; // randomly generate this
        this.startY = targetY; // randomly generate this
    }

    /**
     * Draw a Target circle with a specified colour at a randomly,
     * generated x and y.
     */
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(targetColor); // set the colour
        canvas.drawCircle(targetX, targetY, targetSize, paint); // draw the target
    }

    /**
     * Generate a random x position in the top 10% of the screen
     */
    @Override
    int generateX() {

        int min = targetSize; // ??
        int max = 800; // screen width - targetSize
        int result = random.nextInt(max-min) + min;
        return result;
    }

    @Override
    int generateY() {

        int min = 1200; // ??
        int max = 1500; // top 20% of screen height?
        int result = random.nextInt(max-min) + min;
        return result;
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