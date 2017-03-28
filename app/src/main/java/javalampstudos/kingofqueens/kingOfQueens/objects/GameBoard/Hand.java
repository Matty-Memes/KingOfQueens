package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.basicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 13/03/2017.
 */

public class Hand extends GameObject {
    private basicCard[] hand;
    private final int MAXHANDSIZE = 10;
    private int currentHandSize;

// augmented by brian on 27/03/2017
    public Hand(float x, float y, int width, int height, Bitmap sprite, basicCard[] hand, int currentHandSize) {
        super(x, y, width, height, sprite);
        this.hand = new basicCard[getMAXHANDSIZE()];
        this.currentHandSize = currentHandSize;
    }

    //GETTERS AND SETTERS


    public basicCard[] getHand() {
        return hand;
    }

    public void setHand(basicCard[] hand) {
        this.hand = hand;
    }

    public int getCurrentHandSize() {
        return currentHandSize;
    }

    public void setCurrentHandSize(int currentHandSize) {
        this.currentHandSize = currentHandSize;
    }

    public int getMAXHANDSIZE() {
        return MAXHANDSIZE;
    }

// 40111707
    // brians method to accsess elements within the hand array.
    public basicCard getCardFromHand (int index){

        return this.hand[index];
    }
// 40111707
// brians method
    public void AddToHandArray(basicCard card, int index)
    {
        this.hand[index] = card;
    }
}
