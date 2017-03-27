package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.graveYard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 06/02/2017.
 */

public class PlaySpace extends GameObject {
    private Deck deck;
    private BasicCard[] hand = new BasicCard[10];
    private int life = 6;
    private graveYard GraveYard;
    private ManaCounter[] manaCounter = new ManaCounter[6];
    private CardZone zoneLeft,zoneMiddle,zoneRight;


    public PlaySpace(float x, float y, int width, int height,
               Bitmap sprite,Deck deck, BasicCard[] hand, int life, Deck graveyard, ManaCounter[] manaCounter)
    {
        super(x, y, width, height, sprite);
        this.deck=deck;
        this.life=life;
        this.graveyard=graveyard;
        this.manaCounter=manaCounter;
    }

    public int getLife()
    {
        return life;
    }
    public void setupPlay()
    {
        deck.generateDeck();

    }


    //When a monster is destroyed, call this method
    public void removeLife()
    {
        life--;
    }



    int currentCard =0;
    int deckSize = 30;
    public void addToHand(BasicCard card)
    {
        //checks if there is room in the hand
        if (currentCard< 9)
        {
            //set the current position of card to the current card
            card = deck.draw();
            hand[currentCard] = card;
            currentCard++;
            deckSize--;
        }
        else;
            //move card to the graveyard

    }






}
