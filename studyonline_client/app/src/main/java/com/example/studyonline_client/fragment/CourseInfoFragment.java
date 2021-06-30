package com.example.studyonline_client.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.model.CourseInformation;
import com.example.studyonline_client.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class  CourseInfoFragment extends Fragment {

    private TextView courseName;
    private TextView courseNum;
    private TextView courseTeacherName;
    private TextView courseIntroduce;

    private String url = CourseLiveFragment.url+"/info";
    private CourseInformation courseInfo;
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
        initView(view);
        Intent intent = getActivity().getIntent();
        String teacherName = intent.getStringExtra("name");
        System.out.println(teacherName);
        int id = intent.getIntExtra("id",0);
        courseTeacherName.setText(teacherName);

        OkHttpUtil.usePostById(url,id).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                System.out.println(result);
                courseInfo = JSONObject.parseObject(result, CourseInformation.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        courseName.setText(courseInfo.getName());
                        courseNum.setText(String.valueOf(courseInfo.getViewNumber()));
                        courseIntroduce.setText(courseInfo.getIntroduce());
                    }
                });
            }
        });

        return view;
    }
}
