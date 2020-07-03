package com.github.wuxudong.rncharts.markers;

import com.github.mikephil.charting.components.MarkerView;
import android.content.Context;

import com.github.mikephil.charting.utils.MPPointF;
import com.github.wuxudong.rncharts.R;

public class RNCircleMarkerView extends MarkerView {
    public RNCircleMarkerView(Context context) {
        //the super will take care of displaying the layout
        super(context, R.layout.circle_marker);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() / 2);
    }
}
