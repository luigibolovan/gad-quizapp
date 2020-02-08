package com.google.atelier.quiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM Questions")
    List<Question> getAllQuestions();

    @Insert
    void insertAll(List<Question> questions);

}
