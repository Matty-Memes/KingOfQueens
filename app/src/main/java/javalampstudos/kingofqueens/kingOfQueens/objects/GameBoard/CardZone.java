package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import android.graphics.Rect;
/**
 * Created by Matt on 13/03/2017.
 * alterd by Brian on 27/03
 */

public class CardZone {
    private boolean active;
    private BasicCard currentCard;
    private Rect Zone;

    public CardZone(boolean active, BasicCard currentCard, Rect zone) {
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

    public BasicCard getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(BasicCard currentCard) {
        this.currentCard = currentCard;
    }

    //the cardZones are used to work out adjacency for certain moves e.g turret destruction.



}
