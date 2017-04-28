package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardLevel;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Andrew on 29/03/2017.
 */

public class Hand

{
    /**
     * Created by Matt on 13/03/2017.
     */


        private final int MAXHANDSIZE = 4;
        private BasicCard[] hand;



        // augmented by brian on 27/03/2017

    public Hand() {
        hand = new BasicCard[MAXHANDSIZE];
    }


    //GETTERS AND SETTERS


    public int getMAXHANDSIZE() {
        return MAXHANDSIZE;
    }

    public BasicCard[] getHand() {
        return hand;
    }

    public void setHand(BasicCard[] hand) {
        this.hand = hand;
    }

    // 40111707
        // brians method to accsess elements within the hand array.
        public BasicCard getCardFromHand(int index) {
            if(validateHandIndex(index)){
                return this.hand[index];
            }
            return null;
        }



    // 40111707
    // brians method
    // used to validate an index being searched for is acutaly one within the hand.
    public boolean validateHandIndex(int index)
    {
        if(index < hand.length && index >= 0)
        {
            return true;
        }
        else return  false;
    }
        // 40111707
// brians method
        public void addToHandArray(BasicCard card, int index) {
            if(validateHandIndex(index)) {
                this.hand[index] = card;
            }
        }

        // 40111707
        // brians method
        public void removeFromHandArray(int index) {
            // sets the corresponding index of the hand array to null, thus removing the card.
            if(validateHandIndex(index)) {
                this.hand[index] = null;
            }
        }





}
