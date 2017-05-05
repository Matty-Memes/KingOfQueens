package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 06/02/2017.
 * The idea was that the player and the AI would each have a playspace. This would be contained in the gameBoard
 */

public class GameBoard extends GameObject {

    private PlaySpace playerSpace;
    private PlaySpace ai;

    public GameBoard(float x, float y, int width, int height,
                     Bitmap sprite, boolean player, PlaySpace playerSpace,PlaySpace ai)
    {
        super(x, y, width, height, sprite, player);
        this.playerSpace = playerSpace;
        this.ai = ai;

    }





}

