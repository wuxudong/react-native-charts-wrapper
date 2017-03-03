package com.github.wuxudong.rncharts.charts;


import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.wuxudong.rncharts.data.BubbleDataExtract;
import com.github.wuxudong.rncharts.data.DataExtract;

public class BubbleChartManager extends ChartBaseManager<BubbleChart, BubbleEntry> {

    @Override
    public String getName() {
        return "RNBubbleChart";
    }

    @Override
    protected BubbleChart createViewInstance(ThemedReactContext reactContext) {
        return new BubbleChart(reactContext);
    }


    @Override
    DataExtract getDataExtract() {
        return new BubbleDataExtract();
    }
}
