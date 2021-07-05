package com.example.studyonline_client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.adapter.WorkListAdapter;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.model.WorkList;
import com.example.studyonline_client.utils.ConstantUtil;
import com.example.studyonline_client.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WorkListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private WorkListAdapter workListAdapter;
    private ArrayList<WorkList> workListArrayList;
    private TextView textView;
    private String url = ConstantUtil.url+"/work";
    private int teacherId;

    private void initView(){
        listView = findViewById(R.id.work_list);
        textView = findViewById(R.id.home_work_back);
        teacherId = getIntent().getIntExtra("teacherId",0);
        workListArrayList  = new ArrayList<>();
    }

    private void getData(){

        Map<String,Integer> map = new HashMap<>();
        map.put("teacherId",teacherId);
        map.put("studentId",LoginActivity.studentInfo.getId());
        String json = JSON.toJSONString(map);

        OkHttpUtil.usePost(url+"/list",json).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                workListArrayList = (ArrayList<WorkList>) JSONObject.parseArray(result,WorkList.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        workListAdapter = new WorkListAdapter(workListArrayList,WorkListActivity.this);
                        listView.setAdapter(workListAdapter);
                    }
                });
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_list);
        initView();
        getData();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_work_back:
                finish();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
