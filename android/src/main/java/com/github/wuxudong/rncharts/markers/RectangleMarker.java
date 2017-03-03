package com.github.wuxudong.rncharts.markers;

import android.content.Context;

import com.github.wuxudong.rncharts.R;

public class RectangleMarker extends RNMarkerView {
    public RectangleMarker(Context context) {
        super(context, R.layout.rectangle_marker, R.id.rectangle_markerContent, R.id.rectangle_tvContent);
    }
}
