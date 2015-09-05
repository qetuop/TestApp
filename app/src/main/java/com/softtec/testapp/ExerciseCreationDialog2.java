package com.softtec.testapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.view.View.OnClickListener;

/**
 * Created by Brian on 8/23/2015.
 */
public class ExerciseCreationDialog2 extends DialogFragment  implements OnClickListener {

    private Button btnOk;
    private Button btnCancel;

    // Use this instance of the interface to deliver action events
    private NoticeDialogListener mListener;

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("mine", "onAttach");

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;

        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }

    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("mine", "onCreateDialog");

        // can't use AlertDialog.Builder here

        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("mine", "onCreateView");

        // Inflate the layout to use as dialog or embedded fragment
        View v = inflater.inflate(R.layout.exercise_creation, container, false);

        getDialog().setTitle("Set Your Name");

        //
        btnOk = (Button) v.findViewById(R.id.newExerciseOK);
        btnOk.setOnClickListener(this);
        btnCancel = (Button) v.findViewById(R.id.newExerciseCancel);
        btnCancel.setOnClickListener(this);

        return v;

    }


    /*public void onOk(View view) {
        Log.d("mine", "onOk");
    }*/

    @Override
    public void onClick(View v) {
        Log.d("mine", "onClick");



        switch (v.getId()) {
            case R.id.newExerciseOK:
                // validate entry

                // create Exercise Object

                // return object
                mListener.onDialogPositiveClick(ExerciseCreationDialog2.this);
                break;

            case R.id.newExerciseCancel:
                mListener.onDialogNegativeClick(ExerciseCreationDialog2.this);
                break;
//            case R.id.button_three:
//                // i'm lazy, do nothing
//                break;
        }



        this.dismiss();

    }


//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        // Use the Builder class for convenient dialog construction
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage(R.string.create_new_exercise)
//                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // Send the positive button event back to the host activity
//                        mListener.onDialogPositiveClick(ExerciseCreationDialog2.this);
//
//                    }
//                })
//                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        mListener.onDialogNegativeClick(ExerciseCreationDialog2.this);
//
//                    }
//                });
//        // Create the AlertDialog object and return it
//        return builder.create();
//    }



}