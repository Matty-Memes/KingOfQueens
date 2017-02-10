package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 06/02/2017.
 */

public class GameBoard extends GameObject {

    private Rect playSpace;

    public GameBoard(float x, float y, int width, int height,
                     Bitmap sprite, Rect playSpace,int deck[], int hand,int cardSpace, int playerLife, int aiLife, int manaPool)
    {
        super(x, y, width, height, sprite);
        this.playSpace = playSpace;

    }


}

