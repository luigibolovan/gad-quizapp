package com.google.atelier.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import java.util.List;
import java.util.concurrent.Executors;


public class StartupActivity extends AppCompatActivity {

    public static final int TIMEOUT = 1000;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        Handler startHandler = new Handler();
        waitOneSecond(startHandler);
        loadQuestions(getApplicationContext());
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void waitOneSecond(Handler startHandler) {
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
