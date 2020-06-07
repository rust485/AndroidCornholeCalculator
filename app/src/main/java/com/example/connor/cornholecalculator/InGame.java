package com.example.connor.cornholecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Connor on 5/11/2016.
 */
public class InGame extends AppCompatActivity
{
    private static final String START_VAL = "0";
    private BagsGame g = new BagsGame();

    private TextView p1Score;
    private TextView p2Score;

    private EditText p1InHole;
    private Button p1InHolePlus;
    private Button p1InHoleMinus;
    private EditText p1onBoard;
    private Button p1OnBoardPlus;
    private Button p1OnBoardMinus;

    private EditText p2InHole;
    private Button p2InHolePlus;
    private Button p2InHoleMinus;
    private EditText p2onBoard;
    private Button p2OnBoardPlus;
    private Button p2OnBoardMinus;

    private Button calculateScoreBtn;
    private TextView roundDisp;
    private int roundNum;
    private TextView error;


    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game);
        Intent intent = getIntent();
        ArrayList<String> playerNames = intent.getStringArrayListExtra(GameSetup.PLAYERS);

        g.addAllPlayers(playerNames);

        // set bags positions changers binding
        p1InHole = (EditText) findViewById(R.id.player1InHole);
        p1InHolePlus = (Button) findViewById(R.id.btnP1PlusInHole);
        p1InHoleMinus = (Button) findViewById(R.id.btnP1MinusInHole);
        p1onBoard = (EditText) findViewById(R.id.player1OnBoard);
        p1OnBoardPlus = (Button) findViewById(R.id.btnP1PlusOnBoard);
        p1OnBoardMinus = (Button) findViewById(R.id.btnP1MinusOnBoard);

        p2InHole = (EditText) findViewById(R.id.player2InHole);
        p2InHolePlus = (Button) findViewById(R.id.btnP2PlusInHole);
        p2InHoleMinus = (Button) findViewById(R.id.btnP2MinusInHole);
        p2onBoard = (EditText) findViewById(R.id.player2OnBoard);
        p2OnBoardPlus = (Button) findViewById(R.id.btnP2PlusOnBoard);
        p2OnBoardMinus = (Button) findViewById(R.id.btnP2MinusOnBoard);


        // set click listeners for player 1 bags positions changers
        p1InHolePlus.setOnClickListener(p1PlusInHole);
        p1InHoleMinus.setOnClickListener(p1MinusInHole);
        p1OnBoardPlus.setOnClickListener(p1PlusOnBoard);
        p1OnBoardMinus.setOnClickListener(p1MinusOnBoard);

        // set click listeners for player 2 bags positions changers
        p2InHolePlus.setOnClickListener(p2PlusInHole);
        p2InHoleMinus.setOnClickListener(p2MinusInHole);
        p2OnBoardPlus.setOnClickListener(p2PlusOnBoard);
        p2OnBoardMinus.setOnClickListener(p2MinusOnBoard);

        // set scoreboards
        p1Score = (TextView) findViewById(R.id.p1Score);
        p2Score = (TextView) findViewById(R.id.p2Score);

        calculateScoreBtn = (Button) findViewById(R.id.calculateScore);
        calculateScoreBtn.setOnClickListener(calculateScoreListener);

        roundDisp = (TextView) findViewById(R.id.roundNum);
        roundNum = 1;

        // set player names at the top of each side
        TextView p1Name = (TextView) findViewById(R.id.p1Name);
        TextView p2Name = (TextView) findViewById(R.id.p2Name);
        p1Name.setText(g.getPlayers().get(0).getName());
        p2Name.setText(g.getPlayers().get(1).getName());

        TextView error = (TextView) findViewById(R.id.errorBox);

    }

    protected void onStart ()
    {
        super.onStart();
    }

    protected void onResume ()
    {
        super.onResume();
    }

    @Override
    protected void onStop ()
    {
        super.onStop();

    }
    // Listener to calculate the score in this round
    private OnClickListener calculateScoreListener = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            // pull the entered bags in hole and on board for both players
            Player p1 = g.getPlayers().get(0);
            Player p2 = g.getPlayers().get(1);
            p1.setInHole(Integer.parseInt(p1InHole.getText().toString()));
            p1.setOnBoard(Integer.parseInt(p1onBoard.getText().toString()));
            p2.setInHole(Integer.parseInt(p2InHole.getText().toString()));
            p2.setOnBoard(Integer.parseInt(p2onBoard.getText().toString()));

            try
            {
                g.calculateScore();

                String scoreOne = String.format("%02d", p1.getScore());
                String scoreTwo = String.format("%02d", p2.getScore());
                p1Score.setText(scoreOne);
                p2Score.setText(scoreTwo);

                p1InHole.setText(START_VAL);
                p2InHole.setText(START_VAL);
                p1onBoard.setText(START_VAL);
                p2onBoard.setText(START_VAL);

                roundNum++;

                String round = "Round " + roundNum;
                roundDisp.setText(round);

                // if the error message was recently revealed, get rid of the error message if
                // the problem was fixed
                if (error.getVisibility() == View.VISIBLE)
                    error.setVisibility(View.INVISIBLE);
            } catch (IllegalStateException e)
            {
                error.setText("ERROR: In hole plus on board must add up to 4");
                error.setVisibility(View.VISIBLE);
            }
        }
    };




    // ---------------------------------------------------------------------------------------------
    //                     CLICK LISTENERS FOR PLUS/MINUS BAGS ONBOARD/INHOLE
    // ---------------------------------------------------------------------------------------------
    private OnClickListener p1PlusInHole = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            String inHole = p1InHole.getText().toString();
            int temp = Integer.parseInt(inHole);
            temp++;
            p1InHole.setText(Integer.valueOf(temp).toString());
        }
    };

    private OnClickListener p1MinusInHole = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            String inHole = p1InHole.getText().toString();
            int temp = Integer.parseInt(inHole);
            temp--;
            p1InHole.setText(Integer.valueOf(temp).toString());
        }
    };

    private OnClickListener p1MinusOnBoard = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            String onBoard = p1onBoard.getText().toString();
            int temp = Integer.parseInt(onBoard);
            temp--;
            p1onBoard.setText(Integer.valueOf(temp).toString());
        }
    };


    private OnClickListener p1PlusOnBoard = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            String onBoard = p1onBoard.getText().toString();
            int temp = Integer.parseInt(onBoard);
            temp++;
            p1onBoard.setText(Integer.valueOf(temp).toString());
        }
    };

    private OnClickListener p2PlusInHole = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            String inHole = p2InHole.getText().toString();
            int temp = Integer.parseInt(inHole);
            temp++;
            p2InHole.setText(Integer.valueOf(temp).toString());
        }
    };

    private OnClickListener p2MinusInHole = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            String inHole = p2InHole.getText().toString();
            int temp = Integer.parseInt(inHole);
            temp--;
            p2InHole.setText(Integer.valueOf(temp).toString());
        }
    };

    private OnClickListener p2PlusOnBoard = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            String onBoard = p2onBoard.getText().toString();
            int temp = Integer.parseInt(onBoard);
            temp++;
            p2onBoard.setText(Integer.valueOf(temp).toString());
        }
    };

    private OnClickListener p2MinusOnBoard = new OnClickListener()
    {
        @Override
        public void onClick (View v)
        {
            String onBoard = p2onBoard.getText().toString();
            int temp = Integer.parseInt(onBoard);
            temp--;
            p2onBoard.setText(Integer.valueOf(temp).toString());
        }
    };
}
