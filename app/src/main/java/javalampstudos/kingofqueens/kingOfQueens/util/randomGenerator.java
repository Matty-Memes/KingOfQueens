package javalampstudos.kingofqueens.kingOfQueens.util;

/**
 * Created by Andrew on 14/03/2017.
 */

import java.util.Random;

public class randomGenerator

{
    public boolean active = true;
    public boolean innerSearchActive = true;

    // Should these be initalized in the constructor??

    // randomly index a position in the array
    int randomNumber;

    // Move the arguments here
    int [] playerHasAppeared;
    int counter;
    // used to determine which cardType your talking about.

    Random rand;


    // Constructor
    public randomGenerator ()

    {
        // Instantiate

        playerHasAppeared = new int [40];


        // Initialize appeared arrays

        for (int i = 0; i < 40; i++)

        {
            // Weird
            playerHasAppeared[i] = 40;

        }

        rand = new Random();

    }

    // should switch the variables being accessed depending on whether it's the player or the AI

    public int generateRandomNumber ()

    {
        // choose a card at random from the deck
        int min = 0;
        int max = 13;

        while (active == true)

        {

            randomNumber = rand.nextInt((max - min) + 1) + min;

            // System.out.println(randomNumber + "is");

            // should probably be global
            int innerCounter = 0;


            // if a match is found at any stage the loop fails
            while (randomNumber != playerHasAppeared[innerCounter] && innerSearchActive == true)

            {
                innerCounter++;

                // This would stop you checking position 39
                if (innerCounter == 39)

                {
                    innerSearchActive = false;
                    active = false;
                    // System.out.println("Finished");
                }

            }

        } // end while loop


        // add it to the array seperately - it's not linked so it always goes in the right order
        // this is seperate to the hand array
        playerHasAppeared[counter] = randomNumber;
        counter++;

        // sets up for the next pass
        active = true;
        innerSearchActive = true;


        return randomNumber;

    }

    //Nathan/OpenWorld- 40131544 unimplemented
    public int randomSprite() {

        randomNumber = 0;

        randomNumber = rand.nextInt(2);

        return randomNumber;

    }
    //Nathan/OpenWorld- 40131544 unimplemented
    public void flushRandomSpriteLogic() { randomNumber = 0; }

    // call this after the player deck is drawn

    public void flushRandomLogic ()

    {
        // can't be null
        randomNumber = 0;
        counter = 0;

        for (int i = 0; i < 40; i++)

        {
            if (i == 0) {
                playerHasAppeared[i] = 41;

            } else

            // as per normal intialiazation
            {
                playerHasAppeared[i] = 0;
            }
        }

        // Successful
        System.out.println("Random logic flushed");

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
