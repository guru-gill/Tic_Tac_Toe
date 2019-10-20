package com.midterm.lasalle.tic_tac_toe_game;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Homepage extends AppCompatActivity implements View.OnClickListener {
    //Declaring variables
    //Declaring array for buttons
    Button[][] button_clicks_array = new Button[3][3];
    boolean p1_Turn = true;
    //variables to store result of player 1 and 2
    int total_rounds,p1_score,p2_score;
    TextView player1_score,player2_score;
    Button resetgame;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //calling function
        provide_ids();
    }

    private void provide_ids()
    {
        //typecasting textviews and button into java code with Ids
        player1_score = findViewById(R.id.p1_score);
        player2_score = findViewById(R.id.p2_score);
        resetgame = findViewById(R.id.game_reset);

        //using for loop to converting all buttons to java code

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                //providing Ids to buttons.
                String buttonID = "click_btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                button_clicks_array[i][j] = findViewById(resID);
                button_clicks_array[i][j].setOnClickListener(this);
            }
        }

        //setting clickListener on reset Button
        resetgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Calling Reset function
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        //calling condition_checks function
      condition_checks(v);
    }

    void condition_checks(View v) {

           //Getting data from Intent that we stored in previous activity
            Intent intent = getIntent();
            String player1=intent.getStringExtra("player1");
            String player2=intent.getStringExtra("player2");

            //If condition to checking empty string
            if (!((Button) v).getText().toString().equals(""))
            {
                return;
            }

            if (p1_Turn)
            {
                //setting player 1 symbol
                ((Button) v).setText(player1);
            } else {
                //setting player 2 symbol
                ((Button) v).setText(player2);
            }

            total_rounds++;

            //If else condition to show the winner.
            if (checkForWinPlayer()) {
                if (p1_Turn) {
                    //if player 1 wins a game,then call Player1Winner function
                   player1Winner();
                } else {
                    //otherwise, calls a player2Function
                player2Winner();
                }
            } else if (total_rounds == 9) {
                //total_rounds means when the board is full, calls a draw function because no one can win.
           draw();
            }
            else {
                p1_Turn = !p1_Turn;
            }
        }


        //creating a function to check which player win a game.This is the main function where we are checking each column,row and
        //diagonally  for X and 0.
    boolean checkForWinPlayer() {
        String[][] field = new String[3][3];

        //This for loop is used to get the data from the buttons.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = button_clicks_array[i][j].getText().toString();
            }
        }
        //This for loop is used to check the rows whether whose are equal or not
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        //for loop used to check columns whether equal or not.

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }



        //checking for diagonals whether are equal or not
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }
     void player1Winner() {
        //Function Player 1
        p1_score++;
        //calling function to show score of player 1
         showscore();
         //creating dialog box to show a message for player 1
         final Dialog dialog = new Dialog(Homepage.this);
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
         dialog.setCancelable(false);
         dialog.setContentView(R.layout.cust_dialog);

         TextView text = (TextView) dialog.findViewById(R.id.txt_dia);

         //setting message on dialog box for winning player 1.
         text.setText("congratulations!!!\n\nPlayer 1 Wins");


         //Setting button to cancel dialog box
         Button dialogButton = (Button) dialog.findViewById(R.id.btn_yes);
         dialogButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //calling reset function to clear board.
                 resetBoard();
                 dialog.dismiss();

             }
         });
         dialog.show();


    }

     void player2Winner() {
        p2_score++;
        //calling function to update score of player 2
         showscore();
         //Dialog box for player 2
         final Dialog dialog = new Dialog(Homepage.this);
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
         dialog.setCancelable(false);
         dialog.setContentView(R.layout.cust_dialog);

         TextView text = (TextView) dialog.findViewById(R.id.txt_dia);

         text.setText("congratulations!!!\n\nPlayer 2 Wins");


         Button dialogButton = (Button) dialog.findViewById(R.id.btn_yes);
         dialogButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //resetting board
                 resetBoard();
                 dialog.dismiss();

             }
         });
         dialog.show();
    }

    void draw() {
        //draw function calls when we dont have a any result.
        showscore();
        final Dialog dialog = new Dialog(Homepage.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cust_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.txt_dia);

        text.setText("congratulations!!!\n\n  Draw!");


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_yes);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
                dialog.dismiss();

            }
        });
        dialog.show();
    }
    private void  showscore()
    {
        //displaying player's score
        player1_score.setText("Player1 score: " + p1_score);
        player2_score.setText("Player2 score: " + p2_score);
    }

    private void resetBoard() {
        //This function is used to clear all the buttons.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button_clicks_array[i][j].setText("");
            }
        }

        total_rounds = 0;
        p1_Turn = true;
    }

    private void resetGame()
    {
        //ResetGame function is used to restart a game
        p1_score = 0;
        p2_score = 0;
        showscore();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        //This is the override method used to save the state of the game whenever we rotate a phone.
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", total_rounds);
        outState.putInt("player1Points", p1_score);
        outState.putInt("player2Points", p2_score);
        outState.putBoolean("player1Turn", p1_Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //This override method used to restore the saved state.
        super.onRestoreInstanceState(savedInstanceState);

        total_rounds = savedInstanceState.getInt("roundCount");
        p1_score = savedInstanceState.getInt("player1Points");
        p2_score = savedInstanceState.getInt("player2Points");
        p1_Turn = savedInstanceState.getBoolean("player1Turn");
    }

}
