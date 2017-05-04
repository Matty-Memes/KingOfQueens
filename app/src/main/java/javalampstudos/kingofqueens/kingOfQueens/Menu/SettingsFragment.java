package javalampstudos.kingofqueens.kingOfQueens.Menu;

/**
 * Created by Andrew on 08/03/2017.
 */



import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.GameViewFragment;
import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;


public class SettingsFragment extends MenuFragment


{
    //Settings Bitmap
    private Bitmap bground, volumeBitmap, howToPlayBitmap, statsBitmap, backBitmap, aboutUsBitmap;
    //Settings Rects
    private Rect bgroundRect, volumeRect, howToPlayRect, statsRect, backRect, aboutUsRect;


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
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/Screen 1.png");
        volumeBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/volume.png");
        howToPlayBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/howtoplay.png");
        statsBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/newstats.png");
        backBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/ButtonBack.png");
        aboutUsBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/aboutus.png");

        // Set up values for each menu rect
        volumeRect =new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 - 58 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 - 10 * uiScaling));
        howToPlayRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 + 54 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 + 102 * uiScaling));
        statsRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 - 2 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 + 46 * uiScaling));
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));
        aboutUsRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 + 110 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 + 158 * uiScaling));
    }

    public void doDraw(Canvas canvas)

    {
        // omit super.doDraw

        bgroundRect.set(0, 0, width, height);

        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(volumeBitmap, null, volumeRect, null);
        canvas.drawBitmap(howToPlayBitmap, null, howToPlayRect, null);
        canvas.drawBitmap(statsBitmap, null, statsRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);
        canvas.drawBitmap(aboutUsBitmap, null, aboutUsRect, null);

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



            }
        }


    }
}
