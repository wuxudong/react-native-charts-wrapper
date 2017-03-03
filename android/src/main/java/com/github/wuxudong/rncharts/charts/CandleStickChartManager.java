package com.github.wuxudong.rncharts.charts;

import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.wuxudong.rncharts.data.CandleDataExtract;
import com.github.wuxudong.rncharts.data.DataExtract;

public class CandleStickChartManager extends BarLineChartBaseManager<CandleStickChart, CandleEntry> {

    @Override
    public String getName() {
        return "RNCandleStickChart";
    }

    @Override
    protected CandleStickChart createViewInstance(ThemedReactContext reactContext) {
        return new CandleStickChart(reactContext);
    }


    @Override
    DataExtract getDataExtract() {
        return new CandleDataExtract();
    }
}
