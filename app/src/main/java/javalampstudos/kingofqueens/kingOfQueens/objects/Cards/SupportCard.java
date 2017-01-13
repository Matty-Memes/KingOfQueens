package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BuffType;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardTypes;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 */

public class SupportCard extends BasicCard {
    private CardTypes CardSchool;
    private BuffType buff;

    public SupportCard(int x, int y, int width, int height, Bitmap Sprite, String name, CardTypes cardSchool, BuffType buff) {
        super(x, y, width, height, Sprite, name);
        CardSchool = cardSchool;
        this.buff = buff;
    }

    public CardTypes getCardSchool() {
        return CardSchool;
    }

    public void setCardSchool(CardTypes cardSchool) {
        CardSchool = cardSchool;
    }

    public BuffType getBuff() {
        return buff;
    }

    public void setBuff(BuffType buff) {
        this.buff = buff;
    }
}
