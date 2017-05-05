package javalampstudos.kingofqueens.kingOfQueens.engine.io;


/**
 * Created by Nathan on 10/04/2017.
 */

public class Dialogue {


    //Activates Conversation based on converstaionIndex
    public static String conversationIndex(int index, int dialoguePoint, int response) {
        String mText = null;

        switch(index) {

            case 0: return johnConvo(dialoguePoint, mText, response);
            case 1: return billyConvo(dialoguePoint, mText, response);
            case 2: mText = "Coin"; break;
            default: mText = "None";

        }
        return mText;
    }



    public static String johnConvo(int dialoguePoint, String mText, int response) {

        String responseState = "No Response";

        //If response is required, set responseState to "OPTIONS"
        if(response == 0) {
            responseState = "Options";
        }

        //If response has been given, set responseState to "RESPONSE"
        if(response > 0) {
            responseState = "Response";
        }



        //Identify which conversation needs to be returned based on responseState
        switch(responseState) {

            case "No Response":
                switch (dialoguePoint) {

                    case 1:
                        mText = "What up!!"; break;

                    case 2:
                        mText = "How are you?"; break;

                    case 3:
                        mText = "Wanna play cards?"; break;

                    case 4:
                        mText = "What do you think?"; break;

                    default:
                        mText = "Options"; // Sets GameViewFragment- dialogueState to OPTIONS
                } break;


            case "Options":
                switch (dialoguePoint) {

                    //Draws both Options to the screen awaiting response

                    case 1:
                        mText = "Sure!"; break;

                    case 2:
                        mText = "Not right now"; break;

                } break;


            case "Response":

                //Identify which what lines need to be returned based on the response given by player
                switch (response) {

                    case 1:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "Great!"; break;

                            case 2:
                                mText = "Let's play!"; break;

                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                    case 2:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "Aww... okay."; break;

                            case 2:
                                mText = "Another time then."; break;

                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                } break;
        }
        return mText;
    }



    public static String billyConvo(int dialoguePoint, String mText, int response) {

        switch(dialoguePoint) {

            case 1:
                mText = "Have you seen my gold?"; break;

            case 2:
                mText = "I can't find it anywhere..."; break;

            default:
                mText = "None";
        }
        return mText;
    }

    public static String shopConvo(int dialoguePoint, int coin) {

        String mText = null;

        //Assume player has no coins to start
        String coinState = "No Coins";

        //If player has coins but too few  change coinState
        if (coin > 0 && coin < 2) {
            coinState = "Short";
        }

        //Player has enough coins change coinState
        if (coin > 1) {
            coinState = "Enough Coins";
        }

        switch (coinState) {

            case "No Coins":
                switch (dialoguePoint) {

                    case 1:
                        mText = "Hi welcome to the shop!";
                        break;

                    default:
                        mText = "None";
                } break;


            case "Short":
                switch (dialoguePoint) {

                    case 1:
                        //Draw line using how many coins the player currently has. Seperate lines for singular and plural
                        if (coin > 1) {
                            mText = "Sorry, you only have " + coin + " coins.";
                        } else {
                            mText = "Sorry, you only have " + coin + " coin.";
                        }
                        break;

                    case 2:
                        mText = "Come back if you find them!";
                        break;

                    default:
                        mText = "None";
                } break;


            case "Enough Coins":
                switch (dialoguePoint) {

                    case 1:
                        mText = "Wow. You found them!";
                        break;

                    default:
                        mText = "None";
                }break;

        }
        return mText;
    }
}
