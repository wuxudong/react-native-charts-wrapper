package com.github.wuxudong.rncharts.data;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.wuxudong.rncharts.utils.BridgeUtils;
import com.github.wuxudong.rncharts.utils.ChartDataSetConfigUtils;
import com.github.wuxudong.rncharts.utils.ConversionUtil;
import com.github.wuxudong.rncharts.utils.DrawableUtils;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by xudong on 02/03/2017.
 */
public class ScatterDataExtract extends DataExtract<ScatterData, Entry> {
    @Override
    ScatterData createData() {
        return new ScatterData();
    }

    @Override
    IDataSet<Entry> createDataSet(ArrayList<Entry> entries, String label) {
        return new ScatterDataSet(entries, label);
    }

    @Override
    void dataSetConfig(Chart chart, IDataSet<Entry> dataSet, ReadableMap config) {
        ScatterDataSet scatterDataSet = (ScatterDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(chart, scatterDataSet, config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(scatterDataSet, config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(scatterDataSet, config);

        // ScatterDataSet only config
        if (BridgeUtils.validate(config, ReadableType.Number, "scatterShapeSize")) {
            scatterDataSet.setScatterShapeSize((float) config.getDouble("scatterShapeSize"));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "scatterShape")) {
            scatterDataSet.setScatterShape(ScatterChart.ScatterShape.valueOf(config.getString("scatterShape").toUpperCase(Locale.ENGLISH)));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "scatterShapeHoleColor")) {
            scatterDataSet.setScatterShapeHoleColor(config.getInt("scatterShapeHoleColor"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "scatterShapeHoleRadius")) {
            scatterDataSet.setScatterShapeHoleRadius((float) config.getDouble("scatterShapeHoleRadius"));
        }
    }

    @Override
    Entry createEntry(ReadableArray values, int index) {
        float x = index;

        Entry entry;
        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);
            if (map.hasKey("x")) {
                x = (float) map.getDouble("x");
            }
            if (map.hasKey("icon")) {
                ReadableMap icon = map.getMap("icon");
                ReadableMap bundle = icon.getMap("bundle");
                int width = icon.getInt("width");
                int height = icon.getInt("height");
                entry = new Entry(x, (float) map.getDouble("y"), DrawableUtils.drawableFromUrl(bundle.getString("uri"), width, height));

            } else {
              entry = new Entry(x, (float) map.getDouble("y"), ConversionUtil.toMap(map));
            }
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new Entry(x, (float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }

        return entry;
    }
}
