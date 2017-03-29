package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import java.util.HashMap;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;

/**
 * Created by Andrew on 29/03/2017.
 */

public class ManaCounter

{


        // augmented by brian on 28/03/2017 see oldManaCounterConstructorClass
        private int totalMana, unusedMana;
         private HashMap<ManaTypes, Integer> manaCounterHashMap;

        // hashmap is created within the constructor, this allows for it to be populated with the correct manatypes.
        public ManaCounter(int unusedMana, int totalMana) {
             manaCounterHashMap = new HashMap<ManaTypes, Integer>();
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


        public HashMap<ManaTypes, Integer> getManaCounterHashMap() {
            return manaCounterHashMap;
        }

        public void setManaCounterHashMap(HashMap<ManaTypes, Integer> manaCounterHashMap) {
            this.manaCounterHashMap = manaCounterHashMap;
        }

        //40111707
        // brians method
        // initalises the hashmap for the manacounter and sets all of the values to 0 initally.
        public void setupManaCounter(HashMap map) {
            map.put(ManaTypes.ARTS_HUMANITIES_MANA, 0);
            map.put(ManaTypes.BUILT_ENVIRONMENT_MANA, 0);
            map.put(ManaTypes.EEECS_MANA, 0);
            map.put(ManaTypes.ENGINEERING_MANA, 0);
            map.put(ManaTypes.MEDICS_MANA, 0);
            map.put(ManaTypes.SOCIAL_SCIENCES_MANA, 0);
        }

        // 40111707
        // brians method
        // this method returns the value from the hashmap, by using the enum key for it.
        public void getManaCountValue(ManaTypes manaType) {
            getManaCounterHashMap().get(manaType);
        }

        //Add mana to the mana counter
        // brian + matts method
        public void addMana(ManaTypes mana, int amount) {
            manaCounterHashMap.put(mana, amount);
        }

        //Use mana from the pool in for an attack
        public void useMana(int attackCost) {
            if ((unusedMana - attackCost) < 0) {
                //error code about not enough mana
            } else
                unusedMana -= attackCost;
        }

        //Reset the mana at the beginning of every turn
        public void resetMana() {
            unusedMana = totalMana;
        }








}
