package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Andrew on 29/03/2017.
 */

// Local Imports

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;

public class ManaCard extends BasicCard

{
    // cardSchools already serves this purpose
    private ManaTypes manaType;


    public ManaCard(int x, int y, int width, int height, Bitmap Sprite, boolean player, int id,
                    ManaTypes manaType, CardSchools cardSchool, boolean destroyed, int pointerID, int targetX)

    {
       super(x,  y, width, height, Sprite, player, id,
        cardSchool, destroyed, pointerID, targetX);

         this.manaType = manaType;

    }

    public ManaTypes getManaType() {
        return manaType;
    }

    public void setManaType(ManaTypes manaType) {
        this.manaType = manaType;
    }

    public void draw(Canvas canvas)
    {
      super.draw(canvas);
    }



}
