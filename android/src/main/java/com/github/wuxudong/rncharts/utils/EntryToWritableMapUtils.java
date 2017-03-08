package com.github.wuxudong.rncharts.utils;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * Helper utilities to convert from Entry to WritableMap
 */
public final class EntryToWritableMapUtils {
    public static WritableMap convertEntryToWritableMap(Entry entry) {
        if(entry == null) {
            return null;
        }

        WritableMap map = new WritableNativeMap();

        if(entry.getData() instanceof Map) {
            map.putMap("data", convertMapToWritableMap((Map) entry.getData()));
        }

        if (entry instanceof BarEntry) {
            BarEntry barEntry = (BarEntry) entry;

            map.putDouble("x", entry.getX());

            if (barEntry.getYVals() != null) {
                WritableArray array = new WritableNativeArray();
                for (float f: barEntry.getYVals()) {
                    array.pushDouble(f);
                }

                map.putArray("yValues", array);
            } else {
                map.putDouble("y", entry.getY());
            }
        } else if (entry instanceof BubbleEntry) {
            BubbleEntry bubbleEntry = (BubbleEntry) entry;

            map.putDouble("x", entry.getX());
            map.putDouble("y", entry.getY());

            map.putDouble("size", bubbleEntry.getSize());
        } else if (entry instanceof CandleEntry) {
            CandleEntry candleEntry = (CandleEntry) entry;

            map.putDouble("x", entry.getX());

            map.putDouble("open", candleEntry.getOpen());
            map.putDouble("close", candleEntry.getClose());
            map.putDouble("low", candleEntry.getLow());
            map.putDouble("high", candleEntry.getHigh());
        } else if (entry instanceof PieEntry) {
            PieEntry pieEntry = (PieEntry) entry;

            map.putDouble("value", pieEntry.getValue());
            map.putString("label", pieEntry.getLabel());
        } else if (entry instanceof RadarEntry) {
            RadarEntry radarEntry = (RadarEntry) entry;

            map.putDouble("value", radarEntry.getValue());
        } else {
            map.putDouble("x", entry.getX());
            map.putDouble("y", entry.getY());

        }

        return map;
    }

    private static WritableMap convertMapToWritableMap(Map map) {
        return convertJsonToWritableMap(new JSONObject(map));
    }

    private static WritableMap convertJsonToWritableMap(JSONObject jsonObject) {
        if(jsonObject == null) {
            return null;
        }

        WritableMap map = new WritableNativeMap();

        try {
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject) {
                    map.putMap(key, convertJsonToWritableMap((JSONObject) value));
                } else if (value instanceof  JSONArray) {
                    map.putArray(key, convertJsonToWritableArray((JSONArray) value));
                } else if (value instanceof  Boolean) {
                    map.putBoolean(key, (Boolean) value);
                } else if (value instanceof  Integer) {
                    map.putInt(key, (Integer) value);
                } else if (value instanceof  Double) {
                    map.putDouble(key, (Double) value);
                } else if (value instanceof String)  {
                    map.putString(key, (String) value);
                } else {
                    map.putString(key, value.toString());
                }
            }
        } catch(JSONException ex) {
            map.putString("error", "Failed to convert JSONObject to WritableMap: " + ex.getMessage());
        }

        return map;
    }

    private static WritableArray convertJsonToWritableArray(JSONArray jsonArray) throws JSONException {
        WritableArray array = new WritableNativeArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof JSONObject) {
                array.pushMap(convertJsonToWritableMap((JSONObject) value));
            } else if (value instanceof  JSONArray) {
                array.pushArray(convertJsonToWritableArray((JSONArray) value));
            } else if (value instanceof  Boolean) {
                array.pushBoolean((Boolean) value);
            } else if (value instanceof  Integer) {
                array.pushInt((Integer) value);
            } else if (value instanceof  Double) {
                array.pushDouble((Double) value);
            } else if (value instanceof String)  {
                array.pushString((String) value);
            } else {
                array.pushString(value.toString());
            }
        }
        return array;
    }
}
