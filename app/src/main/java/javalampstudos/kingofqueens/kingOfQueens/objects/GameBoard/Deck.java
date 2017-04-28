package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.JSONcardLibrary;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Matt on 06/02/2017.
 */

public class Deck extends GameObject {

    private int noOfMonsterCards,noOfManaCards,noOfSupportCards;
    private int deckSize=0;
    final private int MAXDECKSIZE=40;

    private ArrayList<MonsterCard> monsterArray = new ArrayList<>(noOfMonsterCards);
    private ArrayList<ManaCard> manaArray= new ArrayList<>(noOfManaCards);
    private ArrayList<SupportCard> supportArray = new ArrayList<>(noOfSupportCards);



    public Deck(float x, float y, int width, int height,
    Bitmap sprite, boolean player, ArrayList<MonsterCard> monsterArray,ArrayList<ManaCard> manaArray,ArrayList<SupportCard> supportArray)
    {
        super(x, y, width, height, sprite, player);
        monsterArray = this.monsterArray;
        manaArray = this.manaArray;
        supportArray = this.supportArray;
    }
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
