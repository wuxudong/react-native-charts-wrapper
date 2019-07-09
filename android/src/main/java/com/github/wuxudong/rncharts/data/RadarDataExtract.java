package com.github.wuxudong.rncharts.data;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.wuxudong.rncharts.utils.ChartDataSetConfigUtils;
import com.github.wuxudong.rncharts.utils.ConversionUtil;

import java.util.ArrayList;

/**
 * Created by xudong on 02/03/2017.
 */
public class RadarDataExtract extends DataExtract<RadarData, RadarEntry> {

    @Override
    RadarData createData() {
        return new RadarData();
    }

    @Override
    IDataSet<RadarEntry> createDataSet(ArrayList<RadarEntry> entries, String label) {
        return new RadarDataSet(entries, label);
    }

    @Override
    void dataSetConfig(Chart chart, IDataSet<RadarEntry> dataSet, ReadableMap config) {
        RadarDataSet radarDataSet = (RadarDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(chart, radarDataSet, config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(radarDataSet, config);
        ChartDataSetConfigUtils.commonLineRadarConfig(radarDataSet, config);

        // RadarDataSet only config
    }

    @Override
    RadarEntry createEntry(ReadableArray values, int index) {
        RadarEntry entry;

        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);
            float value = (float) map.getDouble("value");
            entry = new RadarEntry(value, ConversionUtil.toMap(map));
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new RadarEntry((float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }
        return entry;
    }

}
