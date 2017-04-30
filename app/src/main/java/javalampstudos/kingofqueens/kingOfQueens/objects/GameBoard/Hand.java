package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import java.util.ArrayList;

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
        private ArrayList<MonsterCard> monsterCardArrayList;
        private ArrayList<ManaCard> manaCardArrayList;
        private ArrayList<SupportCard> supportCardArrayList;



        // augmented by brian on 27/03/2017

    public Hand() {
        this.hand = new BasicCard[MAXHANDSIZE];
        this.monsterCardArrayList = new ArrayList<MonsterCard>(2);
        this.manaCardArrayList = new ArrayList<ManaCard>(2);
        this.supportCardArrayList = new ArrayList<SupportCard>(2);
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

    public ArrayList<MonsterCard> getMonsterCardArrayList() {
        return monsterCardArrayList;
    }

    public void setMonsterCardArrayList(ArrayList<MonsterCard> monsterCardArrayList) {
        this.monsterCardArrayList = monsterCardArrayList;
    }

    public ArrayList<ManaCard> getManaCardArrayList() {
        return manaCardArrayList;
    }

    public void setManaCardArrayList(ArrayList<ManaCard> manaCardArrayList) {
        this.manaCardArrayList = manaCardArrayList;
    }

    public ArrayList<SupportCard> getSupportCardArrayList() {
        return supportCardArrayList;
    }

    public void setSupportCardArrayList(ArrayList<SupportCard> supportCardArrayList) {
        this.supportCardArrayList = supportCardArrayList;
    }

    /*// 40111707
        // brians method to accsess elements within the hand array.
        public void getCardFromHand(int index) {
            if(validateHandIndex(index)){
                switch (hand[index].id)
                {
                    case 0: callCorrectMonsterCard(hand[index]); break;
                    case 1 : callCorrectManaCard(hand[index]); break;
                    case 2: callCorrectSupportCard(hand[index]); break;
                }
            }

        }*/



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
        public void removeFromHandArray(int index) {
            // sets the corresponding index of the hand array to null, thus removing the card.
            if(validateHandIndex(index)) {
                this.hand[index] = null;
            }
        }



    //40111707
    // Brians method
    // this method will comare the hand card with the correct card within the deck so that logic can be done to it.
    public int [] findRealCard(BasicCard cardToBeFound)
    {
        int pos =-1;
        int arrayType = -1;
        int[] position = new int [1];
        switch(cardToBeFound.id)
        {
            case 0:
                for (int i = 0; i <monsterCardArrayList.size(); i++) {
                    if(cardToBeFound.sprite == monsterCardArrayList.get(i).sprite)
                    {
                        pos = i ;
                        arrayType = cardToBeFound.id;
                    }
                } break;
            case 1:
                for (int i = 0; i <manaCardArrayList.size(); i++) {
                    if(cardToBeFound.sprite == manaCardArrayList.get(i).sprite)
                    {
                        pos = i ;
                        arrayType = cardToBeFound.id;
                    }
                } break;
            case 2:
                for (int i = 0; i <supportCardArrayList.size(); i++) {
                    if(cardToBeFound.sprite == supportCardArrayList.get(i).sprite)
                    {
                        pos = i ;
                        arrayType = cardToBeFound.id;
                    }
                } break;

            default :    break;
        }
        position [0] = pos;
        position[1] = arrayType;
        return position;

    }


    //40111707
    // brians method
    // this method will find the correct monstercard within the correct array and correct index.
    public MonsterCard callCorrectMonsterCard(BasicCard card)
    {
        int [] index = findRealCard(card);
        if(index[0] == 0)
        {
            return monsterCardArrayList.get(index[1]);
        }
        return null;
    }


    //40111707
    // brians method
    // this method will find the correct ManaCard within the correct array and correct index.
    public ManaCard callCorrectManaCard(BasicCard card)
    {
        int [] index = findRealCard(card);
        if(index[0] == 1)
        {
            return manaCardArrayList.get(index[1]);
        }
        return null;
    }


    //40111707
    // brians method
    // this method will find the correct SupportCard within the correct array and correct index.
    public SupportCard callCorrectSupportCard(BasicCard card)
    {
        int [] index = findRealCard(card);
        if(index[0] == 2)
        {
            return supportCardArrayList.get(index[1]);
        }
        return null;
    }




}
