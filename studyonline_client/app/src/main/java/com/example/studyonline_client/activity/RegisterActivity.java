package com.example.studyonline_client.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.model.HttpResultInfo;
import com.example.studyonline_client.model.StudentInfo;
import com.example.studyonline_client.utils.ConstantUtil;
import com.example.studyonline_client.utils.JsonUtil;
import com.example.studyonline_client.utils.OkHttpUtil;
import com.example.studyonline_client.utils.ToastUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener , CompoundButton.OnCheckedChangeListener{

    @BindView(R.id.btn_register)
    Button register;

    @BindView(R.id.tx_register_back)
    TextView backLogin;

    @BindView(R.id.et_register_user)
    EditText editTextUser;

    @BindView(R.id.et_register_user_name)
    EditText editTextUserName;

    @BindView(R.id.et_register_telephone)
    EditText telephone;

    @BindView(R.id.et_register_password)
    EditText password;

    @BindView(R.id.et_register_age)
    EditText age;

    @BindView(R.id.et_register_password1)
    EditText password1;

    @BindView(R.id.register_checkbox_student)
    CheckBox studentCheckBox;

    @BindView(R.id.register_checkbox_teacher)
    CheckBox teacherCheckBox;

    @BindView(R.id.register_checkbox_boy)
    CheckBox boyCheckBox;

    @BindView(R.id.register_checkbox_girl)
    CheckBox girlCheckBox;


    private String url = ConstantUtil.url+"user/register";

    private HttpResultInfo httpResultInfo;
    private boolean teacher=false;
    private boolean student=false;
    private boolean boy =false;
    private boolean girl =false;

    private StudentInfo studentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        httpResultInfo = new HttpResultInfo();

        studentCheckBox.setOnCheckedChangeListener(this);
        teacherCheckBox.setOnCheckedChangeListener(this);
        boyCheckBox.setOnCheckedChangeListener(this);
        girlCheckBox.setOnCheckedChangeListener(this);
        backLogin.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    private void postRegister(String json){
        try {
            Response response = OkHttpUtil.usePost(url,json).execute();
            String result = response.body().string();
            httpResultInfo = (HttpResultInfo) JSONObject.parseObject(result,HttpResultInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tx_register_back:
                finish();
                break;
            case R.id.btn_register:

                if(editTextUser.getText().toString().equals("")){
                    ToastUtil.show("账号不能为空！",RegisterActivity.this);
                }else if(editTextUserName.getText().toString().equals("")){
                    ToastUtil.show("用户名不能为空！",RegisterActivity.this);
                }else if(telephone.getText().toString().equals("")){
                    ToastUtil.show("手机号不能为空！",RegisterActivity.this);
                }else if(age.getText().toString().equals("")){
                    ToastUtil.show("年龄不能为空！",RegisterActivity.this);
                }else if(password.getText().toString().equals("")||password1.getText().toString().equals("")){
                    ToastUtil.show("密码不能为空！",RegisterActivity.this);
                }else if(!password.getText().toString().equals(password1.getText().toString())){
                    ToastUtil.show("两次密码不一样！",RegisterActivity.this);
                }else if(!boy&&!girl){
                    ToastUtil.show("请选择你的性别！",RegisterActivity.this);
                }else if(boy&&girl){
                    ToastUtil.show("请重新选择你的性别！",RegisterActivity.this);
                }else if(!teacher&&!student){
                    ToastUtil.show("请选择你的身份！",RegisterActivity.this);
                }else if(teacher&&student){
                    ToastUtil.show("只能选择一种身份！",RegisterActivity.this);
                }else if(student){

                    studentInfo = new StudentInfo();
                    studentInfo.setRole(1);
                    studentInfo.setPassword(password.getText().toString());
                    studentInfo.setTelephone(telephone.getText().toString());
                    studentInfo.setAccount(editTextUser.getText().toString());
                    studentInfo.setName(editTextUserName.getText().toString());
                    studentInfo.setAge(Integer.valueOf(age.getText().toString()));
                    if(boy){
                        studentInfo.setSex("男");
                    }else{
                        studentInfo.setSex("女");
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            postRegister(JsonUtil.objectToJson(studentInfo));
                            if(httpResultInfo.getMsg().equals("注册成功")){
                                finish();
                                runOnUiThread(()->{
                                    ToastUtil.show(httpResultInfo.getMsg(),RegisterActivity.this);
                                });
                            }else{
                                runOnUiThread(()->{
                                    ToastUtil.show(httpResultInfo.getMsg(),RegisterActivity.this);
                                });
                            }
                        }
                    }).start();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.register_checkbox_student:
                if(isChecked){
                    student = true;
                }else {
                    student = false;
                }
                break;
            case R.id.register_checkbox_teacher:
                if(isChecked){
                    teacher = true;
                }else {
                    teacher =false;
                }
                break;
            case R.id.register_checkbox_boy:
                if(isChecked){
                    boy = true;
                }else {
                    boy =false;
                }
                break;
            case R.id.register_checkbox_girl:
                if(isChecked){
                    girl = true;
                }else {
                    girl =false;
                }
                break;
        }
    }
}
