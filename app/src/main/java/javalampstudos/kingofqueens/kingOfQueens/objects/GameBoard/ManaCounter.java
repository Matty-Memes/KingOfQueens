package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;

/**
 * Created by Matt on 08/03/2017.
 */

public class ManaCounter {
    private int totalMana,unusedMana;
    private ManaTypes manatype;

    public ManaCounter (ManaTypes manatype,int totalMana)
    {
        this.manatype = manatype;
        this.totalMana = totalMana;
        unusedMana = totalMana;
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

    public ManaTypes getManatype() {
        return manatype;
    }

    //Add mana to the mana counter
    public void addMana()
    {
        totalMana++;
        unusedMana=totalMana;
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
