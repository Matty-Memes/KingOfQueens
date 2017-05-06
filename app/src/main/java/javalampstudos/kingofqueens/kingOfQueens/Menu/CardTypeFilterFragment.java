package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;

/**
 * Created by Matt on 06/05/2017.
 */

public class CardTypeFilterFragment extends MainMenuFragment
{
    // DeckBuilder Bitmaps
    private Bitmap bgroundBitmap,cardSchoolFilterBitmap,cardTypeFilterBitmap,backBitmap,leftArrowBitmap,rightArrowBitmap,deckButtonBitmap,filterBoxBitmap,monsterFilterBitmap,manaFilterBitmap,supportFilterBitmap;
    // DeckBuilder Rects
    private Rect bgroundRect,backRect,filterBoxRect,monsterFilterRect,manaFilterRect,supportFilterRect;


    public Deck playerDeck = new Deck();



    public CardTypeFilterFragment()

    {


    }

    public void doSetup()


    {
        super.doSetup();

        // Loads in image assets
        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();
        filterBoxRect = new Rect();
        monsterFilterRect = new Rect();
        manaFilterRect = new Rect();
        supportFilterRect = new Rect();


        // Load bitmaps
        bgroundBitmap = AssetLoader.loadBitmap(assetManager,"img/DeckBuilderBackground.png");
        cardSchoolFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardSchoolFilter.png");
        cardTypeFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardTypeFilter.png");
        backBitmap = AssetLoader.loadBitmap(assetManager,"img/Marc/ButtonBack.png");
        leftArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowLeft.png");
        rightArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowRight.png");
        deckButtonBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ViewDeckButton.png");
        filterBoxBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/FilterBoxType.png");
        monsterFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/MonsterFilter.png");
        manaFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ManaFilter.png");
        supportFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/SupportFilter.png");




        // Set up values for each rect
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

        canvas.drawBitmap(bgroundBitmap,null,bgroundRect,null);
        canvas.drawBitmap(backBitmap, null, backRect, null);
        canvas.drawBitmap(filterBoxBitmap,null,filterBoxRect,null);
        canvas.drawBitmap(monsterFilterBitmap,null,monsterFilterRect,null);
        canvas.drawBitmap(manaFilterBitmap,null,manaFilterRect,null);
        canvas.drawBitmap(supportFilterBitmap,null,supportFilterRect,null);


        // Touch input for each menu rect

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if (monsterFilterRect.contains(x,y))
                {
                    //Filter by Monster Card
                }

                if(manaFilterRect.contains(x,y))
                {
                    //Filter by Mana Card
                }
                if(supportFilterRect.contains(x,y))
                {
                    //Filter by Support Card
                }
                if(backRect.contains(x,y))
                {
                    //return to deck builder without changing filter
                }
            }


        }

    }
}
