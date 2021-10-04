package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.groupproject.GameActivity;

/**
 * This class is used to display a Water obstacle in the game.
 * Collision with the target is defined by 50% of the Ball
 * overlapping with the Water. When a collision with the
 * Water is detected, the player will fail the round and the
 * game over screen will appear.
 */
public class Water extends Decor {

    private int targetSize = 180; // needs to scale to screen size
    private float targetX = generateX();
    private float targetY = generateY();
    private int targetColor = Color.BLUE;

    /**
     * Constructor
     */
    public Water() {
        super();
        this.size = targetSize;
        this.startX = targetX;
        this.startY = targetY;
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
        int max = 800; // the most right it should be drawn (view width)
        int result = random.nextInt(max-min) + min;
        return result;
    }

    @Override
    int generateY() {

        int min = 800; // highest point it should be drawn
        int max = 1200;; // lowest point it should be drawn
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