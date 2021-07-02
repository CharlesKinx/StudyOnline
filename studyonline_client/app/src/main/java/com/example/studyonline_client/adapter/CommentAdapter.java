package com.example.studyonline_client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.studyonline_client.R;
import com.example.studyonline_client.model.CommentItemInfo;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    private TextView name;
    private TextView time;
    private TextView content;
    private RatingBar ratingBar;
    private ArrayList<CommentItemInfo> commentItemInfoArrayList;
    private Context context;

    public CommentAdapter(Context context,ArrayList<CommentItemInfo> commentItemInfos){
        this.context = context;
        this.commentItemInfoArrayList = commentItemInfos;
    }


    private void initView(View view){
        name = view.findViewById(R.id.comment_user_name);
        time = view.findViewById(R.id.comment_time);
        content = view.findViewById(R.id.comment_content);
        ratingBar = view.findViewById(R.id.comment_star);
    }

    @Override
    public int getCount() {
        if(commentItemInfoArrayList == null){
            return 0;
        }else{
            return commentItemInfoArrayList.size();
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
        View view = layoutInflater.inflate(R.layout.item_course_comment,null);
        initView(view);
        name.setText(commentItemInfoArrayList.get(position).getName());
        time.setText(commentItemInfoArrayList.get(position).getTime());
        content.setText(commentItemInfoArrayList.get(position).getContent());
        ratingBar.setRating(commentItemInfoArrayList.get(position).getScore());

        return view;
    }
}
