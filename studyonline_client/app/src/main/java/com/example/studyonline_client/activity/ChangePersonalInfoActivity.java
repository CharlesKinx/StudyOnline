package com.example.studyonline_client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.model.HttpResultInfo;
import com.example.studyonline_client.model.StudentInfo;
import com.example.studyonline_client.utils.JsonUtil;
import com.example.studyonline_client.utils.OkHttpUtil;
import com.example.studyonline_client.utils.ToastUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChangePersonalInfoActivity extends AppCompatActivity implements View.OnClickListener , CompoundButton.OnCheckedChangeListener{


    @BindView(R.id.change_back)
    TextView textViewBack;

    @BindView(R.id.et_change_user_name)
    EditText changeName;

    @BindView(R.id.et_change_telephone)
    EditText telephone;

    @BindView(R.id.et_change_age)
    EditText age;

    @BindView(R.id.et_change_password)
    EditText password;

    @BindView(R.id.et_change_password1)
    EditText password1;

    @BindView(R.id.btn_change)
    Button change;

    @BindView(R.id.change_checkbox_boy)
    CheckBox boyCheckBox;

    @BindView(R.id.change_checkbox_girl)
    CheckBox girlCheckBox;

    private boolean boy =false;
    private boolean girl =false;
    private StudentInfo studentInfo;
    private String url = LoginActivity.url+"change";
    private HttpResultInfo httpResultInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_infomation);
        ButterKnife.bind(this);
        studentInfo = new StudentInfo();
        textViewBack.setOnClickListener(this);
        change.setOnClickListener(this);
        boyCheckBox.setOnCheckedChangeListener(this);
        girlCheckBox.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_back:
                finish();
                break;
            case R.id.btn_change:
                if(changeName.getText().toString().equals("")){
                    ToastUtil.show("用户名不能为空！",ChangePersonalInfoActivity.this);
                }else if(telephone.getText().toString().equals("")){
                    ToastUtil.show("手机号不能为空！",ChangePersonalInfoActivity.this);
                }else if(age.getText().toString().equals("")){
                    ToastUtil.show("年龄不能为空！",ChangePersonalInfoActivity.this);
                }else if(password.getText().toString().equals("")||password1.getText().toString().equals("")){
                    ToastUtil.show("密码不能为空！",ChangePersonalInfoActivity.this);
                }else if(!password.getText().toString().equals(password1.getText().toString())){
                    ToastUtil.show("两次密码不一样！",ChangePersonalInfoActivity.this);
                }else if(!boy&&!girl){
                    ToastUtil.show("请选择你的性别！",ChangePersonalInfoActivity.this);
                }else if(boy&&girl){
                    ToastUtil.show("请重新选择你的性别！",ChangePersonalInfoActivity.this);
                }else{
                    studentInfo.setId(LoginActivity.studentInfo.getId());
                    studentInfo.setAccount(LoginActivity.studentInfo.getAccount());
                    studentInfo.setRole(1);
                    studentInfo.setPassword(password.getText().toString());
                    studentInfo.setTelephone(telephone.getText().toString());
                    studentInfo.setName(changeName.getText().toString());
                    studentInfo.setAge(Integer.valueOf(age.getText().toString()));

                    if(boy){
                        studentInfo.setSex("男");
                    }else{
                        studentInfo.setSex("女");
                    }

                    OkHttpUtil.usePost(url,JsonUtil.objectToJson(studentInfo)).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String result = response.body().string();
                            httpResultInfo = (HttpResultInfo) JSONObject.parseObject(result,HttpResultInfo.class);
                            if(httpResultInfo.getMsg().equals("修改成功")){
                                String userString = JsonUtil.objectToJson(httpResultInfo.getData());
                                LoginActivity.studentInfo = JSONObject.parseObject(userString,StudentInfo.class);
                                runOnUiThread(()->{
                                    ToastUtil.show(httpResultInfo.getMsg(),ChangePersonalInfoActivity.this);
                                });
                                Intent intent = new Intent(ChangePersonalInfoActivity.this,MainPageActivity.class);
                                startActivity(intent);
                            }
                        }
                    });

                }
        }
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.change_checkbox_boy:
            if(isChecked){
                boy = true;
            }else {
                boy =false;
            }
            break;
            case R.id.change_checkbox_girl:
            if(isChecked){
                girl = true;
            }else {
                girl =false;
            }
            break;
        }
    }
}
