package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardLevel;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Andrew on 29/03/2017.
 */

public class Hand extends GameObject

{
    /**
     * Created by Matt on 13/03/2017.
     */


        private final int MAXHANDSIZE = 10;
        private BasicCard[] hand;
        private int currentHandSize;

        // augmented by brian on 27/03/2017
        public Hand(float x, float y, int width, int height, Bitmap sprite, boolean player, BasicCard[] hand, int currentHandSize) {
            super(x, y, width, height, sprite, player);
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
            if(validateHandIndex(index)){
                return this.hand[index];
            }
            return null;
        }
/*
        //40111707
    //brians method
    // this method allows me to return only a monsterCard for the Ai methods.
    public MonsterCard returnMonsterCardFromHand (int index){
        if(validateHandIndex(index)) {
            if (hand[index] instanceof MonsterCard) {
                float x = hand[index].x;
                float y = hand[index].y;
                int width = hand[index].width;
                int height = hand[index].height;
                boolean player = hand[index].player;
                int id = hand[index].id;
                CardSchools cardschool = hand[index].getCardSchool();
                boolean destroyed = hand[index].destroyed;
                int pointerID = hand[index].pointerID;
                CardLevel cardlevel = ((MonsterCard)hand[index]).getLevel();

                MonsterCard temp = new MonsterCard(x,y,width,height,player,id,cardschool,destroyed,pointerID,cardlevel);


                return temp;
            }

        }
        return null;
    }*/


    // 40111707
    // brians method
    // used to validate an index being searched for is acutall one within the hand.
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
