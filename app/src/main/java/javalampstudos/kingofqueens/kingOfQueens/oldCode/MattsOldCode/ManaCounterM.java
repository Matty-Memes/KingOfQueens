package javalampstudos.kingofqueens.kingOfQueens.oldCode.MattsOldCode;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;
        import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;
/**
 * Created by Matt on 30/03/2017.
 */
public class ManaCounterM {
    private ManaTypes manaType;
    private int totalMana,usedMana;
    public ManaCounterM(ManaTypes manaType, int totalMana) {
        this.manaType = manaType;
        this.totalMana = totalMana;
        usedMana = totalMana;
    }
    //getters.
    public ManaTypes getManaType() {
        return manaType;
    }
    public int getTotalMana() {
        return totalMana;
    }
    public int getUsedMana() {
        return usedMana;
    }
    //setters
    public void setManaType(ManaTypes manaType) {
        this.manaType = manaType;
    }
    //When a mana card is played, the total mana increments
    public void addToTotal()
    {
        totalMana++;
        //Because mana is added before it can be used, the used mana must be updated to reflect any changes
        usedMana= totalMana;
    }
    //When an attack is performed, the mana cost of the attack is subtracted from the available mana
    public void useMana(int cost)
    {
        if(usedMana-cost<0)
            ;//not enough mana need error handling
        else
            usedMana-=cost;
    }
    //Called at the end of the turn. Resets the mana.
    public void resetMana()
    {
        usedMana=totalMana;
    }
}