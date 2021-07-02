package com.example.studyonline_client.fragment;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

public class  ClassContactFragment extends ListFragment {

    private ListView listView;
    private ArrayList<ClassInformation> classInformationArrayList;
    private ClassListAdapter classListAdapter;
    private ClassInformation classInformation;

    private void setData(){

        classInformationArrayList = new ArrayList<>();
        classInformation = new ClassInformation();
        classInformation.setClassName("高等数学");
        classInformation.setClassStudentNum(18);
        classInformationArrayList.add(classInformation);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_center,container,false);
        listView = view.findViewById(android.R.id.list);
        setData();
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
}
