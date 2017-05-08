package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

/**
 * Created by Marc on 04/05/2017.
 */

public class HowToPlayFragment extends MenuFragment {

    //HowToPlay Bitmap
    private Bitmap bground, backBitmap;

    //HowToPlay Rect
    private Rect bgroundRect, backRect;

    //HowToPlay Strings
    private String line1String, line2String, line3String, line4String, line5String, line6String,
    line7String, line8String, line9String, line10String, line11String, line12String, line13String,
            line14String;

    public HowToPlayFragment() {

    }

    public void doSetup() {
        super.doSetup();


        AssetManager assetManager = getActivity().getAssets();

        // Sets up background rect
        bgroundRect = new Rect();

        // load in bitmaps here
        bground = AssetLoader.loadBitmap(assetManager, "img/Marc/HowToPlayBack.PNG");
        backBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/ButtonBack.png");

        //sets up strings
        line1String = "King Of Queens is an open world and turn-based card game based on Queens";
        line2String = "University Belfast. The open world allows you to visit Queens University";
        line3String = "in game. You can interact with NPCs to challenge them to a card game or play";
        line4String = " the mini-game which involves searching the open world to find coins! The aim ";
        line5String = "of the card game is to defeat your opponent by diminishing their 3 lives,";
        line6String = "before they diminish yours. This is done by monster cards. Monster cards must ";
        line7String = "destroy other monster cards on the other side of the field by attacking them,";
        line8String = "monster cards on the other side of the field by attacking them, to remove their";
        line9String = "life points. Thus removing one life from the player.However, for monsters to attack,";
        line10String = "they require mana cards to be played. Different monsters will require different";
        line11String = " amounts of mana for them to be able to attack. Also, there are different";
        line12String = "types of monsters that require their correlating mana cards to be played";
        line13String = "so they can attack. There are 6 schools of monster which are";
        line14String = "Arts, Building Environment, EEECS, Engineering, Medical and Social Sciences.";

        // Sets up Rect parameters
        backRect = new Rect((int) (8 * uiScaling), (int) (8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling),
                (int) (24 * 2 * uiScaling + 8 * uiScaling));

    }


    public void doDraw(Canvas canvas) {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);
        canvas.drawBitmap(bground, null, bgroundRect, null);
        canvas.drawBitmap(backBitmap, null, backRect, null);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(14 * uiScaling);
        canvas.drawText(line1String, width / 2, height / 2 - 90 * uiScaling, paint);
        canvas.drawText(line2String, width / 2, height / 2 - 75 * uiScaling, paint);
        canvas.drawText(line3String, width / 2, height / 2 - 60 * uiScaling, paint);
        canvas.drawText(line4String, width / 2, height / 2 - 45 * uiScaling, paint);
        canvas.drawText(line5String, width / 2, height / 2 - 30 * uiScaling, paint);
        canvas.drawText(line6String, width / 2, height / 2 - 15 * uiScaling, paint);
        canvas.drawText(line7String, width / 2, height / 2 + 0 * uiScaling, paint);
        canvas.drawText(line8String, width / 2, height / 2 + 15 * uiScaling, paint);
        canvas.drawText(line9String, width / 2, height / 2 + 30 * uiScaling, paint);
        canvas.drawText(line10String, width / 2, height / 2 + 45 * uiScaling, paint);
        canvas.drawText(line11String, width / 2, height / 2 + 60 * uiScaling, paint);
        canvas.drawText(line12String, width / 2, height / 2 + 75 * uiScaling, paint);
        canvas.drawText(line13String, width / 2, height / 2 + 90 * uiScaling, paint);
        canvas.drawText(line14String, width / 2, height / 2 + 105 * uiScaling, paint);

        //touch input
        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if (backRect.contains(x, y)) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new SettingsFragment(), "settings_fragment").commit();
                }

            }
        }
    }
}