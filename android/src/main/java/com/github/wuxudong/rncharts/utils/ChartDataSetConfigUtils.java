package com.github.wuxudong.rncharts.utils;

import android.graphics.drawable.GradientDrawable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.LineRadarDataSet;
import com.github.mikephil.charting.data.LineScatterCandleRadarDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.wuxudong.rncharts.charts.CustomFormatter;
import com.github.wuxudong.rncharts.charts.DateFormatter;
import com.github.wuxudong.rncharts.charts.IndexValueFormatter;
import com.github.wuxudong.rncharts.charts.LabelByXValueFormatter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/PhilJay/MPAndroidChart/wiki/The-DataSet-class
 * https://github.com/PhilJay/MPAndroidChart/wiki/DataSet-classes-in-detail
 */
public class ChartDataSetConfigUtils {

    public static void commonConfig(Chart chart, DataSet dataSet, ReadableMap config) {
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

                long since = 0;
                if (BridgeUtils.validate(config, ReadableType.Number, "since")) {
                    since = (long) config.getDouble("since");
                }

                TimeUnit timeUnit = TimeUnit.MILLISECONDS;

                if (BridgeUtils.validate(config, ReadableType.String, "timeUnit")) {
                    timeUnit = TimeUnit.valueOf(config.getString("timeUnit").toUpperCase());
                }

                Locale locale = Locale.getDefault();

                if (BridgeUtils.validate(config, ReadableType.String, "locale")) {
                    locale = Locale.forLanguageTag(config.getString("locale"));
                }

                dataSet.setValueFormatter(new DateFormatter(valueFormatterPattern, since, timeUnit, locale));
            } else if ("labelByXValue".equals(valueFormatter)) {
                ReadableArray valueFormatterLabels = config.getArray("valueFormatterLabels");

                Map<Float, String> labelsByXValue = new HashMap<>();
                for (int index = 0; index < valueFormatterLabels.size(); index++) {
                    ReadableMap entry = valueFormatterLabels.getMap(index);
                    labelsByXValue.put(Float.valueOf((float) entry.getDouble("x")), entry.getString("label"));
                }

                dataSet.setValueFormatter(new LabelByXValueFormatter(labelsByXValue));
            } else {
                dataSet.setValueFormatter(new CustomFormatter(valueFormatter));
            }
        } else if (BridgeUtils.validate(config, ReadableType.Array, "valueFormatter")) {
            dataSet.setValueFormatter(new IndexValueFormatter(BridgeUtils.convertToStringArray(config.getArray("valueFormatter"))));
        }

        if (BridgeUtils.validate(config, ReadableType.String, "axisDependency")) {
            dataSet.setAxisDependency(YAxis.AxisDependency.valueOf(config.getString("axisDependency").toUpperCase(Locale.ENGLISH)));
        }

        if (BridgeUtils.validate(config, ReadableType.String, "fontFamily")) {
            dataSet.setValueTypeface(TypefaceUtils.getTypeface(chart, config));
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
            int[] colors = BridgeUtils.convertToIntArray(config.getMap("fillGradient").getArray("colors"));

            GradientDrawable.Orientation orientation = GradientDrawable.Orientation.BOTTOM_TOP;

            switch (config.getMap("fillGradient").getString("orientation")) {
                case "TOP_BOTTOM":
                    orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                    break;
                case "TR_BL":
                    orientation = GradientDrawable.Orientation.TR_BL;
                    break;
                case "RIGHT_LEFT":
                    orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                    break;
                case "BR_TL":
                    orientation = GradientDrawable.Orientation.BR_TL;
                    break;
                case "BOTTOM_TOP":
                    orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                    break;
                case "BL_TR":
                    orientation = GradientDrawable.Orientation.BL_TR;
                    break;
                case "LEFT_RIGHT":
                    orientation = GradientDrawable.Orientation.LEFT_RIGHT;
                    break;
                case "TL_BR":
                    orientation = GradientDrawable.Orientation.TL_BR;
                    break;
            }

            GradientDrawable gd = new GradientDrawable(
                    orientation,
                    colors);
            gd.setCornerRadius(0f);
            gd.setAlpha(config.getInt("fillAlpha"));
            dataSet.setFillDrawable(gd);
        } else if (BridgeUtils.validate(config, ReadableType.Number, "fillColor")) {
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
