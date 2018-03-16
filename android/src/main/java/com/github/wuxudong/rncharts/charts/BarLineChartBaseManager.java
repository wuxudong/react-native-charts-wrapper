package com.github.wuxudong.rncharts.charts;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.wuxudong.rncharts.utils.BridgeUtils;

import java.util.Map;

import javax.annotation.Nullable;

public abstract class BarLineChartBaseManager<T extends BarLineChartBase, U extends Entry> extends YAxisChartBase<T, U> {

    @Override
    public void setYAxis(Chart chart, ReadableMap propMap) {
        BarLineChartBase barLineChart = (BarLineChartBase) chart;

        if (BridgeUtils.validate(propMap, ReadableType.Map, "left")) {
            YAxis leftYAxis = barLineChart.getAxisLeft();
            setCommonAxisConfig(chart, leftYAxis, propMap.getMap("left"));
            setYAxisConfig(leftYAxis, propMap.getMap("left"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Map, "right")) {
            YAxis rightYAxis = barLineChart.getAxisRight();
            setCommonAxisConfig(chart, rightYAxis, propMap.getMap("right"));
            setYAxisConfig(rightYAxis, propMap.getMap("right"));
        }
    }

    @ReactProp(name = "drawGridBackground")
    public void setDrawGridBackground(BarLineChartBase chart, boolean enabled) {
        chart.setDrawGridBackground(enabled);
    }

    @ReactProp(name = "gridBackgroundColor")
    public void setGridBackgroundColor(BarLineChartBase chart, Integer color) {
        chart.setGridBackgroundColor(color);
    }

    @ReactProp(name = "drawBorders")
    public void setDrawBorders(BarLineChartBase chart, boolean enabled) {
        chart.setDrawBorders(enabled);
    }

    @ReactProp(name = "borderColor")
    public void setBorderColor(BarLineChartBase chart, Integer color) {
        chart.setBorderColor(color);
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(BarLineChartBase chart, float width) {
        chart.setBorderWidth(width);
    }

    @ReactProp(name = "maxVisibleValueCount")
    public void setMaxVisibleValueCount(BarLineChartBase chart, int count) {
        chart.setMaxVisibleValueCount(count);
    }

    @ReactProp(name = "visibleRange")
    public void setVisibleXRangeMinimum(BarLineChartBase chart, ReadableMap propMap) {
        if (BridgeUtils.validate(propMap, ReadableType.Map, "x")) {
            ReadableMap x = propMap.getMap("x");
            if (BridgeUtils.validate(x, ReadableType.Number, "min")) {
                chart.setVisibleXRangeMinimum((float) x.getDouble("min"));
            }

            if (BridgeUtils.validate(x, ReadableType.Number, "max")) {
                chart.setVisibleXRangeMaximum((float) x.getDouble("max"));
            }
        }

        if (BridgeUtils.validate(propMap, ReadableType.Map, "y")) {
            ReadableMap y = propMap.getMap("y");

            if (BridgeUtils.validate(y, ReadableType.Map, "left")) {
                ReadableMap left = y.getMap("left");
                if (BridgeUtils.validate(left, ReadableType.Number, "min")) {
                    chart.setVisibleYRangeMinimum((float) left.getDouble("min"), YAxis.AxisDependency.LEFT);
                }

                if (BridgeUtils.validate(left, ReadableType.Number, "max")) {
                    chart.setVisibleYRangeMaximum((float) left.getDouble("max"), YAxis.AxisDependency.LEFT);
                }
            }

            if (BridgeUtils.validate(y, ReadableType.Map, "right")) {
                ReadableMap right = y.getMap("right");
                if (BridgeUtils.validate(right, ReadableType.Number, "min")) {
                    chart.setVisibleYRangeMinimum((float) right.getDouble("min"), YAxis.AxisDependency.RIGHT);
                }

                if (BridgeUtils.validate(right, ReadableType.Number, "max")) {
                    chart.setVisibleYRangeMaximum((float) right.getDouble("max"), YAxis.AxisDependency.RIGHT);
                }
            }
        }
    }

    @ReactProp(name = "autoScaleMinMaxEnabled")
    public void setAutoScaleMinMaxEnabled(BarLineChartBase chart, boolean enabled) {
        chart.setAutoScaleMinMaxEnabled(enabled);
    }

    @ReactProp(name = "keepPositionOnRotation")
    public void setKeepPositionOnRotation(BarLineChartBase chart, boolean enabled) {
        chart.setKeepPositionOnRotation(enabled);
    }

    @ReactProp(name = "scaleEnabled")
    public void setScaleEnabled(BarLineChartBase chart, boolean enabled) {
        chart.setScaleEnabled(enabled);
    }

    @ReactProp(name = "dragEnabled")
    public void setDragEnabled(BarLineChartBase chart, boolean enabled) {
        chart.setDragEnabled(enabled);
    }

    @ReactProp(name = "scaleXEnabled")
    public void setScaleXEnabled(BarLineChartBase chart, boolean enabled) {
        chart.setScaleXEnabled(enabled);
    }

    @ReactProp(name = "scaleYEnabled")
    public void setScaleYEnabled(BarLineChartBase chart, boolean enabled) {
        chart.setScaleYEnabled(enabled);
    }

    @ReactProp(name = "pinchZoom")
    public void setPinchZoom(BarLineChartBase chart, boolean enabled) {
        chart.setPinchZoom(enabled);
    }

    @ReactProp(name = "doubleTapToZoomEnabled")
    public void setDoubleTapToZoomEnabled(BarLineChartBase chart, boolean enabled) {
        chart.setDoubleTapToZoomEnabled(enabled);
    }

    @ReactProp(name = "zoom")
    public void setZoom(BarLineChartBase chart, ReadableMap propMap) {
        if (BridgeUtils.validate(propMap, ReadableType.Number, "scaleX") &&
                BridgeUtils.validate(propMap, ReadableType.Number, "scaleY") &&
                BridgeUtils.validate(propMap, ReadableType.Number, "xValue") &&
                BridgeUtils.validate(propMap, ReadableType.Number, "yValue")) {

            YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
            if (propMap.hasKey("axisDependency") &&
                    propMap.getString("axisDependency").equalsIgnoreCase("RIGHT")) {
                axisDependency = YAxis.AxisDependency.RIGHT;
            }

            chart.zoom(
                    (float) propMap.getDouble("scaleX") / chart.getScaleX(),
                    (float) propMap.getDouble("scaleY") / chart.getScaleY(),
                    (float) propMap.getDouble("xValue"),
                    (float) propMap.getDouble("yValue"),
                    axisDependency
            );
        }
    }

    // Note: Offset aren't updated until first touch event: https://github.com/PhilJay/MPAndroidChart/issues/892
    @ReactProp(name = "viewPortOffsets")
    public void setViewPortOffsets(BarLineChartBase chart, ReadableMap propMap) {
        double left = 0, top = 0, right = 0, bottom = 0;

        if (BridgeUtils.validate(propMap, ReadableType.Number, "left")) {
            left = propMap.getDouble("left");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "top")) {
            top = propMap.getDouble("top");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "right")) {
            right = propMap.getDouble("right");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "bottom")) {
            bottom = propMap.getDouble("bottom");
        }
        chart.setViewPortOffsets((float) left, (float) top, (float) right, (float) bottom);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        Map<String, Integer> commandsMap = super.getCommandsMap();

        Map<String, Integer> map = MapBuilder.of(
                "moveViewTo", MOVE_VIEW_TO,
                "moveViewToX", MOVE_VIEW_TO_X,
                "moveViewToAnimated", MOVE_VIEW_TO_ANIMATED,
                "fitScreen", FIT_SCREEN);

        if (commandsMap != null) {
            map.putAll(commandsMap);
        }
        return map;
    }

    @Override
    public void receiveCommand(T root, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case MOVE_VIEW_TO:
                root.moveViewTo((float) args.getDouble(0), (float) args.getDouble(1), args.getString(2).equalsIgnoreCase("right") ? YAxis.AxisDependency.RIGHT : YAxis.AxisDependency.LEFT);
                return;

            case MOVE_VIEW_TO_X:
                root.moveViewToX((float) args.getDouble(0));
                return;

            case MOVE_VIEW_TO_ANIMATED:
                root.moveViewToAnimated((float) args.getDouble(0), (float) args.getDouble(1), args.getString(2).equalsIgnoreCase("right") ? YAxis.AxisDependency.RIGHT : YAxis.AxisDependency.LEFT, args.getInt(3));
                return;

            case CENTER_VIEW_TO:
                root.centerViewTo((float) args.getDouble(0), (float) args.getDouble(1), args.getString(2).equalsIgnoreCase("right") ? YAxis.AxisDependency.RIGHT : YAxis.AxisDependency.LEFT);
                return;

            case CENTER_VIEW_TO_ANIMATED:
                root.centerViewToAnimated((float) args.getDouble(0), (float) args.getDouble(1), args.getString(2).equalsIgnoreCase("right") ? YAxis.AxisDependency.RIGHT : YAxis.AxisDependency.LEFT, args.getInt(3));
                return;

            case FIT_SCREEN:
                root.fitScreen();
                return;
        }

        super.receiveCommand(root, commandId, args);
    }

}
