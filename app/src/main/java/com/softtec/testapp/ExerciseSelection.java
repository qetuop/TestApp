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
/*
        // Fragment
        ExerciseCreationDialog ecd = new ExerciseCreationDialog();

        // The second argument, "missiles", is a unique tag name that the system uses to
        // save and restore the fragment state when necessary. The tag also allows you to get a
        // handle to the fragment by calling findFragmentByTag().
        ecd.show(getSupportFragmentManager(), "create_new_exercise");
*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        ExerciseCreationDialog2 newFragment = new ExerciseCreationDialog2();
boolean mIsLargeLayout = true;
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


        //
        /* build in code
        //
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d("asdfasdf", String.valueOf(id));
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        // Set other dialog properties


        // Create the AlertDialog
        AlertDialog dialog = builder.create();

        dialog.show();
        */

        /*
        //
        // build from resource file
        //
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                // Add action buttons
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ;//LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        AlertDialog dialog =  builder.create();
        dialog.show();
*/
    } // createNewExercise

    @Override
    public void onDialogPositiveClick(android.support.v4.app.DialogFragment dialog) {
        Log.d(this.getClass().getName(), "onDialogPositiveClick");
    }

    @Override
    public void onDialogNegativeClick(android.support.v4.app.DialogFragment dialog) {
        Log.d(this.getClass().getName(), "onDialogNegativeClick");
    }
}
