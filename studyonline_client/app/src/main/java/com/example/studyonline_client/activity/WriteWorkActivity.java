package com.example.studyonline_client.activity;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.model.HttpResultInfo;
import com.example.studyonline_client.model.WorkInfo;
import com.example.studyonline_client.utils.ConstantUtil;
import com.example.studyonline_client.utils.JsonUtil;
import com.example.studyonline_client.utils.OkHttpUtil;
import com.example.studyonline_client.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class WriteWorkActivity extends AppCompatActivity implements View.OnClickListener{

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static final int REQUEST_PERMISSION_CODE = 1;

    private TextView textView;
    private ImageView imageView;
    private TextView workName;
    private LinearLayout linearLayout;
    private TextView fileSize;
    private WorkInfo workInfo;
    private TextView workTopic;
    private Button commit;
    private EditText editText;
    private HttpResultInfo resultInfo;

    private String url = ConstantUtil.url+"/work/upload";


    private void initView(){
        textView = findViewById(R.id.find_file);
        imageView = findViewById(R.id.word_uri);
        workName =findViewById(R.id.work_name);
        linearLayout = findViewById(R.id.file_work);
        fileSize = findViewById(R.id.work_file_size);
        linearLayout.setVisibility(View.GONE);
        workTopic = findViewById(R.id.topic);
        workTopic.setText(getIntent().getStringExtra("topic"));
        editText = findViewById(R.id.et_content);
        commit = findViewById(R.id.commit);
        workInfo = new WorkInfo();
        workInfo.setWorkId(getIntent().getIntExtra("workId",0));
        workInfo.setStudentId(LoginActivity.studentInfo.getId());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_work);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }

        initView();
        textView.setOnClickListener(this);
        commit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_file:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//无类型限制
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 33);
                break;
            case R.id.commit:
                if(editText.getText().toString().equals("")){
                    ToastUtil.show("内容不能为空！",WriteWorkActivity.this);
                }else{
                    workInfo.setContent(editText.getText().toString());
                    SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String datetime = tempDate.format(new java.util.Date());
                    workInfo.setCommitTime(datetime);
                    workInfo.setStatus(1);
                    commitWork();
                }
                break;

        }
    }


    private void commitWork(){
        String json = JsonUtil.objectToJson(workInfo);
        OkHttpUtil.usePost(url,json).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result= response.body().string();
                resultInfo = JSONObject.parseObject(result,HttpResultInfo.class);
                if(resultInfo.isSuccess()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show("提交成功",WriteWorkActivity.this);
                        }
                    });

                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 33) {
                Uri uri = data.getData();
                getFileInfo(uri);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }


    private void getFileInfo(Uri uri){

        Cursor returnCursor = getContentResolver().query(uri, null, null, null, null);
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);

        returnCursor.moveToFirst();
        String fileName = returnCursor.getString(nameIndex);

        workInfo.setFileName(fileName);
        workInfo.setUrl(uri.getPath());

        String[] token = fileName.split("\\.");
        String s = token[1];

        String type = s.toLowerCase();
        workInfo.setFileType(type);

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


    public static String getPath(final Context context, final Uri uri) {

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {

            if (isExternalStorageDocument(uri)) {// ExternalStorageProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                String storageDefinition;


                if("primary".equalsIgnoreCase(type)){

                    return Environment.getExternalStorageDirectory() + "/" + split[1];

                } else {

                    if(Environment.isExternalStorageRemovable()){
                        storageDefinition = "EXTERNAL_STORAGE";

                    } else{
                        storageDefinition = "SECONDARY_STORAGE";
                    }

                    return System.getenv(storageDefinition) + "/" + split[1];
                }

            } else if (isDownloadsDocument(uri)) {// DownloadsProvider

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);

            } else if (isMediaDocument(uri)) {// MediaProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {// MediaStore (and general)

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);

        } else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;

        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }



}
