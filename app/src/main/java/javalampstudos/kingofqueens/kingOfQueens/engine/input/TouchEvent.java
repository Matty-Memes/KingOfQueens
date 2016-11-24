
package javalampstudos.kingofqueens.kingOfQueens.engine.input;

/**
 * Created by User on 24/11/2016.
 */

public class TouchEvent {

    //constants
    public static final int TOUCH_DOWN = 0;
    public static final int TOUCH_UP = 1;
    public static final int TOUCH_DRAGGED = 2;

    //type of touch event that has occured
    public int type;

    //screen position (pixels) at ehich the touch event occured
    public float x, y;

    //pointer associated with touch event
    public int pointer;

}
