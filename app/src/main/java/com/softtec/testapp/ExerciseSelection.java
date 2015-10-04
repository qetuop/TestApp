package com.softtec.testapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ExerciseSelection extends FragmentActivity
                                implements ExerciseCreationDialog.NoticeDialogListener  {

    private ExercisesDataSource mExercisesDataSource;
    private ArrayList<Exercise> mExerciseArrayList;
    ExerciseAdapter mExerciseAdapter;
    private ListView mListView;

    //private String mExerciseType; // passed in exercise type


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.setTitle("Select Exercise Type");

        setContentView(R.layout.activity_exercise_selection);

        Intent intent = getIntent();
        String message = intent.getStringExtra(ExerciseTypeSelection.EXTRA_MESSAGE);

        mExercisesDataSource = new ExercisesDataSource(this);
        mExercisesDataSource.open();
//        mExercisesDataSource.clear(); // TODO: remove this

        mExerciseArrayList = (ArrayList) mExercisesDataSource.getAllExercises();
        mExerciseAdapter = new ExerciseAdapter(this, mExerciseArrayList);

        mListView = (ListView) this.findViewById(R.id.exerciseSelectionListView);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListView.setAdapter(mExerciseAdapter);

    } // onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercise_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewExercise(View view) {
        Log.d(this.getClass().getName(), "create new Exercise");

        FragmentManager fragmentManager = getSupportFragmentManager();
        ExerciseCreationDialog newFragment = new ExerciseCreationDialog();

        boolean mIsLargeLayout = true;  // false = crash

        if (mIsLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(fragmentManager, "dialog");

        } else {  // dosen't display anything
            // The device is smaller, so show the fragment fullscreen
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        }

    } // createNewExercise

    @Override
    public void onDialogPositiveClick(Exercise exercise) {
        Log.d(this.getClass().getName(), "onDialogPositiveClick" + " " + exercise.getExerciseName());

        mExercisesDataSource.createExercise(exercise);

        Toast.makeText(this, exercise.getExerciseName() + " Created", Toast.LENGTH_LONG).show();


        updateList(exercise);
    }

    private void updateList(Exercise exercise) {
        mExerciseArrayList.add(exercise);
        mExerciseAdapter.notifyDataSetChanged();

        // sortList
        Collections.sort(mExerciseArrayList, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise ex1, Exercise ex2) {
                String s1 = ex1.getExerciseName();
                String s2 = ex2.getExerciseName();
                return (s1.compareTo(s2));
            }
        });
    }

    @Override
    public void onDialogNegativeClick() {
        Log.d(this.getClass().getName(), "onDialogNegativeClick");
    }

} // ExerciseSelection
