package com.example.studyonline_client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyonline_client.R;
import com.example.studyonline_client.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassCoursePageActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.class_communicate)
    ImageButton communicate;

    @BindView(R.id.class_file)
    ImageButton file;

    @BindView(R.id.class_notification)
    ImageButton notification;

    @BindView(R.id.class_work)
    ImageButton work;

    @BindView(R.id.class_back)
    TextView back;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view);
        ButterKnife.bind(this);

    }

    @Override
    @OnClick({R.id.class_communicate,R.id.class_file,R.id.class_notification,R.id.class_work,R.id.class_back})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.class_communicate:
                intent = new Intent(ClassCoursePageActivity.this,CommunicationActivity.class);
                startActivity(intent);
                break;
            case R.id.class_file:
                intent = new Intent(ClassCoursePageActivity.this,FileSourceActivity.class);
                startActivity(intent);
                break;
            case R.id.class_notification:
                intent = new Intent(ClassCoursePageActivity.this,NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.class_work:
                intent = new Intent(ClassCoursePageActivity.this,CloudWorkActivity.class);
                startActivity(intent);
                break;
            case R.id.class_back:
                finish();
                break;

        }
    }
}
