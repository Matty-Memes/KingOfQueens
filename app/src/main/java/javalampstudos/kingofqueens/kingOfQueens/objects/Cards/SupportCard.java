package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BuffType;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 * Modifed by Matt on 09/03/2017
 */

public class SupportCard extends BasicCard {
    private BuffType buff;
    private int cardAttack,cardDefence;
    private boolean active;

    public SupportCard(int x, int y, int width, int height, Bitmap Sprite, Bitmap cardbackSprite,
                       String name,String description, CardSchools cardSchool, boolean destroyed,BuffType buff)
    {
        super(x, y, width, height, Sprite,cardbackSprite, name,description,cardSchool, destroyed);
        this.buff = buff;
        this.cardAttack=cardAttack;
        this.cardDefence=cardDefence;
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
