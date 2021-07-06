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
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.activity.ClassCoursePageActivity;
import com.example.studyonline_client.activity.CourseInfoActivity;
import com.example.studyonline_client.activity.LoginActivity;
import com.example.studyonline_client.activity.WorkListActivity;
import com.example.studyonline_client.adapter.ClassListAdapter;
import com.example.studyonline_client.model.ClassInformation;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.model.HttpResultInfo;
import com.example.studyonline_client.model.StudentInfo;
import com.example.studyonline_client.model.WorkList;
import com.example.studyonline_client.utils.ConstantUtil;
import com.example.studyonline_client.utils.JsonUtil;
import com.example.studyonline_client.utils.OkHttpUtil;
import com.example.studyonline_client.utils.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    private EditText editText;
    private HttpResultInfo httpResultInfo;
    private void setData(){

        classInformationArrayList = new ArrayList<>();

        OkHttpUtil.usePostById(ConstantUtil.url+"class/list", LoginActivity.studentInfo.getId()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.show("网络异常",getActivity());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                classInformationArrayList = (ArrayList<ClassInformation>) JSONObject.parseArray(result,ClassInformation.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        classListAdapter = new ClassListAdapter(getActivity(),classInformationArrayList);
                        listView.setAdapter(classListAdapter);
                    }
                });
            }
        });

    }

    private void initView(View view){
        listView = view.findViewById(android.R.id.list);
        floatingActionButton = view.findViewById(R.id.fab);
        classInformation = new ClassInformation();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_center,container,false);
        initView(view);
        setData();
        floatingActionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //ClassInformation classInfo = classInformationArrayList.get(position);
        Intent intent = new Intent(getActivity(), WorkListActivity.class);
        intent.putExtra("teacherId",classInformationArrayList.get(position).getTeacherID());
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                showDialog();
                break;
        }
    }


    private void showDialog(){
        customizeDialog = new AlertDialog.Builder(getActivity());
        final View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_add_class,null);
        addClass = dialogView.findViewById(R.id.add_class);
        cancel = dialogView.findViewById(R.id.add_class_cancel);
        editText = dialogView.findViewById(R.id.class_id);

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().toString().equals("")){
                    ToastUtil.show("班课号不能为空！",getActivity());
                }else{
                    int classId = Integer.parseInt(editText.getText().toString());
                    addClass(classId);
                }

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


    private void addClass(int classId){
        Map<String,Integer> map = new HashMap<>();
        map.put("classId",classId);
        map.put("studentId",LoginActivity.studentInfo.getId());
        String json = JSON.toJSONString(map);
        OkHttpUtil.usePost(ConstantUtil.url+"class/add",json).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.show("网络异常！",getActivity());
                        alertDialog.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    httpResultInfo = (HttpResultInfo) JSONObject.parseObject(result,HttpResultInfo.class);
                    String userString = JsonUtil.objectToJson(httpResultInfo.getData());
                    classInformation = JSONObject.parseObject(userString, ClassInformation.class);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show(httpResultInfo.getMsg(),getActivity());
                            alertDialog.dismiss();
                            if(httpResultInfo.isSuccess()){
                                classInformationArrayList.add(classInformation);
                                classListAdapter.notifyDataSetChanged();
                            }
                        }
                    });

            }
        });
    }
}
