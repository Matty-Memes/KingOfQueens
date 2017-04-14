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
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.ManaCounter;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.PlaySpace;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.boardLayout;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import javalampstudos.kingofqueens.kingOfQueens.util.randomGenerator;
import javalampstudos.kingofqueens.kingOfQueens.util.andyManaCounter;


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

// GET RID OF ALL THE PRINT STATEMENTS

public class GameLoop implements Runnable

{

    // delcaring Brians AiBrain
   public Brain aiBrain;
    // delcaring the playspace objects
    // brian
    public PlaySpace playerPlaySpace;
    public PlaySpace aiPlaySpace;

    // instantiate CanvasFragment
    public CanvasFragment fragment;

    // SFX Variables
    private SoundFX test;

    // declare variables
    private Thread gameThread = null;
    private volatile boolean running = false;
    private long targetStepPeriod;
    protected CanvasRenderer canvasRenderer;

    // Setting up UI and graphics variables - this might need to be included
    public static int width, height;
    public static float gameScaling;
    protected float uiScaling;

    // Use enum for game states as a basis
    // Default is for normal gameplay i.e the card game itself
    public enum GameState {

        NEW, CARDGAME, OPENWORLD, PAUSE, MENU;

    }

    public Context context;

    public boardLayout gameBoard;

    // Declare a gamestate
    public GameState gameState;


    // Access the two main arrays - hand and monstersinplay
    // Use proper names
    public int handIndex;
    public int monsterIndex;

    // Variables for flipping images
    private Matrix matrix = new Matrix();

    // PUT TOUCH INPUT RECTS HERE
    // This could be moved to it's own class

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

    // Splitting the screen into movement rects
    private Rect movementRect;

    private Rect playerMovementRect;

    // seperate rect for moving monster cards during the attack phase
    private Rect attackRect;


    //pause rect and bitmap
    private Rect pauseRect;
    private Bitmap pauseBitmap;

    // half the screen
    private Rect halfScreenRect;

    // makea mana rect
    private Rect manaRect;

    // Declare an instance of multi-touch listener
    protected MultitouchListener touchListener;

    // Don't allow dragging till permitted
    public boolean dragActive = false;

    // END TOUCH INPUT LOGIC

    public boolean deckCompleted = false;

    // CARD GAME LOGIC

    public BasicCard[] hand;
    public int handPos;


    public boolean playerTurn;

    // Declare all Sprites here - always include "Sprite" after the name of the image

    public Bitmap DataAdminSprite;
    public Bitmap HackerManSprite;
    public Bitmap GeoSprite;
    public Bitmap cardBackSprite;

    public Bitmap PsychologistSprite;
    public Bitmap SociologistSprite;


    // MANA BITMAPS

    public Bitmap artsManaSprite;
    public Bitmap builtEnvironmentManaSprite;
    public Bitmap eeecsManaSprite;
    public Bitmap engineeringManaSprite;
    public Bitmap medicalManaSprite;
    public Bitmap socialScienceSprite;


    // Mana Bitmap
    public Bitmap manaZoneSprite;

    // Mana GameObject for interaction
    public BasicCard manaZone;

    // Prevent multiple runs of single methods

    // Hands

    /*
    public boolean handRect1Active = true;
    public boolean handRect2Active = true;
    public boolean handRect3Active = true;
    public boolean handRect4Active = true;
    public boolean handRect5Active = true;
    */

    public boolean handActive = true;
    public boolean monsterSlotActive = false;

    // This would be used during the attack phase

    public boolean monsterSlot1Active = false;
    public boolean monsterSlot2Active = false;
    public boolean monsterSlot3Active = false;


    // DataAdmin
    public ManaTypes[] attack1ManaRequiredDA;
    public ManaTypes[] attack2ManaRequiredDA;

    // Hackerman

    public ManaTypes[] attack1ManaRequiredHM;
    public ManaTypes[] attack2ManaRequiredHM;

    // Random Logic

    public randomGenerator rand;
    public int randomIndex;

    // Variables relating to the players hand

    public int handCounter = 0;
    // keeps track of the current hand array position
    public int handPosition = 0;

    // increment this when a card is destroyed
    int cardsDestroyed = 0;

    // monitor the times drawn
    int numDraws = 0;

    // Monitor the size of both players decks
    int playerDeckSize = 40;
    int opponentDeckSize = 40;

    // Current card - temporarily stores the card you're working with
    public BasicCard currentCard;
    public int touchCounter = 0;

    // Attack Logic Variables
    public MonsterCard attacker;

    // The game needs to be able to access every possible card
    public BasicCard[] cardList = new BasicCard[40];

    // Declare all the monsters here
    public MonsterCard DataAdmin;
    public MonsterCard HackerMan;
    public MonsterCard Geologist;
    public MonsterCard Psychologist;
    public MonsterCard Sociologist;

    // Declare opponent monster cards here
    public MonsterCard opponentCard1;
    public MonsterCard opponentCard2;
    public MonsterCard opponentCard3;

    // Declare all the mana cards here
    public ManaCard EEECS;
    public ManaCard BuiltEnvironment;
    public ManaCard MedicalScience;
    public ManaCard SocialSciences;
    public ManaCard ArtsandHumanities;
    public ManaCard AnyMana;
    public ManaCard Engineering;

    // Declare all the buff/support cards here

    // Declare card backs here


    // These are temporary cards for checking positions


    // booleans
    public boolean addToHand;

    // replaced with deckClicked
    public boolean resetDeck = false;


    // These booleans enforce the turn structure
    // Only draw should be true

    public boolean placement = false;
    // used for mana placement
    public boolean mplacement = false;
    public boolean strat = false;
    public boolean attack = false;

    // Turn the manaZone off
    public boolean manaflag = false;

    // "game state" variables - would need proper game state management in Android
    public boolean prepPhase = true;

    public boolean deckInitialized = false;

    // counters
    // public int monstersInPlay = 0;

    // deck booleans
    public boolean deckClicked = false;

    // Random number value for brians method
    // private static Random rand;
    // track the position in the discardPile array
    int discardCounter = 0;

    // the player has chosen a card from their hand
    public boolean handCardSelected = false;

    // ARRAY LISTS OF CARDS
    public ArrayList<BasicCard> monsterCards = new ArrayList<BasicCard>();
    public ArrayList<ManaCard> manaCards = new ArrayList<ManaCard>();


    // These keep track of and draw the actual cards

    public ArrayList<BasicCard> handCards = new ArrayList<>();
    public ArrayList<MonsterCard> monstersInPlay = new ArrayList<>();


    // GameLoop Constructor
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

        gameBoard = new boardLayout();

        // input stuff
        touchListener = new MultitouchListener();
        canvasRenderer.setOnTouchListener(touchListener);

        // load assets - sprite now exists
        loadAssets ();








        // POSITIONING THE HAND

        // The first gap is slightly larger to make it obvious the deck is seperate

        /*handCard1 = new BasicCard(234, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);

        // The rest of the gaps should be either 10 or 20

        handCard2 = new BasicCard(334, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);
        handCard3 = new BasicCard(434, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);
        handCard4 = new BasicCard(534, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);
        handCard5 = new BasicCard(634, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);*/

        // Put the hand cards in the array


        // Positioning the monsters

       /* monsterCard1 = new MonsterCard(234, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana);
        monsterCard2 = new MonsterCard(434, 280, 90, 120, cardBackSprite, true, 3,CardSchools.MEDICS, false,
                49, CardLevel.DOCTRATE, 140, 0, 3,1, requiredMana);
        monsterCard3 = new MonsterCard(634, 280, 90, 120, cardBackSprite, true, 3,CardSchools.MEDICS, false,
                49, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana);
*/
        // Put the monster cards in the array


        // hand

        // Draw the graveyard pile
        /*graveYard = new BasicCard(800, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
            49);*/

        // y co-ordinate is a gap of 50 plus half the card size

        // x is half the height less than the total card size
        /*deck = new BasicCard(800, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49);*/

        // Declare and use the mana zone
        // There should be a proper non-abstract class for this kind of object
       /* manaZone = new BasicCard(100, 340, 140, 240, manaZoneSprite, true, 3, CardSchools.MEDICS, false, 49);
*/
        rand = new randomGenerator();





        // iniatlising the Playspaces
        // brian
       // aiPlaySpace = new PlaySpace();
       // playerPlaySpace = new PlaySpace();
        // initialzing AiBrain.
        aiBrain = new Brain();



    }



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
                        updateHand();
                        // updateMana();
                        // updateMonsterCards();
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

    // Pause
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

    // Resume
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

    private void newGame ()

    {
      // set up a rect for detecting input
      gameSetup();

      // testing state management
      // System.out.println("New game started");

    }

    // continuously checks for new touch input
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


                    if (deckRect.contains((int) x, (int) y) && deckCompleted == false)

                    {

                        populateHand();
                        // Allow mana placement
                        mplacement = true;

                    }

                // The player can only place cards in his half of the screen
                // Turn this on for the monster placement phase
                // You must be allowed to drag before this is activated
                if(playerMovementRect.contains((int) x, (int) y) && placement && dragActive)

                {
                   // how do you know which card this effects
                   // set the index of the array to talk to
                   playerPlaySpace.getHand().getMonsertCards()[handIndex].x = x;
                    playerPlaySpace.getHand().getMonsertCards()[handIndex].y = y;

                }

                    // This is for mana movement only
                    if(playerMovementRect.contains((int) x, (int) y) && mplacement && dragActive)

                    {
                        // how do you know which card this effects
                        // set the index of the array to talk to
                        playerPlaySpace.getHand().getMonsertCards()[handIndex].x = x;
                        playerPlaySpace.getHand().getMonsertCards()[handIndex].y = y;

                    }



                // Set up movement for attack logic
                if(attackRect.contains((int) x, (int) y) && attack && dragActive)

                {
                    playerPlaySpace.getCardZones()[monsterIndex].getCurrentCard().x = x;
                    playerPlaySpace.getCardZones()[monsterIndex].getCurrentCard().y = y;

                }

                // hand detection for mana

                    if (handRect1.contains((int) x, (int) y) && handActive && mplacement)

                    {
                        // Allow the card chosen to be dragged
                        dragActive = true;
                        // The first card becomes the current card
                        handIndex = 0;
                        // No more hand cards can be picked up
                        handActive = false;

                    }


                    if (handRect2.contains((int) x, (int) y) && handActive && mplacement)

                    {
                        dragActive = true;
                        handIndex = 1;
                        handActive = false;

                    }

                    if (handRect3.contains((int) x, (int) y) && handActive && mplacement)

                    {
                        dragActive = true;
                        handIndex = 2;
                        handActive = false;

                    }

                    if (handRect4.contains((int) x, (int) y) && handActive && mplacement)

                    {
                        dragActive = true;
                        handIndex = 3;
                        handActive = false;

                    }

                    if (handRect5.contains((int) x, (int) y) && handActive && mplacement)

                    {
                        dragActive = true;
                        handIndex = 4;
                        handActive = false;

                    }


                    // hand detection during placement phase


                if (handRect1.contains((int) x, (int) y) && handActive && placement)

                {
                   // Allow the card chosen to be dragged
                   dragActive = true;
                   // The first card becomes the current card
                   handIndex = 0;
                   // No more hand cards can be picked up
                   handActive = false;

                }


                    if (handRect2.contains((int) x, (int) y) && handActive && placement)

                    {
                        dragActive = true;
                        handIndex = 1;
                        handActive = false;

                    }

                    if (handRect3.contains((int) x, (int) y) && handActive && placement)

                    {
                        dragActive = true;
                        handIndex = 2;
                        handActive = false;

                    }

                    if (handRect4.contains((int) x, (int) y) && handActive && placement)

                    {
                        dragActive = true;
                        handIndex = 3;
                        handActive = false;

                    }

                    if (handRect5.contains((int) x, (int) y) && handActive && placement)

                    {
                        dragActive = true;
                        handIndex = 4;
                        handActive = false;

                    }

                    // Monster Slot Detection - placement phase
                    // Should be detecting card co-ordinates rather than finger position
                    // Block mana cards from being placed



                    if (MSlot1Rect.contains((int)playerPlaySpace.getHand().getMonsertCards()[handIndex].x, (int)playerPlaySpace.getHand().getMonsertCards()[handIndex].y) && placement
                          && playerPlaySpace.getHand().getMonsertCards()[handIndex].id == 0)

                {

                    // no more card movement
                    dragActive = false;

                   // get rid of the hand card

                    playerPlaySpace.getHand().getMonsertCards()[handIndex].destroyed = true;
                   // update the bitmap of the monster card and lock it at the right slot
                  /* monsterCard1.sprite = handCards.get(handIndex).sprite;
                   monsterCard1.x = 234;
                   monsterCard1.y = 280;*/

                   // the card has been placed
                   placement = false;
                   attack = true;


                    monsterSlotActive = true;

                }


                    if (MSlot2Rect.contains((int)playerPlaySpace.getHand().getMonsertCards()[handIndex].x, (int)playerPlaySpace.getHand().getMonsertCards()[handIndex].y) && placement
                        && playerPlaySpace.getHand().getMonsertCards()[handIndex].id == 0)

                    {

                        // no more card movement
                        dragActive = false;

                        // get rid of the hand card
                        playerPlaySpace.getHand().getMonsertCards()[handIndex].destroyed = true;

                        // update the bitmap of the monster card and lock it at the right slot
                     /*   monsterCard2.sprite = handCards.get(handIndex).sprite;      CARDZONE LOGIC
                        monsterCard2.x = 434;
                        monsterCard2.y = 280;*/

                        // the card has been placed
                        placement = false;
                        attack = true;

                        monsterSlotActive = true;

                    }

                    if (MSlot3Rect.contains((int)playerPlaySpace.getHand().getMonsertCards()[handIndex].x, (int)playerPlaySpace.getHand().getMonsertCards()[handIndex].y) && placement
                            && playerPlaySpace.getHand().getMonsertCards()[handIndex].id == 0)

                    {
                        // no more card movement
                        dragActive = false;

                        // get rid of the hand card
                        playerPlaySpace.getHand().getMonsertCards()[handIndex].destroyed = true;

                        // update the bitmap of the monster card and lock it at the right slot
                        /*monsterCard3.sprite = handCards.get(handIndex).sprite;
                        monsterCard3.x = 634;
                        monsterCard3.y = 280;*/

                        // the card has been placed
                        placement = false;
                        attack = true;

                        monsterSlotActive = true;

                    }

                    // ATTACK PHASE LOGIC


                    // Allow dragging during the attack phase

                    if (MSlot1Rect.contains((int) x, (int) y) && attack && monsterSlotActive)

                    {

                        dragActive = true;
                        monsterIndex = 0;
                        monsterSlotActive = false;

                    }

                    if (MSlot2Rect.contains((int) x, (int) y) && attack && monsterSlotActive)

                    {

                        dragActive = true;
                        monsterIndex = 1;
                        monsterSlotActive = false;

                    }

                    if (MSlot3Rect.contains((int) x, (int) y) && attack && monsterSlotActive)

                    {

                        dragActive = true;
                        monsterIndex = 2;
                        monsterSlotActive = false;

                    }

                    // Mana Handling



                    // Only accept mana cards
                    if (manaRect.contains((int)playerPlaySpace.getHand().getManaCards()[handIndex].x, (int)playerPlaySpace.getHand().getManaCards()[handIndex].y)
                            && playerPlaySpace.getHand().getManaCards()[handIndex].id == 1 && manaflag)

                    {
                            // no more dragging now the mana has been placed
                            dragActive = false;

                            // Work out which manaCounter object to update
                        // only to get rid of error.

                            switch (playerPlaySpace.getHand().getManaCards()[handIndex].getManaType()) // THIS WILL NOT WORK - YOU NEED THE CORRECT OBJECT

                            {
                                case ARTS_HUMANITIES_MANA:
                                   // artsAndHumanities.incrementCounter();
                                    playerPlaySpace.getManaCounter().addMana(ManaTypes.ARTS_HUMANITIES_MANA);
                                    break;
                                case ENGINEERING_MANA:
                                //    engineering.incrementCounter();
                                    playerPlaySpace.getManaCounter().addMana(ManaTypes.ENGINEERING_MANA);
                                    break;
                                case BUILT_ENVIRONMENT_MANA:
                                //    builtEnvironment.incrementCounter();
                                    playerPlaySpace.getManaCounter().addMana(ManaTypes.BUILT_ENVIRONMENT_MANA);
                                    break;
                                case EEECS_MANA:
                                    playerPlaySpace.getManaCounter().addMana(ManaTypes.EEECS_MANA);
                                 //   eeecs.incrementCounter();
                                    break;
                                case MEDICS_MANA:
                                //    Medic.incrementCounter();
                                    playerPlaySpace.getManaCounter().addMana(ManaTypes.MEDICS_MANA);
                                    break;
                                case GENERIC_MANA:
                                    playerPlaySpace.getManaCounter().addMana(ManaTypes.GENERIC_MANA);
                                    break;
                                case SOCIAL_SCIENCES_MANA:
                                    playerPlaySpace.getManaCounter().addMana(ManaTypes.SOCIAL_SCIENCES_MANA);

                            }

                        // get the current card off the screen
                        playerPlaySpace.getHand().getManaCards()[handIndex].destroyed = true;






                      // addToMana
                     // builtEnvironment.incrementCounter();
                      manaflag = false;



                            // need to pass information from the mana card in question


                            manaflag = false;
                            // allow movement of hand cards again
                            handActive = true;
                            // being the placement phase
                            placement = true;

                        }





                }

                // set handActive back here

                else

                {

                  if (Geologist.pointerID == i)

                  {

                      System.out.println("k");

                      Geologist.x = 30;
                      Geologist.y = 350;

                  }


                }

                // Put any single touch logic here

            }

             // end for loop

            break; // end NEW case
        }
    }

    // position the player on the screen
    // make rects for touch input

    private void gameSetup ()

    {
        AssetManager assetManager = fragment.getActivity().getAssets();

        // Instantiate new rects here

        movementRect = new Rect ( 0, 0, GameLoop.width, GameLoop.height);

        //Pause Bitmap
        pauseBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/Pause.png");


        // hand Rects

        handRect1 = new Rect(
                (int) (234 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (234 + (90 / 2)),
                (int) (410 + (120 / 2)));

        handRect2 = new Rect(
                (int) (334 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (334 + (90 / 2)),
                (int) (410 + (120 / 2)));

        handRect3 = new Rect(
                (int) (434 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (434 + (90 / 2)),
                (int) (410 + (120 / 2)));

        handRect4 = new Rect(
                (int) (534 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (534 + (90 / 2)),
                (int) (410 + (120 / 2)));

        handRect5 = new Rect(
                (int) (634 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (634 + (90 / 2)),
                (int) (410 + (120 / 2)));


        // Monster Slot Rects

        MSlot1Rect = new Rect(
                (int) (234 - (90 / 2)),
                (int) (280 - (120 / 2)),
                (int) (234 + (90 / 2)),
                (int) (280 + (120 / 2)));

        MSlot2Rect = new Rect(
                (int) (434 - (90 / 2)),
                (int) (280 - (120 / 2)),
                (int) (434 + (90 / 2)),
                (int) (280 + (120 / 2)));

        MSlot3Rect = new Rect(
                (int) (634 - (90 / 2)),
                (int) (280 - (120 / 2)),
                (int) (634 + (90 / 2)),
                (int) (280 + (120 / 2)));


        // graveyard and deck rects
        graveYardRect = new Rect(
                (int) (800 - (90 / 2)),
                (int) (280 - (120 / 2)),
                (int) (800 + (90 / 2)),
                (int) (280 + (120 / 2)));

        deckRect = new Rect(
                (int) (800 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (800 + (90 / 2)),
                (int) (410 + (120 / 2)));

        // Initialize the mana zone here


        // move all rect initialization here
        gameBoard.initializeRects();

        // Set up values for each menu rect
        pauseRect = new Rect((int) (85),
                (int) (410),
                (int) (285),
                (int) (510));

        // playerArea
        playerMovementRect = new Rect((int) 0, (int) (GameLoop.height / 2), (int) GameLoop.width, (int) GameLoop.height);

        // mana stuff
        manaRect = new Rect(
                (int) (100 - (140 / 2)),
                (int) (340 - (240 / 2)),
                (int) (100 + (140 / 2)),
                (int) (340 + (240 / 2)));

        // no movement any lower than the bottom of the monster cards
        attackRect = new Rect(0, 0, 480, 340);


        }


    private void doThingy ()

    {
        // arguments: Sound ID, left volume, right volume, priority, loop, rate
        // There are known problems with this
        // test.play(0, 0.5f, 0.5f, 1, 0, 1.0f);

        // int x = generateRandomNumber();
        // System.out.println(x);

        // testing soundfx
        // test.playSoundFX();

    }

    // update all monster cards before drawing

    /*
    private void updateMonsterCards ()

    {
        for (int i = 0; i < monsterCards.size(); i++)

        {
            if (monsterCards.get(i).destroyed == false)

            {
              monsterCards.get(i).update();
            }

            else

            {
             monsterCards.remove(i);

            }

        }

    }

    */

    private void updateHand ()

    {

        for (int i = 0; i < handCards.size(); i++)

        {
            playerPlaySpace.getHand().getManaCards()[i].update();
            playerPlaySpace.getHand().getMonsertCards()[i].update();

        }


    }

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

    // This method should be altered.

    private void updateCard ()

    {
        //updating cardZones
        for(int i=0; i <3; i++)
        {
            if(playerPlaySpace.getCardZones()[i].getCurrentCard().destroyed == false)
            {
                playerPlaySpace.getCardZones()[i].getCurrentCard().update();
            }

        }

        // Graveyard and deck updates

        playerPlaySpace.getGraveYard().update();
        playerPlaySpace.getDeck().update();

        manaZone.update();

        // opponent card
        for(int j=0; j < 3; j++)
        {
            if(aiPlaySpace.getCardZones()[j].getCurrentCard().destroyed == false)
            {
                aiPlaySpace.getCardZones()[j].getCurrentCard().update();
            }

        }


    }

    // MATTHEW - MOVE THIS TO THE CARD LIBRARY CLASS

    private void loadAssets ()

    {
        AssetManager assetManager = fragment.getActivity().getAssets();

        // load in individual assets
        DataAdminSprite = AssetLoader.loadBitmap(assetManager, "img/Matthew/SmallDataAdmin.png");
        HackerManSprite = AssetLoader.loadBitmap(assetManager, "img/Matthew/HackermanSmall.png");

        GeoSprite = AssetLoader.loadBitmap(assetManager, "img/Matthew/SmallGeo.png");

        PsychologistSprite = AssetLoader.loadBitmap(assetManager, "img/Matthew/Psycho.png");
        SociologistSprite = AssetLoader.loadBitmap(assetManager, "img/Matthew/Social.png");

        // load cardback sprite
        cardBackSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Cardback.png");

        // ManaSprites

        socialScienceSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/SocialSciencesMana.png");
        medicalManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/MedicalMana.png");
        artsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/ArtsMana.png");
        eeecsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EEECSMana.png");
        engineeringManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EEECSMana.png");
        builtEnvironmentManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/BuiltMana.png");

        // load mana cards here

        manaZoneSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/ManaZoner.png");

        // load manazone sprite



        // load SFX in here

        //NB - Volume is hard coded currently but it should be selectable by the user

        // test = AssetLoader.loadSoundpool(assetManager, "SFX/EnemyDeathNoise.ogg");



        // You have to use a certain API for this to work.
        // Might need a way to check for this.
        try {
            test = new SoundFX(fragment.getActivity(), R.raw.buttonpush);
        } catch (IOException e) {
            Log.e("DialFX", "Dial FX could not be loaded");
        }



    }

    // instantiate cards and put them in the source card array
    // 9 of the 40 cards are generated

    private void initializeDeck ()

    {

        // DataAdmin
        attack1ManaRequiredDA = new ManaTypes[] { ManaTypes.EEECS_MANA, ManaTypes.GENERIC_MANA };
        attack2ManaRequiredDA = new ManaTypes[] { ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA };

        // Hackerman
        attack2ManaRequiredHM = new ManaTypes[] { ManaTypes.EEECS_MANA, ManaTypes.GENERIC_MANA };
        attack2ManaRequiredHM = new ManaTypes[] { ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA };

        // Declare Mana Cards here


        /*

        for (int i = 0; i < 40; i++)

        {
            playerHasAppeared[i] = 41;
            // System.out.println(i + " : " + hasAppeared[i]);
        }

        // Make sure the array isn't null - opponent

        for (int i = 0; i < 40; i++)

        {

            opponentHasAppeared[i] = 41;
        }

        // keep for troubleshooting
        deckInitialized = true;

    */

    }



    private void populateHand ()

    {

      // this fills the hand array
      /*for (int i = 0; i < 5; i++)

      {
          drawFromDeck(i);

      }*/


        // You can't re-run the draw method

        deckCompleted = true;
        mplacement = true;
        manaflag = true;


    }

    private void populateHandAnimation ()

    {
      for (int i = 0; i < 5; i++)

      {
        //  hand[i].x++;
        //  hand[i].y++;

        // need to setup bounds which prevent movement after a certain position

      }


    }

    // keep for testing

    private void clearHand ()

    {


    }

    // Should contain the logic for the player selecting a particular attack
    // In the touch method remember the no of touches that have occured

    // Something needs to clear the touchCounter

    private void selectAttack (BasicCard currentCard)

    {
      // if the touch area is touched once
      // change the colour and "prepare" the first attack


    }


    // get a random index
    // pull a card out of the source deck at random
    // put in the hand

    // modify so the player and opponent can share this

   /* private void drawFromDeck (int index)

    {
        randomIndex = rand.generateRandomNumber();
        System.out.println(randomIndex);

        // pull out a random card
        // give it a position

        // gives you access to the card you pulled out
        // int indie = cardList[randomIndex-1];

        // Depending on which card you drew
        // Updates these using the positions worked out

        int dex = randomIndex-1;

        // Set the x and y first then update
        switch (index)

        {
            case 0:
                handCard1.sprite = cardList[dex].sprite;
                handCard1.id = cardList[dex].id;

                *//*

                if (handCard1.id == 1)

                {
                    ((ManaCard)handCard1).setManaType(((ManaCard)cardList[dex]).getManaType());
                }

                else if (handCard1.id != 1)

                {

                    handCard1.setCardSchool(cardList[dex].getCardSchool());

                }

                *//*
                handCard1.setCardSchool(cardList[dex].getCardSchool());
                break;
            case 1:
                handCard2.sprite = cardList[dex].sprite;
                handCard2.setCardSchool(cardList[dex].getCardSchool());
                handCard2.id = cardList[dex].id;
                break;
            case 2:
                handCard3.sprite = cardList[dex].sprite;
                handCard3.setCardSchool(cardList[dex].getCardSchool());
                handCard3.id = cardList[dex].id;
                break;
            case 3:
                handCard4.sprite = cardList[dex].sprite;
                handCard4.setCardSchool(cardList[dex].getCardSchool());
                handCard4.id = cardList[dex].id;
                break;
            case 4:
                handCard5.sprite = cardList[dex].sprite;
                handCard5.setCardSchool(cardList[dex].getCardSchool());
                handCard5.id = cardList[dex].id;
                break;
        }

        // System.out.println("Finished this method");

    }
*/
    // keep this for testing

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

    // THIS IS PSEUDOCODE WHICH NEEDS TO BE IMPLEMNENTED

    // There are a number of touch areas on screen.
    // When a touch area is touched the card in that area is made the current card.






    // calculates which player will get to to go first
// by returning a true or false to the  that called this method.
    // if a false is returned then the player will have to take that turn slot.
// 40111707

/*

    public boolean coinFlipForFirst(boolean playerChoice)
    {
        // creating a random number between 0 - 9
        // could be better maybe with 1 or 2

        int randomNumber = randInt(0,9);

            if (randomNumber >= 0 & randomNumber < 6)
            {
                if (playerChoice = true)
                {
                    return true;
                }

            }
           else  if (randomNumber > 5 & randomNumber < 11)
            {
                if (playerChoice = false)
                {
                    return true;
                }

            }
        return false;
    }
// method for creating a random number and then returning it



    public static int randInt(int min, int max) {

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    */
}
