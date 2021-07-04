package com.example.studyonline_client.activity;


import android.app.Activity;
import android.content.Intent;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.provider.OpenableColumns;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyonline_client.R;
import com.example.studyonline_client.utils.ToastUtil;

public class WriteWorkActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private ImageView imageView;
    private TextView workName;
    private LinearLayout linearLayout;
    private TextView fileSize;


    private void initView(){
        textView = findViewById(R.id.find_file);
        imageView = findViewById(R.id.word_uri);
        workName =findViewById(R.id.work_name);
        linearLayout = findViewById(R.id.file_work);
        fileSize = findViewById(R.id.work_file_size);
        linearLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_work);
        initView();
        textView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.find_file:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//无类型限制
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                getFileType(uri);
            }
        }
    }

    private void getFileType(Uri uri){

        Cursor returnCursor = getContentResolver().query(uri, null, null, null, null);

        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
        returnCursor.moveToFirst();

        String fileName = returnCursor.getString(nameIndex);
        String[] token = fileName.split("\\.");
        String s = token[1];

        String type = s.toLowerCase();

        if (type.equals("doc")||type.equals("docx")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.word));
        }else if(type.equals("txt")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.txt));
        }else if(type.equals("ppt")||type.equals("pptx")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ppt));
        }else if(type.equals("pdf")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.pdf));
        }else if(type.equals("xls")||type.equals("xlsx")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.excel));
        }else{
            imageView.setImageURI(uri);
        }
        workName.setText(fileName);
        fileSize.setText(longToByteSize(returnCursor.getLong(sizeIndex)));
        linearLayout.setVisibility(View.VISIBLE);
    }


    private String longToByteSize(long size){
        if(size == 0){
            return null;
        }
        if (size < 1024) {
            return String.valueOf(size) + "b";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "Kb";
        } else {
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "Mb";
        }

    }

}
