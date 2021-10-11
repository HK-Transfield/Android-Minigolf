package com.example.groupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // set full screen sticky immersive
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        // declare shared preferences
        SharedPreferences myScores = getSharedPreferences("scores", Context.MODE_PRIVATE);

        // retrieve the high scores
        int score1 = myScores.getInt("score1",0);
        int score2 = myScores.getInt("score2",0);
        int score3 = myScores.getInt("score3",0);
        int score4 = myScores.getInt("score4",0);
        int score5 = myScores.getInt("score5",0);

        TextView first = findViewById(R.id.score_first); // get text view
        first.setText(String.valueOf(score1)); // set the score text

        TextView second = findViewById(R.id.score_second); // get text view
        second.setText(String.valueOf(score2)); // set the score text

        TextView third = findViewById(R.id.score_third); // get text view
        third.setText(String.valueOf(score3)); // set the score text

        TextView fourth = findViewById(R.id.score_fourth); // get text view
        fourth.setText(String.valueOf(score4)); // set the score text

        TextView fifth = findViewById(R.id.score_fifth); // get text view
        fifth.setText(String.valueOf(score5)); // set the score text
    }

    // When Play Again button is clicked, go to game screen
    public void onclickGameStart(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
}