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
        try {
            MPPointD pos = getValsForTouch(x, y);
            float xVal = (float) pos.x;
            float yVal = (float) pos.y;
            MPPointD.recycleInstance(pos);

            List<LineDataSet> dataSets = mChart.getData().getDataSets();

            float minDistance = Float.MAX_VALUE;
            int minIndex = -1;
            Float m1 = null;
            Float q1 = null;

            for (int i = 0; i < dataSets.size(); i++) {

                if (dataSets.get(i).isVisible()) {
                    List<Entry> result = dataSets.get(i).getExtremesEntriesForXValue(xVal);
                    if (result == null) {
                        continue;
                    }

                    float x_1 = result.get(0).getX();
                    float x_2 = result.get(1).getX();

                    float y_1 = result.get(0).getY();
                    float y_2 = result.get(1).getY();

                    float distance;
                    float m;
                    float q;
                    if ((x_2 - x_1) == 0) {
                        m = Float.MAX_VALUE;
                        q = x_1;
                        distance = Math.abs(x_1 - xVal);
                    } else if ((y_2 - y_1) == 0) {
                        m = 0;
                        q = y_1;
                        distance = Math.abs(y_1 - yVal);
                    }
                    else {
                        m = (y_2 - y_1) / (x_2 - x_1);
                        q = (x_2 * y_1 - x_1 * y_2) / (x_2 - x_1);
                        distance = (float) (Math.abs(yVal - (m * xVal + q)) / Math.sqrt(1 + m * m));
                    }

                    if (distance < minDistance) {
                        minDistance = distance;
                        minIndex = i;
                        m1 = m;
                        q1 = q;
                    }
                }
            }

            if (m1 == null || q1 == null) {
                return -1;
            }

            float xIntersection;
            float yIntersection;
            if (m1 == Float.MAX_VALUE) {
                xIntersection = q1;
                yIntersection = yVal;
            }
            else if (m1 == 0) {
                xIntersection = xVal;
                yIntersection = q1;
            }
            else {
                float m2 = -1/m1;
                float q2 = xVal/m1 + yVal;
                xIntersection = (q1 - q2) / (m2 - m1);
                yIntersection = xIntersection * m2 + q2;
            }

            pos = getPixForVal(xIntersection, yIntersection);
            float pixX = (float) pos.x;
            float pixY = (float) pos.y;
            MPPointD.recycleInstance(pos);

            double dist = Math.sqrt((xVal - xIntersection) * (xVal - xIntersection) + (yVal - yIntersection) * (yVal - yIntersection));
            double pixDist = Math.sqrt((x - pixX) * (x - pixX) + (y - pixY) * (y - pixY));
            return pixDist < 30 && dist < 5 ? minIndex : -1;

        } catch (Exception e) {
            // if any calc error occurs, the value -1 have no effect on the app behavior
            return -1;
        }
    }

    /**
     * Returns a recyclable MPPointD instance. Returns the corresponding xPos for a
     * given touch-position in pixels.
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

    protected MPPointD getPixForVal(float x, float y) {

        // take any transformer to determine the x-axis value
        MPPointD pos = mChart.getTransformer(YAxis.AxisDependency.LEFT).getPixelForValues(x, y);
        return pos;
    }
}
