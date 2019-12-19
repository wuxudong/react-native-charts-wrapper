package com.github.mikephil.charting.listener;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

public interface OnChartDataSetSelectedListener {


    void onDataSetSelected(int dataSetIndex);

    /**
     * Called when nothing has been selected or an "un-select" has been made.
     */
    void onNothingSelected();
}
