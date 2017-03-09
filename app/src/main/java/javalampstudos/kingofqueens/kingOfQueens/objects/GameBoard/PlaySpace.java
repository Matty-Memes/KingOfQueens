package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 06/02/2017.
 */

public class PlaySpace extends GameObject {
    private Deck deck;
    private int hand;
    private int life = 6;
    private Deck graveyard;
    private ManaCounter manacount;


    public PlaySpace(float x, float y, int width, int height,
               Bitmap sprite,Deck deck, int hand, int life, Deck graveyard, ManaCounter manacount)
    {
        super(x, y, width, height, sprite);
        this.deck=deck;
        this.hand = hand;
        this.life=life;
        this.graveyard=graveyard;
        this.manacount=manacount;
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



}
