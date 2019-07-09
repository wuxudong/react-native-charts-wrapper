package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by dougl on 05/09/2017.
 */
public class DateFormatter extends ValueFormatter {

    private DateFormat mFormat;

    private long since = 0;

    private TimeUnit timeUnit;

    public DateFormatter(String pattern, long since, TimeUnit timeUnit) {
        mFormat = new SimpleDateFormat(pattern);

        this.since = since;

        this.timeUnit = timeUnit;
    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(new Date(since + timeUnit.toMillis((long) value)));
    }
}