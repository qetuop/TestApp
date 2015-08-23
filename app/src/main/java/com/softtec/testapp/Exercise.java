package com.softtec.testapp;

/**
 * Created by Brian on 8/13/2015.
 */
public class Exercise {
    private long id;
    private String exercise;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExerciset() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return exercise;
    }
}
