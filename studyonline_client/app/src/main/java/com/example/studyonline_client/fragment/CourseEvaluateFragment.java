package com.example.studyonline_client.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.studyonline_client.R;
import com.example.studyonline_client.utils.BarChartUtil;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseEvaluateFragment extends Fragment implements View.OnClickListener {

    private RatingReviews ratingReviews;
    private HorizontalBarChart horizontalBarChart;
    private List<BarEntry> list;
    private ImageButton buttonOne;
    private ImageButton buttonTwo;
    private ImageButton buttonThree;
    private ImageButton buttonFour;
    private ImageButton buttonFive;
    private LinearLayout linearLayoutView;

    private void initView(View view){
        horizontalBarChart = view.findViewById(R.id.hor_chart);
        buttonOne = view.findViewById(R.id.one_star);
        buttonTwo = view.findViewById(R.id.two_star);
        buttonThree = view.findViewById(R.id.three_star);
        buttonFour = view.findViewById(R.id.four_star);
        buttonFive = view.findViewById(R.id.five_star);
        linearLayoutView = view.findViewById(R.id.dis_view);
    }


    private void showBarChart(){

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
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one_star:
                buttonOne.setBackground(getResources().getDrawable(R.drawable.ok_star));
                break;
            case R.id.two_star:
                buttonTwo.setBackground(getResources().getDrawable(R.drawable.ok_star));
                buttonOne.setBackground(getResources().getDrawable(R.drawable.ok_star));
                break;
            case R.id.three_star:
                    buttonTwo.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    buttonOne.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    buttonThree.setBackground(getResources().getDrawable(R.drawable.ok_star));
                break;
            case R.id.four_star:
                    buttonFour.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    buttonTwo.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    buttonOne.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    buttonThree.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    linearLayoutView.setVisibility(View.GONE);
                break;
            case R.id.five_star:
                    buttonFour.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    buttonTwo.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    buttonOne.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    buttonThree.setBackground(getResources().getDrawable(R.drawable.ok_star));
                    buttonFive.setBackground(getResources().getDrawable(R.drawable.ok_star));
                break;
        }
    }
}
