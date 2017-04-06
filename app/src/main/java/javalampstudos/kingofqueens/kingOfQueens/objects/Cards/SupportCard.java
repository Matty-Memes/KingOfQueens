package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Andrew on 29/03/2017.
 */

public class SupportCard extends BasicCard

{

        private BuffType buff;
        private boolean active;

        private int cardAttack, cardDefence;

        public SupportCard(int x, int y, int width, int height, Bitmap Sprite, boolean player, CardSchools cardSchool, boolean destroyed, int pointerID , BuffType buff)
        {
            super(x, y, width, height, Sprite, player, cardSchool, destroyed,pointerID);
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
