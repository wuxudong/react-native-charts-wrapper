package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class CustomFormatter extends ValueFormatter {

    private DecimalFormat mFormat;

    public CustomFormatter(String value) {
        mFormat = new DecimalFormat(value);
    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value);
    }

}
