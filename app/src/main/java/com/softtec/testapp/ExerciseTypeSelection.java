package com.softtec.testapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ExerciseTypeSelection extends AppCompatActivity {
    private Routine mRoutine;
    private ListView mExerciseistView;
    private ExerciseAdapter mExerciseAdapter; // Adapter for ExerciseList within Routine

    public final static String EXTRA_MESSAGE = "com.softtec.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_type_selection);
        //Intent intent = getIntent();

        mRoutine = new Routine();

        // Get ListView object from xml
        mExerciseistView = (ListView) findViewById(R.id.listView);

        mExerciseAdapter = new ExerciseAdapter(this, mRoutine.getExerciseList());
        mExerciseistView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mExerciseistView.setAdapter(mExerciseAdapter);

       /* // Defined Array values to show in ListView
        String[] values = new String[] {
                "Barbell Press",
                "Dumbell Flys",
                "Overhead Tri Push",
                "Pull Ups (wide)",
                "Bar curls",
                "Sh press"
        };*/

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, android.R.id.text1, values);*/

    } // onCreate

    public void selectChestExercises(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ExerciseSelection.class);

        String message = "Chest";
        intent.putExtra(EXTRA_MESSAGE, message);

        //startActivity(intent);
        int REQUEST_CODE = 0; // set it to ??? a code to identify which activity is returning?
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (resultCode == RESULT_OK && requestCode == 0) {
            if ( data.hasExtra("exerciseList") ) {

                ArrayList<Exercise> exerciseList = (ArrayList<Exercise>) data.getSerializableExtra("exerciseList");

                if ( exerciseList != null ) {
                    for (Exercise exercise : exerciseList) {
                        Log.d("mine", "ex name = " + exercise.getExerciseName());
                        mRoutine.addExercise(exercise);
                    }
                    mExerciseAdapter.notifyDataSetChanged();
                }
            }
        }
    } // onActivityResult


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_exercise_type_selection, menu);
//        return true;
//    }

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


}
