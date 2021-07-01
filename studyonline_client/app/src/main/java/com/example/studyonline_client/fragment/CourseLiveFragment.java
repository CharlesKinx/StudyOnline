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

import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.activity.CourseInfoActivity;
import com.example.studyonline_client.adapter.CourseListAdapter;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CourseLiveFragment extends ListFragment {

    private ListView listView;
    private CourseListAdapter courseListAdapter;
    private ArrayList<CourseInfo> courseInfoArrayList;
    private CourseInfo courseInfo;
    public static String url = "http://10.0.116.13:8181/course";
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

        OkHttpUtil.useGet(url+"/list").enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                courseInfoArrayList = (ArrayList<CourseInfo>) JSONObject.parseArray(result,CourseInfo.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        courseListAdapter = new CourseListAdapter(getActivity(),courseInfoArrayList);
                        listView.setAdapter(courseListAdapter);
                    }
                });
            }
        });


        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        CourseInfo courseInfo = courseInfoArrayList.get(position);
        Intent intent = new Intent(getActivity(), CourseInfoActivity.class);
        intent.putExtra("name",courseInfo.getTeacherName());
        intent.putExtra("id",courseInfo.getId());

        startActivity(intent);
    }
}
