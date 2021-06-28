package com.example.studyonline_client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private Button button;

    private void initView(){
        pushView = findViewById(R.id.pusher_tx_cloud_view);
        button = findViewById(R.id.begin_push);
        mLivePusher = new TXLivePusher(this);
        mLivePushConfig = new TXLivePushConfig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_live);
        TXLiveBase.getInstance().setLicence(this, licenceUrl, licenceKey);
        initView();
        mLivePusher.setConfig(mLivePushConfig);
        mLivePusher.startCameraPreview(pushView);
        
        String url ="rtmp://144585.livepush.myqcloud.com/live/demo?txSecret=839e872789fa82d6e97fe1554cd068a5&txTime=60D94364";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mLivePusher.startPusher(url.trim());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLivePusher.stopPusher();
        mLivePusher.stopCameraPreview(true);
    }
}
