package javalampstudos.kingofqueens;

import android.util.DisplayMetrics;

/**
 * Created by 40083349 on 11/01/2017.
 */

// Local Imports

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasRenderer;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.input.MultitouchListener;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.*;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.engine.SFX.SoundFX;
import javalampstudos.kingofqueens.MainActivity;
import javalampstudos.kingofqueens.GameViewFragment;
import javalampstudos.kingofqueens.kingOfQueens.util.Random;

// Android Imports

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.util.Log;
import android.content.Context;

// Java Imports

import java.io.IOException;
// import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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

    // Declare a gamestate
    public GameState gameState;

    // PUT TOUCH INPUT RECTS HERE

    // Monster Slots

    public Rect MSlot1Rect;
    public Rect MSlot2Rect;
    public Rect MSlot3Rect;

    // graveyard and deck rects
    private Rect graveYardRect;
    private Rect deckRect;

    // the whole screen
    private Rect movementRect;

    // half the screen
    private Rect halfScreenRect;

    // Declare an instance of multi-touch listener
    protected MultitouchListener touchListener;

    // CARD GAME LOGIC

    public boolean playerTurn;

    // Declare all Sprites here - always include "Sprite" after the name of the image

    public Bitmap DataAdminSprite;
    public Bitmap HackerManSprite;
    public Bitmap GeoSprite;
    public Bitmap cardBackSprite;


    // DataAdmin
    public ManaTypes [] attack1ManaRequiredDA;
    public ManaTypes [] attack2ManaRequiredDA;

    // Hackerman

    public ManaTypes [] attack1ManaRequiredHM;
    public ManaTypes [] attack2ManaRequiredHM;

    // Random Logic

    public Random random;

    // player
    public int [] playerHasAppeared = new int [40];
    public int playerhCounter = 0;

    // opponent
    public int [] opponentHasAppeared = new int [40];
    public int opponenthCounter = 0;


    // Variables relating to the players hand

    public int handCounter = 0;
    // keeps track of the current hand array position
    public int handPosition = 0;

    // AREAS OF THE BOARD - add using dedicated method

    // player variables
    public BasicCard [] playerHand = new BasicCard [10];
    public BasicCard [] playerMonsterArea = new MonsterCard [3];
    // Four for each monster card and one general mana
    public ManaCard [] playerManaPool = new ManaCard [13];
    // Adjust size accordingly
    public BasicCard [] playerDiscardPile = new BasicCard [40];

    // opponent variables
    public BasicCard [] opponentHand = new BasicCard [10];
    public  BasicCard [] opponentMonsterArea = new MonsterCard [3];
    // Four for each monster card and one general mana
    public ManaCard [] opponentManaPool = new ManaCard [13];
    // Adjust size accordingly
    public BasicCard [] opponentDiscardPile = new BasicCard [40];

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

    // The game needs to be able to access every possible card
    public BasicCard [] cardList = new BasicCard [40];

    // Declare all the monsters here
    public MonsterCard DataAdmin;
    public MonsterCard HackerMan;
    public MonsterCard Geologist;

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

    // booleans
    public boolean addToHand;

    // replaced with deckClicked
    public boolean resetDeck = false;

    // booleans for each state - keep things ordered (probably delete this)
    public boolean draw = true;
    public boolean mana = true;
    public boolean strat = true;
    public boolean attack = true;

    // "game state" variables - would need proper game state management in Android
    public boolean prepPhase = true;

    public boolean deckInitialized = false;

    // counters
    public int monstersInPlay = 0;

    // deck booleans
    public boolean deckClicked = false;



    // Random number value for brians method
    private static Random rand;
    // track the position in the discardPile array
    int discardCounter = 0;

    // the player has chosen a card from their hand
    public boolean handCardSelected = false;

    // ARRAY LISTS OF CARDS
    public ArrayList<BasicCard> monsterCards = new ArrayList<BasicCard>();
    public ArrayList<ManaCard> manaCards = new ArrayList<ManaCard>();
    public ArrayList<SupportCard> supportCards = new ArrayList<SupportCard>();

    // temporary rect variables

    private int rectLX;
    private int rectLY;
    private int rectRX;
    private int rectRY;


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
        // Getting UI scaling from display metrics
        DisplayMetrics metrics = new DisplayMetrics();
        fragment.getActivity().getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        uiScaling = metrics.density;

        // set the gamestate to new intially - in the finished version will depend on the presence of a save file
        gameState = GameState.NEW;

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

        Geologist = new MonsterCard(20, 350, 90, 200, GeoSprite,GeoSprite, false, 49, "Geologist", "description", CardLevel.UNDERGRAD, 140,0,CardSchools.EEECS, CardSchools.ARTS_HUMANITIES,
                CardSchools.MEDICS, "Hack", 20, attack2ManaRequiredHM, "Error 404", 50, attack2ManaRequiredHM);

        // Slot 2

        // Slot 3

        // Mana

        // Hand

        // Draw the graveyard pile
        graveYard = new BasicCard(754, 280, 90, 120, cardBackSprite, GeoSprite, "graveYard", "description", CardSchools.MEDICS, false,
            49);




        // y co-ordinate is a gap of 50 plus half the card size

        deck = new BasicCard(754, 410, 90, 120, cardBackSprite, GeoSprite, "deck", "description", CardSchools.MEDICS, false,
                49);

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

                // set up more rects here for each card

                // These are the slots for each monster card


                }

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



            break; // end NEW case
        }

    }

    // position the player on the screen
    // make rects for touch input

    private void gameSetup ()

    {
        // Instantiate new rects here

        movementRect = new Rect ( 0, 0, GameLoop.width, GameLoop.height);


        // MSlot1Rect = new Rect etc.
        // MSlot2Rect = new Rect etc.
        // MSlot3Rect = new Rect etc.

        // graveyard and deck rects
        graveYardRect = new Rect(
                (int) (754 - (90 / 2)),
                (int) (280 - (120 / 2)),
                (int) (754 + (90 / 2)),
                (int) (280 + (120 / 2)));

        // deckRect = new Rect(754, 410, 90, 120);

        // halfScreenRect = new Rect(0, 0, GameLoop.width / 2, GameLoop.height / 2);

        initializeDeck();

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
        attack1ManaRequiredDA = new ManaTypes [] { ManaTypes.EEECS_MANA, ManaTypes.GENERIC_MANA };
        attack2ManaRequiredDA = new ManaTypes [] { ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA };

        // Hackerman
        attack2ManaRequiredHM = new ManaTypes [] { ManaTypes.EEECS_MANA, ManaTypes.GENERIC_MANA };
        attack2ManaRequiredHM = new ManaTypes [] { ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA, ManaTypes.EEECS_MANA };

        // Declare Mana Cards here

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





    // keep this for testing

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
