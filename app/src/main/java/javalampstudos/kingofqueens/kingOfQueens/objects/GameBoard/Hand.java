package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 13/03/2017.
 */

public class Hand extends GameObject {
    private BasicCard[] hand;
    private final int MAXHANDSIZE = 10;
    private int currentHandSize;

// augmented by brian on 27/03/2017
    public Hand(float x, float y, int width, int height, Bitmap sprite, BasicCard[] hand, int currentHandSize) {
        super(x, y, width, height, sprite);
        this.hand = new BasicCard[getMAXHANDSIZE()];
        this.currentHandSize = currentHandSize;
    }

    //GETTERS AND SETTERS


    public BasicCard[] getHand() {
        return hand;
    }

    public void setHand(BasicCard[] hand) {
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
}
