package com.example.studyonline_client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyonline_client.R;

import butterknife.ButterKnife;

public class ChangePersonalInfoActivity extends AppCompatActivity {

    private TextView textViewBack;

    private void initView(){
        textViewBack = findViewById(R.id.change_back);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_infomation);
        initView();
        textViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
