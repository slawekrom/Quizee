package com.example.sawek.quizee.database;

import android.arch.persistence.room.*;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.*;

@Entity(tableName = "QUESTION")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String category;
    private String correctAnswer;

    public Question() {
    }

    public Question(long id, String question, String answer1, String answer2, String answer3,
                    String answer4, String category, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.category=category;
        this.correctAnswer=correctAnswer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question(String question, String answer1, String answer2, String answer3
            , String answer4,String category,String correctAnswer) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;

        this.answer3 = answer3;
        this.answer4 = answer4;
        this.category=category;
        this.correctAnswer=correctAnswer;
    }

    public String getQuestion() {
        return question;
    }


    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
}

