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

import java.util.List;
import java.util.Map;

public class RNRectangleMarkerView extends MarkerView {
    
    private TextView tvContent;
    
    private Drawable backgroundLeft = getResources().getDrawable(R.drawable.rectangle_marker_left);
    private Drawable background = getResources().getDrawable(R.drawable.rectangle_marker);
    private Drawable backgroundRight = getResources().getDrawable(R.drawable.rectangle_marker_right);
    
    private Drawable backgroundTopLeft = getResources().getDrawable(R.drawable.rectangle_marker_top_left);
    private Drawable backgroundTop = getResources().getDrawable(R.drawable.rectangle_marker_top);
    private Drawable backgroundTopRight = getResources().getDrawable(R.drawable.rectangle_marker_top_right);
    
    private int digits = 0;

    public RNRectangleMarkerView(Context context) {
        super(context, R.layout.rectangle_marker);
        
        tvContent = (TextView) findViewById(R.id.rectangle_tvContent);
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String text = "";
        
        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            text = Utils.formatNumber(ce.getClose(), digits, true);
        } else {
            text = Utils.formatNumber(e.getY(), digits, true);
        }
        
        if (e.getData() instanceof Map) {
            if(((Map) e.getData()).containsKey("marker")) {

                Object marker = ((Map) e.getData()).get("marker");
                text = marker.toString();

                if (highlight.getStackIndex() != -1 && marker instanceof List) {
                    text = ((List) marker).get(highlight.getStackIndex()).toString();
                }

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
            
            if (posY + offset2.y < 0) {
                offset2.y = 0;
                tvContent.setBackground(backgroundTopLeft);
            } else {
                tvContent.setBackground(backgroundLeft);
            }
            
        } else if (chart != null && posX + width + offset2.x > chart.getWidth()) {
            offset2.x = - width;
            
            if (posY + offset2.y < 0) {
                offset2.y = 0;
                tvContent.setBackground(backgroundTopRight);
            } else {
                tvContent.setBackground(backgroundRight);
            }
        } else {
            if (posY + offset2.y < 0) {
                offset2.y = 0;
                tvContent.setBackground(backgroundTop);
            } else {
                tvContent.setBackground(background);
            }
        }
        
        return offset2;
    }
    
    public TextView getTvContent() {
        return tvContent;
    }
    
}

