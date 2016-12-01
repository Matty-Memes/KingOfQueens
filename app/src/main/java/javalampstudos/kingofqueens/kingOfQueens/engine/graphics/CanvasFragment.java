package javalampstudos.kingofqueens.kingOfQueens.engine.graphics;

/**
 * Created by 40083349 on 01/12/2016.
 */

import android.app.Fragment;
import android.graphics.Canvas;

// if there are any abstract methods then the class has to be abstract

public abstract class CanvasFragment extends Fragment

{
    public abstract void doSetup ();

    public abstract void doDraw(Canvas canvas);



}
