package com.example.studyonline_client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyonline_client.R;
import com.example.studyonline_client.adapter.WorkListAdapter;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.model.WorkList;

import java.util.ArrayList;

public class WorkListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private WorkListAdapter workListAdapter;
    private ArrayList<WorkList> workListArrayList;
    private TextView textView;

    private void initView(){
        listView = findViewById(R.id.work_list);
        textView = findViewById(R.id.home_work_back);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_list);

        initView();
    }



    @Override
    public void onClick(View v) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
