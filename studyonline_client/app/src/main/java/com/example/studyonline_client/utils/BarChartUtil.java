package com.example.studyonline_client.utils;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class BarChartUtil {

    private String labels[] = {"","高数","英语","语文","物理","化学","Java","Python"};

    List<BarEntry> list;
    List<BarEntry>list2;

    private BarChart barChart;

    public BarChartUtil(BarChart chart){
        this.barChart = chart;
        initView();

    }

    private void initView(){
        list=new ArrayList<>();
        list2=new ArrayList<>();

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


    public void setData(String[] courses, List<Float> list1, List<Float> list2){

    }
}
