package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by brian on 12/04/2017.
 */

public class HandChange extends GameObject {

    private final int X_POSITION_CARD_ONE = 110;

    MonsterCard monsterOne,monsterTwo,monsterThree;
    boolean monsterOneFLag,monsterTwoFlag,monsterThreeFlag,manaOneFlag,manaTwoFlag,supportCardFlag;
    ManaCard manacardOne,manacardTwo;
    SupportCard supportcardOne;

    public HandChange(float x, float y, int width, int height, Bitmap sprite, boolean player) {
        super(x, y, width, height, sprite, player);
        this.monsterOne = monsterOne; // initalise to downfacing card
        monsterOneFLag =false;
        monsterTwoFlag = false;
        monsterThreeFlag = false;
        manaOneFlag=false;
        manaTwoFlag = false;
        supportCardFlag =false;

    }

    public boolean isSupportCardFlag() {
        return supportCardFlag;
    }

    public void setSupportCardFlag(boolean supportCardFlag) {
        this.supportCardFlag = supportCardFlag;
    }

    public boolean isManaOneFlag() {
        return manaOneFlag;
    }

    public void setManaOneFlag(boolean manaOneFlag) {
        this.manaOneFlag = manaOneFlag;
    }

    public boolean isManaTwoFlag() {
        return manaTwoFlag;
    }

    public void setManaTwoFlag(boolean manaTwoFlag) {
        this.manaTwoFlag = manaTwoFlag;
    }

    public boolean isMonsterOneFLag() {
        return monsterOneFLag;
    }

    public void setMonsterOneFLag(boolean monsterOneFLag) {
        this.monsterOneFLag = monsterOneFLag;
    }

    public boolean isMonsterTwoFlag() {
        return monsterTwoFlag;
    }

    public void setMonsterTwoFlag(boolean monsterTwoFlag) {
        this.monsterTwoFlag = monsterTwoFlag;
    }

    public boolean isMonsterThreeFlag() {
        return monsterThreeFlag;
    }

    public void setMonsterThreeFlag(boolean monsterThreeFlag) {
        this.monsterThreeFlag = monsterThreeFlag;
    }

    public MonsterCard getMonsterOne() {
        return monsterOne;
    }

    public void setMonsterOne(MonsterCard monsterOne) {
        this.monsterOne = monsterOne;
    }

    public MonsterCard getMonsterTwo() {
        return monsterTwo;
    }

    public void setMonsterTwo(MonsterCard monsterTwo) {
        this.monsterTwo = monsterTwo;
    }

    public MonsterCard getMonsterThree() {
        return monsterThree;
    }

    public void setMonsterThree(MonsterCard monsterThree) {
        this.monsterThree = monsterThree;
    }

    public ManaCard getManacardOne() {
        return manacardOne;
    }

    public void setManacardOne(ManaCard manacardOne) {
        this.manacardOne = manacardOne;
    }

    public ManaCard getManacardTwo() {
        return manacardTwo;
    }

    public void setManacardTwo(ManaCard manacardTwo) {
        this.manacardTwo = manacardTwo;
    }

    public SupportCard getSupportcardOne() {
        return supportcardOne;
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
                if (!isMonsterOneFLag()) {
                    setMonsterOne(card);
                    setMonsterOneFLag(true);
                } else if (!isMonsterTwoFlag()) {
                    setMonsterTwo(card);
                    setMonsterTwoFlag(true);
                } else if (!isMonsterThreeFlag()) {
                    setMonsterThree(card);
                    setMonsterThreeFlag(true);
                } else {
                    // cant add the card here
                }

            }
        }


        //40111707
        // brians method
        // this method is for returning true if there is room for a monsterCard to to be put in the hand.
    public boolean checkIfSpaceForMonster()
    {
        if(isMonsterOneFLag() && isMonsterTwoFlag() && isMonsterThreeFlag())
        {
            return false;
        }
        return true;
    }

    //40111707
    // brians method
    // this method is for returning true if there is room for a manaCard to to be put in the hand.
    public boolean checkIfSpaceForManaCard()
    {
        if(isManaOneFlag() && isManaTwoFlag())
        {
            return false;
        }
        return true;
    }

        //40111707
        // brians method
        // this method is for retrieving a mana card form the deck and adding it to the hand.
        public void addManaCardToHand(ManaCard manacard)
        {
            if(checkIfSpaceForManaCard())
            {
                if (!isManaOneFlag()) {
                    setManacardOne(manacard);
                    setManaOneFlag(true);
                } else if (!isManaTwoFlag()) {
                    setManacardTwo(manacard);
                    setManaTwoFlag(true);
                } else {
                    // cant add the card here
                }
            }
        }
        //40111707
        // brians method
        // this method is for adding a support card to the hand from the deck.
        public void addSupportCardToHand(SupportCard supportCard)
        {
            if(!isSupportCardFlag())
            {
                setSupportcardOne(supportCard);
                setSupportCardFlag(true);
            }
            else
            {
                // cant add the card here
            }
        }


        public void removeMonsterCard(MonsterCard card)
        {
            // you need to take the card that was there out of the hand, no array. search all poitions.

        }
}
