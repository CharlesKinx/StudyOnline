package com.example.studyonline_client.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.studyonline_client.R;
import com.example.studyonline_client.adapter.CourseListAdapter;
import com.example.studyonline_client.model.CourseInfo;

import java.util.ArrayList;

public class CourseLiveFragment extends Fragment {

    private ListView listView;
    private CourseListAdapter courseListAdapter;
    private ArrayList<CourseInfo> courseInfoArrayList;
    private CourseInfo courseInfo;

    private void getData(){
        courseInfoArrayList = new ArrayList<>();
        courseInfo = new CourseInfo();
        courseInfo.setName("大学英语");
        courseInfo.setTime("2021/6/22");
        courseInfo.setTeacherName("吴海董");
        courseInfoArrayList.add(courseInfo);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courselive,container,false);
        listView = view.findViewById(android.R.id.list);
        getData();
        courseListAdapter = new CourseListAdapter(getActivity(),courseInfoArrayList);
        listView.setAdapter(courseListAdapter);
        return view;
    }
}
