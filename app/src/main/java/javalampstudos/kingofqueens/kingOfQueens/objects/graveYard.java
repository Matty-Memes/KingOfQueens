package javalampstudos.kingofqueens.kingOfQueens.objects;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;

/**
 * Created by brian on 14/02/2017.
 */

public class graveYard extends GameObject {
    public  final int maxGraveYardSize = 5;
    private int graveCounter;
    private MonsterCard graveYard [] = new MonsterCard[maxGraveYardSize];

    public graveYard(float x, float y, int width, int height, Bitmap sprite, boolean player) {
        super(x, y, width, height, sprite, player);
        this.graveCounter = 0;
    }

    public int getGraveCounter() {
        return graveCounter;
    }

    public MonsterCard[] getGraveYard() {
        return graveYard;
    }

    public void setGraveYard(MonsterCard[] graveYard) {
        graveYard = graveYard;
    }

    public void setGraveCounter(int graveCounter) {
        this.graveCounter = graveCounter;
    }





    // this method allows for the card to be removed from the deck and placed here, where it will be tallied in order to find the score
    // if the grave counter exceeds the maximum number of cards then the match should end.
    //40111707
    public void addToGraveYard(MonsterCard deadCard)
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
    public void removeFromGraveYard(MonsterCard reviveCardChoice)
    {
        if( reviveCardChoice.destroyed)
        {
            for(int i=0; i < graveYard.length; i++)
            {
                if(graveYard[i].equals(reviveCardChoice))
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
