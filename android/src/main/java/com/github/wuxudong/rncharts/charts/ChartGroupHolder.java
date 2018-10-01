package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.jobs.ZoomJob;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ChartGroupHolder {

    private static Map<String, Map<String, ChartHolder>> chartGroups = new HashMap<>();

    private static class ChartHolder {
        public WeakReference<BarLineChartBase> chart;
        public boolean syncX;
        public boolean syncY;

        public ChartHolder(WeakReference<BarLineChartBase> chart, boolean syncX, boolean syncY) {
            this.chart = chart;
            this.syncX = syncX;
            this.syncY = syncY;
        }
    }

    public static synchronized void addChart(String group, String identifier, BarLineChartBase chart, boolean syncX, boolean syncY) {
        if (!chartGroups.containsKey(group)) {
            chartGroups.put(group, new HashMap<String, ChartHolder>());
        }

        chartGroups.get(group).put(identifier, new ChartHolder(new WeakReference<>(chart), syncX, syncY));
    }

    public static synchronized void removeChart(String group, String identifier) {
        if (chartGroups.containsKey(group)) {
            chartGroups.get(group).remove(identifier);
        }
    }

    // sync gesture to other chart in the same group
    public static synchronized void sync(String group, String identifier, float scaleX, float scaleY, float centerX, float centerY, boolean performImmediately) {

        Map<String, ChartHolder> referenceMap = chartGroups.get(group);
        if (referenceMap != null) {
            for (Map.Entry<String, ChartHolder> entry : referenceMap.entrySet()) {
                if (!entry.getKey().equals(identifier)) {
                    ChartHolder chartHolder = entry.getValue();
                    WeakReference<BarLineChartBase> reference = chartHolder.chart;
                    BarLineChartBase chart = reference.get();
                    if (chart != null) {

                        YAxis.AxisDependency axis = chart.getAxisLeft().isEnabled() ? YAxis.AxisDependency.LEFT : YAxis.AxisDependency.RIGHT;
                        Transformer transformer = chart.getTransformer(axis);

                        float zoomScaleX = scaleX / chart.getScaleX();
                        float zoomScaleY = scaleY / chart.getScaleY();

                        MPPointF originalCenter = chart.getCenter();
                        MPPointD originalCenterValue = chart.getValuesByTouchPoint(originalCenter.x, originalCenter.y, axis);

                        float finalScaleX = chartHolder.syncX ? zoomScaleX : 1;
                        float finalScaleY = chartHolder.syncY ? zoomScaleY : 1;

                        float finalCenterX = chartHolder.syncX ? centerX : (float) originalCenterValue.x;
                        float finalCenterY = chartHolder.syncY ? centerY : (float) originalCenterValue.y;

                        if (performImmediately) {
                            ZoomJob.getInstance(chart.getViewPortHandler(), finalScaleX, finalScaleY, finalCenterX, finalCenterY, transformer, axis, chart).run();
                        } else {
                            chart.zoom(finalScaleX, finalScaleY, finalCenterX, finalCenterY);
                        }

                    }
                }
            }
        }
    }
}
