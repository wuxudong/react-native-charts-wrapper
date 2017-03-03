package com.github.wuxudong.rncharts.charts;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.wuxudong.rncharts.utils.BridgeUtils;

public abstract class YAxisChartBase<T extends Chart, U extends Entry> extends ChartBaseManager<T, U> {

    /**
     * yAxis config details: https://github.com/PhilJay/MPAndroidChart/wiki/YAxis
     */
    @ReactProp(name = "yAxis")
    public abstract void setYAxis(Chart chart, ReadableMap propMap);

    protected void setYAxisConfig(YAxis axis, ReadableMap propMap) {

        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "inverted")) {
            axis.setInverted(propMap.getBoolean("inverted"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "spaceTop")) {
            axis.setSpaceTop((float) propMap.getDouble("spaceTop"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "spaceBottom")) {
            axis.setSpaceBottom((float) propMap.getDouble("spaceBottom"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "position")) {
            axis.setPosition(YAxis.YAxisLabelPosition.valueOf(propMap.getString("position")));
        }

        // TODO docs says the remaining config needs to be applied before setting data. Test it
        // zero line
        if (BridgeUtils.validate(propMap, ReadableType.Map, "zeroLine")) {
            ReadableMap zeroLineConfig = propMap.getMap("zeroLine");

            if (BridgeUtils.validate(zeroLineConfig, ReadableType.Boolean, "enabled")) {
                axis.setDrawZeroLine(zeroLineConfig.getBoolean("enabled"));
            }
            if (BridgeUtils.validate(zeroLineConfig, ReadableType.Number, "lineWidth")) {
                axis.setZeroLineWidth((float) zeroLineConfig.getDouble("lineWidth"));
            }
            if (BridgeUtils.validate(zeroLineConfig, ReadableType.Number, "lineColor")) {
                axis.setZeroLineColor(zeroLineConfig.getInt("lineColor"));
            }
        }
    }

}
