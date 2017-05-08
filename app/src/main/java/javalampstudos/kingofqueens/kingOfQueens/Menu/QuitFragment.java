package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.SoundPool;

import javalampstudos.kingofqueens.MainActivity;
import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

/**
 * Created by Marc on 30/04/2017.
 */

public class QuitFragment extends MenuFragment{

        // Quit Bitmaps
        private Bitmap bground, areYouSureBitmap, yesBitmap, noBitmap;
        // Pause Menu Rects
        private Rect bgroundRect, areYouSureRect, yesRect, noRect;

    private SoundPool yeahSFX;
    private float sfxVolume;


        public QuitFragment()

        {


        }

        public void doSetup()


        {
            super.doSetup();

            // Loads in image assets related to the currently selected language
            AssetManager assetManager = getActivity().getAssets();

            yeahSFX = AssetLoader.loadSoundpool(assetManager, "yeah.mp3");

            // set up rects here
            bgroundRect = new Rect();


            // Load bitmaps for menu buttons
            bground = AssetLoader.loadBitmap(assetManager, "img/Marc/KOFQMenu2.png");
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
                        System.exit(0);
                    }

                    if(noRect.contains(x, y)) {
                        sfxVolume = MainActivity.setting.getVolume("sfxValue") / 10.0f;
                        yeahSFX.play(1, sfxVolume, sfxVolume, 1, 0, 1.0f);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new MainMenuFragment(),
                                        "main_menu_fragment").commit();
                    }

                }
            }

        }
    }


