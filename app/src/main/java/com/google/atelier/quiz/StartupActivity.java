package com.google.atelier.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import java.util.List;
import java.util.concurrent.Executors;


public class StartupActivity extends AppCompatActivity {

    final static int WAIT_VALUE = 1000;
    private CountDownTimer startTimer = new CountDownTimer(WAIT_VALUE, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            //nothing to do
        }

        @Override
        public void onFinish() {
            Intent playIntent = new Intent(StartupActivity.this, PlayActivity.class);
            startActivity(playIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        loadQuestions(getApplicationContext());
        startTimer.start();
    }

    private void loadQuestions(final Context myContext) {
        try {
            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    final List<Question> questions = new QuestionCollector().fetchQuestions(myContext);
                    QuizDatabase.getAppInstance(myContext).clearAllTables();
                    QuizDatabase.getAppInstance(myContext).questionDao().insertAll(questions);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
