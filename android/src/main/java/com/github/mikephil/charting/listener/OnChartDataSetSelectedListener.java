package com.github.mikephil.charting.listener;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.DataSetHighlight;

public interface OnChartDataSetSelectedListener {


    void onDataSetSelected(DataSetHighlight d);

    /**
     * Called when nothing has been selected or an "un-select" has been made.
     */
    void onNothingSelected();
}
