package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import javalampstudos.kingofqueens.MainActivity;
import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

/**
 * Created by User on 04/05/2017.
 */

public class StatsFragment extends MenuFragment {

    //Stats Bitmap
    private Bitmap bground, backBitmap, resetBitmap;
    //Stats Rect
    private Rect bgroundRect, backRect, resetRect;

    private int totalPlayTimeMins, totalPlayTimeSecs, longestPlayTimeMins, longestPlayTimeSecs,
            gamesPlayed, totalCardsPlayed, monstersPlayed, manaCardsPlayed, buffsPlayed,
            numberOfCardsDrawn, monstersDestroyed, numberOfWins, numberOfLoses;

    public StatsFragment() {
    }

    public void doSetup() {
        super.doSetup();

        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();

        // load in bitmaps here
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/Screen 1.png");
        backBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/ButtonBack.png");
        resetBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/reset.png");

        // Sets up Rect parameters
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));


        monstersPlayed = MainActivity.setting.getInt("monstersPlayed");
        manaCardsPlayed = MainActivity.setting.getInt("manaCardsPlayed");
        buffsPlayed = MainActivity.setting.getInt("buffCardsPlayed");
        numberOfCardsDrawn =  MainActivity.setting.getInt("numberOfCardsDrawn");
        monstersDestroyed =  MainActivity.setting.getInt("monstersDestroyed");
        numberOfWins =  MainActivity.setting.getInt("numberOfWins");
        numberOfLoses =  MainActivity.setting.getInt("numberOfLoses");
        gamesPlayed = MainActivity.setting.getInt("gamesPlayed");
        totalCardsPlayed = monstersPlayed + manaCardsPlayed + buffsPlayed;


        int totalPlayTime = MainActivity.setting.getInt("totalPlayTimeValue") / 1000;
        totalPlayTimeMins = totalPlayTime / 60;
        totalPlayTimeSecs = totalPlayTime % 60;

        int longestPlayTime = MainActivity.setting
                .getInt("longestPlayTimeValue") / 1000;
        longestPlayTimeMins = longestPlayTime / 60;
        longestPlayTimeSecs = longestPlayTime % 60;

        // Sets up Rect values
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));
        resetRect =  new Rect(755, 50, 805, 100);
    }

    /**
     * Draws assets to the canvas
     */
    public void doDraw(Canvas canvas) {
        super.doDraw(canvas);

        // Setting the paint values and drawing the title to the canvas
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(32 * uiScaling);


        bgroundRect.set(0, 0, width, height);
        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);
        canvas.drawBitmap(resetBitmap, null, resetRect, null);

        canvas.drawText(totalCardsPlayed + "", width / 2 + 208 * uiScaling, height / 2
                - 64 * uiScaling, paint);
        canvas.drawText(monstersPlayed + "", width / 2 + 208 * uiScaling, height
                / 2 - 24 * uiScaling, paint);
        canvas.drawText(manaCardsPlayed + "", width / 2 + 208 * uiScaling,
                height / 2 + 16 * uiScaling, paint);
        canvas.drawText(numberOfWins + "", width / 2 + 208 * uiScaling,
                height / 2 + 56 * uiScaling, paint);
        canvas.drawText(gamesPlayed + "", width / 2 + 208 * uiScaling,
                height / 2 + 96 * uiScaling, paint);
        canvas.drawText(String.format("%d:%02d", totalPlayTimeMins,
                totalPlayTimeSecs), width / 2 + 208 * uiScaling, height / 2
                + 136 * uiScaling, paint);
        canvas.drawText(String.format("%d:%02d", longestPlayTimeMins,
                longestPlayTimeSecs), width / 2 + 208 * uiScaling, height
                / 2 + 176 * uiScaling, paint);

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if (backRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new SettingsFragment(), "settings_fragment").commit();
                }

                if (resetRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new ResetFragment(), "reset_fragment").commit();
                }

            }
        }
    }
}


