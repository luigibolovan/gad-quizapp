package com.google.atelier.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Executors;

import static com.google.atelier.quiz.PlayActivity.SWITCH_BUTTON;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private Button nextButton;
    private TextView questionStringNo;
    private int questionNumber = 1;
    private TextView correctAnsTextView;
    private TextView wrongAnsTextView;
    private boolean selectedOption;

    private QuizDatabase mQuizDatabase;
    private List<Question> mAllQuestions;
    private int questionListSize;
    private int correctAnswersCounter;
    private int wrongAnswersCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        boolean isSwitchedWhite = false;
        Intent mIntent = getIntent();
        if (mIntent != null) {
            isSwitchedWhite = mIntent.getBooleanExtra(SWITCH_BUTTON, false);
        }
        if(isSwitchedWhite) {
            setWhiteTheme();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        questionTextView        =   findViewById(R.id.question);
        option1                 =   findViewById(R.id.option1);
        option2                 =   findViewById(R.id.option2);
        option3                 =   findViewById(R.id.option3);
        option4                 =   findViewById(R.id.option4);
        nextButton              =   findViewById(R.id.nextBtn);
        questionStringNo        =   findViewById(R.id.idx_question);
        correctAnsTextView      =   findViewById(R.id.correctAnsCounter);
        wrongAnsTextView        =   findViewById(R.id.wrongAnsCounter);

        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mQuizDatabase = QuizDatabase.getAppInstance(getApplicationContext());
                mAllQuestions = mQuizDatabase.questionDao().getAllQuestions();
                handleQuestions((int)(Math.random() * mAllQuestions.size()));
            }
        });
    }


    private void showQuestion(Question question) {

        questionTextView.setText(question.getMQuestion());
        option1.setText(question.getMOption1());
        option2.setText(question.getMOption2());
        option3.setText(question.getMOption3());
        option4.setText(question.getMOption4());
    }


    private void setWhiteTheme() {
        RelativeLayout quizLayout   =   (RelativeLayout)findViewById(R.id.quizLayout);
        TextView countdown          =   (TextView)findViewById(R.id.timer);
        TextView question           =   (TextView)findViewById(R.id.question);

        quizLayout.setBackgroundColor(Color.argb(255,255,255,255));
        countdown.setTextColor(Color.argb(255, 105, 105, 105));
        question.setTextColor(Color.argb(255, 105, 105, 105));
    }

    private void setSelectedOptionTrue() {
        selectedOption = true;
    }

    private void setSelectedOptionFalse() {
        selectedOption = false;
    }

    private boolean isOptionSelected(){
        return selectedOption;
    }

    private void handleQuestions(int questionIndex){
        if(mAllQuestions != null){
            handleQuestion(mAllQuestions.get(questionIndex));
        }
    }

    private void handleQuestion(Question question){
        showQuestion(question);
        handleOptions(question);
    }

    private void handleOptions(final Question question){
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option1.setBackgroundResource(R.drawable.option_button_clicked);
                option2.setBackgroundResource(R.drawable.option_button);
                option3.setBackgroundResource(R.drawable.option_button);
                option4.setBackgroundResource(R.drawable.option_button);
                setSelectedOptionTrue();
                handleNext(question, option1.getText().toString());
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option2.setBackgroundResource(R.drawable.option_button_clicked);
                option1.setBackgroundResource(R.drawable.option_button);
                option3.setBackgroundResource(R.drawable.option_button);
                option4.setBackgroundResource(R.drawable.option_button);
                setSelectedOptionTrue();
                handleNext(question, option2.getText().toString());
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option3.setBackgroundResource(R.drawable.option_button_clicked);
                option2.setBackgroundResource(R.drawable.option_button);
                option1.setBackgroundResource(R.drawable.option_button);
                option4.setBackgroundResource(R.drawable.option_button);
                setSelectedOptionTrue();
                handleNext(question, option3.getText().toString());
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option4.setBackgroundResource(R.drawable.option_button_clicked);
                option2.setBackgroundResource(R.drawable.option_button);
                option3.setBackgroundResource(R.drawable.option_button);
                option1.setBackgroundResource(R.drawable.option_button);
                setSelectedOptionTrue();
                handleNext(question, option4.getText().toString());
            }
        });
    }

    private void handleNext(final Question question, final String selectedAns){

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOptionSelected()){
                    Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                }else{
                    questionNumber++;
                    questionStringNo.setText(Integer.toString(questionNumber));
                    if (question.getMAnswer().equals(selectedAns)) {
                        correctAnswersCounter++;
                        correctAnsTextView.setText(Integer.toString(correctAnswersCounter));
                    } else {
                        wrongAnswersCounter++;
                        wrongAnsTextView.setText(Integer.toString(wrongAnswersCounter));

                    }
                    if (mAllQuestions != null) {
                        option4.setBackgroundResource(R.drawable.option_button);
                        option2.setBackgroundResource(R.drawable.option_button);
                        option3.setBackgroundResource(R.drawable.option_button);
                        option1.setBackgroundResource(R.drawable.option_button);
                        setSelectedOptionFalse();
                        handleQuestions((int) (Math.random() * mAllQuestions.size()));
                    }
                }
            }
        });
    }
}