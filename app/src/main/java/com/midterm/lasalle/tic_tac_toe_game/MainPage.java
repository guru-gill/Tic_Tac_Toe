package com.midterm.lasalle.tic_tac_toe_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;

public class MainPage extends AppCompatActivity {

    //Declaring Variables.
    //In this Activity I use four CheckBox and one Button.
    //I used checkBox to choose X and 0 by both players and button to start a game.
    public CheckBox P1X, P1O, P2X, P2O;
    boolean player1ax = true;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        intialize();

        //Setting click on listner on checkbox.
        P1X.setOnClickListener(checkboxClickListener);
        P1O.setOnClickListener(checkboxClickListener);
        P2X.setOnClickListener(checkboxClickListener);
        P2O.setOnClickListener(checkboxClickListener);


        //I have set values by defualt to true.(X of player 1 and 0 of Player 2)
        P1X.setChecked(true);
        P2O.setChecked(true);


        //setting clickOnListener on Start Button .
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Calling startTictacToe function to move to next activity.
               startTicTacToe();
            }
        });

    }

    public void intialize()
    {
        //This function is used to type casting widgets(checkBox,Button) into java code.
        P1X = (CheckBox) findViewById(R.id.player1x);
        P1O = (CheckBox) findViewById(R.id.player1o);
        P2X = (CheckBox) findViewById(R.id.player2x);
        P2O = (CheckBox) findViewById(R.id.player2o);
        start=(Button)findViewById(R.id.start);
    }

    View.OnClickListener checkboxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            boolean checked = ((CheckBox) view).isChecked();
            if (checked) {
                switch (view.getId())
                {
                    //used switch statement to check which player select X or 0.
                    case R.id.player1x:
                        P1O.setChecked(false);
                        P2X.setChecked(false);
                        P2O.setChecked(true);
                        player1ax = true;
                        break;
                    case R.id.player1o:
                        P1X.setChecked(false);
                        P2O.setChecked(false);
                        P2X.setChecked(true);
                        player1ax = false;
                        break;
                    case R.id.player2x:
                        P2O.setChecked(false);
                        P1X.setChecked(false);
                        P1O.setChecked(true);
                        player1ax = false;
                        break;
                    case R.id.player2o:
                        P2X.setChecked(false);
                        P1O.setChecked(false);
                        P1X.setChecked(true);
                        player1ax = true;
                        break;
                }
            } else
                {
                switch (view.getId())
                {
                    case R.id.player1x:
                        P1O.setChecked(true);
                        P2X.setChecked(true);
                        P2O.setChecked(false);
                        player1ax = false;
                        break;
                    case R.id.player1o:
                        P1X.setChecked(true);
                        P2O.setChecked(true);
                        P2X.setChecked(false);
                        player1ax = true;
                        break;
                    case R.id.player2x:
                        P2O.setChecked(true);
                        P1X.setChecked(true);
                        P1O.setChecked(false);
                        player1ax = true;
                        break;
                    case R.id.player2o:
                        P2X.setChecked(true);
                        P1O.setChecked(true);
                        P1X.setChecked(false);
                        player1ax = false;
                        break;
                }
            }
        }
    };

    public void startTicTacToe()
    {
        String player1,player2;
        //If Else condition to check whether player 1 select X or 0.and also same for player 2.
        if(player1ax)
        {
            player1="X";
            player2="O";
            //Intent to start HomePage Acivity
            Intent i = new Intent(this, Homepage.class);
            //sending a data using Intent to next Activity
            i.putExtra("player1", player1);
            i.putExtra("player2",player2);
            startActivity(i);
        }else
        {
            player1="O";
            player2="X";
            Intent i = new Intent(this, Homepage.class);
            i.putExtra("player1", player1);
            i.putExtra("player2",player2);
            startActivity(i);
        }

    }
}


