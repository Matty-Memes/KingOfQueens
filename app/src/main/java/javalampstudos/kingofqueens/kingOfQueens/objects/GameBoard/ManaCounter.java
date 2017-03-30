package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import java.util.HashMap;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;

/**
 * Created by Brian on 30/03/2017.
 */

public class ManaCounter

{



        private HashMap<ManaTypes,Integer> unusedMana;
         private HashMap<ManaTypes, Integer> manaCounterHashMap;

        // hashmaps are created within the constructor, this allows for it to be populated with the correct manatypes.
        public ManaCounter() {
             manaCounterHashMap = new HashMap<ManaTypes, Integer>();
            setupManaCounter(manaCounterHashMap);
            unusedMana = new HashMap<ManaTypes,Integer>();
            setupManaCounter(unusedMana);
        }

    public HashMap<ManaTypes, Integer> getUnusedMana() {
        return unusedMana;
    }

    public void setUnusedMana(HashMap<ManaTypes, Integer> unusedMana) {
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

           //Add mana to the mana counter
        // brian method
        public void addMana(ManaTypes mana, int amount) {
            manaCounterHashMap.put(mana, amount);
        }

        // 40111707
        // brians method
        //Use mana from the pool in for an attack
        public void useMana(int manaCost,ManaTypes mana) {
            if((manaCounterHashMap.get(mana)-manaCost) > 0){
                unusedMana.put(mana, manaCounterHashMap.get(mana) - manaCost);
            }
            else{
                // there is not enough mana for this attack
            }
        }
        // 40111707
        // brians method
        //Reset the unusedMana at the beginning of every turn
        public void resetMana() {
            setupManaCounter(unusedMana);
        }








}
