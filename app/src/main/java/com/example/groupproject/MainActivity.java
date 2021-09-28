package com.example.groupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        // hide the action and status bar from
        // the user. Keep the navigation bar so
        // they can sill exit out of the game
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

    }

    // When play button is clicked, go to game screen
    public void onclickGameStart(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
}