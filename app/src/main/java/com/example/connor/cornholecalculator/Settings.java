package com.example.connor.cornholecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.*;

/**
 * Created by Connor on 5/11/2016.
 */
public class Settings extends AppCompatActivity
{
    private EditText maxScoreTxt;
    private EditText resetScoreTxt;
    private Button save;

    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
        maxScoreTxt = (EditText) findViewById(R.id.score_to_play_to);
        resetScoreTxt = (EditText) findViewById(R.id.score_to_reset_to);
        save.setOnClickListener(saveInput);
    }

    protected void saveSettings ()
    {
        int maxScore = Integer.parseInt(maxScoreTxt.getText().toString());
        int resetScore = Integer.parseInt((resetScoreTxt.getText().toString()));

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private OnClickListener saveInput = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            saveSettings();
        }
    };
}
