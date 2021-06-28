package com.example.studyonline_client.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.studyonline_client.R;
import com.example.studyonline_client.activity.ChangePersonalInfoActivity;
import com.example.studyonline_client.activity.LoginActivity;

public class PersonalCenterFragment extends Fragment implements View.OnClickListener {


    private TextView userAccount;
    private TextView userName;
    private TextView userTelephone;
    private TextView userAge;
    private LinearLayout changeInfo;
    private Button logout;
    private ImageView imageView;



    private void initView(View view){
        userAccount = view.findViewById(R.id.user_account);
        userName = view.findViewById(R.id.user_name);
        userTelephone = view.findViewById(R.id.user_telephone);
        userAge = view.findViewById(R.id.user_age);
        imageView = view.findViewById(R.id.user_sex);
        changeInfo = view.findViewById(R.id.btn_change_information);
        logout = view.findViewById(R.id.logout);


        if (LoginActivity.studentInfo.getAge()==0){
            userAge.setText("未填写年龄");
        }else{
            userAge.setText(String.valueOf(LoginActivity.studentInfo.getAge()));
        }

        if(LoginActivity.studentInfo.getSex().equals("男")){
            imageView.setBackground(getResources().getDrawable(R.drawable.boy));
        }else{
            imageView.setBackground(getResources().getDrawable(R.drawable.girl));

        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_center,container,false);
        initView(view);
        userAccount.setText(LoginActivity.studentInfo.getAccount());
        userName.setText(LoginActivity.studentInfo.getName());
        userTelephone.setText(LoginActivity.studentInfo.getTelephone());

        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChangePersonalInfoActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
