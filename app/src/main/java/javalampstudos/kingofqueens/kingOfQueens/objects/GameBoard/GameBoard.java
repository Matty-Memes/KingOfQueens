package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 06/02/2017.
 */

public class GameBoard extends GameObject {

    private PlaySpace player;
    private PlaySpace ai;

    public GameBoard(float x, float y, int width, int height,
                     Bitmap sprite, PlaySpace player,PlaySpace ai)
    {
        super(x, y, width, height, sprite);
        this.player = player;
        this.ai = ai;

    }





}

