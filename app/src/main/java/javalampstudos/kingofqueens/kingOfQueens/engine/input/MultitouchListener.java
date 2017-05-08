package javalampstudos.kingofqueens.kingOfQueens.engine.input;

/**
 * Created by 40083349 on 12/01/2017.
 */

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

// Sourced from Blasto by mtstudios
public class MultitouchListener implements OnTouchListener

{
    public static final int X_AXIS = 0, Y_AXIS = 1;

    public final int MAX_TOUCH_POINTS = 10;

    private boolean touchDown[] = new boolean[MAX_TOUCH_POINTS];
    private boolean touchContinuous[] = new boolean[MAX_TOUCH_POINTS];
    private float touchX[] = new float[MAX_TOUCH_POINTS];
    private float touchY[] = new float[MAX_TOUCH_POINTS];

    /**
     * Creates a new MultitouchListener object.
     */
    public MultitouchListener() {
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        int pointerId = e.getPointerId(e.getActionIndex());

        if(pointerId < MAX_TOUCH_POINTS) {
            if(e.getActionMasked() == MotionEvent.ACTION_UP
                    || e.getActionMasked() == MotionEvent.ACTION_POINTER_UP)
                touchContinuous[pointerId] = false;
            else
                touchContinuous[pointerId] = true;

            if(e.getActionMasked() == MotionEvent.ACTION_DOWN) {
                touchDown[pointerId] = true;
            }

            for(int pointer = 0; pointer < e.getPointerCount(); pointer++) {
                touchX[e.getPointerId(pointer)] = e.getX(pointer);
                touchY[e.getPointerId(pointer)] = e.getY(pointer);
            }
        }

        return true;
    }

    /**
     * Returns true as long as the specified pointer is down
     *
     * @param touchId
     *            The pointer ID.
     * @return True if touch is continuous, false otherwise.
     */
    public boolean isTouchContinuous(int touchId) {
        return touchContinuous[touchId];
    }

    /**
     * Returns true for the first frame of a touch event
     *
     * @param touchId
     *            The pointer ID.
     * @return True for the first occurrence of touch, false otherwise.
     */
    public boolean isTouchDown(int touchId) {
        if(touchDown[touchId]) {
            touchDown[touchId] = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the current x of the touch with the specified pointer ID
     *
     * @param touchId
     *            The pointer ID.
     * @return X position of touch.
     */
    public float getTouchX(int touchId) {
        return touchX[touchId];
    }

    /**
     * Returns the current y of the touch with the specified pointer ID
     *
     * @param touchId
     *            The pointer ID.
     * @return Y position of touch.
     */
    public float getTouchY(int touchId) {
        return touchY[touchId];
    }


}
