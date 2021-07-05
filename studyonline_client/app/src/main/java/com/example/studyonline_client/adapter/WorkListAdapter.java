package com.example.studyonline_client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studyonline_client.R;
import com.example.studyonline_client.model.WorkInfo;
import com.example.studyonline_client.model.WorkList;

import java.util.ArrayList;

public class WorkListAdapter extends BaseAdapter {

    private ArrayList<WorkList> workInfoArrayList;
    private Context context;
    private TextView workTopic;
    private TextView workTime;
    private TextView workScore;
    private ImageView imageView;
    private TextView workCommitTime;

    public WorkListAdapter( ArrayList<WorkList> arrayList,Context context){
        this.workInfoArrayList = arrayList;
        this.context = context;
    }

    private void initView(View view){
        workTopic = view.findViewById(R.id.work_topic);
        workTime = view.findViewById(R.id.work_time);
        workScore = view.findViewById(R.id.work_score);
        imageView = view.findViewById(R.id.work_status);
        workCommitTime = view.findViewById(R.id.commit_work_time);
    }


    @Override
    public int getCount() {
        if(workInfoArrayList == null){
            return 0;
        }else{
            return workInfoArrayList.size();
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

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_home_work,null);

        initView(view);

        workTopic.setText(workInfoArrayList.get(position).getWorkTopic());
        workTime.setText(workInfoArrayList.get(position).getPublishTime());
        if(workInfoArrayList.get(position).getStatus() == 0){
            workScore.setText("");
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.no_ok));
            workCommitTime.setText("未提交");
        }else if(workInfoArrayList.get(position).getScore()==0){
            workCommitTime.setText("提交时间："+workInfoArrayList.get(position).getCommitTime());
            workScore.setText("已提交");
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.ing));
        }else {
            workCommitTime.setText("提交时间："+workInfoArrayList.get(position).getCommitTime());
            workScore.setText("分数："+String.valueOf(workInfoArrayList.get(position).getScore()));
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.commit_ok));
        }


        return view;
    }
}
