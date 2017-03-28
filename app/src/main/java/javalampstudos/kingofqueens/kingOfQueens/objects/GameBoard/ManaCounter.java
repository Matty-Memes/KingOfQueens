package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import java.util.HashMap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.manaTypes;

/**
 * Created by Matt on 08/03/2017.
 */

public class ManaCounter {

    // augmented by brian on 28/03/2017 see oldManaCounterConstructorClass
    private int totalMana,unusedMana;
    private HashMap<manaTypes,Integer> manaCounterHashMap;
// hashmap is created within the constructor, this allows for it to be populated with the correct manatypes.
    public ManaCounter(int unusedMana, int totalMana) {
        manaCounterHashMap = new HashMap<manaTypes,Integer>();
        setupManaCounter(manaCounterHashMap);
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

    public HashMap<manaTypes, Integer> getManaCounterHashMap() {
        return manaCounterHashMap;
    }

    public void setManaCounterHashMap(HashMap<manaTypes, Integer> manaCounterHashMap) {
        this.manaCounterHashMap = manaCounterHashMap;
    }

    //40111707
    // brians method
    // initalises the hashmap for the manacounter and sets all of the values to 0 initally.
    public void setupManaCounter(HashMap map){
        map.put(manaTypes.ARTS_HUMANITIES_MANA,0);
        map.put(manaTypes.BUILT_ENVIRONMENT_MANA,0);
        map.put(manaTypes.EEECS_MANA,0);
        map.put(manaTypes.ENGINEERING_MANA,0);
        map.put(manaTypes.MEDICS_MANA,0);
        map.put(manaTypes.SOCIAL_SCIENCES_MANA,0);
    }
// 40111707
    // brians method
    // this method returns the value from the hashmap, by using the enum key for it.
    public void getManaCountValue(manaTypes manaType)
    {
        getManaCounterHashMap().get(manaType);
    }

    //Add mana to the mana counter
    // brian + matts method
    public void addMana(manaTypes mana, int amount)
    {
        manaCounterHashMap.put(mana,amount);
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
