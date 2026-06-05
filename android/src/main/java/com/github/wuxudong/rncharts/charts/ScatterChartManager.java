package com.github.wuxudong.rncharts.charts;

import android.content.Context;

import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.data.ScatterDataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

public class ScatterChartManager extends BarLineChartBaseManager<ScatterChart, Entry> {

    @Override
    public String getName() {
        return "RNScatterChart";
    }

    Context context;

    @Override
    protected ScatterChart createViewInstance(ThemedReactContext reactContext) {
        ScatterChart scatterChart = new ScatterChart(reactContext);
        context = reactContext;
        scatterChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(scatterChart));
        scatterChart.setOnChartGestureListener(new RNOnChartGestureListener(scatterChart));
        return scatterChart;
    }


    @Override
    DataExtract getDataExtract() {
        ScatterDataExtract dataExtract = new ScatterDataExtract();
        dataExtract.context = context;
        return dataExtract;
    }
}
