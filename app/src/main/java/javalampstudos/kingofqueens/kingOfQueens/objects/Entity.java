package javalampstudos.kingofqueens.kingOfQueens.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import javalampstudos.kingofqueens.kingOfQueens.world.LayerViewport;
import javalampstudos.kingofqueens.kingOfQueens.world.ScreenViewport;

/**
 * Created by 40083349 on 13/01/2017.
 */

// Should extend game object

public class Entity extends GameObject

{


    public Entity(float x, float y, float width, float height, Bitmap sprite, boolean player) {
        super(x, y, width, height, sprite, player);
    }

    public void draw(Canvas canvas, LayerViewport layerViewport, ScreenViewport screenViewport)

    {
        super.drawOpenWorld(canvas, layerViewport, screenViewport);


    }


}
