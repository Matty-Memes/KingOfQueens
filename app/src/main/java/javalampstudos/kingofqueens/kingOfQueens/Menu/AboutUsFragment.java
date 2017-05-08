package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

/**
 * Created by User on 04/05/2017.
 */

public class AboutUsFragment extends MenuFragment {

    //About Us Bitmap
    private Bitmap bground, backBitmap, javaBitmap;
    //About Us Rect
    private Rect bgroundRect, backRect, javaRect;

    //HowToPlay Strings
    private String line1String;

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
        javaBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/JavaLampS.png");


        // Sets up Rect parameters
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));
        javaRect = new Rect((int) (width / 2 - 200 * uiScaling),
                (int) (height / 2 - 80 * uiScaling),
                (int) (width / 2 + 200 * uiScaling),
                (int) (height / 2 + 80 * uiScaling));


        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(14 * uiScaling);
        line1String = "We are Java Lamp Studios! Hope you enjoy our game Phil!";

    }

    /**
     * Draws assets to the canvas
     */
    public void doDraw(Canvas canvas) {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);
        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);
        canvas.drawBitmap(javaBitmap, null, javaRect, null);

        canvas.drawText(line1String, width / 2, height / 2 + 100 * uiScaling, paint);

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
