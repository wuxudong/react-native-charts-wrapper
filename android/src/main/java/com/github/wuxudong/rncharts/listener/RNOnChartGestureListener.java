package com.github.wuxudong.rncharts.listener;

import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.github.wuxudong.rncharts.charts.YAxisChartBase;
import com.github.wuxudong.rncharts.utils.EntryToWritableMapUtils;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudong on 07/03/2017.
 */

public class RNOnChartGestureListener implements OnChartGestureListener {

    private WeakReference<Chart> mWeakChart;

    public RNOnChartGestureListener(Chart chart) {
        mWeakChart = new WeakReference<>(chart);
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        sendEvent("chartScaled");
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        sendEvent("chartTranslated");
    }

    private void sendEvent(String action) {
        if (mWeakChart != null) {
            Chart chart = mWeakChart.get();

            WritableMap event = getEvent(action, chart);

            ReactContext reactContext = (ReactContext) chart.getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    chart.getId(),
                    "topChange",
                    event);
        }
    }

    @NonNull
    private WritableMap getEvent(String action, Chart chart) {
        WritableMap event = Arguments.createMap();

        event.putString("action", action);

        if (chart instanceof BarLineChartBase) {
            ViewPortHandler viewPortHandler = chart.getViewPortHandler();
            event.putDouble("scaleX", chart.getScaleX());
            event.putDouble("scaleY", chart.getScaleY());

            MPPointD center = ((BarLineChartBase) chart).getValuesByTouchPoint(viewPortHandler.getContentCenter().getX(), viewPortHandler.getContentCenter().getY(), YAxis.AxisDependency.LEFT);
            event.putDouble("centerX", center.x);
            event.putDouble("centerY", center.y);

            MPPointD leftBottom = ((BarLineChartBase) chart).getValuesByTouchPoint(viewPortHandler.contentLeft(), viewPortHandler.contentBottom(), YAxis.AxisDependency.LEFT);
            MPPointD rightTop = ((BarLineChartBase) chart).getValuesByTouchPoint(viewPortHandler.contentRight(), viewPortHandler.contentTop(), YAxis.AxisDependency.LEFT);

            event.putDouble("left", leftBottom.x);
            event.putDouble("bottom", leftBottom.y);
            event.putDouble("right", rightTop.x);
            event.putDouble("top", rightTop.y);
        }
        return event;
    }
}
