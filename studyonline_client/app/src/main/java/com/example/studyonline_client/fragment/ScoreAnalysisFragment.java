package com.example.studyonline_client.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.studyonline_client.R;
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

import java.util.ArrayList;
import java.util.List;

public class ScoreAnalysisFragment extends Fragment {
    private BarChart barChart;
    private RadarChart radarChart;
    List<BarEntry> list;
    List<BarEntry>list2;

    List<RadarEntry> radarEntryList;
    List<RadarEntry> radarEntryList1;
    private String labels[] = {"","高数","英语","语文","物理","化学","Java","Python"};
    private void showBarChart(View view){
        list=new ArrayList<>();
        list2=new ArrayList<>();
        barChart = view.findViewById(R.id.bar_chart);

        //为第一组添加数据
        list.add(new BarEntry(0,89));
        list.add(new BarEntry(1,76));
        list.add(new BarEntry(2,58));
        list.add(new BarEntry(3,78));
        list.add(new BarEntry(4,100));
        list.add(new BarEntry(5,78));
        list.add(new BarEntry(6,86));

        //为第二组添加数据
        list2.add(new BarEntry(0,58));
        list2.add(new BarEntry(1,79));
        list2.add(new BarEntry(2,86));
        list2.add(new BarEntry(3,87));
        list2.add(new BarEntry(4,86));
        list2.add(new BarEntry(5,88));
        list2.add(new BarEntry(6,99));




        BarDataSet barDataSet=new BarDataSet(list,"平均成绩");
        barDataSet.setColor(Color.parseColor("#36D0E6"));//为第一组柱子设置颜色
        BarDataSet barDataSet2=new BarDataSet(list2,"我的成绩");
        barDataSet2.setColor(Color.parseColor("#33FAC1"));   //为第二组柱子设置颜色

        BarData barData=new BarData(barDataSet);   //加上第一组
        barData.addDataSet(barDataSet2);    //加上第二组   （多组也可以用同样的方法）
        barChart.setData(barData);

        barData.setBarWidth(0.4f);//柱子的宽度
        //重点！   三个参数   分别代表   X轴起点     组与组之间的间隔      组内柱子的间隔
        barData.groupBars(1f,0.2f,0);

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setCenterAxisLabels(true);   //设置柱子（柱子组）居中对齐X轴上的点
        barChart.setDrawGridBackground(false);
        barChart.getXAxis().setCenterAxisLabels(true);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getAxisLeft().setAxisMaximum(120);
        barChart.getXAxis().setAxisMaximum(8);   //X轴最大数值
        barChart.getXAxis().setAxisMinimum(1);   //X轴最小数值
        //X轴坐标的个数    第二个参数一般填false     true表示强制设置标签数 可能会导致X轴坐标显示不全等问题
        barChart.getXAxis().setGranularity(1);
        barChart.getXAxis().setLabelCount(7,false);
        barChart.getDescription().setEnabled(false);    //右下角一串英文字母不显示
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);   //X轴的位置设置为下  默认为上
        barChart.getAxisRight().setEnabled(false);     //右侧Y轴不显示   默认为显示




        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.notifyDataSetChanged();
        barChart.invalidate();
        barChart.animateY(2000);
    }

    private void showRadarChart(View view){
        radarChart = view.findViewById(R.id.radar_chart);

        radarEntryList=new ArrayList<>();
        radarEntryList1=new ArrayList<>();

        radarEntryList.add(new RadarEntry(70));
        radarEntryList.add(new RadarEntry(35));
        radarEntryList.add(new RadarEntry(40));
        radarEntryList.add(new RadarEntry(85));
        radarEntryList.add(new RadarEntry(20));
        radarEntryList.add(new RadarEntry(35));
        radarEntryList.add(new RadarEntry(90));

        radarEntryList1.add(new RadarEntry(30));
        radarEntryList1.add(new RadarEntry(35));
        radarEntryList1.add(new RadarEntry(80));
        radarEntryList1.add(new RadarEntry(35));
        radarEntryList1.add(new RadarEntry(70));
        radarEntryList1.add(new RadarEntry(65));
        radarEntryList1.add(new RadarEntry(20));


        RadarDataSet radarDataSet=new RadarDataSet(radarEntryList,"各科最高分");
        radarDataSet.setColor(Color.parseColor("#F41F83"));
        radarDataSet.setDrawFilled(true);
        radarDataSet.setFillColor(Color.parseColor("#F46DAD"));
        radarDataSet.setLineWidth(1f);

        RadarDataSet radarDataSet1=new RadarDataSet(radarEntryList1,"我的成绩");
        radarDataSet1.setColor(Color.parseColor("#2BED78"));
        radarDataSet1.setDrawFilled(true);
        radarDataSet1.setFillColor(Color.parseColor("#A2F4C3"));
        radarDataSet1.setLineWidth(1f);

        RadarData radarData=new RadarData(radarDataSet);
        radarData.addDataSet(radarDataSet1);
        radarChart.setData(radarData);

        radarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        //Y轴最小值不设置会导致数据中最小值默认成为Y轴最小值
        radarChart.getYAxis().setAxisMinimum(0);
        radarChart.getDescription().setEnabled(false);    //右下角一串英文字母不显示

        radarChart.notifyDataSetChanged();
        radarChart.invalidate();
        radarChart.animateY(2000);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_analysis,container,false);
        showBarChart(view);
        showRadarChart(view);
        return view;
    }
}
