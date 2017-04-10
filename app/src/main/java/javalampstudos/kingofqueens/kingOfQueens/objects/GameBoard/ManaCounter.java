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

    // these values are used for drawing the values to the screen.
    private final int EEECS_Y_VALUE = 370;
    private final int GENERIC_Y_VALUE = 410;
    private final int BUILTENCIRONMENT_Y_VALUE = 330;
    private final int ENGINERRING_Y_VALUE = 250;
    private final int MEDICS_Y_VALUE = 450;
    private final int SOCAILSCIENCE_Y_VALUE = 490;
    private final int ARTSANDHUMANITIES_Y_VALUE = 290;
    private final int X_CO_ORDINATES = 100;


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
            map.put(ManaTypes.GENERIC_MANA, 0);
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



        public String manaValueToString(ManaTypes manaTypes)
        {
         String valueConvert =   manaCounterHashMap.get(manaTypes).toString();

            return valueConvert;
        }
        // 40111707
        // 40083349
        //brian + andrews method
        // this method returns an arrray of converted objects so that i can print them.
    public andyManaCounter[] manaCounterToDrawObject() {
        andyManaCounter eeecs = new andyManaCounter(X_CO_ORDINATES, EEECS_Y_VALUE, manaValueToString(ManaTypes.EEECS_MANA));
        andyManaCounter generic = new andyManaCounter(X_CO_ORDINATES, GENERIC_Y_VALUE, manaValueToString(ManaTypes.GENERIC_MANA));
        andyManaCounter builtEnvironment = new andyManaCounter(X_CO_ORDINATES, BUILTENCIRONMENT_Y_VALUE,manaValueToString(ManaTypes.BUILT_ENVIRONMENT_MANA));
        andyManaCounter enginerring = new andyManaCounter(X_CO_ORDINATES, ENGINERRING_Y_VALUE, manaValueToString(ManaTypes.ENGINEERING_MANA));
        andyManaCounter medics = new andyManaCounter(X_CO_ORDINATES, MEDICS_Y_VALUE, manaValueToString(ManaTypes.MEDICS_MANA));
        andyManaCounter socailScience = new andyManaCounter(X_CO_ORDINATES, SOCAILSCIENCE_Y_VALUE, manaValueToString(ManaTypes.SOCIAL_SCIENCES_MANA));
        andyManaCounter artsAndHumanities = new andyManaCounter(X_CO_ORDINATES, ARTSANDHUMANITIES_Y_VALUE, manaValueToString(ManaTypes.ARTS_HUMANITIES_MANA));


        andyManaCounter manaArray[] = {eeecs,generic,builtEnvironment,enginerring,medics,socailScience,artsAndHumanities};


        return manaArray;
    }

    // 40111707
    // brians method
    // this method preforms draw on all of the objects in the manacounter.
    public void drawValues (andyManaCounter[] values, Canvas canvas)
    {

        for(int i=0; i < values.length; i++)
        {
            values[i].draw(canvas);
        }
    }

}
