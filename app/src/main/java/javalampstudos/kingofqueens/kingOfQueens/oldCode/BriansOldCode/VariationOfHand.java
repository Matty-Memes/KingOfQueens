package javalampstudos.kingofqueens.kingOfQueens.oldCode.BriansOldCode;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;

/**
 * Created by brian on 28/04/2017.
 */

public class VariationOfHand {

/*
    private final int MONSTER_AMOUNT =2;
    private final int MANA_AMOUNT =2;
    private final int SUPPORT_AMOUNT =1;
    private final int BOOLEAN_FLAGS_AMOUNT = 6;

    private MonsterCard monsertCards [];
    boolean flags [];
    private ManaCard manaCards [];
    private SupportCard supportcardOne;

    public HandChange() {


        monsertCards = new MonsterCard[MONSTER_AMOUNT];
        manaCards = new ManaCard[MANA_AMOUNT];
        flags = new boolean[BOOLEAN_FLAGS_AMOUNT];


        // all of the cards need to be set up in here to the correct positions
    }


    public MonsterCard[] getMonsertCards() {
        return monsertCards;
    }

    public void setMonsertCards(MonsterCard[] monsertCards) {
        this.monsertCards = monsertCards;
    }

    public boolean[] getFlags() {
        return flags;
    }

    public void setFlags(boolean[] flags) {
        this.flags = flags;
    }

    public ManaCard[] getManaCards() {
        return manaCards;
    }

    public void setManaCards(ManaCard[] manaCards) {
        this.manaCards = manaCards;
    }

    public void setSupportcardOne(SupportCard supportcardOne) {
        this.supportcardOne = supportcardOne;
    }

    //40111707
    // brians method
    // this method is for retrieving a monsterCArd from the deck and then adding it to the hand.
    public void addMonsterToHand(MonsterCard card)
    {
        if(checkIfSpaceForMonster()) {
            // this runs through each of the zones and if it finds a zone that isnt active, then it allocates a card to there.
            for(int i=0; i < MONSTER_AMOUNT; i ++)
            {
                if(!flags[i])
                {
                    monsertCards[i] = card;
                    flags[i] = true;
                }

            }


        }
        else {
            // cant add the card here
        }
    }




    //40111707
    // brians method
    // this method is for returning true if there is room for a monsterCard to to be put in the hand.
    public boolean checkIfSpaceForMonster()
    {
        for(int i=0;i< MONSTER_AMOUNT; i++)
        {
            if(flags[i])
            {
                return false;
            }
        }
        return true;
    }

    //40111707
    // brians method
    // this method is for returning true if there is room for a manaCard to to be put in the hand.
    public boolean checkIfSpaceForManaCard()
    {
        for(int i=0; i < MANA_AMOUNT; i++)
        {
            if(flags[i + MONSTER_AMOUNT])
            {
                return false;
            }
        }
        return true;
    }

    //40111707
    // brians method
    // this method is for retrieving a mana card form the deck and adding it to the hand.
    public void addManaCardToHand(ManaCard card)
    {
        if(checkIfSpaceForManaCard())
        {
            for(int i=0; i < MANA_AMOUNT; i ++)
            {
                if(!flags[i + MONSTER_AMOUNT])
                {
                    manaCards[i+ MONSTER_AMOUNT] = card;
                    flags[i+MONSTER_AMOUNT] = true;
                }

            }


        }
        else {
            // cant add the card here
        }

    }
    //40111707
    // brians method
    // this method is for adding a support card to the hand from the deck.
    public void addSupportCardToHand(SupportCard supportCard)
    {
        if(!flags[6])
        {
            setSupportcardOne(supportCard);
            flags[6] = true;
        }
        else
        {
            // cant add the card here
        }
    }


    public void removeMonsterCard(MonsterCard card)
    {
        // you need to take the card that was there out of the hand, no array. search all poitions.

    }*/
}
