package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import java.util.HashMap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.basicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.cardSchools;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.monsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.graveYard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 06/02/2017.
 */

public class PlaySpace extends GameObject {
    private final int maxLifeValue = 6;
    private Deck deck;
    private Hand hand;
    private int life;
    private graveYard GraveYard;
    private  ManaCounter manaCounter;
    private CardZone zoneLeft,zoneMiddle,zoneRight;
// NOTE : MATT THIS CONSTRUCTOR NEEDS UPDATED.
    public PlaySpace(float x, float y, int width, int height, Bitmap sprite, Deck deck,
                     Hand hand, int life, graveYard graveYard,
                     ManaCounter[] manaCounter, CardZone zoneLeft,
                     CardZone zoneMiddle, CardZone zoneRight, int currentCard, int deckSize) {
        super(x, y, width, height, sprite);
        this.deck = deck;
        this.hand = hand;
        this.life = maxLifeValue;
        this.GraveYard = graveYard;
        this.zoneLeft = zoneLeft;
        this.zoneMiddle = zoneMiddle;
        this.zoneRight = zoneRight;
        this.currentCard = currentCard;
        this.deckSize = deckSize;



    }


    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getLifeValue() {
        return maxLifeValue;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public graveYard getGraveYard() {
        return GraveYard;
    }

    public void setGraveYard(graveYard graveYard) {
        GraveYard = graveYard;
    }

    public void getManaCount(cardSchools manaType)
    {

        manaCounter.get(manaType);
    }

    public CardZone getZoneLeft() {
        return zoneLeft;
    }

    public void setZoneLeft(CardZone zoneLeft) {
        this.zoneLeft = zoneLeft;
    }

    public CardZone getZoneMiddle() {
        return zoneMiddle;
    }

    public void setZoneMiddle(CardZone zoneMiddle) {
        this.zoneMiddle = zoneMiddle;
    }

    public CardZone getZoneRight() {
        return zoneRight;
    }

    public void setZoneRight(CardZone zoneRight) {
        this.zoneRight = zoneRight;
    }

    public int getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(int currentCard) {
        this.currentCard = currentCard;
    }

    public int getDeckSize() {
        return deckSize;
    }

    public void setDeckSize(int deckSize) {
        this.deckSize = deckSize;
    }

    public void setupPlay()
    {
        deck.generateDeck();

    }



     // this method just checks if the defending card of the above method has died, then adds it to the graveyard
     // this method needs to be sent in to where the stuff is being played. NOTE:: BRIAN
     //40111707
     //brian
     public void determineDeathOfMonster(monsterCard dyingCard){
         if(dyingCard.getHealth() <= 0)
         {
           GraveYard.addToGraveYard(dyingCard);
            removeLife();
             // the card needs to be removed here aswell.
         }

     }
    //When a monster is destroyed, call this method
    public void removeLife()
    {
        life--;
    }


    int currentCard =0;
    int deckSize = 30;
    public void addToHand(basicCard card)
    {
        //checks if there is room in the hand
        if (currentCard< 9)
        {
            //set the current position of card to the current card
            card = deck.draw();
            hand.addToHandArray(card,currentCard); // method to add the card to the array. takes the card + the index where it should be added to.
            currentCard++;
            deckSize--;
        }
        else;
            //move card to the graveyard

    }






}
