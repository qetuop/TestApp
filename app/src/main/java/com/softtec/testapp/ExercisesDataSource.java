package com.softtec.testapp;

/**
 * Created by Brian on 8/13/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ExercisesDataSource {

    // Database fields
    private SQLiteDatabase database;
    private ExercisesSQLiteHelper dbHelper;
    private String[] allColumns = {
            ExercisesSQLiteHelper.COLUMN_ID,
            ExercisesSQLiteHelper.COLUMN_EXERCISE_NAME,
            ExercisesSQLiteHelper.COLUMN_EXERCISE_TYPE
    };

    public ExercisesDataSource(Context context) {
        dbHelper = new ExercisesSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Exercise createExercise(Exercise exercise) {
        ContentValues values = new ContentValues();
        values.put(ExercisesSQLiteHelper.COLUMN_EXERCISE_NAME, exercise.getExerciseName());
        values.put(ExercisesSQLiteHelper.COLUMN_EXERCISE_TYPE, exercise.getExerciseType());

        long insertId = database.insert(ExercisesSQLiteHelper.TABLE_EXERCISES, null, values);
        Cursor cursor = database.query(ExercisesSQLiteHelper.TABLE_EXERCISES,
                allColumns,
                ExercisesSQLiteHelper.COLUMN_ID + " = " + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Exercise newExercise = cursorToExercise(cursor);
        cursor.close();
        return newExercise;
    }

    public void deleteExercise(Exercise Exercise) {
        long id = Exercise.getId();
        System.out.println("Exercise deleted with id: " + id);
        database.delete(ExercisesSQLiteHelper.TABLE_EXERCISES, ExercisesSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> Exercises = new ArrayList<Exercise>();

        Cursor cursor = database.query(ExercisesSQLiteHelper.TABLE_EXERCISES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exercise Exercise = cursorToExercise(cursor);
            Exercises.add(Exercise);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Exercises;
    }

    // ?convert a "ptr/cursor" of a table entry to an Exercise Object
    private Exercise cursorToExercise(Cursor cursor) {
        Exercise Exercise = new Exercise();
        Exercise.setId(cursor.getLong(0));
        Exercise.setExerciseName(cursor.getString(1));
        Exercise.setExerciseType(cursor.getString(2));

        return Exercise;
    }

    public void clear() {
        for (Exercise exercise : getAllExercises()) {
            deleteExercise(exercise);
        }
    }

}

