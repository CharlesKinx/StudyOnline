package com.example.studyonline_client.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.adapter.CourseArrangementAdapter;
import com.example.studyonline_client.model.CourseArrangement;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CourseArrangementFragment extends Fragment {
    private ListView listView;
    private ArrayList<CourseArrangement> courseArrangementArrayList;
    private CourseArrangementAdapter courseArrangementAdapter;
    private String url = CourseLiveFragment.url+"/arrangement";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course_arrangement,container,false);
        listView = view.findViewById(android.R.id.list);
        courseArrangementArrayList = new ArrayList<>();

        Intent intent = getActivity().getIntent();
        int id = intent.getIntExtra("id",0);

        OkHttpUtil.usePostById(url,id).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                courseArrangementArrayList = (ArrayList<CourseArrangement>) JSONObject.parseArray(result,CourseArrangement.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        courseArrangementAdapter = new CourseArrangementAdapter(getActivity(),courseArrangementArrayList);
                        listView.setAdapter(courseArrangementAdapter);
                    }
                });
            }
        });
        return view;
    }
}
