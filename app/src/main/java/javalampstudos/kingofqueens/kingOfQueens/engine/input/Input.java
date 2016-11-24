package javalampstudos.kingofqueens.kingOfQueens.engine.input;

/**
 * Created by User on 24/11/2016.
 */

import android.view.View;

import java.util.List;

public class Input {

    //touch handler that is responsible for touch input
    private TouchHandler mTouchHandler;

    //creating a new input manager for the specified content view
    //view that this handler will collect input from
    public Input (View view) {
        mTouchHandler = new TouchHandler(view);
    }

    //determine if there is an ongoing touch event for the specified pointer
    //test needed
    //true if there is an ongoing touch event, otherwise false
    public boolean existsTouch(int pointerId) {
        return mTouchHandler.existsTouch(pointerId);
    }

    //gets the x-cordinate for the specified pointer
    //test needed
    //returns x-cordinate value if any
    public float getTouchX(int pointerId) {
        return mTouchHandler.getTouchX(pointerId);
    }
    //gets the y-cordinate for the specified pointer
    //test needed
    //returns y-cordinate value if any
    public float getTouchY(int pointerId) {
        return mTouchHandler.getTouchY(pointerId);
    }

    //returns a list of captured touch events
    public List<TouchEvent> getTouchEvents() {
        return mTouchHandler.getTouchEvents();
    }

    //reset the touch accumulators
    public void resetAccumulators() {
        mTouchHandler.resetAccumulator();
    }
}
