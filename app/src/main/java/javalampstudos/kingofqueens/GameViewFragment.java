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
import javalampstudos.kingofqueens.kingOfQueens.engine.io.Dialogue;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.boardLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameViewFragment extends CanvasFragment {

    // instantiate a loop
    protected GameLoop loop;

    private Point playerPoint;

    // image stuff that the whole class can see
    private Paint mPaint;

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

        int spacingX = width / 6;
        int spacingY = height / 6;

        mPaint = new Paint();
        playerPoint = new Point(150, 150);

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

            case OPENWORLD:

                drawOpenWorld(canvas);

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

                drawMana(canvas);

                drawUI(canvas);

                break;
            case DRAW:
                drawCard(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;
            case ANIMATION:

                drawCard(canvas);

                drawMana(canvas);

                drawUI(canvas);

            case AIANIMATION:

                drawCard(canvas);

                // draw the text for the mana counter
                drawMana(canvas);

                drawUI(canvas);

            case MANAPLACEMENT:

                drawCard(canvas);

                drawHand(canvas);

                drawUI(canvas);

                break;
            case MONSTERPLACEMENT:

                drawCard(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;

            case ATTACK:

                drawCard(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;
            case VICTORY:

                drawVictory(canvas);

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

    public void drawOpenWorld(Canvas canvas)

    {

        int spacingX = width / 10;
        int spacingY = height / 10;

        loop.mapTest.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);
        //loop.coin.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);

        for (int i = 0; i < loop.objectList.size(); i++) {
            loop.objectList.get(i).drawOpenWorld(canvas, loop.mLayerViewport, loop.mScreenViewport);
        }

        for (int i = 0; i < loop.coinList.size(); i++) {
            loop.coinList.get(i).drawOpenWorld(canvas, loop.mLayerViewport, loop.mScreenViewport);
        }

        loop.player.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);
        loop.mcclayTop.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);
        loop.lanyonTop.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);
        //loop.player2.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);

        /*if(loop.interactionIndex >= 0) {
            System.out.println(Dialogue.conversationIndex(loop.interactionIndex));
        }*/
        if(loop.interactionIndex >= 0) {
            if(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint) != "None") {
                canvas.clipRect(spacingX, spacingY * 5, spacingX * 9, spacingY * 9);
                canvas.drawColor(-1);
                mPaint.setTextSize(150.0f);
                mPaint.setTextAlign(Paint.Align.LEFT);
                mPaint.setColor(Color.BLACK);

                if(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint) == "Coin" &&
                        Dialogue.shopConvo(loop.dialoguePoint, loop.coinCounter) != "None") {
                    canvas.drawText(Dialogue.shopConvo(loop.dialoguePoint, loop.coinCounter),
                            canvas.getClipBounds().left + spacingX / 2, canvas.getClipBounds().top + spacingY, mPaint);
                } else {
                    if(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint) != "Coin") {
                        canvas.drawText(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint),
                                canvas.getClipBounds().left + spacingX / 2, canvas.getClipBounds().top + spacingY, mPaint);
                    } else {
                        loop.dialoguePoint = 0;
                        loop.inDialogue = false;
                        loop.interactionIndex = -1;
                    }
                }
            } else {
                loop.dialoguePoint = 0;
                loop.inDialogue = false;
                loop.interactionIndex = -1;
            }

        }

        /*if(loop.interactionIndex >= 0) {

            if(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint) == "Coin" &&
                    Dialogue.shopConvo(loop.dialoguePoint, loop.coinCounter) != "None") {
                canvas.drawText(Dialogue.shopConvo(loop.dialoguePoint, loop.coinCounter),
                        canvas.getClipBounds().left + spacingX / 2, canvas.getClipBounds().top + spacingY, mPaint);
            }

            else if(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint) != "None") {
                canvas.clipRect(spacingX, spacingY * 5, spacingX * 9, spacingY * 9);
                canvas.drawColor(-1);
                mPaint.setTextSize(150.0f);
                mPaint.setTextAlign(Paint.Align.LEFT);
                mPaint.setColor(Color.BLACK);
                canvas.drawText(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint),
                        canvas.getClipBounds().left + spacingX / 2, canvas.getClipBounds().top + spacingY, mPaint);

            } else {
                loop.dialoguePoint = 0;
                loop.inDialogue = false;
                loop.interactionIndex = -1;
            }

        }*/

        canvas.drawBitmap(loop.aButton, null, loop.interactButton, null);


        /*String mText = "What Up!!";
        for (int i = 0; i < mText.length(); i++) {
            canvas.drawText(mText.substring(i, i + 1), canvas.getClipBounds().left + spacingX / 2 + i * 30, canvas.getClipBounds().top + spacingY * 2, mPaint);
        }*/

        // might be able to add text

        /*

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Draw the loaded bitmap 1,000 times
        int batchSize = 1000;
        for (int drawIdx = 0; drawIdx < batchSize; drawIdx++) {


            int spacingX = width / 6;
            int spacingY = height / 6;
            mLittleManBound  = new Rect(2 * spacingX, spacingY, 4 * spacingX, 5 * spacingY);
            cloudyBackgroundBound = new Rect (0, 0, 6 * spacingX, 6 * spacingY);
            KofQBound = new Rect (10, 4* spacingY, 2 * spacingX, 6 * spacingY);
            //mLittleManBound2 = new Rect(4 * spacingX, spacingY, 5 * spacingX, 2 * spacingY);

            //canvas.drawColor(-1);

            // these lines are replaced with the first line
            canvas.drawBitmap(mImage2, null, cloudyBackgroundBound, null);
            canvas.drawBitmap(mImage, null, mLittleManBound, null);
            canvas.drawBitmap(mImage3, null, KofQBound, null);
            //canvas.drawBitmap(mImage, null, mLittleManBound2, null);
        }

        // Display a count of the number of frames that have been displayed
        mNumCalls++;
        mPaint.setTextSize(36.0f);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("Num=" + mNumCalls, 50.0f, 50.0f, mPaint);
        */

    }

    // draw the current card

    private void drawCard (Canvas canvas)

    {

        loop.handCard1.draw(canvas);
        loop.handCard2.draw(canvas);
        loop.handCard3.draw(canvas);
        loop.handCard4.draw(canvas);
        loop.handCard5.draw(canvas);

        loop.monsterCard1.draw(canvas);
        loop.monsterCard2.draw(canvas);
        loop.monsterCard3.draw(canvas);

        // draw opponent card
        loop.opponent1.draw(canvas);
        loop.opponent2.draw(canvas);
        loop.opponent3.draw(canvas);

        loop.opponentDeck.draw(canvas);

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

    private void drawVictory(Canvas canvas)

    {
      loop.victory.draw(canvas);
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
