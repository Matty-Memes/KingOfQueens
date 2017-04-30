package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

/**
 * Created by Marc on 28/04/2017.
 */

public class LoadingFragment extends MenuFragment {

    //Settings Bitmap
    private Bitmap bground;
    //Settings Rects
    private Rect bgroundRect;


    // Constructor
    public LoadingFragment()

    {


    }

    public void doSetup()


    {
        // call the doSetup method of the superclass
        super.doSetup();

        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();

        // load in bitmaps here
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/JavaLampS.png");

    }

    public void doDraw(Canvas canvas)

    {
        // omit super.doDraw

        bgroundRect.set(0, 0, width, height);

        canvas.drawBitmap(bground, null, bgroundRect, null);


           try{

            Thread.sleep(3000);
        }catch(InterruptedException ex){

        }

        getFragmentManager().beginTransaction().add(R.id.container, new MainMenuFragment()).commit();

//        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
//            if (input.isTouchDown(i)) {
//                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);
//
//                if (bgroundRect.contains(x, y)) {
//                    getFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.container, new MainMenuFragment(), "main_menu_fragment").commit();
//                }


            }
        }





