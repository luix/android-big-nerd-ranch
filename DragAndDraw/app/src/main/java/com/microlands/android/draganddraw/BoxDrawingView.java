package com.microlands.android.draganddraw;

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
 * Created by luisvivero on 7/8/16.
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";

    private Box mCurrentBox;
    private List<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    public BoxDrawingView(Context context) {
        this(context, null);
    }

    public BoxDrawingView(Context context, AttributeSet attributes) {
        super(context, attributes);

        // Paint the boxes a nice semitransparent red (ARGB)
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        // Paint the background off-white
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Fill the background
        canvas.drawPaint(mBackgroundPaint);

        for (Box box : mBoxes) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                // Reset drawing state
                mCurrentBox = new Box(current);
                mBoxes.add(mCurrentBox);
                break;

            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                mCurrentBox = null;
                break;

            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                mCurrentBox = null;
                break;
        }

        Log.i(TAG, action + " at x = " + current.x + " , y = " + current.y);

        return true;
    }
}
