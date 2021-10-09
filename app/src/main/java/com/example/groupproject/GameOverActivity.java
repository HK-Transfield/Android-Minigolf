package com.example.groupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameOverActivity extends AppCompatActivity {

    private int rank; // the rank
    private Boolean rankSet = false; // if the score is top 5
    private final String noRank = "Not ranked"; // text for score not top 5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Bundle bundle = getIntent().getExtras(); // get the player info

        String reason = bundle.getString("gameOverReason"); // get the game over reason
        TextView reasonText = findViewById(R.id.game_over_reason); // get reason textview
        reasonText.setText(reason); // set reason text

        int playerScore = bundle.getInt("score"); // get the score from game activity
        TextView scoreText = findViewById(R.id.gameover_score_value); // get score textview
        scoreText.setText(String.valueOf(playerScore)); // set the score text

        // declare shared preferences
        SharedPreferences myScores = getSharedPreferences("scores", Context.MODE_PRIVATE);
        // declare shared preferences editor
        SharedPreferences.Editor editor = myScores.edit();

        TextView rankText = findViewById(R.id.gameover_highscore_rank); // get rank textview

        // retrieve the high scores
        int score1 = myScores.getInt("score1",0);
        int score2 = myScores.getInt("score2",0);
        int score3 = myScores.getInt("score3",0);
        int score4 = myScores.getInt("score4",0);
        int score5 = myScores.getInt("score5",0);

        // TODO USE to reset the high scores
/*        int reset = 0;
        editor.putInt("score1", reset );
        editor.putInt("score2", reset );
        editor.putInt("score3", reset );
        editor.putInt("score4", reset );
        editor.putInt("score5", reset );*/

        // an array to hold & sort the scores
        List<Integer> scoreList = new ArrayList<>();
        // add the saved scores
        scoreList.add(score1);
        scoreList.add(score2);
        scoreList.add(score3);
        scoreList.add(score4);
        scoreList.add(score5);

        Collections.sort(scoreList); // sort the list
        Collections.reverse(scoreList); // sort by small first

        // for each score in the score list
        for (int i = 0; i < 5; i++){

            // if the current score is more than the stored score
            if (playerScore > scoreList.get(i)){

                rank = i + 1; // get the rank
                rankSet = true; // declare that rank has been set
                rankText.setText(String.valueOf(rank)); // set the rank text

                // assign the score to the correct place

                // # 1
                if (i == 0){
                    // make the player score #1
                    editor.putInt("score1", playerScore); // new # 1
                    // swap the other scores
                    editor.putInt("score2", scoreList.get(0)); // 1 -> 2
                    editor.putInt("score3", scoreList.get(1)); // 2 -> 3
                    editor.putInt("score4", scoreList.get(2)); // 3 -> 4
                    editor.putInt("score5", scoreList.get(3)); // 4 -> 5
                    editor.commit(); //save
                }
                // # 2
                else if (i == 1){
                    editor.putInt("score2", playerScore); // new # 2
                    // swap the other scores
                    editor.putInt("score3", scoreList.get(1)); // 2 -> 3
                    editor.putInt("score4", scoreList.get(2)); // 3 -> 4
                    editor.putInt("score5", scoreList.get(3)); // 4 -> 5
                    editor.commit(); //save
                }
                // # 3
                else if (i == 2){
                    editor.putInt("score3", playerScore); // new # 3
                    // swap the other scores
                    editor.putInt("score4", scoreList.get(2)); // 3 -> 4
                    editor.putInt("score5", scoreList.get(3)); // 4 -> 5
                    editor.commit(); //save
                }
                // # 4
                else if (i == 3){
                    editor.putInt("score4", playerScore); // new # 4
                    // swap the other scores
                    editor.putInt("score5", scoreList.get(3)); // 4 -> 5
                    editor.commit(); //save
                }
                // # 5
                else {
                    editor.putInt("score5", playerScore); // new # 5
                    editor.commit(); //save
                }
                break;
            }
        }

        // if a high score was not achieved
        if (!rankSet){
            rankText.setText(noRank); // set the rank text
        }

        // set full screen sticky immersive
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
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