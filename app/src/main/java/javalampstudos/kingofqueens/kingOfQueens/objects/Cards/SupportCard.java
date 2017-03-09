package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BuffType;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 */

public class SupportCard extends BasicCard {
    private BuffType buff;
    private int cardAttack,cardDefence;
    private boolean active;

    public SupportCard(int x, int y, int width, int height, Bitmap Sprite,
                       String name,String description, CardSchools cardSchool, boolean destroyed, int pointerID, BuffType buff)
    {
        super(x, y, width, height, Sprite, name,description,cardSchool, destroyed, pointerID);
        this.buff = buff;
    }

    public BuffType getBuff() {
        return buff;
    }

    public void setBuff(BuffType buff) {
        this.buff = buff;
    }

    public void draw(Canvas canvas)

    {

        // canvas.drawBitmap();


    }

    public void attackBuff(int turns,int attack)
    {
        active=true;
        while(active=true)
        {
            cardAttack += attack;
            // TODO: 01/02/2017 When turn structure is implemented, work out how to increment a counter after a turn
        }
        if (!active)
        {
            cardAttack -= attack;
        }
    }

    public void defenceBuff(int turns, int defence)
    {
        active = true;
        while (active = true)
        {
            cardDefence += defence;
        }
        if(!active)
        {
            cardDefence -= defence;
        }
    }
}
