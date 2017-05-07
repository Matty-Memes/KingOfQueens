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
import javalampstudos.kingofqueens.kingOfQueens.util.andyManaCounter;

/**
 * Created by brian on 07/04/2017.
 */

// This class scans player and AI monsters for information about them

public class Brain {


    // Constructor for the Ai Brain.
    public Brain(){

    }
    // Andrew - 40083349
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

    // Would need to about opponent health
    // Andrew - 40083349
    public int attackWeakestMonster(ArrayList<MonsterCard> playerMonsters)

    {
        int min = 50;
        int index = 0;

        System.out.println("The size of playerMonsters is" + playerMonsters.size());

        for (int i = 0; i < playerMonsters.size(); i++)
        {
            if (playerMonsters.get(i).health <= min)
            {
                index = i;
                min = playerMonsters.get(i).attackValue;
            }
        }

        return index;
    }

    // 40111707 brians
    // this method will find the highest attack card that can be played.
    // use for putting level 1 cards in play
    //altered for andrews logic
    public void PlayCardWithHighestAtt(ArrayList<MonsterCard>  hand, ArrayList<MonsterCard> monstersInPlay) {

        int bestCardIndex = 0;
        // check all of the onsters in the hand, find the best one, then play it.
        if(!checkAllZonesAreActive(monstersInPlay))
        {

            for(int i=0; i < hand.size();i++)
            {
                if(hand.get(i).getLevel() == CardLevel.UNDERGRAD
                        && hand.get(i).getAttackValue() > hand.get(bestCardIndex).getAttackValue())
                {
                    bestCardIndex = i;
                }
            }
        }
        playMonsterCard(hand.get(bestCardIndex),monstersInPlay);

    }




    // 40111707
    // brians method
    // this method checks to see if the Ai can evolve a card currently in play to the next level.
    // alterd for andrews logic
    public void canIEvolve(ArrayList<MonsterCard> hand, ArrayList<MonsterCard> cardZoneMonsters){
        int nextLevelMonsterIndex = -1;
        int previousLevelMonsterIndex =-1;
        boolean upgradeableCard =false;
        for(int i=0; i < hand.size() && !upgradeableCard;i++)
        {
            for(int j=0; j<cardZoneMonsters.size(); j++)
            {

                // put in matthews method here !!!
                if(hand.get(i).evolutionCheck(cardZoneMonsters.get(j)))
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
    // altered to fit andrews logic
    private void playMonsterCard(MonsterCard card, ArrayList<MonsterCard> cardZoneMonsters) {
        boolean found = false;
        // you need to allocate a zone for the card to be sent to
        // requires a seek method, it should send the card to the correct zone, then also give the card zone that card as its current card.
        if(!checkAllZonesAreActive(cardZoneMonsters)) {
            for(int i=0; i < cardZoneMonsters.size() && !found;i++)
            {
                if(cardZoneMonsters.get(i).getLevel() == CardLevel.DOCTRATE)
                {
                    cardZoneMonsters.add(card);
                    found =true;
                }
            }
        }

    }

    // brian method
    //40111707
    // to be fixed to work with array.
    private boolean checkAllZonesAreActive(ArrayList<MonsterCard> monstersInPlay){
        boolean allZonesActive = true;
        for(int i=0; i < monstersInPlay.size()|| !allZonesActive; i++)
        {
            if(monstersInPlay.get(i).getLevel() == CardLevel.DOCTRATE)
            {
                allZonesActive= false;
            }

        }
        return allZonesActive;
    }
    // brians method
    // 40111707
    // this method is used for comparing the mana requirements of a single monster card to see if there is enough mana to be played.
    private boolean compareManaRequirementWithManaCounter(HashMap<ManaTypes,Integer> manaRequirement, HashMap<ManaTypes,Integer> manaCounter){
        for (ManaTypes key : manaRequirement.keySet()) {
            if (manaRequirement.get(key) > manaCounter.get(key)) {
                return false;
            }

        }
        return true;
    }
/*

NOTE : THESE METHODS ARE CREATED TO BE USED WITH THE MANACOUNTER CLASS WITHIN GAMEBOARD PACKAGE
 - TO SEE FINAL IMPLEMENTATION SEE playSpaceBranch Brain.java
    //brians method
    //40111707
    // this method allows the Ai to check if it is holding mana within its hand, if it is it should be played.
    // at the minute it is only playing the first mana type that it comes across.
    public void PlayMana(ArrayList<ManaCard> hand){
    boolean manaCardFound =false;
    ManaTypes key = findKey();
//              YOU NEED TO REVERT THE CARDS BACK TO THEIR ORIGINAL VARIABLES
        for(int i =0; i< hand.size() && !manaCardFound; i++)
    {
        if(hand.get(i).id == 1)
        {
            ManaCard temp = hand.get(i);
            if(temp.getManaType().equals(key))
            {
                aiPlayer.getManaCounter().addMana(temp.getManaType());
                manaCardFound =true;
            }
        }
    }
        if(!manaCardFound)
    {
        playAnyMana(hand);
    }
}
    private void playAnyMana(ArrayList<ManaCard> hand, andyManaCounter manaCounter)
    {
        //this will check through the hand to find the first mana Carad that it can to play if the hand doesnt contain the mana card type that is needed.

        for (int i = 0; i <hand.size() ; i++) {
            if(hand.get(i).id == 1)
            {

//                aiPlayer.getManaCounter().addMana(aiPlayer.getHand().callCorrectManaCard(getHandCard(j)).getManaType());
            }
        }

    }
    // brians method
    // 40111707
    // this method finds the manatype that the Ai will need the most depeneing on the cards that are
    // currently in its hand
    private HashMap<ManaTypes,Integer> whichManaDoINeedTheMost(){
        HashMap<ManaTypes,Integer> temp = new HashMap<ManaTypes,Integer>();
        // this loop is used to count all of the mana requirements of the monstercards within the hand.
        for(int i=0; i < HAND_LENGTH;i++ )
        {
            for (ManaTypes key:aiPlayer.getManaCounter().getManaCounterHashMap().keySet())
            {
                temp.put(key,temp.get(key)+(aiPlayer.getHand().callCorrectMonsterCard(getHandCard(i))).getAttackManaRequirement().get(key) ) ;
            }
        }
        return temp;
    }
    //40111707
    // brians method
    // this method allows the Ai to find the specifi mana type that has the highest need and then returns that type to be searched for.
    private ManaTypes findKey()
    {
        HashMap<ManaTypes,Integer> temp = whichManaDoINeedTheMost();
        int highestNeedMana=0;
        ManaTypes key1 = null;
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
        return key1;
    }

   *//* // brians method
    // 40111707
    // this method finds the manatype that the Ai will need the most depeneing on the cards that are
    // currently in its hand
    private ManaTypes whichManaDoINeedTheMost(){
        HashMap<ManaTypes,Integer> temp = new HashMap<ManaTypes,Integer>();
        int highestNeedMana=0;
        ManaTypes key1 = null;

        // this loop is used to count all of the mana requirements of the monstercards within the hand.
        for(int i=0; i < aiPlayer.getHand().getScreenHand().size();i++ )
        {
            for (ManaTypes key:aiPlayer.getManaCounter().getManaCounterHashMap().keySet())
            {
                temp.put(key,temp.get(key)+(aiPlayer.getHand().callCorrectMonsterCard(aiPlayer.getHand().getScreenHand().get(i))).getAttackManaRequirement().get(key) ) ;
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
*//*

    // 40111707
// brians method
    // this method is for choosing which card has the lowest defence of the enemey in order for it to be attacked.
    // this method also finds the best card within the Ais cardzone to attack that card.
    public void whichCardShouldIAttack(CardZone[] enemyCardZone)
    {
        final int LOWEST_HEALTH_SCORE =10;
        // this section of the method finds the enemeys card with the lowest defence, then stores its index
        int indexForCardWithLowestDef = enemeysWeakestCard(enemyCardZone);

        // this section of the method finds the card that has a higher attack than the player, then it attacks it.
        int indexForCardToAttackWith = cardToAttackWith(enemyCardZone,indexForCardWithLowestDef,aiPlayer.getManaCounter().getUnusedManaHashMap());

        // calling the ais monster to attack the enemey card if they have found suitbanle targets.
        if(indexForCardToAttackWith > -1 && indexForCardWithLowestDef >=0) {
            aiPlayer.getCardZones()[indexForCardToAttackWith].getCurrentCard().attack(enemyCardZone[indexForCardWithLowestDef].getCurrentCard());
        }else{
            // do nothing.
        }
    }

    // 40111707
    // brians method
    // this method will find the best card to attack the enemy card with
    private int cardToAttackWith( CardZone[] enemyCardZone, int indexForCardWithLowestDef, HashMap<ManaTypes,Integer> aiUnusedMana)
    {
        int indexForCardToAttackWith = -1;
        boolean bestCard = false;
        if(checkAllZonesAreActive())
        {
            for(int i=0; i < aiPlayer.getCardZones().length-1 && !bestCard; i++)
            {
                if(aiPlayer.getCardZones()[i].getCurrentCard().getAttackValue() > enemyCardZone[indexForCardWithLowestDef].getCurrentCard().getDefence())
                {
                    if(compareManaRequirementWithManaCounter(aiPlayer.getCardZones()[i].getCurrentCard().getAttackManaRequirement(),aiUnusedMana))
                    {
                        indexForCardToAttackWith = i;
                        bestCard = true;
                    }
                }
            }
        }
        return indexForCardToAttackWith;
    }

    //40111707
    // brians method
    // this method finds the weakest card within the enemys cardzones.
    private int enemeysWeakestCard(CardZone[] enemyCardZone)
    {
        int indexForCardWithLowestDef =0;
        if(checkAllZonesAreActive()) // a method to see if there is any cards on the baord?
        {
            for(int i=1; i < enemyCardZone.length-1; i++)
            {
                if(enemyCardZone[i].getCurrentCard().getDefence() >= enemyCardZone[indexForCardWithLowestDef].getCurrentCard().getDefence())
                {
                    indexForCardWithLowestDef =i;
                }
            }
        }
        return indexForCardWithLowestDef;

    }
*/
}
