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
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.boardLayout;
import javalampstudos.kingofqueens.kingOfQueens.util.randomGenerator;

// Android Imports

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.content.res.AssetManager;
import android.util.Log;
import android.content.Context;

// Java Imports

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

public class GameLoop implements Runnable

{

    // delcaring Brians AiBrain
   public Brain aiBrain;


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

    public int handIndex;



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

    // the whole screen
    private Rect movementRect;

    private Rect playerMovementRect;

    //pause rect and bitmap
    private Rect pauseRect;
    private Bitmap pauseBitmap;

    // half the screen
    private Rect halfScreenRect;

    // Declare an instance of multi-touch listener
    protected MultitouchListener touchListener;

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

    public boolean handRect1Active = true;
    public boolean handRect2Active = true;
    public boolean handRect3Active = true;
    public boolean handRect4Active = true;
    public boolean handRect5Active = true;


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

    public BasicCard graveYard;
    public BasicCard deck;

    // These are temporary cards for checking positions

    // hand Cards

    public BasicCard handCard1;
    public BasicCard handCard2;
    public BasicCard handCard3;
    public BasicCard handCard4;
    public BasicCard handCard5;

    // Monster Cards

    public BasicCard monsterCard1;
    public BasicCard monsterCard2;
    public BasicCard monsterCard3;


    // booleans
    public boolean addToHand;

    // replaced with deckClicked
    public boolean resetDeck = false;

    // booleans for each state - keep things ordered (probably delete this)
    public boolean draw = true;
    public boolean placement = true;
    public boolean strat = true;
    public boolean attack = true;

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
    public ArrayList<SupportCard> supportCards = new ArrayList<SupportCard>();

    // These keep track of and draw the actual cards

    public ArrayList<BasicCard> handCards = new ArrayList<>();
    public ArrayList<MonsterCard> monstersInPlay = new ArrayList<>();

    // Opponent card variables
    public MonsterCard opponent1;
    public MonsterCard opponent2;
    public MonsterCard opponent3;

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

        // EXAMPLE CARDS TO TEST

        // 90,120 - standard card width and height

        // Use these for the touch rects as well


        // Draw monster cards in each of the 3 slots

        // Slot 1

     /*   Geologist = new monsterCard(20, 350, 90, 120, GeoSprite, cardSchools.EEECS, false, 49, cardLevel.UNDERGRAD, 140,0,
                cardSchools.MEDICS, "Hack", 20, attack2ManaRequiredHM);

        */
        // ANDREW: the code below was causing an erro because of monstercards constructor. it needs to be given a hashman with the right values.
        HashMap<ManaTypes,Integer> requiredMana = new HashMap<ManaTypes,Integer>();
        requiredMana.put(ManaTypes.BUILT_ENVIRONMENT_MANA,5);
        // Geologist = new MonsterCard(20, 350, 90, 120, GeoSprite, CardSchools.EEECS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,requiredMana);

        Geologist = new MonsterCard(20, 350, 90, 120, GeoSprite, true, CardSchools.EEECS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,10, requiredMana);
        DataAdmin = new MonsterCard(0, 0, 0, 0, DataAdminSprite, true, CardSchools.EEECS, false, 49, CardLevel.GRAD, 140, 0, 2,16, requiredMana);
        HackerMan = new MonsterCard(0, 0, 0, 0, HackerManSprite, true, CardSchools.MEDICS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,16, requiredMana);
        Psychologist = new MonsterCard(0, 0, 0, 0, PsychologistSprite, true, CardSchools.MEDICS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,12, requiredMana);
        Sociologist = new MonsterCard(0, 0, 0, 0, SociologistSprite, true, CardSchools.MEDICS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,4, requiredMana);

        // Load the created cards into the cardList array
        // cardList[0] = Geologist;
        cardList[0] = DataAdmin;
        cardList[1] = HackerMan;
        cardList[2] = Psychologist;
        cardList[3] = Sociologist;
        cardList[4] = Geologist;

        // Opponent card
        opponentCard1 = new MonsterCard(194, 100, 90, 120, cardBackSprite, false, CardSchools.EEECS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,1, requiredMana);
        opponentCard2 = new MonsterCard(394, 100, 90, 120, cardBackSprite, false, CardSchools.EEECS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,4, requiredMana);
        opponentCard3 = new MonsterCard(594, 100, 90, 120, cardBackSprite, false, CardSchools.EEECS, false, 49, CardLevel.DOCTRATE, 140, 0, 3,8, requiredMana);



      /*

            NOTE:DREWBIE THE  IMPLEMENTATION BELOW IS CORRECT
        MonsterCard geol = new MonsterCard(20,350,90,120,GeoSprite,false,49,
                CardLevel.UNDERGRAD,140,70,CardSchools.EEECS,20,attack2ManaRequiredHM);
        // Slot 2

        // Slot 3

        // Mana


          */
        // Positioning the hand - horizontal gaps are always 10

        // The first gap is slightly larger to make it obvious the deck is seperate




        handCard1 = new BasicCard(194, 410, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
                49);

        // The rest of the gaps should be either 10 or 20

        handCard2 = new BasicCard(294, 410, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
                49);
        handCard3 = new BasicCard(394, 410, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
                49);
        handCard4 = new BasicCard(494, 410, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
                49);
        handCard5 = new BasicCard(594, 410, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
                49);

        // Put the hand cards in the array

        handCards.add(handCard1);
        handCards.add(handCard2);
        handCards.add(handCard3);
        handCards.add(handCard4);
        handCards.add(handCard5);

        // Positioning the monsters

        monsterCard1 = new BasicCard(194, 280, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
                49);
        monsterCard2 = new BasicCard(394, 280, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
                49);
        monsterCard3 = new BasicCard(594, 280, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
                49);

        // hand

        // Draw the graveyard pile
        graveYard = new BasicCard(754, 280, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
            49);

        // y co-ordinate is a gap of 50 plus half the card size

        deck = new BasicCard(754, 410, 90, 120, cardBackSprite, true, CardSchools.MEDICS, false,
                49);

        rand = new randomGenerator();


        // initialzing AiBrain.
        aiBrain = new Brain();
        // Initialize hand stuff
        hand = new BasicCard[5];
        handPos = 0;

    }


    // calculates rects based on the fields of the cards
    public void calculateRect (int x, int y, int w, int h)

    {



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




                /*

                if (movementRect.contains((int) x, (int) y))

                    {
                        System.out.println("Touch detected");

                        // This is needed for all methods
                        Geologist.setPointerID(i);

                        Geologist.x = x;
                        Geologist.y = y;

                    }

                 */

                 // the graveyard can destroy cards
                 if (graveYardRect.contains((int) Geologist.x , (int) Geologist.y))

                    {
                      System.out.println("Ok");
                      Geologist.destroyed = true;

                    }

                    if (deckRect.contains((int) x, (int) y) && deckCompleted == false)

                    {
                        populateHand();
                        System.out.println("Completed");

                    }

                // cards can be moved anywhere in the players half of the screen
                if(playerMovementRect.contains((int) x, (int) y) && dragActive)

                {
                   // how do you know which card this effects
                   // set the index of the array to talk to
                   handCards.get(handIndex).x = x;
                   handCards.get(handIndex).y = y;

                }

                if (handRect1.contains((int) x, (int) y) && handRect1Active == true)

                {
                   dragActive = true;
                   handIndex = 0;
                   handRect1Active = false;

                }


                    if (handRect2.contains((int) x, (int) y) && handRect2Active == true)

                    {
                        dragActive = true;
                        handIndex = 1;
                        handRect2Active = false;

                    }

                    if (handRect3.contains((int) x, (int) y) && handRect3Active == true)

                    {
                        dragActive = true;
                        handIndex = 2;
                        handRect3Active = false;

                    }

                    if (handRect4.contains((int) x, (int) y) && handRect4Active == true)

                    {
                        dragActive = true;
                        handIndex = 3;
                        handRect4Active = false;

                    }

                    if (handRect5.contains((int) x, (int) y) && handRect5Active == true)

                    {
                        dragActive = true;
                        handIndex = 4;
                        handRect5Active = false;

                    }

                    // Monster Slot Detection


                    if (MSlot1Rect.contains((int) x, (int) y))

                {
                   handCards.get(handIndex).x = 194;
                   handCards.get(handIndex).y = 280;
                   dragActive = false;


                }

                    if (MSlot2Rect.contains((int) x, (int) y))

                    {
                        handCards.get(handIndex).x = 394;
                        handCards.get(handIndex).y = 280;
                        dragActive = false;


                    }

                    if (MSlot3Rect.contains((int) x, (int) y))

                    {
                        handCards.get(handIndex).x = 594;
                        handCards.get(handIndex).y = 280;
                        dragActive = false;


                    }








                    // Monster Card Placement Logic
                // the hand must be populated



                /*

                    if (handRect2.contains((int) x, (int) y))

                    {
                        // make the current object the one currently at hand position 1
                        currentCard = hand[1];
                        // Now the user can drag the card
                        currentCard.x = x;
                        currentCard.y = y;

                    }

                    if (handRect3.contains((int) x, (int) y))

                    {
                        // make the current object the one currently at hand position 1
                        currentCard = hand[2];
                        // Now the user can drag the card
                        currentCard.x = x;
                        currentCard.y = y;


                    }

                    if (handRect4.contains((int) x, (int) y))

                    {
                        // make the current object the one currently at hand position 1
                        currentCard = hand[3];
                        // Now the user can drag the card
                        currentCard.x = x;
                        currentCard.y = y;


                    }

                    if (handRect5.contains((int) x, (int) y))

                    {
                        // make the current object the one currently at hand position 1
                        currentCard = hand[4];
                        // Now the user can drag the card
                        currentCard.x = x;
                        currentCard.y = y;


                    }

                    // Deal with monster card placement

                    if (MSlot1Rect.contains((int) currentCard.x , (int) currentCard.y) && placement == true)

                    {
                       // the current card knows snaps to the position of the rect
                       currentCard.x = 40;
                       currentCard.y = 32;
                       // add the card to an array containg the mosnters on the field


                    }

                    if (MSlot2Rect.contains((int) currentCard.x , (int) currentCard.y) && placement == true)

                    {
                        // the current card knows snaps to the position of the rect
                        currentCard.x = 40;
                        currentCard.y = 32;

                    }

                    if (MSlot3Rect.contains((int) currentCard.x , (int) currentCard.y) && placement == true)

                    {
                        // the current card knows snaps to the position of the rect
                        currentCard.x = 40;
                        currentCard.y = 32;

                    }

                    // For the attack phase the monster slot becomes the point from which to drag


               */


                }



                // Snapping goes here

                else

                {

                  if (Geologist.pointerID == i)

                  {

                      System.out.println("k");

                      Geologist.x = 30;
                      Geologist.y = 350;

                  }



                }

                // Single touch stuff - fires once and then stops




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
                (int) (194 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (194 + (90 / 2)),
                (int) (410 + (120 / 2)));

        handRect2 = new Rect(
                (int) (294 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (294 + (90 / 2)),
                (int) (410 + (120 / 2)));

        handRect3 = new Rect(
                (int) (394 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (394 + (90 / 2)),
                (int) (410 + (120 / 2)));

        handRect4 = new Rect(
                (int) (494 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (494 + (90 / 2)),
                (int) (410 + (120 / 2)));

        handRect5 = new Rect(
                (int) (594 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (594 + (90 / 2)),
                (int) (410 + (120 / 2)));


        // Monster Slot Rects

        MSlot1Rect = new Rect(
                (int) (194 - (90 / 2)),
                (int) (280 - (120 / 2)),
                (int) (194 + (90 / 2)),
                (int) (280 + (120 / 2)));

        MSlot2Rect = new Rect(
                (int) (394 - (90 / 2)),
                (int) (280 - (120 / 2)),
                (int) (394 + (90 / 2)),
                (int) (280 + (120 / 2)));

        MSlot3Rect = new Rect(
                (int) (594 - (90 / 2)),
                (int) (280 - (120 / 2)),
                (int) (594 + (90 / 2)),
                (int) (280 + (120 / 2)));



        // MSlot1Rect = new Rect etc.
        // MSlot2Rect = new Rect etc.
        // MSlot3Rect = new Rect etc.

        // graveyard and deck rects
        graveYardRect = new Rect(
                (int) (754 - (90 / 2)),
                (int) (280 - (120 / 2)),
                (int) (754 + (90 / 2)),
                (int) (280 + (120 / 2)));

        deckRect = new Rect(
                (int) (754 - (90 / 2)),
                (int) (410 - (120 / 2)),
                (int) (754 + (90 / 2)),
                (int) (410 + (120 / 2)));

        // move all rect initialization here
        gameBoard.initializeRects();

        // Set up values for each menu rect
        pauseRect = new Rect((int) (85),
                (int) (410),
                (int) (285),
                (int) (510));

        // playerArea
        playerMovementRect = new Rect((int) 0, (int) (GameLoop.height / 2), (int) GameLoop.width, (int) GameLoop.height);

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

    private void updateHand ()

    {

        for (int i = 0; i < handCards.size(); i++)

        {
            handCards.get(i).update();

        }



    }

    // This method should be altered.

    private void updateCard ()

    {
        monsterCard1.update();
        monsterCard2.update();
        monsterCard3.update();

        // Graveyard and deck updates

        graveYard.update();
        deck.update();

        // opponent card
        opponentCard1.update();
        opponentCard2.update();
        opponentCard3.update();


    }

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
      for (int i = 0; i < 5; i++)

      {
          drawFromDeck(i);

      }


        System.out.println("Hand populated");

        deckCompleted = true;


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

    private void drawFromDeck (int index)

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
                break;
            case 1:
                handCard2.sprite = cardList[dex].sprite;
                break;
            case 2:
                handCard3.sprite = cardList[dex].sprite;
                break;
            case 3:
                handCard4.sprite = cardList[dex].sprite;
                break;
            case 4:
                handCard5.sprite = cardList[dex].sprite;
                break;
        }



        System.out.println("Finished this method");


    }

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
