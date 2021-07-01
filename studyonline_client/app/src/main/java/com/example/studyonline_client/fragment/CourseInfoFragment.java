package com.example.studyonline_client.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSONObject;

import com.bumptech.glide.Glide;
import com.example.studyonline_client.R;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.model.CourseInformation;
import com.example.studyonline_client.utils.GlideApp;
import com.example.studyonline_client.utils.OkHttpUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class  CourseInfoFragment extends Fragment {

    private TextView courseName;
    private TextView courseNum;
    private TextView courseTeacherName;
    private TextView courseIntroduce;
    private ImageView courseImg;

    private String url = CourseLiveFragment.url+"/info";
    private CourseInformation courseInfo;
    private void initView(View view){

        courseName = view.findViewById(R.id.course_name);
        courseNum = view.findViewById(R.id.course_num);
        courseTeacherName = view.findViewById(R.id.course_teacher_name);
        courseIntroduce = view.findViewById(R.id.course_intro);
        courseImg = view.findViewById(R.id.course_img);
    }

    private void imgView(int id){
        String imgUrl = CourseLiveFragment.url+"img";
        OkHttpUtil.usePostById(imgUrl,id).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GlideApp.with(getContext()).load(bytes).into(courseImg);
                        //GlideApp.with(getContext()).load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgss0.baidu.com%2F9vo3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F241f95cad1c8a786d814d6eb6709c93d70cf501c.jpg&refer=http%3A%2F%2Fgss0.baidu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1627699586&t=fa8f55932b798ecd7832a3ca87e8ea11").into(courseImg);
                    }
                });
            }
        });

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

        imgView(id);

        return view;
    }
}
