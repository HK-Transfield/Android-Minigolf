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
    private final int MAX_TIME = 30; // seconds (t)
    private final float MAX_VELOCITY = 80;
    private final float[] gridXArr = new float[3]; // stores every point on the grid along the x-axis
    private final float[] gridYArr = new float[3]; // stores every point on the grid along the y-axis

    /* CLASS MEMBER VARIABLES */
    private int travelTime = 0; // how long the ball has travelled for
    private int movesLeft = 3;
    private float x = 0; // change along x-axis
    private float y = 0; // change along y-axis
    private float velocityX = 0; // current ball velocity along x-axis
    private float velocityY = 0; // current ball velocity along y-axis
    private float swipedX; // x-axis of where the screen was touched
    private float swipedY; // y-axis of where the screen was touched
    private float stationaryX; // stationary position along x-axis
    private float stationaryY; // stationary position along y-axis
    private boolean collidedX = false;
    private boolean colliedY = false; // the ball has touched left or right edge

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
        x = stationaryX = (float)(width / 2);
        y = stationaryY = (float)(height / 2);

        // calculate and store every point in the grid along each axis
        for(int i = 0; i < gridXArr.length; i++) gridXArr[i] = (i + 1) * gridX;
        for(int i = 0; i < gridYArr.length; i++) gridYArr[i] = (i + 1) * gridY;
    }

    /**
     * Called whenever the user interacts and swipes the ball.
     *
     * @param sX Where the user moved their finger along the X-axis
     * @param sY Where the user moved their finder along the Y-axis
     */
    public void setGesture(float sX, float sY) {

        // player used up a move
        movesLeft--;

        // reset travel time
        travelTime = 0;

        // reset collision checks
        collidedX = colliedY = false;

        // set where the user swiped
        swipedX = sX;
        swipedY = sY;
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

    /**
     * Gets the current number of moves that the ball has left to make
     */
    public int getMovesLeft() {
        return movesLeft;
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion

    //region MOVEMENT METHODS
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Draws the ball on screen and move if the user interacts with the ball.
     */
    public void onDraw(Canvas canvas) {
        if(movesLeft >= 0) { // check that the ball still has moves left it can make
            if(swipedX != 0.0f || swipedY != 0.0f) { // the user has interacted with the ball

                // has the ball collided with any edges, if so rebound it
                if (hasCollided(x, gridXArr[2])) collidedX = !collidedX;
                if (hasCollided(y, gridYArr[2])) colliedY = !colliedY;

                // move the circle
                x += velocityX * direction(swipedX, stationaryX, collidedX);
                y += velocityY * direction(swipedY, stationaryY, colliedY);

                // change the speed of the ball
                velocityX += acceleration(velocityX);
                velocityY += acceleration(velocityY);

                // make the ball stationary and save its current position
                if(velocityX <= 0) {
                    velocityX = 0;
                    stationaryX = x;
                }
                if(velocityY <= 0) {
                    velocityY = 0;
                    stationaryY = y;
                }
                travelTime++;
            }
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
    private float direction(float axisTouched, float axis, boolean collided) {
        int direction = 0;

        // determine where to move the ball based on where the the user swiped the screen in relation to its stationary position
        if(axisTouched + size <= axis && axisTouched - size <= axis) direction = -1; // move UP or LEFT
        if(axisTouched + size == axis && axisTouched - size == axis) direction = 0; // no movement in this direction
        if(axisTouched + size >= axis && axisTouched - size >= axis) direction = 1; // move DOWN or RIGHT

        // invert the direction when the circle collides with an edge
        if (collided) direction *= -1;

        return direction;
    }

    /**
     * Computes the basic formula for acceleration -> a = (Vf - Vi) / t
     * This is used to compute the change in speed the ball will make
     * over a set amount of time while travelling.
     *
     * @param initialVelocity The ball's current speed it is moving at
     * @return +ve is accelerating, -ve if decelerating
     */
    private float acceleration(float initialVelocity) {

        // formula for acceleration
        float acceleration = (MAX_VELOCITY - initialVelocity) / MAX_TIME;

        // check how long its travelled
        if(travelTime <= MAX_TIME) return acceleration;
        else return -acceleration;
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
    public boolean swipedBall(float axis, float touchedAxis) {
        return touchedAxis <= axis + size && touchedAxis >= axis - size;
    }

    /**
     * Checks if the ball has collided with any of the 4 edges of the screen.
     *
     * @param axis Either the top and bottom edge, or right and left edge
     * @param screenEdge Specifically for the bottom or right edge
     * @return True if the ball has collided with any edges
     */
    private boolean hasCollided(float axis, float screenEdge) {
        return axis - size < 0 || axis + size > screenEdge;
    }

    // TODO: Maybe put the code for detectCollision() here?
    public boolean collisionCheck() {
        return false;
    }

    /*--------------------------------------------------------------------------------------------*/
    //endregion
}
