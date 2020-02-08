package com.google.atelier.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executors;

public class PlayActivity extends AppCompatActivity {

    private boolean isSwitched                  =      false;
    public static final String SWITCH_BUTTON    =      "White theme switch";
    public static final String QUESTION_LIST    =      "Question list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Switch darkModeSwitch = (Switch)findViewById(R.id.switchBtn);

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "White theme " + (isChecked ? "on" : "off"), Toast.LENGTH_SHORT).show();
                if(isChecked)
                {
                    RelativeLayout myRelativeLayout     =   (RelativeLayout) findViewById(R.id.playLayout);
                    TextView appTitle                   =   (TextView)findViewById(R.id.title);
                    TextView highScoreTxt               =   (TextView)findViewById(R.id.txt_highscore);
                    TextView highScoreValue             =   (TextView)findViewById(R.id.inp_highscore);
                    TextView lastScoreTxt               =   (TextView)findViewById(R.id.txt_lastscore);

                    myRelativeLayout.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    appTitle.setTextColor(Color.argb(255, 0, 0, 0));
                    highScoreTxt.setTextColor(Color.argb(255,0,0,0));
                    highScoreValue.setTextColor((Color.argb(255, 0, 0, 0)));
                    lastScoreTxt.setTextColor(Color.argb(255, 0, 0, 0));
                    isSwitched = isChecked;
                }
                else
                {
                    RelativeLayout myRelativeLayout     =   (RelativeLayout) findViewById(R.id.playLayout);
                    TextView appTitle                   =   (TextView)findViewById(R.id.title);
                    TextView highScoreTxt               =   (TextView)findViewById(R.id.txt_highscore);
                    TextView highScoreValue             =   (TextView)findViewById(R.id.inp_highscore);
                    TextView lastScoreTxt               =   (TextView) findViewById(R.id.txt_lastscore);

                    myRelativeLayout.setBackgroundColor(Color.argb(255, 0, 0, 0));
                    appTitle.setTextColor(Color.argb(255, 255, 255, 255));
                    highScoreTxt.setTextColor(Color.argb(255,255,255,255));
                    highScoreValue.setTextColor((Color.argb(255, 255, 255, 255)));
                    lastScoreTxt.setTextColor(Color.argb(255, 255, 255, 255));
                    isSwitched = isChecked;
                }
            }
        });

        ImageView playButton = (ImageView)findViewById(R.id.i_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starter = new Intent(PlayActivity.this, QuizActivity.class);
                starter.putExtra(SWITCH_BUTTON, isSwitched);
                startActivity(starter);
            }
        });
    }
}
