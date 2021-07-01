package com.example.studyonline_client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studyonline_client.R;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.utils.GlideApp;

import java.util.ArrayList;

public class CourseListAdapter extends BaseAdapter {

    private ArrayList<CourseInfo> courseInfoArrayList;
    private Context context;

    private TextView courseName;
    private TextView teacherName;
    private TextView time;
    private ImageView imageView;

    public CourseListAdapter(Context context,ArrayList<CourseInfo> arrayList){
        this.context = context;
        this.courseInfoArrayList = arrayList;

    }

    private void initView(View view){
        courseName = view.findViewById(R.id.course_item_name);
        teacherName = view.findViewById(R.id.teacher_item_name);
        time = view.findViewById(R.id.course_item_time);
        imageView = view.findViewById(R.id.course_item_img);
    }

    @Override
    public int getCount() {
        if(courseInfoArrayList == null){
            return 0;
        }else{
            return courseInfoArrayList.size();
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_course,null);
        initView(view);

        courseName.setText(courseInfoArrayList.get(position).getName());
        teacherName.setText(courseInfoArrayList.get(position).getTeacherName());
        time.setText(courseInfoArrayList.get(position).getTime());
        GlideApp.with(context).load(courseInfoArrayList.get(position).getImgUrl()).into(imageView);
        return view;
    }
}
