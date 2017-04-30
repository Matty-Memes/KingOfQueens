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
    // For test purposes
    final private int MAXDECKSIZE = 14;
    // Might need to move this
    private JSONcardLibrary lib = new JSONcardLibrary();
    private randomGenerator rand = new randomGenerator();

    // Could be re-used
    public ArrayList<MonsterCard> monsterArray = new ArrayList<>(noOfMonsterCards);
    public ArrayList<ManaCard> manaArray= new ArrayList<>(noOfManaCards);
    public ArrayList<SupportCard> supportArray = new ArrayList<>(noOfSupportCards);

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

    // first parse the JSON for all the cards
    // then allocate the cards

    // create deck would be run in GameLoop
    // Created by Andrew - 40083349
    public void createDeck (GameLoop loop)

    {
        lib = new JSONcardLibrary();
        lib.loadAssets(loop);

        for (int i = 0; i < MAXDECKSIZE; i++)

        {
            int randex = rand.generateRandomNumber();
            randex = randex-1;

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
    private void generateDeck(ArrayList<MonsterCard> libraryMonster,ArrayList<ManaCard> libraryMana,ArrayList<SupportCard> librarySupport)
    {
        for (int i = 0; i < 17; i++)
        {
            monsterArray.add(i, libraryMonster.get(i));
            noOfMonsterCards++;
        }
        //add six mana cards for Engineer and Medic
        for(int i = 0;i<5;i++)
        {
            manaArray.add(i, libraryMana.get(1)); //Medic mana
            noOfManaCards++;
        }
        for(int i = 6;i<11;i++)
        {
            manaArray.add(i, libraryMana.get(3)); //Engineer mana
            noOfManaCards++;
        }
        //add support cards to library
        for(int i = 0;i<librarySupport.size();i++)
        {
            supportArray.add(i, librarySupport.get(i));
            noOfSupportCards++;
        }
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
