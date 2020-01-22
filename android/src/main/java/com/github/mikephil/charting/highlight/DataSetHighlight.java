package com.github.mikephil.charting.highlight;

public class DataSetHighlight {

    private int index;
    private float pixelDistance;
    private float pointDistance;

    public DataSetHighlight(int index) {
        this.index = index;
    }

    public DataSetHighlight(int index, float pixelDistance, float pointDistance) {
        this.index = index;
        this.pixelDistance = pixelDistance;
        this.pointDistance = pointDistance;
    }

    public int getIndex() {
        return index;
    }
    
    public float getPixelDistance() {
        return pixelDistance;
    }

    public float getPointDistance() {
        return pointDistance;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setPixelDistance(float pixelDistance) {
        this.pixelDistance = pixelDistance;
    }

    public void getPointDistance(float pointDistance) {
        this.pointDistance = pointDistance;
    }

    public boolean equalTo(DataSetHighlight h) {
        if (h == null)
            return false;
        else 
            return this.index == h.index;
    }

    @Override
    public String toString() {
        return "DataSetHighlight, index: " + index + ", pixelDistance: " + pixelDistance + ", pointDistance: " + pointDistance;
    }

}