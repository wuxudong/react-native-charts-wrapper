package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class CustomFormatter implements IAxisValueFormatter {

    private String mFormat;

    public CustomFormatter(String value) {
        this.mFormat = value;
    }

    @Override
    public String getFormattedValue(float value, AxisBase yAxis) {
        return String.format(mFormat, value);
    }
}
