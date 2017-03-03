package com.github.wuxudong.rncharts.data;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.wuxudong.rncharts.utils.BridgeUtils;

import java.util.ArrayList;

/**
 * Created by xudong on 02/03/2017.
 */

public abstract class DataExtract<D extends ChartData, U extends Entry> {

    public D extract(ReadableMap propMap) {
        if (!BridgeUtils.validate(propMap, ReadableType.Array, "dataSets")) {
            return null;
        }

        D chartData = createData();

        if (BridgeUtils.validate(propMap, ReadableType.Map, "config")) {
            dataConfig(chartData, propMap.getMap("config"));
        }

        ReadableArray dataSets = propMap.getArray("dataSets");
        for (int i = 0; i < dataSets.size(); i++) {
            ReadableMap dataSet = dataSets.getMap(i);

            // TODO validation
            ReadableArray values = dataSet.getArray("values");
            String label = dataSet.getString("label");

            ArrayList<U> entries = createEntries(values);

            IDataSet<U> lineDataSet = createDataSet(entries, label);

            if (BridgeUtils.validate(dataSet, ReadableType.Map, "config")) {
                dataSetConfig(lineDataSet, dataSet.getMap("config"));
            }

            chartData.addDataSet(lineDataSet);
        }


        return chartData;
    }

    abstract D createData();

    void dataConfig(D data, ReadableMap config) {}

    abstract IDataSet<U> createDataSet(ArrayList<U> entries, String label);

    abstract void dataSetConfig(IDataSet<U> dataSet, ReadableMap config);

    ArrayList<U> createEntries(ReadableArray yValues) {
        ArrayList<U> entries = new ArrayList<>(yValues.size());
        for (int j = 0; j < yValues.size(); j++) {
            if (!yValues.isNull(j)) {
                entries.add(createEntry(yValues, j));
            }
        }
        return entries;
    }

    abstract U createEntry(ReadableArray values, int index);



}
