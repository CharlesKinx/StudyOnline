package com.example.studyonline_client.fragment;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.studyonline_client.R;
import com.example.studyonline_client.activity.CourseInfoActivity;
import com.example.studyonline_client.adapter.CourseListAdapter;
import com.example.studyonline_client.model.CourseInfo;

import java.util.ArrayList;

public class CourseLiveFragment extends ListFragment {

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

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        CourseInfo courseInfo = courseInfoArrayList.get(position);
        Intent intent = new Intent(getActivity(), CourseInfoActivity.class);
        startActivity(intent);
    }
}
