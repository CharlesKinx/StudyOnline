package com.example.studyonline_client.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyonline_client.R;

import kotlin.jvm.internal.PropertyReference0Impl;

public class ToastUtil {

    public static void show(String msg,Context context) {
        show(msg, Toast.LENGTH_SHORT,context);
    }

    /**
     * 展示toast==LENGTH_LONG
     *
     * @param msg
     */
    public static void showLong(String msg,Context context) {
        show(msg, Toast.LENGTH_LONG,context);
    }


    private static void show(String massage, int show_length,Context context) {
        //使用布局加载器，将编写的toast_layout布局加载进来
        View view = LayoutInflater.from(context).inflate(R.layout.style_toast, null);
        //获取ImageView
        ImageView image = (ImageView) view.findViewById(R.id.toast_img);
        //设置图片
        //image.setImageResource(R.mipmap.cofe);
        //获取TextView
        TextView title = (TextView) view.findViewById(R.id.toast_text);
        //设置显示的内容
        title.setText(massage);
        Toast toast = new Toast(context);
        //设置Toast要显示的位置，水平居中并在底部，X轴偏移0个单位，Y轴偏移70个单位，
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
        //设置显示时间
        toast.setDuration(show_length);
        toast.setView(view);
        toast.show();
    }

}
