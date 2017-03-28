package javalampstudos.kingofqueens;

import android.graphics.Matrix;
import android.util.DisplayMetrics;

/**
 * Created by 40083349 on 11/01/2017.
 */

// Local Imports

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.cardSchools;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.manaCard;
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

public class GameLoop implements Runnable

{
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


    // Variables for flipping images
    private Matrix matrix = new Matrix();

    // PUT TOUCH INPUT RECTS HERE
    // This could be moved to it's own class

    // Hand Slots - Must instantiate these
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

    //pause rect and bitmap
    private Rect pauseRect;
    private Bitmap pauseBitmap;

    // half the screen
    private Rect halfScreenRect;

    // Declare an instance of multi-touch listener
    protected MultitouchListener touchListener;

    // END TOUCH INPUT LOGIC


    // CARD GAME LOGIC

    public basicCard[] hand;
    public int handPos;


    public boolean playerTurn;

    // Declare all Sprites here - always include "Sprite" after the name of the image

    public Bitmap DataAdminSprite;
    public Bitmap HackerManSprite;
    public Bitmap GeoSprite;
    public Bitmap cardBackSprite;


    // DataAdmin
    public manaTypes[] attack1ManaRequiredDA;
    public manaTypes[] attack2ManaRequiredDA;

    // Hackerman

    public manaTypes[] attack1ManaRequiredHM;
    public manaTypes[] attack2ManaRequiredHM;

    // Random Logic

    public randomGenerator rand;
    public int randomIndex;

    // Variables relating to the players hand

    public int handCounter = 0;
    // keeps track of the current hand array position
    public int handPosition = 0;

    // AREAS OF THE BOARD - add using dedicated method

    // player variables
    public basicCard[] playerHand = new basicCard[10];
    public basicCard[] playerMonsterArea = new monsterCard[3];
    // Four for each monster card and one general mana
    public manaCard[] playerManaPool = new manaCard[13];
    // Adjust size accordingly
    public basicCard[] playerDiscardPile = new basicCard[40];

    // opponent variables
    public basicCard[] opponentHand = new basicCard[10];
    public  basicCard[] opponentMonsterArea = new monsterCard[3];
    // Four for each monster card and one general mana
    public manaCard[] opponentManaPool = new manaCard[13];
    // Adjust size accordingly
    public basicCard[] opponentDiscardPile = new basicCard[40];

    // increment this when a card is destroyed
    int cardsDestroyed = 0;

    // monitor the times drawn
    int numDraws = 0;

    // Monitor the size of both players decks
    int playerDeckSize = 40;
    int opponentDeckSize = 40;

    // Current card - temporarily stores the card you're working with
    public basicCard currentCard;
    public int touchCounter = 0;

    // Attack Logic Variables
    public monsterCard attacker;

    // The game needs to be able to access every possible card
    public basicCard[] cardList = new basicCard[40];

    // Declare all the monsters here
    public monsterCard DataAdmin;
    public monsterCard HackerMan;
    public monsterCard Geologist;

    // Declare all the mana cards here
    public manaCard EEECS;
    public manaCard BuiltEnvironment;
    public manaCard MedicalScience;
    public manaCard SocialSciences;
    public manaCard ArtsandHumanities;
    public manaCard AnyMana;
    public manaCard Engineering;

    // Declare all the buff/support cards here


    // Declare card backs here

    public basicCard graveYard;
    public basicCard deck;

    // These are temporary cards for checking positions

    // Hand Cards

    public basicCard handCard1;
    public basicCard handCard2;
    public basicCard handCard3;
    public basicCard handCard4;
    public basicCard handCard5;

    // Monster Cards

    public basicCard monsterCard1;
    public basicCard monsterCard2;
    public basicCard monsterCard3;


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
    public ArrayList<basicCard> monsterCards = new ArrayList<basicCard>();
    public ArrayList<manaCard> manaCards = new ArrayList<manaCard>();
    public ArrayList<supportCard> supportCards = new ArrayList<supportCard>();

    // These keep track of and draw the actual cards

    public ArrayList<basicCard> handCards = new ArrayList<>();
    public ArrayList<monsterCard> monstersInPlay = new ArrayList<>();

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

            NOTE:DREWBIE THE  IMPLEMENTATION BELOW IS CORRECT
        monsterCard geol = new monsterCard(20,350,90,120,GeoSprite,false,49,
                cardLevel.UNDERGRAD,140,70,cardSchools.EEECS,20,attack2ManaRequiredHM);*/
        // Slot 2

        // Slot 3

        // Mana

        // Positioning the hand - horizontal gaps are always 10

        // The first gap is slightly larger to make it obvious the deck is seperate
        handCard1 = new basicCard(194, 410, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
                49);

        // The rest of the gaps should be either 10 or 20

        handCard2 = new basicCard(294, 410, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
                49);
        handCard3 = new basicCard(394, 410, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
                49);
        handCard4 = new basicCard(494, 410, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
                49);
        handCard5 = new basicCard(594, 410, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
                49);

        // Positioning the monsters

        monsterCard1 = new basicCard(194, 280, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
                49);
        monsterCard2 = new basicCard(394, 280, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
                49);
        monsterCard3 = new basicCard(594, 280, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
                49);

        // Hand

        // Draw the graveyard pile
        graveYard = new basicCard(754, 280, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
            49);

        // y co-ordinate is a gap of 50 plus half the card size

        deck = new basicCard(754, 410, 90, 120, cardBackSprite, cardSchools.MEDICS, false,
                49);

        rand = new randomGenerator();

        // Initialize hand stuff
        hand = new basicCard[5];
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
                        updateTouch ();
                        updateCard();
                        updateMonsterCards();
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




                if (movementRect.contains((int) x, (int) y))

                    {
                        System.out.println("Touch detected");

                        // This is needed for all methods
                        Geologist.setPointerID(i);

                        Geologist.x = x;
                        Geologist.y = y;

                    }

                 // the graveyard can destroy cards
                 if (graveYardRect.contains((int) Geologist.x , (int) Geologist.y))

                    {
                      System.out.println("Ok");
                      Geologist.destroyed = true;

                    }

                 // testing other touch input rects

                 if (handRect1.contains((int) x, (int) y))

                 {
                    System.out.println("HandRect1 was touched");


                 }


                /*

                // Monster Card Placement Logic

                if (handRect1.contains((int) x, (int) y))

                {
                  // make the current object the one currently at hand position 1
                  currentCard = hand[0];
                  // Now the user can drag the card
                  currentCard.x = x;
                  currentCard.y = y;

                }

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


        // Hand Rects

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
    }


    private void doThingy ()

    {
        // arguments: Sound ID, left volume, right volume, priority, loop, rate
        // There are known problems with this
        // test.play(0, 0.5f, 0.5f, 1, 0, 1.0f);



        System.out.println("Touch input was received and now thingy is being done");
        // int x = generateRandomNumber();
        // System.out.println(x);

        // testing soundfx
        // test.playSoundFX();

        // Add the dataadmin to the array
        monsterCards.add(DataAdmin);

        // Test another monster card
        monsterCards.add(HackerMan);


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

    // This method should be altered.

    private void updateCard ()

    {
        Geologist.update();

        // Get all the placeholders drawn

        // Hands

        // As long as the card exists update information must be relayed back
        handCard1.update();
        handCard2.update();
        handCard3.update();
        handCard4.update();
        handCard5.update();

        // Monsters

        monsterCard1.update();
        monsterCard2.update();
        monsterCard3.update();

        // Graveyard and deck updates

        graveYard.update();
        deck.update();

    }

    private void loadAssets ()

    {
        AssetManager assetManager = fragment.getActivity().getAssets();

        // load in individual assets
        DataAdminSprite = AssetLoader.loadBitmap(assetManager, "img/Matthew/SmallDataAdmin.png");
        HackerManSprite = AssetLoader.loadBitmap(assetManager, "img/Matthew/HackermanSmall.png");

        GeoSprite = AssetLoader.loadBitmap(assetManager, "img/Matthew/SmallGeo.png");

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
        attack1ManaRequiredDA = new manaTypes[] { manaTypes.EEECS_MANA, manaTypes.GENERIC_MANA };
        attack2ManaRequiredDA = new manaTypes[] { manaTypes.EEECS_MANA, manaTypes.EEECS_MANA, manaTypes.EEECS_MANA };

        // Hackerman
        attack2ManaRequiredHM = new manaTypes[] { manaTypes.EEECS_MANA, manaTypes.GENERIC_MANA };
        attack2ManaRequiredHM = new manaTypes[] { manaTypes.EEECS_MANA, manaTypes.EEECS_MANA, manaTypes.EEECS_MANA, manaTypes.EEECS_MANA };

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

    private void selectAttack (basicCard currentCard)

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
        basicCard temp = cardList[randomIndex];

        // Depending on which card you drew
        // Updates these using the positions worked out

        switch (index)

        {
            case 1:
                temp.x = 4;
                temp.y = 3;
            case 2:
                temp.x = 10;
                temp.y = 6;
            case 3:
                temp.x = 14;
                temp.y = 12;
            case 4:
                temp.x = 13;
                temp.x = 12;
            case 5:
                temp.x = 54;
                temp.y = 15;

        }

        handCards.add(temp);

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
