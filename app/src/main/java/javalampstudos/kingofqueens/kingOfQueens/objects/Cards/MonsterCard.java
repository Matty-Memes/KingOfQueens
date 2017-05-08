package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.HashMap;
import java.util.Random;

import javalampstudos.kingofqueens.kingOfQueens.util.randomGenerator;
import javalampstudos.kingofqueens.kingOfQueens.world.LayerViewport;
import javalampstudos.kingofqueens.kingOfQueens.world.ScreenViewport;

/**
 * //We don't actually know who created the orignal file, but Andrew had to recreate some classes back in March
 * Created by Andrew on 29/03/2017.
 */

public class MonsterCard extends BasicCard

{
    private final double attackBonus = 0.3;
    private CardLevel level; // ENUM
    public int health,defence,attackValue,evolutionID,maxHealth,turnsBuffed;
    private StatusEffect statusEffect;
    private boolean isBuffed = false;
    private int statBeforeBuff = 0;

    // holds a list of the mana types required
   // private HashMap<ManaTypes, Integer> attackManaRequirement;
    private int attackManaRequirement;


    // Brian + Matt
    // Modified by Andrew - 27/03/17
    // constructor
    //Matt 22/04/17: Added in a maxHealth variable. realised that there would be a bug if we tried to
    //heal a monster and it didn't have a health limit
    //Matt 05/05/2017: Added in Buff variables to manage buff functions

    public MonsterCard(int x, int y, int width, int height, Bitmap Sprite, boolean player, int id,
                       CardSchools cardSchool, boolean destroyed, int pointerID, int targetX, CardLevel level,
                       int health, int defence, int attackValue, int evolutionID,int attackManaRequirement) {
        super(x, y, width, height, Sprite, player, id, cardSchool, destroyed, pointerID, targetX);
        this.level = level;
        this.health = health;
        this.maxHealth = health;
        this.defence = defence;
        this.attackValue = attackValue;
        this.evolutionID = evolutionID;
        this.attackManaRequirement = attackManaRequirement;
    }

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

    public int getMaxHealth() {return maxHealth;}

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

    public int getAttackManaRequirement()
    {
        return attackManaRequirement;
    }

    public void setAttackManaRequirement(int attackManaRequirement)
    {
        this.attackManaRequirement = attackManaRequirement;
    }

    //    public HashMap<ManaTypes, Integer> getAttackManaRequirement() {
//        return attackManaRequirement;
//    }
//    public void setAttackManaRequirement(HashMap<ManaTypes, Integer> attackManaRequirement) {
//        this.attackManaRequirement = attackManaRequirement;
//    }

    public int getEvolutionID() {
        return evolutionID;
    }

    public void setEvolutionID(int evolutionID) {
        this.evolutionID = evolutionID;
    }

    public StatusEffect getStatusEffect()
    {
        return statusEffect;
    }

    public void setStatusEffect(StatusEffect statusEffect)
    {
        this.statusEffect = statusEffect;
    }

    public int getTurnsBuffed()
    {
        return turnsBuffed;
    }

    public void setTurnsBuffed(int turnsBuffed)
    {
        this.turnsBuffed = turnsBuffed;
    }

    public boolean isBuffed()
    {
        return isBuffed;
    }

    public void setBuffed(boolean buffed)
    {
        isBuffed = buffed;
    }

    public int getStatBeforeBuff()
    {
        return statBeforeBuff;
    }

    public void setStatBeforeBuff(int statBeforeBuff)
    {
        this.statBeforeBuff = statBeforeBuff;
    }

    // draw monster cards to the screen

    // Andrew - 40083349
    public void draw(Canvas canvas)

    {
        super.draw(canvas);

    }

    public void drawOpenWorld(Canvas canvas, LayerViewport layerViewport, ScreenViewport screenViewport)

    {
        super.drawOpenWorld(canvas, layerViewport, screenViewport);

    }

    // Andrew - 40083349
    public void resetPosition (int index)

    {
        if (index == 0)

        {
            this.x = 234;
            this.y = 280;
        }

        if (index == 1)

        {
            this.x = 434;
            this.y = 280;
        }

        if (index == 2)

        {
            this.x = 634;
            this.y = 280;
        }
    }

    // Andrew - 40083349
    public void update()
    {
        super.update();
    }

    // Brian's + Matts Method
    public void attack(MonsterCard defendingCard) {

        randomGenerator rand = new randomGenerator();
        int randomMultiplier = rand.generateRandomNumber();
        if (compareCardAttackBonus(getCardSchool(), defendingCard.getCardSchool())) {
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
        } else if (attackingCard == CardSchools.BUILT_ENVIORNMENT&& defendingCard == CardSchools.ENGINEERING) {
            return true;
        } else if (attackingCard == CardSchools.SOCIAL_SCIENCES && defendingCard == CardSchools.BUILT_ENVIORNMENT) {
            return true;
        } else {
            return false;
        }


    }

    //MATT:
    public boolean evolutionCheck(MonsterCard evolutionCandidateCard)
    {
        if((evolutionID == evolutionCandidateCard.evolutionID)&& ((level == CardLevel.UNDERGRAD) && (evolutionCandidateCard.level == CardLevel.GRAD))
                ||((level==CardLevel.GRAD)&&(evolutionCandidateCard.level==CardLevel.DOCTRATE)))
            return true;
        else
            return false;
    }


    //Matt 40149561
    private void heal(int healing,MonsterCard healedCard)
    {
        int newHealth = healedCard.getHealth()+ healing;
        if (newHealth> healedCard.getMaxHealth())
            newHealth=healedCard.getMaxHealth();
        healedCard.setHealth(newHealth);
    }
    //Matt 40149561
    //Certain monsters have a special attack. This method allows the monster to use it
    private int statBeforeSpecial = -1; //Placeholder int for storing stat values pre-Special
    private boolean specialEnabled = false; //If a special attack modifies values,this allows the values to be reset
    private BuffType statChanged = null;
    private boolean hasRecievedDefence = false; // Stops a card from receiving more than one buff from the Tinkerer/Craftsman
    private void specialAttack(MonsterCard affectedCard) // @param affectedCard - if a special move doesn't affect any monsters, just pass through the monster itself
    {
        switch (this.id)
        {
            case 3:Random rand = new Random();
                int randomNum = rand.nextInt((10-1)+1)+1;
                if(randomNum >=4) { //60% chance of critical
                    specialEnabled = true;
                    statBeforeSpecial = this.attackValue;
                    this.setAttackValue(this.attackValue + 20);
                }
                break;
            case 6: specialEnabled = true;
                statBeforeSpecial = this.attackValue;
                this.setAttackValue(120);
                break;
            case 9: heal(30,affectedCard);
                break;
            case 10:heal(50,affectedCard);
                break;
            case 11:heal(70,affectedCard);
                break;
            case 12:heal(10,affectedCard);
                break;
            case 13:heal(25,affectedCard);
                break;
            case 14:heal(50,affectedCard);
                break;
            case 15:affectedCard.specialEnabled = true;
                statChanged = BuffType.AttackBoost;
                statBeforeSpecial = affectedCard.attackValue;
                affectedCard.setAttackValue(affectedCard.attackValue+20);
                break;
            case 16: affectedCard.specialEnabled = true;
                statChanged = BuffType.AttackBoost;
                statBeforeSpecial = affectedCard.attackValue;
                affectedCard.setAttackValue(affectedCard.attackValue+30);
                break;
            case 25: affectedCard.setStatusEffect(StatusEffect.Sleep);
                break;
            /*This is a tough one
            Mana cost for an allied monster goes down by one*/
            // TODO: 03/05/2017 Come back to this special
            case 27:
                break;
            case 32: if(!affectedCard.hasRecievedDefence)
                affectedCard.setDefence(affectedCard.defence+10);
            else
                System.out.print("Card has already been buffed!");
                break;
            case 33:if(!affectedCard.hasRecievedDefence)
                affectedCard.setDefence(affectedCard.defence+20);
            else
                System.out.print("Card has already been buffed!");
                break;
            default:System.out.print("Card does not have a special Attack");
        }
    }
    //Matt 40149561: When a special buff ends, call this method
    public void endSpecialStats()
    {
        if(specialEnabled)
        {
            if (statChanged == BuffType.AttackBoost)
                setAttackValue(statBeforeSpecial);
            else if (statChanged == BuffType.DefenceBoost)
                setDefence(statBeforeSpecial);
        }
    }
}
