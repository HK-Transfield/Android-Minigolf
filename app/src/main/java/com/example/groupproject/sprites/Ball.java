package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.groupproject.R;

/**
 * This is an interactive sprite the user can directly interact
 * with. With the ball, the user will have to fling it within
 * a certain amount of moves, else they will lose.
 *
 * @author Harmon Transfield, 1317381
 */
public class Ball extends Sprite {
    private final Paint paint = new Paint();
    private final float[] xGridArr = new float[3]; // stores every point on the grid along the x-axis
    private final float[] yGridArr = new float[3]; // stores every point on the grid along the y-axis
    private float xTouched; // x-axis of where the screen was touched
    private float yTouched; // y-axis of where the screen was touched
    boolean xCollision, yCollision = false; // the ball has touched left or right edge
    private float x, y = 0; // current ball location along x-axis
    private final int TRAVEL_TIME = 15; // seconds (t)
    private int elapsedTime = 0;
    private float initialVelocityX = 0;
    private float initialVelocityY = 0;
    private float xCurr = 0;
    private float yCurr = 0;

    /**
     * Constructor, instantiates a new Ball object
     *
     * @param color what the ball will look like on screen
     * @param size the size used as the radius of the ball
     */
    public Ball(int color, int size) {
        this.size = size; // set radius to 50
        paint.setColor(color);
    }

    public void setStartPosition(int width, int height) {
        float gridX = (float)(width / 3);
        float gridY = (float)(height / 3);

        x = (float)(width / 2);
        y = (float)(height / 2);
        xCurr = x;
        yCurr = y;

        // calculate and store every point in the grid along each axis
        for(int i = 0; i < xGridArr.length; i++) xGridArr[i] = (i + 1) * gridX;
        for(int i = 0; i < yGridArr.length; i++) yGridArr[i] = (i + 1) * gridY;
    }

    public void onDraw(Canvas canvas) {
        if(xTouched != 0.0f || yTouched != 0.0f) {
            // has the ball collided with any edges
            if (detectCollision(x, xGridArr[2])) xCollision = !xCollision;
            if (detectCollision(y, yGridArr[2])) yCollision = !yCollision;

            // move the circle
            x += animateCircle(xTouched, xCurr, xCollision, initialVelocityX);
            y += animateCircle(yTouched, yCurr, yCollision, initialVelocityY);

            // calculate what the velocity should be
            float finalVelocityX = 80;
            float finalVelocityY = 80;
            if(elapsedTime <= TRAVEL_TIME) {
                initialVelocityX += acceleration(initialVelocityX, finalVelocityX);
                initialVelocityY += acceleration(initialVelocityY, finalVelocityY);
            } else {
                initialVelocityX -= acceleration(initialVelocityX, finalVelocityX);
                initialVelocityY -= acceleration(initialVelocityY, finalVelocityY);

                if(initialVelocityX <= 0) {
                    initialVelocityX = 0;
                    xCurr = x;
                }
                if(initialVelocityY <= 0) {
                    initialVelocityY = 0;
                    yCurr = y;
                }

            }
            elapsedTime++;
        }
        canvas.drawCircle(x, y, size, paint);
    }

    public boolean interactedWith(float axis, float touchedAxis) {
        return touchedAxis <= axis + size && touchedAxis >= axis - size;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setXCollision(boolean collision) {
        xCollision = collision;
    }

    public void setYCollision(boolean collision) {
        yCollision = collision;
    }

    public void setTouchedCoordinates(float xT, float yT) {
        xTouched = xT;
        yTouched = yT;
    }

    public void setElapsedTime(int et) {
        elapsedTime = et;
    }

    private float animateCircle(float axisTouched, float axis, boolean collided, float speed) {
        if (axisTouched == 0) {
            return 0;
        } else {
            int direction = 0;

            // determine where to move the circle based on where the screen was touched
            if(axisTouched + size <= axis && axisTouched - size <= axis) direction = -1; // move UP or LEFT
            if(axisTouched + size == axis && axisTouched - size == axis) direction = 0; // no movement in this direction
            if(axisTouched + size >= axis && axisTouched - size >= axis) direction = 1; // move DOWN or RIGHT

            // invert the direction when the circle collides with an edge
            if (collided) direction *= -1;

            return (speed * direction);
        }
    }

    private float acceleration(float initialVelocity, float finalVelocity) {
        //Log.i("FINAL VELOCITY", "val: " + finalVelocity);
        return (finalVelocity - initialVelocity) / TRAVEL_TIME;
    }


    private boolean detectCollision(float axis, float screenEdge) {
        return axis - size < 0 || axis + size > screenEdge;
    }

    public boolean collisionCheck() {
        return false;
    }
}
