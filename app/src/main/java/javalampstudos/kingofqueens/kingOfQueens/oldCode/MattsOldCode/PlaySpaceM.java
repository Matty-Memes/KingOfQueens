package javalampstudos.kingofqueens.kingOfQueens.oldCode.MattsOldCode;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.CardZone;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Hand;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 06/02/2017.
 */
public class PlaySpaceM extends GameObject
{
    private final int maxLifeValue = 6;
    private Deck deck;
   private Hand hand;
    private int life;
    //private graveYard GraveYard;
    int amountOfManaTypes =0;
    private ManaCounterM[] manaCounter = new ManaCounterM[7];
    private CardZone zoneLeft,zoneMiddle,zoneRight;
    // NOTE : MATT THIS CONSTRUCTOR NEEDS UPDATED.
    public PlaySpaceM(float x, float y, int width, int height, Bitmap sprite, Deck deck,
                      Hand hand, int life,
                      ManaCounterM[] manaCounter, CardZone zoneLeft,
                      CardZone zoneMiddle, CardZone zoneRight, int currentCard, int deckSize) {
        super(x, y, width, height, sprite,true);
        this.deck = deck;
        this.hand = hand;
        this.life = maxLifeValue;
        this.zoneLeft = zoneLeft;
        this.zoneMiddle = zoneMiddle;
        this.zoneRight = zoneRight;
        this.currentCard = currentCard;
        this.deckSize = deckSize;
    }

    int currentCard =0;
    int deckSize = 30;

    //Matt: My mana counter implementation
    //Assume a manacard has been played
    public void playMana()
    {
        for (int i= 0; i<amountOfManaTypes;i++)
        {
            //if (manaCard.getManaType()==manaCounter[i].getManaType())
            manaCounter[i].addToTotal();
            //else
            //manaCounter[i].setManaType(manaCard.getManaType());
            //manaCounter[i].setManaType(manaCard.getManatype);
            manaCounter[i].addToTotal();
            amountOfManaTypes++;
        }
    }
    //Assume an attack has been made
    public void Attack(MonsterCard card)
    {
        for (int i = 0;i < amountOfManaTypes;i++)
        {
            //since brian has made the monstercard use hashmaps for mana cost we'll do some assumptions
            //assume this is calling an array of mana needed
            //if (card.getAttackManaRequirement() == manaCounter[i].getManaType()) {
            // Normally we'll take the cost of that manaType from the card but that isn't in the current MonsterCard
            int cost = 3;
            manaCounter[i].useMana(cost);
        }
    }

}
