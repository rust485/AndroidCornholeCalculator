package com.example.connor.cornholecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.View.*;
import android.widget.TextView;

/**
 * Created by Connor on 5/11/2016.
 */
public class MainActivity extends AppCompatActivity
{
    private TextView startGame = (TextView) findViewById(R.id.mainStartGame);
    private TextView openSettings = (TextView) findViewById(R.id.goToSettings);

    private void goToGameSetup()
    {
        Intent intent = new Intent(this, GameSetup.class);
        startActivity(intent);
    }

    private OnClickListener startGameListner = new OnClickListener() {
        @Override
        public void onClick (View v)
        {
            goToGameSetup();
        }
    };

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        startGame.setOnClickListener(startGameListner);
    }

    @Override
    protected void onPause ()
    {
        super.onPause();
    }
}
