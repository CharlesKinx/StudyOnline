package com.example.studyonline_client.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.studyonline_client.R;
import com.example.studyonline_client.activity.LoginActivity;
import com.example.studyonline_client.model.CourseScoreAnalysisInfo;
import com.example.studyonline_client.model.ScoreInfo;
import com.example.studyonline_client.utils.BarChartUtil;
import com.example.studyonline_client.utils.OkHttpUtil;
import com.example.studyonline_client.utils.StringAxisValueFormatterUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ScoreAnalysisFragment extends Fragment {

    private BarChart barChart;
    private RadarChart radarChart;
    private CourseScoreAnalysisInfo courseScoreAnalysisInfo;
    private ArrayList<String> labels;
    private BarChartUtil barChartUtil;
    List<RadarEntry> radarEntryList;
    List<RadarEntry> radarEntryList1;


    private void showRadarChart(ArrayList<ScoreInfo> myScore,ArrayList<ScoreInfo> maxScore){

        radarEntryList=new ArrayList<>();
        radarEntryList1=new ArrayList<>();
        labels = new ArrayList<>();
        for(int i =0;i<maxScore.size();i++){
            radarEntryList.add(new RadarEntry(maxScore.get(i).getScore()));
            radarEntryList1.add(new RadarEntry(myScore.get(i).getScore()));
        }


        for(ScoreInfo scoreInfo:maxScore){
            labels.add(scoreInfo.getCourseName());
        }

        RadarDataSet radarDataSet=new RadarDataSet(radarEntryList,"???????????????");
        radarDataSet.setColor(Color.parseColor("#F41F83"));
        radarDataSet.setDrawFilled(true);
        radarDataSet.setFillColor(Color.parseColor("#F46DAD"));
        radarDataSet.setLineWidth(1f);

        RadarDataSet radarDataSet1=new RadarDataSet(radarEntryList1,"????????????");
        radarDataSet1.setColor(Color.parseColor("#2BED78"));
        radarDataSet1.setDrawFilled(true);
        radarDataSet1.setFillColor(Color.parseColor("#A2F4C3"));
        radarDataSet1.setLineWidth(1f);

        RadarData radarData=new RadarData(radarDataSet);
        radarData.addDataSet(radarDataSet1);
        radarChart.setData(radarData);

        radarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        //Y????????????????????????????????????????????????????????????Y????????????
        radarChart.getYAxis().setAxisMinimum(0);
        radarChart.getDescription().setEnabled(false);    //????????????????????????????????????

        radarChart.notifyDataSetChanged();
        radarChart.invalidate();
        radarChart.animateY(2000);

    }

    private void initView(View view){
        barChart = view.findViewById(R.id.bar_chart);
        radarChart = view.findViewById(R.id.radar_chart);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_analysis,container,false);
        initView(view);
        courseScoreAnalysisInfo = new CourseScoreAnalysisInfo();
        getScoreData();
        barChartUtil = new BarChartUtil(barChart);
        return view;
    }


    private void getScoreData(){
        OkHttpUtil.usePostById(CourseLiveFragment.url+"/score", LoginActivity.studentInfo.getId()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                courseScoreAnalysisInfo = JSONObject.parseObject(result,CourseScoreAnalysisInfo.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        barChartUtil.showBarChart(courseScoreAnalysisInfo.getMyScore(),courseScoreAnalysisInfo.getAverageScore());
                        showRadarChart(courseScoreAnalysisInfo.getMyScore(),courseScoreAnalysisInfo.getMaxScore());
                    }
                });
            }
        });
    }
}
