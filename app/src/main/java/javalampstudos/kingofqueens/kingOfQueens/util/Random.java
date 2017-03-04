package javalampstudos.kingofqueens.kingOfQueens.util;

/**
 * Created by Andrew on 04/03/2017.
 */

import java.util.concurrent.ThreadLocalRandom;

public class Random

{

    public boolean active = true;
    public boolean innerSearchActive = true;

    // Should these be initalized in the constructor??

    // randomly index a position in the array
    int randomNumber;

    // Constructor
    public Random ()

    {

    }

    public int generateRandomNumber (int [] appeared, int counter)

    {
        // choose a card at random from the deck
        int min = 0;
        int max = 8;

        while (active == true)

        {
            // may have to generate this in a different way
            // randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);

            System.out.println(randomNumber);

            // should probably be global
            int innerCounter = 0;


            // if a match is found at any stage the loop fails
            while (randomNumber != appeared[innerCounter] && innerSearchActive == true)

            {


                innerCounter++;

                // This would stop you checking position 39
                if (innerCounter == 39)

                {
                    innerSearchActive = false;
                    active = false;
                    System.out.println("Finished");
                }

            }

        } // end while loop


        // add it to the array seperately - it's not linked so it always goes in the right order
        // this is seperate to the hand array
        appeared[counter] = randomNumber;
        counter++;

        // keep this
        active = true;
        innerSearchActive = true;

        return randomNumber;

    }

    // Make getters

    // Player

    /*

    public int [] getPlayerHasAppeared ()

    {
       return this.playerHasAppeared;

    }


    public int getPlayerhCounter() {
        return this.playerhCounter;
    }

    // Opponent

    public int [] getOpponentHasAppeared ()

    {
        return this.opponentHasAppeared;

    }

    public int getOpponenthCounter ()

    {
        return this.opponenthCounter;

    }

    */

}
