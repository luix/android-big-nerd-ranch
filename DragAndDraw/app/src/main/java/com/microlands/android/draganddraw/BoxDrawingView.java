package com.microlands.android.draganddraw;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by luisvivero on 7/8/16.
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";

    public BoxDrawingView(Context context) {
        this(context, null);
    }

    public BoxDrawingView(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                break;

            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                break;

            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                break;

            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                break;
        }

        Log.i(TAG, action + " at x = " + current.x + " , y = " + current.y);

        return true;
    }
}
