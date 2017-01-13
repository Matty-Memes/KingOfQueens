package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 */

public class ManaCard extends BasicCard {
    private CardTypes CardSchool;
    private ManaTypes ManaType;
    private int ManaValue;

    public ManaCard(int x, int y, int width, int height, Bitmap Sprite, String name, CardTypes cardSchool, ManaTypes manaType, int manaValue) {
        super(x, y, width, height, Sprite, name);
        CardSchool = cardSchool;
        ManaType = manaType;
        ManaValue = manaValue;
    }

    public CardTypes getCardSchool() {
        return CardSchool;
    }

    public void setCardSchool(CardTypes cardSchool) {
        CardSchool = cardSchool;
    }

    public ManaTypes getManaType() {
        return ManaType;
    }

    public void setManaType(ManaTypes manaType) {
        ManaType = manaType;
    }

    public int getManaValue() {
        return ManaValue;
    }

    public void setManaValue(int manaValue) {
        ManaValue = manaValue;
    }
}
