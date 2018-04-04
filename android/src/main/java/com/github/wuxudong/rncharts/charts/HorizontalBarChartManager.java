package com.github.wuxudong.rncharts.charts;

import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

public class HorizontalBarChartManager extends BarChartManager {

    @Override
    public String getName() {
        return "RNHorizontalBarChart";
    }

    @Override
    protected BarChart createViewInstance(ThemedReactContext reactContext) {
        HorizontalBarChart horizontalBarChart = new HorizontalBarChart(reactContext);
        horizontalBarChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(horizontalBarChart));
        horizontalBarChart.setOnChartGestureListener(new RNOnChartGestureListener(horizontalBarChart));
        return horizontalBarChart;
    }
}
