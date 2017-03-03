package com.github.wuxudong.rncharts.markers;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.react.bridge.ReadableMap;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

public abstract class RNMarkerView extends MarkerView {

    private RelativeLayout markerContent;
    private TextView tvContent;

    public RNMarkerView(Context context, int layoutResource, int markerContentId, int tvContentId) {
        super(context, layoutResource);

        tvContent = (TextView) findViewById(tvContentId);
        markerContent = (RelativeLayout) findViewById(markerContentId);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String text = "";

        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            text = Utils.formatNumber(ce.getClose(), 2, true);
        } else {
            text = Utils.formatNumber(e.getY(), 0, true);
        }

        if (e.getData() instanceof ReadableMap) {
            if(((ReadableMap) e.getData()).hasKey("marker")) {
                text = ((ReadableMap) e.getData()).getString("marker");
            }
        }

        tvContent.setText(text);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF( -(getWidth() / 2), -getHeight());
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public RelativeLayout getMarkerContent() {
        return markerContent;
    }
}
