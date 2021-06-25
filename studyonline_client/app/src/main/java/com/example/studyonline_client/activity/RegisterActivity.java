package com.example.studyonline_client.activity;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_register)
    Button register;

    @BindView(R.id.tx_register_back)
    TextView backLogin;

    @BindView(R.id.et_register_user)
    EditText editTextUser;

    @BindView(R.id.et_register_telephone)
    EditText telephone;

    @BindView(R.id.et_register_password)
    EditText password;

    @BindView(R.id.et_register_password1)
    EditText password1;

    @BindView(R.id.register_checkbox_student)
    CheckBox studentCheckBox;

    @BindView(R.id.register_checkbox_teacher)
    CheckBox teacherCheckBox;


    private boolean teacher=false;
    private boolean student=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        backLogin.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tx_register_back:
                finish();
                break;
            case R.id.btn_register:
                if(!teacher&&!student){
                    Toast.makeText(RegisterActivity.this,"请选择你的身份",Toast.LENGTH_SHORT).show();
                }else if(teacher&&student){
                    Toast.makeText(RegisterActivity.this,"只能选择一种身份",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }
}
