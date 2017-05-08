package javalampstudos.kingofqueens.kingOfQueens.engine.io;


/**
 * Created by Nathan on 10/04/2017.
 */

public class Dialogue {

    private static int introConvoCounter = 0;
    private static int sebastianConvoCounter = 0;
    private static int ronAndHenryConvoCounter = 0;


    //Activates Conversation based on conversationIndex
    public static String conversationIndex(int index, int dialoguePoint, int response) {
        String mText = null;

        switch(index) {

            case 0: return johnConvo(dialoguePoint, mText, response);
            case 1: return billyConvo(dialoguePoint, mText, response);
            case 2: return jessieConvo(dialoguePoint, mText, response);
            case 3: return sebastianConvo(dialoguePoint, mText, response);
            case 4: return introConvo(dialoguePoint, mText, response);
            case 5: return cardConvo(dialoguePoint, mText, response);
            case 6: return timConvo(dialoguePoint, mText, response);
            case 7: return cardConvo(dialoguePoint, mText, response);
            case 8: return cardConvo(dialoguePoint, mText, response);
            case 9: return cardConvo(dialoguePoint, mText, response);
            case 10: return kevinConvo(dialoguePoint, mText, response);
            case 12: return cardConvo(dialoguePoint, mText, response);
            case 11: mText = "Coin"; break;
            case 13: case 14: return ronAndHenryConvo(dialoguePoint, mText, response);
            case 15: return cardConvo(dialoguePoint, mText, response);
            default: mText = "None";

        }
        return mText;
    }

    public static String cardConvo(int dialoguePoint, String mText, int response) {

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
                        mText = "Hey!"; break;
                    case 2:
                        mText = "Wanna play cards?"; break;

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
                            case 3:
                                mText = "Switch"; break;
                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                    case 2:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "Aww... okay"; break;
                            case 2:
                                mText = "Another time then"; break;

                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                } break;
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
                            case 3:
                                mText = "Switch"; break;
                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                    case 2:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "Aww... okay"; break;
                            case 2:
                                mText = "Another time then"; break;

                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                } break;
        }
        return mText;
    }


    public static String sebastianConvo(int dialoguePoint, String mText, int response) {

        String responseState = "No Response";

        if(sebastianConvoCounter > 0) {
            return cardConvo(dialoguePoint, mText, response);
        }

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
                        mText = "Hey!!"; break;
                    case 2:
                        mText = "Keep off the grass!"; break;

                    default:
                        mText = "Options"; // Sets GameViewFragment- dialogueState to OPTIONS
                } break;


            case "Options":
                switch (dialoguePoint) {

                    //Draws both Options to the screen awaiting response

                    case 1:
                        mText = "Oh my. I'm so sorry"; break;
                    case 2:
                        mText = "But... you're on the grass"; break;

                } break;


            case "Response":

                //Identify which what lines need to be returned based on the response given by player
                switch (response) {

                    case 1:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "Wow, thank you"; break;
                            case 2:
                                mText = "Most people just get mad"; break;
                            case 3:
                                mText = "I'm sorry I yelled at you"; break;
                            case 4:
                                mText = "Come back and we can play cards!"; break;

                            default:
                                sebastianConvoCounter++;
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                    case 2:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "Umm... yes"; break;
                            case 2:
                                mText = "Yes!"; break;
                            case 3:
                                mText = "So I can tell people to stay off!"; break;

                            default:
                                mText = "None";

                        } break;

                } break;

        }
        return mText;
    }


    public static String timConvo(int dialoguePoint, String mText, int response) {

        switch(dialoguePoint) {

            case 1:
                mText = "That guy by the library"; break;
            case 2:
                mText = "He welcomes people to Queen's"; break;
            case 3:
                mText = "Everyone who passes by"; break;
            case 4:
                mText = "Can he not help himself?"; break;
            case 5:
                mText = "Weird fella..."; break;

            default:
                mText = "None"; break;
        }
        return mText;

    }




    public static String billyConvo(int dialoguePoint, String mText, int response) {

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
                        mText = "Have you seen my gold?"; break;
                    case 2:
                        mText = "I can't find it anywhere..."; break;

                    default:
                        mText = "Options"; // Sets GameViewFragment- dialogueState to OPTIONS
                } break;


            case "Options":
                switch (dialoguePoint) {

                    //Draws both Options to the screen awaiting response

                    case 1:
                        mText = "There's gold everywhere"; break;
                    case 2:
                        mText = "Nope, sorry"; break;

                } break;


            case "Response":

                //Identify which what lines need to be returned based on the response given by player
                switch (response) {

                    case 1:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "What are you talking about?"; break;
                            case 2:
                                mText = "Gold everywhere..."; break;
                            case 3:
                                mText = "How could that even happen??"; break;

                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                    case 2:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "Darn..."; break;

                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                } break;
        }
        return mText;
    }


    public static String jessieConvo(int dialoguePoint, String mText, int response) {

        switch(dialoguePoint) {

            case 1:
                mText = "Those are some pretty flowers"; break;

            default:
                mText = "None";
        }
        return mText;

    }



    public static String kevinConvo(int dialoguePoint, String mText, int response) {

        switch(dialoguePoint) {

            case 1:
                mText = "Have you talked to Suzie?"; break;
            case 2:
                mText = "She can check your coins"; break;
            case 3:
                mText = "I saw her in Botanic Gardens"; break;

            default:
                mText = "None";
        }
        return mText;

    }



    public static String introConvo(int dialoguePoint, String mText, int response) {

        String responseState = "No Response";

        if(introConvoCounter > 0 ) {
            return introConvo2(dialoguePoint, mText, response);
        }

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
                        mText = "Hey!"; break;
                    case 2:
                        mText = "Welcome to Queen's!"; break;
                    case 3:
                        mText = "It's a magical place"; break;
                    case 4:
                        mText = "Where you can explore,"; break;
                    case 5:
                        mText = "Talk to people,"; break;
                    case 6:
                        mText = "And of course..."; break;
                    case 7:
                        mText = "PLAY CARD GAMES!"; break;
                    case 8:
                        mText = "Most folk are super friendly"; break;
                    case 9:
                        mText = "If you want to play cards"; break;
                    case 10:
                        mText = "Just ask around!"; break;
                    case 11:
                        mText = "Or you can just chat"; break;
                    case 12:
                        mText = "You decide what you say!"; break;

                    default:
                        mText = "Options"; // Sets GameViewFragment- dialogueState to OPTIONS
                } break;


            case "Options":
                switch (dialoguePoint) {

                    //Draws both Options to the screen awaiting response

                    case 1:
                        mText = "Wow! That's awesome!"; break;
                    case 2:
                        mText = "What?! Impossible!"; break;

                } break;


            case "Response":

                //Identify which what lines need to be returned based on the response given by player
                switch (response) {

                    case 1:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "I know right!"; break;
                            case 2:
                                mText = "If we were to talk again"; break;
                            case 3:
                                mText = "It could be completely different!"; break;
                            case 4:
                                mText = "And I'm such a scatterbrain"; break;
                            case 5:
                                mText = "I may not remember we talked!"; break;
                            case 6:
                                mText = "HA HA HA HA HA!"; break;
                            case 7:
                                mText = ". . ."; break;
                            case 8:
                                mText = "Anyway! We should talk again!"; break;
                            case 9:
                                mText = "Either way..."; break;
                            case 10:
                                mText = "Enjoy Queen's!"; break;

                            default:
                                introConvoCounter++;
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                    case 2:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "Silly..."; break;
                            case 2:
                                mText = "Here, anything is possible!"; break;
                            case 3:
                                mText = "But mostly the stuff I said earlier..."; break;
                            case 4:
                                mText = "Anyway! We should talk again!"; break;
                            case 5:
                                mText = "But I'm such a scatterbrain"; break;
                            case 6:
                                mText = "I may not remember we talked!"; break;
                            case 7:
                                mText = "Maybe we've already talked..."; break;
                            case 8:
                                mText = "Who knows! HA HA HA!"; break;
                            case 9:
                                mText = "Either way..."; break;
                            case 10:
                                mText = "Enjoy Queen's!"; break;

                            default:
                                introConvoCounter++;
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                } break;
        }
        return mText;
    }



    public static String introConvo2(int dialoguePoint, String mText, int response) {

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
                        mText = "Hey!"; break;
                    case 2:
                        mText = "Welcome to Queen's!"; break;
                    case 3:
                        mText = "It's a magical... HA HA HA!!!"; break;
                    case 4:
                        mText = "Hey I'm totally kidding!"; break;
                    case 5:
                        mText = "Of course I remember you!"; break;
                    case 6:
                        mText = "I got you didn't I??"; break;

                    default:
                        mText = "Options"; // Sets GameViewFragment- dialogueState to OPTIONS
                } break;


            case "Options":
                switch (dialoguePoint) {

                    //Draws both Options to the screen awaiting response

                    case 1:
                        mText = "Okay, you got me"; break;
                    case 2:
                        mText = "Pfft... No!"; break;

                } break;


            case "Response":

                //Identify which what lines need to be returned based on the response given by player
                switch (response) {

                    case 1:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "HA HA! Oh that never gets old"; break;

                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                    case 2:
                        switch(dialoguePoint) {

                            case 1:
                                mText = "Sure buddy..."; break;

                            default:
                                mText = "None"; // Sets GameViewFragment- dialogueState to NONE

                        } break;

                } break;
        }
        return mText;
    }




    public static String shopConvo(int dialoguePoint, int coin) {

        String mText = null;

        //Assume player has no coins to start
        String coinState = "No Coins";

        //If player has coins but too few  change coinState
        if (coin > 0 && coin < 10) {
            coinState = "Short";
        }

        //Player has enough coins change coinState
        if (coin == 10) {
            coinState = "Enough Coins";
        }

        switch (coinState) {

            case "No Coins":
                switch (dialoguePoint) {

                    case 1:
                        mText = "Hi! I'm Suzie"; break;
                    case 2:
                        mText = "I check up on coins"; break;
                    case 3:
                        mText = "If you see any in the world"; break;
                    case 4:
                        mText = "Pick them up by pressing A"; break;
                    case 5:
                        mText = "There are 10 coins to find"; break;
                    case 6:
                        mText = "If you lose track of them"; break;
                    case 7:
                        mText = "Come see me!"; break;
                    case 8:
                        mText = "Good Luck!"; break;

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
                        mText = "Wow. You found them!"; break;
                    case 2:
                        mText = "What a master explorer you are!"; break;

                    default:
                        mText = "None";
                }break;

        }
        return mText;
    }


    public static String ronAndHenryConvo(int dialoguePoint, String mText, int response) {

        if(ronAndHenryConvoCounter > 0) {
            return ronAndHenryConvo2(dialoguePoint, mText, response);
        }



        switch(dialoguePoint) {

            case 1:
                mText = "Man: Back in my day..."; break;
            case 2:
                mText = "This was all a 10x10 grid"; break;
            case 3:
                mText = "If you hit a wall..."; break;
            case 4:
                mText = "You'd go straight through!"; break;
            case 5:
                mText = "As if it wasn't even there!"; break;
            case 6:
                mText = "Things would disappear..."; break;
            case 7:
                mText = "For no reason at all!"; break;
            case 8:
                mText = "Everything looked strange"; break;
            case 9:
                mText = "Warped and blurred"; break;
            case 10:
                mText = "Then one day..."; break;
            case 11:
                mText = "The world made sense"; break;
            case 12:
                mText = "Boy: But Pa..."; break;
            case 13:
                mText = "Things jitter sometimes"; break;
            case 14:
                mText = "Everyone looks the same"; break;
            case 15:
                mText = "Man: That's not true"; break;
            case 16:
                mText = "People's shirts are different"; break;
            case 17:
                mText = "Boy: Come on Pa..."; break;
            case 18:
                mText = "Man: Oh alright...";  break;
            case 19:
                mText = "Boy: But these things..."; break;
            case 20:
                mText = "Will they ever change?"; break;
            case 21:
                mText = "Man: Well..."; break;
            case 22:
                mText = "Maybe someday they will"; break;
            case 23:
                mText = ". . ."; break;
            case 24:
                mText = "Someday..."; break;

            default:
                ronAndHenryConvoCounter++;
                mText = "None";
        }
        return mText;

    }


    public static String ronAndHenryConvo2(int dialoguePoint, String mText, int response) {


        switch(dialoguePoint) {

            case 1:
                mText = "Man: Back in my day..."; break;
            case 2:
                mText = "Boy: Pa..."; break;
            case 3:
                mText = "That talk was super long"; break;
            case 4:
                mText = "We can't have it again"; break;
            case 5:
                mText = "People will get bored"; break;
            case 6:
                mText = "Man: *sigh*"; break;
            case 7:
                mText = "You're probably right son"; break;
            case 8:
                mText = "You're probaly right..."; break;
            case 9:
                mText = ". . ."; break;
            case 10:
                mText = "You're probably... right"; break;
            case 11:
                mText = "Boy: Pa!"; break;
            case 12:
                mText = "Man: What??"; break;
            case 13:
                mText = "Boy: Stop!"; break;

            default:
                mText = "None";
        }
        return mText;

    }

}
