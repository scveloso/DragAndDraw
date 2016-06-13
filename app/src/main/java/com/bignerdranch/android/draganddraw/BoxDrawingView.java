package com.bignerdranch.android.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veloso on 6/13/2016.
 */
public class BoxDrawingView extends View{

    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<>();

    private Line mCurrentLine;
    private List<Line> mLines = new ArrayList<>();

    private Paint mPaint;
    private Paint mBackgroundPaint;
    private String mMode;

    // Used when creating the view in code
    public BoxDrawingView(Context context) {
        this(context, null);
    }

    // Used when inflating the view from XML
    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Paint the boxes a nice semitransparent red(ARGB)
        mPaint = new Paint();
        mPaint.setColor(0x22ff0000);

        // Paint the background off-white
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);

        mMode = "Box";
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (mMode) {
            case "Line":
                handleLineEvent(event);
                break;
            case "Box":
                handleBoxEvent(event);
                break;
        }
        return true;
    }

    private void handleBoxEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCurrentBox = new Box(current);
                mBoxen.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_UP:
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    // Makes the BoxDrawingView invalid again, causing it to redraw itself and its children - calling onDraw
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mCurrentBox = null;
                break;
        }
    }

    private void handleLineEvent (MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCurrentLine = new Line(current);
                mLines.add(mCurrentLine);
                break;
            case MotionEvent.ACTION_UP:
                mCurrentLine = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCurrentLine != null) {
                    mCurrentLine.setCurrent(current);
                    // Makes the BoxDrawingView invalid again, causing it to redraw itself and its children - calling onDraw
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mCurrentLine = null;
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Fill the background
        canvas.drawPaint(mBackgroundPaint);

        for (Box box: mBoxen) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left, top, right, bottom, mPaint);
        }

        for (Line line: mLines) {
            float startX = line.getOrigin().x;
            float startY = line.getOrigin().y;
            float endX = line.getCurrent().x;
            float endY = line.getCurrent().y;

            canvas.drawLine(startX, startY, endX, endY, mPaint);
        }
    }

    public void setMode (String s) {
        mMode = s;
    }
}
