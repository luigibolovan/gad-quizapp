package com.google.atelier.quiz;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class QuestionCollector {

    private List<Question> mQuestionList = new ArrayList<>();

    List<Question> fetchQuestions(Context appContext)
    {
        AssetManager mAssetManager;
        InputStream assetStream;
        int lineIndex                   =       0;
        String question                 =       null;
        String option1                  =       null;
        String option2                  =       null;
        String option3                  =       null;
        String option4                  =       null;
        String answer                   =       null;
        String input;

        mAssetManager = appContext.getAssets();
        try {
            assetStream = mAssetManager.open("questions.dat");
            BufferedReader qBufferedReader = new BufferedReader(new InputStreamReader(assetStream));
            while ((input = qBufferedReader.readLine()) != null){
                switch (lineIndex % 6) {
                    case 0:
                        question = input;
                        break;
                    case 1:
                        option1 = input;
                        break;
                    case 2:
                        option2 = input;
                        break;
                    case 3:
                        option3 = input;
                        break;
                    case 4:
                        option4 = input;
                        break;
                    case 5:
                        answer = input;
                        break;
                    default:
                        break;
                }
                if ((lineIndex % 6 == 5)) {
                    mQuestionList.add(new Question(question, option1, option2, option3, option4, answer));
                }
                lineIndex++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return mQuestionList;
    }
}
