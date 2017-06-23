package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeFormatter implements IAxisValueFormatter {
    private int mInterval;
    private Date mStartTime;
    private String mFormat;

    public DateTimeFormatter(long startTimeStamp, String format) {
        mStartTime = new Date(startTimeStamp);
        mFormat = format;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Calendar c = Calendar.getInstance();
        c.setTime(mStartTime);
        c.add(Calendar.SECOND, (int) value);
        Date axisTime = c.getTime();

        DateFormat df = new SimpleDateFormat(format);
        return df.format(axisTime);
//            DateFormat df = new SimpleDateFormat("HH:mm:ss");
//            DateFormat df = new SimpleDateFormat("HH:mm");
//        DateFormat df = new SimpleDateFormat("dd/MM");
    }
}
