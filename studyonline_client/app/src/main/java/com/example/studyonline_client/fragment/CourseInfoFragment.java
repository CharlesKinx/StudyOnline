package com.example.studyonline_client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studyonline_client.R;

public class  CourseInfoFragment extends Fragment {

    private TextView courseName;
    private TextView courseNum;
    private TextView courseTeacherName;
    private TextView courseIntroduce;


    private void initView(View view){

        courseName = view.findViewById(R.id.course_name);
        courseNum = view.findViewById(R.id.course_num);
        courseTeacherName = view.findViewById(R.id.course_teacher_name);
        courseIntroduce = view.findViewById(R.id.course_intro);

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_infomation,container,false);


        return view;
    }
}
