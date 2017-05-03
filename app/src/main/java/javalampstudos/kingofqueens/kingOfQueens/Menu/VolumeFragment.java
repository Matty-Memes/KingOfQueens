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
 * Created by User on 02/05/2017.
 */

public class VolumeFragment extends MenuFragment {

    //Settings Bitmap
    private Bitmap bground, plusBitmap, minusBitmap, backBitmap, musicVolBitmap, sfxVolBitmap;
    //Settings Rects
    private Rect bgroundRect, musicMinusRect, musicPlusRect, sfxMinusRect, sfxPlusRect, backRect,
    musicVolRect, sfxVolRect;


    private int musicVolume, sfxVolume;

    // Constructor
    public VolumeFragment()

    {


    }

    public void doSetup()

    {
        // call the doSetup method of the superclass
        super.doSetup();

        musicVolume = MainActivity.setting.getVolume("musicValue");
        sfxVolume = MainActivity.setting.getVolume("sfxValue");


        AssetManager assetManager = getActivity().getAssets();


        // set up rects here
        bgroundRect = new Rect();

        // load in bitmaps here
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/Screen 1.png");
        plusBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/plus.png");
        minusBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/minus.png");
        backBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/ButtonBack.png");
        musicVolBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/MusicVol.png");
        sfxVolBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/SFXVol.png");

        // Set up values for each menu rect
        // Sets up Rect parameters
        musicVolRect = new Rect((int) (width / 2 - 201 * uiScaling),
                (int) (height / 2 - 114 * uiScaling),
                (int) (width / 2 - 20 * uiScaling),
                (int) (height / 2 - 66 * uiScaling));
        sfxVolRect = new Rect((int) (width / 2 - 200 * uiScaling),
                (int) (height / 2 - 58 * uiScaling),
                (int) (width / 2 - 20 * uiScaling),
                (int) (height / 2 - 10 * uiScaling));
        musicMinusRect = new Rect((int) (width / 2 + 16 * uiScaling),
                (int) (height / 2 - 114 * uiScaling),
                (int) (width / 2 + 64 * uiScaling),
                (int) (height / 2 - 66 * uiScaling));
        musicPlusRect = new Rect((int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 - 114 * uiScaling),
                (int) (width / 2 + 176 * uiScaling),
                (int) (height / 2 - 66 * uiScaling));
        sfxMinusRect = new Rect((int) (width / 2 + 16 * uiScaling),
                (int) (height / 2 - 58 * uiScaling),
                (int) (width / 2 + 64 * uiScaling),
                (int) (height / 2 - 10 * uiScaling));
        sfxPlusRect = new Rect((int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 - 58 * uiScaling),
                (int) (width / 2 + 176 * uiScaling),
                (int) (height / 2 - 10 * uiScaling));
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));


    }

    public void doDraw(Canvas canvas)

    {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);
        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(musicVolBitmap, null, musicVolRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);
        canvas.drawBitmap(sfxVolBitmap, null, sfxVolRect, null);



        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(32 * uiScaling);
//        canvas.drawText(title, width / 2, 66 * uiScaling, paint);
//
//        paint.setTextAlign(Paint.Align.RIGHT);
//        paint.setTextSize(24 * uiScaling);
//
//        int var = -64;
//        for (String label : labels) {
//            canvas.drawText(label, width / 2 - 16 * uiScaling, height / 2 + var
//                    * uiScaling, paint);
//            var += 56;

//
//		/*
//		 * If the musicVolume is 0, the image is grayed out to indicate to the
//		 * user that the volume cannot go down any further
//		 */
        if (musicVolume == 0)
            paint.setAlpha(127);
        canvas.drawBitmap(minusBitmap, null, musicMinusRect, paint);
        paint.setAlpha(255);

        canvas.drawText(musicVolume + "", width / 2 + 96 * uiScaling, height
                / 2 - 64 * uiScaling, paint);
//		/*
//		 * If the musicVolume is 10, the image is grayed out to indicate to the
//	 * user that the volume cannot go up any further
//		 */
        if (musicVolume == 10)
            paint.setAlpha(127);
        canvas.drawBitmap(plusBitmap, null, musicPlusRect, paint);
        paint.setAlpha(255);
//
//		/*
//		 * If the sfxVolume is 0, the image is grayed out to indicate to the
//		 * user that the volume cannot go down any further
//		 */
        if (sfxVolume == 0)
            paint.setAlpha(127);
        canvas.drawBitmap(minusBitmap, null, sfxMinusRect, paint);
        paint.setAlpha(255);

        canvas.drawText(sfxVolume + "", width / 2 + 96 * uiScaling, height / 2
                - 8 * uiScaling, paint);

//		/*
//		 * If the sfxVolume is 10, the image is grayed out to indicate to the
//		 * user that the volume cannot go down any further
//		 */
        if (sfxVolume == 10)
            paint.setAlpha(127);
        canvas.drawBitmap(plusBitmap, null, sfxPlusRect, paint);
        paint.setAlpha(255);


        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if (backRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new MainMenuFragment(), "main_menu_fragment").commit();
                }

                // Decreases the value of music Volume
                if(musicMinusRect.contains(x, y)) {
                    if(musicVolume > 0) {
                        musicVolume = MainActivity.setting
                                .decreaseVolume("musicValue");
                    }

                    ((MainActivity) getActivity()).music();
                }
                // Increases the value of music Volume
                if(musicPlusRect.contains(x, y)) {
                    if(musicVolume < 10) {
                        musicVolume = MainActivity.setting
                                .increaseVolume("musicValue");
                    }

                    ((MainActivity) getActivity()).music();
                }

                // Decreases the value of sfx volume
                if(sfxMinusRect.contains(x, y)) {
                    if(sfxVolume > 0) {
                        sfxVolume = MainActivity.setting
                                .decreaseVolume("sfxValue");
                    }
                }

                // increases the value of sfx volume
                if(sfxPlusRect.contains(x, y)) {
                    if(sfxVolume < 10) {
                        sfxVolume = MainActivity.setting
                                .increaseVolume("sfxValue");
                    }
                }
            }
        }
    }
}







