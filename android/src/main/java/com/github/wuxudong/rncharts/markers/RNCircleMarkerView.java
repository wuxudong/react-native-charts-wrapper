package com.github.wuxudong.rncharts.markers;

import com.github.mikephil.charting.components.MarkerView;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.util.TypedValue;

import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.wuxudong.rncharts.R;

import java.util.List;
import java.util.Map;

public class RNCircleMarkerView extends MarkerView {
    private ImageView ivContent;

    public void setSize(int size) {
        int dpSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics());
        ivContent.setLayoutParams(new LinearLayout.LayoutParams(dpSize, dpSize));
    }

    public void setStroke(int strokeSize, int strokeColor) {
        GradientDrawable background = (GradientDrawable) ivContent.getBackground();
        background.mutate(); // only change this instance of the xml, not all components using this xml
        int dpSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, strokeSize, getResources().getDisplayMetrics());
        background.setStroke(dpSize, strokeColor); // set stroke width and stroke color
    }

    public void setColor(int markerColor) {
        GradientDrawable background = (GradientDrawable) ivContent.getBackground();
        background.setColor(markerColor);
    }

    public RNCircleMarkerView(Context context) {
        // the super will take care of displaying the layout
        super(context, R.layout.circle_marker);

        ivContent = (ImageView) findViewById(R.id.circle_ivContent);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() / 2);
    }
}
