package com.github.wuxudong.rncharts.markers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.react.bridge.ReadableMap;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.wuxudong.rncharts.R;

public class RNRectangleMarkerView extends MarkerView {

    private RelativeLayout markerContent;
    private TextView tvContent;

    private Drawable backgroundLeft = getResources().getDrawable(R.drawable.rectangle_marker_left);
    private Drawable background = getResources().getDrawable(R.drawable.rectangle_marker);
    private Drawable backgroundRight = getResources().getDrawable(R.drawable.rectangle_marker_right);

    public RNRectangleMarkerView(Context context) {
        super(context, R.layout.rectangle_marker);

        tvContent = (TextView) findViewById(R.id.rectangle_tvContent);
        markerContent = (RelativeLayout) findViewById(R.id.rectangle_markerContent);
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

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {

        MPPointF offset = getOffset();

        MPPointF offset2 = new MPPointF();

        offset2.x = offset.x;
        offset2.y = offset.y;

        Chart chart = getChartView();

        float width = getWidth();
        float height = getHeight();

        if (posX + offset2.x < 0) {
            offset2.x = 0;

            markerContent.setBackground(backgroundLeft);

        } else if (chart != null && posX + width + offset2.x > chart.getWidth()) {
            offset2.x = - width;

            markerContent.setBackground(backgroundRight);
        } else {
            markerContent.setBackground(background);
        }

        return offset2;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public RelativeLayout getMarkerContent() {
        return markerContent;
    }
}
