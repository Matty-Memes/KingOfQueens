package javalampstudos.kingofqueens.kingOfQueens.Game;

import android.graphics.Bitmap;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 */

public class SupportCard extends BasicCard {
    private CardTypes CardSchool;
    private BuffType  buff;

    public SupportCard(Bitmap bitmapImage, String name, CardTypes cardSchool, BuffType buff) {
        super(bitmapImage, name);
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
