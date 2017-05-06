package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;

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

    public Deck playerDeck = new Deck();



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
        cardSchoolFilterRect = new Rect();
        cardTypeFilterRect = new Rect();
        leftArrowRect = new Rect();
        rightArrowRect = new Rect();
        deckButtonRect = new Rect();
        returnToBuilderRect = new Rect();
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
        cardSchoolFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardSchoolFilter.png");
        cardTypeFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardTypeFilter.png");
        backBitmap = AssetLoader.loadBitmap(assetManager,"img/Marc/ButtonBack.png");
        leftArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowLeft.png");
        rightArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowRight.png");
        deckButtonBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ViewDeckButton.png");
        returnToBuilderBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/BackToBuilder.png");



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

                if (card1Rect.contains(x,y))
                {
                    //run add to deck method
                    //playerDeck.addToDeck();

                }

                if(cardSchoolFilterRect.contains(x,y))
                {

                }
                if(cardTypeFilterRect.contains(x,y))
                {

                }
                if(rightArrowRect.contains(x,y))
                {

                }
            }


        }

    }
}

