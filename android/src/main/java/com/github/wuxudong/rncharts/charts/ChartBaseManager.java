package com.github.wuxudong.rncharts.charts;

import android.content.res.ColorStateList;
import android.os.Build;
import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.markers.RNRectangleMarkerView;
import com.github.wuxudong.rncharts.markers.RNCircleMarkerView;
import com.github.wuxudong.rncharts.utils.BridgeUtils;
import com.github.wuxudong.rncharts.utils.EasingFunctionHelper;
import com.github.wuxudong.rncharts.utils.TypefaceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public abstract class ChartBaseManager<T extends Chart, U extends Entry> extends SimpleViewManager<T> {

    protected static final int MOVE_VIEW_TO = 1;
    protected static final int MOVE_VIEW_TO_X = 2;
    protected static final int MOVE_VIEW_TO_ANIMATED = 3;
    protected static final int CENTER_VIEW_TO = 4;
    protected static final int CENTER_VIEW_TO_ANIMATED = 6;
    protected static final int FIT_SCREEN = 7;
    protected static final int HIGHLIGHTS = 8;

    protected static final int SET_DATA_AND_LOCK_INDEX = 9;

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
        if (BridgeUtils.validate(propMap, ReadableType.String, "horizontalAlignment")) {
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.valueOf(propMap.getString("horizontalAlignment").toUpperCase(Locale.ENGLISH)));
        }

        if (BridgeUtils.validate(propMap, ReadableType.String, "verticalAlignment")) {
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.valueOf(propMap.getString("verticalAlignment").toUpperCase(Locale.ENGLISH)));
        }

        if (BridgeUtils.validate(propMap, ReadableType.String, "orientation")) {
            legend.setOrientation(Legend.LegendOrientation.valueOf(propMap.getString("orientation").toUpperCase(Locale.ENGLISH)));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "drawInside")) {
            legend.setDrawInside(propMap.getBoolean("drawInside"));
        }

        if (BridgeUtils.validate(propMap, ReadableType.String, "direction")) {
            legend.setDirection(Legend.LegendDirection.valueOf(propMap.getString("direction").toUpperCase(Locale.ENGLISH)));
        }

        if (BridgeUtils.validate(propMap, ReadableType.String, "fontFamily")) {
            legend.setTypeface(TypefaceUtils.getTypeface(chart, propMap));
        }

        if (BridgeUtils.validate(propMap, ReadableType.String, "form")) {
            legend.setForm(LegendForm.valueOf(propMap.getString("form").toUpperCase(Locale.ENGLISH)));
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
    }

    @ReactProp(name = "logEnabled")
    public void setLogEnabled(Chart chart, boolean enabled) {
        chart.setLogEnabled(enabled);
    }

    @ReactProp(name = "chartBackgroundColor")
    public void setChartBackgroundColor(Chart chart, Integer color) {
        chart.setBackgroundColor(color);
    }

    @ReactProp(name = "highlightPerTapEnabled")
    public void setHighlightPerTapEnabled(Chart chart, boolean enabled) {
        chart.setHighlightPerTapEnabled(enabled);
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

    @ReactProp(name = "noDataTextColor")
    public void setNoDataTextColor(Chart chart, Integer color) {
        chart.setNoDataTextColor(color);
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
        Easing.EasingFunction easingX = Easing.Linear;
        Easing.EasingFunction easingY = Easing.Linear;

        if (BridgeUtils.validate(propMap, ReadableType.Number, "durationX")) {
            durationX = propMap.getInt("durationX");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "durationY")) {
            durationY = propMap.getInt("durationY");
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "easingX")) {
             easingX = EasingFunctionHelper.getEasingFunction(propMap.getString("easingX"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "easingY")) {
             easingY = EasingFunctionHelper.getEasingFunction(propMap.getString("easingY"));
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

        String markerType = propMap.hasKey("markerType") ? propMap.getString("markerType") : "";

        MarkerView markerView = null;
        switch(markerType) {
            case "circle":
                markerView = circleMarker(chart);

                break;
            default:
                markerView = rectangleMarker(chart, propMap);
        }

        markerView.setChartView(chart);
        chart.setMarker(markerView);
    }

    private RNRectangleMarkerView rectangleMarker(Chart chart, ReadableMap propMap) {
        RNRectangleMarkerView marker = new RNRectangleMarkerView(chart.getContext());
        setMarkerParams(marker, propMap);
        return marker;
    }

    private RNCircleMarkerView circleMarker(Chart chart) {
        return new RNCircleMarkerView(chart.getContext());
    }

    private void setMarkerParams(RNRectangleMarkerView marker, ReadableMap propMap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                BridgeUtils.validate(propMap, ReadableType.Number, "markerColor")) {
            marker.getTvContent()
                    .setBackgroundTintList(
                            ColorStateList.valueOf(propMap.getInt("markerColor"))
                    );
        }

        if (BridgeUtils.validate(propMap, ReadableType.Number, "digits")) {
            marker.setDigits(propMap.getInt("digits"));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Number, "textColor")) {
            marker.getTvContent().setTextColor(propMap.getInt("textColor"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            marker.getTvContent().setTextSize(propMap.getInt("textSize"));
        }

        if (BridgeUtils.validate(propMap, ReadableType.String, "textAlign")) {

            int alignment = View.TEXT_ALIGNMENT_CENTER;
            switch (propMap.getString("textAlign")) {
                case "left":
                    alignment = View.TEXT_ALIGNMENT_TEXT_START;
                    break;
                case "center":
                    alignment = View.TEXT_ALIGNMENT_CENTER;
                    break;
                case "right":
                    alignment = View.TEXT_ALIGNMENT_TEXT_END;
                    break;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                marker.getTvContent().setTextAlignment(alignment);
            }
        }
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
        if (BridgeUtils.validate(propMap, ReadableType.String, "fontFamily")) {
            axis.setTypeface(TypefaceUtils.getTypeface(chart, propMap));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "yOffset")) {
            axis.setYOffset((float)(propMap.getDouble("yOffset")));
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

            axis.removeAllLimitLines();
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

                    if (BridgeUtils.validate(limitLineMap, ReadableType.Number, "valueTextColor")) {
                        limitLine.setTextColor(limitLineMap.getInt("valueTextColor"));
                    }

                    if (BridgeUtils.validate(limitLineMap, ReadableType.Number, "valueTextSize")) {
                        limitLine.setTextSize((float) limitLineMap.getDouble("valueTextSize"));
                    }

                    if (BridgeUtils.validate(limitLineMap, ReadableType.String, "fontFamily")) {
                        limitLine.setTypeface(TypefaceUtils.getTypeface(chart, limitLineMap));
                    }

                    // Deprecated
                    if (BridgeUtils.validate(limitLineMap, ReadableType.Number, "valueFont")) {
                        limitLine.setTextSize(limitLineMap.getInt("valueFont"));
                    }

                    if (BridgeUtils.validate(limitLineMap, ReadableType.String, "labelPosition")) {
                        limitLine.setLabelPosition(LimitLine.LimitLabelPosition.valueOf(limitLineMap.getString("labelPosition")));
                    }

                    if (BridgeUtils.validate(limitLineMap, ReadableType.Number, "lineDashPhase")
                            && BridgeUtils.validate(limitLineMap, ReadableType.Array, "lineDashLengths")) {
                        if (limitLineMap.getArray("lineDashLengths").size() > 1) {
                            float lineDashPhase = (float) limitLineMap.getDouble("lineDashPhase");
                            float lineLength = limitLineMap.getArray("lineDashLengths").getInt(0);
                            float spaceLength = limitLineMap.getArray("lineDashLengths").getInt(1);
                            limitLine.enableDashedLine(lineLength, spaceLength, lineDashPhase);
                        }
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
            } else if ("date".equals(valueFormatter)) {
                String valueFormatterPattern = propMap.getString("valueFormatterPattern");

                long since = 0;
                if (BridgeUtils.validate(propMap, ReadableType.Number, "since")) {
                    since = (long) propMap.getDouble("since");
                }

                TimeUnit timeUnit = TimeUnit.MILLISECONDS;

                if (BridgeUtils.validate(propMap, ReadableType.String, "timeUnit")) {
                    timeUnit = TimeUnit.valueOf(propMap.getString("timeUnit").toUpperCase());
                }
                Locale locale = Locale.getDefault();

                if (BridgeUtils.validate(propMap, ReadableType.String, "locale")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        locale = Locale.forLanguageTag(propMap.getString("locale"));
                    }
                }

                axis.setValueFormatter(new DateFormatter(valueFormatterPattern, since, timeUnit, locale));
            } else {
                axis.setValueFormatter(new CustomFormatter(valueFormatter));
            }
        } else if (BridgeUtils.validate(propMap, ReadableType.Array, "valueFormatter")) {
            axis.setValueFormatter(new IndexAxisValueFormatter(BridgeUtils.convertToStringArray(propMap.getArray("valueFormatter"))));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "centerAxisLabels")) {
            axis.setCenterAxisLabels(propMap.getBoolean("centerAxisLabels"));
        }
    }



    /**
     * Dataset config details: https://github.com/PhilJay/MPAndroidChart/wiki/DataSet-classes-in-detail
     */
    @ReactProp(name = "data")
    public void setData(T chart, ReadableMap propMap) {
        chart.setData(getDataExtract().extract(chart, propMap));
    }

    @ReactProp(name = "highlights")
    public void setHighlights(T chart, ReadableArray array) {
        List<Highlight> highlights = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            if (!ReadableType.Map.equals(array.getType(i))) {
                continue;
            }

            ReadableMap highlightMap = array.getMap(i);

            if (BridgeUtils.validate(highlightMap, ReadableType.Number, "x")) {

                int dataSetIndex = BridgeUtils.validate(highlightMap, ReadableType.Number, "dataSetIndex") ? highlightMap.getInt("dataSetIndex") : 0;

                float y = BridgeUtils.validate(highlightMap, ReadableType.Number, "y") ? (float) highlightMap.getDouble("y") : 0;

                Highlight e = null;
                if (BridgeUtils.validate(highlightMap, ReadableType.Number, "stackIndex")) {
                    e = new Highlight((float) highlightMap.getDouble("x"), dataSetIndex, highlightMap.getInt("stackIndex"));
                } else {
                    e = new Highlight((float) highlightMap.getDouble("x"), y, dataSetIndex);
                }

                if (BridgeUtils.validate(highlightMap, ReadableType.Number, "dataIndex")) {
                    e.setDataIndex(highlightMap.getInt("dataIndex"));
                }

                highlights.add(e);
            }
        }

        chart.highlightValues(highlights.toArray(new Highlight[highlights.size()]));
    }

    protected void onAfterDataSetChanged(T chart) {

    }

    @Override
    protected void onAfterUpdateTransaction(T chart) {
        super.onAfterUpdateTransaction(chart);
        chart.notifyDataSetChanged();
        onAfterDataSetChanged(chart);
        chart.postInvalidate();;
    }

}
