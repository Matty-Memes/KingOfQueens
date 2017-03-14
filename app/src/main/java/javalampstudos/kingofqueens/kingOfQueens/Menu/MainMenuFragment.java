package javalampstudos.kingofqueens.kingOfQueens.Menu;

/**
 * Created by Andrew on 05/03/2017.
 */

// Local Imports
import javalampstudos.kingofqueens.GameViewFragment;
import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

// Android Imports
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;

// Java Imports


public class MainMenuFragment extends MenuFragment

{
    // Main Menu Bitmaps
    private Bitmap startBitmap, loadBitmap, settingsBitmap, quitBitmap;
    // Main Menu Rects
    private Rect startRect, loadRect, settingsRect, quitRect;


    public MainMenuFragment ()

    {


    }

    public void doSetup ()


    {
      super.doSetup();

        // Loads in image assets related to the currently selected languge
        AssetManager assetManager = getActivity().getAssets();

        // Sets up paint values
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(16 * gameScaling);

      // Load bitmaps for menu buttons
      startBitmap = AssetLoader.loadBitmap(assetManager, "img/TempButtons/play.png");
      loadBitmap = AssetLoader.loadBitmap(assetManager, "img/TempButtons/continue.png");
      settingsBitmap = AssetLoader.loadBitmap(assetManager, "img/TempButtons/settings.png");

      // Set up values for each menu rect
        startRect = new Rect((int) (width - 256 * uiScaling - 8 * gameScaling),
                (int) (height / 2 - 56 * uiScaling),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 - 8 * uiScaling));
        loadRect = new Rect((int) (width - 256 * uiScaling - 8 * gameScaling),
                (int) (height / 2), (int) (width - 8 * gameScaling),
                (int) (height / 2 + 48 * uiScaling));
        settingsRect = new Rect(
                (int) (width - 256 * uiScaling - 8 * gameScaling),
                (int) (height / 2 + 56 * uiScaling),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 + 104 * uiScaling));
       // Add more rects as required
    }

    public void doDraw (Canvas canvas)

    {
        super.doDraw(canvas);

        // experiment with drawing text
        canvas.drawText("King of Queens", 200, 100, paint);


        // draw each bitmap to the screen
        canvas.drawBitmap(startBitmap, null, startRect, null);
        canvas.drawBitmap(loadBitmap, null, loadRect, null);
        canvas.drawBitmap(settingsBitmap, null, settingsRect, null);
        // canvas.drawBitmap(quitBitmap, null, quitRect, null);

        // Touch input for each menu rect

        for(int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if(input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                //
                if(startRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new GameViewFragment(), "game_fragment").commit();
                }




                if(loadRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new SettingsFragment(),
                                    "settings_fragment").commit();
                }

                /*

                // The Settings Button loads the SettingsFragment
                if(settingsRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new SettingsFragment(),
                                    "settings_fragment").commit();
                }
                // The How To Play Button loads the HowToPlayFragment
                if(quitRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new HowToPlayFragment(),
                                    "how_to_play_fragment").commit();
                }

                */
            }
        }


    }


}
