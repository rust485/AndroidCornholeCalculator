package com.example.connor.cornholecalculator;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.*;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Connor on 5/11/2016
 */
public class GameSetup extends AppCompatActivity
{
    public final static String PLAYERS = "com.example.connor.cornholecalculator.PLAYERS";

    private Button btnStart = (Button) findViewById(R.id.btn_start);

    private OnClickListener startListener = new View.OnClickListener()
    {
        public void onClick (View v)
        {
            startGame();
        }
    };

    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_setup);
    }

    @Override
    protected void onStart ()
    {
        super.onStart();
        btnStart.setOnClickListener(startListener);
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
    }

    @Override
    protected void onStop ()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy ()
    {
        super.onDestroy();
    }

    private void startGame ()
    {
        EditText playerOne = (EditText) findViewById(R.id.player_one_name);
        EditText playerTwo = (EditText) findViewById(R.id.player_two_name);
        String playerOneName = playerOne.getText().toString();
        String playerTwoName = playerTwo.getText().toString();
        ArrayList<String> players = new ArrayList<>();
        players.add(playerOneName);
        players.add(playerTwoName);

        Intent intent = new Intent(this, InGame.class);
        intent.putExtra(PLAYERS, players);
        startActivity(intent);
    }
}
