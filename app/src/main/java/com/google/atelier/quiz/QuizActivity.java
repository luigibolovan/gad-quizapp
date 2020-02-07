package com.google.atelier.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.google.atelier.quiz.PlayActivity.SWITCH_BUTTON;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        boolean isSwitchedWhite = false;

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            isSwitchedWhite = extra.getBoolean(SWITCH_BUTTON);
        }

        if(isSwitchedWhite) {
            RelativeLayout quizLayout = (RelativeLayout)findViewById(R.id.quizLayout);
            quizLayout.setBackgroundColor(Color.argb(255,255,255,255));

            TextView countdown = (TextView)findViewById(R.id.timer);
            countdown.setTextColor(Color.argb(255, 105, 105, 105));

            TextView question = (TextView)findViewById(R.id.question);
            question.setTextColor(Color.argb(255, 105, 105, 105));
        }
    }
}
