package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.BasicCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 13/03/2017.
 */

public class Hand extends GameObject {
    private BasicCard[] hand = new BasicCard[10];
    private final int MAXHANDSIZE = 10;
    private int currentHandSize;


    public Hand(float x, float y, int width, int height,
                      Bitmap sprite) {
        super(x,y,width,height,null);

    }





}
