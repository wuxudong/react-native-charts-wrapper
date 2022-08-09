package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class ConfigurableMinimumLinePositionFillFormatter implements IFillFormatter {
    private final float min;

    public ConfigurableMinimumLinePositionFillFormatter(float min) {

        this.min = min;
    }

    @Override
    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
        return min;
    }
}
