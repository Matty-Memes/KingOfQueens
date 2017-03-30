package javalampstudos.kingofqueens.kingOfQueens.AiEngine;




import java.util.HashMap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Hand;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.PlaySpace;
/*
 * Created by brian on 27/02/2017.
 */


public class boardGameStrategy {
    PlaySpace AiPlayer ; // NOTE:: THIS IS ONLY FOR REFERENCE IT NEEDS POPULATED


    // 40111707 brians + nathans method
    // this method will find the highest attack card that can be played.
    // in order for this method to be finished manacounter needs to be made into a hashmap.
    public void searchHandForCardWithHighestAttack(Hand hand, MonsterCard enemeyCard) {

        int bestCardIndex = 0;
        // for loop begins at one so that first card can be compared to the second.
        for (int i = 1; i < hand.getHand().length; i++) {// within here i need to make sure only monster cards will be accepted.
            // this if statement checks 3 things.
            //1. the card is higher attack than enemeys defence
            // 2.the card is also higher than the best card so far
            // 3.the cards mana also allows it to be played.

            if (hand.getCardFromHand(i) instanceof MonsterCard) {

                if (((MonsterCard) hand.getCardFromHand(i)).getAttackValue() > enemeyCard.getDefence()
                        && ((MonsterCard) hand.getCardFromHand(i)).getAttackValue() > ((MonsterCard) hand.getCardFromHand(bestCardIndex)).getAttackValue() // there needs to be a method to convert from card school to card mana type.
                        && compareManaRequirementWithManaCounter(((MonsterCard)hand.getCardFromHand(i)).getAttackManaRequirement(),AiPlayer.getManaCounter().getManaCounterHashMap()))

                {

                    bestCardIndex = i;
                }
                /*// this assumes the card that has the highest attack cannot be played,
				//so it simply checks for a card with a better attack than the player, with enough mana to be played.
                else if (((MonsterCard) hand.getCardFromHand(i)).getAttackValue() > enemeyCard.getDefence()
                        && compareManaRequirementWithManaCounter(((MonsterCard)hand.getCardFromHand(i)).getAttackManaRequirement(),AiPlayer.getManaCounter().getManaCounterHashMap())) {
                    bestCardIndex = i;
                }*/

                playCard(hand.getCardFromHand(bestCardIndex));
            }
        }
    }



// brians method,
    // this method will choose which zone to play the card on then drag it to that zone.
    //40111707
    public void playCard(BasicCard card) {

// you need to allocate a zone for the card to be sent to
// requires a seek method, it should send the card to the correct zone, then also give the card zone that card as its current card.
       if(!AiPlayer.checkAllZonesAreActive()) {
           if (!AiPlayer.getZoneLeft().isActive()) {
               //call the seek method to send the card there
               AiPlayer.getZoneLeft().setCurrentCard(card);
           } else if (!AiPlayer.getZoneMiddle().isActive()) {
               //call the seek method to send the card there
               AiPlayer.getZoneMiddle().setCurrentCard(card);
           } else if (!AiPlayer.getZoneRight().isActive()) {
               //call the seek method to send the card there
               AiPlayer.getZoneRight().setCurrentCard(card);
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
    public void playMana(Hand hand){
        int i =0;
        boolean manaCardFound =false;
        while( manaCardFound = false){
            if(hand.getCardFromHand(i) instanceof ManaCard)
            {
                manaCardFound =true;
                playCard(hand.getCardFromHand(i));
            }
            i++;
        }
    }

    // brians method
    // 40111707
    // make sure that it is the ai players mana counter that is pased in
    // the Ai's hand also needs to be passed in so that each monster cards mana requirement can be accsessed.
    public int whichManaDoINeedTheMost(Hand hand,HashMap<ManaTypes,Integer> manaCounter){
        int genericMana =0;
        int eeecsMana=0;
        int medicMana=0;
        int artsHumanitiesMana =0;
        int enegineeringMana =0;
        int socaulSciencesMana =0;

       // for(manaTypes key : )

        // you need to go through the hand, count each cards mana requirements
        // then check if i have a mana card of that type that has the most need
        // then it needs to be played.
        int j=0; // used only for return statement.
        return j;
    }





}
