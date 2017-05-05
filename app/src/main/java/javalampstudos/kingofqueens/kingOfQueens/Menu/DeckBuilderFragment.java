package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

/**
 * Created by Matt on 05/05/2017.
 */

public class DeckBuilderFragment extends MenuFragment
{
    // DeckBuilder Bitmaps
    private Bitmap bgroundBitmap,cardSchoolFilterBitmap,cardTypeFilterBitmap,backBitmap,leftArrowBitmap,rightArrowBitmap,deckButtonBitmap,returnToBuilderBitmap;
    // DeckBuilder Rects
    private Rect bgroundRect, cardSchoolFilterRect,cardTypeFilterRect,backRect,leftArrowRect,rightArrowRect,deckButtonRect,returnToBuilderRect;
    //rects for storing cards
    private Rect card1Rect,card2Rect,card3Rect,card4Rect,card5Rect,card6Rect,card7Rect,card8Rect;



    public DeckBuilderFragment()

    {


    }

    public void doSetup()


    {
        super.doSetup();

        // Loads in image assets related to the currently selected language
        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();


        // Load bitmaps for menu buttons


        // Set up values for each deckBuilder rect
        backRect = new Rect((int) (width - 96 * uiScaling - 3 * gameScaling),
                (int) (height / 2 + 56 * uiScaling),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 + 98 * uiScaling));
    }

    public void doDraw(Canvas canvas)

    {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);

        // draw each bitmap to the screen

        canvas.drawBitmap(backBitmap, null, backRect, null);

        // Touch input for each menu rect

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);
            }
        }

    }
}

