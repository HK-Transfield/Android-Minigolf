package com.example.groupproject.sprites;

import android.graphics.Canvas;

/**
 * This is an interactive sprite the user can directly interact
 * with. With the ball, the user will have to fling it within
 * a certain amount of moves, else they will lose.
 *
 * @author Harmon Transfield, 1317381
 */
public class Ball extends Sprite {
    private final int MAX_SPEED = 10; // the fastest possible speed the ball can move
    private final int MAX_MOVES = 3; // how many moves the player can make before a game over
    private float x; // current x position on screen
    private float y; // current y position on screen
    private boolean inMotion; // the ball is travelling, meaning the user cannot touch it
    private int movesLeft; // the number of moves the player has made
    private double time; // used to calculate acceleration

    public void onDraw(Canvas canvas) {

    }

    public boolean collisionCheck() {
        return false;
    }

    public boolean onTouchEvent() {
        return false;
    }

    public void onFling() {

    }

    public void moveBall() {

    }

    public int getMovesLeft() {
        return movesLeft;
    }
}
