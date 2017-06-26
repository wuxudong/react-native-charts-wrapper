package com.github.wuxudong.rncharts.charts;

import android.content.res.ColorStateList;
import android.os.Build;
import android.util.Log;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;
import com.github.wuxudong.rncharts.markers.RNRectangleDateTimeMarkerView;
import com.github.wuxudong.rncharts.markers.RNRectangleMarkerView;
import com.github.wuxudong.rncharts.utils.BridgeUtils;

public abstract class ChartBaseManager<T extends Chart, U extends Entry> extends SimpleViewManager {


    abstract DataExtract getDataExtract();

    /**
     * More details about legend customization: https://github.com/PhilJay/MPAndroidChart/wiki/Legend
     */
    @ReactProp(name = "legend")
    public void setLegend(T chart, ReadableMap propMap) {
        Legend legend = chart.getLegend();

        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "enabled")) {
            legend.setEnabled(propMap.getBoolean("enabled"));
        }

        // Styling
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textColor")) {
            legend.setTextColor(propMap.getInt("textColor"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            legend.setTextSize((float) propMap.getDouble("textSize"));
        }

        // Wrapping / clipping avoidance
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "wordWrapEnabled")) {
            legend.setWordWrapEnabled(propMap.getBoolean("wordWrapEnabled"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "maxSizePercent")) {
            legend.setMaxSizePercent((float) propMap.getDouble("maxSizePercent"));
        }

        // Customizing
        if (BridgeUtils.validate(propMap, ReadableType.String, "position")) {
            legend.setPosition(LegendPosition.valueOf(propMap.getString("position").toUpperCase()));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "form")) {
            legend.setForm(LegendForm.valueOf(propMap.getString("form").toUpperCase()));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "formSize")) {
            legend.setFormSize((float) propMap.getDouble("formSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "xEntrySpace")) {
            legend.setXEntrySpace((float) propMap.getDouble("xEntrySpace"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "yEntrySpace")) {
            legend.setYEntrySpace((float) propMap.getDouble("yEntrySpace"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "formToTextSpace")) {
            legend.setFormToTextSpace((float) propMap.getDouble("formToTextSpace"));
        }

        // Custom labels & colors
        if (BridgeUtils.validate(propMap, ReadableType.Map, "custom")) {
            ReadableMap customMap = propMap.getMap("custom");
            if (BridgeUtils.validate(customMap, ReadableType.Array, "colors") &&
                    BridgeUtils.validate(customMap, ReadableType.Array, "labels")) {

                ReadableArray colorsArray = customMap.getArray("colors");
                ReadableArray labelsArray = customMap.getArray("labels");

                if (colorsArray.size() == labelsArray.size()) {
                    // TODO null label should start a group
                    // TODO -2 color should avoid drawing a form
                    String[] labels = BridgeUtils.convertToStringArray(labelsArray);
                    int[] colorsParsed = BridgeUtils.convertToIntArray(colorsArray);

                    LegendEntry[] legendEntries = new LegendEntry[labels.length];
                    for (int i = 0; i < legendEntries.length; i++) {
                        legendEntries[i] = new LegendEntry();
                        legendEntries[i].formColor = colorsParsed[i];
                        legendEntries[i].label = labels[i];
                    }

                    legend.setCustom(legendEntries);
                }
            }
        }

        // TODO resetCustom function
        // TODO extra

        chart.invalidate();     // TODO is this necessary? Looks like enabled is not refreshing without it
    }

    @ReactProp(name = "logEnabled")
    public void setLogEnabled(Chart chart, boolean enabled) {
        chart.setLogEnabled(enabled);
    }

    @ReactProp(name = "chartBackgroundColor")
    public void setChartBackgroundColor(Chart chart, Integer color) {
        chart.setBackgroundColor(color);
    }

    @ReactProp(name = "chartDescription")
    public void setChartDescription(Chart chart, ReadableMap propMap) {

        Description description = new Description();

        if (BridgeUtils.validate(propMap, ReadableType.String, "text")) {
            description.setText(propMap.getString("text"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textColor")) {
            description.setTextColor(propMap.getInt("textColor"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            description.setTextSize((float) propMap.getDouble("textSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "positionX") &&
                BridgeUtils.validate(propMap, ReadableType.Number, "positionY")) {
            description.setPosition((float) propMap.getDouble("positionX"), (float) propMap.getDouble("positionY"));
        }

        chart.setDescription(description);
    }

    @ReactProp(name = "noDataText")
    public void setNoDataText(Chart chart, String noDataText) {
        chart.setNoDataText(noDataText);
    }

    @ReactProp(name = "touchEnabled")
    public void setTouchEnabled(Chart chart, boolean enabled) {
        chart.setTouchEnabled(enabled);
    }

    @ReactProp(name = "dragDecelerationEnabled")
    public void setDragDecelerationEnabled(Chart chart, boolean enabled) {
        chart.setDragDecelerationEnabled(enabled);
    }

    @ReactProp(name = "dragDecelerationFrictionCoef")
    public void setDragDecelerationFrictionCoef(Chart chart, float coef) {
        chart.setDragDecelerationFrictionCoef(coef);
    }

    /**
     * Animations docs: https://github.com/PhilJay/MPAndroidChart/wiki/Animations
     */
    @ReactProp(name = "animation")
    public void setAnimation(Chart chart, ReadableMap propMap) {
        Integer durationX = null;
        Integer durationY = null;
        EasingOption easingX = EasingOption.Linear;
        EasingOption easingY = EasingOption.Linear;

        if (BridgeUtils.validate(propMap, ReadableType.Number, "durationX")) {
            durationX = propMap.getInt("durationX");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "durationY")) {
            durationY = propMap.getInt("durationY");
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "easingX")) {
            easingX = EasingOption.valueOf(propMap.getString("easingX"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "easingY")) {
            easingY = EasingOption.valueOf(propMap.getString("easingY"));
        }

        if (durationX != null && durationY != null) {
            chart.animateXY(durationX, durationY, easingX, easingY);
        } else if (durationX != null) {
            chart.animateX(durationX, easingX);
        } else if (durationY != null) {
            chart.animateY(durationY, easingY);
        }
    }

    /**
     * xAxis config details: https://github.com/PhilJay/MPAndroidChart/wiki/XAxis
     */
    @ReactProp(name = "xAxis")
    public void setXAxis(Chart chart, ReadableMap propMap) {
        XAxis axis = chart.getXAxis();

        setCommonAxisConfig(chart, axis, propMap);

        if (BridgeUtils.validate(propMap, ReadableType.Number, "labelRotationAngle")) {
            axis.setLabelRotationAngle((float) propMap.getDouble("labelRotationAngle"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "avoidFirstLastClipping")) {
            axis.setAvoidFirstLastClipping(propMap.getBoolean("avoidFirstLastClipping"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "position")) {
            axis.setPosition(XAxisPosition.valueOf(propMap.getString("position")));
        }
    }

    @ReactProp(name = "marker")
    public void setMarker(Chart chart, ReadableMap propMap) {
        if (!BridgeUtils.validate(propMap, ReadableType.Boolean, "enabled") || !propMap.getBoolean("enabled")) {
            chart.setMarker(null);
            return;
        }

        RNRectangleMarkerView marker;

        if (BridgeUtils.validate(propMap, ReadableType.String, "type") &&
                "dateTime".equals(propMap.getString("type")) &&
                BridgeUtils.validate(propMap, ReadableType.String, "startTimeStamp") &&
                BridgeUtils.validate(propMap, ReadableType.String, "format")) {
            String format = propMap.getString("format");
            String timeStamp = propMap.getString("startTimeStamp");
            long tsn = Long.parseLong(timeStamp);
            marker = new RNRectangleDateTimeMarkerView(chart.getContext(), tsn, format);
        } else {
            marker = new RNRectangleMarkerView(chart.getContext());
        }

        marker.setChartView(chart);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                BridgeUtils.validate(propMap, ReadableType.Number, "markerColor")) {
            marker.getTvContent()
                    .setBackgroundTintList(
                            ColorStateList.valueOf(propMap.getInt("markerColor"))
                    );
        }

        if (BridgeUtils.validate(propMap, ReadableType.Number, "textColor")) {
            marker.getTvContent().setTextColor(propMap.getInt("textColor"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            marker.getTvContent().setTextSize(propMap.getInt("textSize"));
        }

        chart.setMarker(marker);
    }

    /**
     * General axis config details: https://github.com/PhilJay/MPAndroidChart/wiki/The-Axis
     */
    protected void setCommonAxisConfig(Chart chart, AxisBase axis, ReadableMap propMap) {
        // what is drawn
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "enabled")) {
            axis.setEnabled(propMap.getBoolean("enabled"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "drawLabels")) {
            axis.setDrawLabels(propMap.getBoolean("drawLabels"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "drawAxisLine")) {
            axis.setDrawAxisLine(propMap.getBoolean("drawAxisLine"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "drawGridLines")) {
            axis.setDrawGridLines(propMap.getBoolean("drawGridLines"));
        }

        // style
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textColor")) {
            axis.setTextColor(propMap.getInt("textColor"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            axis.setTextSize((float) propMap.getDouble("textSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "gridColor")) {
            axis.setGridColor(propMap.getInt("gridColor"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "gridLineWidth")) {
            axis.setGridLineWidth((float) propMap.getDouble("gridLineWidth"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "axisLineColor")) {
            axis.setAxisLineColor(propMap.getInt("axisLineColor"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "axisLineWidth")) {
            axis.setAxisLineWidth((float) propMap.getDouble("axisLineWidth"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Map, "gridDashedLine")) {
            ReadableMap gridDashedLine = propMap.getMap("gridDashedLine");
            float lineLength = 0;
            float spaceLength = 0;
            float phase = 0;

            if (BridgeUtils.validate(gridDashedLine, ReadableType.Number, "lineLength")) {
                lineLength = (float) gridDashedLine.getDouble("lineLength");
            }
            if (BridgeUtils.validate(gridDashedLine, ReadableType.Number, "spaceLength")) {
                spaceLength = (float) gridDashedLine.getDouble("spaceLength");
            }
            if (BridgeUtils.validate(gridDashedLine, ReadableType.Number, "phase")) {
                phase = (float) gridDashedLine.getDouble("phase");
            }

            axis.enableGridDashedLine(lineLength, spaceLength, phase);
        }

        // limit lines
        if (BridgeUtils.validate(propMap, ReadableType.Array, "limitLines")) {
            ReadableArray limitLines = propMap.getArray("limitLines");

            for (int i = 0; i < limitLines.size(); i++) {
                if (!ReadableType.Map.equals(limitLines.getType(i))) {
                    continue;
                }

                ReadableMap limitLineMap = limitLines.getMap(i);
                if (BridgeUtils.validate(limitLineMap, ReadableType.Number, "limit")) {
                    LimitLine limitLine = new LimitLine((float) limitLineMap.getDouble("limit"));

                    if (BridgeUtils.validate(limitLineMap, ReadableType.String, "label")) {
                        limitLine.setLabel(limitLineMap.getString("label"));
                    }
                    if (BridgeUtils.validate(limitLineMap, ReadableType.Number, "lineColor")) {
                        limitLine.setLineColor(limitLineMap.getInt("lineColor"));
                    }
                    if (BridgeUtils.validate(limitLineMap, ReadableType.Number, "lineWidth")) {
                        limitLine.setLineWidth((float) limitLineMap.getDouble("lineWidth"));
                    }

                    axis.addLimitLine(limitLine);
                }

            }
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "drawLimitLinesBehindData")) {
            axis.setDrawLimitLinesBehindData(propMap.getBoolean("drawLimitLinesBehindData"));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Number, "axisMaximum")) {
            axis.setAxisMaximum((float) propMap.getDouble("axisMaximum"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "axisMinimum")) {
            axis.setAxisMinimum((float) propMap.getDouble("axisMinimum"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "granularity")) {
            axis.setGranularity((float) propMap.getDouble("granularity"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "granularityEnabled")) {
            axis.setGranularityEnabled(propMap.getBoolean("granularityEnabled"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "labelCount")) {
            boolean labelCountForce = false;
            if (BridgeUtils.validate(propMap, ReadableType.Boolean, "labelCountForce")) {
                labelCountForce = propMap.getBoolean("labelCountForce");
            }
            axis.setLabelCount(propMap.getInt("labelCount"), labelCountForce);
        }

        // formatting
        if (BridgeUtils.validate(propMap, ReadableType.String, "valueFormatter")) {
            String valueFormatter = propMap.getString("valueFormatter");

            if ("largeValue".equals(valueFormatter)) {
                axis.setValueFormatter(new LargeValueFormatter());
            } else if ("percent".equals(valueFormatter)) {
                axis.setValueFormatter(new PercentFormatter());
            } else {
                axis.setValueFormatter(new CustomFormatter(valueFormatter));
            }
        } else if (BridgeUtils.validate(propMap, ReadableType.Array, "valueFormatter")) {
            axis.setValueFormatter(new IndexAxisValueFormatter(BridgeUtils.convertToStringArray(propMap.getArray("valueFormatter"))));
        } else if (BridgeUtils.validate(propMap, ReadableType.Map, "valueFormatter")) {
            ReadableMap formatterMap = propMap.getMap("valueFormatter");
            if (BridgeUtils.validate(formatterMap, ReadableType.Map.String, "type")) {
                String formatType = formatterMap.getString("type");
                if ("dateTime".equals(formatType)) {
                    if (BridgeUtils.validate(formatterMap, ReadableType.Map.String, "format") &&
                            BridgeUtils.validate(formatterMap, ReadableType.Map.String, "startTimeStamp")) {
                        String format = formatterMap.getString("format");
                        String timeStamp = formatterMap.getString("startTimeStamp");
                        long tsn = Long.parseLong(timeStamp);

                        axis.setValueFormatter(new DateTimeFormatter(tsn, format));
                    }
                }
            }
        }

        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "centerAxisLabels")) {
            axis.setCenterAxisLabels(propMap.getBoolean("centerAxisLabels"));
        }
    }

    /**
     * Dataset config details: https://github.com/PhilJay/MPAndroidChart/wiki/DataSet-classes-in-detail
     */
    @ReactProp(name = "data")
    public void setData(Chart chart, ReadableMap propMap) {
        chart.setData(getDataExtract().extract(propMap));
        chart.invalidate();
    }


}
