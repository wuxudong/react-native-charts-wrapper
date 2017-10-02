package com.github.wuxudong.rncharts.listener;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.wuxudong.rncharts.utils.EntryToWritableMapUtils;

import java.lang.ref.WeakReference;

/**
 * Created by xudong on 07/03/2017.
 */

public class RNOnChartGestureListener implements OnChartGestureListener {

    private WeakReference<Chart> mWeakChart;

    public RNOnChartGestureListener(Chart chart) {
        mWeakChart = new WeakReference<>(chart);
    }

    // @Override
    // public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
    //   if (mWeakChart != null) {
    //       Chart chart = mWeakChart.get();
    //
    //       ReactContext reactContext = (ReactContext) chart.getContext();
    //       reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
    //               chart.getId(),
    //               "topSelect",
    //               EntryToWritableMapUtils.convertEntryToWritableMap(entry));
    //   }
    // }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
      if (mWeakChart != null) {
          Chart chart = mWeakChart.get();

          ReactContext reactContext = (ReactContext) chart.getContext();
          reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                  chart.getId(),
                  "onChartGestureEnd",
                  lastPerformedGesture);
      }
    }

    // @Override
    // public void onChartLongPressed(MotionEvent me) {
    //   if (mWeakChart != null) {
    //       Chart chart = mWeakChart.get();
    //
    //       ReactContext reactContext = (ReactContext) chart.getContext();
    //       reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
    //               chart.getId(),
    //               "topSelect",
    //               EntryToWritableMapUtils.convertEntryToWritableMap(entry));
    //   }
    // }
    //
    // @Override
    // public void onChartDoubleTapped(MotionEvent me) {
    //   if (mWeakChart != null) {
    //       Chart chart = mWeakChart.get();
    //
    //       ReactContext reactContext = (ReactContext) chart.getContext();
    //       reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
    //               chart.getId(),
    //               "topSelect",
    //               EntryToWritableMapUtils.convertEntryToWritableMap(entry));
    //   }
    // }
    //
    // @Override
    // public void onChartSingleTapped(MotionEvent me) {
    //   if (mWeakChart != null) {
    //       Chart chart = mWeakChart.get();
    //
    //       ReactContext reactContext = (ReactContext) chart.getContext();
    //       reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
    //               chart.getId(),
    //               "topSelect",
    //               EntryToWritableMapUtils.convertEntryToWritableMap(entry));
    //   }
    // }
    //
    // @Override
    // public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
    //   if (mWeakChart != null) {
    //       Chart chart = mWeakChart.get();
    //
    //       ReactContext reactContext = (ReactContext) chart.getContext();
    //       reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
    //               chart.getId(),
    //               "topSelect",
    //               EntryToWritableMapUtils.convertEntryToWritableMap(entry));
    //   }
    // }
    //
    // @Override
    // public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
    //   if (mWeakChart != null) {
    //       Chart chart = mWeakChart.get();
    //
    //       ReactContext reactContext = (ReactContext) chart.getContext();
    //       reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
    //               chart.getId(),
    //               "topSelect",
    //               EntryToWritableMapUtils.convertEntryToWritableMap(entry));
    //   }
    // }
    //
    // @Override
    // public void onChartTranslate(MotionEvent me, float dX, float dY) {
    //   if (mWeakChart != null) {
    //       Chart chart = mWeakChart.get();
    //
    //       ReactContext reactContext = (ReactContext) chart.getContext();
    //       reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
    //               chart.getId(),
    //               "topSelect",
    //               EntryToWritableMapUtils.convertEntryToWritableMap(entry));
    //   }
    // }
}
