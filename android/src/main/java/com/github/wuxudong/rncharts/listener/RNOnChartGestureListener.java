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
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.github.wuxudong.rncharts.charts.ChartGroupHolder;

import java.lang.ref.WeakReference;

/**
 * Created by xudong on 07/03/2017.
 */
public class RNOnChartGestureListener implements OnChartGestureListener {

    private WeakReference<Chart> mWeakChart;

    private String group = null;

    private String identifier = null;

    public RNOnChartGestureListener(Chart chart) {
        this.mWeakChart = new WeakReference<>(chart);
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
        sendEvent("doubleTapped", me);
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        sendEvent("chartScaled", me);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        sendEvent("chartTranslated", me);
    }

    private void sendEvent(String action, MotionEvent me) {
        Chart chart = mWeakChart.get();
        if (chart != null) {

            WritableMap event = getEvent(action, me, chart);

            ReactContext reactContext = (ReactContext) chart.getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    chart.getId(),
                    "topChange",
                    event);
        }
    }

    @NonNull
    private WritableMap getEvent(String action, MotionEvent me, Chart chart) {
        WritableMap event = Arguments.createMap();

        event.putString("action", action);

        if (chart instanceof BarLineChartBase) {
            BarLineChartBase barLineChart = (BarLineChartBase) chart;
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

            if (group != null && identifier != null) {
                ChartGroupHolder.sync(group, identifier, chart.getScaleX(), chart.getScaleY(), (float) center.x, (float) center.y);

            }
        }
        return event;
    }
}
