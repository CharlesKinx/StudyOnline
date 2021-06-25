package com.example.studyonline_client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studyonline_client.R;
import com.example.studyonline_client.model.ClassInformation;

import java.util.ArrayList;

public class ClassListAdapter extends BaseAdapter {

    private ArrayList<ClassInformation> classInformationArrayList;
    private Context context;
    private TextView className;
    private TextView classNum;

    private void initView(View view){
        className = view.findViewById(R.id.class_name);
        classNum = view.findViewById(R.id.class_number);
    }

    @Override
    public int getCount() {
        if(classInformationArrayList==null){
            return 0;
        }else{
            return classInformationArrayList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_class,null);
        initView(view);

        className.setText(classInformationArrayList.get(position).getClassName());
        classNum.setText(classInformationArrayList.get(position).getClassStudentNum());
        return view;
    }
}
