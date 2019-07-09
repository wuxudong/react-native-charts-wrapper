package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.Collection;

/**
 * This formatter is used for passing an array of x-axis labels, on whole x steps.
 */
public class IndexValueFormatter extends ValueFormatter {
    private String[] mValues = new String[]{};
    private int mValueCount = 0;

    /**
     * An empty constructor.
     * Use `setValues` to set the axis labels.
     */
    public IndexValueFormatter() {
    }

    /**
     * Constructor that specifies value labels.
     *
     * @param values The values string array
     */
    public IndexValueFormatter(String[] values) {
        if (values != null)
            setValues(values);
    }

    /**
     * Constructor that specifies value labels.
     *
     * @param values The values string array
     */
    public IndexValueFormatter(Collection<String> values) {
        if (values != null)
            setValues(values.toArray(new String[values.size()]));
    }

    public String[] getValues() {
        return mValues;
    }

    public void setValues(String[] values) {
        if (values == null)
            values = new String[]{};

        this.mValues = values;
        this.mValueCount = values.length;
    }

    @Override
    public String getBarLabel(BarEntry barEntry) {
        return getFormattedValue(barEntry);
    }

    @Override
    public String getBarStackedLabel(float value, BarEntry stackedEntry) {
        return getFormattedValue(stackedEntry);
    }

    @Override
    public String getPointLabel(Entry entry) {
        return getFormattedValue(entry);
    }

    @Override
    public String getPieLabel(float value, PieEntry pieEntry) {
        return getFormattedValue(pieEntry);
    }

    @Override
    public String getRadarLabel(RadarEntry radarEntry) {
        return getFormattedValue(radarEntry);
    }

    @Override
    public String getBubbleLabel(BubbleEntry bubbleEntry) {
        return getFormattedValue(bubbleEntry);
    }

    @Override
    public String getCandleLabel(CandleEntry candleEntry) {
        return getFormattedValue(candleEntry);
    }

    private String getFormattedValue(Entry entry) {
        int index = Math.round(entry.getX());

        if (index < 0 || index >= mValueCount || index != (int) entry.getX())
            return "";

        return mValues[index];
    }
}
