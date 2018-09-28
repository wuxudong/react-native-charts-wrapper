package com.github.wuxudong.rncharts.charts;


import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.data.RadarDataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

public class RadarChartManager extends YAxisChartBase<RadarChart, RadarEntry> {

    @Override
    public String getName() {
        return "RNRadarChart";
    }

    @Override
    protected RadarChart createViewInstance(ThemedReactContext reactContext) {
        RadarChart radarChart = new RadarChart(reactContext);
        radarChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(radarChart));
        radarChart.setOnChartGestureListener(new RNOnChartGestureListener(radarChart));
        return radarChart;
    }

    @Override
    DataExtract getDataExtract() {
        return new RadarDataExtract();
    }

    @Override
    public void setYAxis(Chart chart, ReadableMap propMap) {
        RadarChart radarChart = (RadarChart) chart;
        YAxis axis = radarChart.getYAxis();

        setCommonAxisConfig(chart, axis, propMap);
        setYAxisConfig(axis, propMap);
    }

    @ReactProp(name = "skipWebLineCount")
    public void setSkipWebLineCount(RadarChart chart, int count) {
        chart.setSkipWebLineCount(count);
    }

    @ReactProp(name = "webLineWidth")
    public void setWebLineWidth(RadarChart chart, float width) {
        chart.setWebLineWidth(width);
    }

    @ReactProp(name = "webLineWidthInner")
    public void setWebLineWidthInner(RadarChart chart, float width) {
        chart.setWebLineWidthInner(width);
    }

    @ReactProp(name = "webAlpha")
    public void setWebAlpha(RadarChart chart, int alpha) {
        chart.setWebAlpha(alpha);
    }

    @ReactProp(name = "webColor")
    public void setWebColor(RadarChart chart, int color) {
        chart.setWebColor(color);
    }


    @ReactProp(name = "webColorInner")
    public void setWebColorInner(RadarChart chart, int color) {
        chart.setWebColorInner(color);
    }

    @ReactProp(name = "drawWeb")
    public void setDrawWeb(RadarChart chart, boolean enabled) {
        chart.setDrawWeb(enabled);
    }

    @ReactProp(name = "minOffset")
    public void setMinOffset(RadarChart chart, float minOffset) {
        chart.setMinOffset(minOffset);
    }

    @ReactProp(name = "rotationEnabled")
    public void setRotationEnabled(RadarChart chart, boolean enabled) {
        chart.setRotationEnabled(enabled);
    }

    @ReactProp(name = "rotationAngle")
    public void setRotationAngle(RadarChart chart, float angle) {
        chart.setRotationAngle(angle);
    }


}
