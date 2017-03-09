package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaTypes;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 */

public class ManaCard extends BasicCard {
    private ManaTypes manaType;

    public ManaCard(int x, int y, int width, int height, Bitmap Sprite, Bitmap cardbackSprite,String name,String description,
                    boolean destroyed, CardSchools cardSchool, ManaTypes manaType)
    {
        super(x,y,width,height,Sprite,cardbackSprite,name,description,cardSchool,destroyed);
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
