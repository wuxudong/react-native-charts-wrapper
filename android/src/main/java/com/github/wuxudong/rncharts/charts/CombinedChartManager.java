package com.github.wuxudong.rncharts.charts;


import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.Entry;
import com.github.wuxudong.rncharts.data.CombinedDataExtract;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

public class CombinedChartManager extends BarLineChartBaseManager<CombinedChart, Entry> {

    @Override
    public String getName() {
        return "RNCombinedChart";
    }

    @Override
    protected CombinedChart createViewInstance(ThemedReactContext reactContext) {
        CombinedChart combinedChart = new CombinedChart(reactContext);
        combinedChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(combinedChart));
        combinedChart.setOnChartGestureListener(new RNOnChartGestureListener(combinedChart));
        return combinedChart;
    }

    @Override
    DataExtract getDataExtract() {
        return new CombinedDataExtract();
    }
}
