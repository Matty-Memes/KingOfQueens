package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.basicCard;

import android.graphics.Rect;
/**
 * Created by Matt on 13/03/2017.
 * alterd by Brian on 27/03
 */

public class CardZone {
    private boolean active;
    private basicCard currentCard;
    private Rect Zone;

    public CardZone(boolean active, basicCard currentCard, Rect zone) {
        this.active = active;
        this.currentCard = currentCard;
        Zone = zone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public basicCard getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(basicCard currentCard) {
        this.currentCard = currentCard;
    }

    //the cardZones are used to work out adjacency for certain moves e.g turret destruction.



}
