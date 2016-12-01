package javalampstudos.kingofqueens.kingOfQueens.engine.graphics;

/**
 * Created by 40083349 on 01/12/2016.
 */

import android.graphics.Canvas;
import android.content.Context;
import android.view.View;

import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;

public class CanvasRenderer extends View implements Runnable

{
    Thread renderThread = null;

    protected volatile boolean running = false;
    public volatile boolean drawNeeded = false;


    CanvasFragment fragment;

    // CanvasRenderer constructor

    public CanvasRenderer(Context context, CanvasFragment canvasFragment)

    {
        super(context);

        fragment = canvasFragment;
        fragment.doSetup();
    }

    // Must implement the run method

    public void run ()

    {
        while (running)
        {
            if (drawNeeded)
            {
                drawNeeded = false;
                postInvalidate();

            }

        }


    }

    public void onDraw (Canvas canvas)

    {
        fragment.doDraw(canvas);
        drawNeeded = true;
    }

    public void pause ()

    {
        running = false;
        while (true)
        {
            try {
                renderThread.join();
                return;
            }

            catch (InterruptedException e)

            {


            }

        }

    }

    public void resume ()

    {
        running = true;
        drawNeeded = true;
        renderThread = new Thread(this);
        renderThread.start();


    }






}
