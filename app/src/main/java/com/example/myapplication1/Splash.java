package com.example.myapplication1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;


public class Splash extends Activity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressBar pb=(ProgressBar)findViewById(R.id.progressBar);
                for(int a=0; a<=100 ;a++)
                {
                    pb.setProgress(a);

                }

                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}