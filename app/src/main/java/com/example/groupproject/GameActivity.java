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
import com.example.groupproject.sprites.Target;

import org.w3c.dom.Text;

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
        private TextView textView;

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            golfBall.setStartPosition(w, h);
        }

        /**
         * Constructor. Creates a new instance of a
         * GraphicsView object.
         */
        public GraphicsView(Context context) {
            super(context);
            golfBall = new Ball(getColor(R.color.white), 50);
            target = new Target();
            gd = new GestureDetector(context, new MyGestureListener());
        }

        public void setTextView(TextView tv) {
            textView = tv;
        }


        /**
         * Draws a ball on screen. The ball will remain in the center of the screen
         * until the screen is touched anywhere, which will then cause the ball to
         * move around the screen and bounce off the edges.
         */
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            textView.setText(String.valueOf(golfBall.getMovesLeft()));
            golfBall.onDraw(canvas);
            target.onDraw(canvas);
            invalidate();
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
                if(golfBall.swipedBall(golfBall.getX(), e1.getX()) && golfBall.swipedBall(golfBall.getY(), e1.getY())) {
                    if (golfBall.getMovesLeft() != 0)
                        golfBall.setGesture(e2.getX(), e2.getY());
                }

                if (golfBall.getMovesLeft() == 0) {
                    Intent gameOver = new Intent(getContext(), GameOverActivity.class);
                    startActivity(gameOver);
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

        TextView textView = findViewById(R.id.gamescreen_moves_left_value);

        // set full screen sticky immersive
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        // create a new instance of GraphicsView, which will be added to the ConstraintLayout
        GraphicsView graphicsView = new GraphicsView(this);
        graphicsView.setTextView(textView);
        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.constraint_layout_graphics);
        constraintLayout.addView(graphicsView);

    }



    // *TEMP HELP*
    // When Main button is clicked, go to Main screen
    public void onclickMainScreen(View view) {
        Intent mainScreen = new Intent(this, MainActivity.class);
        startActivity(mainScreen);
    }
    // *TEMP HELP*
    // When Game Over button is clicked, go to Game Over screen
    public void onclickGameOverScreen(View view) {
        Intent gameOver = new Intent(this, GameOverActivity.class);
        startActivity(gameOver);
    }
}