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
    private CardSchools strength;
    private CardSchools weakness; // ENUM


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

    public MonsterCard(int x, int y, int width, int height, Bitmap Sprite,Bitmap cardBackSprite, boolean destroyed,int pointerID,
                       String name, String description, CardLevel level, int health,int defence, CardSchools cardSchools, CardSchools strength, CardSchools weakness,
                       String attack1Name, int attack1Strength, ManaTypes [] attack1ManaRequired,
                       String attack2Name, int attack2Strength, ManaTypes [] attack2ManaRequired ) {
        super(x, y, width, height, Sprite,cardBackSprite, name,description,cardSchools,destroyed,pointerID);


        this.level = level;
        this.health = health;
        this.defence = defence;
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

    public CardSchools getWeakness() {
        return weakness;
    }

    public void setDefence(CardSchools weakness) {
        this.weakness = weakness;
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
            defendingCard.health -= (attackingCard.attack1Strength *1.5);
            determineDeathOfMonster(defendingCard);
        }else {
            defendingCard.health -= attackingCard.attack1Strength;
            determineDeathOfMonster(defendingCard);
        }


    }

    // this method just checks if the defending card of the above method has died, then adds it to the graveyard
    //40111707
    //brian
    public void determineDeathOfMonster(MonsterCard dyingCard){
        if(dyingCard.getHealth() <= 0)
        {
          graveYard.addToGraveYard(dyingCard);

            // the card needs to be removed here aswell.
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
