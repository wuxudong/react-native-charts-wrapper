package com.github.wuxudong.rncharts.charts;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.github.wuxudong.rncharts.utils.BridgeUtils;

/**
 * Adds support for rendering YAxis labels with outer stroke
 */
public class CustomYAxisRenderer extends YAxisRenderer {
    protected Paint mLabelOutlinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected Boolean mHasStroke = false;

    public CustomYAxisRenderer(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer trans, ReadableMap propMap) {
        super(viewPortHandler, yAxis, trans);

        if (BridgeUtils.validate(propMap, ReadableType.Number, "strokeWidth")) {
            mHasStroke = true;
            mLabelOutlinePaint.setStyle(Paint.Style.STROKE);
            mLabelOutlinePaint.setStrokeWidth(propMap.getInt("strokeWidth"));
            mLabelOutlinePaint.setColor(propMap.getInt("strokeColor"));
        }
        ;
    }

    /**
     * draws the y-axis labels to the screen
     */
    @Override
    public void renderAxisLabels(Canvas c) {

        if (!mYAxis.isEnabled() || !mYAxis.isDrawLabelsEnabled())
            return;

        float[] positions = getTransformedPositions();

        mAxisLabelPaint.setTypeface(mYAxis.getTypeface());
        mAxisLabelPaint.setTextSize(mYAxis.getTextSize());
        mAxisLabelPaint.setColor(mYAxis.getTextColor());
        mLabelOutlinePaint.setTypeface(mYAxis.getTypeface());
        mLabelOutlinePaint.setTextSize(mAxis.getTextSize());

        float xoffset = mYAxis.getXOffset();
        float yoffset = Utils.calcTextHeight(mAxisLabelPaint, "A") / 2.5f + mYAxis.getYOffset();

        YAxis.AxisDependency dependency = mYAxis.getAxisDependency();
        YAxis.YAxisLabelPosition labelPosition = mYAxis.getLabelPosition();

        float xPos = 0f;

        if (dependency == YAxis.AxisDependency.LEFT) {

            if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
                mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
                mLabelOutlinePaint.setTextAlign(Paint.Align.RIGHT);
                xPos = mViewPortHandler.offsetLeft() - xoffset;
            } else {
                mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
                mLabelOutlinePaint.setTextAlign(Paint.Align.LEFT);
                xPos = mViewPortHandler.offsetLeft() + xoffset;
            }

        } else {

            if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
                mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
                mLabelOutlinePaint.setTextAlign(Paint.Align.LEFT);
                xPos = mViewPortHandler.contentRight() + xoffset;
            } else {
                mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
                mLabelOutlinePaint.setTextAlign(Paint.Align.RIGHT);
                xPos = mViewPortHandler.contentRight() - xoffset;
            }
        }

        drawYLabels(c, xPos, positions, yoffset);
    }

    /**
     * draws the y-labels on the specified x-position
     *
     * @param fixedPosition
     * @param positions
     */
    protected void drawYLabels(Canvas c, float fixedPosition, float[] positions, float offset) {

        final int from = mYAxis.isDrawBottomYLabelEntryEnabled() ? 0 : 1;
        final int to = mYAxis.isDrawTopYLabelEntryEnabled()
                ? mYAxis.mEntryCount
                : (mYAxis.mEntryCount - 1);

        // draw
        for (int i = from; i < to; i++) {

            String text = mYAxis.getFormattedLabel(i);

            if (mHasStroke) {
                c.drawText(text, fixedPosition, positions[i * 2 + 1] + offset, mLabelOutlinePaint);
            }
            c.drawText(text, fixedPosition, positions[i * 2 + 1] + offset, mAxisLabelPaint);
        }
    }
}
