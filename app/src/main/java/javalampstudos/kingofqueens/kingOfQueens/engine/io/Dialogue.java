package javalampstudos.kingofqueens.kingOfQueens.engine.io;


/**
 * Created by Nathan on 10/04/2017.
 */

public class Dialogue {



    public static String conversationIndex(int index, int dialoguePoint) {
        String mText = null;

        switch(index) {

            case 0: return johnConvo(dialoguePoint, mText);
            case 1: return billyConvo(dialoguePoint, mText);
            case 2: mText = "Coin"; break;
            default: mText = "None";

        }
        return mText;
    }



    public static String johnConvo(int dialoguePoint, String mText) {

        switch(dialoguePoint) {

            case 1:
                mText = "What up!!"; break;

            case 2:
                mText = "How are you?"; break;

            case 3:
                mText = "Wanna play cards?"; break;

            default:
                mText = "None";
        }
        return mText;
    }

    public static String billyConvo(int dialoguePoint, String mText) {

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

        String coinState = "No Coins";

        if (coin > 0 && coin < 2) {
            coinState = "Short";
        }
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
                        if (coin > 1) {
                            mText = "Sorry, you only have " + coin + " coins";
                        } else {
                            mText = "Sorry, you only have " + coin + " coin";
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
