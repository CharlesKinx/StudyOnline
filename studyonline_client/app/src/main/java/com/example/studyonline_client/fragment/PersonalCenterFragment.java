package com.example.studyonline_client.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.studyonline_client.R;
import com.example.studyonline_client.activity.ChangePersonalInfoActivity;
import com.example.studyonline_client.activity.LoginActivity;

public class PersonalCenterFragment extends Fragment {
    private LinearLayout changeInfo;
    private Button logout;

    private void initView(View view){
        changeInfo = view.findViewById(R.id.btn_change_information);
        logout = view.findViewById(R.id.logout);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_center,container,false);

        initView(view);
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
}
