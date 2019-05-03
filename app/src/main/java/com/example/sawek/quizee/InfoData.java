package com.example.sawek.quizee;

import java.util.ArrayList;
import java.util.List;

public class InfoData {
    private static InfoData instance =null;
    private int points;
    private List<String> categoriesList= new ArrayList<>();
    private List<String> categoriesInDatabse= new ArrayList<>();
    private int goodAnswer=0;
    private int badAnswer=0;

    public int getPoints() {
        return points;
    }

    private InfoData(){
        categoriesList.add("piłka nożna");
        categoriesList.add("fizyka");
        categoriesList.add("kosmos");
        categoriesList.add("stolice");
        categoriesList.add("informatyka");

        points = 100;
    }

    public int getGoodAnswer() {
        return goodAnswer;
    }

    public List<String> getCategoriesInDatabse() {
        return categoriesInDatabse;
    }

    public void setCategoriesInDatabse(List<String> categoriesInDatabse) {
        this.categoriesInDatabse = categoriesInDatabse;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGoodAnswer(int goodAnswer) {
        this.goodAnswer = goodAnswer;
    }

    public void setBadAnswer(int badAnswer) {
        this.badAnswer = badAnswer;
    }

    public int getBadAnswer() {
        return badAnswer;
    }

    public List<String> getCategoriesList() {
        return categoriesList;
    }

    public static InfoData getInstance(){
        if(instance==null){
            instance=new InfoData();
        }
        return instance;
    }
    public void addPoints(int point){
        points+=point;
    }
}
