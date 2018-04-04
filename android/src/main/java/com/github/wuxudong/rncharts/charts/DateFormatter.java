package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dougl on 05/09/2017.
 */
public class DateFormatter implements IAxisValueFormatter, IValueFormatter {

    private DateFormat mFormat;

    public DateFormatter(String pattern) {
        mFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public String getFormattedValue(float value, AxisBase yAxis) {
        return format((long) value);
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return format((long) value);
    }

    private String format(long millis) {
        return mFormat.format(new Date(millis));
    }
}