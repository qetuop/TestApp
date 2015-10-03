package com.softtec.testapp;

/**
 * Created by Brian on 8/13/2015.
 */
public class Exercise {
    private long id = 0;
    private String exerciseName = null;

    // for custom row view - can it be removed?
    boolean selected = false;

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

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
