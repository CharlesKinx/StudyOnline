package com.example.studyonline_client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studyonline_client.R;
import com.example.studyonline_client.model.CourseArrangement;

import java.util.ArrayList;

public class CourseArrangementAdapter extends BaseAdapter {

    private ArrayList<CourseArrangement> courseArrangementArrayList;
    private Context context;
    private TextView textView;

    public CourseArrangementAdapter(Context context, ArrayList<CourseArrangement> arrangements){
        this.courseArrangementArrayList = arrangements;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(courseArrangementArrayList == null){
            return 0;
        }else{
            return courseArrangementArrayList.size();
        }

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_arrangement,null);

        textView = view.findViewById(R.id.arrange_content);
        textView.setText(courseArrangementArrayList.get(position).getContent());

        return view;
    }
}
