package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import java.util.HashMap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardLevel;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.graveYard;

/**
 * Created by brian on 06/04/2017.
 * THIS IS WHERE THE AI LOGIC WILL GO FROM NOW ON.
 */

public class AiPlaySpace extends PlaySpace {


    public AiPlaySpace(float x, float y, int width, int height, Bitmap sprite, boolean player, Deck deck, Hand hand, int life, graveYard graveYard, ManaCounter manaCounter, CardZone[] cardZones, int currentCard, int deckSize) {
        super(x, y, width, height, sprite, player, deck, hand, life, graveYard, manaCounter, cardZones, currentCard, deckSize);
    }

    // 40111707 brians
        // this method will find the highest attack card that can be played.
        // use for putting level 1 cards in play
        public void PlayCardWithHighestAtt(Hand hand, MonsterCard enemeyCard, ManaCounter unusedMana) {

            int bestCardIndex = 0;

            // for loop begins at one so that first card can be compared to the second.
            for (int i = 1; i < hand.getHand().length; i++) {// within here i need to make sure only monster cards will be accepted.
                // this if statement checks 3 things.
                //1. the card is higher attack than enemeys defence
                // 2.the card is also higher than the best card so far
                // 3.the cards mana also allows it to be played.

                if (hand.getCardFromHand(i) instanceof MonsterCard) {

                    if (((MonsterCard) hand.getCardFromHand(i)).getAttackValue() > enemeyCard.getDefence()
                            && ((MonsterCard) hand.getCardFromHand(i)).getLevel() == CardLevel.UNDERGRAD
                            && ((MonsterCard) hand.getCardFromHand(i)).getAttackValue() > ((MonsterCard) hand.getCardFromHand(bestCardIndex)).getAttackValue() // there needs to be a method to convert from card school to card mana type.
                            && compareManaRequirementWithManaCounter(((MonsterCard)hand.getCardFromHand(i)).getAttackManaRequirement(),unusedMana.getUnusedManaHashMap()))

                    {

                        bestCardIndex = i;
                    }


                }
            }
            playCard(hand.getCardFromHand(bestCardIndex));
        }



        // brians method,
        // this method will choose which zone to play the card on then drag it to that zone.
        //40111707
        public void playCard(BasicCard card) {
            boolean found = false;
            // you need to allocate a zone for the card to be sent to
            // requires a seek method, it should send the card to the correct zone, then also give the card zone that card as its current card.
            if(!checkAllZonesAreActive()) {
                for(int i=0; i < getCardZones().length && !found;i++)
                {
                    if(!getCardZones()[i].isActive())
                    {
                        getCardZones()[i].setCurrentCard(card);
                        found =true;
                    }
                }
            }
            else
            {
                // the card cannot be played - there is no room.
            }

        }

        // brians method
        // 40111707
        public boolean compareManaRequirementWithManaCounter(HashMap<ManaTypes,Integer> manaRequirement, HashMap<ManaTypes,Integer> manaCounter){
            for (ManaTypes key : manaRequirement.keySet()) {
                if (manaRequirement.get(key) > manaCounter.get(key)) {
                   /* System.out.println("they are not equal");  THIS IS FOR TESTING IT
                    System.out.println("current mana for that type" + manaCounter.get(key) + " mana required"
                            + manaRequirement.get(key));*/
                    return false;
                }

            }
            return true;
        }

        //brians method
        //40111707
        // this method allows the Ai to check if it is holding mana within its hand, if it is it should be played.
        // at the minute it is only playing the first mana type that it comes across.
        public void playMana(Hand hand){
            int i =0;
            boolean manaCardFound =false;
            while( manaCardFound = false){// NOTE THIS STILL NEEDS TO BE TESTED. IT SHOULD COMPARE THE ENUMS TO STRINGS THEN SEE IF CARD SCHOOLS IS CONTAINTED WITHIN THE MANATYPE.
                if(hand.getCardFromHand(i) instanceof ManaCard && whichManaDoINeedTheMost(hand).toString().startsWith( hand.getCardFromHand(i).getCardSchool().toString()))
                {
                    manaCardFound =true;
                    getManaCounter().addMana(((ManaCard) hand.getCardFromHand(i)).getManaType());
                }
                i++;
            }
        }

        // brians method
        // 40111707
        // this method finds the manatype that the Ai will need the most depeneing on the cards that are
        // currently in its hand
        public ManaTypes whichManaDoINeedTheMost(Hand hand){
            HashMap<ManaTypes,Integer> temp = new HashMap<ManaTypes,Integer>();
            int highestNeedMana=0;
            ManaTypes key1 = null;

            // this loop is used to count all of the mana requirements of the monstercards within the hand.
            for(int i=0; i < hand.getHand().length;i++ )
            {
                if(hand.getCardFromHand(i) instanceof  MonsterCard)
                {
                    for (ManaTypes key:getManaCounter().getManaCounterHashMap().keySet())
                    {
                       temp.put(key,((MonsterCard)hand.getCardFromHand(i)).getAttackManaRequirement().get(key)) ;
                    }

                }

            }
            // this loop is used to find the mana type that will have the highest need.
            for (ManaTypes key:temp.keySet())
            {
                if(temp.get(key) > highestNeedMana)
                {
                    highestNeedMana = temp.get(key);

                }
            }


            // this loop is for finding the key of the manaType that has the highest need.
            for (ManaTypes key:temp.keySet())
            {
             if(temp.get(key) == highestNeedMana)
             {
                 key1 = key;
             }
            }


            // finally reutnring the key for the mana type that has the highest need.
            return key1;
        }










}
