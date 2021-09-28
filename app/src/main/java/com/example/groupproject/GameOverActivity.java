package com.example.groupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // set full screen sticky immersive
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }

    // When Highscores button is clicked, go to Highscores screen
    public void onclickHighscoreScreen(View view) {
        Intent highScoreScreen = new Intent(this, HighscoreActivity.class);
        startActivity(highScoreScreen);
    }

    // When Play Again button is clicked, go to Game screen
    public void onclickPlayAgain(View view) {
        Intent playAgain = new Intent(this, GameActivity.class);
        startActivity(playAgain);
    }
}