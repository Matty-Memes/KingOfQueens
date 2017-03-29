package javalampstudos.kingofqueens.kingOfQueens.objects;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.monsterCard;

/**
 * Created by brian on 14/02/2017.
 */

public class graveYard {
    public  final int maxGraveYardSize = 5;
    private int graveCounter;
    private monsterCard graveYard [] = new monsterCard[maxGraveYardSize];
    public graveYard( )
    { // NOTE not to sure about the intialising of the array to contain graveyard - in here or where it is ?

        setGraveCounter(0);
    }

    public int getGraveCounter() {
        return graveCounter;
    }

    public monsterCard[] getGraveYard() {
        return graveYard;
    }

    public void setGraveYard(monsterCard[] graveYard) {
        graveYard = graveYard;
    }

    public void setGraveCounter(int graveCounter) {
        this.graveCounter = graveCounter;
    }



    // this method allows for the card to be removed from the deck and placed here, where it will be tallied in order to find the score
    // if the grave counter exceeds the maximum number of cards then the match should end.
    //40111707
    public void addToGraveYard(monsterCard deadCard)
    {
        if( graveCounter < maxGraveYardSize )
        {
            // set the destroyed value to true, this removes it from the hand and deck.
            deadCard.setDestroyed(true);
            // puting it into the graveyard allows for faster searching of dead cards.
            graveYard[graveCounter] = deadCard;
            graveCounter ++;
        }
        else
        {
            // cant add the card
            // you need to end the match here as well
        }
    }


// this method allows for the dead card the the player choose to be revived by
// setting the destroyed variable to false, and removing it from the graveyard array.
    //40111707
    public void removeFromGraveYard(monsterCard reviveCardChoice)
    {
        if( reviveCardChoice.destroyed == true )
        {
            for(int i=0; i < graveYard.length; i++)
            {
                if(graveYard[i].equals(reviveCardChoice) == true)
                {
                    // when the card is found it needs to be removed from the array.
                    reviveCardChoice.setDestroyed(false); // this allows it to come back into the decks view.
                    graveYard[i] = null;
                }
            }
        }

    }
    // this method will allow the player to see the cards that have died and then
    // choose which they want to revive by calling the above method.



    public void displayGraveYard()
    {

    }
}
