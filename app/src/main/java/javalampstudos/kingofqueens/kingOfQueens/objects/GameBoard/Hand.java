package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Andrew on 29/03/2017.
 */

public class Hand

{
    /**
     * Created by Matt on 13/03/2017.
     */

    public class hand extends GameObject {
        private final int MAXHANDSIZE = 10;
        private BasicCard[] hand;
        private int currentHandSize;

        // augmented by brian on 27/03/2017
        public hand(float x, float y, int width, int height, Bitmap sprite, BasicCard[] hand, int currentHandSize) {
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

        // 40111707
        // brians method to accsess elements within the hand array.
        public BasicCard getCardFromHand(int index) {

            return this.hand[index];
        }

        // 40111707
// brians method
        public void addToHandArray(BasicCard card, int index) {
            this.hand[index] = card;
        }

        // 40111707
        // brians method
        public void removeFromHandArray(int index) {
            // sets the corresponding index of the hand array to null, thus removing the card.
            this.hand[index] = null;
        }
    }




}
