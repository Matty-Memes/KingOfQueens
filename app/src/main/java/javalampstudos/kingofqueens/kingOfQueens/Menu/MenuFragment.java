package javalampstudos.kingofqueens.kingOfQueens.Menu;

/**
 * Created by Andrew on 05/03/2017.
 */


// Local Imports
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;
import javalampstudos.kingofqueens.MainActivity;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasRenderer;
import javalampstudos.kingofqueens.kingOfQueens.engine.input.*;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

// Android Imports
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

// Java Imports

public abstract class MenuFragment extends CanvasFragment

{
    // Declare Variables
    protected int height;
    protected int width;
    protected float uiScaling, gameScaling = 0;
    protected CanvasRenderer renderer;
    protected MultitouchListener input;

    // Sprites and bitmaps etc
    protected Bitmap backgroundSprite;
    protected Rect backgroundRect;

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

        gameScaling = width / 256.0f;

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        uiScaling = metrics.density;

        ((MainActivity) getActivity()).music();

        renderer = new CanvasRenderer(getActivity(), this);
        input = new MultitouchListener();
        renderer.setOnTouchListener(input);

        return renderer;
    }

    public MenuFragment ()

    {


    }

    public void doSetup ()

    {

        AssetManager assetManager = getActivity().getAssets();

        // Set up the basic background
        backgroundRect = new Rect();
        backgroundSprite = AssetLoader.loadBitmap(assetManager,
                "img/Nathan/cloudyBackground.png");

        /*

        Splash text can go here as well

         */




    }

    public void doDraw (Canvas canvas)

    {
        backgroundRect.set(0, 0, width, height);

        canvas.drawBitmap(backgroundSprite, null, backgroundRect, null);


    }

    @Override
    public void onPause() {
        renderer.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        renderer.resume();
    }



}
