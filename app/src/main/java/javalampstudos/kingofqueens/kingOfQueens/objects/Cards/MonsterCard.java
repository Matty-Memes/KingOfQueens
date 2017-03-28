package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;



// Android imports

import android.graphics.Bitmap;
import android.graphics.Canvas;

// Local imports


public class monsterCard extends basicCard {
    private final double attackBonus = 1.5;
    private cardLevel level; // ENUM
    private int health;
    private int defence;
    private int attackValue;
    // holds a list of the mana types required
    private manaTypes[] attackManaRequired;

    // Brian + Matt
    // Modified by Andrew - 27/03/17
    // constructor
    public monsterCard(int x, int y, int width, int height, Bitmap Sprite,
                       boolean destroyed, int pointerID,
                       cardLevel level, int health, int defence,
                       cardSchools cardSchool, int attackValue,
                       manaTypes[] attackManaRequired) {
        super(x, y, width, height, Sprite, cardSchool, destroyed, pointerID);
        this.level = level;
        this.health = health;
        this.defence = defence;

        this.attackValue = attackValue;
        this.attackManaRequired = attackManaRequired;
    }


    // ADD GETTERS AND SETTERS //

    public cardLevel getLevel() {
        return level;
    }

    public void setLevel(cardLevel level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public double getAttackBonus() {
        return attackBonus;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public manaTypes[] getAttackManaRequired() {
        return attackManaRequired;
    }

    public void setAttackManaRequired(manaTypes[] attackManaRequired) {
        this.attackManaRequired = attackManaRequired;
    }


    // draw monster cards to the screen

    public void draw(Canvas canvas)

    {
        super.draw(canvas);

    }




// Brian's + Matts Method
    public void attack(monsterCard attackingCard, monsterCard defendingCard)
    {

        //monsterCard target = this.monsterCard;
        //Matt: I need to get touch input to tell the class what monster to attack.

        if(compareCardAttackBonus(attackingCard.getCardSchool(),defendingCard.getCardSchool()) == true) {
            defendingCard.health -= (attackingCard.attackValue *attackBonus);

        }else {
            defendingCard.health -= attackingCard.attackValue;

        }


    }

    // gets both cards school types and then goes through the if statements in order to find if the combination fits
    // if the combination fits then it returns true, this then allows for the 1.5X multiplier to be added to the attack.
    // 40111707

    public boolean compareCardAttackBonus(cardSchools attackingCard, cardSchools defendingCard)
    {
        if(attackingCard == cardSchools.MEDICS && defendingCard == cardSchools.EEECS)
        {
            return true;
        }

        else if(attackingCard == cardSchools.EEECS && defendingCard == cardSchools.ARTS_HUMANITIES)
        {
            return true;
        }

        else if(attackingCard == cardSchools.ARTS_HUMANITIES && defendingCard == cardSchools.MEDICS)

        {
            return true;

        }
        else if(attackingCard == cardSchools.ENGINEERING && defendingCard == cardSchools.SOCIAL_SCIENCES)
        {
            return true;
        }

        else if(attackingCard == cardSchools.BUILT_ENVIORNMENT && defendingCard == cardSchools.ENGINEERING)
        {
            return true;
        }

        else if(attackingCard == cardSchools.SOCIAL_SCIENCES && defendingCard == cardSchools.BUILT_ENVIORNMENT)
        {
            return true;
        }

        else {
            return false;
        }


    }

}
