package com.softtec.testapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ExerciseTypeSelection extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.softtec.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_type_selection);
        //Intent intent = getIntent();

        // Get ListView object from xml
        ListView listView = (ListView) findViewById(R.id.listView);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Barbell Press",
                "Dumbell Flys",
                "Overhead Tri Push",
                "Pull Ups (wide)",
                "Bar curls",
                "Sh press"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

    }

    public void selectChestExercises(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ExerciseSelection.class);

        String message = "Chest";
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }
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
