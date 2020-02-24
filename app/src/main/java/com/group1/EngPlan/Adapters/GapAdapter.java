package com.group1.EngPlan.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.group1.EngPlan.R;

public class GapAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    String[] st;


    public GapAdapter(Context c, String[] s){
        st = s;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return st.length;
    }

    @Override
    public Object getItem(int position) {
        return st[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.listview_headings_quick_view, null);
        TextView semesterName = (TextView) v.findViewById(R.id.TextViewSemester);


        String sem =  st[position];
        semesterName.setText(sem);


        return v;
    }
}
