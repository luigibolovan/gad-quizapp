package com.google.atelier.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.google.atelier.quiz.QuizActivity.ACTIVITY_ID;
import static com.google.atelier.quiz.QuizActivity.ACTIVITY_ID_KEY;

public class PlayActivity extends AppCompatActivity {

    private boolean isSwitched                      =      false;
    public static final String SWITCH_BUTTON        =       "White theme switch";
    public static final String SHARED_PREFERENCES   =       "SharedPreferences";
    public static final String HIGHSCORE_SP         =       "Highscore";
    public static final String LASTSCORE_SP         =       "Lastscore";
    public static final String SWITCH_SP            =       "Switch";
    TextView highscoreValueTextView;
    TextView lastScoreValueTextView;
    Switch darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        highscoreValueTextView = findViewById(R.id.inp_highscore);
        lastScoreValueTextView = findViewById(R.id.inp_lastscore);
        int highscore          = Integer.parseInt((getScores(HIGHSCORE_SP)));
        int lastScore          = Integer.parseInt((getScores(LASTSCORE_SP)));
        boolean isWhiteTheme   = isWhite();
        if(isWhiteTheme){
            setWhiteTheme();
        }else{
            setBlackTheme();
        }


        Intent endIntent = getIntent();
        if (endIntent != null) {
            highscore = endIntent.getIntExtra(QuizActivity.HIGHSCORE_KEY, highscore);
            lastScore = endIntent.getIntExtra(QuizActivity.LASTSCORE_KEY, lastScore);

            highscoreValueTextView.setText("");
            highscoreValueTextView.setText("" + highscore);
            lastScoreValueTextView.setText("");
            lastScoreValueTextView.setText("" + lastScore);
            saveScores();
            saveTheme();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        darkModeSwitch = (Switch)findViewById(R.id.switchBtn);
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "White theme " + (isChecked ? "on" : "off"), Toast.LENGTH_SHORT).show();
                if(isChecked)
                {
                    setWhiteTheme();
                    saveTheme();
                }
                else
                {
                    setBlackTheme();
                    saveTheme();
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

    private void setBlackTheme() {
        RelativeLayout myRelativeLayout     =   (RelativeLayout) findViewById(R.id.playLayout);
        TextView appTitle                   =   (TextView)findViewById(R.id.title);
        TextView highScoreTxt               =   (TextView)findViewById(R.id.txt_highscore);
        TextView highScoreValue             =   (TextView)findViewById(R.id.inp_highscore);
        TextView lastScoreTxt               =   (TextView)findViewById(R.id.txt_lastscore);
        TextView lastScoreValue             =   (TextView)findViewById(R.id.inp_lastscore);

        myRelativeLayout.setBackgroundColor(Color.argb(255, 0, 0, 0));
        appTitle.setTextColor(Color.argb(255, 255, 255, 255));
        highScoreTxt.setTextColor(Color.argb(255,255,255,255));
        highScoreValue.setTextColor((Color.argb(255, 255, 255, 255)));
        lastScoreTxt.setTextColor(Color.argb(255, 255, 255, 255));
        lastScoreValue.setTextColor(Color.argb(255, 255, 255, 255));
        isSwitched = false;
    }

    private void setWhiteTheme() {
        RelativeLayout myRelativeLayout     = (RelativeLayout) findViewById(R.id.playLayout);
        TextView appTitle                   = (TextView) findViewById(R.id.title);
        TextView highScoreTxt               = (TextView) findViewById(R.id.txt_highscore);
        TextView highScoreValue             = (TextView) findViewById(R.id.inp_highscore);
        TextView lastScoreTxt               = (TextView) findViewById(R.id.txt_lastscore);
        TextView lastScoreValue             = (TextView) findViewById(R.id.inp_lastscore);
        Switch whiteThemeSwitch             = (Switch) findViewById(R.id.switchBtn);

        myRelativeLayout.setBackgroundColor(Color.argb(255, 255, 255, 255));
        appTitle.setTextColor(Color.argb(255, 0, 0, 0));
        highScoreTxt.setTextColor(Color.argb(255, 0, 0, 0));
        highScoreValue.setTextColor((Color.argb(255, 0, 0, 0)));
        lastScoreTxt.setTextColor(Color.argb(255, 0, 0, 0));
        lastScoreValue.setTextColor(Color.argb(255, 0, 0, 0));
        whiteThemeSwitch.setChecked(true);
        isSwitched = true;
    }

    private void saveScores(){
        SharedPreferences sharedPrefs   = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString(HIGHSCORE_SP, highscoreValueTextView.getText().toString());
        editor.putString(LASTSCORE_SP, lastScoreValueTextView.getText().toString());
        editor.apply();
    }

    private void saveTheme(){
        SharedPreferences sharedPrefs   =   getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =   sharedPrefs.edit();
        editor.putBoolean(SWITCH_SP, isSwitched);
        editor.apply();
    }

    private String getScores(String key){
        SharedPreferences sharedPrefs   =   getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        return sharedPrefs.getString(key,"0");
    }

    private boolean isWhite(){
        SharedPreferences sharedPrefs   =   getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        return sharedPrefs.getBoolean(SWITCH_SP, false);
    }


}
