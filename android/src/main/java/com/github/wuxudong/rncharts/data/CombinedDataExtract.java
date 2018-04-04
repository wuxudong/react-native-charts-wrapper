package com.github.wuxudong.rncharts.data;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.wuxudong.rncharts.utils.BridgeUtils;

import java.util.ArrayList;

/**
 * Created by xudong on 02/03/2017.
 */
public class CombinedDataExtract extends DataExtract<CombinedData, Entry> {
    private LineDataExtract lineDataExtract = new LineDataExtract();
    private BarDataExtract barDataExtract = new BarDataExtract();
    private ScatterDataExtract scatterDataExtract = new ScatterDataExtract();
    private CandleDataExtract candleDataExtract = new CandleDataExtract();
    private BubbleDataExtract bubbleDataExtract = new BubbleDataExtract();

    @Override
    public CombinedData extract(ReadableMap propMap) {
        CombinedData chartData = new CombinedData();

        if (BridgeUtils.validate(propMap, ReadableType.Map, "lineData")) {
            chartData.setData(lineDataExtract.extract(propMap.getMap("lineData")));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Map, "barData")) {
            chartData.setData(barDataExtract.extract(propMap.getMap("barData")));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Map, "scatterData")) {
            chartData.setData(scatterDataExtract.extract(propMap.getMap("scatterData")));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Map, "candleData")) {
            chartData.setData(candleDataExtract.extract(propMap.getMap("candleData")));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Map, "bubbleData")) {
            chartData.setData(bubbleDataExtract.extract(propMap.getMap("bubbleData")));
        }

        return chartData;


    }

    @Override
    CombinedData createData() {
        throw new UnsupportedOperationException();
    }

    @Override
    IDataSet<Entry> createDataSet(ArrayList<Entry> entries, String label) {
        throw new UnsupportedOperationException();
    }

    @Override
    void dataSetConfig(IDataSet<Entry> dataSet, ReadableMap config) {
        throw new UnsupportedOperationException();
    }

    @Override
    Entry createEntry(ReadableArray values, int index) {
        throw new UnsupportedOperationException();
    }
}
