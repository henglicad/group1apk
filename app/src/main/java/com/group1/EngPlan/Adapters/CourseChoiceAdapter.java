package com.group1.EngPlan.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.group1.EngPlan.R;

import java.util.ArrayList;

public class CourseChoiceAdapter extends BaseAdapter {

    public boolean[] checkBoxState;
    LayoutInflater mInflater;
    ArrayList<String> courseCode;
    ArrayList<String> courseName;

    public CourseChoiceAdapter(Context c, ArrayList<String> i, ArrayList<String> j){
        courseCode = i;
        courseName = j;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        checkBoxState = new boolean[courseCode.size()];
    }

   /* private class ViewHolder{
        TextView courseCodeView;
        TextView courseNameView;
        CheckBox courseCheck;

    }*/

    @Override
    public int getCount() {
        return courseCode.size();
    }

    @Override
    public Object getItem(int position) {
        return courseCode.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.listview_layout_passfail, null);
        TextView courseCodeView = (TextView) v.findViewById(R.id.textViewCourseCode);
        TextView courseNameView = (TextView) v.findViewById(R.id.textViewCourseName);
        CheckBox courseCheck = (CheckBox) v.findViewById(R.id.courseCheckBox);


        String code =  courseCode.get(position);
        String name =  courseName.get(position);

        courseCodeView.setText(code);
        courseNameView.setText(name);

        courseCheck.setChecked(checkBoxState[position]);
        courseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked())
                    checkBoxState[position] = true;
                else
                    checkBoxState[position] = false;
            }
        });

        return v;
    }
}

