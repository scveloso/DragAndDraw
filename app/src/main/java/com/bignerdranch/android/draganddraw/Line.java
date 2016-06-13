package com.bignerdranch.android.draganddraw;

import android.graphics.PointF;

/**
 * Created by Veloso on 6/13/2016.
 */
public class Line {

    private PointF mOrigin;
    private PointF mCurrent;

    public Line (PointF point) {
        mOrigin = point;
        mCurrent = point;
    }

    public PointF getOrigin() {
        return mOrigin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

}
