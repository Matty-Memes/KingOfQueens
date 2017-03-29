package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 * Modifed by Matt on 09/03/2017
 */


// Modified by Andrew - 27/03/17
public class supportCard extends basicCard {
    private buffType buff;
    private boolean active;

    private int cardAttack, cardDefence;

    public supportCard(int x, int y, int width, int height, Bitmap Sprite, cardSchools cardSchool, boolean destroyed, int pointerID , buffType buff)
    {
        super(x, y, width, height, Sprite, cardSchool, destroyed,pointerID);
        this.buff = buff;

    }

    public buffType getBuff() {
        return buff;
    }

    public void setBuff(buffType buff) {
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
            if(turns==0)
                active=false;
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
            if(turns==0)
                active=false;
        }
        if(!active)
        {
            cardDefence -= defence;
        }
    }
}
