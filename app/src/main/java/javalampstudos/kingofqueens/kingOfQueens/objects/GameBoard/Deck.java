package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import java.util.ArrayList;

/**
 * Created by Matt on 06/02/2017.
 */

public class Deck extends GameObject {

    private int noOfMonsterCards,noOfManaCards,noOfSupportCards;
    private int deckSize=0;
    final private int MAXDECKSIZE=40;

    private BasicCard[] deck = new BasicCard[MAXDECKSIZE];

    public Deck(float x, float y, int width, int height,
    Bitmap sprite)
    {
        super(x, y, width, height, sprite);
    }

    public BasicCard[] getDeck() {
        return deck;
    }

    public void setDeck(BasicCard[] deck) {
        this.deck = deck;
    }

    boolean deckSizeCheck(boolean deckMaximum)
    {
        if (deckSize>MAXDECKSIZE)
            return true;
        else return false;
    }

    /*Deck assembly*/

    private ArrayList<MonsterCard> monsterArray = new ArrayList<>(noOfMonsterCards);
    public void addMonstersCards(MonsterCard monsterCard)
    {
        monsterArray.add(noOfMonsterCards,monsterCard);
    }
    private ArrayList<ManaCard> manaArray= new ArrayList<>(noOfManaCards);
    public void addManaCards(ManaCard manaCard)
    {
        manaArray.add(noOfManaCards,manaCard);

    }

    private ArrayList<SupportCard> supportArray = new ArrayList<>(noOfSupportCards);
    public void addSupportCards(SupportCard supportCard)
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

}
