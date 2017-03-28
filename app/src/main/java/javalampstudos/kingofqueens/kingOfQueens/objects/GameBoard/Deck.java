package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.basicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.manaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.monsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.supportCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import java.util.ArrayList;

/**
 * Created by Matt on 06/02/2017.
 */

public class Deck extends GameObject {

    private int noOfMonsterCards,noOfManaCards,noOfSupportCards;
    private int deckSize=0;
    final private int MAXDECKSIZE=40;

    private basicCard[] deck = new basicCard[MAXDECKSIZE];

    public Deck(float x, float y, int width, int height,
    Bitmap sprite)
    {
        super(x, y, width, height, sprite);

    }

    public basicCard[] getDeck() {
        return deck;
    }

    public void setDeck(basicCard[] deck) {
        this.deck = deck;
    }

    public int getDeckSize()
    {
        return deckSize;
    }

    boolean deckSizeCheck(boolean deckMaximum)
    {
        if (deckSize>MAXDECKSIZE)
            return true;
        else return false;
    }

    /*Deck assembly*/

    private ArrayList<monsterCard> monsterArray = new ArrayList<>(noOfMonsterCards);
    public void addMonstersCards(monsterCard monsterCard)
    {
        monsterArray.add(noOfMonsterCards,monsterCard);
    }
    private ArrayList<manaCard> manaArray= new ArrayList<>(noOfManaCards);
    public void addManaCards(manaCard manaCard)
    {
        manaArray.add(noOfManaCards,manaCard);

    }

    private ArrayList<supportCard> supportArray = new ArrayList<>(noOfSupportCards);
    public void addSupportCards(supportCard supportCard)
    {
        supportArray.add(noOfSupportCards,supportCard);

    }


    public void generateDeck()
    {
        boolean deckMaximum =false;
        while(!deckMaximum)
        {
            for (int i = 0; i < noOfMonsterCards; i++) {
                deckSize++;
                deckSizeCheck(deckMaximum);
                deck[i] = monsterArray.get(i);
            }
            for (int i = 0; i < noOfManaCards; i++) {
                deckSize++;
                deckSizeCheck(deckMaximum);
                deck[deckSize] = manaArray.get(i);
            }
            for (int i = 0; i < noOfSupportCards; i++) {
                deckSize++;
                deckSizeCheck(deckMaximum);
                deck[deckSize] = supportArray.get(i);

            }
        }

    }

    public basicCard draw()
    {
        basicCard drawnCard=deck[0];
        for (int i=0;i<deckSize;i++) {
            deck[i] = deck[i + 1];
        }
        return drawnCard;
    }


}
