package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;

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
    private int turns = 3;

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
    // TODO: 05/05/2017 Try and make this a switch statement
    public void doBuff(MonsterCard affectedCard)
    {
        if (this.buff == BuffType.AttackBoost )
            attackBuff(affectedCard);
        else if (this.buff ==BuffType.DefenceBoost)
            defenceBuff(affectedCard);
        else if (this.buff ==BuffType.Heal)
            healBuff(affectedCard);
        else if (this.buff == BuffType.EnvokeStatus)
            envokeStatus(affectedCard);
        else if (this.buff == BuffType.CureStatus)
            cureStatus(affectedCard);
    }

    //Matt 40149561
    public void attackBuff(MonsterCard buffedCard)
    {
            buffedCard.setStatBeforeBuff(buffedCard.attackValue);
            buffedCard.setAttackValue(buffedCard.attackValue += this.buffValue);
            buffedCard.setTurnsBuffed(turns);
            buffedCard.setBuffed(true);


        //05/05/2017 Going to put this in the turn logic
//            // TODO: 01/02/2017 When turn structure is implemented, work out how to increment a counter after a turn
//            if(turns==0) {
//                active = false;
//                buffedCard.setAttackValue(buffedCard.attackValue -= attack);
//            }

    }

    //Matt 40149561
    public void defenceBuff(MonsterCard buffedCard)
    {
            buffedCard.setStatBeforeBuff(buffedCard.defence);
            buffedCard.setDefence(buffedCard.defence += this.buffValue);
            buffedCard.setTurnsBuffed(turns);
            buffedCard.setBuffed(true);
    }

    //Matt 40149561
    public void healBuff(MonsterCard healedCard)
    {
        int newHealth = healedCard.getHealth()+ this.buffValue;
        if (newHealth> healedCard.getMaxHealth())
            newHealth=healedCard.getMaxHealth();
        healedCard.setHealth(newHealth);
    }

    //Matt 40149561
    public void cureStatus(MonsterCard affectedCard)
    {
            affectedCard.setStatusEffect(StatusEffect.None);
    }

    //Matt 40149561
    public void envokeStatus(MonsterCard affectedCard)
    {
            affectedCard.setStatusEffect(this.statusEffect);
    }

}
