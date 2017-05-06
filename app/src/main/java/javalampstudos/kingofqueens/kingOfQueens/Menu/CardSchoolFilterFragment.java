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

public class CardSchoolFilterFragment extends MainMenuFragment
{
    // DeckBuilder Bitmaps
    private Bitmap bgroundBitmap,cardSchoolFilterBitmap,cardTypeFilterBitmap,backBitmap,leftArrowBitmap,rightArrowBitmap,deckButtonBitmap,filterBoxBitmap,engineerFilterBitmap,medicFilterBitmap,EEECSFitlerBitmap,artsFilterBitmap,builtEnviFilterBitmap;
    // DeckBuilder Rects
    private Rect bgroundRect,backRect,filterBoxRect,engineerFilterRect,medicFilterRect,EEECSFilterRect,artsFilterRect,builtEnviFilterRect;


    public Deck playerDeck = new Deck();



    public CardSchoolFilterFragment()

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
        engineerFilterRect = new Rect();
        medicFilterRect = new Rect();
        EEECSFilterRect = new Rect();
        artsFilterRect = new Rect();
        builtEnviFilterRect = new Rect();


        // Load bitmaps
        bgroundBitmap = AssetLoader.loadBitmap(assetManager,"img/DeckBuilderBackground.png");
        cardSchoolFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardSchoolFilter.png");
        cardTypeFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardTypeFilter.png");
        backBitmap = AssetLoader.loadBitmap(assetManager,"img/Marc/ButtonBack.png");
        leftArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowLeft.png");
        rightArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowRight.png");
        deckButtonBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ViewDeckButton.png");
        filterBoxBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/FilterBoxSchool.png");
        engineerFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/EngineeringLogo.png");
        medicFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/MedicalLogo.png");
        EEECSFitlerBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/EEECSLogo.png");
        artsFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArtsLogo.png");
        builtEnviFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/BuiltEnvriLogo.png");




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
        canvas.drawBitmap(engineerFilterBitmap,null,engineerFilterRect,null);
        canvas.drawBitmap(medicFilterBitmap,null,medicFilterRect,null);
        canvas.drawBitmap(EEECSFitlerBitmap,null,EEECSFilterRect,null);
        canvas.drawBitmap(artsFilterBitmap,null,artsFilterRect,null);
        canvas.drawBitmap(builtEnviFilterBitmap,null,builtEnviFilterRect,null);


        // Touch input for each menu rect

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if(engineerFilterRect.contains(x,y))
                {
                    //filter by Engineering cards
                }
                if(medicFilterRect.contains(x,y))
                {
                    //filter by medical cards
                }
                if(EEECSFilterRect.contains(x,y))
                {
                    //filter by EEECS cards
                }
                if(artsFilterRect.contains(x,y))
                {
                    //filter by arts cards
                }
                if(builtEnviFilterRect.contains(x,y))
                {
                    //filter by built environment cards
                }

                if(backRect.contains(x,y))
                {
                    //return to deck builder without changing filter
                }
            }


        }

    }
}
