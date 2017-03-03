package com.github.wuxudong.rncharts.markers;

import android.content.Context;
import com.github.wuxudong.rncharts.R;

public class OvalMarker extends RNMarkerView {
    public OvalMarker(Context context) {
        super(context, R.layout.oval_marker, R.id.oval_markerContent, R.id.oval_tvContent);
    }
}
