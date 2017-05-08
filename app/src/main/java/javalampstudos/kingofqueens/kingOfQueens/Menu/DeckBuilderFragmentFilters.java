package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.FilterType;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.JSONcardLibrary;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;

/**
 * Created by Matt on 05/05/2017.
 * //07/05/2017  messy implementation trying to draw all deckbuilder items in the same class.
 */

public class DeckBuilderFragmentFilters extends MenuFragment
{
    // DeckBuilder Bitmaps
    private Bitmap bgroundBitmap,cardSchoolFilterBitmap,cardTypeFilterBitmap,resetFilterBitmap,backBitmap,leftArrowBitmap,rightArrowBitmap,deckButtonBitmap,returnToBuilderBitmap,cardbackBitmap;
    // Bitmaps for the CardType Filter
    private Bitmap typeFilterBitmap,monsterFilterBitmap,manaFilterBitmap,supportFilterBitmap;
    //Bitmaps for the CardSchool Filter
    private Bitmap schoolFilterBitmap,engineerFilterBitmap,medicFilterBitmap,EEECSFitlerBitmap,artsFilterBitmap,builtEnviFilterBitmap;
    // DeckBuilder Rects
    private Rect bgroundRect, cardSchoolFilterRect,cardTypeFilterRect,resetFilterRect,backRect,leftArrowRect,rightArrowRect,deckButtonRect,returnToBuilderRect;
    //rects for storing cards
    private Rect card1Rect,card2Rect,card3Rect,card4Rect,card5Rect,card6Rect,card7Rect,card8Rect;
    //Rects for the CardType Filter
    private Rect typeFilterRect,monsterFilterRect,manaFilterRect,supportFilterRect;
    //Rects for the CardSchool Filter
    private Rect schoolFilterRect,engineerFilterRect,medicFilterRect,EEECSFilterRect,artsFilterRect,builtEnviFilterRect;

    public Deck playerDeck = new Deck();

    int page = 1; //Starts the view on page 1
    int totalNumberOfPages = 8; //total number of pages, remains the same
    int currentNumberOfPages = 8;//can be manipulated by the filters. Used for showing page numbers
    private FilterType filter = FilterType.none;
    //Arrays containing the id for each card needed on the page. more for reference
//    private int[] page1 = {1,2,3,4,5,6,7,8}; //Engineer cards
//    private int[] page2 = {9,10,11,12,13,14,15,16}; //Medical cards
//    private int[] page3 = {17,18,19,20,21,22,23,24};//EEECS cards
//    private int[] page4 = {25,26,27,28,29,30,31,32};//Arts cards
//    private int[] page5 = {33,34,35,36,37,38,39}; //Built envi cards
//    private int[] page6 = {40,41,42,43,45}; //Mana cards //44 is SocSci which isn't being included
//    private int[] page7 = {46,47,48,49,50,51,52,53};//Support1
//    private int[] page8 = {54,55,56,57,58,59}; //support2
    private int[] card1 = {1,9,17,25,33,40,46,54};
    private int[] card2 = {2,10,18,26,34,41,47,55};
    private int[] card3 = {3,11,19,27,35,42,48,56};
    private int[] card4 = {4,12,20,28,36,43,49,57};
    private int[] card5 = {5,13,21,29,37,45,50,58};
    private int[] card6 = {6,14,22,30,38,-1,51,59};//use-1 for cardback
    private int[] card7 = {7,15,23,31,39,-1,52,-1};
    private int[] card8 = {8,16,24,32,40,-1,53,-1};

    //used to print card bitmaps
    public JSONcardLibrary library;


    public DeckBuilderFragmentFilters()

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
        resetFilterRect = new Rect();
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
        typeFilterRect = new Rect();
        monsterFilterRect = new Rect();
        manaFilterRect = new Rect();
        supportFilterRect = new Rect();
        schoolFilterRect = new Rect();
        engineerFilterRect = new Rect();
        medicFilterRect = new Rect();
        EEECSFilterRect = new Rect();
        artsFilterRect = new Rect();
        builtEnviFilterRect = new Rect();

        // Load bitmaps
        bgroundBitmap = AssetLoader.loadBitmap(assetManager,"img/DeckBuilderBackground.png");
        cardSchoolFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardSchoolFilter.png");
        cardTypeFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardTypeFilter.png");
        resetFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ResetFilter.png");
        backBitmap = AssetLoader.loadBitmap(assetManager,"img/Marc/ButtonBack.png");
        leftArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowLeft.png");
        rightArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowRight.png");
        deckButtonBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ViewDeckButton.png");
        cardbackBitmap = AssetLoader.loadBitmap(assetManager,"img/Cards/Cardback.png");
        typeFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/FilterBoxType.png");
        monsterFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/MonsterFilter.png");
        manaFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ManaFilter.png");
        supportFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/SupportFilter.png");
        schoolFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/FilterBoxSchool.png");
        engineerFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/EngineeringLogo.png");
        medicFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/MedicalLogo.png");
        EEECSFitlerBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/EEECSLogo.png");
        artsFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArtsLogo.png");
        builtEnviFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/BuiltEnvriLogo.png");


        // Set up values for each deckBuilder rect
        backRect = new Rect((int) (width - 96 * uiScaling - 3 * gameScaling),
                (int) (height / 2 + 56 * uiScaling),
                (int) (width - 8 * gameScaling),
                (int) (height / 2 + 98 * uiScaling));
    }

    //used for displaying Filter Menus
    boolean typeFilterOn=false;
    boolean schoolFilterOn = false;

    public void doDraw(Canvas canvas)

    {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);

        // draw each bitmap to the screen

        canvas.drawBitmap(bgroundBitmap,null,bgroundRect,null);
        canvas.drawBitmap(backBitmap, null, backRect, null);
        canvas.drawBitmap(cardSchoolFilterBitmap,null,cardSchoolFilterRect,null);
        canvas.drawBitmap(cardTypeFilterBitmap,null,cardTypeFilterRect,null);
        canvas.drawBitmap(resetFilterBitmap,null,cardTypeFilterRect,null);
        canvas.drawBitmap(backBitmap,null,backRect,null);
        canvas.drawBitmap(deckButtonBitmap,null,deckButtonRect,null);

        //Draw cards for each page

        //Matt08/05/2017: If we were able to pull images from the library class, this is how I would've liked to do the implementation
        switch(page)
        {
            case 1:
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(library.monsterCards.get(0).getBitmap(), null, card1Rect, null);
                canvas.drawBitmap(library.monsterCards.get(1).getBitmap(), null, card2Rect, null);
                canvas.drawBitmap(library.monsterCards.get(2).getBitmap(), null, card3Rect, null);
                canvas.drawBitmap(library.monsterCards.get(3).getBitmap(), null, card4Rect, null);
                canvas.drawBitmap(library.monsterCards.get(4).getBitmap(), null, card5Rect, null);
                canvas.drawBitmap(library.monsterCards.get(5).getBitmap(), null, card6Rect, null);
                canvas.drawBitmap(library.monsterCards.get(6).getBitmap(), null, card7Rect, null);
                canvas.drawBitmap(library.monsterCards.get(7).getBitmap(), null, card8Rect, null);
                break;
            case 2:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(library.monsterCards.get(8).getBitmap(), null, card1Rect, null);
                canvas.drawBitmap(library.monsterCards.get(9).getBitmap(), null, card2Rect, null);
                canvas.drawBitmap(library.monsterCards.get(10).getBitmap(), null, card3Rect, null);
                canvas.drawBitmap(library.monsterCards.get(11).getBitmap(), null, card4Rect, null);
                canvas.drawBitmap(library.monsterCards.get(12).getBitmap(), null, card5Rect, null);
                canvas.drawBitmap(library.monsterCards.get(13).getBitmap(), null, card6Rect, null);
                canvas.drawBitmap(library.monsterCards.get(14).getBitmap(), null, card7Rect, null);
                canvas.drawBitmap(library.monsterCards.get(15).getBitmap(), null, card8Rect, null);
                break;
            case 3:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(library.monsterCards.get(16).getBitmap(), null, card1Rect, null);
                canvas.drawBitmap(library.monsterCards.get(17).getBitmap(), null, card2Rect, null);
                canvas.drawBitmap(library.monsterCards.get(18).getBitmap(), null, card3Rect, null);
                canvas.drawBitmap(library.monsterCards.get(19).getBitmap(), null, card4Rect, null);
                canvas.drawBitmap(library.monsterCards.get(20).getBitmap(), null, card5Rect, null);
                canvas.drawBitmap(library.monsterCards.get(21).getBitmap(), null, card6Rect, null);
                canvas.drawBitmap(library.monsterCards.get(22).getBitmap(), null, card7Rect, null);
                canvas.drawBitmap(library.monsterCards.get(23).getBitmap(), null, card8Rect, null);
                break;
            case 4:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(library.monsterCards.get(24).getBitmap(), null, card1Rect, null);
                canvas.drawBitmap(library.monsterCards.get(25).getBitmap(), null, card2Rect, null);
                canvas.drawBitmap(library.monsterCards.get(26).getBitmap(), null, card3Rect, null);
                canvas.drawBitmap(library.monsterCards.get(27).getBitmap(), null, card4Rect, null);
                canvas.drawBitmap(library.monsterCards.get(28).getBitmap(), null, card5Rect, null);
                canvas.drawBitmap(library.monsterCards.get(29).getBitmap(), null, card6Rect, null);
                canvas.drawBitmap(library.monsterCards.get(30).getBitmap(), null, card7Rect, null);
                canvas.drawBitmap(library.monsterCards.get(31).getBitmap(), null, card8Rect, null);
                break;
            case 5:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                if(filter!=FilterType.monster)
                {
                    canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                }
                canvas.drawBitmap(library.monsterCards.get(31).getBitmap(), null, card1Rect, null);
                canvas.drawBitmap(library.monsterCards.get(32).getBitmap(), null, card2Rect, null);
                canvas.drawBitmap(library.monsterCards.get(33).getBitmap(), null, card3Rect, null);
                canvas.drawBitmap(library.monsterCards.get(34).getBitmap(), null, card4Rect, null);
                canvas.drawBitmap(library.monsterCards.get(35).getBitmap(), null, card5Rect, null);
                canvas.drawBitmap(library.monsterCards.get(36).getBitmap(), null, card6Rect, null);
                canvas.drawBitmap(library.monsterCards.get(37).getBitmap(), null, card7Rect, null);
                canvas.drawBitmap(library.monsterCards.get(38).getBitmap(), null, card8Rect, null);
                break;
            case 6:
                if(filter!=FilterType.mana)
                {
                    canvas.drawBitmap(leftArrowBitmap, null, leftArrowRect, null);
                    canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                }
                canvas.drawBitmap(library.manaCards.get(0).getBitmap(), null, card1Rect, null);
                canvas.drawBitmap(library.manaCards.get(1).getBitmap(), null, card2Rect, null);
                canvas.drawBitmap(library.manaCards.get(2).getBitmap(), null, card3Rect, null);
                canvas.drawBitmap(library.manaCards.get(3).getBitmap(), null, card4Rect, null);
                canvas.drawBitmap(library.manaCards.get(5).getBitmap(), null, card5Rect, null);
                canvas.drawBitmap(cardbackBitmap, null, card6Rect, null);
                canvas.drawBitmap(cardbackBitmap, null, card7Rect, null);
                canvas.drawBitmap(cardbackBitmap, null, card8Rect, null);
                break;
            case 7:
                if(filter!=FilterType.support)
                {
                    canvas.drawBitmap(leftArrowBitmap, null, leftArrowRect, null);
                }
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(library.supportCards.get(0).getBitmap(),null,card1Rect,null);
                canvas.drawBitmap(library.supportCards.get(1).getBitmap(),null,card2Rect,null);
                canvas.drawBitmap(library.supportCards.get(2).getBitmap(),null,card3Rect,null);
                canvas.drawBitmap(library.supportCards.get(3).getBitmap(),null,card4Rect,null);
                canvas.drawBitmap(library.supportCards.get(4).getBitmap(),null,card5Rect,null);
                canvas.drawBitmap(library.supportCards.get(5).getBitmap(),null,card6Rect,null);
                canvas.drawBitmap(library.supportCards.get(6).getBitmap(),null,card7Rect,null);
                canvas.drawBitmap(library.supportCards.get(7).getBitmap(),null,card8Rect,null);
                break;
            case 8:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                canvas.drawBitmap(library.supportCards.get(8).getBitmap(),null,card1Rect,null);
                canvas.drawBitmap(library.supportCards.get(9).getBitmap(),null,card2Rect,null);
                canvas.drawBitmap(library.supportCards.get(10).getBitmap(),null,card3Rect,null);
                canvas.drawBitmap(library.supportCards.get(11).getBitmap(),null,card4Rect,null);
                canvas.drawBitmap(library.supportCards.get(12).getBitmap(),null,card5Rect,null);
                canvas.drawBitmap(library.supportCards.get(13).getBitmap(),null,card6Rect,null);
                canvas.drawBitmap(cardbackBitmap,null,card8Rect,null);
                canvas.drawBitmap(cardbackBitmap,null,card8Rect,null);
                break;
        }

        //If a Filter button has been pressed, draw the Filter assets
        if(typeFilterOn)
        {
            canvas.drawBitmap(typeFilterBitmap,null,typeFilterRect,null);
            canvas.drawBitmap(monsterFilterBitmap,null,monsterFilterRect,null);
            canvas.drawBitmap(manaFilterBitmap,null,manaFilterRect,null);
            canvas.drawBitmap(supportFilterBitmap,null,supportFilterRect,null);
        }
        if(schoolFilterOn)
        {
            canvas.drawBitmap(schoolFilterBitmap,null,schoolFilterRect,null);
            canvas.drawBitmap(engineerFilterBitmap,null,engineerFilterRect,null);
            canvas.drawBitmap(medicFilterBitmap,null,medicFilterRect,null);
            canvas.drawBitmap(EEECSFitlerBitmap,null,EEECSFilterRect,null);
            canvas.drawBitmap(artsFilterBitmap,null,artsFilterRect,null);
            canvas.drawBitmap(builtEnviFilterBitmap,null,builtEnviFilterRect,null);
        }

        // Touch input for each menu rect

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);


                if(cardSchoolFilterRect.contains(x,y))
                {
                    //Bring up the cardSchool Filter menu
                    schoolFilterOn =true;
                }

                if(schoolFilterOn)
                {
                    if(engineerFilterRect.contains(x,y))
                    {
                        //Filter by engineer cards
                        page = 1;
                        filter = FilterType.engineer;
                        currentNumberOfPages = 1;
                        schoolFilterOn =false;
                    }
                    if(medicFilterRect.contains(x,y))
                    {
                        //filter by medic cards
                        page = 2;
                        filter = FilterType.medic;
                        currentNumberOfPages = 1;
                        schoolFilterOn = false;
                    }
                    if(EEECSFilterRect.contains(x,y))
                    {
                        //filter by EEECS cards
                        page =3;
                        filter = FilterType.EEECS;
                        currentNumberOfPages = 1;
                        schoolFilterOn = false;
                    }
                    if(artsFilterRect.contains(x,y))
                    {
                        //filter by arts cards
                        page =4;
                        filter = FilterType.arts;
                        currentNumberOfPages = 1;
                        schoolFilterOn=false;
                    }
                    if(builtEnviFilterRect.contains(x,y))
                    {
                        //filter by built environment cards
                        page = 5;
                        filter = FilterType.built;
                        currentNumberOfPages = 1;
                        schoolFilterOn = false;
                    }

                    if(backRect.contains(x,y))
                    {
                        //return to deck builder without changing filter
                        schoolFilterOn =false;
                    }
                }

                if(cardTypeFilterRect.contains(x,y))
                {
                    //Bring up the cardType filter menu
                    typeFilterOn = true;
                }
                if(typeFilterOn)
                {
                    if(monsterFilterRect.contains(x,y))
                    {
                        page = 1;
                        filter = FilterType.monster;
                        currentNumberOfPages = 5;
                        typeFilterOn =false;
                    }
                    if(manaFilterRect.contains(x,y))
                    {
                        page =6;
                        filter = FilterType.mana;
                        currentNumberOfPages = 1;
                        typeFilterOn = false;
                    }
                    if(supportFilterRect.contains(x,y))
                    {
                        page = 7;
                        filter = FilterType.support;
                        currentNumberOfPages = 2;
                        typeFilterOn = false;
                    }
                }

                if (resetFilterRect.contains(x,y))
                {
                    filter = FilterType.none;
                    currentNumberOfPages = totalNumberOfPages;
                    System.out.print("The filter has been reset");
                }


                //when there is only one page due to a filter, the arrow does not appear
                if (!filterSchoolEnabled()||(filter !=FilterType.support&&page==7)||filter!=FilterType.mana||page!=1) //Arrow does not appear on first page
                {
                    if (leftArrowRect.contains(x, y)) {
                        //move to previous page
                        page--;
                    }
                }

                //when there is only one page due to a filter, the arrow does not appear
                if (!filterSchoolEnabled()||(filter !=FilterType.engineer && page==5)||filter!=FilterType.mana||page==currentNumberOfPages) //Arrow does not appear on last page
                {
                    if (rightArrowRect.contains(x, y)) {
                        //move to next page
                        page++;
                    }
                }


                if(deckButtonRect.contains(x,y))
                {
                    //move to the Deck View
//                    getFragmentManager()
//                            .beginTransaction()
                          //  .replace(R.id.container, new DeckViewFragment(playerDeck), "DeckViewFragment").commit();
                }

                //If a card is touched it is added to the deck. This pulls information from the page arrays
                if (card1Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card1[page-1]);
                }
                if (card2Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card2[page-1]);
                }
                if (card3Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card3[page-1]);
                }
                if (card4Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card4[page-1]);
                }
                if (card5Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card5[page-1]);
                }
                if (card6Rect.contains(x,y))
                {
                    if(card6[page-1]!=-1)
                        playerDeck.addToDeck(card6[page-1]);
                }
                if (card7Rect.contains(x,y))
                {
                    if(card7[page-1]!=-1)
                        playerDeck.addToDeck(card7[page-1]);
                }
                if (card8Rect.contains(x,y))
                {
                    if(card8[page-1]!=-1)
                        playerDeck.addToDeck(card8[page-1]);
                }
            }
        }
    }
    private boolean filterSchoolEnabled()
    {
        boolean enabled = false;
        switch(page)
        {
            case 1: if(filter == FilterType.engineer) {
                enabled = true;
            }
                break;
            case 2: if (filter == FilterType.medic)
            {
                enabled = true;
                break;
            }
            case 3: if(filter == FilterType.EEECS)
            {
                enabled = true;
                break;
            }
            case 4: if(filter == FilterType.arts)
            {
                enabled = true;
                break;
            }
            case 5: if(filter == FilterType.built)
            {
                enabled = true;
                break;
            }
        }
        return enabled;
    }

    boolean filterTypeEnabled()
    {
        boolean enabled = false;
        switch(page)
        {
            case 5:if(filter ==FilterType.monster)
                enabled =true;
                break;
            case 6:if(filter == FilterType.mana)
                enabled = true;
                break;
            case 7:if(filter ==FilterType.support)
                enabled = true;
                break;
        }
        return enabled;
    }
}

