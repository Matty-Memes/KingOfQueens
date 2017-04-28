package javalampstudos.kingofqueens;

import android.content.Intent;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

/**
 * Created by 40083349 on 11/01/2017.
 */

// Local Imports

import javalampstudos.kingofqueens.kingOfQueens.AiEngine.Brain;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasRenderer;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.input.MultitouchListener;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.*;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.engine.SFX.SoundFX;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.GameBoard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.ManaCounter;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.boardLayout;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import javalampstudos.kingofqueens.kingOfQueens.util.randomGenerator;
import javalampstudos.kingofqueens.kingOfQueens.util.andyManaCounter;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.JSONcardLibrary;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.boardLayout;

// Android Imports

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.content.res.AssetManager;
import android.util.Log;
import android.content.Context;
import android.graphics.Canvas;

// Java Imports

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

public class GameLoop implements Runnable

{
    // CORE GAMELOOP VARIABLES

    private Thread gameThread = null;
    private volatile boolean running = false;
    private long targetStepPeriod;
    protected CanvasRenderer canvasRenderer;

    // Setting up UI and graphics variables - this might need to be included
    public static int width, height;
    public static float gameScaling;
    protected float uiScaling;

    // instantiate CanvasFragment
    public CanvasFragment fragment;

    // GAME STATE

    // Use enum for game states as a basis
    // Default is for normal gameplay i.e the card game itself
    public enum GameState {

        NEW, CARDGAME, OPENWORLD, PAUSE, MENU;

    }

    // Declare a gamestate
    public GameState gameState;

    // TOUCH INPUT/DRAW LOGIC

    // Access the two main arrays - hand and monstersinplay
    // Use proper names
    public int handIndex;
    public int monsterIndex;

    private Rect playerMovementRect;

    // seperate rect for moving monster cards during the attack phase
    private Rect attackRect;

    // makea mana rect
    private Rect manaRect;

    // Declare an instance of multi-touch listener
    protected MultitouchListener touchListener;

    // Don't allow dragging till permitted
    public boolean dragActive = false;
    public boolean deckCompleted = false;

    // These booleans enforce the turn structure
    // Only draw should be true

    public boolean placement = false;
    // used for mana placement
    public boolean mplacement = false;
    public boolean strat = false;
    public boolean attack = false;

    // Turn the manaZone off
    public boolean manaflag = false;

    public boolean handActive = true;
    public boolean monsterSlotActive = false;

    // This would be used during the attack phase

    public boolean monsterSlot1Active = false;
    public boolean monsterSlot2Active = false;
    public boolean monsterSlot3Active = false;

    // Card Back Sprite
    public Bitmap cardBackSprite;

    // ACTUAL CARDS TO BE DRAWN

    // Arrays to be drawn

    public ArrayList<BasicCard> handCards = new ArrayList<>();
    public ArrayList<MonsterCard> monstersInPlay = new ArrayList<>();

    // hand Cards

    public BasicCard handCard1;
    public BasicCard handCard2;
    public BasicCard handCard3;
    public BasicCard handCard4;
    public BasicCard handCard5;

    // Monster Cards

    public MonsterCard monsterCard1;
    public MonsterCard monsterCard2;
    public MonsterCard monsterCard3;

    // Opponent Cards

    public MonsterCard opponent1;
    public MonsterCard opponent2;
    public MonsterCard opponent3;

    // BEHIND THE SCENES CARD ARRAYS FOR CARD LOGIC
    public ArrayList<MonsterCard> handmonsters = new ArrayList<>();
    public ArrayList<ManaCard> handmana = new ArrayList<>();

    // ANIMATION

    // testing movement
    public BasicCard testCard;

    // boundary conditions for the test card
    public boolean testDown;
    // Hard code the boundary conditions??

    public Bitmap testBitmap;

    // OTHER CLASSES
    // This will replace individual rect declarations
    public boardLayout gameBoard;

    // hand Slots - Must instantiate these
    public Rect handRect1;
    public Rect handRect2;
    public Rect handRect3;
    public Rect handRect4;
    public Rect handRect5;

    // Monster Slots

    public Rect MSlot1Rect;
    public Rect MSlot2Rect;
    public Rect MSlot3Rect;

    // graveyard and deck rects
    private Rect graveYardRect;
    private Rect deckRect;

    // Random Logic

    public randomGenerator rand;
    public int randomIndex;

    // MANA

    // Mana Bitmap
    public Bitmap manaZoneSprite;

    // Mana GameObject for interaction
    public BasicCard manaZone;

    // Mana Counters for each type
    /*andyManaCounter engineering;
    andyManaCounter artsAndHumanities;
    andyManaCounter builtEnvironment;
    andyManaCounter eeecs;
    andyManaCounter Medic;*/

    // brians test for mc
    ManaCounter manaTest;

    // AI

    // delcaring Brians AiBrain
    public Brain aiBrain;


    // SFX
    private SoundFX test;

    // REDUNDANT

    // Not necessary
    /*
    public PlaySpace playerPlaySpace;
    public PlaySpace aiPlaySpace;
    */

    // MISC

    public BasicCard[] hand;
    public int handPos;

    // Variables relating to the players hand

    public int handCounter = 0;
    // keeps track of the current hand array position
    public int handPosition = 0;

    // booleans
    public boolean addToHand;

    // replaced with deckClicked
    public boolean resetDeck = false;

    public boolean deckInitialized = false;

    // counters
    // public int monstersInPlay = 0;

    // deck booleans
    public boolean deckClicked = false;

    // the player has chosen a card from their hand
    public boolean handCardSelected = false;

    // Declare card backs here - Possibly move to the JSON parser

    public BasicCard graveYard;
    public BasicCard deck;

    // JSON STUFF
    JSONcardLibrary lib = new JSONcardLibrary();

    // Created by Andrew - 40083349
    public GameLoop (CanvasFragment fragment, int width, int height)

    {
        targetStepPeriod = 1000000000 / 60;

        canvasRenderer = new CanvasRenderer(fragment.getActivity(), fragment);

        this.fragment = fragment;

        // Set up dimensions and scaling
        GameLoop.width = width;
        GameLoop.height = height;

        System.out.println("The width is " + GameLoop.width);
        System.out.println("The height is " + GameLoop.height);

        gameScaling = width / 320.0f;
        System.out.println("GameScaling is " + gameScaling);

        // Getting UI scaling from display metrics
        DisplayMetrics metrics = new DisplayMetrics();
        fragment.getActivity().getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        uiScaling = metrics.density;

        // set the gamestate to new intially - in the finished version will depend on the presence of a save file
        gameState = GameState.NEW;

        // Now all the rects exist
        gameBoard = new boardLayout(width, height);

        // input stuff
        touchListener = new MultitouchListener();
        canvasRenderer.setOnTouchListener(touchListener);

        // Create two deck objects which can be drawn from

        // POSITIONING THE ACTUAL CARDS TO BE DRAWN

        // Load the cardBackSprite

        AssetManager assetManager = fragment.getActivity().getAssets();
        cardBackSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Cardback.png");

        // Hand

        handCard1 = new BasicCard(234, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);
        handCard2 = new BasicCard(334, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);
        handCard3 = new BasicCard(434, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);
        handCard4 = new BasicCard(534, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);
        handCard5 = new BasicCard(634, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);

        handCards.add(handCard1);
        handCards.add(handCard2);
        handCards.add(handCard3);
        handCards.add(handCard4);
        handCards.add(handCard5);

        // required mana
        // Temporary variable to use for all hash maps
        HashMap<ManaTypes,Integer> requiredMana = new HashMap<ManaTypes,Integer>();
        requiredMana.put(ManaTypes.BUILT_ENVIRONMENT_MANA,5);

        monsterCard1 = new MonsterCard(234, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana);
        monsterCard2 = new MonsterCard(434, 280, 90, 120, cardBackSprite, true, 3,CardSchools.MEDICS, false,
                49, CardLevel.DOCTRATE, 140, 0, 3,1, requiredMana);
        monsterCard3 = new MonsterCard(634, 280, 90, 120, cardBackSprite, true, 3,CardSchools.MEDICS, false,
                49, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana);

        graveYard = new BasicCard(800, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);

        deck = new BasicCard(800, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);
        manaZone = new BasicCard(100, 340, 140, 240, manaZoneSprite, true, 3, CardSchools.MEDICS, false, 49);

        // Opponent card
        opponent1 = new MonsterCard(234, 100, 90, 120, cardBackSprite, false, 0, CardSchools.EEECS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,1, requiredMana);
        opponent2 = new MonsterCard(434, 100, 90, 120, cardBackSprite, false, 0, CardSchools.EEECS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,4, requiredMana);
        opponent3 = new MonsterCard(634, 100, 90, 120, cardBackSprite, false, 0, CardSchools.EEECS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,8, requiredMana);

        rand = new randomGenerator();

        // Test Brian's mana counter
        manaTest = new ManaCounter();

        // initialzing AiBrain.
        aiBrain = new Brain();

        // Make a hand to hold basic card
        // Assign cards to this later
        hand = new BasicCard[5];
        handPos = 0;

        // load the library assets
        lib.loadAssets(this);

    }

    // Created by Andrew - 40083349
    public void run ()

    {

        // timer variables
        long startRun;
        long startStep, endStep;
        long sleepTime, overSleepTime;

        startRun = System.nanoTime() - targetStepPeriod;
        startStep = startRun;
        overSleepTime = 0;

        try {
            while(running) {
                // get current time
                long currentTime = System.nanoTime();
                startStep = currentTime;

                // switch and case to decide which update behaviour to choose

                switch (gameState)

                {
                    case NEW:

                        newGame();
                        // always do this first
                        updateCard();
                        updateTouch();
                        break;
                    case CARDGAME:

                        // updateTouch ();
                        break;
                    case OPENWORLD:

                        break;
                    case PAUSE:

                        break;

                    case MENU:
                        // call updateMenu here
                        break;
                }


                // now draw the new updates using the other thread
                canvasRenderer.drawNeeded = true;

                // get current time
                endStep = System.nanoTime();

                // calculate how long to sleep
                sleepTime = (targetStepPeriod - (endStep - startStep))
                        - overSleepTime;

                // decide whether to sleep or not
                if(sleepTime > 0) {
                    // sleep
                    Thread.sleep(sleepTime / 1000000);
                    overSleepTime = (System.nanoTime() - endStep) - sleepTime;
                } else {
                    overSleepTime = 0;
                }
            }

        } catch(InterruptedException e)

        {

        }
    }

    // Created by Andrew - 40083349
    public void pause() {
        // stop running

        canvasRenderer.pause();

        running = false;

        while(true) {
            try {
                // wait for the thread to die
                gameThread.join();
                return;
            } catch(InterruptedException e) {
            }
        }

    }

    // Created by Andrew - 40083349
    public void resume()

    {
        // start running
        running = true;
        // setup new thread
        gameThread = new Thread(this);
        // start the thread
        gameThread.start();

        // go to the renderer thread and run it's resume method
        canvasRenderer.resume();

    }

    // Created by Andrew - 40083349
    private void newGame ()

    {
        gameBoard = new boardLayout(width, height);
    }

    // Created by Andrew - 40083349
    private void updateTouch ()

    {
        switch (gameState)

        // thumbstick stuff??

        {
            case NEW:

                for (int i = 0; i < touchListener.MAX_TOUCH_POINTS; i++)

                {

                    if (touchListener.isTouchContinuous(i))
                    {

                        float x = touchListener.getTouchX(i), y = touchListener.getTouchY(i);


                        if (boardLayout.deckRect.contains((int) x, (int) y) && deckCompleted == false)

                        {

                            populateHand();
                            // Allow mana placement
                            mplacement = true;

                        }

                        // The player can only place cards in his half of the screen
                        // Turn this on for the monster placement phase
                        // You must be allowed to drag before this is activated
                        if(boardLayout.playerMovementRect.contains((int) x, (int) y) && placement && dragActive)

                        {
                            // how do you know which card this effects
                            // set the index of the array to talk to
                            handCards.get(handIndex).x = x;
                            handCards.get(handIndex).y = y;

                        }

                        // This is for mana movement only
                        if(boardLayout.playerMovementRect.contains((int) x, (int) y) && mplacement && dragActive)

                        {
                            // how do you know which card this effects
                            // set the index of the array to talk to
                            handCards.get(handIndex).x = x;
                            handCards.get(handIndex).y = y;


                        }

                        // Set up movement for attack logic
                        if(boardLayout.attackRect.contains((int) x, (int) y) && attack && dragActive)

                        {
                            monstersInPlay.get(monsterIndex).x = x;
                            monstersInPlay.get(monsterIndex).y = y;

                        }

                        // hand detection for mana

                        if (boardLayout.handRect1.contains((int) x, (int) y) && handActive && mplacement)

                        {
                            // Allow the card chosen to be dragged
                            dragActive = true;
                            // The first card becomes the current card
                            handIndex = 0;
                            // No more hand cards can be picked up
                            handActive = false;

                        }


                        if (boardLayout.handRect2.contains((int) x, (int) y) && handActive && mplacement)

                        {
                            dragActive = true;
                            handIndex = 1;
                            handActive = false;

                        }

                        if (boardLayout.handRect3.contains((int) x, (int) y) && handActive && mplacement)

                        {
                            dragActive = true;
                            handIndex = 2;
                            handActive = false;

                        }

                        if (boardLayout.handRect4.contains((int) x, (int) y) && handActive && mplacement)

                        {
                            dragActive = true;
                            handIndex = 3;
                            handActive = false;

                        }

                        if (boardLayout.handRect5.contains((int) x, (int) y) && handActive && mplacement)

                        {
                            dragActive = true;
                            handIndex = 4;
                            handActive = false;

                        }


                        // hand detection during placement phase


                        if (boardLayout.handRect1.contains((int) x, (int) y) && handActive && placement)

                        {
                            // Allow the card chosen to be dragged
                            dragActive = true;
                            // The first card becomes the current card
                            handIndex = 0;
                            // No more hand cards can be picked up
                            handActive = false;

                        }


                        if (boardLayout.handRect2.contains((int) x, (int) y) && handActive && placement)

                        {
                            dragActive = true;
                            handIndex = 1;
                            handActive = false;

                        }

                        if (boardLayout.handRect3.contains((int) x, (int) y) && handActive && placement)

                        {
                            dragActive = true;
                            handIndex = 2;
                            handActive = false;

                        }

                        if (boardLayout.handRect4.contains((int) x, (int) y) && handActive && placement)

                        {
                            dragActive = true;
                            handIndex = 3;
                            handActive = false;

                        }

                        if (boardLayout.handRect5.contains((int) x, (int) y) && handActive && placement)

                        {
                            dragActive = true;
                            handIndex = 4;
                            handActive = false;

                        }

                        // Monster Slot Detection - placement phase
                        // Should be detecting card co-ordinates rather than finger position
                        // Block mana cards from being placed



                        if (boardLayout.MSlot1Rect.contains((int)handCards.get(handIndex).x, (int)handCards.get(handIndex).y) && placement
                                && handCards.get(handIndex).id == 0)

                        {

                            // no more card movement
                            dragActive = false;

                            // get rid of the hand card
                            handCards.get(handIndex).destroyed = true;

                            // update the bitmap of the monster card and lock it at the right slot
                            monsterCard1.sprite = handCards.get(handIndex).sprite;
                            monsterCard1.x = 234;
                            monsterCard1.y = 280;

                            // the card has been placed
                            placement = false;
                            attack = true;


                            monsterSlotActive = true;

                        }


                        if (boardLayout.MSlot2Rect.contains((int)handCards.get(handIndex).x, (int)handCards.get(handIndex).y) && placement
                                && handCards.get(handIndex).id == 0)

                        {

                            // no more card movement
                            dragActive = false;

                            // get rid of the hand card
                            handCards.get(handIndex).destroyed = true;
                            // update the bitmap of the monster card and lock it at the right slot
                            monsterCard2.sprite = handCards.get(handIndex).sprite;
                            monsterCard2.x = 434;
                            monsterCard2.y = 280;

                            // the card has been placed
                            placement = false;
                            attack = true;

                            monsterSlotActive = true;

                        }

                        if (boardLayout.MSlot3Rect.contains((int)handCards.get(handIndex).x, (int)handCards.get(handIndex).y) && placement
                                && handCards.get(handIndex).id == 0)

                        {
                            // no more card movement
                            dragActive = false;

                            // get rid of the hand card
                            handCards.get(handIndex).destroyed = true;

                            // update the bitmap of the monster card and lock it at the right slot
                            monsterCard3.sprite = handCards.get(handIndex).sprite;
                            monsterCard3.x = 634;
                            monsterCard3.y = 280;


                            // the card has been placed
                            placement = false;
                            attack = true;

                            monsterSlotActive = true;

                        }

                        // ATTACK PHASE LOGIC


                        // Allow dragging during the attack phase

                        if (boardLayout.MSlot1Rect.contains((int) x, (int) y) && attack && monsterSlotActive)

                        {

                            dragActive = true;
                            monsterIndex = 0;
                            monsterSlotActive = false;

                        }

                        if (boardLayout.MSlot2Rect.contains((int) x, (int) y) && attack && monsterSlotActive)

                        {

                            dragActive = true;
                            monsterIndex = 1;
                            monsterSlotActive = false;

                        }

                        if (boardLayout.MSlot3Rect.contains((int) x, (int) y) && attack && monsterSlotActive)

                        {
                            dragActive = true;
                            monsterIndex = 2;
                            monsterSlotActive = false;

                        }

                        // Mana Handling

                        // && handCards.get(handIndex) instanceof ManaCard

                        // Only accept mana cards
                        if (boardLayout.manaRect.contains((int)handCards.get(handIndex).x, (int)handCards.get(handIndex).y)
                                && handCards.get(handIndex).id == 1 && manaflag)

                        {
                            // no more dragging now the mana has been placed
                            dragActive = false;

                            // Work out which manaCounter object to update
                            switch (handCards.get(handIndex).getCardSchool())

                            {
                                case ARTS_HUMANITIES:
                                    // artsAndHumanities.incrementCounter();
                                    manaTest.addMana(ManaTypes.ARTS_HUMANITIES_MANA);
                                    break;
                                case ENGINEERING:
                                    //    engineering.incrementCounter();
                                    manaTest.addMana(ManaTypes.ENGINEERING_MANA);
                                    break;
                                case BUILT_ENVIORNMENT:
                                    //    builtEnvironment.incrementCounter();
                                    manaTest.addMana(ManaTypes.BUILT_ENVIRONMENT_MANA);
                                    break;
                                case EEECS:
                                    manaTest.addMana(ManaTypes.EEECS_MANA);
                                    //   eeecs.incrementCounter();
                                    break;
                                case MEDICS:
                                    //    Medic.incrementCounter();
                                    manaTest.addMana(ManaTypes.MEDICS_MANA);
                                    break;
                                // Deal with generic mana
                                /*
                                case
                                    manaTest.addMana(ManaTypes.GENERIC_MANA);
                                    break;
                                */
                                case SOCIAL_SCIENCES:
                                    manaTest.addMana(ManaTypes.SOCIAL_SCIENCES_MANA);

                            }

                            // get the current card off the screen
                            handCards.get(handIndex).destroyed = true;


                            // need to pass information from the mana card in question


                            manaflag = false;
                            // allow movement of hand cards again
                            handActive = true;
                            // being the placement phase
                            placement = true;

                        }

                    }

                    // set handActive back here

                    // Touch Input logic goes here
                    else

                    {


                    }

                    // Put any single touch logic here

                }

                // end for loop

                break; // end NEW case
        }
    }


    private void testSoundFX ()

    {
        // arguments: Sound ID, left volume, right volume, priority, loop, rate
        // There are known problems with this
        // test.play(0, 0.5f, 0.5f, 1, 0, 1.0f);

        // int x = generateRandomNumber();
        // System.out.println(x);

        // testing soundfx
        // test.playSoundFX();


    }

    // This is the original mana method
    /*

    private void updateMana ()

    {
      engineering.update();
      artsAndHumanities.update();
      builtEnvironment.update();
      // eeecs.update();
      Medic.update();

        manaTest.update();

    }

    */

    // This handles all updates - needs split into arraylists fore readability
    private void updateCard ()

    {
        handCard1.update();
        handCard2.update();
        handCard3.update();
        handCard4.update();
        handCard5.update();

        monsterCard1.update();
        monsterCard2.update();
        monsterCard3.update();

        // Graveyard and deck updates

        graveYard.update();
        deck.update();

        manaZone.update();

        // opponent card
        opponent1.update();
        opponent2.update();
        opponent3.update();

    }


    private void populateHand ()

    {

        // this fills the hand array
        for (int i = 0; i < 5; i++)

        {
            drawFromDeck(i);

        }


        // You can't re-run the draw method

        deckCompleted = true;
        mplacement = true;
        manaflag = true;

    }


    // get a random index
    // pull a card out of the source deck at random
    // put in the hand

    // modify so the player and opponent can share this
    private void drawFromDeck (int index)

    {

        randomIndex = rand.generateRandomNumber();
        System.out.println("Random is" + randomIndex);

        int dex = randomIndex-1;

        // Set the x and y first then update
        switch (index)

        {
            case 0:

                if (dex <= 7)

                {
                    handCard1.sprite = lib.monsterCards.get(dex).sprite;

                }

                break;


            case 1:
                if (dex <= 7)

                {
                    handCard2.sprite = lib.monsterCards.get(dex).sprite;

                }


                break;


            case 2:
                if (dex <= 7)

                {
                    handCard3.sprite = lib.monsterCards.get(dex).sprite;
                }


                break;

            case 3:
                if (dex <= 7)

                {
                    handCard4.sprite = lib.monsterCards.get(dex).sprite;

                }

                if (dex <= 12)


                    break;

            case 4:

                if (dex <= 7)

                {
                    handCard5.sprite = lib.monsterCards.get(dex).sprite;

                }

                break;

        }

        // System.out.println("Finished this method");

    }


    /*

    private void populateHand ()

    {

        if (playerTurn)

        {

            for (int i = 0; i < 3; i++)

            {

                int rand = random.generateRandomNumber(playerHasAppeared, playerhCounter);
                playerHand[i] = cardList[rand];

            }

        } // end playerturn logic

        if (!playerTurn)

        {
            for (int i = 0; i < 3; i++)

            {
                // System.out.println("We're here");

                int rand = random.generateRandomNumber(opponentHasAppeared, opponenthCounter);


                System.out.println("The number generated was " + rand);
                // No need to actually draw anything here since you don't see the oppoennts hand
                opponentHand[i] = cardList[rand];

            }

        }

    }


    */


}


