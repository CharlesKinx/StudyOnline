package com.example.studyonline_client.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.studyonline_client.R;
import com.example.studyonline_client.activity.ClassCoursePageActivity;
import com.example.studyonline_client.activity.CourseInfoActivity;
import com.example.studyonline_client.adapter.ClassListAdapter;
import com.example.studyonline_client.model.ClassInformation;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.utils.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class  ClassContactFragment extends ListFragment implements View.OnClickListener {

    private ListView listView;
    private ArrayList<ClassInformation> classInformationArrayList;
    private ClassListAdapter classListAdapter;
    private ClassInformation classInformation;
    private FloatingActionButton floatingActionButton;
    private Button addClass;
    private Button cancel;
    private AlertDialog.Builder customizeDialog;
    private AlertDialog alertDialog;

    private void setData(){

        classInformationArrayList = new ArrayList<>();
        classInformation = new ClassInformation();
        classInformation.setClassName("高等数学");
        classInformation.setClassStudentNum(18);
        classInformationArrayList.add(classInformation);

    }

    private void initView(View view){
        listView = view.findViewById(android.R.id.list);
        floatingActionButton = view.findViewById(R.id.fab);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_center,container,false);
        initView(view);
        setData();
        floatingActionButton.setOnClickListener(this);
        classListAdapter = new ClassListAdapter(getActivity(),classInformationArrayList);
        listView.setAdapter(classListAdapter);
        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //ClassInformation classInfo = classInformationArrayList.get(position);
        Intent intent = new Intent(getActivity(), ClassCoursePageActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                showDialog();
                ToastUtil.show("悬浮按钮",getActivity());
                break;
        }
    }


    private void showDialog(){
        customizeDialog = new AlertDialog.Builder(getActivity());
        final View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_add_class,null);
        addClass = dialogView.findViewById(R.id.add_class);
        cancel = dialogView.findViewById(R.id.add_class_cancel);

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("确认",getActivity());
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        customizeDialog.setCancelable(false);
        customizeDialog.setView(dialogView);
        alertDialog = customizeDialog.create();
        alertDialog.show();

    }
}
