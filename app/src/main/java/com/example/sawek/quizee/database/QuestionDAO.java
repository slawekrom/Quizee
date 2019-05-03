package com.example.sawek.quizee.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface QuestionDAO {

    @Insert
    void insert(Question question);

    @Query("DELETE FROM QUESTION")
    void deleteAll();

    @Insert
    void insertAll(List<Question> questions);

    @Query("SELECT * FROM QUESTION c WHERE c.category LIKE :search")
    List<Question> searchQuestion(String search);

    @Delete
    void delete(Question question);

    @Query("SELECT * from QUESTION ORDER BY question ASC")
    List<Question> getAllQuestions();
    @Update
    void update(Question question);
}

