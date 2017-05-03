package javalampstudos.kingofqueens;

import android.content.Intent;
import android.graphics.Matrix;
import android.media.MediaPlayer;
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
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardLevel;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.ManaCounter;
import javalampstudos.kingofqueens.kingOfQueens.util.GameTimer;
import javalampstudos.kingofqueens.kingOfQueens.util.randomGenerator;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.boardLayout;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;
import javalampstudos.kingofqueens.kingOfQueens.Menu.MainMenuFragment;
import javalampstudos.kingofqueens.kingOfQueens.AiEngine.Window;
import javalampstudos.kingofqueens.kingOfQueens.util.andyManaCounter;

// Android Imports

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.content.res.AssetManager;

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

    // Define the possible game states
    public enum GameState {

        NEW, PAUSED,
        // Non-player states
        PROMPT, AITURN,
        // Turn Structure
        DRAW, MANAPLACEMENT, MONSTERPLACEMENT, ATTACK

    }

    // Allow the prep phase to happen
    public boolean prepPhase;

    // Declare a gamestate
    public GameState gameState;

    // TOUCH INPUT/DRAW LOGIC

    // These remember the index of the last touch for the two zones - monster and hand
    public int handIndex;
    public int monsterIndex;

    // Declare an instance of multi-touch listener
    protected MultitouchListener touchListener;

    // Don't allow dragging till permitted
    public boolean dragActive = false;

    // Turn the manaZone off
    public boolean manaflag = false;

    public boolean handActive = true;
    public boolean monsterSlotActive = false;

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

    public Deck playerDeck = new Deck();
    public Deck aiDeck = new Deck();

    // players logic arrays
    public ArrayList<MonsterCard> playerHandMonsters = new ArrayList<>();
    public ArrayList<ManaCard> playerHandMana = new ArrayList<>();

    // the players monsters on the field
    public ArrayList<MonsterCard> playerFieldMonsters = new ArrayList<>();

    // AI logic arrays

    public ArrayList<MonsterCard> aiHandMonsters = new ArrayList<>();
    public ArrayList<ManaCard> aiHandMana = new ArrayList<>();

    // the players monsters on the field
    public ArrayList<MonsterCard> aiFieldMonsters = new ArrayList<>();

    // ANIMATION

    // When you want to move a card this should become true
    public boolean animationNeeded = false;

    // boundary conditions for the test card
    public boolean boundHit;

    public int bound;

    public BasicCard cardToAnimate;

    // OTHER CLASSES
    // This will replace individual rect declarations
    public boardLayout gameBoard;

    // Random Logic

    public randomGenerator rand;
    // Used for drawing cards and populating the hand
    public int randex;

    // MANA

    // Mana Bitmap
    public Bitmap manaZoneSprite;

    // Mana GameObject for interaction
    public BasicCard manaZone;

    // Mana Counters for each type
    andyManaCounter engineering;
    andyManaCounter artsAndHumanities;
    andyManaCounter builtEnvironment;
    andyManaCounter eeecs;
    andyManaCounter Medic;

    // AI

    // delcaring Brians AiBrain
    public Brain aiBrain;

    public Bitmap aiThinking;
    public Window window;

    // true when the window is moving down
    public boolean windowDown = true;

    // SFX
    // private SoundFX test;

    // REDUNDANT

    // Not necessary
    /*
    public PlaySpace playerPlaySpace;
    public PlaySpace aiPlaySpace;
    */

    // MISC

    public BasicCard[] hand;

    // Declare card backs here - Possibly move to the JSON parser

    public BasicCard graveYard;
    public BasicCard deck;

    // Created by Andrew - 40083349

    // SPLIT THIS OUT

    public GameTimer timer = new GameTimer();

    public GameLoop (CanvasFragment fragment, int width, int height)

    {
        targetStepPeriod = 1000000000 / 60;

        canvasRenderer = new CanvasRenderer(fragment.getActivity(), fragment);

        this.fragment = fragment;

        // Set up dimensions and scaling
        GameLoop.width = width;
        GameLoop.height = height;

        gameScaling = width / 320.0f;

        // Getting UI scaling from display metrics
        DisplayMetrics metrics = new DisplayMetrics();
        fragment.getActivity().getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        uiScaling = metrics.density;

        // set the game state to new initially
        // Once the board is set up you can move to the default game state
        gameState = GameState.NEW;

        // Now all the rects exist
        gameBoard = new boardLayout(width, height, uiScaling);

        // input stuff
        touchListener = new MultitouchListener();
        canvasRenderer.setOnTouchListener(touchListener);

        // THESE CARDS ARE PLACEHOLDERS FOR BITMAPS - THIS SHOULD BE IN ANOTHER METHOD

        // Load the cardBackSprite

        AssetManager assetManager = fragment.getActivity().getAssets();

        aiThinking = AssetLoader.loadBitmap(assetManager, "img/AiThinking3.png");

        // This is the ai message
        window = new Window(477, 240, 740, 240, aiThinking, true);

        cardBackSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Cardback.png");
        manaZoneSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/ManaZoner.png");

        // Hand - Accessible individually and in their array positions

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

        monstersInPlay.add(monsterCard1);
        monstersInPlay.add(monsterCard2);
        monstersInPlay.add(monsterCard3);

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

        // initialzing AiBrain.
        aiBrain = new Brain();

        // populate logical arrays
        initializeHandMonsters();
        initializeHandMana();
        intializeFieldMonsters();

        System.out.println("Hand Monsters is " + playerHandMonsters.size());

        playerDeck.createDeck(this);
        System.out.println("playerDeck monsters " + playerDeck.monsterArray.size());
        System.out.println("playerDeck mana" + playerDeck.manaArray.size());

        // this only affects basic card at the moment
        populatePlayerHand ();

        rand.flushRandomLogic();

        aiDeck.createDeck(this);
        System.out.println("playerDeck monsters " + playerDeck.monsterArray.size());
        System.out.println("playerDeck mana" + playerDeck.manaArray.size());

        rand.flushRandomLogic();

        populateOpponentHand();

        System.out.println("Opponent hand populated");

        engineering = new andyManaCounter(100, 250, "0");
        artsAndHumanities = new andyManaCounter(100, 290 , "0");
        builtEnvironment = new andyManaCounter(100, 335, "0");
        eeecs = new andyManaCounter(100, 380, "0");
        Medic = new andyManaCounter(100, 430, "0");

        // Once everything is loaded the user can interact
        handActive = true;

        // The prep phase becomes active here
        prepPhase = true;

        // Starts game timer
        timer.start();


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

                // Split this into smaller game states to simplify touch input logic
                switch (gameState)

                {
                    case NEW:

                        newGame();
                        break;
                    case PROMPT:
                        updateCard();
                        updateWindow();
                        updatePrompt();
                        break;
                    case PAUSED:
                        updateTouch ();
                        break;
                    case AITURN:
                        updateCard();
                        updateAICards();
                        break;
                    // automated so no updateTouch
                    case DRAW:
                        updateCard();
                        updateDraw();
                        break;
                    case MANAPLACEMENT:
                        updateCard();
                        // not really necessary
                        updateMana();
                        updateTouch();
                        break;
                    case MONSTERPLACEMENT:
                        updateCard();
                        updateTouch();
                        break;
                    case ATTACK:
                        updateCard();
                        updateTouch();
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
        if(gameState != GameState.PAUSED) {
            pauseGame();

            timer.stop();
        }

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
        // Do all the setup in here

        gameBoard = new boardLayout(width, height, uiScaling);

        // Hands and deck are set up so begin the prep phase
        gameState = GameState.MONSTERPLACEMENT;
    }

    // display the ai window briefly then move back to normal gameplay
    // tracking variables

    private void updateMana ()

    {
        engineering.update();
        artsAndHumanities.update();
        builtEnvironment.update();
        eeecs.update();
        Medic.update();
    }

    // THIS IS TO MAKE SURE THE ARRAY LISTS AREN'T EMPTY

    private void initializeHandMonsters()

    {

        HashMap<ManaTypes,Integer> requiredMana = new HashMap<ManaTypes,Integer>();
        requiredMana.put(ManaTypes.BUILT_ENVIRONMENT_MANA,5);

        for (int i = 0; i < 5; i++)

        {
            playerHandMonsters.add(new MonsterCard(234, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                    49, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana));
        }

    }

    private void initializeHandMana()

    {
        for (int i = 0; i < 5; i++)

        {
            playerHandMana.add(new ManaCard(0, 0, 90, 120, cardBackSprite, true, 2,
                    ManaTypes.SOCIAL_SCIENCES_MANA, CardSchools.SOCIAL_SCIENCES, false, 49 ));

        }

    }

    private void intializeFieldMonsters ()

    {
        HashMap<ManaTypes,Integer> requiredMana = new HashMap<ManaTypes,Integer>();
        requiredMana.put(ManaTypes.BUILT_ENVIRONMENT_MANA,5);

        for (int i = 0; i < 3; i++)

        {

            playerFieldMonsters.add(new MonsterCard(234, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                    49, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana));

        }

        for (int i = 0; i < 3; i++)

        {

            aiFieldMonsters.add(new MonsterCard(234, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                    49, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana));

        }

    }

    private void updatePrompt ()

    {
        // move the prompt up and down
        // set bounds for the prompt
        // speed = 0.1
        if(windowDown) {

            window.y += 0.5;
            if(window.y >= 270)
                windowDown = false;

            // start moving the window up
        } else {
            window.y -= 0.5;
            if(window.y <= 210) {
                // animation completed return to the card game
                gameState = GameState.AITURN;
                // reset the prompt window
                window.x = 477;
                window.y = 240;
                windowDown = true;
            }
        }

    }

    private void updateWindow ()

    {
        window.update();

    }

    // Could apply similar logic here
    private void updateAnimation ()

    {
        // could get rid of this
        if (animationNeeded)

        {
            // if the bound hasn't been hit yet keep incrementing the x position once per frame
            if (!boundHit) {
                handCard5.x -= 2.0;

                if (handCard5.x <= bound) {
                    boundHit = true;
                    animationNeeded = false;
                }

            }

        }

    }

    // Created by Andrew - 40083349
    private void updateTouch ()

    {
        switch (gameState)

        {
            case MANAPLACEMENT:

                for(int i = 0; i < touchListener.MAX_TOUCH_POINTS; i++) {
                    if (touchListener.isTouchContinuous(i)) {
                        int x = (int) touchListener.getTouchX(i), y = (int) touchListener
                                .getTouchY(i);

                        if(boardLayout.pauseRect.contains((int) x, (int) y)) {
                            pauseGame();
                        }

                        if (boardLayout.handRect1.contains((int) x, (int) y) && handActive)

                        {
                            // Allow the card chosen to be dragged
                            dragActive = true;
                            // The first card becomes the current card
                            handIndex = 0;
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;
                            // No more hand cards can be picked up
                            handActive = false;

                        }


                        if (boardLayout.handRect2.contains((int) x, (int) y) && handActive )

                        {
                            dragActive = true;
                            handIndex = 1;

                            handActive = false;

                        }

                        if (boardLayout.handRect3.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 2;
                            handActive = false;

                        }

                        if (boardLayout.handRect4.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 3;
                            handActive = false;

                        }

                        if (boardLayout.handRect5.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 4;
                            handActive = false;

                        }

                        if(boardLayout.playerMovementRect.contains((int) x, (int) y) && dragActive)

                        {
                            // how do you know which card this effects
                            // set the index of the array to talk to
                            handCards.get(handIndex).x = x;
                            handCards.get(handIndex).y = y;

                        }

                        // Only accept mana cards
                        if (boardLayout.manaRect.contains((int) handCards.get(handIndex).x, (int) handCards.get(handIndex).y)
                                && handCards.get(handIndex).id == 1 && manaflag)

                        {
                            // no more dragging now the mana has been placed
                            dragActive = false;

                            // Work out which manaCounter object to update
                            switch (handCards.get(handIndex).getCardSchool())

                            {
                                case ARTS_HUMANITIES:
                                    artsAndHumanities.incrementCounter();
                                    break;
                                case ENGINEERING:
                                    engineering.incrementCounter();
                                    break;
                                case BUILT_ENVIORNMENT:
                                    builtEnvironment.incrementCounter();
                                    break;
                                case EEECS:
                                    eeecs.incrementCounter();
                                    break;
                                case MEDICS:
                                    Medic.incrementCounter();
                                    break;
                                // Deal with generic mana
                                /*
                                case
                                    manaTest.addMana(ManaTypes.GENERIC_MANA);
                                    break;
                                */
                            }
                            // get the current card off the screen
                            handCards.get(handIndex).destroyed = true;

                            // move to the monster placement phase
                            gameState = GameState.MONSTERPLACEMENT;

                            manaflag = false;
                            // allow movement of hand cards again
                            handActive = true;
                        }
                    }

                    else

                    {
                        // how do you control which one goes where
                        if (handCards.get(handIndex).pointerID == i)

                        {
                            // Need to work out the right position from the hand index
                            handCards.get(handIndex).x = 234;
                            handCards.get(handIndex).y = 410;

                        }


                    }
                }

            case MONSTERPLACEMENT:

                for(int i = 0; i < touchListener.MAX_TOUCH_POINTS; i++) {
                    if(touchListener.isTouchContinuous(i)) {
                        int x = (int) touchListener.getTouchX(i), y = (int) touchListener
                                .getTouchY(i);

                        if(boardLayout.pauseRect.contains((int) x, (int) y)) {
                            pauseGame();
                        }

                        if (boardLayout.handRect1.contains((int) x, (int) y) && handActive)

                        {
                            // Allow the card chosen to be dragged
                            dragActive = true;
                            // The first card becomes the current card
                            handIndex = 0;
                            // No more hand cards can be picked up
                            handActive = false;

                        }

                        if (boardLayout.handRect2.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 1;
                            handActive = false;

                        }

                        if (boardLayout.handRect3.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 2;
                            handActive = false;

                        }

                        if (boardLayout.handRect4.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 3;
                            handActive = false;

                        }

                        if (boardLayout.handRect5.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 4;
                            handActive = false;

                        }

                        if(boardLayout.playerMovementRect.contains((int) x, (int) y) && dragActive)

                        {
                            // how do you know which card this effects
                            // set the index of the array to talk to
                            handCards.get(handIndex).x = x;
                            handCards.get(handIndex).y = y;

                        }

                        // Hand Index
                        if (boardLayout.MSlot1Rect.contains((int)handCards.get(handIndex).x, (int)handCards.get(handIndex).y)
                                && handCards.get(handIndex).id == 0)

                        {

                            // prevents cards splipping off the edge
                            dragActive = false;

                            // get rid of the hand card
                            handCards.get(handIndex).destroyed = true;

                            // update the bitmap of the monster card and lock it at the right slot
                            monsterCard1.sprite = handCards.get(handIndex).sprite;
                            monsterCard1.x = 234;
                            monsterCard1.y = 280;

                            // move from the hand to the field
                            // the card should be moved to the player's logical monster array
                            // The touch zones dictate the index
                            playerFieldMonsters.add(0, playerHandMonsters.get(handIndex));

                            // puts the card back in it's proper position
                            // handCards.get(handIndex).resetPosition();

//                          attack = true;

                            // Turn this block into a new method??

                            if (prepPhase)

                            {
                                think();
                                prepPhase = false;
                            }

                            else

                            {
                                gameState = GameState.ATTACK;
                                monsterSlotActive = true;
                            }

                        }

                        // Test with the middle

                        if (boardLayout.MSlot2Rect.contains((int)handCards.get(handIndex).x, (int)handCards.get(handIndex).y)
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

                            // move from the hand to the field
                            // the card should be moved to the player's logical monster array
                            // The touch zones dictate the index

                            System.out.println(handIndex);
                            // Avoid out of bounds exceptions
                            playerFieldMonsters.add(1, playerHandMonsters.get(handIndex));

                            handCards.get(handIndex).resetPosition();

                            if (prepPhase)

                            {
                                think();
                                prepPhase = false;
                            }

                            else

                            {
                                gameState = GameState.ATTACK;
                                monsterSlotActive = true;
                            }

                        }

                        //

                        if (boardLayout.MSlot3Rect.contains((int)handCards.get(handIndex).x, (int)handCards.get(handIndex).y)
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

                            // move from the hand to the field
                            // the card should be moved to the player's logical monster array
                            // The touch zones dictate the index
                            playerFieldMonsters.add(3, playerHandMonsters.get(handIndex));


//                            attack = true;

                            // Could pass in the index to decide the position
//                            handCards.get(handIndex).resetPosition();

                            if (prepPhase)

                            {
                                think();
                                prepPhase = false;
                            }

                            else

                            {
                                gameState = GameState.ATTACK;
                                monsterSlotActive = true;
                            }

                        }

                    }

                    // release that pointerID
                    else

                    {
                        // how do you control which one goes where
                        if (handCards.get(handIndex).pointerID == i)

                        {
                            handCards.get(handIndex).x = 234;
                            handCards.get(handIndex).y = 410;

                        }

                    }
                }

                break;

            // add soundfx here
            // before this is accessed drag active should be false

            case ATTACK:

                for(int i = 0; i < touchListener.MAX_TOUCH_POINTS; i++) {
                    if(touchListener.isTouchContinuous(i)) {
                        int x = (int) touchListener.getTouchX(i), y = (int) touchListener
                                .getTouchY(i);

                        if(boardLayout.pauseRect.contains((int) x, (int) y)) {
                            pauseGame();
                        }

                        if (boardLayout.MSlot1Rect.contains((int) x, (int) y) && monsterSlotActive)

                        {

                            dragActive = true;
                            monsterIndex = 0;
                            monsterSlotActive = false;

                        }

                        if (boardLayout.MSlot2Rect.contains((int) x, (int) y) && monsterSlotActive)

                        {
                            dragActive = true;
                            monsterIndex = 1;
                            monsterSlotActive = false;

                        }

                        if (boardLayout.MSlot3Rect.contains((int) x, (int) y) && monsterSlotActive)

                        {
                            dragActive = true;
                            monsterIndex = 2;
                            monsterSlotActive = false;

                        }

                        // Set up movement for attack logic
                        if(boardLayout.attackRect.contains((int) x, (int) y) && dragActive)

                        {
                            monstersInPlay.get(monsterIndex).x = x;
                            monstersInPlay.get(monsterIndex).y = y;

                        }

                        if (boardLayout.opponent1Rect.contains((int)monstersInPlay.get(monsterIndex).x, (int)monstersInPlay.get(monsterIndex).y))

                        {
                            System.out.println("We're in the attack phase");
                            aiFieldMonsters.get(0).attackValue = 1000;

                            if (aiFieldMonsters.get(0).attackValue > playerFieldMonsters.get(handIndex).health)

                            {
                                System.out.println("Test");

                                // beats the opponent and kills the card
                                // or does some damage

                                // stop drawing the monster that was destroyed
                                opponent1.destroyed = true;

                                // transfer control back to the AI
                                gameState = GameState.PROMPT;

                                // winning conditions go here
                                // if you kill 2 monsters you've won the game
                                // monstersKilled++;
                                // if (monstersKilled == 2) move to win state

                            }

                        }

                        if (boardLayout.opponent2Rect.contains((int)monstersInPlay.get(monsterIndex).x, (int)monstersInPlay.get(monsterIndex).y))

                        {
                            if (aiFieldMonsters.get(1).attackValue > playerFieldMonsters.get(monsterIndex).health)

                            {
                                System.out.println("Test");

                                // beats the opponent and kills the card
                                // or does some damage

                                // stop drawing the monster that was destroyed
                                opponent2.destroyed = true;

                                // winning conditions go here
                                // if you kill 2 monsters you've won the game
                                // monstersKilled++;
                                // if (monstersKilled == 2) move to win state

                            }

                        }

                        if (boardLayout.opponent3Rect.contains((int)monstersInPlay.get(monsterIndex).x, (int)monstersInPlay.get(monsterIndex).y))

                        {
                            if (aiFieldMonsters.get(2).attackValue > playerFieldMonsters.get(monsterIndex).health)

                            {
                                System.out.println("Test");

                                // beats the opponent and kills the card
                                // or does some damage

                                // stop drawing the monster that was destroyed
                                opponent3.destroyed = true;

                                // transfer control back to the AI
                                gameState = GameState.PROMPT;

                                // winning conditions go here
                                // if you kill 2 monsters you've won the game
                                // monstersKilled++;
                                // if (monstersKilled == 2) move to win state

                            }

                        }
                    }

                    // Snapping goes here - code in positions
                    else

                    {



                    }
                }

                break;

            case PAUSED:

                for(int i = 0; i < touchListener.MAX_TOUCH_POINTS; i++) {
                    if(touchListener.isTouchDown(i)) {
                        int x = (int) touchListener.getTouchX(i), y = (int) touchListener
                                .getTouchY(i);

                        // This should be the default case
                        if(boardLayout.resumeRect.contains(x, y)) {
                            gameState = GameState.NEW;

                            timer.start();



                        }

                        if(boardLayout.restartRect.contains(x, y)) {

                            fragment.getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container, new GameViewFragment(),
                                            "game_fragment").commit();
                        }

                        if(boardLayout.mainMenuRect.contains(x, y)) {
                            fragment.getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container,
                                            new MainMenuFragment(),
                                            "main_menu_fragment").commit();
                        }
                    }
                }

                break;
        }
    }


    // show that the ai is thinking about it's choice

    public void think ()

    {
        // Show that the AI is thinking
        gameState = GameState.PROMPT;
    }

    public void pauseGame ()

    {
        gameState = GameState.PAUSED;

        timer.stop();

    }

    public void updateAICards ()

    {
        // This needs to happen seperately.
        int index = aiBrain.playHighestAttack(aiHandMonsters);
        System.out.println(index);

        // This is the logic not the visual stuff
        aiFieldMonsters.add(aiHandMonsters.get(index));

        // THis is for monster placement
        // monsterSlotActive = true;
        opponent1.sprite = aiHandMonsters.get(index).sprite;

        // begin the player draw phase
        gameState = GameState.DRAW;
    }

    private void updateDraw ()

    {
        // draw a new card
        takeCard();

        // begin the mana placement phase
        gameState = GameState.MANAPLACEMENT;

        // Set all your variables and then move on


        // allow dragging
        dragActive = true;
        // allow mana detection
        manaflag = true;
        // make the hand cards active
        handActive = true;

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

    // ALL THE LOGIC RELATED TO DRAWING CARDS

    // randomly selects hand cards for the player and draws them to the screen
    // the hand cards objects need to increment
    private void populatePlayerHand ()

    {
        for (int i = 0; i < 5; i++)

        {
            randex = rand.generateRandomNumber();
            // keep for troubleshooting
            System.out.println("Random is" + randex);

            if (randex <= 7)

            {
                // visual stuff is updated
                handCards.get(i).sprite = playerDeck.monsterArray.get(randex).sprite;
                handCards.get(i).id = 0;
                handCards.get(i).cardSchool = playerDeck.monsterArray.get(randex).cardSchool;

                playerHandMonsters.add(i, playerDeck.monsterArray.get(randex));
            }

            // trap this in certain bounds
            if (randex > 7 && randex <= 13)

            {
                handCards.get(i).sprite = playerDeck.manaArray.get(randex % 8).sprite;
                handCards.get(i).id = 1;
                handCards.get(i).cardSchool = playerDeck.manaArray.get(randex % 8).cardSchool;

                playerHandMana.add(i, playerDeck.manaArray.get(randex % 8));
            }

        }

        // Error Checking
        System.out.println("Completed");


    }
    // randomly selects hand cards but doesn't draw them

    private void populateOpponentHand ()

    {
        for (int i = 0; i < 5; i++)

        {

            randex = rand.generateRandomNumber();
            // keep for troubleshooting
            System.out.println("Random is" + randex);

            if (randex <= 7)

            {
                aiHandMonsters.add(aiDeck.monsterArray.get(randex));
            }

            // trap this in certain bounds
            if (randex > 7 && randex <= 13)

            {
                aiHandMana.add(aiDeck.manaArray.get(randex % 8));
            }

        }

        // Error Checking
        System.out.println("Completed");

    }

    // Created by Andrew - 40083349
    // Take an individual card from the deck - this takes place once the prep phase is over
    // TakeCard can only place cards on empty slots

    // Should this be animated??
    private void takeCard()

    {
        randex = rand.generateRandomNumber();
        // keep for troubleshooting
        System.out.println("Random is" + randex);

        // The thing that's updated depends on what was previously removed
        if (randex <= 7)

        {
            // LOGIC
            playerHandMonsters.add(handIndex, playerDeck.monsterArray.get(randex));

            // VISUAL
            handCards.get(handIndex).sprite = playerDeck.monsterArray.get(randex).sprite;
            handCards.get(handIndex).id = 0;
            handCards.get(handIndex).cardSchool = playerDeck.monsterArray.get(randex).cardSchool;

            // Make the card visible
            handCards.get(handIndex).destroyed = false;

        }

        // trap this in certain bounds
        if (randex > 7 && randex <= 13)

        {
            // LOGIC
            playerHandMana.add(handIndex, playerDeck.manaArray.get(randex % 8));

            // VISUAL
            handCards.get(handIndex).sprite = playerDeck.manaArray.get(randex % 8).sprite;
            handCards.get(handIndex).id = 1;
            handCards.get(handIndex).cardSchool = playerDeck.manaArray.get(randex % 8).cardSchool;

            // Make the card visible
            handCards.get(handIndex).destroyed = false;
        }

    }

//        // You can't re-run the draw method
//
//        deckCompleted = true;
//        mplacement = true;
//        manaflag = true;
//
//    }


}


