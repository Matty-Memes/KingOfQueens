package javalampstudos.kingofqueens;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Matrix;
import android.view.WindowManager;

// Local imports

import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.boardLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameViewFragment extends CanvasFragment {

    // instantiate a loop
    protected GameLoop loop;

    // paint objects/s
    private Paint paint;

    // BITMAPS
    private Bitmap backgroundImage, pauseBitmap;
    // RECTS
    private Rect backgroundImageRect;
    // STRINGS - For the pause menu and the win and lose cases
    private String pauseString, mainMenuString, resumeString, restartString;

    // Width and heihgt for creating the view
    private int width, height;

    // Empty GameViewFragment constructor
    public GameViewFragment ()

    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ((MainActivity) getActivity()).hideNav();

        WindowManager wm = ((WindowManager) getActivity().getSystemService(
                Context.WINDOW_SERVICE));
        Display display = wm.getDefaultDisplay();
        Point point = new Point();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            display.getRealSize(point);
        else
            display.getSize(point);

        width = point.x;


        height = point.y;

        // instantiate the loop and start things running
        loop = new GameLoop(this, width, height);
        return loop.canvasRenderer;


    }


    // SETUP AND DRAW METHODS


    // The render thread calls this - see Canvas Renderer

    // Load in all the pause stuff
    public void doSetup() {

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),
                "minecraftia.ttf"));

        // create an AssetManager
        AssetManager assetManager = getActivity().getAssets();
        // draw the background
        backgroundImage = AssetLoader.loadBitmap(assetManager, "img/Nathan/cloudyBackground.png");
        pauseBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/Pause.png");
        // create a rect for the background
        backgroundImageRect = new Rect(0, 0, width, height);

        pauseString = "Pause";
        mainMenuString = "Main Menu";
        resumeString = "Resume";
        restartString = "Restart";

    }

    /**
     * Method that will be called by the render thread when the canvas needs to
     * be redrawn
     *
     * @param canvas
     *            Canvas to be redrawn
     */

    /*

    private Rect mLittleManBound;
    private Rect cloudyBackgroundBound;
    private Rect KofQBound;
    private Rect mLittleManBound2;

    */


    // tidy this up
    public void doDraw(Canvas canvas) {

        canvas.drawBitmap(backgroundImage, null, backgroundImageRect, null);

        // list all cases so it's in sync with game loop

        switch (loop.gameState)

        {
            case NEW:

                break;

            case PAUSED:

                drawCard(canvas);

                drawMonsterCards(canvas);

                drawHand(canvas);

                // draw the text for the mana counter
                drawMana(canvas);

                drawUI(canvas);

                canvas.drawARGB(127, 0, 0, 0);

                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize(32 * loop.uiScaling);
                canvas.drawText(pauseString, width / 2, 66 * loop.uiScaling, paint);

                canvas.drawText(resumeString, width / 2, height / 2 - 8
                        * loop.uiScaling, paint);
                canvas.drawText(restartString, width / 2, height / 2 + 48
                        * loop.uiScaling, paint);
                canvas.drawText(mainMenuString, width / 2, height / 2 + 104
                        * loop.uiScaling, paint);

                break;

            case PROMPT:

                drawCard(canvas);

                drawMonsterCards(canvas);

                drawHand(canvas);

                // dim the screen - make it obvious that the player can't interact
                canvas.drawARGB(127, 0, 0, 0);

                drawPrompt(canvas);

                break;
            case AITURN:

                drawCard(canvas);

                drawMonsterCards(canvas);

                drawHand(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;
            case DRAW:
                drawCard(canvas);

                drawMonsterCards(canvas);

                drawHand(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;

            case MANAPLACEMENT:

                drawCard(canvas);

                drawMonsterCards(canvas);

                drawHand(canvas);

                // draw the text for the mana counter
                drawMana(canvas);

                drawUI(canvas);

                break;
            case MONSTERPLACEMENT:

                drawCard(canvas);

                drawMonsterCards(canvas);

                drawHand(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;

            case ATTACK:

                drawCard(canvas);

                drawMonsterCards(canvas);

                drawHand(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;
        }


    }

    public void onPause()

    {

        loop.pause();
        super.onPause();
    }

    public void onResume()

    {
        super.onResume();
        loop.resume();

    }

    // draw the current card

    private void drawCard (Canvas canvas)

    {
        // draw temp cards for positioning

        // hands
        /*loop.handCard1.draw(canvas);
        loop.handCard2.draw(canvas);
        loop.handCard3.draw(canvas);
        loop.handCard4.draw(canvas);
        loop.handCard5.draw(canvas);*/

        loop.monsterCard1.draw(canvas);
        loop.monsterCard2.draw(canvas);
        loop.monsterCard3.draw(canvas);

        // draw opponent card
        loop.opponent1.draw(canvas);
        loop.opponent2.draw(canvas);
        loop.opponent3.draw(canvas);

        loop.manaZone.draw(canvas);

        // deck and graveyard
        loop.graveYard.draw(canvas);
        loop.deck.draw(canvas);
    }

    private void drawUI (Canvas canvas)

    {
        canvas.drawBitmap(pauseBitmap, null, boardLayout.pauseRect, paint);

        // Paints time at bottom of game screen

        paint.setTextSize(16 * loop.uiScaling);

        paint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(String.format("%02d:%02d",loop.timer.minutes,
                loop.timer.seconds), width / 2, height + 2 * loop.uiScaling,
                paint);

    }

    // draw the prompt during the prompt phase
    private void drawPrompt (Canvas canvas)

    {
        loop.window.draw(canvas);

    }

    // This needs changed anyway
    private void drawMana(Canvas canvas)

    {
        loop.Medic.draw(canvas);
        loop.artsAndHumanities.draw(canvas);
        loop.engineering.draw(canvas);
        loop.eeecs.draw(canvas);
        loop.builtEnvironment.draw(canvas);
    }

    // DRAW SETS OF CARDS

    private void drawHand (Canvas canvas)

    {
        for (int i = 0; i < loop.handCards.size(); i++)

        {
            loop.handCards.get(i).draw(canvas);

        }

    }

    private void drawMonsterCards(Canvas canvas)

    {
        loop.monsterCard1.draw(canvas);
        loop.monsterCard2.draw(canvas);
        loop.monsterCard3.draw(canvas);

      /*  for(int i =0; i < loop.player.getCardZones().length; i++)
        {
            loop.player.getCardZones()[i].getCurrentCard().draw(canvas);
        }*/

    }

    /*

    private void drawOpponentMonsters(Canvas canvas)

    {
     // draw opponent card
        loop.opponentCard1.draw(canvas);
        loop.opponentCard2.draw(canvas);
        loop.opponentCard3.draw(canvas);


    }


    // draw the monster cards using a loop
    private void drawMonsterCards (Canvas canvas)

    {
        for (int i = 0; i < loop.monstersInPlay.size(); i++)

        {

            loop.monstersInPlay.get(i).draw(canvas);

        }

    }

    */

}
