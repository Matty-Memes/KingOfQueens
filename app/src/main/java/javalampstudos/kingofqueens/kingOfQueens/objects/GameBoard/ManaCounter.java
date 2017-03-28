package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import java.util.HashMap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.cardSchools;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.manaTypes;

/**
 * Created by Matt on 08/03/2017.
 */

public class ManaCounter {

    // augmented by brian on 28/03/2017 see oldManaCounterConstructorClass
    private int totalMana,unusedMana;
    private HashMap<manaTypes,Integer> manaCounter = new HashMap<manaTypes,Integer>();

    public ManaCounter(HashMap<manaTypes, Integer> manaCounter, int unusedMana, int totalMana) {
        setupManaCounter();
        this.unusedMana = unusedMana;
        this.totalMana = totalMana;
    }

    public int getTotalMana() {
        return totalMana;
    }

    public void setTotalMana(int totalMana) {
        this.totalMana = totalMana;
    }

    public int getUnusedMana() {
        return unusedMana;
    }

    public void setUnusedMana(int unusedMana) {
        this.unusedMana = unusedMana;
    }

    public HashMap<manaTypes, Integer> getManaCounter() {
        return manaCounter;
    }

    public void setManaCounter(HashMap<manaTypes, Integer> manaCounter) {
        this.manaCounter = manaCounter;
    }

    //40111707
    // brians method
    // initalises the hashmap for the manacounter and sets all of the values to 0 initally.
    public void setupManaCounter(){
        manaCounter.put(manaTypes.ARTS_HUMANITIES_MANA,0);
        manaCounter.put(manaTypes.BUILT_ENVIRONMENT_MANA,0);
        manaCounter.put(manaTypes.EEECS_MANA,0);
        manaCounter.put(manaTypes.ENGINEERING_MANA,0);
        manaCounter.put(manaTypes.MEDICS_MANA,0);
        manaCounter.put(manaTypes.SOCIAL_SCIENCES_MANA,0);
    }
    //Add mana to the mana counter
    // brian + matts method
    public void addMana(manaTypes mana, int amount)
    {
        manaCounter.put(mana,amount);
    }

    //Use mana from the pool in for an attack
    public void useMana(int attackCost)
    {
        if ((unusedMana-attackCost)<0)
        {
            //error code about not enough mana
        }
        else
            unusedMana-=attackCost;
    }

    //Reset the mana at the beginning of every turn
    public void resetMana()
    {
        unusedMana=totalMana;
    }
}
