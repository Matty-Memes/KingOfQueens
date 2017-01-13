package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardLevel;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardTypes;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 */

public class MonsterCard extends BasicCard {
    private CardTypes CardSchool; // use ENUM for this
    private String attackinfoLine1;
    private String attackinfoLine2;
    private CardLevel level; // use ENUM for this
    private int health;
    private int attack;
    private int defence;


    public MonsterCard(int x, int y, int width, int height, Bitmap Sprite, String name, CardTypes cardType, String attack1, String attack2, CardLevel level, int health, int attack, int defence) {
        super(x, y, width, height, Sprite, name);
        this.CardSchool = cardType;
        this.attackinfoLine1 = attack1;
        this.attackinfoLine2 = attack2;
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.defence = defence;
    }

    public CardTypes getCardType() {
        return CardSchool;
    }

    public void setCardType(CardTypes cardType) {
        CardSchool = cardType;
    }

    public String getAttack1() {
        return attackinfoLine1;
    }

    public void setAttack1(String attack1) {
        this.attackinfoLine1 = attack1;
    }

    public String getAttack2() {
        return attackinfoLine2;
    }

    public void setAttack2(String attack2) {
        this.attackinfoLine2 = attack2;
    }

    public CardLevel getLevel() {
        return level;
    }

    public void setLevel(CardLevel level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
