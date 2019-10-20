package com.midterm.lasalle.tic_tac_toe_game;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    //Splash Activity
    //This is the first screen that shows when we open a Tic Tac Toe.I created a lunch screen to display sone kind progress before application setup complete.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // I use a Handler class.The main function of handler is to handle a screen for a specific time interval.So, my splash screen will be show for 3 seconds.
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                // Here I use Intent that will start a next Activity after 3 seconds.(jump from Splash Activity to MainPage activity).
                Intent intent = new Intent(Splash.this, MainPage.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
