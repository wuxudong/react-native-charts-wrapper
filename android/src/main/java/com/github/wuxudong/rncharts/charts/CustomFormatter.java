package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class CustomFormatter implements IAxisValueFormatter, IValueFormatter {

    private DecimalFormat mFormat;

    public CustomFormatter(String value) {
        mFormat = new DecimalFormat(value);
    }

    @Override
    public String getFormattedValue(float value, AxisBase yAxis) {
        return mFormat.format(value);
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value);
    }
}
