package com.github.mikephil.charting.highlight;

import android.util.Log;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.utils.MPPointD;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ListResourceBundle;

public class LineDataSetHighlighter<T extends LineDataProvider> implements IDataSetHighlighter {

    /**
     * instance of the data-provider
     */
    protected T mChart;

    public LineDataSetHighlighter(T chart) {
        this.mChart = chart;
    }

    @Override
    public int getDataSetIndexHighlight(float x, float y) {
        try{
            MPPointD pos = getValsForTouch(x, y);
            float xVal = (float) pos.x;
            float yVal = (float) pos.y;
            MPPointD.recycleInstance(pos);

            List<LineDataSet> dataSets = mChart.getData().getDataSets();

            float minDistance = Float.MAX_VALUE;
            int minIndex = 0;

            for (int i = 0; i <  dataSets.size(); i++) {

                /*
                Entry p_1 = dataSets.get(i).getEntryForIndex((int) Math.floor(xVal));
                Entry p_2 = dataSets.get(i).getEntryForIndex((int) Math.floor(xVal) + 1);

                float x_1 = p_1.getX();
                float x_2 = p_2.getX();

                float y_1 = p_1.getY();
                float y_2 = p_2.getY();
                */

                List<Entry> result = dataSets.get(i).getExtremesEntriesForXValue(xVal);

                float x_1 = result.get(0).getX();
                float x_2 = result.get(1).getX();

                float y_1 = result.get(0).getY();
                float y_2 = result.get(1).getY();
                
                float distance;

                if ((x_2 - x_1) == 0) {
                    distance = Math.abs(x_1 - x);
                } else {

                    float m = (y_2 - y_1) / (x_2 - x_1);
                    float q = (x_2 * y_1 - x_1 * y_2) / (x_2 - x_1);

                    distance = (float) (Math.abs(yVal - (m * xVal + q)) / Math.sqrt(1 + m * m));
                }

                if (distance < minDistance) {
                    minDistance = distance;
                    minIndex = i;
                }
            }

            Log.i("minIndex", "" + minIndex);
            return minIndex;
        }catch(Exception e){
            //if any calc error occurs, the value -1 have no effect on the app behavior
            return -1;
        }
    }


    /**
     * Returns a recyclable MPPointD instance.
     * Returns the corresponding xPos for a given touch-position in pixels.
     *
     * @param x
     * @param y
     * @return
     */
    protected MPPointD getValsForTouch(float x, float y) {

        // take any transformer to determine the x-axis value
        MPPointD pos = mChart.getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(x, y);
        return pos;
    }
}
