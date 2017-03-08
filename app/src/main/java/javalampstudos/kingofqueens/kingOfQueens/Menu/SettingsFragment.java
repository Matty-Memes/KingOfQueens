package javalampstudos.kingofqueens.kingOfQueens.Menu;

/**
 * Created by Andrew on 08/03/2017.
 */



import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

public class SettingsFragment extends MenuFragment


{

   private Bitmap bground;
   private Rect bgroundRect;


   // Constructor
   public SettingsFragment ()

   {


   }

    public void doSetup ()


    {
        // call the doSetup method of the superclass
        super.doSetup();

        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();

        // load in bitmaps here
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/MarcBackground.png");


    }

    public void doDraw (Canvas canvas)

    {
        // omit super.doDraw

        bgroundRect.set(0, 0, width, height);

        canvas.drawBitmap(bground, null, bgroundRect, null);

    }


}
