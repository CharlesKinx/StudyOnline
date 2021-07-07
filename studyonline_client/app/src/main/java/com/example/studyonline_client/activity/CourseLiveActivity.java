package com.example.studyonline_client.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyonline_client.R;
import com.example.studyonline_client.utils.ToastUtil;
import com.tencent.live2.V2TXLiveDef;
import com.tencent.live2.V2TXLivePusher;
import com.tencent.live2.V2TXLivePusherObserver;
import com.tencent.live2.impl.V2TXLivePusherImpl;
import com.tencent.rtmp.TXLiveBase;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

import static com.tencent.live2.V2TXLiveCode.V2TXLIVE_ERROR_INVALID_LICENSE;

public class CourseLiveActivity extends AppCompatActivity {

    private static String licenceUrl = "http://license.vod2.myqcloud.com/license/v1/c9f6ee1cd0fbf2a81cd5f41641ac21b2/TXLiveSDK.licence";
    private static String licenceKey = "9e50b46727dbbd1b097406df314a6b81";
    private TXCloudVideoView pushView ;
    private  TXLivePusher mLivePusher;
    private TXLivePushConfig mLivePushConfig;
    private ImageButton button;
    private ImageButton buttonChange;
    private TextView textView;
    private ImageButton over;

    private void initView(){
        pushView = findViewById(R.id.pusher_tx_cloud_view);
        button = findViewById(R.id.begin_push);
        buttonChange = findViewById(R.id.change_camera);
        mLivePusher = new TXLivePusher(this);
        over = findViewById(R.id.over_push);
        over.setVisibility(View.GONE);
        mLivePushConfig = new TXLivePushConfig();
        textView = findViewById(R.id.begin_live);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course_live);
        TXLiveBase.getInstance().setLicence(this, licenceUrl, licenceKey);
        initView();
        mLivePusher.setConfig(mLivePushConfig);


        mLivePusher.startCameraPreview(pushView);
        
        String url = "rtmp://144585.livepush.myqcloud.com/live/live?txSecret=73c06cfa87afd38812fdbe0e17a7668d&txTime=60E76A52";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtil.show("推流成功！",getApplicationContext());
                mLivePusher.startPusher(url.trim());
                textView.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                over.setVisibility(View.VISIBLE);
            }
        });


        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLivePusher.switchCamera();
            }
        });

        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLivePusher.stopPusher();
                mLivePusher.stopCameraPreview(true);
                ToastUtil.show("结束直播",CourseLiveActivity.this);
                finish();
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
