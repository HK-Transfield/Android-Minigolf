package com.example.groupproject.sprites;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * This is an interactive sprite the user can directly interact
 * with. With the ball, the user will have to fling it within
 * a certain amount of moves, else they will lose.
 *
 * @author Harmon Transfield, 1317381
 */
public class Ball extends Sprite {

    /* CONSTANT CLASS MEMBER VARIABLES */
    private final Paint paint = new Paint();
    private final int TRAVEL_TIME = 15; // seconds (t)
    private final float MAX_VELOCITY = 80;
    private final float[] xGridArr = new float[3]; // stores every point on the grid along the x-axis
    private final float[] yGridArr = new float[3]; // stores every point on the grid along the y-axis

    /* CLASS MEMBER VARIABLES */
    private int elapsedTime = 0; // how long the ball has travelled for
    private float x = 0; // change along x-axis
    private float y = 0; // change along y-axis
    private float initialVelocityX = 0; // current ball velocity along x-axis
    private float initialVelocityY = 0; // current ball velocity along y-axis
    private float xTouched; // x-axis of where the screen was touched
    private float yTouched; // y-axis of where the screen was touched
    private float xCurr; // stationary position along x-axis
    private float yCurr; // stationary position along y-axis
    private boolean xCollision = false;
    private boolean yCollision = false; // the ball has touched left or right edge

    /**
     * Constructor, instantiates a new Ball object
     *
     * @param color what the ball will look like on screen
     * @param size the size used as the radius of the ball
     */
    public Ball(int color, int size) {
        this.size = size;
        paint.setColor(color);
    }

    //region SETTERS
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Determines where the ball will first be drawn on the screen and creates a 3x3 grid with the
     * given width and height of the play area.
     *
     * @param width how wide the playable game screen is.
     * @param height how long the playable game screen is.
     */
    public void setStartPosition(int width, int height) {

        // calculate the size of 1 section of the grid
        float gridX = (float)(width / 3);
        float gridY = (float)(height / 3);

        // calculate the starting position of the ball and save it
        x = (float)(width / 2);
        y = (float)(height / 2);
        xCurr = x;
        yCurr = y;

        // calculate and store every point in the grid along each axis
        for(int i = 0; i < xGridArr.length; i++) xGridArr[i] = (i + 1) * gridX;
        for(int i = 0; i < yGridArr.length; i++) yGridArr[i] = (i + 1) * gridY;
    }

    /**
     * Resets the collision check along the X-axis
     */
    public void setXCollision(boolean collision) {
        xCollision = collision;
    }

    /**
     * Resets the collision check along the Y-axis
     */
    public void setYCollision(boolean collision) {
        yCollision = collision;
    }

    /**
     * Set where the user has swiped the ball
     *
     * @param xT Where the user moved their finger along the X-axis
     * @param yT Where the user moved their finder along the Y-axis
     */
    public void setTouchedCoordinates(float xT, float yT) {
        xTouched = xT;
        yTouched = yT;
    }

    /**
     * Reset the time for how long the ball has travelled for
     */
    public void setElapsedTime(int et) {
        elapsedTime = et;
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region GETTERS
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Gets the current position of the ball along the X-axis
     */
    public float getX() {
        return x;
    }

    /**
     * Gets the current position of the ball along the Y-axis
     */
    public float getY() {
        return y;
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region MOVEMENT METHODS
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Draws the ball on screen and move if the user interacts with the ball.
     */
    public void onDraw(Canvas canvas) {

        if(xTouched != 0.0f || yTouched != 0.0f) { // the user has interacted with the ball

            // has the ball collided with any edges, if so rebound it
            if (detectCollision(x, xGridArr[2])) xCollision = !xCollision;
            if (detectCollision(y, yGridArr[2])) yCollision = !yCollision;

            // move the circle
            x += animateCircle(xTouched, xCurr, xCollision, initialVelocityX);
            y += animateCircle(yTouched, yCurr, yCollision, initialVelocityY);

            if(elapsedTime <= TRAVEL_TIME) { // check that ball should still be travelling
                initialVelocityX += acceleration(initialVelocityX);
                initialVelocityY += acceleration(initialVelocityY);
            } else { // ball has travelled long enough, slow it down
                initialVelocityX -= acceleration(initialVelocityX);
                initialVelocityY -= acceleration(initialVelocityY);

                // make the ball stationary and save its current position
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

    /**
     * Uses the current speed and where the ball was touched to determine what direction the
     * ball should should move in.
     *
     * @param axis The current stationary position of the ball
     * @param axisTouched Where the user swiped on the screen
     * @param collided Inverts the direction if the ball has collided with any edge
     * @return The direction modifier
     */
    private float animateCircle(float axisTouched, float axis, boolean collided, float speed) {
        if (axisTouched == 0) {
            return 0;
        } else {
            int direction = 0;

            // determine where to move the ball based on where the the user swiped the screen in relation to its stationary position
            if(axisTouched + size <= axis && axisTouched - size <= axis) direction = -1; // move UP or LEFT
            if(axisTouched + size == axis && axisTouched - size == axis) direction = 0; // no movement in this direction
            if(axisTouched + size >= axis && axisTouched - size >= axis) direction = 1; // move DOWN or RIGHT

            // invert the direction when the circle collides with an edge
            if (collided) direction *= -1;

            return (speed * direction);
        }
    }

    /**
     * Computes the basic formula for acceleration -> a = (Vf - Vi) / t
     * This is used to have the ball speed up and slow down
     *
     * @param initialVelocity The ball's current speed it is moving at
     * @return The change it speed the ball should make
     */
    private float acceleration(float initialVelocity) {
        return (MAX_VELOCITY - initialVelocity) / TRAVEL_TIME;
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region CONDITIONAL METHODS
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Determines if the origin of when the swiped occurred happened in the ball.
     *
     * @param axis the direction to compare the gesture's direction
     * @param touchedAxis the location of the gesture
     * @return True if the ball was swiped
     */
    public boolean interactedWith(float axis, float touchedAxis) {
        return touchedAxis <= axis + size && touchedAxis >= axis - size;
    }

    /**
     * Checks if the ball has collided with any of the 4 edges of the screen.
     *
     * @param axis Either the top and bottom edge, or right and left edge
     * @param screenEdge Specifically for the bottom or right edge
     * @return True if the ball has collided with any edges
     */
    private boolean detectCollision(float axis, float screenEdge) {
        return axis - size < 0 || axis + size > screenEdge;
    }

    // TODO: Maybe put the code for detectCollision() here?
    public boolean collisionCheck() {
        return false;
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion
}
