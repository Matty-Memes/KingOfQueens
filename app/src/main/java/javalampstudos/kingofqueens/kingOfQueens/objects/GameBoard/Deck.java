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
    // Might need to move this
    private JSONcardLibrary lib = new JSONcardLibrary();
    private randomGenerator rand = new randomGenerator();

    //Matthew 40149561
    //Will use card ID numbers to specify cards to place in deck
    /*A deck with Engineering and Medic cards*/
    private int startingDeck1[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,49,49,49,49,49,49,51,51,51,51,51,51,54,54,55,57,57,58,60,61,63,65,66,67};

    // Could be re-used
    public ArrayList<MonsterCard> monsterArray = new ArrayList<>(noOfMonsterCards);
    public ArrayList<ManaCard> manaArray= new ArrayList<>(noOfManaCards);
    private ArrayList<SupportCard> supportArray = new ArrayList<>(noOfSupportCards);
    private ArrayList<BasicCard> basicArray = new ArrayList<>(MAXDECKSIZE);

    public Deck ()

    {

    }


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

    private void loadLibraryAssets ()

    {

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

    /*Deck assembly*/
    //generates a starting deck
    /*
    public void generateDeck(GameLoop loop,int cardIDs[])
    {
        lib = new JSONcardLibrary();
        lib.loadAssets(loop);
        for(int i = 0;i<MAXDECKSIZE;i++)
        {
            if(cardIDs[i]<=47)
            {
                monsterArray.add(lib.monsterCards.get((cardIDs[i]-1)));
                noOfMonsterCards++;
                deckSize++;
            }
            if((cardIDs[i]>47) && (cardIDs[i] <=53))
            {
                manaArray.add(lib.manaCards.get(manaIDLookup(cardIDs[i])));
                noOfManaCards++;
                deckSize++;
            }
            if(cardIDs[i] >53)
            {
                supportArray.add(lib.supportCards.get(supportIDLookup(cardIDs[i])));
                noOfSupportCards++;
                deckSize++;
            }
        }
    }
    */

    //Since ID doesn't map as easily to other card types, this looks up the position in the array
    private int manaIDLookup(int ID)
    {
        int pos = 0;
        switch(ID)
        {
            case 48: pos = 0;
                break;
            case 49:pos = 1;
                break;
            case 50:pos = 2;
                break;
            case 51:pos = 3;
                break;
            case 52:pos = 4;
                break;
            case 53:pos = 5;
                break;
        }
        return pos;
    }
    private int supportIDLookup(int ID)
    {
        int pos =0;
        switch(ID)
        {
            case 54:pos = 0;
                break;
            case 55:pos = 1;
                break;
            case 56:pos = 2;
                break;
            case 57:pos = 3;
                break;
            case 58:pos = 4;
                break;
            case 59:pos = 5;
                break;
            case 60:pos = 6;
                break;
            case 61:pos = 7;
                break;
            case 62:pos = 8;
                break;
            case 63:pos = 9;
                break;
            case 64:pos = 10;
                break;
            case 65:pos = 11;
                break;
            case 66:pos = 12;
                break;
            case 67:pos = 13;
                break;
        }
        return pos;
    }

    //These methods will be used in the deck assembler fragment
    private void addMonstersCards(MonsterCard monsterCard)
    {
        if(!isDeckFull())
        {
            monsterArray.add(monsterCard);
            noOfMonsterCards++;
        }
        else
            System.out.print("The deck is full");
    }
    private void addManaCards(ManaCard manaCard)
    {
        if(!isDeckFull())
        {
            manaArray.add(manaCard);
            noOfManaCards++;
        }
        else
            System.out.print("The deck is full");
    }
    private void addSupportCards(SupportCard supportCard)
    {
        if(!isDeckFull())
        {
            supportArray.add(supportCard);
            noOfSupportCards++;
        }
        else
            System.out.print("The deck is full");

    }
    private boolean isDeckFull()
    {
        return (deckSize>MAXDECKSIZE);
    }
}
