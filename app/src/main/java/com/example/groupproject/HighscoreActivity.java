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

        // string array to retrieve high score rank positions
        String[] numList = getResources().getStringArray(R.array.string_array_highscore_numbers);
        // array adapter to populate the list view
        ArrayAdapter<String> arrayAdapterRanks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numList);
        // the list view
        ListView listViewNum = (ListView)findViewById(R.id.highscore_list_numbers);
        // set adapter
        listViewNum.setAdapter(arrayAdapterRanks);

        // declare shared preferences
        SharedPreferences myScores = getSharedPreferences("scores", Context.MODE_PRIVATE);

        // retrieve the high scores
        int score1 = myScores.getInt("score1",0);
        int score2 = myScores.getInt("score2",0);
        int score3 = myScores.getInt("score3",0);
        int score4 = myScores.getInt("score4",0);
        int score5 = myScores.getInt("score5",0);

        // an array to hold & sort the scores
        List<Integer> scoreList = new ArrayList<Integer>();
        // add the saved scores
        scoreList.add(score1);
        scoreList.add(score2);
        scoreList.add(score3);
        scoreList.add(score4);
        scoreList.add(score5);

        Collections.sort(scoreList); // sort the list
        Collections.reverse(scoreList); // sort by small first

        // array adapter to populate the list view
        ArrayAdapter<Integer> arrayAdapterScores = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoreList);
        // the list view
        ListView listViewScore = (ListView)findViewById(R.id.highscore_list_values);
        // set adapter
        listViewScore.setAdapter(arrayAdapterScores);

        // remove horizontal dividers from listviews / no grid mode
        listViewNum.setDividerHeight(0);
        listViewScore.setDividerHeight(0);

        // disable visual feedback when clicking on the high score lists
        listViewNum.setEnabled(false);
        listViewScore.setEnabled(false);
    }

    // When Play Again button is clicked, go to game screen
    public void onclickGameStart(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
}