package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import java.util.HashMap;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;
import javalampstudos.kingofqueens.kingOfQueens.util.Text;
import javalampstudos.kingofqueens.kingOfQueens.util.andyManaCounter;
import android.graphics.Canvas;

/**
 * Created by Brian on 30/03/2017.
 */

public class ManaCounter

{
         private HashMap<ManaTypes,Integer> unusedManaHashMap;
         private HashMap<ManaTypes, Integer> manaCounterHashMap;



        // hashmaps are created within the constructor, this allows for it to be populated with the correct manatypes.
        public ManaCounter() {
             manaCounterHashMap = new HashMap<ManaTypes, Integer>();
            setupManaCounter(manaCounterHashMap);
            unusedManaHashMap = new HashMap<ManaTypes,Integer>();
            setupUsedMana();
        }

    public HashMap<ManaTypes, Integer> getUnusedManaHashMap() {
        return unusedManaHashMap;
    }

    public void setUnusedManaHashMap(HashMap<ManaTypes, Integer> unusedMana) {
        this.unusedManaHashMap = unusedMana;
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
        public void addMana(ManaTypes mana) {
            manaCounterHashMap.put(mana, manaCounterHashMap.get(mana)+1);
        }

        // 40111707
        // brians method
        //Use mana finds the remainder of mana after an attack has been done.
        public void useMana(int manaCost,ManaTypes mana) {
            if(unusedManaHashMap.get(mana) > 0) {
                if ((manaCounterHashMap.get(mana) - manaCost) > 0) {
                    unusedManaHashMap.put(mana, manaCounterHashMap.get(mana) - manaCost);
                } else {
                    // there is not enough mana for this attack
                }
            } else{
                if(((unusedManaHashMap.get(mana) - manaCost) > 0)){
                    unusedManaHashMap.put(mana, unusedManaHashMap.get(mana) - manaCost);
                }
                else {
                    // the mana is not enough
                }
            }
        }
        // 40111707
        // brians method
        //Reset the unusedMana at the beginning of every turn
        public void setupUsedMana() {
            unusedManaHashMap.putAll(manaCounterHashMap);
        }



        // 40111707
        // 40083349
        //brian + andrews method

    public void drawManaCounter(Canvas canvas) {
        andyManaCounter drawingthingy = new andyManaCounter(100, 380, manaCounterHashMap.get(ManaTypes.EEECS_MANA).toString());
        drawingthingy.draw(canvas);


    }

}
