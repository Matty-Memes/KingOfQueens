package javalampstudos.kingofqueens;

import android.media.SoundPool;
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
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import javalampstudos.kingofqueens.kingOfQueens.util.GameTimer;
import javalampstudos.kingofqueens.kingOfQueens.util.randomGenerator;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.boardLayout;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;
import javalampstudos.kingofqueens.kingOfQueens.Menu.MainMenuFragment;
import javalampstudos.kingofqueens.kingOfQueens.AiEngine.Window;
import javalampstudos.kingofqueens.kingOfQueens.util.andyManaCounter;
import javalampstudos.kingofqueens.kingOfQueens.util.CardAnimation;

// Android Imports

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.content.res.AssetManager;

import java.util.ArrayList;
import java.util.HashMap;

// Nathan Imports
import android.graphics.Canvas;
import javalampstudos.kingofqueens.kingOfQueens.objects.Entity;
import javalampstudos.kingofqueens.kingOfQueens.objects.Entities.littleMan;
import javalampstudos.kingofqueens.kingOfQueens.world.ScreenViewport;
import javalampstudos.kingofqueens.kingOfQueens.world.LayerViewport;

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
        PROMPT, AITURN, ANIMATION, AIANIMATION,
        // Turn Structure
        DRAW, MANAPLACEMENT, MONSTERPLACEMENT, ATTACK,
        // Win / lose
        VICTORY, DEFEAT,
        OPENWORLD
    }

    // Allow the prep phase to happen
    public boolean prepPhase;

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

    // Opponent Deck
    public BasicCard opponentDeck;

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

    // When a card is placed by the player remember the empty slot.
    public int emptySlot;

    // boundary conditions for the test card
    public boolean boundHit = false;

    public CardAnimation animation;

    // OTHER CLASSES
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
    private SoundPool startSFX;
    private float sfxVolume;

    // MISC

    public BasicCard[] hand;

    // Declare card backs here - Possibly move to the JSON parser

    public BasicCard graveYard;
    public BasicCard deck;

    // Windows

    public Window victory;
    public Bitmap victoryScreen;
    public int opponentMonstersKilled;

    // Nathan variables
    public int[][] grid = new int[100][100];

    //NathanMessingAbout
    public enum MoveDirection {

        LEFT, RIGHT, UP, DOWN;

    }

    //All Nathan's Sprites
    private Bitmap playerSprite;
    public Bitmap player2Sprite;
    private Bitmap backgroundBitmap;
    private Bitmap mcclayTopBitmap;
    private Bitmap lanyonTopBitmap;
    private Bitmap coinBitmap;
    public Bitmap grassSprite;
    public Bitmap wallSprite;
    public Bitmap aButton;

    public littleMan player;
    public Entity player2;
    //public Entity cloudyBackground;
    public Entity mapTest;
    public Entity mcclayTop;
    public Entity lanyonTop;
    public Entity tile;

    public Rect interactButton;
    public int interactionIndex = -1;
    public int coinIndex = -1;
    public int coinCounter;
    public boolean inDialogue;
    public int dialoguePoint = 0;

    private Rect moveLeftRect;
    public Rect moveRightRect;
    private Rect moveUpRect;
    private Rect moveDownRect;

    public MoveDirection moveDirection;


    //Screen Width and Height dimensions
    private int mScreenWidth = -1;

    public int getScreenWidth() {
        return mScreenWidth;
    }

    private int mScreenHeight = -1;

    public int getScreenHeight() {
        return mScreenHeight;
    }

    /**
     * Width and height of the level
     */
    public final float LEVEL_WIDTH = 1500.0f;
    public final float LEVEL_HEIGHT = 1500.0f;

    ArrayList<GameObject> tileList = new ArrayList<>();
    ArrayList<GameObject> collisionList = new ArrayList<>();
    ArrayList<GameObject> objectList = new ArrayList<>();
    ArrayList<GameObject> coinList = new ArrayList<>();
    ArrayList<GameObject> interactionList = new ArrayList<>();

    public LayerViewport mLayerViewport;
    public ScreenViewport mScreenViewport;

    public int getCoinCounter() {
        return coinCounter;
    }

    // Declare a gamestate
    public GameState gameState;

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

        System.out.println("width");

        gameScaling = width / 320.0f;

        // Getting UI scaling from display metrics
        DisplayMetrics metrics = new DisplayMetrics();
        fragment.getActivity().getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        uiScaling = metrics.density;

        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        // set the game state to new initially
        // Once the board is set up you can move to the default game state
        gameState = GameState.NEW;

        // Now all the rects exist
        gameBoard = new boardLayout(width, height, uiScaling);

        // input stuff
        touchListener = new MultitouchListener();
        canvasRenderer.setOnTouchListener(touchListener);

        // load assets
        loadAssets();

        openWorldSetup();

        // THESE CARDS ARE PLACEHOLDERS FOR BITMAPS - THIS SHOULD BE IN ANOTHER METHOD

        // Load the cardBackSprite

        AssetManager assetManager = fragment.getActivity().getAssets();

        aiThinking = AssetLoader.loadBitmap(assetManager, "img/AiThinking3.png");
        victoryScreen = AssetLoader.loadBitmap(assetManager, "img/Screens/VictoryScreen.png");

        // This is the ai message
        window = new Window(477, 240, 740, 240, aiThinking, true);

        // The victory screen
        victory = new Window(477, 240, 740, 240, victoryScreen, true);

        cardBackSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Cardback.png");
        manaZoneSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/ManaZoner.png");

        // Hand - Accessible individually and in their array positions

        handCard1 = new BasicCard(234, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49, 234);
        handCard2 = new BasicCard(334, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49, 334);
        handCard3 = new BasicCard(434, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49, 434);
        handCard4 = new BasicCard(534, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49, 534);
        handCard5 = new BasicCard(634, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49, 634);

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
                49, 0, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana);
        monsterCard2 = new MonsterCard(434, 280, 90, 120, cardBackSprite, true, 3,CardSchools.MEDICS, false,
                49, 0, CardLevel.DOCTRATE, 140, 0, 3,1, requiredMana);
        monsterCard3 = new MonsterCard(634, 280, 90, 120, cardBackSprite, true, 3,CardSchools.MEDICS, false,
                49, 0, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana);

        monstersInPlay.add(monsterCard1);
        monstersInPlay.add(monsterCard2);
        monstersInPlay.add(monsterCard3);

        graveYard = new BasicCard(800, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49, 0);

        deck = new BasicCard(800, 410, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                49, 0);
        manaZone = new BasicCard(100, 340, 140, 240, manaZoneSprite, true, 3, CardSchools.MEDICS, false, 49, 0);

        // Opponent card
        opponent1 = new MonsterCard(234, 100, 90, 120, cardBackSprite, false, 0, CardSchools.EEECS, false, 49, 234, CardLevel.DOCTRATE, 140, 0, 3,1, requiredMana);
        opponent2 = new MonsterCard(434, 100, 90, 120, cardBackSprite, false, 0, CardSchools.EEECS, false, 49, 434, CardLevel.DOCTRATE, 140, 0, 3,4, requiredMana);
        opponent3 = new MonsterCard(634, 100, 90, 120, cardBackSprite, false, 0, CardSchools.EEECS, false, 49, 634, CardLevel.DOCTRATE, 140, 0, 3,8, requiredMana);

        // Opponent cards snap back here
        opponentDeck = new BasicCard(70, 100, 90, 120, cardBackSprite, false, 0, CardSchools.EEECS, false, 49, 0);

        // Use this to aniamte card objects
        animation = new CardAnimation();

        rand = new randomGenerator();

        // initialzing AiBrain.
        aiBrain = new Brain();

        // populate logical arrays
        initializeHandMonsters();
        initializeHandMana();
        intializeFieldMonsters();

//        int startingDeck1[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,49,49,49,49,49,49,51,51,51,51,51,51,54,54,55,57,57,58,60,61,63,65,66,67};
//
//        playerDeck.generateDeck(this,startingDeck1);
        // this only affects basic card at the moment

        playerDeck.createDeck(this);
        rand.flushRandomLogic();

        populatePlayerHand ();
        rand.flushRandomLogic();

//        aiDeck.generateDeck(this,startingDeck1);

        populateOpponentHand();
        rand.flushRandomLogic();

        engineering = new andyManaCounter(100, 250, "0");
        artsAndHumanities = new andyManaCounter(100, 290 , "0");
        builtEnvironment = new andyManaCounter(100, 335, "0");
        eeecs = new andyManaCounter(100, 380, "0");
        Medic = new andyManaCounter(100, 430, "0");

        // Once everything is loaded the user can interact
        handActive = true;

        // The prep phase becomes active here
        prepPhase = true;

        timer.start();

        startSFX = AssetLoader.loadSoundpool(assetManager, "start.mp3");

    }

    //Create the Open World
    public void openWorldSetup() {


        int spacingX = width / 3;
        int spacingY = height / 2;

        createViewports();

        initialise2DGrid();

        tile = new Entity(0, 0, LEVEL_WIDTH / 100, LEVEL_HEIGHT / 100, null, true);
        player = new littleMan(tile.width * 10 + tile.width / 2, tile.height * 24 +  tile.height / 2, tile.width, tile.height, playerSprite, true);
        //interactionList.add(player2 = new Entity(100, 100, 20, 20, player2Sprite));
        //collisionList.add(player2);
        //cloudyBackground = new Entity(LEVEL_WIDTH / 2, LEVEL_HEIGHT / 2, LEVEL_WIDTH, LEVEL_HEIGHT, backgroundBitmap);
        mapTest = new Entity(LEVEL_WIDTH / 2, LEVEL_HEIGHT / 2, LEVEL_WIDTH, LEVEL_HEIGHT, backgroundBitmap, true);
        mcclayTop = new Entity(tile.width * 33 + tile.width / 2, tile.height * 92, tile.width * 25, tile.height * 14, mcclayTopBitmap, true);
        lanyonTop = new Entity(tile.width * 33, tile.height * 37 + tile.height / 2, tile.width * 58, tile.height * 17, lanyonTopBitmap, true);
        //coin =  new Entity(tile.width * 17 + tile.width / 2, tile.height * 24 + tile.height / 2, tile.width, tile.height, coinBitmap);

        //tileList.add(coin);

        interactButton = new Rect(spacingX * 2, spacingY + spacingY / 2, spacingX * 2 + spacingX / 2, spacingY * 2);

        int x = 0;
        int y;

        while (x < 100) {

            y = 0;
            while(y < 100) {

                switch (grid[y][x]) {

                    case 1:
                        collisionList.add(new Entity((tile.width * x) + tile.width / 2,
                                LEVEL_HEIGHT - ((tile.height * (y)) - tile.height / 2),
                                tile.width, tile.height, null, true)); break;
                    //collisionList.add(tileList.get(tileList.size() - 1));

                    case 2:
                        coinList.add(new Entity((tile.width * x) + tile.width / 2,
                                LEVEL_HEIGHT - ((tile.height * (y + 1)) - tile.height / 2),
                                tile.width, tile.height, coinBitmap, true)); break;

                    case 3:
                        objectList.add(new Entity((tile.width * x) + tile.width / 2,
                                LEVEL_HEIGHT - ((tile.height * (y + 1)) - tile.height / 2),
                                tile.width, tile.height, player2Sprite, true));
                        collisionList.add(objectList.get(objectList.size() - 1));
                        interactionList.add(objectList.get(objectList.size() - 1)); break;

                }
                y++;
            }
            x++;
        }



        //System.out.println("X " + tileArrayList.get(0).x + " Y " + tileArrayList.get(0).y);
        //System.out.println("X " + tileArrayList.get(1).x + " Y " + tileArrayList.get(1).y);
        //System.out.println("X " + tileArrayList.get(2).x + " Y " + tileArrayList.get(2).y);
        //System.out.println("X " + tileArrayList.get(3).x + " Y " + tileArrayList.get(3).y);
        //System.out.println("X " + tileArrayList.get(4).x + " Y " + tileArrayList.get(4).y);

        moveLeftRect = new Rect(0, 0, spacingX, spacingY * 2);
        moveRightRect = new Rect(spacingX * 2, 0, spacingX * 3, spacingY * 2);
        moveUpRect = new Rect(spacingX, 0, spacingX * 2, spacingY);
        moveDownRect = new Rect(spacingX, spacingY, spacingX * 2, spacingY * 2);
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
                    /*
                    case OPENWORLD:
                        updateOWTouch();
                        updateObjects();
                        break;
                    */
                    case PROMPT:
                        updateCard();
                        updateWindow();
                        // updatePrompt();
                        break;
                    case PAUSED:
                        updateTouch ();
                        break;
                    case AITURN:
                        updateCard();
                        updateAICards();
                        break;
                    // automated so no updateTouch
                    case AIANIMATION:
                        updateCard();
                        updateAIAnimation();
                        break;
                    case DRAW:
                        updateCard();
                        updateDraw();
                        break;
                    case ANIMATION:
                        // don't update touch
                        updateAnimation();
                        updateCard();
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
                    case VICTORY:
                        updateVictoryScreen();
                        updateVS();
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

            // timer.stop();
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

        sfxVolume = MainActivity.setting.getVolume("sfxValue") / 10.0f;

    }

    // Created by Andrew - 40083349
    private void newGame ()

    {
        // Do all the setup in here

        gameBoard = new boardLayout(width, height, uiScaling);

        // Hands and deck are set up so the prep phase can begin
        gameState = GameState.MONSTERPLACEMENT;

        sfxVolume = MainActivity.setting.getVolume("sfxValue") / 10.0f;
        startSFX.play(1, sfxVolume, sfxVolume, 1, 0, 1.0f);
    }

    private void loadAssets()

    {
        AssetManager assetManager = fragment.getActivity().getAssets();

        playerSprite = AssetLoader.loadBitmap(assetManager, "img/Nathan/playerSpriteSheet.png");
        player2Sprite = AssetLoader.loadBitmap(assetManager, "img/Nathan/trimLittleMan.png");
        aButton = AssetLoader.loadBitmap(assetManager, "img/Nathan/aButton.png");
        grassSprite = AssetLoader.loadBitmap(assetManager, "img/Nathan/GrassTile.png");
        wallSprite = AssetLoader.loadBitmap(assetManager, "img/Nathan/Wall.png");
        //backgroundBitmap = AssetLoader.loadBitmap(assetManager, "img/Nathan/10x10Grid.png");
        backgroundBitmap = AssetLoader.loadBitmap(assetManager, "img/Nathan/MapTest.png");
        mcclayTopBitmap = AssetLoader.loadBitmap(assetManager, "img/Nathan/McClayTop.png");
        lanyonTopBitmap = AssetLoader.loadBitmap(assetManager, "img/Nathan/LanyonTop.png");
        coinBitmap = AssetLoader.loadBitmap(assetManager, "img/Nathan/Coin.png");

    }

    private void updateObjects()

    {

        player.update();
        //player2.update();


        // Ensure the player cannot leave the confines of the world
        if ((player.x - player.width / 2) < 0)
            player.x -= (player.x - player.width / 2);
        else if ((player.x + player.width / 2) > LEVEL_WIDTH)
            player.x -= ((player.x + player.width / 2) - LEVEL_WIDTH);

        if ((player.y - player.height / 2) < 0)
            player.y -= (player.y - player.height / 2);
        else if ((player.y + player.height / 2) > LEVEL_HEIGHT)
            player.y -= ((player.y + player.height / 2) - LEVEL_HEIGHT);


        // Focus the layer viewport on the player
        mLayerViewport.update(mLayerViewport, player);


        // Ensure the viewport cannot leave the confines of the world
        if ((mLayerViewport.x - mLayerViewport.width / 2) < 0)
            mLayerViewport.x -= (mLayerViewport.x - mLayerViewport.width / 2);
        else if ((mLayerViewport.x + mLayerViewport.width / 2) > LEVEL_WIDTH)
            mLayerViewport.x -= ((mLayerViewport.x + mLayerViewport.width / 2) - LEVEL_WIDTH);

        if ((mLayerViewport.y - mLayerViewport.height / 2) < 0)
            mLayerViewport.y -= (mLayerViewport.y - mLayerViewport.height / 2);
        else if ((mLayerViewport.y + mLayerViewport.height / 2) > LEVEL_HEIGHT)
            mLayerViewport.y -= ((mLayerViewport.y + mLayerViewport.height / 2) - LEVEL_HEIGHT);


    }

    // continuously checks for new touch input
    private void updateOWTouch()

    {
        switch (gameState)

        // thumbstick stuff??

        {
            case OPENWORLD:

                for (int i = 0; i < 1; i++)

                {
                    if (touchListener.isTouchContinuous(i) && !inDialogue) {

                        float x = touchListener.getTouchX(i), y = touchListener.getTouchY(i);

                        if (moveLeftRect.contains((int) x, (int) y))

                        {
                            moveDirection = MoveDirection.LEFT;
                            movePlayer();

                        }

                        if (moveRightRect.contains((int) x, (int) y))

                        {
                            moveDirection = MoveDirection.RIGHT;
                            movePlayer();

                        }
                        if (moveUpRect.contains((int) x, (int) y))

                        {
                            moveDirection = MoveDirection.UP;
                            movePlayer();

                        }

                        if (moveDownRect.contains((int) x, (int) y))

                        {
                            moveDirection = MoveDirection.DOWN;
                            movePlayer();

                        }



                    }

                    float x = touchListener.getTouchX(i), y = touchListener.getTouchY(i);

                    if (touchListener.isTouchDown(i)) {

                        if(interactButton.contains((int) x, (int) y))

                        {
                            /*if(interactBounds()) {
                                System.out.println("Interact");
                            }*/
                            interactionIndex = littleMan.interactBounds(player, interactionList, tile);

                            if(interactionIndex >= 0) {
                                dialoguePoint++;
                                inDialogue = true;
                            }

                            coinIndex = littleMan.interactBounds(player, coinList, tile);

                            if (coinIndex >= 0) {
                                coinList.remove(coinIndex);
                                coinCounter++;
                                coinIndex = -1;
                            }



                            /*else if(interactionIndex >= 0) {
                                dialoguePoint++;
                            }*/

                            /*if (interactionIndex >= 0) {
                                interact(interactionState, interactionIndex);
                            }*/
                        }
                    }

                }

                break; // end OPENWORLD case
        }

    }

    // display the ai window briefly then move back to normal gameplay
    // tracking variables

    // Andrew - 40083349
    private void updateMana ()

    {
        engineering.update();
        artsAndHumanities.update();
        builtEnvironment.update();
        eeecs.update();
        Medic.update();
    }

    //movement has temporary restrictions currently based only on pixels
    public void movePlayer() {

        player.updateCurrentFrame(moveDirection);

        switch (moveDirection) {

            case LEFT:
                //if ((player.x - 400) - 15 >= 0) {
                //if(player.collision(player, player2, moveDirection) != littleMan.CollisionSide.LEFT)
                if(player.collision(player, collisionList, moveDirection) != littleMan.CollisionSide.LEFT)
                    player.x -= player.width / 10;
                //}
                System.out.println(player.x);
                System.out.println("Moving Left");
                break;
            case RIGHT:
                //if ((player.x + 400) + 15 <= getScreenWidth()) {
                if(player.collision(player, collisionList, moveDirection) != littleMan.CollisionSide.RIGHT)
                    player.x += player.width / 10;
                //}
                //System.out.println(player.y);
                break;
            case UP:
                //if ((player.y - 400) - 15 >= 0)
                if(player.collision(player, collisionList, moveDirection) != littleMan.CollisionSide.TOP)
                    player.y += player.height / 10;
                break;
            case DOWN:
                //if ((player.y + 400) + 15 <= getScreenHeight())
                if(player.collision(player, collisionList, moveDirection) != littleMan.CollisionSide.BOTTOM)
                    player.y -= player.height / 10;
                //System.out.println("Moving Down");
                break;
        }
    }

    private void createViewports() {
        // Create the screen viewport
        mScreenViewport = new ScreenViewport(0, 0, getScreenWidth(),
                getScreenHeight());

        //mLayerViewport = new LayerViewport(player.x, player.y, 600, 600);

        // Create the layer viewport, taking into account the orientation
        // and aspect ratio of the screen.
        if (mScreenViewport.width > mScreenViewport.height)
            mLayerViewport = new LayerViewport(240.0f, 240.0f
                    * mScreenViewport.height / mScreenViewport.width, 240,
                    240.0f * mScreenViewport.height / mScreenViewport.width);
        else
            mLayerViewport = new LayerViewport(240.0f * mScreenViewport.height
                    / mScreenViewport.width, 240.0f, 240.0f
                    * mScreenViewport.height / mScreenViewport.width, 240);
    }


    private void initialise2DGrid() {


        //McClay Collision Info
        grid[18][21] = 1;
        grid[18][22] = 1;
        grid[17][21] = 1;
        grid[17][45] = 1;
        grid[18][45] = 1;

        for (int col = 21; col <= 45; col++) {
            grid[16][col] = 1;
        }

        for (int col = 23; col <= 45; col++) {
            if(col == 37 || col == 41) {
                col = col + 2;
            }
            grid[19][col] = 1;
        }

        for (int col = 37; col <= 42; col++) {
            if(col == 39) {
                col = col + 2;
            }
            grid[20][col] = 1;
        }


        //Lanyon Collision Info
        for (int col = 4; col <= 61; col++) {

            switch(col) {

                case 32:
                    col = col + 2; break;
                case 24:case 39:
                    col = col + 3; break;
                case 9:case 50:
                    col = col + 7; break;
            }
            grid[73][col] = 1;
        }


        for (int col = 9; col <= 56; col++) {

            switch(col) {

                case 32:
                    col = col + 2; break;
                case 28:case 35:
                    col = col + 3; break;
                case 16:case 43:
                    col = col + 7; break;
            }
            grid[74][col] = 1;
        }


        for (int col = 9; col <= 56; col++) {

            if(col == 16) {
                col = col + 34;
            }
            grid[75][col] = 1;
        }


        for (int col = 4; col <= 61; col++) {

            switch(col) {

                case 5:case 35:
                    col = col + 26; break;
                case 32:
                    col = col + 2; break;
            }
            grid[72][col] = 1;
        }


        for (int row = 32; row <= 71; row++) {

            grid[row][4] = 1;

        }


        for (int col = 5; col <= 61; col++) {

            if (col == 9) {
                col = col + 11;
            }
            grid[32][col] = 1;

        }

        for (int row = 32; row <= 71; row++) {

            grid[row][61] = 1;

        }

        for (int row = 36; row <= 70; row++) {

            grid[row][57] = 1;

        }

        for (int col = 5; col <= 61; col++) {

            if (col == 9) {
                col = col + 11;
            }
            grid[32][col] = 1;

        }

        for (int col = 20; col <= 56; col++) {

            grid[35][col] = 1;

        }

        for (int row = 33; row <= 70; row++) {

            grid[row][8] = 1;

        }

        for (int col = 9; col <= 56; col++) {

            if (col == 32) {
                col = col + 2;
            }
            grid[70][col] = 1;

        }

        grid[33][20] = 1;
        grid[34][20] = 1;


        //Load in coins
        grid[75][24] = 2;
        grid[75][27] = 2;

        //Load in NPCs
        grid[78][10] = 3;
        grid[78][12] = 3;
        grid[78][14] = 3;
    }




    /*public void interact(boolean state, int index) {

        state = true;
        currentConvo.conversationIndex(index);

    }*/




    private class GameWorld {

        private Rect mLayerViewport;

        public void draw(Canvas canvas, Rect screenViewport) {

            // Determine the x- and y-aspect ratios between the layer and screen viewports
            float screenXScale =
                    (float) screenViewport.width() / (float) mLayerViewport.width();
            float screenYScale =
                    (float) screenViewport.height() / (float) mLayerViewport.height();
        }
    }

    // PUT THIS IN A SINGLE METHOD

    // Andrew - 40083349
    private void initializeHandMonsters()

    {

        HashMap<ManaTypes,Integer> requiredMana = new HashMap<ManaTypes,Integer>();
        requiredMana.put(ManaTypes.BUILT_ENVIRONMENT_MANA,5);

        for (int i = 0; i < 5; i++)

        {
            playerHandMonsters.add(new MonsterCard(234, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                    49, 0, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana));
        }

    }

    // Andrew - 40083349
    private void initializeHandMana()

    {
        for (int i = 0; i < 5; i++)

        {
            playerHandMana.add(new ManaCard(0, 0, 90, 120, cardBackSprite, true, 2,
                    ManaTypes.SOCIAL_SCIENCES_MANA, CardSchools.SOCIAL_SCIENCES, false, 49, 0));

        }

    }

    // Andrew - 40083349
    private void intializeFieldMonsters ()

    {
        HashMap<ManaTypes,Integer> requiredMana = new HashMap<ManaTypes,Integer>();
        requiredMana.put(ManaTypes.BUILT_ENVIRONMENT_MANA,5);

        for (int i = 0; i < 3; i++)

        {

            playerFieldMonsters.add(new MonsterCard(234, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                    49, 0, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana));

        }

        for (int i = 0; i < 3; i++)

        {

            aiFieldMonsters.add(new MonsterCard(234, 280, 90, 120, cardBackSprite, true, 3, CardSchools.MEDICS, false,
                    49, 0, CardLevel.DOCTRATE, 140, 0, 3, 1, requiredMana));
        }

    }

    private void updateVictoryScreen ()

    {
        // move the prompt up and down
        // set bounds for the prompt
        // speed = 0.1
        if(windowDown) {

            victory.y += 1.0;
            if(victory.y >= 270)
                windowDown = false;

            // start moving the window up
        } else {
            victory.y -= 1.0;
            if(victory.y <= 210) {

                // go to the main menu for now
                // this should go back to the open world
                fragment.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container,
                                new MainMenuFragment(),
                                "main_menu_fragment").commit();
            }
        }
    }

    // Andrew - 40083349

    /*
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
    */

    // Andrew - 40083349
    private void updateWindow ()

    {
        window.update();

    }

    private void updateVS()

    {
        victory.update();

    }

    // Andrew - 40083349
    // Currently used for drawing cards
    private void updateAnimation ()

    {
        System.out.println("Animating player");

        // move the hand card left till it hits it's target position
        // associate the target position with the card
        if (!boundHit) {
            handCards.get(emptySlot).x -= 2.5;
            // the proper position of any card is tied to that individual card
            if (handCards.get(emptySlot).x <= handCards.get(emptySlot).targetX) {
                boundHit = true;
                // animation finished start mana placement
                gameState = GameState.MANAPLACEMENT;
            }

        }
    }

    private void updateAIAnimation ()

    {
        // Call the animation method on the animation object here with the right methods
        animation.updateCardAnimation(this, 2.5f, opponent1, false);

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
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;

                            handActive = false;

                        }

                        if (boardLayout.handRect3.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 2;
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;
                            handActive = false;

                        }

                        if (boardLayout.handRect4.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 3;
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;
                            handActive = false;

                        }

                        if (boardLayout.handRect5.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 4;
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;
                            handActive = false;

                        }

                        if(boardLayout.playerMovementRect.contains((int) x, (int) y) && dragActive)

                        {
                            // how do you know which card this effects
                            // set the index of the array to talk to
                            handCards.get(handIndex).x = x;
                            handCards.get(handIndex).y = y;

                        }

                        // Mana cards are placed here - Reset the position of the card that was placed
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

                            // The last card to be placed is set back to the deck for the drawing phase
                            handCards.get(handIndex).moveToDeck();

                            // move to the monster placement phase
                            gameState = GameState.MONSTERPLACEMENT;

                            manaflag = false;
                            // allow movement of hand cards again
                            handActive = true;

                            // reset the logic for taking cards at the start of the player's turn
                            boundHit = false;
                        }
                    }

                    else

                    {
                        // how do you control which one goes where
                        if (handCards.get(handIndex).pointerID == i)

                        {
                            // Put whatever BasicCard was picked up back in it's old position
                            handCards.get(handIndex).resetPosition(handIndex);
                            // the player has let go of the card and needs to pick up a new one
                            handActive = true;

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
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;
                            // No more hand cards can be picked up
                            handActive = false;
                        }

                        if (boardLayout.handRect2.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 1;
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;
                            handActive = false;

                        }

                        if (boardLayout.handRect3.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 2;
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;
                            handActive = false;

                        }

                        if (boardLayout.handRect4.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 3;
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;
                            handActive = false;

                        }

                        if (boardLayout.handRect5.contains((int) x, (int) y) && handActive)

                        {
                            dragActive = true;
                            handIndex = 4;
                            // Index into the array and set the pointer ID
                            handCards.get(handIndex).pointerID = i;
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
                            MainActivity.setting.getInt("totalPlayTimeValue");

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

                            // The last card to be placed is set back to the deck for the drawing phase
                            handCards.get(handIndex).moveToDeck();

                            System.out.println("The x value is now" + handCards.get(handIndex).x);

                            if (prepPhase)

                            {
                                prepPhase = false;
                                emptySlot = handIndex;
                                // The AI chooses a card and plays it
                                gameState = GameState.AITURN;
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

                            MainActivity.setting.getInt("totalPlayTimeValue");

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

                            // The last card to be placed is set back to the deck for the drawing phase
                            handCards.get(handIndex).moveToDeck();

                            System.out.println("The x value is now" + handCards.get(handIndex).x);

                            if (prepPhase)

                            {
                                prepPhase = false;
                                emptySlot = handIndex;
                                gameState = GameState.AITURN;

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

                            MainActivity.setting.getInt("mosterCardPlayed");

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

                            // The last card to be placed is set back to the deck for the drawing phase
                            handCards.get(handIndex).moveToDeck();

                            System.out.println("The x value is now" + handCards.get(handIndex).x);

                            if (prepPhase)

                            {
                                prepPhase = false;
                                emptySlot = handIndex;
                                gameState = GameState.AITURN;
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
                            // Put whatever BasicCard was picked up back in it's old position
                            handCards.get(handIndex).resetPosition(handIndex);
                            // the player has let go of the card and needs to pick up a new one
                            handActive = true;

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
                            // Index into the array and set the pointer ID
                            handCards.get(monsterIndex).pointerID = i;
                            monsterSlotActive = false;

                        }

                        if (boardLayout.MSlot2Rect.contains((int) x, (int) y) && monsterSlotActive)

                        {
                            dragActive = true;
                            monsterIndex = 1;
                            // Index into the array and set the pointer ID
                            handCards.get(monsterIndex).pointerID = i;
                            monsterSlotActive = false;

                        }

                        if (boardLayout.MSlot3Rect.contains((int) x, (int) y) && monsterSlotActive)

                        {
                            dragActive = true;
                            monsterIndex = 2;
                            // Index into the array and set the pointer ID
                            handCards.get(monsterIndex).pointerID = i;
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

                            // Check if a monster has been killed
                            if (aiFieldMonsters.get(0).attackValue > playerFieldMonsters.get(handIndex).health)

                            {
                                System.out.println("Test");

                                // beats the opponent and kills the card
                                // or does some damage

                                // stop drawing the monster that was destroyed
                                opponent1.destroyed = true;

                                // Check if the player has won the game
                                opponentMonstersKilled++;
                                if (opponentMonstersKilled >= 2)
                                {
                                    gameState = GameState.VICTORY;

                                }

                                else

                                {
                                    // transfer control back to the AI
                                    gameState = GameState.AITURN;
                                }

                            }

                        }

                        if (boardLayout.opponent2Rect.contains((int)monstersInPlay.get(monsterIndex).x, (int)monstersInPlay.get(monsterIndex).y))

                        {
                            if (aiFieldMonsters.get(1).attackValue > playerFieldMonsters.get(monsterIndex).health)

                            {
                                System.out.println("Test");

                                // beats the opponent and kills the card
                                // or does some damage

                                // Check if the player has won the game
                                opponentMonstersKilled++;
                                if (opponentMonstersKilled >= 2)
                                {
                                    gameState = GameState.VICTORY;

                                }

                                else

                                {
                                    // transfer control back to the AI
                                    gameState = GameState.AITURN;

                                }

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

                                // Check if the player has won the game
                                opponentMonstersKilled++;
                                if (opponentMonstersKilled >= 2)
                                {
                                    gameState = GameState.VICTORY;

                                }

                                else

                                {
                                    // transfer control back to the AI
                                    gameState = GameState.AITURN;
                                }
                            }

                        }
                    }

                    // Snapping goes here - code in positions
                    else

                    {
                        // how do you control which one goes where
                        if (monstersInPlay.get(monsterIndex).pointerID == i)

                        {
                            // Put whatever BasicCard was picked up back in it's old position
                            monstersInPlay.get(handIndex).resetPosition(handIndex);
                            // The player has let go of that monster
                            handActive = true;

                        }

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


                            sfxVolume = MainActivity.setting.getVolume("sfxValue") / 10.0f;
                            startSFX.play(1, sfxVolume, sfxVolume, 1, 0, 1.0f);

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


    // remove this
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

    // This is like the player's draw method

    // Should work for any ai animation
    public void updateAICards ()

    {
        // This needs to happen seperately.
        int index = aiBrain.playHighestAttack(aiHandMonsters);
        System.out.println(index);

        // This is the logic not the visual stuff
        aiFieldMonsters.add(aiHandMonsters.get(index));

        // THis is for monster placement
        // monsterSlotActive = true;
        opponent1.x = 70;
        opponent1.y = 100;
        opponent1.sprite = aiHandMonsters.get(index).sprite;

        // animate the ai's sprite
        gameState = GameState.AIANIMATION;
    }

    private void updateDraw ()

    {
        // draw a new card
        takeCard();

        // allow dragging
        dragActive = true;
        // allow mana detection
        manaflag = true;
        // make the hand cards active
        handActive = true;

        boundHit = false;

        // run the card movement animation
        gameState = GameState.ANIMATION;

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

        opponentDeck.update();

    }

    // ALL THE LOGIC RELATED TO DRAWING CARDS

    // randomly selects hand cards for the player and draws them to the screen
    // the hand cards objects need to increment
    private void populatePlayerHand ()

    {
        for (int i = 0; i < 5; i++)

        {
            // remember this has been changed
            randex = rand.generateRandomNumber();

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
                aiHandMonsters.add(playerDeck.monsterArray.get(randex));
            }

            // trap this in certain bounds
            if (randex > 7 && randex <= 13)

            {
                aiHandMana.add(playerDeck.manaArray.get(randex % 8));
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

}


