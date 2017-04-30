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
    private Bitmap bground, sfxBitmap, musicBitmap, statsBitmap, backBitmap;
    //Settings Rects
    private Rect bgroundRect, sfxRect, musicRect, statsRect, backRect;


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
        sfxBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/SFXVol.png");
        musicBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/MusicVol.png");
        statsBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/Stats.png");
        backBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/ButtonBack.png");

        // Set up values for each menu rect
        sfxRect = new Rect((int) (width - 256 * uiScaling - 8 * gameScaling),
                (int) (height / 2 - 56 * uiScaling),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 - 8 * uiScaling));
        musicRect = new Rect((int) (width - 256 * uiScaling - 8 * gameScaling),
                (int) (height / 2),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 + 48 * uiScaling));
        statsRect = new Rect((int) (width - 256 * uiScaling - 8 * gameScaling),
                (int) (height / 2 + 104 * uiScaling),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 + 148 * uiScaling));
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));
    }

    public void doDraw(Canvas canvas)

    {
        // omit super.doDraw

        bgroundRect.set(0, 0, width, height);

        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(sfxBitmap, null, sfxRect, null);
        canvas.drawBitmap(musicBitmap, null, musicRect, null);
        canvas.drawBitmap(statsBitmap, null, statsRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if(backRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new MainMenuFragment(), "main_menu_fragment").commit();
                }



            }
        }


    }
}
