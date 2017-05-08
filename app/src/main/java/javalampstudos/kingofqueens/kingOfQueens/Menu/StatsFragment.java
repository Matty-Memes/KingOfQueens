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

    private int gamesPlayed, totalCardsPlayed, monstersPlayed, manaCardsPlayed, buffsPlayed,
            numberOfCardsDrawn, monstersDestroyed, numberOfWins, totalCoins,
            forfeit;

    private String totalCardsPlayedString, gamesString, destroyedString, cardsDrawnString,
            monsterString, manaString, buffString, winString, totalCoinsString, forfeitString;

    public StatsFragment() {
    }

    public void doSetup() {
        super.doSetup();

        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();

        // load in bitmaps here
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/StatsBack.PNG");
        backBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/ButtonBack.png");
        resetBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/reset.png");

        // Sets up Rect parameters
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));
        resetRect = new Rect(755, 50, 805, 100);

        monstersPlayed = MainActivity.setting.getInt("monstersPlayed");
        manaCardsPlayed =  MainActivity.setting.getInt("manaPlayed");
        buffsPlayed = MainActivity.setting.getInt("monstersPlayed");
        monstersDestroyed = MainActivity.setting.getInt("monstersDestroyed");
        numberOfCardsDrawn = MainActivity.setting.getInt("Cards Drawn");
        numberOfWins =  MainActivity.setting.getInt("numberOfWins");
        gamesPlayed = MainActivity.setting.getInt("gamesPlayed");
        totalCoins = MainActivity.setting.getInt("totalCoins");
        forfeit = MainActivity.setting.getInt("forfeit");
        totalCardsPlayed = monstersPlayed + manaCardsPlayed + buffsPlayed;

        totalCardsPlayedString = "Total Cards Played";
        gamesString = "Games Played";
        winString = "Number of Wins";
        monsterString = "Monster Cards Played";
        totalCoinsString = "Total Coins Collected";
        forfeitString = "Number of Forfeits";
        manaString = "Mana Cards Played";
        buffString = "Buff Cards Played";
        destroyedString = "Monsters Destroyed";
        cardsDrawnString = "Cards Drawn";

    }

    /**
     * Draws assets to the canvas
     */
    public void doDraw(Canvas canvas) {
        super.doDraw(canvas);

        // Setting the paint values and drawing the title to the canvas
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(18 * uiScaling);


        bgroundRect.set(0, 0, width, height);
        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);
        canvas.drawBitmap(resetBitmap, null, resetRect, null);

        canvas.drawText(gamesString, width / 2 -125 * uiScaling, height / 2
                - 106 * uiScaling, paint);
        canvas.drawText(gamesPlayed + "", width / 2 + 208 * uiScaling, height / 2
                - 106 * uiScaling, paint);
        canvas.drawText(totalCardsPlayedString, width / 2 -125 * uiScaling, height / 2
                - 86 * uiScaling, paint);
        canvas.drawText(totalCardsPlayed + "", width / 2 + 208 * uiScaling, height
                / 2 - 86 * uiScaling, paint);
        canvas.drawText(monsterString, width / 2 -125 * uiScaling, height / 2
                - 66 * uiScaling, paint);
        canvas.drawText (monstersPlayed + "", width / 2 + 208 * uiScaling,
                height / 2 - 66 * uiScaling, paint);
        canvas.drawText(manaString, width / 2 -125 * uiScaling, height / 2
                - 46 * uiScaling, paint);
        canvas.drawText(manaCardsPlayed + "", width / 2 + 208 * uiScaling,
                height / 2 - 46 * uiScaling, paint);
        canvas.drawText(buffString, width / 2 -125 * uiScaling, height / 2
                - 26 * uiScaling, paint);
        canvas.drawText(buffsPlayed + "", width / 2 + 208 * uiScaling,
                height / 2 - 26 * uiScaling, paint);
        canvas.drawText(destroyedString, width / 2 -125 * uiScaling, height / 2
                - 6 * uiScaling, paint);
        canvas.drawText(monstersDestroyed + "", width / 2 + 208 * uiScaling, height
                / 2 - 6 * uiScaling, paint);
//        canvas.drawText(cardsDrawnString, width / 2 -125 * uiScaling, height / 2
//                + 16 * uiScaling, paint);
//        canvas.drawText(numberOfCardsDrawn + "", width / 2 + 208 * uiScaling, height
//                / 2 + 16 * uiScaling, paint);
        canvas.drawText(totalCoinsString, width / 2 -125 * uiScaling, height / 2
                + 36 * uiScaling, paint);
        canvas.drawText(totalCoins + "", width / 2 + 208 * uiScaling, height
                / 2 + 36 * uiScaling, paint);
        canvas.drawText(winString, width / 2 -125 * uiScaling, height / 2
                + 56 * uiScaling, paint);
        canvas.drawText(numberOfWins + "", width / 2 + 208 * uiScaling, height
                / 2 + 56 * uiScaling, paint);
        canvas.drawText(forfeitString, width / 2 -125 * uiScaling, height / 2
                + 76 * uiScaling, paint);
        canvas.drawText(forfeit + "", width / 2 + 208 * uiScaling, height
                / 2 + 76 * uiScaling, paint);


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
