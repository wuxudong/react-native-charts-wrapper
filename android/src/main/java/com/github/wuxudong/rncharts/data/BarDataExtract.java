package com.github.wuxudong.rncharts.data;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.wuxudong.rncharts.utils.BridgeUtils;
import com.github.wuxudong.rncharts.utils.ChartDataSetConfigUtils;

import java.util.ArrayList;

/**
 * Created by xudong on 02/03/2017.
 */

public class BarDataExtract extends DataExtract<BarData, BarEntry> {
    @Override
    BarData createData() {
        return new BarData();
    }

    @Override
    IDataSet<BarEntry> createDataSet(ArrayList<BarEntry> entries, String label) {
        return new BarDataSet(entries, label);
    }

    @Override
    BarEntry createEntry(ReadableArray values, int index) {
        BarEntry entry;

        float x = index;
        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);

            if (map.hasKey("x")) {
                x = (float) map.getDouble("x");
            }

            if (ReadableType.Array.equals(map.getType("y"))) {
                entry = new BarEntry(x, BridgeUtils.convertToFloatArray(map.getArray("y")));
            } else if (ReadableType.Number.equals(map.getType("y"))) {
                entry = new BarEntry(x, (float) map.getDouble("y"));
            } else {
                throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
            }

            entry.setData(map);

        } else if (ReadableType.Array.equals(values.getType(index))) {
            entry = new BarEntry(x, BridgeUtils.convertToFloatArray(values.getArray(index)));
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new BarEntry(x, (float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }

        return entry;
    }

    @Override
    void dataSetConfig(IDataSet<BarEntry> dataSet, ReadableMap config) {
        BarDataSet barDataSet = (BarDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(barDataSet, config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(barDataSet, config);

        if (BridgeUtils.validate(config, ReadableType.Number, "barShadowColor")) {
            barDataSet.setBarShadowColor(config.getInt("barShadowColor"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "highlightAlpha")) {
            barDataSet.setHighLightAlpha(config.getInt("highlightAlpha"));
        }
        if (BridgeUtils.validate(config, ReadableType.Array, "stackLabels")) {
            barDataSet.setStackLabels(BridgeUtils.convertToStringArray(config.getArray("stackLabels")));
        }
    }

    @Override
    void dataConfig(BarData data, ReadableMap config) {
        super.dataConfig(data, config);

        if (BridgeUtils.validate(config, ReadableType.Number, "barWidth")) {
            data.setBarWidth((float) config.getDouble("barWidth"));
        }

        if (BridgeUtils.validate(config, ReadableType.Number, "group")) {
            ReadableMap propMap = config.getMap("group");
            if (BridgeUtils.validate(propMap, ReadableType.Number, "fromX") &&
                    BridgeUtils.validate(propMap, ReadableType.Number, "groupSpace") &&
                    BridgeUtils.validate(propMap, ReadableType.Number, "barSpace")
                    ) {
                float fromX = (float) propMap.getDouble("fromX");
                float groupSpace = (float) propMap.getDouble("groupSpace");
                float barSpace = (float) propMap.getDouble("barSpace");

                data.groupBars(fromX, groupSpace, barSpace);
            }
        }


    }
}
