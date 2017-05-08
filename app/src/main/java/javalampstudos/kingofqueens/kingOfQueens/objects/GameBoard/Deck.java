package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.GameLoop;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.JSONcardLibrary;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import javalampstudos.kingofqueens.kingOfQueens.util.randomGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Matt on 06/02/2017.
 */

// This doesn't need to extend GameObject
// Seperate the drawing of the deck image from the actual deck logic
public class Deck {

    private int noOfMonsterCards,noOfManaCards,noOfSupportCards;
    private int deckSize=0;
    // Generate 13 values
    final private int MAXDECKSIZE = 14;
//    final private int MAXDECKSIZE = 40;
    // Might need to move this
    private JSONcardLibrary lib = new JSONcardLibrary();
    private randomGenerator rand = new randomGenerator();

    //Matthew 40149561
    //Will use card ID numbers to specify cards to place in deck
    /*A deck with Engineering and Medic cards*/
    //private int startingDeck1[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,41,41,41,41,41,41,43,43,43,43,43,43,46,46,47,49,49,50,52,53,55,57,58,59};

    //Stores the players custom deck
    private int playerDeck[] = new int[MAXDECKSIZE];


    // Could be re-used
    public ArrayList<MonsterCard> monsterArray = new ArrayList<>(noOfMonsterCards);
    public ArrayList<ManaCard> manaArray= new ArrayList<>(noOfManaCards);
    private ArrayList<SupportCard> supportArray = new ArrayList<>(noOfSupportCards);
    private ArrayList<BasicCard> basicArray = new ArrayList<>(MAXDECKSIZE);

    // Andrew - 40083349
    public Deck ()

    {

    }

//Matt:old constructor
    //    public Deck(float x, float y, int width, int height,
//    Bitmap sprite, boolean player, ArrayList<MonsterCard> monsterArray,ArrayList<ManaCard> manaArray,ArrayList<SupportCard> supportArray)
//    {
//        super(x, y, width, height, sprite, player);
//        monsterArray = this.monsterArray;
//        manaArray = this.manaArray;
//        supportArray = this.supportArray;
//    }

    //getters and setters
    public int getDeckSize()
    {
        return deckSize;
    }

    public int getNoOfMonsterCards()
    {
        return noOfMonsterCards;
    }

    public int getNoOfManaCards()
    {
        return noOfManaCards;
    }

    public int getNoOfSupportCards()
    {
        return noOfSupportCards;
    }

    public int getMAXDECKSIZE()
    {
        return MAXDECKSIZE;
    }

    public ArrayList<MonsterCard> getMonsterArray()
    {
        return monsterArray;
    }

    public void setMonsterArray(ArrayList<MonsterCard> monsterArray)
    {
        this.monsterArray = monsterArray;
    }

    public ArrayList<ManaCard> getManaArray()
    {
        return manaArray;
    }

    public void setManaArray(ArrayList<ManaCard> manaArray)
    {
        this.manaArray = manaArray;
    }

    public ArrayList<SupportCard> getSupportArray()
    {
        return supportArray;
    }

    public void setSupportArray(ArrayList<SupportCard> supportArray)
    {
        this.supportArray = supportArray;
    }

    public ArrayList<BasicCard> getBasicArray()
    {
        return basicArray;
    }

    public void setBasicArray(ArrayList<BasicCard> basicArray)
    {
        this.basicArray = basicArray;
    }

    private void loadLibraryAssets (GameLoop loop)

    {
        lib.loadAssets(loop);
    }


    // first parse the JSON for all the cards
    // then allocate the cards

    // create deck would be run in GameLoop
    // Created by Andrew - 40083349
    public void createDeck (GameLoop loop)

    {
        // Think of a way to run this only once
        lib.loadAssets(loop);

        for (int i = 0; i < MAXDECKSIZE; i++)

        {
            int randex = rand.generateRandomNumber();

            System.out.println("Index generated was " + randex);

            // Interaction with each array list
            // mirrored in JSONcardLibrary
            if (randex <= 7)
            {
                monsterArray.add(lib.monsterCards.get(randex));

            }

            // this needs to be offset since it's starts in a new array

            // trap this in certain bounds
            if (randex > 7 && randex <= 13)

            {
                manaArray.add(lib.manaCards.get(randex % 8));

            }

        }

    }

    //Matt 40149561
    /*Deck assembly*/
    //generates a deck
    public void generateDeck(int cardIDs[])
    {
        for(int i = 0;i<MAXDECKSIZE;i++)
        {
            if(cardIDs[i]<=39)
            {
                monsterArray.add(lib.monsterCards.get((cardIDs[i]-1)));
                noOfMonsterCards++;
                deckSize++;
            }
            if((cardIDs[i]>39) && (cardIDs[i] <=45))
            {
                manaArray.add(lib.manaCards.get(manaIDLookup(cardIDs[i])));
                noOfManaCards++;
                deckSize++;
            }
            if(cardIDs[i] >45)
            {
                supportArray.add(lib.supportCards.get(supportIDLookup(cardIDs[i])));
                noOfSupportCards++;
                deckSize++;
            }
        }
    }

    //Matt 40149561: Called from the DeckBuilder to add selected card to the Deck
    public void addToDeck(int ID)
    {
        if(!isDeckFull())
        {
            playerDeck[deckSize] = ID;
            deckSize++;
//            if (ID <= 39)
//            {
//                addMonstersCards(lib.monsterCards.get(ID - 1));
//            }
//            if ((ID > 39) && (ID <= 45))
//            {
//                addManaCards(lib.manaCards.get(manaIDLookup(ID)));
//            }
//            if (ID > 45)
//            {
//                addSupportCards(lib.supportCards.get(supportIDLookup(ID)));
//            }
        }
        else
            System.out.print("The deck is full");
    }
    private void addMonstersCards(MonsterCard monsterCard)
    {
        monsterArray.add(monsterCard);
        noOfMonsterCards++;
    }
    private void addManaCards(ManaCard manaCard)
    {
        manaArray.add(manaCard);
        noOfManaCards++;
    }
    private void addSupportCards(SupportCard supportCard)
    {
        supportArray.add(supportCard);
        noOfSupportCards++;
    }

    //Matt 40149561
    //Since ID doesn't map as easily to other card types, this looks up the position in the array
    private int manaIDLookup(int ID)
    {
        int pos = 0;
        switch(ID)
        {
            case 40: pos = 0;
                break;
            case 41:pos = 1;
                break;
            case 42:pos = 2;
                break;
            case 43:pos = 3;
                break;
            case 44:pos = 4;
                break;
            case 45:pos = 5;
                break;
        }
        return pos;
    }
    private int supportIDLookup(int ID)
    {
        int pos =0;
        switch(ID)
        {
            case 46:pos = 0;
                break;
            case 47:pos = 1;
                break;
            case 48:pos = 2;
                break;
            case 49:pos = 3;
                break;
            case 50:pos = 4;
                break;
            case 51:pos = 5;
                break;
            case 52:pos = 6;
                break;
            case 53:pos = 7;
                break;
            case 54:pos = 8;
                break;
            case 55:pos = 9;
                break;
            case 56:pos = 10;
                break;
            case 57:pos = 11;
                break;
            case 58:pos = 12;
                break;
            case 59:pos = 13;
                break;
        }
        return pos;
    }

    //Matt 40149561
    private boolean isDeckFull()
    {
        return (deckSize>=MAXDECKSIZE);
    }

    //If the player wants to recreate their deck call this method
    private void resetDeck()
    {
        for(int i=0;i<MAXDECKSIZE;i++)
            playerDeck[i] = -1;
        deckSize = 0;
    }

}
