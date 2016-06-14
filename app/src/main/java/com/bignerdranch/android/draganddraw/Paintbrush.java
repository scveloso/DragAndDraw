package com.bignerdranch.android.draganddraw;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veloso on 6/14/2016.
 */
public class Paintbrush {

    private List<PointF> mPoints;

    public Paintbrush(PointF origin) {
        mPoints = new ArrayList<>();
        mPoints.add(origin);
    }

    public void addPoint(PointF newPoint) {
        mPoints.add(newPoint);
    }

    public List<PointF> getPoints() {
        return mPoints;
    }
}
