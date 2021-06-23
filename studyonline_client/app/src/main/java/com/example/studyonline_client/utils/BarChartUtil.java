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
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class BarChartUtil {

    private static final String TAG = "BarChartUtils";
    private BarChart bar_chart;
    private XAxis xAxis;
    public BarChartUtil(BarChart chart){
        this.bar_chart = chart;
        initX();
        initY();
    }

    private void initX(){
        bar_chart.setDrawGridBackground(false); // 是否显示表格颜色
        bar_chart.setTouchEnabled(true); // 设置是否可以触摸
        bar_chart.setDragEnabled(true);// 是否可以拖拽
        bar_chart.setScaleEnabled(true);// 是否可以缩放
        bar_chart.getDescription().setText("");//设置不显示右下角的描述
        bar_chart.setDrawBorders(false);//设置无边框
        bar_chart.setExtraBottomOffset(10); //偏移 为了使x轴的文字显示完全
        bar_chart.setDrawValueAboveBar(true);// 如果设置为true,在条形图上方显示值。如果为false，会显示在顶部下方。
        //设置阴影
        bar_chart.setDrawBarShadow(false);
        Legend legend = bar_chart.getLegend();//设置比例图
        legend.setEnabled(true); //设置是否显示比例图
        legend.setForm(Legend.LegendForm.CIRCLE);//图示 标签的形状。  圆
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        bar_chart.setPinchZoom(true);//设置按比例放缩柱状图
        bar_chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.i(TAG,"---"+e.getX()); //点击第几个柱子
            }

            @Override
            public void onNothingSelected() {

            }
        });

        ////获得x轴对象实例
        xAxis= bar_chart.getXAxis();
        xAxis.setDrawAxisLine(true); //设置显示x轴的线
        xAxis.setDrawGridLines(false); //设置是否显示网格
        xAxis.setGranularity(1f);//设置最小的区间，避免标签的迅速增多
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//数据位于底部
        xAxis.setTextColor(Color.BLACK);//设置x轴文字颜色
//        xAxis.setAxisMinimum(-0.5f);//设置离左边位置0.5个柱状图的宽度,否则最左侧的柱子会显示半个
    }

    //设置Y轴
    private void initY() {
        YAxis leftY = bar_chart.getAxisLeft();
        YAxis rightY = bar_chart.getAxisRight();
        leftY.setDrawAxisLine(true);//显示左侧y轴的线
        leftY.setTextSize(16);//显示左侧y轴字体大小
        leftY.setLabelCount(5, false);//设置左侧y轴显示文字数量
        //保证Y轴从0开始，不然会上移一点
        leftY.setAxisMinimum(0f);
        rightY.setAxisMinimum(0f);
        rightY.setEnabled(false);//设置y轴关闭
        //设置左侧Y轴上文字的样式


    }


    class CustomX extends ValueFormatter implements IAxisValueFormatter{
        private ArrayList<String> list;
        public CustomX(ArrayList<String> list ){
            this.list = list;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int v = (int) value;
            if (v<list.size()){
                return list.get(v);
            }else{
                return "";
            }
        }
    }

    public void showData(List<List<Float>> yValues, List<String> labels , List<Integer>  colors, ArrayList<String> xValues){
        BarData data = new BarData();
        for (int i = 0; i < yValues.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < yValues.get(i).size(); j++) {
                entries.add(new BarEntry(i+1, yValues.get(i).get(j)));
            }

            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));
            barDataSet.setColor(colors.get(i));
            barDataSet.setValueTextColor(colors.get(i));
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);

            CustomX customX = new CustomX(xValues);
            xAxis.setValueFormatter(customX);
            data.addDataSet(barDataSet);
        }
        int amount = yValues.size();

        float groupSpace = 0.12f; //柱状图组之间的间距
        float barSpace = (float) ((1 - 0.12) / amount / 10);
        float barWidth = (float) ((1 - 0.12) / amount / 10 * 9);

        // group"的宽度
        xAxis.setLabelCount(xValues.size() - 1, false);
        xAxis.setAxisMaximum(xValues.size());//不加的话柱子会显示不全
        xAxis.setAxisMinimum(0);
        data.setBarWidth(barWidth);
        data.groupBars(0, groupSpace, barSpace);
        bar_chart.setData(data);
    }

}
