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

import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.model.HttpResultInfo;
import com.example.studyonline_client.model.StudentInfo;
import com.example.studyonline_client.teacher.TeacherActivity;
import com.example.studyonline_client.utils.JsonUtil;
import com.example.studyonline_client.utils.OkHttpUtil;
import com.example.studyonline_client.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static String url = "http://10.0.116.13:8181/user/";
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

    public static StudentInfo studentInfo;

    private boolean teacher=false;
    private boolean student=false;
    private HttpResultInfo httpResultInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_login);

        ButterKnife.bind(this);
        httpResultInfo = new HttpResultInfo();
        studentInfo = new StudentInfo();
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
                if(editTextUser.getText().toString().equals("")){
                    ToastUtil.show("账号不能为空!",LoginActivity.this);
                }else if(editTextPassword.getText().toString().equals("")){
                    ToastUtil.show("密码不能为空!",LoginActivity.this);
                }else if(!teacher&&!student){
                    ToastUtil.show("请选择你的身份",LoginActivity.this);
                }else if(teacher&&student){
                    ToastUtil.show("只能选择一个身份",LoginActivity.this);
                }else if(teacher){
                    postLogin(JsonUtil.mapToJson(getLoginInfo()));

                }else {
                    postLogin(JsonUtil.mapToJson(getLoginInfo()));
                }
                break;

            case R.id.tx_login_resister:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;
        }
    }


    private Map<String,String> getLoginInfo(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("account",editTextUser.getText().toString());
        map.put("password",editTextPassword.getText().toString());
        if(teacher){
            map.put("role","2");
        }else{
            map.put("role","1");
        }
        return map;

    }


    private void postLogin(String json){
        OkHttpUtil.usePost(url+"login",json).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpResultInfo.setMsg("网络错误，请连接网络！");
                runOnUiThread(()->{
                    ToastUtil.show(httpResultInfo.getMsg(),LoginActivity.this);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                httpResultInfo = (HttpResultInfo) JSONObject.parseObject(result,HttpResultInfo.class);
                if(httpResultInfo.getMsg().equals("登录成功")){
                    String userString = JsonUtil.objectToJson(httpResultInfo.getData());
                    studentInfo = JSONObject.parseObject(userString,StudentInfo.class);
                    Intent intent = new Intent(LoginActivity.this,MainPageActivity.class);
                    startActivity(intent);
                    finish();
                    runOnUiThread(()->{
                        ToastUtil.show(httpResultInfo.getMsg(),LoginActivity.this);
                    });
                }else{
                    runOnUiThread(()->{
                        ToastUtil.show(httpResultInfo.getMsg(),LoginActivity.this);
                    });
                }
            }
        });
    }

}
