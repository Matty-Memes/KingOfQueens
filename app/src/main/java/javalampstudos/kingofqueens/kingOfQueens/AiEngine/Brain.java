package javalampstudos.kingofqueens.kingOfQueens.AiEngine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardLevel;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.CardZone;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Hand;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.ManaCounter;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.PlaySpace;
import javalampstudos.kingofqueens.kingOfQueens.objects.graveYard;

/**
 * Created by brian on 07/04/2017.
 */

public class Brain {

    // Constructor for the Ai Brain.
    public Brain(){

    }

    // loop through and find the index of the monster with the highest attack
    public int playHighestAttack (ArrayList<MonsterCard> monster)

    {
       int max = 0;
       int index = 0;

       System.out.println("The size of monster is" + monster.size());

       for (int i = 0; i < monster.size(); i++)
       {
         if (monster.get(i).attackValue >= max)
         {
          index = i;
          max = monster.get(i).attackValue;
         }
       }
      return index;

    }

    // 40111707 brians
    // this method will find the highest attack card that can be played.
    // use for putting level 1 cards in play
    public void PlayCardWithHighestAtt(PlaySpace aiPlayer) {

        int bestCardIndex = 0;
      // check all of the onsters in the hand, find the best one, then play it.
        if(checkAllZonesAreActive(aiPlayer.getCardZones()))
        {

            for(int i=0; i < aiPlayer.getHand().getHandBasicCardArray().length;i++)
            {

                if(!aiPlayer.getCardZones()[i].isActive())
                {
                    if(aiPlayer.callCorrectMonsterCard(aiPlayer.getHand().getHandBasicCardArray()[i]).getLevel() == CardLevel.UNDERGRAD
                            && aiPlayer.callCorrectMonsterCard(aiPlayer.getHand().getHandBasicCardArray()[i]).getAttackValue() > aiPlayer.callCorrectMonsterCard(aiPlayer.getHand().getHandBasicCardArray()[bestCardIndex]).getAttackValue())
                    {
                        bestCardIndex = i;
                    }


                }
            }
        }


        playCard(aiPlayer.callCorrectMonsterCard(aiPlayer.getHand().getHandBasicCardArray()[bestCardIndex]),aiPlayer.getCardZones());
    }




// 40111707
    // brians method
    // this method checks to see if the Ai can evolve a card currently in play to the next level.
    public void canIEvolve(PlaySpace aiPlayer){
       int nextLevelMonsterIndex = -1;
        int previousLevelMonsterIndex =-1;
        boolean upgradeableCard =false;
        for(int i=0; i < aiPlayer.getHand().getHandBasicCardArray().length && !upgradeableCard;i++)
        {
            for(int j=0; j<aiPlayer.getCardZones().length; j++)
            {

                    // put in matthews method here !!!
                    if(aiPlayer.callCorrectMonsterCard(aiPlayer.getHand().getHandBasicCardArray()[i]).evolutionCheck(aiPlayer.getCardZones()[j].getCurrentCard()) )
                    {
                        nextLevelMonsterIndex = i;
                        previousLevelMonsterIndex =j;
                        upgradeableCard = true;
                    }

            }
        }

        // this is where you need to evolve the cards when the method is avaialble, if both indexs > -1.


    }

    // brians method,
    // this method will choose which zone to play the card on then drag it to that zone.
    //40111707
    public void playCard(MonsterCard card,CardZone [] cardZones) {
        boolean found = false;
        // you need to allocate a zone for the card to be sent to
        // requires a seek method, it should send the card to the correct zone, then also give the card zone that card as its current card.
        if(!checkAllZonesAreActive(cardZones)) {
            for(int i=0; i < cardZones.length && !found;i++)
            {
                if(!cardZones[i].isActive())
                {
                    cardZones[i].setCurrentCard(card);
                    found =true;
                }
            }
        }
        else
        {
            // the card cannot be played - there is no room.
        }

    }

    // brian method
    //40111707
    // to be fixed to work with array.
    public boolean checkAllZonesAreActive(CardZone[] cardZones){
        boolean allZonesActive = false;
        for(int i=0; i < cardZones.length || allZonesActive; i++)
        {
            if(!cardZones[i].isActive())
            {
                allZonesActive= false;
            }
            else {
               return true;
            }
        }
        return allZonesActive;
    }
    // brians method
    // 40111707
    // this method is used for comparing the mana requirements of a single monster card to see if there is enough mana to be played.
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
    public void playMana(PlaySpace aiPlayer){

        boolean manaCardFound =false;
        ManaTypes key = whichManaDoINeedTheMost(aiPlayer);


//              YOU NEED TO REVERT THE CARDS BACK TO THEIR ORIGINAL VARIABLES
                for(int i =0; i< aiPlayer.getHand().getHandBasicCardArray().length && !manaCardFound; i++)
                {
                    if(aiPlayer.getHand().getHandBasicCardArray()[i].id == 1)
                    {
                        if(aiPlayer.callCorrectManaCard(aiPlayer.getHand().getHandBasicCardArray()[i]).getManaType().equals(key))
                        {

                            aiPlayer.getManaCounter().addMana(aiPlayer.callCorrectManaCard(aiPlayer.getHand().getHandBasicCardArray()[i]).getManaType());
                            manaCardFound =true;
                        }
                    }

                }
    }

    // brians method
    // 40111707
    // this method finds the manatype that the Ai will need the most depeneing on the cards that are
    // currently in its hand
    public ManaTypes whichManaDoINeedTheMost(PlaySpace aiPlayer){
        HashMap<ManaTypes,Integer> temp = new HashMap<ManaTypes,Integer>();
        int highestNeedMana=0;
        ManaTypes key1 = null;

        // this loop is used to count all of the mana requirements of the monstercards within the hand.
        for(int i=0; i < aiPlayer.getHand().getHandBasicCardArray().length;i++ )
        {

                for (ManaTypes key:aiPlayer.getManaCounter().getManaCounterHashMap().keySet())
                {
                    temp.put(key,temp.get(key)+(aiPlayer.callCorrectMonsterCard(aiPlayer.getHand().getHandBasicCardArray()[i])).getAttackManaRequirement().get(key) ) ;
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


// 40111707
// brians method
    // this method is for choosing which card has the lowest defence of the enemey in order for it to be attacked.
    // this method also finds the best card within the Ais cardzone to attack that card.
    public void whichCardShouldIAttack(CardZone[] enemyCardZone,CardZone[] aiCardZone, ManaCounter aiUnusedMana)
    {
        final int LOWEST_HEALTH_SCORE =10;
        // this section of the method finds the enemeys card with the lowest defence, then stores its index
        int indexForCardWithLowestDef =0;
        if(checkAllZonesAreActive(enemyCardZone)) // a mthod to see if there is any cards on the baord?
        {
            for(int i=1; i < enemyCardZone.length; i++)
            {
                if(enemyCardZone[i].getCurrentCard().getDefence() >= enemyCardZone[indexForCardWithLowestDef].getCurrentCard().getDefence())
                {
                    indexForCardWithLowestDef =i;
                }
            }
        }

        // this section of the method finds the card that has a higher attack than the player, then it attacks it.
        int indexForCardToAttackWith =-1;
        if(checkAllZonesAreActive(aiCardZone))
        {
            for(int i=0; i < aiCardZone.length; i++)
            {
                if(aiCardZone[i].getCurrentCard().getAttackValue() > enemyCardZone[indexForCardWithLowestDef].getCurrentCard().getDefence())
                {
                    if(compareManaRequirementWithManaCounter(aiCardZone[i].getCurrentCard().getAttackManaRequirement(),aiUnusedMana.getUnusedManaHashMap()))
                    {
                        indexForCardToAttackWith = i;
                    }
                }


            }
        }

        // calling the ais monster to attack the enemey card if they have found suitbanle targets.
        if(indexForCardToAttackWith > -1 && indexForCardWithLowestDef >=0) {
            aiCardZone[indexForCardToAttackWith].getCurrentCard().attack(enemyCardZone[indexForCardWithLowestDef].getCurrentCard());
        }else{
            // do nothing.
        }
    }

    // 40111707
    // Brians method
    // this method will find out if the ai has a card that has a higher attack than al of the enemeys cards.
    public void doIhaveAnyCardsWithGreaterAttack(CardZone [] aiCardZone,CardZone [] enemeyCardZone)
    {

    }
}
