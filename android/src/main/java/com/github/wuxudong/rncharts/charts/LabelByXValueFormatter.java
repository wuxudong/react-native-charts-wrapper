package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.Map;

public class LabelByXValueFormatter extends ValueFormatter {
    private Map<Float, String> labelsByXValue;

    public LabelByXValueFormatter(Map<Float, String> labelsByXValue) {
        this.labelsByXValue = labelsByXValue;
    }

    @Override
    public String getFormattedValue(float value) {
        String label = this.labelsByXValue.get(Float.valueOf(value));
        return (label != null) ? label : "";
    }

    @Override
    public String getPointLabel(Entry entry) {
        return this.getFormattedValue(entry.getX());
    }
}
