package com.github.wuxudong.rncharts.utils;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.LineRadarDataSet;
import com.github.mikephil.charting.data.LineScatterCandleRadarDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.wuxudong.rncharts.charts.CustomFormatter;
import com.github.wuxudong.rncharts.charts.DateFormatter;
import android.graphics.drawable.GradientDrawable;
import java.util.Locale;

/**
 * https://github.com/PhilJay/MPAndroidChart/wiki/The-DataSet-class
 * https://github.com/PhilJay/MPAndroidChart/wiki/DataSet-classes-in-detail
 */
public class ChartDataSetConfigUtils {

    public static void commonConfig(DataSet dataSet, ReadableMap config) {
        // Setting main color
        if (BridgeUtils.validate(config, ReadableType.Number, "color")) {
            dataSet.setColor(config.getInt("color"));
        }
        if (BridgeUtils.validate(config, ReadableType.Array, "colors")) {
            dataSet.setColors(BridgeUtils.convertToIntArray(config.getArray("colors")));
        }

        // TODO more config to add: https://github.com/PhilJay/MPAndroidChart/wiki/The-DataSet-class

        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawValues")) {
            dataSet.setDrawValues(config.getBoolean("drawValues"));
        }

        if (BridgeUtils.validate(config, ReadableType.Boolean, "highlightEnabled")) {
            dataSet.setHighlightEnabled(config.getBoolean("highlightEnabled"));
        }

        if (BridgeUtils.validate(config, ReadableType.Boolean, "visible")) {
            dataSet.setVisible(config.getBoolean("visible"));
        }

        if (BridgeUtils.validate(config, ReadableType.Number, "valueTextSize")) {
            dataSet.setValueTextSize((float) config.getDouble("valueTextSize"));
        }

        if (BridgeUtils.validate(config, ReadableType.Number, "valueTextColor")) {
            dataSet.setValueTextColor(config.getInt("valueTextColor"));
        }

        if (BridgeUtils.validate(config, ReadableType.String, "valueFormatter")) {
            String valueFormatter = config.getString("valueFormatter");

            if ("largeValue".equals(valueFormatter)) {
                dataSet.setValueFormatter(new LargeValueFormatter());
            } else if ("percent".equals(valueFormatter)) {
                dataSet.setValueFormatter(new PercentFormatter());
            } else if ("date".equals(valueFormatter)) {
                String valueFormatterPattern = config.getString("valueFormatterPattern");
                dataSet.setValueFormatter(new DateFormatter(valueFormatterPattern));
            } else {
                dataSet.setValueFormatter(new CustomFormatter(valueFormatter));
            }
        }

        if (BridgeUtils.validate(config, ReadableType.String, "axisDependency")) {
            dataSet.setAxisDependency(YAxis.AxisDependency.valueOf(config.getString("axisDependency").toUpperCase(Locale.ENGLISH)));
        }
    }

    public static void commonBarLineScatterCandleBubbleConfig(BarLineScatterCandleBubbleDataSet dataSet, ReadableMap config) {
        if (BridgeUtils.validate(config, ReadableType.Number, "highlightColor")) {
            dataSet.setHighLightColor(config.getInt("highlightColor"));
        }
    }

    public static void commonLineScatterCandleRadarConfig(LineScatterCandleRadarDataSet dataSet, ReadableMap config) {
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawHighlightIndicators")) {
            dataSet.setDrawHighlightIndicators(config.getBoolean("drawHighlightIndicators"));
        }
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawVerticalHighlightIndicator")) {
            dataSet.setDrawVerticalHighlightIndicator(config.getBoolean("drawVerticalHighlightIndicator"));
        }
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawHorizontalHighlightIndicator")) {
            dataSet.setDrawHorizontalHighlightIndicator(config.getBoolean("drawHorizontalHighlightIndicator"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "highlightLineWidth")) {
            dataSet.setHighlightLineWidth((float) config.getDouble("highlightLineWidth"));
        }
    }

    public static void commonLineRadarConfig(LineRadarDataSet dataSet, ReadableMap config) {

        if (BridgeUtils.validate(config, ReadableType.Map, "fillGradient")) {
            int [] colors = BridgeUtils.convertToIntArray( config.getMap("fillGradient").getArray("colors"));

            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.BOTTOM_TOP,
                    colors);
            gd.setCornerRadius(0f);

            dataSet.setFillDrawable(gd);
        }
        else if (BridgeUtils.validate(config, ReadableType.Number, "fillColor")) {
            dataSet.setFillColor(config.getInt("fillColor"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "fillAlpha")) {
            dataSet.setFillAlpha(config.getInt("fillAlpha"));
        }
        // TODO setFillDrawable android.graphics.drawable.Drawable
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawFilled")) {
            dataSet.setDrawFilled(config.getBoolean("drawFilled"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "lineWidth")) {
            float lineWidth = (float) config.getDouble("lineWidth");
            if (lineWidth >= 0.2f && lineWidth < 10f) {
                dataSet.setLineWidth(lineWidth);
            }
        }
    }


}
