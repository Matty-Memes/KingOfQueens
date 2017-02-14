package javalampstudos.kingofqueens.kingOfQueens.objects;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;

/**
 * Created by brian on 14/02/2017.
 */

public class graveYard {
    public final int maxGaraveYardSize = 5;
    private int graveCounter;
    private MonsterCard graveYardArray [] = new MonsterCard[maxGaraveYardSize];

    public graveYard( )
    { // NOTE not to sure about the intialising of the array to contain graveyard - in here or where it is ?
        setGraveCounter(0);
    }

    public int getGraveCounter() {
        return graveCounter;
    }

    public void setGraveCounter(int graveCounter) {
        this.graveCounter = graveCounter;
    }

    public MonsterCard[] getGraveYard()
    {
        return graveYardArray;
    }

    public void setGraveYard(MonsterCard[] graveYard)
    {
        this.graveYardArray = graveYard;
    }

    // this method allows for the card to be removed from the deck and placed here, where it will be tallied in order to find the score
    // if the grave counter exceeds the maximum number of cards then the match should end.
    public void addToGraveYard(MonsterCard deadCard)
    {
        if( graveCounter >= maxGaraveYardSize )
        {
            // cant add the card
            // you need to end the match here as well
        }
        else
        {//  this may have to be done via cloneing from the deck array
            graveYardArray[graveCounter] = deadCard;
            graveCounter ++;
        }
    }
}
