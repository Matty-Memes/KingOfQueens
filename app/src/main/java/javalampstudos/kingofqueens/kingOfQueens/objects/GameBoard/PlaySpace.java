package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.graveYard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Hand;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.ManaCounter;

/**
 * Created by Matt on 06/02/2017.
 */

public class PlaySpace  {
    private final int maxLifeValue = 6;
    private Deck deck;
    private Hand hand;
    private int life;
    private graveYard GraveYard;
    private ManaCounter manaCounter;
    private CardZone [] cardZones;

    public PlaySpace( Deck deck, Hand hand,
                     graveYard graveYard, ManaCounter manaCounter, CardZone[] cardZones,
                     int currentCard, int deckSize) {

        this.deck = deck;
        this.hand = hand;
        this.life = maxLifeValue;
        GraveYard = graveYard;
        this.manaCounter = manaCounter;
        this.cardZones = new CardZone[3];
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

    public void setHandChange(Hand hand) {
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


    public ManaCounter getManaCounter() {
        return manaCounter;
    }


    public CardZone[] getCardZones() {
        return cardZones;
    }

    public void setCardZones(CardZone[] cardZones) {
        this.cardZones = cardZones;
    }

    public void setupPlay()
    {
       // deck.generateDeck();

    }


   


     // this method just checks if the defending card of the above method has died, then adds it to the graveyard
     // this method needs to be sent in to where the stuff is being played. NOTE:: BRIAN
     //40111707
     //brian
     public void determineDeathOfMonster(MonsterCard dyingCard){
         if(dyingCard.getHealth() <= 0)
         {
           GraveYard.addToGraveYard(dyingCard);
            removeLife();
             // the card needs to be removed here aswell.
         }

     }


     // brian method
    //40111707
    public boolean checkAllZonesAreActive(){
        boolean allZonesActive =false;
       for(int i=0; i < getCardZones().length || allZonesActive; i++)
       {
           if(!getCardZones()[i].isActive())
           {
               allZonesActive = false;
           }
           else {
               allZonesActive = true;
           }
       }
       return allZonesActive;
    }

// 040111707
    // brians method
    // this method puts the card into the hand, and also correct copies of it into their arraylist holders for logic reasons.

    public void addToHand(BasicCard card, int index) {

        this.hand.getHand()[index] = card;
        int [] cardTypePos = findRealCard(card);
        switch(cardTypePos[0])
        {
            case 0: hand.getMonsterCardArrayList().add(deck.monsterArray.get(cardTypePos[1])); break;
            case 1 : hand.getManaCardArrayList().add(deck.manaArray.get(cardTypePos[1])); break;
            case 2: hand.getSupportCardArrayList().add(deck.supportArray.get(cardTypePos[1])); break;
        }

    }

    //40111707
    // Brians method
    // this method will comare the hand card with the correct card within the deck so that logic can be done to it.
    public int [] findRealCard(BasicCard cardToBeFound)
    {
        int pos =-1;
        int arrayType = -1;
        int[] position = new int [1];
        switch(cardToBeFound.id)
        {
            case 0:
                for (int i = 0; i < deck.monsterArray.size(); i++) {
                    if(cardToBeFound.sprite == deck.monsterArray.get(i).sprite)
                    {
                        pos = i ;
                        arrayType = cardToBeFound.id;
                    }
                } break;
            case 1:
                for (int i = 0; i <deck.manaArray.size(); i++) {
                    if(cardToBeFound.sprite == deck.manaArray.get(i).sprite)
                    {
                        pos = i ;
                        arrayType = cardToBeFound.id;
                    }
                } break;
            case 2:
                for (int i = 0; i <deck.supportArray.size(); i++) {
                    if(cardToBeFound.sprite == deck.supportArray.get(i).sprite)
                    {
                        pos = i ;
                        arrayType = cardToBeFound.id;
                    }
                } break;

            default :    break;
        }
        position [0] = pos;
        position[1] = arrayType;
        return position;

    }
    //When a monster is destroyed, call this method
    public void removeLife()
    {
        life--;
    }


    int currentCard =0;
    int deckSize = 30;
   /* public void addToHand(BasicCard card)
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

    }*/

    //MATT:takes the information of the card currently held by the player/AI and the card placed in the CardZone and swaps the cards if the criteria is met
    public void evolve(MonsterCard heldCard)
    {
        if(cardZones[1].getCurrentCard().evolutionCheck(heldCard))
            cardZones[1].setCurrentCard(heldCard);
        //We could throw in some fun animation here if we have the time
        else;
            //return heldcard to hand
    }




}
