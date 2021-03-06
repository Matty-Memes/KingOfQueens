package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.Stats;

/**
 * Created by User on 05/05/2017.
 */

public class ResetFragment extends MenuFragment {
    //Bitmaps
    private Bitmap bground, areYouSureBitmap, yesBitmap, noBitmap;
    //Rects
    private Rect bgroundRect, areYouSureRect, yesRect, noRect;

    public ResetFragment() {

    }

    public void doSetup() {
        super.doSetup();

        // Loads in image assets related to the currently selected language
        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();

        // Load bitmaps for menu buttons
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/StatsBack.PNG");
        areYouSureBitmap= AssetLoader.loadBitmap(assetManager, "img/Marc/AreYouSure.png");
        yesBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/Yes.png");
        noBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/No.png");

        // Set up values for each menu rect
        areYouSureRect = new Rect((int) (width / 2 - 200 * uiScaling),
                (int) (height / 2 - 80 * uiScaling),
                (int) (width / 2 + 200 * uiScaling),
                (int) (height / 2 + 80 * uiScaling));
        yesRect = new Rect((int) (width / 2 - 176 * uiScaling),
                (int) (height / 2 + 2 * uiScaling),
                (int) (width / 2 - 16 * uiScaling),
                (int) (height / 2 + 90 * uiScaling));
        noRect = new Rect((int) (width / 2 + 16 * uiScaling),
                (int) (height / 2 + 2 * uiScaling),
                (int) (width / 2 + 176 * uiScaling),
                (int) (height / 2 + 90 * uiScaling));
    }

    public void doDraw(Canvas canvas)

    {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);

        // draw each bitmap to the screen
        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(areYouSureBitmap, null, areYouSureRect, null);
        canvas.drawBitmap(yesBitmap, null, yesRect, null);
        canvas.drawBitmap(noBitmap, null, noRect, null);


        // Touch input for each menu rect

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if(yesRect.contains(x, y)) {
                    Stats.resetStats();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new StatsFragment(),
                                    "stats_fragment").commit();
                }

                if(noRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new StatsFragment(),
                                    "stats_fragment").commit();
                }

            }
        }

    }
}


