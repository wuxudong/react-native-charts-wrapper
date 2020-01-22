package com.github.wuxudong.rncharts.listener;

import androidx.annotation.NonNull;
import android.view.MotionEvent;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartDataSetSelectedListener;
import com.github.mikephil.charting.highlight.DataSetHighlight;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.github.wuxudong.rncharts.charts.ChartGroupHolder;

import java.lang.ref.WeakReference;

public class RNOnChartDataSetSelectedListener implements OnChartDataSetSelectedListener {

    private WeakReference<Chart> mWeakChart;

    public RNOnChartDataSetSelectedListener(Chart chart) {
        this.mWeakChart = new WeakReference<>(chart);
    }

    @Override
    public void onDataSetSelected(DataSetHighlight d) {
        if (mWeakChart != null) {
            Chart chart = mWeakChart.get();

            WritableMap event = Arguments.createMap();
            event.putInt("dataSetIndex", d.getIndex());
            event.putDouble("dataSetPixelDistance", d.getPixelDistance());
            event.putDouble("dataSetPointDistance", d.getPointDistance());

            ReactContext reactContext = (ReactContext) chart.getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    chart.getId(),
                    "topSelect",
                    event);
        }
    }

    @Override
    public void onNothingSelected() {
        if (mWeakChart != null) {
            Chart chart = mWeakChart.get();

            ReactContext reactContext = (ReactContext) chart.getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    chart.getId(),
                    "topSelect",
                    null);
        }

    }
}
