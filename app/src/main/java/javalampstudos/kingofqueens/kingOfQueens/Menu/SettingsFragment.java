package javalampstudos.kingofqueens.kingOfQueens.Menu;

/**
 * Created by Andrew on 08/03/2017.
 */



import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.GameViewFragment;
import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;


public class SettingsFragment extends MenuFragment


{
    //Settings Bitmap
    private Bitmap bground, backBitmap;
    //Settings Rects
    private Rect bgroundRect, volumeRect, howToPlayRect, statsRect, backRect, aboutUsRect,
    deckBuilderRect;

    private String volumeString, statsString, howToPlayString, aboutUsString, deckBuilderString;

    // Constructor
    public SettingsFragment()

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
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/SettingsBack.PNG");
        backBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/ButtonBack.png");

        volumeString = "Volume";
        statsString = "Stats";
        howToPlayString = "How To Play";
        aboutUsString = "About Us";
        deckBuilderString = "Deck Builder";

        // Set up values for each menu rect
        volumeRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 - 80 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 - 50 * uiScaling));
        deckBuilderRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 - 29 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 + 20 * uiScaling));
        statsRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 + 27 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 + 80 * uiScaling));
        howToPlayRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 + 83 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 + 140 * uiScaling));
        aboutUsRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 + 139 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 + 200 * uiScaling));
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));
    }

    public void doDraw(Canvas canvas)

    {
        // omit super.doDraw

        bgroundRect.set(0, 0, width, height);

        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(32 * uiScaling);
        canvas.drawText(volumeString, width / 2, height / 2 - 80
                * uiScaling, paint);
        canvas.drawText(deckBuilderString, width / 2, height / 2 - 20
                * uiScaling, paint);
        canvas.drawText(statsString, width / 2, height / 2 + 40
                * uiScaling, paint);
        canvas.drawText(howToPlayString, width / 2, height / 2 + 100
                * uiScaling, paint);
        canvas.drawText(aboutUsString, width / 2, height / 2 + 160
            * uiScaling, paint);


        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if(backRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new MainMenuFragment(),
                                    "main_menu_fragment").commit();
                }

                if(volumeRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new VolumeFragment(),
                                    "volume_fragment").commit();
                }

                if(statsRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new StatsFragment(),
                                    "stats_fragment").commit();
                }

                if(howToPlayRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new HowToPlayFragment(),
                                    "how_to_play_fragment").commit();
                }

                if(aboutUsRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new AboutUsFragment(),
                                    "about_us_fragment").commit();
                }

                if(deckBuilderRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new DeckBuilderWorkingFragment(),
                                    "deck_builder_working_fragment").commit();
                }



            }
        }


    }
}
