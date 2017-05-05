package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

/**
 * Created by 40083349 on 14/03/2017.
 */

// declare all rects here

import android.graphics.Rect;


public class boardLayout

{
    public static Rect movementRect;
    // hand rects
    public static Rect handRect1, handRect2, handRect3, handRect4, handRect5;
    // monster slots
    public static Rect MSlot1Rect, MSlot2Rect, MSlot3Rect;
    // graveyard rect
    public static Rect graveYardRect;
    // movement rect
    public static Rect attackRect, playerMovementRect;
    // mana
    public static Rect manaRect;
    // deck rect
    public static Rect deckRect;

    // Opponent Rects
    public static Rect opponent1Rect, opponent2Rect, opponent3Rect;

    // MOVE TO SEPERATE CLASSES SINCE THEY DON'T RELATE TO THE BOARD

    // Menu Rect/Rects
    public static Rect pauseRect, resumeRect, restartRect, mainMenuRect;

    // User Prompts/Messages
    public static Rect aiRect;


    // constructor
    public boardLayout(float width, float height, float uiScaling)

    {
        initializeRects(width, height, uiScaling);
    }


    // call this during gameSetup
    public void initializeRects (float width, float height, float uiScaling)

    {
        // Instantiate new rects here

        movementRect = new Rect ( 0, 0, (int)width, (int)height);

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

        // Rects for the opponent cards
        opponent1Rect = new Rect(
                (int) (234 - (90 / 2)),
                (int) (100 - (120 / 2)),
                (int) (234 + (90 / 2)),
                (int) (100 + (120 / 2)));

        opponent2Rect = new Rect(
                (int) (234 - (90 / 2)),
                (int) (100 - (120 / 2)),
                (int) (234 + (90 / 2)),
                (int) (100 + (120 / 2)));

        opponent3Rect = new Rect(
                (int) (234 - (90 / 2)),
                (int) (100 - (120 / 2)),
                (int) (234 + (90 / 2)),
                (int) (100 + (120 / 2)));

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

        // playerArea
        playerMovementRect = new Rect((int) 0, (int) (height / 2), (int) width, (int) height);

        // mana stuff
        manaRect = new Rect(
                (int) (100 - (140 / 2)),
                (int) (340 - (240 / 2)),
                (int) (100 + (140 / 2)),
                (int) (340 + (240 / 2)));

        // no movement any lower than the bottom of the monster cards
        attackRect = new Rect(0, 0, 679, 340);

        pauseRect = new Rect(755, 50, 805, 100);

        resumeRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 - 58 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 - 10 * uiScaling));
        restartRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 - 2 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 + 46 * uiScaling));
        mainMenuRect = new Rect((int) (width / 2 - 128 * uiScaling),
                (int) (height / 2 + 54 * uiScaling),
                (int) (width / 2 + 128 * uiScaling),
                (int) (height / 2 + 102 * uiScaling));

        // For the aithinking message
        aiRect = new Rect(107, 120, 747, 360);

    }

}
