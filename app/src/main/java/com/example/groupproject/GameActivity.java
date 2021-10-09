package com.example.groupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.groupproject.sprites.Ball;
import com.example.groupproject.sprites.Sand;
import com.example.groupproject.sprites.Target;
import com.example.groupproject.sprites.Water;

public class GameActivity extends AppCompatActivity {

    ///////////////////////////////////////////////////////////////////////////////

    /**
     * This class fills up the screen and uses a Canvas to draw a ball
     * on screen. This ball can be interacted with through touched the
     * screen on any of the square grids
     *
     * @author Harmon Transfield, 1317381
     */
    public class GraphicsView extends View {
        private final GestureDetector gd;
        private final Ball golfBall;
        private final Target target;
        private final Sand sand;
        private final Water water;
        private TextView movesTextView;
        private TextView scoreTextView;
        private int score = 0;
        private final int BALL_SIZE = 50;

        /**
         * Constructor. Creates a new instance of a
         * GraphicsView object.
         */
        public GraphicsView(Context context) {
            super(context);
            golfBall = new Ball(BALL_SIZE);
            target = new Target();
            sand = new Sand();
            water = new Water();
            gd = new GestureDetector(context, new MyGestureListener());
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            golfBall.setPosition(w, h);
            target.setPosition(w, h);

            water.setCurrentTarget(target);
            water.setPosition(w, h);

            sand.setTargetCurrent(target);
            sand.setWaterCurrent(water);
            sand.setPosition(w, h);
        }

        public void setMovesTextView(TextView tv) {
            movesTextView = tv;
        }

        public void setScoreTextView(TextView tv) {
            scoreTextView = tv;
        }

        /**
         * Draws a ball on screen. The ball will remain in the center of the screen
         * until the screen is touched anywhere, which will then cause the ball to
         * move around the screen and bounce off the edges.
         */
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            movesTextView.setText(String.valueOf(golfBall.getMovesLeft()));
            scoreTextView.setText(String.valueOf(score));

            if (golfBall.getMovesLeft() == 0)
                endGame(0);

            target.drawSprite(canvas);
            water.drawSprite(canvas);
            sand.drawSprite(canvas);
            golfBall.drawSprite(canvas);

            if(water.collisionCheck(golfBall.getX(), golfBall.getY()))
                endGame(1);

            if(sand.collisionCheck(golfBall.getX(), golfBall.getY())) {
                if(!sand.getHasBallHit()) {
                    sand.setHasBallHit();
                    golfBall.setGesture(0, 0);
                    golfBall.reduceVelocity(getColor(R.color.sandYellow));
                }
            }

            if (target.collisionCheck(golfBall.getX(), golfBall.getY())) {

                if(score == 0)
                    score++;
                else
                    score += score * golfBall.getMovesLeft();

                golfBall.setPosition(getWidth(), getHeight());
                target.setPosition(getWidth(), getHeight());
                water.setPosition(getWidth(), getHeight());
                sand.setPosition(getWidth(), getHeight());
            }
            invalidate();
        }

        /**
         * @param reason The reason to end the game. 1 = water, 0 = 0 moves left
         */
        private void endGame(int reason) {
            this.setWillNotDraw(true);
            Intent gameOver = new Intent(getContext(), GameOverActivity.class);
            if (reason == 0){
                gameOver.putExtra("gameOverReason", "Ran out of moves"); // give the reason
            }
            else {
                gameOver.putExtra("gameOverReason", "Ball in the water"); // give the reason
            }
            startActivity(gameOver);
        }

        /**
         * Tracks every time the screen is touched by the user. The
         * coordinates retrieved from the touch is used to calculate
         * where the ball should move to.
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(gd.onTouchEvent(event)) {
                return true;
            }
            return super.onTouchEvent(event);
        }

        private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if(golfBall.swipedBall(e1.getX(), e1.getY())) {
                    golfBall.setGesture(e2.getX(), e2.getY());
                }
                return false;
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView movesTextView = findViewById(R.id.gamescreen_moves_left_value);
        TextView scoreTextView = findViewById(R.id.gamescreen_score_value);

        // set full screen sticky immersive
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        // create a new instance of GraphicsView, which will be added to the ConstraintLayout
        GraphicsView graphicsView = new GraphicsView(this);
        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.constraint_layout_graphics);
        constraintLayout.addView(graphicsView);

        graphicsView.setMovesTextView(movesTextView);
        graphicsView.setScoreTextView(scoreTextView);

    }
}