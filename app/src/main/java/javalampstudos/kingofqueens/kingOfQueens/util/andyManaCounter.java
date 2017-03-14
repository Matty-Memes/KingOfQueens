package javalampstudos.kingofqueens.kingOfQueens.util;

/**
 * Created by 40083349 on 14/03/2017.
 */

// Android Imports
import android.graphics.Paint;


// Local Imports



public class andyManaCounter

{
    // Variables relating to each type of mana

    private int csManaCounter;
    private int medicsManaCounter;
    private int engineeringManaCounter;

    // take in the card school and update the relevant mana card
    private void incrementCounter (String school)

    {
        switch (school)

        {

            case "EEECS":
                // increment EEECS
                csManaCounter++;

                // Convert the counter to a string and update the paint object
                break;
            case "Medics":
                medicsManaCounter++;
                break;
            case "Engineering":
                engineeringManaCounter++;
                break;

        } // end case statement

    }


    // take in the card school and decrement mana
    private void decrementCounter (String school)

    {
        switch (school)

        {

            case "EEECS":
                // increment EEECS
                csManaCounter++;

                // Convert the counter to a string and update the paint object
                break;
            case "Medics":
                medicsManaCounter++;
                break;
            case "Engineering":
                engineeringManaCounter++;
                break;

        } // end case statement


    }


}
