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

    private int targetSize = 50; // needs to scale to screen size
    private float targetX = generateX();
    private float targetY = generateY();
    private int targetColor = Color.RED;

    /**
     * Constructor
     */
    public Target() {
        super();
        this.size = targetSize;
        this.startX = targetX;
        this.startY = targetY;
    }

    /**
     * Draw a Target (circle) with a specified colour at a randomly,
     * generated x and y.
     */
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(targetColor); // set the colour
        canvas.drawCircle(targetX, targetY, targetSize, paint); // draw the target
    }

    /**
     * Generate a random x position in the top area of the screen
     */
    @Override
    int generateX() {

        int min = targetSize;
        int max = 800; // the most right it should be drawn (view width)
        int result = random.nextInt(max-min) + min;
        return result;
    }

    @Override
    int generateY() {

        int min = targetSize; // highest point it should be drawn
        int max = 400; // lowest point it should be drawn
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
