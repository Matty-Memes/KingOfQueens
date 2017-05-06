package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

/**
 * Created by User on 04/05/2017.
 */

public class AboutUsFragment extends MenuFragment {

    //AboutUs Bitmap
    private Bitmap bground, backBitmap;
    //AboutUs Rect
    private Rect bgroundRect, backRect;

    public AboutUsFragment() {

    }

    public void doSetup() {
        super.doSetup();

        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();

        // load in bitmaps here
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/AboutUsBack.PNG");
        backBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/ButtonBack.png");

        // Sets up Rect parameters
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));

        // Sets up Rect values
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));
    }

    /**
     * Draws assets to the canvas
     */
    public void doDraw(Canvas canvas) {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);
        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);


        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if (backRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new SettingsFragment(), "settings_fragment").commit();
                }


            }
        }
    }
}
