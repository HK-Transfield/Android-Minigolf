package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // *testing* auto open the game activity on start
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);

        // *testing* auto open the game OVER activity on start
        //Intent i = new Intent(this, GameOverActivity.class);
        //startActivity(i);
    }
}