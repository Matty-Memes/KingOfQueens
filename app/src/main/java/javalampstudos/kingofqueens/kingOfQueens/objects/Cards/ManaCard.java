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
    private ManaTypes ManaType;
    private int ManaValue;

    public ManaCard(int x, int y, int width, int height, Bitmap Sprite, String name,String description, boolean destroyed, CardSchools cardSchool, ManaTypes manaType, int manaValue) {
        super(x,y,width,height,Sprite,name,description,cardSchool,destroyed);
        ManaType = manaType;
        ManaValue = manaValue;
    }


    public ManaTypes getManaType() {
        return ManaType;
    }

    public void setManaType(ManaTypes manaType) {
        ManaType = manaType;
    }

    public int getManaValue() {
        return ManaValue;
    }

    public void setManaValue(int manaValue) {
        ManaValue = manaValue;
    }

    public void draw(Canvas canvas)

    {

        // canvas.drawBitmap();


    }
}
