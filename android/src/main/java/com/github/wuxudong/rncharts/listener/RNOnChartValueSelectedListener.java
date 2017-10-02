package com.github.wuxudong.rncharts.listener;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.utils.EntryToWritableMapUtils;

import java.lang.ref.WeakReference;

/**
 * Created by xudong on 07/03/2017.
 */

public class RNOnChartValueSelectedListener implements OnChartValueSelectedListener {

    private WeakReference<Chart> mWeakChart;

    public RNOnChartValueSelectedListener(Chart chart) {
        mWeakChart = new WeakReference<>(chart);
    }

    @Override
    public void onValueSelected(Entry entry, Highlight h) {

        if (mWeakChart != null) {
            Chart chart = mWeakChart.get();

            ReactContext reactContext = (ReactContext) chart.getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    chart.getId(),
                    "topSelect",
                    EntryToWritableMapUtils.convertEntryToWritableMap(entry));
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
