package javalampstudos.kingofqueens.kingOfQueens.Menu;

/**
 * Created by Andrew on 05/03/2017. Edited by Marc
 */

// Local Imports
import javalampstudos.kingofqueens.GameViewFragment;
import javalampstudos.kingofqueens.MainActivity;
import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

// Android Imports
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.SoundPool;

// Java Imports

// Based on Blasto by mtstudios
public class MainMenuFragment extends MenuFragment

{
    // Main Menu Bitmaps
    private Bitmap newBitmap, continueBitmap, settingsBitmap, quitBitmap;
    // Main Menu Rects
    private Rect newRect, continueRect, settingsRect, quitRect;

    private SoundPool noSFX;
    private float sfxVolume;


    public MainMenuFragment ()

    {


    }

    public void doSetup ()


    {
      super.doSetup();

        // Loads in image assets related to the currently selected languge
        AssetManager assetManager = getActivity().getAssets();

        noSFX = AssetLoader.loadSoundpool(assetManager, "no.mp3");

      // Load bitmaps for menu buttons
      newBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/new.png");
      continueBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/continue.png");
      settingsBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/SettingsButton.png");
        quitBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/quit.png");

      // Set up values for each menu rect
        newRect = new Rect((int) (85),
                (int) (410),
                (int) (285),
                (int) (510));
        continueRect = new Rect((int) (300),
                (int) (410),
                (int) (500),
                (int) (510));
        settingsRect = new Rect ( 515, 410, 715, 510 );
        quitRect = new Rect((int) (730),
                (int) (410),
                (int) (930),
                (int) (510));
       // Add more rects as required
    }

    public void doDraw (Canvas canvas)

    {
        super.doDraw(canvas);

        // draw each bitmap to the screen
        canvas.drawBitmap(newBitmap, null, newRect, null);
        canvas.drawBitmap(continueBitmap, null, continueRect, null);
        canvas.drawBitmap(settingsBitmap, null, settingsRect, null);
        canvas.drawBitmap(quitBitmap, null, quitRect, null);

        // Touch input for each menu rect

        for(int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if(input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                //
                if(newRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new GameViewFragment(), "game_fragment").commit();
                }


                if(continueRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new PauseFragment(),
                                    "pause_fragment").commit();
                }



                if(settingsRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new SettingsFragment(),
                                    "settings_fragment").commit();
                }

                if(quitRect.contains(x, y)) {
                    sfxVolume = MainActivity.setting.getVolume("sfxValue") / 10.0f;
                    noSFX.play(1, sfxVolume, sfxVolume, 1, 0, 1.0f);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new QuitFragment(),
                                    "quit_fragment").commit();
                }

            }
        }


    }


}
