package com.bignerdranch.android.draganddraw;

import android.graphics.PointF;

/**
 * Created by Veloso on 6/13/2016.
 */
public class Box {

    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF origin) {
        mOrigin = origin;
        mCurrent = origin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF point) {
        mCurrent = point;
    }

    public PointF getOrigin() {
        return mOrigin;
    }

}
