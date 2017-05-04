package javalampstudos.kingofqueens.kingOfQueens.world;

import javalampstudos.kingofqueens.kingOfQueens.objects.Entities.littleMan;

/**
 * Created by Nathan on 09/02/2017.
 */

public class LayerViewport {

    public float x;
    public float y;
    public float width;
    public float height;

    public LayerViewport(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void update(LayerViewport layerViewport, littleMan player) {

        layerViewport.x = player.x;
        layerViewport.y = player.y;

    }
}
