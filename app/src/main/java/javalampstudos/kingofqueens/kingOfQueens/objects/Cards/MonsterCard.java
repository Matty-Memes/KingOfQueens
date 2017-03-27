package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;



// Android imports

import android.graphics.Bitmap;
import android.graphics.Canvas;

// Local imports

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardLevel;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;
import javalampstudos.kingofqueens.kingOfQueens.objects.graveYard;

public class MonsterCard extends BasicCard {

    private CardLevel level; // ENUM
    private int health;
    private int defence;
    private CardSchools school;
    private final double attackBonus = 1.5;
    // Attack Values

    private int attackValue;
    // holds a list of the mana types required
    private ManaTypes [] attackManaRequired;


    // Brian + Matt
    public MonsterCard(int x, int y, int width, int height, Bitmap Sprite, Bitmap cardBackSprite,
                       String name, String description, CardSchools cardSchool, boolean destroyed,
                       int pointerID, CardLevel level, int health, int defence, CardSchools school,
                       int attackValue, ManaTypes[] attackManaRequired) {
        super(x, y, width, height, Sprite, cardBackSprite, name, description, cardSchool, destroyed, pointerID);
        this.level = level;
        this.health = health;
        this.defence = defence;
        this.school = school;
        this.attackValue = attackValue;
        this.attackManaRequired = attackManaRequired;
    }


    // ADD GETTERS AND SETTERS //

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

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public CardSchools getSchool() {
        return school;
    }

    public void setSchool(CardSchools school) {
        this.school = school;
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

    public ManaTypes[] getAttackManaRequired() {
        return attackManaRequired;
    }

    public void setAttackManaRequired(ManaTypes[] attackManaRequired) {
        this.attackManaRequired = attackManaRequired;
    }


    // draw monster cards to the screen

    public void draw(Canvas canvas)

    {
        super.draw(canvas);

    }




// Brian's + Matts Method
    public void attack(MonsterCard attackingCard, MonsterCard defendingCard)
    {

        //MonsterCard target = this.MonsterCard;
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

    public boolean compareCardAttackBonus(CardSchools attackingCard, CardSchools defendingCard)
    {
        if(attackingCard == CardSchools.MEDICS && defendingCard == CardSchools.EEECS)
        {
            return true;
        }

        else if(attackingCard == CardSchools.EEECS && defendingCard == CardSchools.ARTS_HUMANITIES)
        {
            return true;
        }

        else if(attackingCard == CardSchools.ARTS_HUMANITIES && defendingCard == CardSchools.MEDICS)

        {
            return true;

        }
        else if(attackingCard == CardSchools.ENGINEERING && defendingCard == CardSchools.SOCIAL_SCIENCES)
        {
            return true;
        }

        else if(attackingCard == CardSchools.BUILT_ENVIORNMENT && defendingCard == CardSchools.ENGINEERING)
        {
            return true;
        }

        else if(attackingCard == CardSchools.SOCIAL_SCIENCES && defendingCard == CardSchools.BUILT_ENVIORNMENT)
        {
            return true;
        }

        else {
            return false;
        }


    }

}
