package com.example.studyonline_client.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.studyonline_client.R;
import com.example.studyonline_client.activity.LoginActivity;
import com.example.studyonline_client.model.CommentInfo;
import com.example.studyonline_client.utils.BarChartUtil;
import com.example.studyonline_client.utils.JsonUtil;
import com.example.studyonline_client.utils.OkHttpUtil;
import com.example.studyonline_client.utils.ToastUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.taufiqrahman.reviewratings.BarLabels;
import com.taufiqrahman.reviewratings.RatingReviews;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CourseEvaluateFragment extends Fragment implements View.OnClickListener {

    private HorizontalBarChart horizontalBarChart;
    private List<BarEntry> list;
    private RatingBar ratingBar;
    private Button publishComment;
    private EditText editTextComment;
    private CommentInfo commentInfo;
    private int courseId;
    private String url = "http://10.0.116.13:8181/comment";

    private void initView(View view){
        horizontalBarChart = view.findViewById(R.id.hor_chart);
        ratingBar = view.findViewById(R.id.ll_rb_star);
        publishComment = view.findViewById(R.id.btn_course_comment);
        editTextComment = view.findViewById(R.id.et_course_comment);
        commentInfo = new CommentInfo();
    }


    private void showBarChart()
    {

        String[] labels = {"",
                "5★ ",
                "4★ ",
                "3★ ",
                "2★ ",
                "1★ "};
        list = new ArrayList<>();
        list.add(new BarEntry(1,89));
        list.add(new BarEntry(2,85));
        list.add(new BarEntry(3,46));
        list.add(new BarEntry(4,52));
        list.add(new BarEntry(5,48));

        BarDataSet barDataSet=new BarDataSet(list,"评价人数");
        barDataSet.setColor(Color.parseColor("#606060"));
        BarData barData=new BarData(barDataSet);
        horizontalBarChart.setData(barData);

        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int)value)+"";
            }
        });


        horizontalBarChart.getDescription().setEnabled(false);//隐藏右下角英文
        horizontalBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为右边
        horizontalBarChart.getAxisLeft().setEnabled(false);//隐藏上侧Y轴   默认是上下两侧都有Y轴
        barData.setBarWidth(0.8f);//柱子的宽度
        //重点！   三个参数   分别代表   X轴起点     组与组之间的间隔      组内柱子的间隔

        horizontalBarChart.getXAxis().setDrawGridLines(false);
        //horizontalBarChart.getXAxis().setCenterAxisLabels(true);   //设置柱子（柱子组）居中对齐X轴上的点
        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.getXAxis().setCenterAxisLabels(true);
        horizontalBarChart.getAxisLeft().setAxisMinimum(0);
        horizontalBarChart.getAxisLeft().setAxisMaximum(100);
        horizontalBarChart.getXAxis().setAxisMaximum(6);   //X轴最大数值
        horizontalBarChart.getXAxis().setAxisMinimum(0);   //X轴最小数值

        //X轴坐标的个数    第二个参数一般填false     true表示强制设置标签数 可能会导致X轴坐标显示不全等问题
        horizontalBarChart.getXAxis().setGranularity(1);
        horizontalBarChart.getXAxis().setLabelCount(5,false);
        horizontalBarChart.getDescription().setEnabled(false);    //右下角一串英文字母不显示
        //horizontalBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);   //X轴的位置设置为下  默认为上
        horizontalBarChart.getAxisRight().setEnabled(false);     //右侧Y轴不显示   默认为显示
        horizontalBarChart.getXAxis().setCenterAxisLabels(false);

        horizontalBarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        horizontalBarChart.notifyDataSetChanged();
        horizontalBarChart.invalidate();
        horizontalBarChart.animateY(2000);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_evaluate,container,false);
//

        initView(view);
        showBarChart();
        Intent intent = getActivity().getIntent();
        courseId = intent.getIntExtra("id",0);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ToastUtil.show("你给本课程评了"+rating+"星，谢谢您的评分",getContext());
                ratingBar.setIsIndicator(true);

                //linearLayoutView.setVisibility(View.GONE);
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_course_comment:
                    SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String datetime = tempDate.format(new java.util.Date());
                    commentInfo.setCourseId(courseId);
                    commentInfo.setStudentId(LoginActivity.studentInfo.getId());
                    commentInfo.setContent(editTextComment.getText().toString());
                    commentInfo.setTime(datetime);
                    postComment(commentInfo);
                    break;
        }
    }

    private void postComment(CommentInfo commentInfo){
        OkHttpUtil.usePost(url+"/publish", JsonUtil.objectToJson(commentInfo)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }


}
