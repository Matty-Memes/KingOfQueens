package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 * Modifed by Matt on 09/03/2017
 * Modified by Matt on 22/04/2017 and Refactored on 04/05/2017
 */

public class SupportCard extends BasicCard

{

    private BuffType buff;
    private StatusEffect statusEffect;
    private boolean active;

    private int buffValue;

    public SupportCard(int x, int y, int width, int height, Bitmap Sprite, boolean player, int id, CardSchools cardSchool, boolean destroyed, int pointerID, int targetX, BuffType buff,int buffValue,StatusEffect statusEffect)
    {
        super(x, y, width, height, Sprite, player, id, cardSchool, destroyed, pointerID, targetX);
        this.buff = buff;
        this.buffValue = buffValue;
        this.statusEffect = statusEffect;
    }

    public BuffType getBuff()
    {
        return buff;
    }

    public void setBuff(BuffType buff) {
            this.buff = buff;
        }

    public void draw(Canvas canvas)

    {
        // canvas.drawBitmap();
    }

    //Matt 40149561
    public void attackBuff(int turns,int attack,MonsterCard buffedCard)
    {
        active=true;
        buffedCard.setAttackValue(buffedCard.attackValue += attack);
        while(active=true)
        {
            // TODO: 01/02/2017 When turn structure is implemented, work out how to increment a counter after a turn
            if(turns==0) {
                active = false;
                buffedCard.setAttackValue(buffedCard.attackValue -= attack);
            }
        }
    }

    //Matt 40149561
    public void defenceBuff(int turns, int defence,MonsterCard buffedCard)
    {
        active = true;
        buffedCard.setDefence(buffedCard.defence += defence);

        while (active = true) {
            if (turns == 0) {
                active = false;
                buffedCard.setDefence(buffedCard.defence -= defence);
            }
        }
    }

    //Matt 40149561
    public void healBuff(int health,MonsterCard healedCard)
    {
        int newHealth = healedCard.getHealth()+ health;
        if (newHealth> healedCard.getMaxHealth())
            newHealth=healedCard.getMaxHealth();
        healedCard.setHealth(newHealth);
    }
}
