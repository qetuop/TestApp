package com.softtec.testapp;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by brian on 10/4/15.
 */
public class Routine {
    private ArrayList<Exercise> mExerciseList;
    private Date date;

    public Routine() {
        mExerciseList = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        mExerciseList.add(exercise);
    }

    public ArrayList<Exercise> getExerciseList() {
        return mExerciseList;
    }
}
