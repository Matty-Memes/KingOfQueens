package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.HashMap;

import javalampstudos.kingofqueens.kingOfQueens.util.randomGenerator;

/**
 * Created by Andrew on 29/03/2017.
 */

public class MonsterCard extends BasicCard

{
        private final double attackBonus = 0.3;
        private CardLevel level; // ENUM
        private int health,defence,attackValue,evolutionID;
        // holds a list of the mana types required
         private HashMap<ManaTypes, Integer> attackManaRequirement;


        // Brian + Matt
        // Modified by Andrew - 27/03/17
        // constructor

    public MonsterCard(int x, int y, int width, int height, Bitmap Sprite, boolean player, int id, CardSchools cardSchool,
                       boolean destroyed, int pointerID,CardLevel level, int health, int defence,
                       int attackValue,int evolutionID, HashMap<ManaTypes, Integer> attackManaRequirement) {
        super(x, y, width, height, Sprite, player, id, cardSchool, destroyed, pointerID);
        this.id = id;
        this.level = level;
        this.health = health;
        this.defence = defence;
        this.attackValue = attackValue;
        this.evolutionID = evolutionID;
        this.attackManaRequirement = attackManaRequirement;
    }

    // Set the source and destination rects



    // ADD GETTERS AND SETTERS //

        public double getAttackBonus() {
            return attackBonus;
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

        public int getDefence() {
            return defence;
        }

        public void setDefence(int defence) {
            this.defence = defence;
        }

        public int getAttackValue() {
            return attackValue;
        }

        public void setAttackValue(int attackValue) {
            this.attackValue = attackValue;
        }



        public HashMap<ManaTypes, Integer> getAttackManaRequirement() {
            return attackManaRequirement;
        }


        public void setAttackManaRequirement(HashMap<ManaTypes, Integer> attackManaRequirement) {
            this.attackManaRequirement = attackManaRequirement;
        }

    public int getEvolutionID() {
        return evolutionID;
    }

    public void setEvolutionID(int evolutionID) {
        this.evolutionID = evolutionID;
    }


    // draw monster cards to the screen

        public void draw(Canvas canvas)

        {
            super.draw(canvas);

        }



    // Brian's + Matts Method
    public void attack(MonsterCard defendingCard) {

        //monsterCard target = this.monsterCard;
        //Matt: I need to get touch input to tell the class what monster to attack.

        randomGenerator rand = new randomGenerator();
        int randomMultiplier = rand.generateRandomNumber();
        if (compareCardAttackBonus(getCardSchool(), defendingCard.getCardSchool()) == true) {
            defendingCard.health -= (getAttackValue() * (attackBonus * randomMultiplier));

        } else {
            defendingCard.health -= getAttackValue();

        }


    }



    // gets both cards school types and then goes through the if statements in order to find if the combination fits
    // if the combination fits then it returns true, this then allows for the 1.5X multiplier to be added to the attack.
    // 40111707
    // brian
    public boolean compareCardAttackBonus(CardSchools attackingCard, CardSchools defendingCard) {
        if (attackingCard == CardSchools.MEDICS && defendingCard == CardSchools.EEECS) {
            return true;
        } else if (attackingCard == CardSchools.EEECS && defendingCard == CardSchools.ARTS_HUMANITIES) {
            return true;
        } else if (attackingCard == CardSchools.ARTS_HUMANITIES && defendingCard == CardSchools.MEDICS) {
            return true;
        } else if (attackingCard == CardSchools.ENGINEERING && defendingCard == CardSchools.SOCIAL_SCIENCES) {
            return true;
        } else if (attackingCard == CardSchools.BUILT_ENVIORNMENT && defendingCard == CardSchools.ENGINEERING) {
            return true;
        } else if (attackingCard == CardSchools.SOCIAL_SCIENCES && defendingCard == CardSchools.BUILT_ENVIORNMENT) {
            return true;
        } else {
            return false;
        }


    }

    //MATT:
    public boolean evolutionCheck(MonsterCard currentCard, MonsterCard evolutionCandidateCard)
    {
        if((currentCard.evolutionID == evolutionCandidateCard.evolutionID)&& ((currentCard.level == CardLevel.UNDERGRAD) && (evolutionCandidateCard.level == CardLevel.GRAD))
                ||((currentCard.level==CardLevel.GRAD)&&(evolutionCandidateCard.level==CardLevel.DOCTRATE)))
            return true;
        else
            return false;
    }



}
