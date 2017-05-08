package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;

/**
 * Created by Matt on 07/05/2017.
 */

public class DeckViewFragment extends MenuFragment
{
    // DeckBuilder Bitmaps
    private Bitmap bgroundBitmap,backBitmap,leftArrowBitmap,rightArrowBitmap,backToBuilderBitmap,cardbackBitmap;
    // DeckBuilder Rects
    private Rect bgroundRect,backRect,leftArrowRect,rightArrowRect,backToBuilderRect;
    //rects for storing cards
    private Rect card1Rect,card2Rect,card3Rect,card4Rect,card5Rect,card6Rect,card7Rect,card8Rect;

    int page = 1;

   //Take in a player deck somehow



    public DeckViewFragment()

    {


    }

    public void doSetup()


    {
        super.doSetup();

        // Loads in image assets
        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();
        leftArrowRect = new Rect();
        rightArrowRect = new Rect();
        backToBuilderRect = new Rect();
        card1Rect = new Rect();
        card2Rect = new Rect();
        card3Rect = new Rect();
        card4Rect = new Rect();
        card5Rect = new Rect();
        card6Rect = new Rect();
        card7Rect = new Rect();
        card8Rect = new Rect();


        // Load bitmaps
        bgroundBitmap = AssetLoader.loadBitmap(assetManager,"img/DeckBuilderBackground.png");
        backBitmap = AssetLoader.loadBitmap(assetManager,"img/Marc/ButtonBack.png");
        leftArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowLeft.png");
        rightArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowRight.png");
        backToBuilderBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/BackToBuilder.png");
        cardbackBitmap = AssetLoader.loadBitmap(assetManager,"img/Cards/Cardback.png");

        // Set up values for each rect
        backRect = new Rect((4),(24), (96), (96));
        leftArrowRect = new Rect(0,201,96,279);
        rightArrowRect = new Rect(703,201,800,279);
        backToBuilderRect = new Rect(688,400,792,472);
        //Cardslot Rects
        card1Rect = new Rect(125,108,247,276);
        card2Rect = new Rect(269,108,391,276);
        card3Rect = new Rect(408,108,530,276);
        card4Rect = new Rect(552,108,674,276);
        card5Rect = new Rect(125,288,391,456);
        card6Rect = new Rect(269,288,391,456);
        card7Rect = new Rect(408,288,552,456);
        card8Rect = new Rect(552,288,391,674);
    }

    public void doDraw(Canvas canvas)

    {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);

        // draw each bitmap to the screen

        canvas.drawBitmap(bgroundBitmap,null,bgroundRect,null);
        canvas.drawBitmap(backBitmap,null,backRect,null);
        canvas.drawBitmap(backToBuilderBitmap,null,backToBuilderRect,null);
        canvas.drawBitmap(cardbackBitmap, null, card1Rect, null);
        canvas.drawBitmap(cardbackBitmap, null, card2Rect, null);
        canvas.drawBitmap(cardbackBitmap, null, card3Rect, null);
        canvas.drawBitmap(cardbackBitmap, null, card4Rect, null);
        canvas.drawBitmap(cardbackBitmap, null, card5Rect, null);
        canvas.drawBitmap(cardbackBitmap, null, card6Rect, null);
        canvas.drawBitmap(cardbackBitmap, null, card7Rect, null);
        canvas.drawBitmap(cardbackBitmap, null, card8Rect, null);


        // Touch input for each menu rect

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if(backToBuilderRect.contains(x,y))
                {
                    //return to the deckbuilder fragment
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new DeckBuilderWorkingFragment(),
                                    "deck_builder_working_fragment").commit();
                }


            }

        }

    }
}
