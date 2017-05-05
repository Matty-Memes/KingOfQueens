package javalampstudos.kingofqueens.kingOfQueens.util;

/**
 * Created by Andrew on 05/05/2017.
 */

import javalampstudos.kingofqueens.GameLoop;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;

import javalampstudos.kingofqueens.kingOfQueens.util.Direction;

public class CardAnimation

{

    public CardAnimation ()

    {


    }

    // public boolean boundHit;
    // manage the boundHit boolean inside this class

    // Add a direction

    public void updateCardAnimation (GameLoop loop, float speed, BasicCard card,  boolean boundHit)

    {
        System.out.println("Animating AI");

        // move the hand card left till it hits it's target position
        // associate the target position with the card
        if (!boundHit) {
            card.x += speed;
            // the proper position of any card is tied to that individual card
            if (card.x >= card.targetX) {
                boundHit = true;
                // Control should return to the method which called it
                loop.gameState = GameLoop.GameState.DRAW;
            }

        }

    }



}
