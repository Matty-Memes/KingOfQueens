
package javalampstudos.kingofqueens.kingOfQueens.engine.input;

import android.annotation.SuppressLint;
import android.support.v4.util.Pools;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javalampstudos.kingofqueens.kingOfQueens.util.Pool;

/**
 * Created by User on 24/11/2016.
 */

public class TouchHandler implements View.OnTouchListener {

    //define the max number of touch events that can be supported
    public static final int MAX_TOUCHPOINTS = 10;


    //occured and position information for the supported number of touch events
    private boolean[] mExistsTouch = new boolean[MAX_TOUCHPOINTS];
    private float mTouchX[] = new float[MAX_TOUCHPOINTS];
    private float mTouchY[] = new float[MAX_TOUCHPOINTS];

    //touch event pool and lists of current (for this frame) and unconsumed
    // (occuring since the frame started) touch events
    private Pools.Pool<TouchEvent> mPool;
    private List<TouchEvent> mTouchEvents = new ArrayList<TouchEvent>();
    private List<TouchEvent> mUnconsumedTouchEvents = new ArrayList<TouchEvent>();

    //axis scale values
    private float mScaleX;
    private float mScaleY;

    //define the maximum number of touch events that can be retained in the
    //touch store
    private final int TOUCH_POOL_SIZE = 100;

    //create a new touch handler instance for the specified view
    //view whose touch events shpuld be captured by this handler
    public TouchHandler(View view) {

        mPool = new Pools.Pool<TouchEvent>(new Pool.ObjectFactory<TouchEvent>() {
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        }, TOUCH_POOL_SIZE);

        view.setOnTouchListener(this);

        // raw pixel or 0-1 or -1 to 1 ranges
        mScaleX = 1.0f;
        mScaleY = 1.0f;
    }

    //set the scaling facor that is applied along the x-axis
    public void setScaleX(float scaleX) {
        mScaleX = scaleX;
    }

    //set the scaling factor that is applied along the y-axis
    public void setScaleY(float scaleY) {
        mScaleY = scaleY;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //update the locations of all occuring touch points
        for (int ptrIdx = 0; ptrIdx < event.getPointerCount(); ptrIdx++) {
            //update the relavent touch point locations
            int pointerId = event.getPointerId(ptrIdx);
            mTouchX[pointerId] = event.getX(ptrIdx) * mScaleX;
            mTouchY[pointerId] = event.getY(ptrIdx) * mScaleY;
        }

        // Extract details of this event
        int eventType = event.getActionMasked();
        int pointerId = event.getPointerId(event.getActionIndex());

        // Retrieve and populate a touch event
        TouchEvent touchEvent = mPool.get();
        touchEvent.pointer = pointerId;
        touchEvent.x = mTouchX[pointerId];
        touchEvent.y = mTouchY[pointerId];

        switch (eventType) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touchEvent.type = TouchEvent.TOUCH_DOWN;
                mExistsTouch[pointerId] = true;
                break;

            case MotionEvent.ACTION_MOVE:
                touchEvent.type = TouchEvent.TOUCH_DRAGGED;
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                touchEvent.type = TouchEvent.TOUCH_UP;
                mExistsTouch[pointerId] = false;
                break;
        }

        //add this touch event to the list of unconsumed events
        synchronized (this) {
            mUnconsumedTouchEvents.add(touchEvent);
        }

        return true;
    }
    //determine if the touch event exists for the specified pointer
    public boolean existsTouch(int pointerId) {
        synchronized (this) {
            return mExistsTouch[pointerId];
        }
    }

    //get the scaled x-location for the specified ponter
    public float getTouchX(int pointerId) {
        synchronized (this) {
            // Assumes the user will ensure correct range checking - for speed
            if (mExistsTouch[pointerId])
                return mTouchX[pointerId];
            else
                return Float.NaN;
        }
    }

    //get the scaled y-location for the specified ponter
    public float getTouchY(int pointerId) {
        synchronized (this) {
            // Assumes the user will ensure correct range checking - for speed
            if (mExistsTouch[pointerId])
                return mTouchY[pointerId];
            else
                return Float.NaN;
        }
    }

    //return the list of touch events accumulated for the current frame
    public List<TouchEvent> getTouchEvents() {
        synchronized (this) {
            return mTouchEvents;
        }
    }

    //reset the accumulator
    public void resetAccumulator() {
        synchronized (this) {
            // Release all existing touch events
            int len = mTouchEvents.size();
            for (int i = 0; i < len; i++)
                mPool.add(mTouchEvents.get(i));
            mTouchEvents.clear();
            // Copy across accumulated events
            mTouchEvents.addAll(mUnconsumedTouchEvents);
            mUnconsumedTouchEvents.clear();
        }
    }
}
}