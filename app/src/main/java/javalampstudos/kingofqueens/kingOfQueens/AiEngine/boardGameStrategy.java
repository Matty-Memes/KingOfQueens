package javalampstudos.kingofqueens.kingOfQueens.AiEngine;

/**
 * Created by brian on 27/02/2017.
 */

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.ManaCounter;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.PlaySpace;

public class boardGameStrategy {


    PlaySpace AiPlayer = new PlaySpace(); // still neeeds to be populated



    // this method is for sorting the monster cards after they have been read into a deck
    // so that they can later be put into a hash map
// should make sure they are the first 20 cards
    public void quickSortMonstercards ()
    {



    }

/* BRIAN : COMMENTED OUT DUE TO ERROES
    //this method is for allocating the monster cards to the correct hash map.
    public void allocateMonsterCardToCorectMapArea(deck Aideck)
    {// only checking the monster cards here could then thero itly just use 20 as a final,
        // after the sorft method makes sure the monsters are all of the first 20 cards in order of atack.
        int i =0;
        while(i < Aideck.size ())        {
            if(Aideck.get(i).getAttack() > 50)
            {
                if(Aideck.get(i).getDefence() > 50)
                {
                    //insert into map
                    map.put(cardPowerLevels.HIGHATTACK_HIGHDEFENCE,Aideck.get(i));
                    i++;
                }
                else {
                    map.put(cardPowerLevels.HIGHATACK_LOWDEFENCE,Aideck.get(i));
                    i++;
                }


            }
            if(Aideck.get(i).getAttack() < 50)
            {
                if(Aideck.get(i).getDefence() < 50)
                {
                    //insert into map
                    map.put(cardPowerLevels.LOWATTACK_LOWDEFENCE,Aideck.get(i);
                    i++;
                }
                else {
                    map.put(cardPowerLevels.LOWATTACK_HIGHDEFENCE,Aideck.get(i));
                    i++;
                }


            }

        }
    */


// 40111707 brians method
    // this method will find the highest attack card that can be played.
    public void searchHandForCardWithHighestAttack(BasicCard[] hand, MonsterCard enemeyCard)
    {
        int bestCardIndex=0;
// for loop begins at one so that first card can be compared to
        for (int i=1; i < hand.length; i++)
        {// within here i need to make sure only monster cards will be accepted.
            // this if statement checks 3 things.
            //1. the card is higher attack than enemeys defence
            // 2.the card is also higher than the best card so far
            // 3.the cards mana also allows it to be played.
            if (hand[i].getAttack1Strength() > enemeyCard.getDefence() && hand[i].getAttack1Strength() > hand[bestCardIndex].getAttack1Strength()
                    && hand[i].getAttack1ManaRequired() < ManaCounter.get(hand[i].getCardSchool())) // manacounter still needs to be changed to a hashmap
            {
                bestCardIndex =i;

            }
            // this assumes the card that has the highest attack cannot be played,
//				so it simply checks for a card with a better attack than the player, with enough mana to be played.
            else if (hand[i].getAttack1Strength() > enemeyCard.getDefence() &&  hand[i].getAttack1ManaRequired() < ManaCounter.get(hand[i].getCardSchool())
            {
                bestCardIndex = i;
            }

            playCard(hand[bestCardIndex]);
        }
}


// brians method,
    // this method will choose which zone to play the card on then drag it to that zone.
    public void playCard(basicCard card) {

// you need to allocate a zone for the card to be sent to
// waiting for matt to change the zone stuff
        if (!zone1.isActive()) {
            //call the seek method to send the card there
            card.seek(zone1.position());
        } else if (!zone2.isActive()) {
            //call the seek method to send the card there
            card.seek(zone2.position());
        } else if (!zone3.isActive()) {
            //call the seek method to send the card there
            card.seek(zone3.position());
        }

    }





    //
}
