package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

// Android imports

import android.graphics.Bitmap;
import android.graphics.Canvas;

// Local imports

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardLevel;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardTypes;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;

public class MonsterCard extends BasicCard {

    private CardTypes CardSchool; // ENUM
    private CardLevel level; // ENUM
    private int health;
    private CardTypes strength;
    private CardTypes weakness; // ENUM

    // Attack Values

    private String attack1Name;
    private int attack1Strength;
    // holds a list of the mana types required
    private ManaTypes [] attack1ManaRequired;

    private String attack2Name;
    private int attack2Strength;
    // holds a list of the mana types required
    private ManaTypes [] attack2ManaRequired;

    // Reduce the number of fields somehow

    public MonsterCard(int x, int y, int width, int height, Bitmap Sprite, boolean destroyed, String name, CardTypes CardSchool, CardLevel level, int health, CardTypes strength, CardTypes weakness,
                       String attack1Name, int attack1Strength, ManaTypes [] attack1ManaRequired, String attack2Name, int attack2Strength, ManaTypes [] attack2ManaRequired ) {
        super(x, y, width, height, Sprite, name, destroyed);

        this.CardSchool = CardSchool;
        this.level = level;
        this.health = health;
        this.strength = strength;
        this.weakness = weakness;

        this.attack1Name = attack1Name;
        this.attack1Strength = attack1Strength;
        this.attack1ManaRequired = attack1ManaRequired;
        this.attack2Name = attack2Name;
        this.attack2Strength = attack2Strength;
        this.attack2ManaRequired = attack2ManaRequired;

    }

    // ADD GETTERS AND SETTERS

    public CardTypes getCardType() {
        return CardSchool;
    }

    public void setCardType(CardTypes cardType) {
        CardSchool = cardType;
    }

    public CardLevel getLevel() {
        return level;
    }

    public void setLevel(CardLevel level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public CardTypes getWeakness() {
        return weakness;
    }

    public void setDefence(CardTypes weakness) {
        this.weakness = weakness;
    }

    // draw monster cards to the screen

    public void draw(Canvas canvas)

    {

        // canvas.drawBitmap();


    }

}
