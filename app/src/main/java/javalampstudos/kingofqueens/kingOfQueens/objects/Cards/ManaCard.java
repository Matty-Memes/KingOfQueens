package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 * Modified by Matt on 09/03/2017
 * Removed mana value as it is now redundant with the ManaCounter class. Each mana card is worth 1
 */


// Modified by Andrew - 27/03/17
public class manaCard extends basicCard {
    private manaTypes manaType;

    public manaCard(int x, int y, int width, int height, Bitmap Sprite, Bitmap cardbackSprite, String name, String description,
                    boolean destroyed, cardSchools cardSchool, manaTypes manaType, int pointerID)
    {
        super(x,y,width,height,Sprite, cardSchool,destroyed,pointerID);
        this.manaType = manaType;
    }


    public manaTypes getManaType() {
        return manaType;
    }

    public void setManaType(manaTypes manaType) {
        manaType = manaType;
    }


    public void draw(Canvas canvas)

    {

        // canvas.drawBitmap();


    }
}
