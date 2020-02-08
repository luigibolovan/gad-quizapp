package com.google.atelier.quiz;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Question.class}, version = 1)
public abstract class QuizDatabase extends RoomDatabase {

    private static QuizDatabase APP_INSTANCE;
    private final static String DB_NAME = "questionsDB";
    public abstract QuestionDao questionDao();

    public static QuizDatabase getAppInstance(Context context){
        if(APP_INSTANCE == null)
        {
            APP_INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuizDatabase.class, DB_NAME)
                    .build();
        }
        return APP_INSTANCE;
    }
}
