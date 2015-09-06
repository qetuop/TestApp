package com.softtec.testapp;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ExerciseSelection extends FragmentActivity
implements ExerciseCreationDialog2.NoticeDialogListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_selection);
/*
        Intent intent = getIntent();
        String message = intent.getStringExtra(ExerciseTypeSelection.EXTRA_MESSAGE);

        //setContentView(R.layout.activity_exercise_selection);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        setContentView(textView);
        */
    }

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
        ExerciseCreationDialog2 newFragment = new ExerciseCreationDialog2();

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
            transaction.add(android.R.id.content, newFragment)
                    .addToBackStack(null).commit();
        }

    } // createNewExercise

    @Override
    public void onDialogPositiveClick(Exercise exercise) {
        Log.d(this.getClass().getName(), "onDialogPositiveClick" + " " + exercise.getExerciseName());



        Toast.makeText(this, exercise.getExerciseName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDialogNegativeClick() {
        Log.d(this.getClass().getName(), "onDialogNegativeClick");
    }
}
