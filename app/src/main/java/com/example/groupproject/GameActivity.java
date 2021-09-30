package com.example.groupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // set full screen sticky immersive
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
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