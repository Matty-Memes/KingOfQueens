package javalampstudos.kingofqueens.kingOfQueens.engine.graphics;

import android.graphics.Canvas;
import android.app.Fragment;

/**
 * Created by 40083349 on 11/01/2017.
 */

public abstract class CanvasFragment extends Fragment

{
    public abstract void doSetup ();

    public abstract void doDraw(Canvas canvas);


}
