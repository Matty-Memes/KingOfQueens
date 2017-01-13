package javalampstudos.kingofqueens;

import android.util.DisplayMetrics;

/**
 * Created by 40083349 on 11/01/2017.
 */

// Local Imports

import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasRenderer;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.input.MultitouchListener;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.*;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

// Android Imports

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.content.res.AssetManager;

public class GameLoop implements Runnable

{
    // instantiate CanvasFragment
    public CanvasFragment fragment;

    // declare variables
    private Thread gameThread = null;
    private volatile boolean running = false;
    private long targetStepPeriod;
    protected CanvasRenderer canvasRenderer;

    // Setting up UI and graphics variables - this might need to be included
    public static int width, height;
    public static float gameScaling;
    protected float uiScaling;

    // GameLoop constructor
    // This all gets set up when the gameloop is instantiated in the gameViewFragment


    // Where do the width and height come from?

    // Use enum for game states as a basis
    // Default is for normal gameplay i.e the card game itself
    public enum GameState {

        NEW, CARDGAME, OPENWORLD, PAUSE, MENU;

    }

    // Declare a gamestate
    public GameState gameState;

    // Set up rects for touchinput
    private Rect movementRect;

    // Declare an instance of multi-touch listener
    protected MultitouchListener touchListener;

    // Card game logic variables - may need to move this

    public boolean playerTurn;

    // Card objects
    public MonsterCard DataAdmin;

    // Declare all Sprites here - always include "Sprite" after the name of the image

    public Bitmap DataAdminSprite;

    public String [] attack1ManaRequired;
    public String [] attack2ManaRequired;



    // GameLoop Constructor
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

        // load assets
        loadAssets ();

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
                        updateTouch ();
                        break;
                    case CARDGAME:

                        // updateTouch ();
                        break;
                    case OPENWORLD:

                        break;
                    case PAUSE:

                        break;

                    case MENU:

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
                        doThingy();

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
        // make rects for touch input
        movementRect = new Rect ((int) (78 * uiScaling), height / 2, (int) (width - 78 * uiScaling), (int) (height - 78 * uiScaling));

        // Assets are now loaded - declare cards here

        // Monster Cards
        // attack1ManaRequired =





        // DataAdmin = new MonsterCard(4, 5, 3, 2, DataAdminSprite, "DataAdmin", CardTypes.EECS, "Query", "Relation", CardLevel.GRAD, 100, 20, CardTypes.MEDICS);

        // Mana Cards

    }

    private void doThingy ()

    {
        System.out.println("Touch input was received and now thingy is being done");

    }

    private void loadAssets ()

    {
        AssetManager assetManager = fragment.getActivity().getAssets();

        // load in individual assets
        DataAdminSprite =  AssetLoader.loadBitmap(assetManager, "img/Matthew/SmallDataAdmin.png");

    }


}
