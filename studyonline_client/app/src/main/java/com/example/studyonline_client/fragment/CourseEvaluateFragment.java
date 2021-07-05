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
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.activity.LoginActivity;
import com.example.studyonline_client.adapter.CommentAdapter;
import com.example.studyonline_client.model.CommentInfo;
import com.example.studyonline_client.model.CommentItemInfo;
import com.example.studyonline_client.model.CourseInfo;
import com.example.studyonline_client.model.EvaluateCourseStarInfo;
import com.example.studyonline_client.model.StudentInfo;
import com.example.studyonline_client.utils.BarChartUtil;
import com.example.studyonline_client.utils.ConstantUtil;
import com.example.studyonline_client.utils.JsonUtil;
import com.example.studyonline_client.utils.MyListView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CourseEvaluateFragment extends ListFragment implements View.OnClickListener {

    private ArrayList<CommentItemInfo> commentItemInfoArrayList;
    private CommentAdapter commentAdapter;
    private CommentInfo commentInfo;
    private String url = ConstantUtil.url+"comment";
    private EvaluateCourseStarInfo evaluateCourseStarInfo;

    private HorizontalBarChart horizontalBarChart;
    private List<BarEntry> list;
    private RatingBar ratingBar;
    private ListView listView;
    private Button publishComment;
    private EditText editTextComment;

    private int courseId;


    private void initView(View view){

        listView = view.findViewById(android.R.id.list);
        commentInfo = new CommentInfo();
        commentItemInfoArrayList = new ArrayList<>();
        evaluateCourseStarInfo = new EvaluateCourseStarInfo();

    }

    private void showBarChart() {

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
        Intent intent = getActivity().getIntent();
        courseId = intent.getIntExtra("id",0);
        getCommentList(courseId);
        listView.addHeaderView(getPersonalView());
        publishComment.setOnClickListener(this);
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
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                System.out.println(result);
                CommentItemInfo commentItemInfo = JSONObject.parseObject(result,CommentItemInfo.class);
                commentItemInfoArrayList.add(commentItemInfo);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        editTextComment.setText("");
                        ToastUtil.show("发布成功",getActivity());
                        commentAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private View getPersonalView(){
        View headView = getLayoutInflater().inflate(R.layout.fragment_evaluate_head, null);
        horizontalBarChart = headView.findViewById(R.id.hor_chart);
        publishComment = headView.findViewById(R.id.btn_course_comment);
        editTextComment = headView.findViewById(R.id.et_course_comment);
        ratingBar = headView.findViewById(R.id.ll_rb_star);
        showBarChart();
        postEvaluation();


        return headView;
    }

    private void getCommentList(int id){
        OkHttpUtil.usePostById(url+"/list",id).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                commentItemInfoArrayList = (ArrayList<CommentItemInfo>) JSONObject.parseArray(result,CommentItemInfo.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        commentAdapter = new CommentAdapter(getActivity(),commentItemInfoArrayList);
                        listView.setAdapter(commentAdapter);
                    }
                });
            }
        });
    }

    private void postEvaluation(){
        Map<String,Integer> map = new HashMap<>();
        map.put("courseId",courseId);
        map.put("studentId",LoginActivity.studentInfo.getId());
        String json = JSON.toJSONString(map);

        OkHttpUtil.usePost(CourseLiveFragment.url+"/star",json).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                evaluateCourseStarInfo = JSONObject.parseObject(result, EvaluateCourseStarInfo.class);
                System.out.println(evaluateCourseStarInfo);
                if (evaluateCourseStarInfo!=null && evaluateCourseStarInfo.getStatus()==1){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ratingBar.setRating(evaluateCourseStarInfo.getScore());
                            ratingBar.setIsIndicator(true);
                        }
                    });
                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                @Override
                                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                                    ratingBar.setIsIndicator(true);
                                    EvaluateCourseStarInfo evaluateCourseStar = new EvaluateCourseStarInfo();
                                    evaluateCourseStar.setScore(rating);
                                    evaluateCourseStar.setCourseId(courseId);
                                    evaluateCourseStar.setStatus(1);
                                    evaluateCourseStar.setStudentId(LoginActivity.studentInfo.getId());

                                    OkHttpUtil.usePost(CourseLiveFragment.url+"/evaluate",JsonUtil.objectToJson(evaluateCourseStar)).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            System.out.println(e);
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtil.show("您给本课程评了"+rating+"颗星，谢谢您的评分!",getContext());
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            }
        });

    }

}
