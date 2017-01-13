package javalampstudos.kingofqueens;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// import the asset loader from the IO package

import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

import android.graphics.Matrix;
import android.view.WindowManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameViewFragment extends CanvasFragment {

    // instantiate a loop
    protected GameLoop loop;

    // image stuff that the whole class can see
    private Paint mPaint;
    // these are the bitmaps themselves which map to the stuff below
    private Bitmap mImage;
    private Bitmap mImage2;
    private Bitmap mImage3;


    private int width, height;

    // Needs these for collision detection
    private Rect mLittleManBound;
    private Rect cloudyBackgroundBound;
    private Rect KofQBound;
    private Rect mLittleManBound2;


    // what's this for??
    private Matrix matrix = new Matrix();

    // Empty GameViewFragment constructor

    public GameViewFragment ()

    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ((MainActivity) getActivity()).hideNav();

        WindowManager wm = ((WindowManager) getActivity().getSystemService(
                Context.WINDOW_SERVICE));
        Display display = wm.getDefaultDisplay();
        Point point = new Point();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            display.getRealSize(point);
        else
            display.getSize(point);

        width = point.x;
        height = point.y;

        // instantiate the loop and start things running
        loop = new GameLoop(this, width, height);
        return loop.canvasRenderer;

        /*
        // Create a new render surface renderer that will be used to provide the
        // render view for this fragment
        surfaceViewRenderer = new SurfaceViewRenderer(getActivity());
        return surfaceViewRenderer;

        */

    }




    // ////////////////////////////////////////////////////////////////////////
    // Setup and Draw Methods
    // ////////////////////////////////////////////////////////////////////////

    /**
     * Define the data items used when drawing
     */


    private long mNumCalls;


    // The render thread calls this - see Canvas Renderer

    public void doSetup() {


        mPaint = new Paint();

        // create an AssetManager
        AssetManager assetManager = getActivity().getAssets();

        mImage = AssetLoader.loadBitmap(assetManager, "img/Nathan/trimLittleMan.png");
        mImage2 = AssetLoader.loadBitmap(assetManager, "img/Nathan/cloudyBackground.png");
        mImage3 = AssetLoader.loadBitmap(assetManager, "img/Nathan/KofQ.png");

        // deal with just the background
        // instantiate a rectangle that relates to the background
        cloudyBackgroundBound = new Rect(0, 0, width, height);


    }

    /**
     * Method that will be called by the render thread when the canvas needs to
     * be redrawn
     *
     * @param canvas
     *            Canvas to be redrawn
     */

    /*

    private Rect mLittleManBound;
    private Rect cloudyBackgroundBound;
    private Rect KofQBound;
    private Rect mLittleManBound2;

    */


    public void doDraw(Canvas canvas) {

        // start with just this
        // there's no reason why you have to use everything
        canvas.drawBitmap(mImage2, null, cloudyBackgroundBound, null);


        // might be able to add text

        /*

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Draw the loaded bitmap 1,000 times
        int batchSize = 1000;
        for (int drawIdx = 0; drawIdx < batchSize; drawIdx++) {


            int spacingX = width / 6;
            int spacingY = height / 6;
            mLittleManBound  = new Rect(2 * spacingX, spacingY, 4 * spacingX, 5 * spacingY);
            cloudyBackgroundBound = new Rect (0, 0, 6 * spacingX, 6 * spacingY);
            KofQBound = new Rect (10, 4* spacingY, 2 * spacingX, 6 * spacingY);
            //mLittleManBound2 = new Rect(4 * spacingX, spacingY, 5 * spacingX, 2 * spacingY);

            //canvas.drawColor(-1);

            // these lines are replaced with the first line
            canvas.drawBitmap(mImage2, null, cloudyBackgroundBound, null);
            canvas.drawBitmap(mImage, null, mLittleManBound, null);
            canvas.drawBitmap(mImage3, null, KofQBound, null);
            //canvas.drawBitmap(mImage, null, mLittleManBound2, null);
        }

        // Display a count of the number of frames that have been displayed
        mNumCalls++;
        mPaint.setTextSize(36.0f);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("Num=" + mNumCalls, 50.0f, 50.0f, mPaint);

        */
    }

    // ////////////////////////////////////////////////////////////////////////
    // Render Thread
    // ////////////////////////////////////////////////////////////////////////

    /**
     * Surface view render thread that will repeatedly acquire the view and
     * render to it as fast as possible.
     */


    public void onPause() {

        loop.pause();
        super.onPause();
    }

    public void onResume()

    {
        super.onResume();
        loop.resume();


    }







}
