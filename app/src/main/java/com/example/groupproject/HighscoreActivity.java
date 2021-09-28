package com.example.groupproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HighscoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // set full screen sticky immersive
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        // string array to retrieve high score rank numbers
        String[] numList = getResources().getStringArray(R.array.string_array_highscore_numbers);
        // array adapter to populate the list view
        ArrayAdapter<String> arrayAdapterRanks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numList);
        // the list view
        ListView listViewNum = (ListView)findViewById(R.id.highscore_list_numbers);
        // set adapter
        listViewNum.setAdapter(arrayAdapterRanks);

        // string array to retrieve high score names
        String[] nameList = getResources().getStringArray(R.array.string_array_highscore_names);
        // array adapter to populate the list view
        ArrayAdapter<String> arrayAdapterNames = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        // the list view
        ListView listViewName = (ListView)findViewById(R.id.highscore_list_names);
        // set adapter
        listViewName.setAdapter(arrayAdapterNames);

    }

    // When Play Again button is clicked, go to game screen
    public void onclickGameStart(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
}