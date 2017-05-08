package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

/**
 * Created by Marc on 15/03/2017.
 */

//Not Used

public class PauseFragment extends MenuFragment {

    // Pause Menu Bitmaps
    private Bitmap bground, resumeBitmap, settingsBitmap, quitBitmap, backBitmap;
    // Pause Menu Rects
    private Rect bgroundRect, resumeRect, settingsRect, quitRect, backRect;



    public PauseFragment()

    {


    }

    public void doSetup()


    {
        super.doSetup();

        // Loads in image assets related to the currently selected language
        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();


        // Load bitmaps for menu buttons
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/Pause Screen.png");
        resumeBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/Resume.png");
        settingsBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/Settings.png");
        quitBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/quit.png");
        backBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/ButtonBack.png");

        // Set up values for each menu rect
        resumeRect = new Rect((int) (width - 256 * uiScaling - 8 * gameScaling),
                (int) (height / 2 - 56 * uiScaling),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 - 8 * uiScaling));
        settingsRect = new Rect((int) (width - 256 * uiScaling - 8 * gameScaling),
                (int) (height / 2),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 + 48 * uiScaling));
        backRect = new Rect((int) (width - 96 * uiScaling - 3 * gameScaling),
                (int) (height / 2 + 56 * uiScaling),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 + 98 * uiScaling));
        quitRect = new Rect((int) (width - 256 * uiScaling - 8 * gameScaling),
                (int) (height / 2 + 104 * uiScaling),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 + 148 * uiScaling));
    }

    public void doDraw(Canvas canvas)

    {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);

        // draw each bitmap to the screen
        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(resumeBitmap, null, resumeRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);
        canvas.drawBitmap(settingsBitmap, null, settingsRect, null);
        canvas.drawBitmap(quitBitmap, null, quitRect, null);

        // Touch input for each menu rect

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);
            }
        }

    }
}