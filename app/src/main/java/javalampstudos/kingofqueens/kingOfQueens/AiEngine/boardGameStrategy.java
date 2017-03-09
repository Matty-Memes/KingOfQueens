package javalampstudos.kingofqueens.kingOfQueens.AiEngine;

/**
 * Created by brian on 27/02/2017.
 */

public class boardGameStrategy {

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
}
