package com.google.atelier.quiz;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Questions")
public class Question{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int mQuestionId;

    @ColumnInfo(name = "question")
    private String mQuestion;

    @ColumnInfo(name = "option1")
    private String mOption1;

    @ColumnInfo(name = "option2")
    private String mOption2;

    @ColumnInfo(name = "option3")
    private String mOption3;

    @ColumnInfo(name = "option4")
    private String mOption4;

    @ColumnInfo(name = "answer")
    private String mAnswer;


    public String getMAnswer() {
        return mAnswer;
    }

    public String getMOption1() {
        return mOption1;
    }

    public String getMOption2() {
        return mOption2;
    }

    public String getMOption3() {
        return mOption3;
    }

    public String getMOption4() {
        return mOption4;
    }

    public String getMQuestion() {
        return mQuestion;
    }

    public Question(String mQuestion, String mOption1, String mOption2, String mOption3, String mOption4, String mAnswer) {
        this.mQuestion  =     mQuestion;
        this.mOption1   =     mOption1;
        this.mOption2   =     mOption2;
        this.mOption3   =     mOption3;
        this.mOption4   =     mOption4;
        this.mAnswer    =     mAnswer;
    }

}
