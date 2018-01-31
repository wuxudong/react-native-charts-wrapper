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
 * Created by yangdong on 01/31/2018.
 */

public class DateSinceFormatter implements IAxisValueFormatter, IValueFormatter {

    private static int MILLSECONDS = 1000;
    private DateFormat mFormat;
    private long mMillsecSinceThen;

    public DateSinceFormatter(String pattern, long millsecSinceThen) {
        mFormat = new SimpleDateFormat(pattern);
        mMillsecSinceThen = millsecSinceThen;
    }

    @Override
    public String getFormattedValue(float value, AxisBase yAxis) {
        return format((long) (value * MILLSECONDS + mMillsecSinceThen));
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return format((long) (value * MILLSECONDS + mMillsecSinceThen));
    }

    private String format(long seconds) {
        return mFormat.format(new Date(seconds * MILLSECONDS + mMillsecSinceThen));
    }
}