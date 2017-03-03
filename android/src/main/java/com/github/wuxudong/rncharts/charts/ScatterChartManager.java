package com.github.wuxudong.rncharts.charts;


import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.charts.ScatterChart.ScatterShape;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.data.ScatterDataExtract;
import com.github.wuxudong.rncharts.utils.BridgeUtils;
import com.github.wuxudong.rncharts.utils.ChartDataSetConfigUtils;

import java.util.ArrayList;

public class ScatterChartManager extends BarLineChartBaseManager<ScatterChart, Entry> {

    @Override
    public String getName() {
        return "RNScatterChart";
    }

    @Override
    protected ScatterChart createViewInstance(ThemedReactContext reactContext) {
        return new ScatterChart(reactContext);
    }


    @Override
    DataExtract getDataExtract() {
        return new ScatterDataExtract();
    }
}
