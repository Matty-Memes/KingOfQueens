package javalampstudos.kingofqueens.kingOfQueens.objects;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;

/**
 * Created by brian on 14/02/2017.
 */

public class graveYard {
    public static final int maxGaraveYardSize = 5;
    private static int graveCounter;

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



    // this method allows for the card to be removed from the deck and placed here, where it will be tallied in order to find the score
    // if the grave counter exceeds the maximum number of cards then the match should end.
    //40111707
    public static void addToGraveYard(MonsterCard deadCard)
    {
        if( graveCounter < maxGaraveYardSize )
        {

            //  this may have to be done via cloneing from the deck array
            deadCard.destroyed = true;
            graveCounter ++;
        }
        else
        {
            // cant add the card
            // you need to end the match here as well
        }
    }


// this method allows for the dead card the the player choose to be revived by setting the destroyed variable to false.
    //40111707
    public void removeFromGraveYard(MonsterCard reviveCardChoice)
    {
        if( reviveCardChoice.destroyed == true )
        {
            // changing the destroyed vairable back to false will allow the player to reuse the card once again
            reviveCardChoice.destroyed = false;
        }

    }
    // this method will allow the player to see the cards that have died and then choose which they want to revive by calling the above method.
    public void displayGraveYard()
    {

    }
}
