package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;

/**
 * Created by Matt on 13/03/2017.
 */

public class CardZone {
    private boolean active;
    private BasicCard currentCard;
    private CardZone leftCardzone;
    private CardZone rightCardzone;

    public CardZone(boolean active, BasicCard currentCard, CardZone leftCardzone, CardZone rightCardzone) {
        this.active = active;
        this.currentCard = currentCard;
        this.leftCardzone = leftCardzone;
        this.rightCardzone = rightCardzone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BasicCard getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(BasicCard currentCard) {
        this.currentCard = currentCard;
    }

    //the cardZones are used to work out adjacency for certain moves e.g turret destruction.
    public CardZone getLeftCardzone() {
        return leftCardzone;
    }


    public CardZone getRightCardzone() {
        return rightCardzone;
    }


}
