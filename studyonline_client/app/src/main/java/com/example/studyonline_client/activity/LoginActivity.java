package com.example.studyonline_client.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyonline_client.R;
import com.example.studyonline_client.teacher.TeacherActivity;
import com.example.studyonline_client.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_login)
    Button login;

    @BindView(R.id.et_login_user)
    EditText editTextUser;

    @BindView(R.id.et_login_password)
    EditText editTextPassword;

    @BindView(R.id.tx_login_resister)
    TextView register;

    @BindView(R.id.checkbox_student)
    CheckBox studentCheckBox;

    @BindView(R.id.checkbox_teacher)
    CheckBox teacherCheckBox;


    private boolean teacher=false;
    private boolean student=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_login);
        ButterKnife.bind(this);

        studentCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    student = true;
                }else {
                    student = false;
                }
            }
        });

        teacherCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    teacher = true;
                }else {
                    teacher =false;
                }
            }
        });
        register.setOnClickListener(this);
        login.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if(!teacher&&!student){
                    ToastUtil toastUtil = new ToastUtil();
                    toastUtil.show("请选择你的身份",LoginActivity.this);

                }else if(teacher&&student){
                    Toast.makeText(LoginActivity.this,"只能选择一种身份",Toast.LENGTH_SHORT).show();
                }else if(teacher){
                    Intent intent = new Intent(LoginActivity.this, TeacherActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.tx_login_resister:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;
        }
    }

}
