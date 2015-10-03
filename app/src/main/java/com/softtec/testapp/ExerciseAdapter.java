package com.softtec.testapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 10/3/15.
 */
public class ExerciseAdapter extends BaseAdapter {
    private ArrayList<Exercise> mExerciseList;
    private Context mContext;
    private LayoutInflater mLayoutInflater = null;

    /*public ExerciseAdapter(Context context, int textViewResourceId, ArrayList<Exercise> exerciseList) {
        super(context, textViewResourceId, exerciseList);
        this.exerciseList = new ArrayList<Exercise>();
        this.exerciseList.addAll(exerciseList);

        mContext = context;
    }*/

    public ExerciseAdapter(Activity context, ArrayList<Exercise> exerciseList) {
        mContext = context;
        mExerciseList = exerciseList;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class ViewHolder {
        CheckBox name;

        public ViewHolder(View base) {
            name = (CheckBox) base.findViewById(R.id.row_exercise_cb);
        }
    }

    @Override
    public int getCount() {
        return mExerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return mExerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.row_exercise, null);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

            // Click Listerner Code
            holder.name.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Exercise exercise = (Exercise) cb.getTag();
                    Toast.makeText(
                            mContext,
                            "Clicked on Checkbox: " + cb.getText() + " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    exercise.setSelected(cb.isChecked());
                }
            });
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Exercise exercise = mExerciseList.get(position);

        holder.name.setText(exercise.getExerciseName());
        holder.name.setChecked(exercise.isSelected());
        holder.name.setTag(exercise);

        return convertView;

    }

} // ExerciseArrayAdapter
