package com.github.mikephil.charting.highlight;

public interface IDataSetHighlighter {

    /**
     * Returns the index corresponding to the highlighted DataSet near the given x- and y- touch positions in pixels.
     *
     * @param x
     * @param y
     * @return
     */
    int getDataSetIndexHighlight(float x, float y);
}
