package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 06/02/2017.
 */

public class PlaySpace extends GameObject {
    private Deck deck;
    private int hand;


    public PlaySpace(float x, float y, int width, int height,
               Bitmap sprite)
    {
        super(x, y, width, height, sprite);
    }

}
