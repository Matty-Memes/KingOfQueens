package javalampstudos.kingofqueens.kingOfQueens.AiEngine;

/**
 * Created by brian on 27/02/2017.
 */

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.ManaCounter;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.PlaySpace;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Hand;

public class boardGameStrategy {

   /*

   PlaySpace AiPlayer = new PlaySpace(); // still neeeds to be populated

// 40111707 brians method
    // this method will find the highest attack card that can be played.
    // in order for this method to be finished manacounter needs to be made into a hashmap.
    public void searchHandForCardWithHighestAttack(Hand hand, MonsterCard enemeyCard)
    {

        int bestCardIndex=0;
        // for loop begins at one so that first card can be compared to
        for (int i=1; i < hand.getHand().length; i++)
        {// within here i need to make sure only monster cards will be accepted.
            // this if statement checks 3 things.
            //1. the card is higher attack than enemeys defence
            // 2.the card is also higher than the best card so far
            // 3.the cards mana also allows it to be played.

            if(hand.etCardFromHand(i) instanceof MonsterCard)
            {

            if (((MonsterCard) hand.getCardFromHand(i)).getAttackValue() > enemeyCard.getDefence()
                    && ((MonsterCard) hand.getCardFromHand(i)).getAttackValue() > ((MonsterCard) hand.getCardFromHand(bestCardIndex)).getAttackValue()
                    && ((MonsterCard) hand.getCardFromHand(i)).getAttackValue() < ManaCounter.get(((MonsterCard) hand.getCardFromHand(i)).getCardSchool())) // manacounter still needs to be changed to a hashmap
            {
                bestCardIndex =i;
            }
            // this assumes the card that has the highest attack cannot be played,
//				so it simply checks for a card with a better attack than the player, with enough mana to be played.
            else if (((MonsterCard) hand.getCardFromHand(i)).getAttackValue() > enemeyCard.getDefence()
                    &&  ((MonsterCard) hand.getCardFromHand(i)).getAttackManaRequired() <  ManaCounter.get(((MonsterCard) hand.getCardFromHand(i)).getCardSchool()))
            {
                bestCardIndex = i;
            }

            playCard(((MonsterCard) hand.getCardFromHand(bestCardIndex)));
        }
        }
}


    /*

// brians method,
    // this method will choose which zone to play the card on then drag it to that zone.
    //40111707
    public void playCard(BasicCard card) {

// you need to allocate a zone for the card to be sent to
// requires a seek method, it should send the card to the correct zone, then also give the card zone that card as its current card.
        if (AiPlayer.getZoneLeft().isActive() == false) {
            //call the seek method to send the card there
            card.seek(AiPlayer.getZoneLeft().setCurrentCard(card));
        } else if (AiPlayer.getZoneMiddle().isActive() == false) {
            //call the seek method to send the card there
            card.seek(AiPlayer.getZoneMiddle().setCurrentCard(card));
        } else if (AiPlayer.getZoneRight().isActive() == false) {
            //call the seek method to send the card there
            card.seek(AiPlayer.getZoneRight().setCurrentCard(card));
        } else
        {
            // the card cannot be played - there is no room.
        }

    }



  */


}
