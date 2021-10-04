package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Paint;

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
    private final int TRAVEL_TIME = 30; // seconds (t)
    private int elapsedTime = 0;
    private float initialVelocityX = 0;
    private float initialVelocityY = 0;
    private int movesLeft;
    private boolean inStartPosition = false;

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

    // TODO: calculate screen size within the class and define boundaries
    public void onDraw(Canvas canvas) {
        if(!inStartPosition) {
            float width = canvas.getWidth();
            float height = canvas.getHeight();

            x = width / (float)2;
            y = height / (float)3;

            // calculate and store every point in the grid along each axis
            for(int i = 0; i < xGridArr.length; i++) xGridArr[i] = (i + 1) * (width / (float)3);
            for(int i = 0; i < yGridArr.length; i++) yGridArr[i] = (i + 1) * (height / (float)3);

            inStartPosition = true;
        } else {
            if(yTouched != 0.0f || xTouched != 0.0f) {
                // has the ball collided with any edges
                if (detectCollision(x, xGridArr[2])) xCollision = !xCollision;
                if (detectCollision(y, yGridArr[2])) yCollision = !yCollision;

                // move the circle
                x += animateCircle(xTouched, xGridArr, xCollision, initialVelocityX);
                y += animateCircle(yTouched, yGridArr, yCollision, initialVelocityY);

                // calculate what the velocity should be
                float finalVelocityX = 100;
                float finalVelocityY = 100;
                if(elapsedTime <= TRAVEL_TIME) {
                    initialVelocityX += acceleration(initialVelocityX, finalVelocityX);
                    initialVelocityY += acceleration(initialVelocityY, finalVelocityY);
                } else {
                    initialVelocityX -= acceleration(initialVelocityX, finalVelocityX);
                    initialVelocityY -= acceleration(initialVelocityY, finalVelocityY);

                    if(initialVelocityX <= 0) initialVelocityX = 0;
                    if(initialVelocityY <= 0) initialVelocityY = 0;

                }
                elapsedTime++;
            }
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

    private float animateCircle(float axisTouched, float[] gridArr, boolean collided, float speed) {
        int direction = 0;

        // determine where to move the circle based on where the screen was touched
        if(axisTouched <= gridArr[0]) direction = -1; // move UP or LEFT
        if(axisTouched >= gridArr[0] && axisTouched <= gridArr[1]) direction = 0; // no movement in this direction
        if(axisTouched >= gridArr[1] && axisTouched <= gridArr[2]) direction = 1; // move DOWN or RIGHT

        // invert the direction when the circle collides with an edge
        if (collided) direction *= -1;

        return (speed * direction);
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

    public int getMovesLeft() {
        return movesLeft;
    }
}
