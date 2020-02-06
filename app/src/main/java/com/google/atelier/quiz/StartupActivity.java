package com.google.atelier.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;


public class StartupActivity extends AppCompatActivity {

    public static final int TIMEOUT = 1000;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler startHandler = new Handler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        startHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent playIntent = new Intent(StartupActivity.this, PlayActivity.class);
                startActivity(playIntent);
                finish();
            }
        }, null, TIMEOUT);

    }
}
