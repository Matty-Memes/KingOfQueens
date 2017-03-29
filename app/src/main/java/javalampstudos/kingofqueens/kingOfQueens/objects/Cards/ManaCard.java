package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Andrew on 29/03/2017.
 */

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools;

public class ManaCard extends BasicCard

{

    private ManaTypes manaType;

    public ManaCard(int x, int y, int width, int height, Bitmap Sprite, Bitmap cardbackSprite, String name, String description,
                    boolean destroyed, CardSchools cardSchool, ManaTypes manaType, int pointerID)
    {
        super(x,y,width,height,Sprite, cardSchool,destroyed,pointerID);
        this.manaType = manaType;
    }


    public ManaTypes getManaType() {
        return manaType;
    }

    public void setManaType(ManaTypes manaType) {
        manaType = manaType;
    }


    public void draw(Canvas canvas)

    {

        // canvas.drawBitmap();


    }



}
