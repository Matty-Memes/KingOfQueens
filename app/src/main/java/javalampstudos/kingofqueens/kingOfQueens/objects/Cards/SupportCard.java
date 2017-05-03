package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Andrew on 29/03/2017.
 * Modified by Matt on 22/04/2017
 */

public class SupportCard extends BasicCard

{

        private BuffType buff;
        private boolean active;

        private int cardAttack, cardDefence;

        public SupportCard(int x, int y, int width, int height, Bitmap Sprite, boolean player, int id, CardSchools cardSchool, boolean destroyed, int pointerID, int targetX, BuffType buff)
        {
            super(x, y, width, height, Sprite, player, id, cardSchool, destroyed, pointerID, targetX);
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

        public void attackBuff(int turns,int attack,MonsterCard buffedCard)
        {
            active=true;
            buffedCard.setAttackValue(cardAttack += attack);
            while(active=true)
            {
                // TODO: 01/02/2017 When turn structure is implemented, work out how to increment a counter after a turn
                if(turns==0)
                    active=false;
                cardAttack -= attack;
                buffedCard.setAttackValue(cardAttack);
            }
        }

        public void defenceBuff(int turns, int defence,MonsterCard buffedCard)
        {
            active = true;
            buffedCard.setDefence(cardDefence += defence);

            while (active = true)
            {
                if(turns==0)
                    active=false;
                cardDefence -= defence;
                buffedCard.setDefence(cardDefence);
            }
        }

        public void healBuff(int health,MonsterCard healedCard)
        {
            int newHealth = healedCard.getHealth()+ health;
            if (newHealth> healedCard.getMaxHealth())
                newHealth=healedCard.getMaxHealth();
            healedCard.setHealth(newHealth);
        }
}
