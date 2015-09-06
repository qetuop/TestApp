package com.softtec.testapp;

/**
 * Created by Brian on 8/13/2015.
 */
public class Exercise {
    private long id;
    private String exerciseName;

    public Exercise() {
    }

    public Exercise(String name) {
        setExerciseName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return exerciseName;
    }
}
