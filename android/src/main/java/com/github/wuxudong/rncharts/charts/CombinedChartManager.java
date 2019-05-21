package com.github.wuxudong.rncharts.charts;


import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.Entry;
import com.github.wuxudong.rncharts.data.CombinedDataExtract;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

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

    @ReactProp(name = "drawOrder")
    public void setDrawOrder(CombinedChart chart, ReadableArray array) {
        List<CombinedChart.DrawOrder> orders = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            orders.add(CombinedChart.DrawOrder.valueOf(array.getString(i)));
        }

        chart.setDrawOrder(orders.toArray(new CombinedChart.DrawOrder[orders.size()]));
    }

    @ReactProp(name = "drawValueAboveBar")
    public void setDrawValueAboveBar(CombinedChart chart, boolean enabled) {
        chart.setDrawValueAboveBar(enabled);
    }

    @ReactProp(name = "drawBarShadow")
    public void setDrawBarShadow(CombinedChart chart, boolean enabled) {
        chart.setDrawBarShadow(enabled);
    }

    @ReactProp(name = "highlightFullBarEnabled")
    public void setHighlightFullBarEnabled(CombinedChart chart, boolean enabled) {
        chart.setHighlightFullBarEnabled(enabled);
    }

    @Override
    DataExtract getDataExtract() {
        return new CombinedDataExtract();
    }
}
