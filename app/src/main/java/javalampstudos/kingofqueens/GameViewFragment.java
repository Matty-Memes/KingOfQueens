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
    private Bitmap backgroundImage, pauseBitmap, victoryScreen;;
    // RECTS
    private Rect backgroundImageRect;
    // STRINGS - For the pause menu and the win and lose cases
    private String pauseString, mainMenuString, resumeString, restartString, forfeitString;

    // Width and heihgt for creating the view
    private int width, height;

    //States of Dialogue
    private enum DialogueState {
        REGULAR, SHOP, OPTIONS, RESPONSE, SWITCH, NONE
    }

    //Boolean for resetting DialoguePoint variable
    private boolean resetDialoguePoint = true;

    private DialogueState dialogueState;

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
                "txt/OpenSans-BoldItalic.ttf"));

        // create an AssetManager
        AssetManager assetManager = getActivity().getAssets();
        // draw the background
        victoryScreen = AssetLoader.loadBitmap(assetManager, "img/Screens/Victory.png");
        backgroundImage = AssetLoader.loadBitmap(assetManager, "img/Screens/KoQBoard.png");
        pauseBitmap = AssetLoader.loadBitmap(assetManager, "img/Marc/Pause.png");
        // create a rect for the background
        backgroundImageRect = new Rect(0, 0, width, height);

        pauseString = "Pause";
        mainMenuString = "Main Menu";
        resumeString = "Resume";
        restartString = "Restart";
        forfeitString = "Forfeit";

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

                canvas.drawText(resumeString, width / 2, height / 2 - 13
                        * loop.uiScaling, paint);
                canvas.drawText(restartString, width / 2, height / 2 + 43
                        * loop.uiScaling, paint);
                canvas.drawText(forfeitString, width / 2, height / 2 + 99
                        * loop.uiScaling, paint);
                canvas.drawText(mainMenuString, width / 2, height / 2 + 155
                        * loop.uiScaling, paint);

                break;

            case PROMPT:

                drawCard(canvas);

                drawMana(canvas);

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

                break;

            case AIANIMATION:

                drawCard(canvas);

                // draw the text for the mana counter
                drawMana(canvas);

                drawUI(canvas);

                break;

            case MANAPLACEMENT:

                drawCard(canvas);

                drawHand(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;
            case MONSTERPLACEMENT:

                drawCard(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;
            case DAMAGE:

                drawCard(canvas);

                drawDamage(canvas);

                drawMana(canvas);

                break;
            case ATTACK:

                drawDamage(canvas);

                drawCard(canvas);

                drawMana(canvas);

                drawUI(canvas);

                break;
            case VICTORY:

                canvas.drawBitmap(victoryScreen, null, backgroundImageRect, null);

                break;


        }

    }

    // new array list of particles in Game Loop

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

    //Draw OpenWorld
    //Nathan-   40131544
    public void drawOpenWorld(Canvas canvas)

    {

        //Setup spacing to set up Rect in context of the screen
        int spacingX = width / 10;
        int spacingY = height / 10;

        int clipSpacingX = canvas.getClipBounds().width() / 10;
        int clipSpacingY = canvas.getClipBounds().height() / 10;

        //Draw map
        loop.mapTest.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);
        //loop.coin.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);

        //Draw objects from objectList
        for (int i = 0; i < loop.objectList.size(); i++) {
            loop.objectList.get(i).drawOpenWorld(canvas, loop.mLayerViewport, loop.mScreenViewport);
        }

        //Draw coins from coinList
        for (int i = 0; i < loop.coinList.size(); i++) {
            loop.coinList.get(i).drawOpenWorld(canvas, loop.mLayerViewport, loop.mScreenViewport);
        }

        //Draw player and buildings
        loop.player.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);
        loop.mcclayTop.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);
        loop.lanyonTop.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);
        //loop.player2.draw(canvas, loop.mLayerViewport, loop.mScreenViewport);



        dialogueState = DialogueState.NONE;


        //Check if the player has interacted with an NPC
        if (loop.interactionIndex >= 0) {


            //At this point we know that we need to start dialogue//

            //Setup canvas and paint
            canvas.clipRect(spacingX, spacingY * 5, spacingX * 9, spacingY * 9);
            canvas.drawColor(-1);
            mPaint.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "minecraftia.ttf"));
            mPaint.setTextSize(clipSpacingY - clipSpacingY / 4);
            System.out.println(canvas.getClipBounds().height());
            mPaint.setTextAlign(Paint.Align.LEFT);
            mPaint.setColor(Color.BLACK);


            ////////////////////////////////////////////////
            //Code to determine what the dialogue state is//
            ////////////////////////////////////////////////


            //Assume dialogueState is REGULAR to start
            dialogueState = DialogueState.REGULAR;

            //If "Coin" is returned then dialogueState is  set to SHOP
            if(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint, loop.response) == "Coin") {
                dialogueState = DialogueState.SHOP;
            }


            //If "OPTIONS" is returned then dialogueState is set to OPTIONS, loop.response = 0 to notify the GameLoop class
            //that a response is required from the user, dialoguePoint is reset for a new conversation.
            //If loop.response is 0, the code is awaitng user response after the dialogue options have been drawn
            if(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint, loop.response) == "Options" ||
                    loop.response == 0) {
                loop.response = 0;
                loop.dialoguePoint = 1;
                dialogueState = DialogueState.OPTIONS;
            }


            //If response is greater than 0, user has given their response. DialoguePoint is set the first time the statement
            //is accessed and dialogueState is set to REGULAR to allow dialogue to continue normally
            if(loop.response > 0) {

                if (resetDialoguePoint == true) {
                    loop.dialoguePoint = 1;
                }

                resetDialoguePoint = false;
                dialogueState = DialogueState.REGULAR;
            }

            if(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint, loop.response) == "Switch") {
                dialogueState = DialogueState.SWITCH;
            }


            //If "None" is returned from conversationIndex, the conversation has ended
            //If "Coin" is returned from shopConvo and "None" is returned from conversationIndex, the conversation with the
            //shopkeeper has ended.
            //      DialogueState is set to none for both
            if(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint, loop.response) == "None" ||
                    (Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint, loop.response) == "Coin" &&
                            Dialogue.shopConvo(loop.dialoguePoint, loop.coinCounter) == "None")) {
                dialogueState = DialogueState.NONE;
            }


            //At this point dialogueState has been determined


            //Once dialogue State has been assigned, run the right case
            switch(dialogueState) {

                case REGULAR:
                    canvas.drawText(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint, loop.response),
                            canvas.getClipBounds().left + clipSpacingX / 2, canvas.getClipBounds().top + clipSpacingY * 2, mPaint); break;

                case SHOP:
                    canvas.drawText(Dialogue.shopConvo(loop.dialoguePoint, loop.coinCounter),
                            canvas.getClipBounds().left + clipSpacingX / 2, canvas.getClipBounds().top + clipSpacingY * 2, mPaint); break;

                case OPTIONS:
                    //Reset colour to distinguish NPC lines from player dialogue options
                    mPaint.setColor(Color.BLUE);
                    //Draw both options
                    canvas.drawText(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint, loop.response),
                            canvas.getClipBounds().left + clipSpacingX / 2, canvas.getClipBounds().top + clipSpacingY * 2, mPaint);
                    loop.dialoguePoint++;
                    canvas.drawText(Dialogue.conversationIndex(loop.interactionIndex, loop.dialoguePoint, loop.response),
                            canvas.getClipBounds().left + clipSpacingX / 2, canvas.getClipBounds().top + clipSpacingY * 4, mPaint); break;

                case SWITCH:
                    loop.gameStateSwitch = true;

                case NONE:
                    //When reached, conversation has ended. Reset dialoguePoint, inDialogue, interactionIndex, response
                    //and resetDialoguePoint to return game to a free-roam gameplay
                    loop.dialoguePoint = 0;
                    loop.inDialogue = false;
                    loop.interactionIndex = -1;
                    loop.response = -1;
                    resetDialoguePoint = true; break;
            }


        }


        //Draw the interact button last
        canvas.drawBitmap(loop.aButton, null, loop.interactButton, null);
        if(dialogueState == DialogueState.NONE) {
            canvas.drawBitmap(loop.dPadSprite, null, loop.dPad, null);
        }

    }

    private void drawDamage (Canvas canvas)

    {

        loop.damageText.draw(canvas);

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

    }

    // draw the prompt during the prompt phase
    private void drawPrompt (Canvas canvas)

    {
        loop.userPrompt.draw(canvas);

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
