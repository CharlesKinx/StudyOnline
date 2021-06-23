package com.example.studyonline_client.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

public class StringAxisValueFormatterUtil extends IndexAxisValueFormatter {

    @Override
    public String getFormattedValue(float value) {
        return super.getFormattedValue(value);
    }

    private final String[] mLabels;
    public StringAxisValueFormatterUtil(String[] labels) {
        mLabels = labels;
    }
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        try {
            return mLabels[(int) value];
        } catch (Exception e) {
            e.printStackTrace();
            return mLabels[0];
        }
    }
}
